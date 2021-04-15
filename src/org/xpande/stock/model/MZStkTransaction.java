package org.xpande.stock.model;

import java.sql.ResultSet;
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
}
