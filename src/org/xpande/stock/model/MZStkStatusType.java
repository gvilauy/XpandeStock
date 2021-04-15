package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/21.
 */
public class MZStkStatusType extends X_Z_StkStatusType{

    public MZStkStatusType(Properties ctx, int Z_StkStatusType_ID, String trxName) {
        super(ctx, Z_StkStatusType_ID, trxName);
    }

    public MZStkStatusType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
