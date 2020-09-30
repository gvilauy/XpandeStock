package org.xpande.stock.model;

import org.compiere.util.Env;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 9/28/20.
 */
public class MZStkTransferLin extends X_Z_StkTransferLin {

    public MZStkTransferLin(Properties ctx, int Z_StkTransferLin_ID, String trxName) {
        super(ctx, Z_StkTransferLin_ID, trxName);
    }

    public MZStkTransferLin(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    @Override
    protected boolean beforeSave(boolean newRecord) {

        // Me aseguro la organización del cabezal
        MZStkTransfer stkTransfer = (MZStkTransfer) this.getZ_StkTransfer();
        this.setAD_Org_ID(stkTransfer.getAD_Org_ID());

        if ((this.getQtyEntered() == null) || (this.getQtyEntered().compareTo(Env.ZERO) <= 0)){
            log.saveError("ATENCIÓN", "Debe indicar cantidad a consumir mayor a cero.");
            return false;
        }

        return true;
    }
}
