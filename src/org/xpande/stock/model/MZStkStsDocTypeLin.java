package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 4/15/21.
 */
public class MZStkStsDocTypeLin extends X_Z_StkStsDocTypeLin{

    public MZStkStsDocTypeLin(Properties ctx, int Z_StkStsDocTypeLin_ID, String trxName) {
        super(ctx, Z_StkStsDocTypeLin_ID, trxName);
    }

    public MZStkStsDocTypeLin(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
