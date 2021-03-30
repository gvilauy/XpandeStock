package org.xpande.stock.report;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Reporte de Inventario de Stock a una determinado fecha.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 3/29/21.
 */
public class InventarioStock extends SvrProcess {

    private final String TABLA_REPORTE = "Z_RP_InventarioStock";

    private int adClientID = -1;
    private int adOrgID = -1;
    private Timestamp startDate = null;
    private Timestamp endDate = null;
    private int mProductID = -1;
    private int mWarehouseID = -1;
    private int mLocatorID = -1;
    private int mProductCategoryID = -1;
    private boolean hideQtyZero = false;

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){
                if (name.trim().equalsIgnoreCase("AD_Client_ID")){
                    this.adClientID = ((BigDecimal)para[i].getParameter()).intValueExact();
                }
                else if (name.trim().equalsIgnoreCase("AD_Org_ID")){
                    this.adOrgID = ((BigDecimal)para[i].getParameter()).intValueExact();
                }
                else if (name.trim().equalsIgnoreCase("M_Product_ID")){
                    if (para[i].getParameter() != null){
                        this.mProductID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
                else if (name.trim().equalsIgnoreCase("M_Product_Category_ID")){
                    if (para[i].getParameter() != null){
                        this.mProductCategoryID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
                else if (name.equalsIgnoreCase("M_Warehouse_ID")){
                    if(para[i].getParameter()!=null){
                        this.mWarehouseID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
                else if (name.equalsIgnoreCase("M_Locator_ID")){
                    if(para[i].getParameter()!=null){
                        this.mLocatorID = ((BigDecimal)para[i].getParameter()).intValueExact();
                    }
                }
                else if (name.equalsIgnoreCase("HideQtyZero")){
                    if(para[i].getParameter()!=null){
                        this.hideQtyZero = ((String) para[i].getParameter()).equalsIgnoreCase("Y");
                    }
                }
                else if (name.trim().equalsIgnoreCase("StartDate")){
                    if (para[i].getParameter() != null){
                        this.startDate = (Timestamp)para[i].getParameter();
                    }
                }
                else if (name.trim().equalsIgnoreCase("EndDate")){
                    this.endDate = (Timestamp)para[i].getParameter();
                }
            }
        }

    }

    @Override
    protected String doIt() throws Exception {

        try{
            this.deleteData();
            this.getData();
            //this.updateData();
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return "OK";
    }

    /***
     * Elimina información anterior para este usuario de la tabla del reporte.
     * Xpande. Created by Gabriel Vila on 3/29/21.
     */
    private void deleteData() {

        try{

            String action = " delete from " + TABLA_REPORTE + " where ad_user_id =" + this.getAD_User_ID();
            DB.executeUpdateEx(action, null);

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }


    /***
     * Obtiene información inicial para el reporte.
     * Xpande. Created by Gabriel Vila on 3/29/21.
     * @throws Exception
     */
    private void getData() throws Exception {

        String action = "", sql = "";

        try{

            action = " insert into " + TABLA_REPORTE + " (ad_client_id, ad_org_id,  m_warehouse_id, m_locator_id, " +
                    "m_product_category_id, m_product_id, c_uom_id, ad_user_id, startdate, enddate, hideqtyzero, qtyonhand) ";

            // Armo condiciones según filtros
            String whereClause = "";

            if (this.startDate != null){
                whereClause += " and t.movementdate >='" + this.startDate + "'" ;
            }
            if (this.endDate != null){
                whereClause += " and t.movementdate <='" + this.endDate + "'" ;
            }
            if (this.mWarehouseID > 0){
                whereClause += " and l.m_warehouse_id =" + this.mWarehouseID;
            }
            if (this.mLocatorID > 0){
                whereClause += " and t.m_locator_id =" + this.mLocatorID;
            }
            if (this.mProductID > 0){
                whereClause += " and t.m_product_id =" + this.mProductID;
            }
            if (this.mProductCategoryID > 0){
                whereClause += " and p.m_product_category_id =" + this.mProductCategoryID;
            }

            String havingClause = "";
            if (this.hideQtyZero){
                havingClause = " having sum(coalesce(t.movementqty,0)) != 0 ";
            }

            String fieldStartDate = "";
            if (this.startDate == null){
                fieldStartDate = " null ";
            }
            else {
                fieldStartDate = "'" + this.startDate + "'";
            }


            sql = " select t.ad_client_id, t.ad_org_id, l.m_warehouse_id, t.m_locator_id, " +
                    "p.m_product_category_id, t.m_product_id, p.c_uom_id, " +
                    this.getAD_User_ID() + "," + fieldStartDate + ", '" + this.endDate + "', '" +
                    ((this.hideQtyZero) ? "Y" : "N") + "', " +
                    " sum(coalesce(t.movementqty,0)) as movementqty " +
                    " from m_transaction t " +
                    " inner join m_product p on t.m_product_id = p.m_product_id " +
                    " inner join m_locator l on t.m_locator_id = l.m_locator_id " +
                    " where t.ad_client_id =" + this.adClientID +
                    " and t.ad_org_id =" + this.adOrgID + whereClause +
                    " group by 1, 2, 3, 4, 5, 6, 7 " + havingClause;

            DB.executeUpdateEx(action + sql, null);
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
    }

}
