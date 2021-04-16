package org.xpande.stock.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

import java.math.BigDecimal;
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
     * @param zStkStatusID
     * @param movementDate
     * @param movementQty
     * @param lineID
     * @return
     */
    public static String add(PO model, int mWarehouseID, int mLocatorID, int mProductID, int mAttributeSetInstanceID,
                             int zStkStatusID, Timestamp movementDate, BigDecimal movementQty, int lineID){

        try{
            // No hago nada si no tengo cantidad
            if ((movementQty == null) || (movementQty.compareTo(Env.ZERO) == 0)){
                return null;
            }

            // Si no tengo fecha aviso
            if (movementDate == null){
                return "Falta indicar Fecha para Movimiento de Stock";
            }
            
            Timestamp fechaHoy = TimeUtil.trunc(new Timestamp(System.currentTimeMillis()), TimeUtil.TRUNC_DAY);



        }
        catch (Exception e){
            throw new AdempiereException(e);
        }

        return null;
    }
}
