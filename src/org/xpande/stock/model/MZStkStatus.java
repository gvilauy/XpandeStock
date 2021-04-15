package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para estados de stock.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/21.
 */
public class MZStkStatus extends X_Z_StkStatus{

    public MZStkStatus(Properties ctx, int Z_StkStatus_ID, String trxName) {
        super(ctx, Z_StkStatus_ID, trxName);
    }

    public MZStkStatus(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
