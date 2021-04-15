/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.xpande.stock.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for Z_StkStatus
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_Z_StkStatus extends PO implements I_Z_StkStatus, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210415L;

    /** Standard Constructor */
    public X_Z_StkStatus (Properties ctx, int Z_StkStatus_ID, String trxName)
    {
      super (ctx, Z_StkStatus_ID, trxName);
      /** if (Z_StkStatus_ID == 0)
        {
			setName (null);
			setZ_StkStatus_ID (0);
			setZ_StkStatusType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_StkStatus (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_Z_StkStatus[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** Set Z_StkStatus ID.
		@param Z_StkStatus_ID Z_StkStatus ID	  */
	public void setZ_StkStatus_ID (int Z_StkStatus_ID)
	{
		if (Z_StkStatus_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_StkStatus_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_StkStatus_ID, Integer.valueOf(Z_StkStatus_ID));
	}

	/** Get Z_StkStatus ID.
		@return Z_StkStatus ID	  */
	public int getZ_StkStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_StkStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_Z_StkStatusType getZ_StkStatusType() throws RuntimeException
    {
		return (I_Z_StkStatusType)MTable.get(getCtx(), I_Z_StkStatusType.Table_Name)
			.getPO(getZ_StkStatusType_ID(), get_TrxName());	}

	/** Set Z_StkStatusType ID.
		@param Z_StkStatusType_ID Z_StkStatusType ID	  */
	public void setZ_StkStatusType_ID (int Z_StkStatusType_ID)
	{
		if (Z_StkStatusType_ID < 1) 
			set_Value (COLUMNNAME_Z_StkStatusType_ID, null);
		else 
			set_Value (COLUMNNAME_Z_StkStatusType_ID, Integer.valueOf(Z_StkStatusType_ID));
	}

	/** Get Z_StkStatusType ID.
		@return Z_StkStatusType ID	  */
	public int getZ_StkStatusType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_StkStatusType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}