package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/21.
 */
public class MZStkStatusDocType extends X_Z_StkStatusDocType{

    public MZStkStatusDocType(Properties ctx, int Z_StkStatusDocType_ID, String trxName) {
        super(ctx, Z_StkStatusDocType_ID, trxName);
    }

    public MZStkStatusDocType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
