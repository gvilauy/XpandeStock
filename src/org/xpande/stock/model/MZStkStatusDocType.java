package org.xpande.stock.model;

import org.compiere.model.MQuery;
import org.compiere.model.Query;

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

    /***
     * Obtiene y retorna modelo según valor de DocBaseType recibido.
     * Xpande. Created by Gabriel Vila on 4/18/21.
     * @param ctx
     * @param docBaseType
     * @param trxName
     * @return
     */
    public static MZStkStatusDocType get(Properties ctx, String docBaseType, String trxName) {

        String whereClause = X_Z_StkStatusDocType.COLUMNNAME_DocBaseType + " ='" + docBaseType + "' ";

        MZStkStatusDocType model = new Query(ctx, I_Z_StkStatusDocType.Table_Name, whereClause, trxName).setOnlyActiveRecords(true).first();

        return model;
    }


}
