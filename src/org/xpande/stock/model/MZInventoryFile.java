package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para interface de carga de inventario.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 12/11/20.
 */
public class MZInventoryFile extends X_Z_InventoryFile{

    public MZInventoryFile(Properties ctx, int Z_InventoryFile_ID, String trxName) {
        super(ctx, Z_InventoryFile_ID, trxName);
    }

    public MZInventoryFile(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
