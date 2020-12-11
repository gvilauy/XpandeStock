package org.xpande.stock.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.impexp.ImpFormat;
import org.compiere.model.*;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.xpande.stock.model.I_Z_InventoryFile;
import org.xpande.stock.model.MZInventoryFile;
import org.xpande.stock.model.X_Z_InventoryFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 12/11/20.
 */
public class CargaInventarioFile extends SvrProcess {

    MInventory inventory;

    @Override
    protected void prepare() {

        this.inventory = new MInventory(getCtx(), getRecord_ID(), get_TrxName());

    }

    @Override
    protected String doIt() throws Exception {

        String message = this.executeInterface();

        if (message != null){
            return "@Error@ " + message;
        }


        return "OK";
    }


    /***
     * Metodo que ejecuta el proceso de interface desde archivo para carga de productos para el inventario físico.
     * Xpande. Created by Gabriel Vila on 12/11/20.
     * @return
     */
    private String executeInterface(){

        String message;

        try{

            String fileName = this.inventory.get_ValueAsString("FileName");
            if ((fileName == null) || (fileName.trim().equalsIgnoreCase(""))){
                return "Debe indicar archivo a procesar";
            }

            // Elimino información anterior.
            this.deleteFileData();

            // Lee lineas de archivo
            this.getDataFromFile();

            // Valida lineas de archivo y trae información asociada.
            message = this.setDataFromFile();
            if (message != null){
                return message;
            }

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return null;
    }

    /***
     * Elimina información leída desde archivo.
     * Xpande. Created by Gabriel Vila on 12/11/20.
     */
    private void deleteFileData() {

        String action = "";

        try{

            action = " delete from m_inventoryline " +
                    " where m_inventory_id =" + this.inventory.get_ID() +
                    " and m_inventoryline_id in (select m_inventoryline_id from z_inventoryfile " +
                    " where m_inventory_id =" + this.inventory.get_ID() + ")";
            DB.executeUpdateEx(action, get_TrxName());

            action = " delete from " + I_Z_InventoryFile.Table_Name +
                    " where " + X_Z_InventoryFile.COLUMNNAME_M_Inventory_ID + " =" + this.inventory.get_ID();
            DB.executeUpdateEx(action, get_TrxName());

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }

    /***
     * Proceso que lee archivo de interface.
     * Xpande. Created by Gabriel Vila on 12/11/20.
     */
    private void getDataFromFile() {

        FileReader fReader = null;
        BufferedReader bReader = null;

        String lineaArchivo = null;
        String mensaje = "";
        String action = "";

        try{

            // Formato de importación de archivo de interface para carga de medios de pago
            ImpFormat formatoImpArchivo = ImpFormat.load("Stock_CargaInventario");

            // Abro archivo
            File archivo = new File(this.inventory.get_ValueAsString("FileName").trim());

            fReader = new FileReader(archivo);
            bReader = new BufferedReader(fReader);

            int contLineas = 0;
            int lineaID = 0;

            // Leo lineas del archivo
            lineaArchivo = bReader.readLine();

            while (lineaArchivo != null) {

                lineaArchivo = lineaArchivo.replace("'", "");

                contLineas++;

                lineaID = formatoImpArchivo.updateDB(lineaArchivo, getCtx(), get_TrxName());

                if (lineaID <= 0){
                    MZInventoryFile inventoryFile = new MZInventoryFile(getCtx(), 0, get_TrxName());
                    inventoryFile.setM_Inventory_ID(this.inventory.get_ID());
                    inventoryFile.setLineNumber(contLineas);
                    inventoryFile.setFileLineText(lineaArchivo);
                    inventoryFile.setIsConfirmed(false);
                    inventoryFile.setErrorMsg("Formato de Linea Incorrecto.");
                    inventoryFile.saveEx();
                }
                else{
                    // Seteo atributos de linea procesada en tabla
                    action = " update " + I_Z_InventoryFile.Table_Name +
                            " set " + X_Z_InventoryFile.COLUMNNAME_M_Inventory_ID + " = " + this.inventory.get_ID() + ", " +
                            " LineNumber =" + contLineas + ", " +
                            " FileLineText ='" + lineaArchivo + "' " +
                            " where " + X_Z_InventoryFile.COLUMNNAME_Z_InventoryFile_ID+ " = " + lineaID;
                    DB.executeUpdateEx(action, get_TrxName());
                }

                lineaArchivo = bReader.readLine();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            if (bReader != null){
                try{
                    bReader.close();
                    if (fReader != null){
                        fReader.close();
                    }
                }
                catch (Exception e){
                    log.log(Level.SEVERE, e.getMessage());
                }
            }
        }
    }

    /***
     * Valida lineas leídas desde archivo y carga información asociada.
     * Xpande. Created by Gabriel Vila on 4/2/19.
     */
    private String setDataFromFile() {

        String sql;

        try{

            MWarehouse warehouse = (MWarehouse) this.inventory.getM_Warehouse();
            MLocator locator = MLocator.getDefault(warehouse);

            if ((locator == null) || (locator.get_ID() <= 0)){
                return "No hay Ubicación predeterminada definida para este Almacén";
            }

            int contadorOK = 0;
            int contadorError = 0;

            List<MZInventoryFile> inventoryFileList = this.getLines();
            for (MZInventoryFile inventoryFile : inventoryFileList){

                if (inventoryFile.getErrorMsg() != null){
                    contadorError++;
                    continue;
                }

                inventoryFile.setIsConfirmed(true);

                // Codigo interno y/o codigo de barra
                String codProducto = inventoryFile.getCodigoProducto();
                String codBarra = inventoryFile.getUPC();

                // Si no tengo ni codigo interno, ni codigo de barra, es un error.
                if ((codProducto == null || codProducto.trim().equalsIgnoreCase("")) &&
                        (codBarra == null || codBarra.trim().equalsIgnoreCase(""))){

                    inventoryFile.setIsConfirmed(false);
                    inventoryFile.setErrorMsg("Debe indicar código de producto, o código de barras.");
                }
                else{
                    int productID = -1;
                    if ((codProducto != null) && (!codProducto.trim().equalsIgnoreCase(""))){
                        // Busco producto por codigo interno
                        sql = " select max(m_product_id) from m_product where lower(value) ='" + codProducto.trim().toLowerCase() + "'";
                    }
                    else {
                        // Busco producto por código de barras
                        sql = " select max(m_product_id) from z_productoupc where lower(upc) ='" + codBarra.trim().toLowerCase() + "'";
                    }
                    productID = DB.getSQLValueEx(null, sql);

                    if (productID <= 0){
                        inventoryFile.setIsConfirmed(false);
                        inventoryFile.setErrorMsg("No existe Producto definido con ese código interno o código de barras.");
                    }
                    else {
                        inventoryFile.setM_Product_ID(productID);
                    }
                }

                if (inventoryFile.isConfirmed()){
                    if ((inventoryFile.getQtyCount() == null) || (inventoryFile.getQtyCount().compareTo(Env.ZERO) <= 0)){
                        inventoryFile.setIsConfirmed(false);
                        inventoryFile.setErrorMsg("Debe indicar Cantidad Contada");
                    }
                }

                if (inventoryFile.isConfirmed()){
                    if (inventoryFile.getM_Locator_ID() > 0){
                        MLocator locatorAux = new MLocator(getCtx(), inventoryFile.getM_Locator_ID(), null);
                        if ((locatorAux == null) || (locatorAux.get_ID() <= 0)){
                            inventoryFile.setIsConfirmed(false);
                            inventoryFile.setErrorMsg("No existe Ubicación en el sistema con ese Identificador : " + inventoryFile.getM_Locator_ID());
                        }
                        else{
                            if (locatorAux.getM_Warehouse_ID() != warehouse.get_ID()){
                                inventoryFile.setIsConfirmed(false);
                                inventoryFile.setErrorMsg("La Ubicación no pertenece al Almacén de este Documento");
                            }
                        }
                    }
                    else {
                        inventoryFile.setM_Locator_ID(locator.get_ID());
                    }
                }

                if (inventoryFile.isConfirmed()){
                    contadorOK++;

                    // Genero linea de inventario para esta linea de interface
                    MInventoryLine inventoryLine = new MInventoryLine(getCtx(), 0, get_TrxName());
                    inventoryLine.setM_Inventory_ID(this.inventory.get_ID());
                    inventoryLine.setM_Product_ID(inventoryFile.getM_Product_ID());
                    inventoryLine.setQtyCount(inventoryFile.getQtyCount());
                    inventoryLine.setM_AttributeSetInstance_ID(0);
                    inventoryLine.setM_Locator_ID(inventoryFile.getM_Locator_ID());

                    // Cantidad en sistema (QtyOnHand)
                    sql = " select qtyonhand from zv_stk_warlocproddisp " +
                            " where m_locator_id =" + inventoryLine.getM_Locator_ID() +
                            " and m_product_id =" + inventoryLine.getM_Product_ID();
                    BigDecimal qtyOnHand = DB.getSQLValueBDEx(null, sql);
                    if (qtyOnHand == null) qtyOnHand = Env.ZERO;

                    inventoryLine.setQtyBook(qtyOnHand);
                    inventoryLine.saveEx();

                    inventoryFile.setM_InventoryLine_ID(inventoryLine.get_ID());
                }
                else{
                    contadorError++;
                }

                inventoryFile.saveEx();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return null;
    }

    /***
     * Obtiene y retorna lineas de intgerface de carga de inventario.
     * Xpande. Created by Gabriel Vila on 12/11/20.
     * @return
     */
    private List<MZInventoryFile> getLines(){

        String whereClause = X_Z_InventoryFile.COLUMNNAME_M_Inventory_ID + " =" + this.inventory.get_ID();

        List<MZInventoryFile> lines = new Query(getCtx(), I_Z_InventoryFile.Table_Name, whereClause, get_TrxName()).list();

        return lines;
    }
}
