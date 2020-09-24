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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for Z_StkTransferLin
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class X_Z_StkTransferLin extends PO implements I_Z_StkTransferLin, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200924L;

    /** Standard Constructor */
    public X_Z_StkTransferLin (Properties ctx, int Z_StkTransferLin_ID, String trxName)
    {
      super (ctx, Z_StkTransferLin_ID, trxName);
      /** if (Z_StkTransferLin_ID == 0)
        {
			setC_UOM_ID (0);
			setM_Locator_ID (0);
			setM_LocatorTo_ID (0);
			setMovementQty (Env.ZERO);
			setM_Product_ID (0);
			setQtyEntered (Env.ZERO);
			setZ_StkTransfer_ID (0);
			setZ_StkTransferLin_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_StkTransferLin (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_StkTransferLin[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set CodigoProducto.
		@param CodigoProducto 
		Código de Producto
	  */
	public void setCodigoProducto (String CodigoProducto)
	{
		set_Value (COLUMNNAME_CodigoProducto, CodigoProducto);
	}

	/** Get CodigoProducto.
		@return Código de Producto
	  */
	public String getCodigoProducto () 
	{
		return (String)get_Value(COLUMNNAME_CodigoProducto);
	}

	public I_C_UOM getC_UOM() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_LocatorTo() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_LocatorTo_ID(), get_TrxName());	}

	/** Set Locator To.
		@param M_LocatorTo_ID 
		Location inventory is moved to
	  */
	public void setM_LocatorTo_ID (int M_LocatorTo_ID)
	{
		if (M_LocatorTo_ID < 1) 
			set_Value (COLUMNNAME_M_LocatorTo_ID, null);
		else 
			set_Value (COLUMNNAME_M_LocatorTo_ID, Integer.valueOf(M_LocatorTo_ID));
	}

	/** Get Locator To.
		@return Location inventory is moved to
	  */
	public int getM_LocatorTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LocatorTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UPC/EAN.
		@param UPC 
		Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC)
	{
		set_Value (COLUMNNAME_UPC, UPC);
	}

	/** Get UPC/EAN.
		@return Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC () 
	{
		return (String)get_Value(COLUMNNAME_UPC);
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

	public I_Z_StkTransfer getZ_StkTransfer() throws RuntimeException
    {
		return (I_Z_StkTransfer)MTable.get(getCtx(), I_Z_StkTransfer.Table_Name)
			.getPO(getZ_StkTransfer_ID(), get_TrxName());	}

	/** Set Z_StkTransfer ID.
		@param Z_StkTransfer_ID Z_StkTransfer ID	  */
	public void setZ_StkTransfer_ID (int Z_StkTransfer_ID)
	{
		if (Z_StkTransfer_ID < 1) 
			set_Value (COLUMNNAME_Z_StkTransfer_ID, null);
		else 
			set_Value (COLUMNNAME_Z_StkTransfer_ID, Integer.valueOf(Z_StkTransfer_ID));
	}

	/** Get Z_StkTransfer ID.
		@return Z_StkTransfer ID	  */
	public int getZ_StkTransfer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_StkTransfer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Z_StkTransferLin ID.
		@param Z_StkTransferLin_ID Z_StkTransferLin ID	  */
	public void setZ_StkTransferLin_ID (int Z_StkTransferLin_ID)
	{
		if (Z_StkTransferLin_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_StkTransferLin_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_StkTransferLin_ID, Integer.valueOf(Z_StkTransferLin_ID));
	}

	/** Get Z_StkTransferLin ID.
		@return Z_StkTransferLin ID	  */
	public int getZ_StkTransferLin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_StkTransferLin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}