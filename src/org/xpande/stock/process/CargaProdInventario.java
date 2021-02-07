package org.xpande.stock.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocator;
import org.compiere.model.MWarehouse;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xpande.core.model.MZDataFiltro;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Proceso que carga productos en documento de inventario de stock.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 12/11/20.
 */
public class CargaProdInventario extends SvrProcess {

    private MInventory inventory;

    @Override
    protected void prepare() {

        this.inventory = new MInventory(getCtx(), getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        String sql, action;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            // Elimino lineas anteriores del inventario
            action = " delete from m_inventoryline where m_inventory_id =" + this.inventory.get_ID();
            DB.executeUpdateEx(action, get_TrxName());

            MWarehouse warehouse = (MWarehouse) this.inventory.getM_Warehouse();
            MLocator locator = MLocator.getDefault(warehouse);

            if ((locator == null) || (locator.get_ID() <= 0)){
                return "@Error@ " + " No hay Ubicación predeterminada definida para este Almacén";
            }

            String whereClause = "";

            // Filtro de Productos si tengo filtro de informe seteado.
            int dataFiltroID = this.inventory.get_ValueAsInt("Z_DataFiltro_ID");
            if (dataFiltroID > 0){
                MZDataFiltro dataFiltro = new MZDataFiltro(getCtx(), dataFiltroID, null);
                if (dataFiltro.getM_Product_Category_ID() > 0){
                    whereClause += " and prod.m_product_category_id =" + dataFiltro.getM_Product_Category_ID();
                }

                // Retail
                if (dataFiltro.get_ValueAsInt("Z_ProductoSeccion_ID") > 0){
                    whereClause += " and prod.z_productoseccion_id =" + dataFiltro.get_ValueAsInt("Z_ProductoSeccion_ID");
                }
                if (dataFiltro.get_ValueAsInt("Z_ProductoRubro_ID") > 0){
                    whereClause += " and prod.z_productorubro_id =" + dataFiltro.get_ValueAsInt("Z_ProductoRubro_ID");
                }

                if (dataFiltro.get_ValueAsInt("Z_ProductoFamilia_ID") > 0){
                    whereClause += " and prod.z_productofamilia_id =" + dataFiltro.get_ValueAsInt("Z_ProductoFamilia_ID");
                }

                if (dataFiltro.get_ValueAsInt("Z_ProductoSubfamilia_ID") > 0){
                    whereClause += " and prod.z_productosubfamilia_id =" + dataFiltro.get_ValueAsInt("Z_ProductoSubfamilia_ID");
                }

                sql = " select count(*) from Z_DataFiltroProd where Z_DataFiltro_ID =" + dataFiltroID;
                int contadorProd = DB.getSQLValueEx(get_TrxName(), sql);
                if (contadorProd > 0) {
                    whereClause += " and prod.m_product_id in (select distinct(m_product_id) " +
                            " from Z_DataFiltroProd where Z_DataFiltro_ID =" + dataFiltroID + ") ";
                }
            }

            sql = " select prod.m_product_id " +
                    " from m_product prod " +
                    " where prod.isactive ='Y' " +
                    " and prod.isstocked ='Y' " +
                    " and prod.producttype ='I' " + whereClause +
                    " order by name ";

        	pstmt = DB.prepareStatement(sql, get_TrxName());
        	rs = pstmt.executeQuery();

        	while(rs.next()){

                // Genero linea de inventario para esta linea de interface
                MInventoryLine inventoryLine = new MInventoryLine(getCtx(), 0, get_TrxName());
                inventoryLine.setM_Inventory_ID(this.inventory.get_ID());
                inventoryLine.setAD_Org_ID(this.inventory.getAD_Org_ID());
                inventoryLine.setM_Product_ID(rs.getInt("m_product_id"));
                inventoryLine.setM_AttributeSetInstance_ID(0);
                inventoryLine.setM_Locator_ID(locator.get_ID());

                // Cantidad en sistema (QtyOnHand)
                sql = " select qtyonhand from zv_stk_warlocproddisp " +
                        " where m_locator_id =" + inventoryLine.getM_Locator_ID() +
                        " and m_product_id =" + inventoryLine.getM_Product_ID();
                BigDecimal qtyOnHand = DB.getSQLValueBDEx(null, sql);
                if (qtyOnHand == null) qtyOnHand = Env.ZERO;

                //inventoryLine.setQtyCount(qtyOnHand);
                inventoryLine.setQtyCount(Env.ZERO);
                inventoryLine.setQtyBook(qtyOnHand);
                inventoryLine.saveEx();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }

        return "OK";
    }

}
