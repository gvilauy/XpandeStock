package org.xpande.stock.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Modelo para ficha de movimientos de stock.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/21.
 */
public class MZStkTransaction extends X_Z_StkTransaction{

    public MZStkTransaction(Properties ctx, int Z_StkTransaction_ID, String trxName) {
        super(ctx, Z_StkTransaction_ID, trxName);
    }

    public MZStkTransaction(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /***
     * Metodo que inserta nuevo registro de movimiento de stock según información recibida.
     * Xpande. Created by Gabriel Vila on 4/16/21.
     * @param model
     * @param mWarehouseID
     * @param mLocatorID
     * @param mProductID
     * @param mAttributeSetInstanceID
     * @param cUomToID
     * @param UomMultiplyRate
     * @param zStkStatusID
     * @param movementDate
     * @param movementQty
     * @param lineID
     * @return
     */
    public String setStkTransaction(PO model, int mWarehouseID, int mLocatorID, int mProductID, int mAttributeSetInstanceID,
                      int cUomToID, BigDecimal UomMultiplyRate, int zStkStatusID, Timestamp movementDate,
                      BigDecimal movementQty, BigDecimal qtyEntered, int lineID){

        try{
            // No hago nada si no tengo cantidad
            if ((movementQty == null) || (movementQty.compareTo(Env.ZERO) == 0)){
                return null;
            }
            // Si no tengo fecha aviso
            if (movementDate == null){
                return "Falta indicar Fecha para Movimiento de Stock";
            }
            // Instancio modelo de producto
            MProduct prod = new MProduct(model.getCtx(), mProductID, null);

            // Si el producto no es stockeable, no hago nada
            if (!prod.isStocked()) return null;

            Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);

            // Valido que tenga documento para este movimiento de stock
            int cDocTypeID = model.get_ValueAsInt(X_C_DocType.COLUMNNAME_C_DocType_ID);
            if (cDocTypeID <= 0){
                cDocTypeID = model.get_ValueAsInt("C_DocTypeTarget_ID");
            }
            if (cDocTypeID <= 0){
                return "No se recibe tipo de documento para movimiento de stock";
            }
            MDocType docType = new MDocType(model.getCtx(), cDocTypeID, null);

            Timestamp dateDoc = (Timestamp) model.get_Value("DateDoc");
            if (dateDoc == null){
                dateDoc = (Timestamp) model.get_Value("DateTrx");
            }

            String documentNo = model.get_ValueAsString("DocumentNo");
            if (documentNo == null){
                documentNo = model.get_ValueAsString("DocumentNoRef");
            }

            // Me aseguro que la fecha de movimiento no tenga hora
            movementDate = TimeUtil.trunc(movementDate, TimeUtil.TRUNC_DAY);

            // Sino tengo definicion de estados de stock para este documento aviso y salgo
            MZStkStatusDocType stkStatusDocType = MZStkStatusDocType.get(model.getCtx(), docType.getDocBaseType() ,null);
            if ((stkStatusDocType == null) || (stkStatusDocType.get_ID() <= 0)){
                return "Documento no esta configurado para realizar movimientos de stock: " + docType.getName();
            }

            // Seteo este modelo para movimiento de stock
            this.setAD_Client_ID(model.getAD_Client_ID());
            this.setAD_Org_ID(model.getAD_Org_ID());
            this.setAD_Table_ID(model.get_Table_ID());
            this.setRecord_ID(model.get_ID());
            this.setC_DocType_ID(docType.getC_DocType_ID());
            this.setC_UOM_ID(prod.getC_UOM_ID());
            this.setDocumentNo(documentNo);
            this.setMovementDate(movementDate);
            this.setM_AttributeSetInstance_ID(mAttributeSetInstanceID);
            this.setZ_StkStatus_ID(zStkStatusID);

            if (movementQty.compareTo(Env.ZERO) < 0){
                movementQty = movementQty.negate();
                this.setSigno(-1);
            }
            else {
                this.setSigno(1);
            }

            if (qtyEntered == null){
                qtyEntered = movementQty;
            }
            else {
                if (qtyEntered.compareTo(Env.ZERO) < 0){
                    qtyEntered = qtyEntered.negate();
                }
            }
            this.setMovementQty(movementQty);
            this.setQtyEntered(qtyEntered);

            if (model.get_ValueAsInt("C_BPartner_ID") > 0){
                this.setC_BPartner_ID(model.get_ValueAsInt("C_BPartner_ID"));
            }
            if (model.get_ValueAsInt("C_BPartner_Location_ID") > 0){
                this.setC_BPartner_Location_ID(model.get_ValueAsInt("C_BPartner_Location_ID"));
            }
            if (lineID > 0){
                this.setLine_ID(lineID);
            }
            if (cUomToID > 0){
                this.setC_UOM_To_ID(cUomToID);
            }
            else {
                this.setC_UOM_To_ID(prod.getC_UOM_ID());
            }
            if (UomMultiplyRate != null){
                this.setUomMultiplyRate(UomMultiplyRate);
            }
            else {
                this.setUomMultiplyRate(Env.ONE);
            }
            if (dateDoc != null){
                this.setDateDoc(dateDoc);
            }


            // Cantidad y signo. Cantidad siempre se guarda positiva y el signo le da la naturaleza
            BigDecimal signo = Env.ONE;
            if (movementQty.compareTo(Env.ZERO) < 0){
                signo = new BigDecimal(-1);
                movementQty = movementQty.multiply(new BigDecimal(-1));
            }

            this.setM_Product_ID(mProductID);

            // Si recibo warehouse y no recibo locator, considero locator por defecto del warehouse
            if ((mWarehouseID > 0) && (mLocatorID <= 0)){
                MWarehouse warehouse = new MWarehouse(model.getCtx(), mWarehouseID, null);
                this.setM_Warehouse_ID(mWarehouseID);
                this.setM_Locator_ID(warehouse.getDefaultLocator().getM_Locator_ID());
            }
            // Si no recibo warehouse y recibo locator, considero warehouse asociado al locator
            else if ((mWarehouseID <= 0) && (mLocatorID > 0)){
                MLocator locator = new MLocator(model.getCtx(), mLocatorID, null);
                this.setM_Warehouse_ID(locator.getM_Warehouse_ID());
                this.setM_Locator_ID(mLocatorID);
            }
            // Si recibo ambos, valido que el locator se corresponda con el warehouse,
            // y si no se corresponde tomo el locator por defecto del warehouse
            else if ((mWarehouseID > 0) && (mLocatorID > 0)){
                MLocator locator = new MLocator(model.getCtx(), mLocatorID, null);
                int locatorAuxID = mLocatorID;
                if (mWarehouseID != locator.getM_Warehouse_ID()){
                    MWarehouse warehouse = new MWarehouse(model.getCtx(), mWarehouseID, null);
                    locatorAuxID = warehouse.getDefaultLocator().getM_Locator_ID();
                }
                this.setM_Warehouse_ID(mWarehouseID);
                this.setM_Locator_ID(locatorAuxID);
            }

            // Me aseguro de no dejar pasar un movimiento de stock sin warehouse, locator o unidad de medida
            if (this.getM_Warehouse_ID() <= 0) {
                return  "Error al Crear Movimiento de Stock :\n" + "No se indica Almacen para el Producto : " + prod.getValue();
            }
            if (this.getM_Locator_ID() <= 0) {
                return  "Error al Crear Movimiento de Stock :\n" + "No se indica Ubicacion para el Producto : " + prod.getValue();
            }
            if (this.getC_UOM_ID() <= 0) {
                return  "Error al Crear Movimiento de Stock :\n" + "No se indica Unidad de Medida Minima para el Producto : " + prod.getValue();
            }
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return null;
    }

    /***
     * Verifica que no quede stock negativo según determinados parametros.
     * Xpande. Created by Gabriel Vila on 4/18/21.
     * @param ctx
     * @param mWarehouseID
     * @param mLocatorID
     * @param mProductID
     * @param mAttributeSetInstanceID
     * @param zStkStatusID
     * @param movementDate
     * @param trxName
     * @return
     */
    private static String validateStockStatusNegativo(Properties ctx, int mWarehouseID, int mLocatorID, int mProductID,
                                                      int mAttributeSetInstanceID, int zStkStatusID,
                                                      Timestamp movementDate, String trxName) {
        String sql = "";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            sql = " select sum(movementqty * sign) as cantidad " +
                    " from z_stktransaction " +
                    " where m_warehouse_id =" + mWarehouseID +
                    " and m_locator_id =" + mLocatorID +
                    " and m_product_id =" + mProductID +
                    " and m_attributesetinstance_id =" + mAttributeSetInstanceID +
                    " and z_stkstatus_id =" + zStkStatusID +
                    " and movementdate <='" + movementDate + "' ";

        	pstmt = DB.prepareStatement(sql, trxName);
        	rs = pstmt.executeQuery();

        	if (rs.next()){

        	}
        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        finally {
            DB.close(rs, pstmt);
        	rs = null; pstmt = null;
        }

        try{

        }
        catch (Exception e){
            throw new AdempiereException(e);
        }
        return null;
    }
}
