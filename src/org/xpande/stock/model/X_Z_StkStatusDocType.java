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

/** Generated Model for Z_StkStatusDocType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_Z_StkStatusDocType extends PO implements I_Z_StkStatusDocType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210415L;

    /** Standard Constructor */
    public X_Z_StkStatusDocType (Properties ctx, int Z_StkStatusDocType_ID, String trxName)
    {
      super (ctx, Z_StkStatusDocType_ID, trxName);
      /** if (Z_StkStatusDocType_ID == 0)
        {
			setDocBaseType (null);
			setZ_StkStatusDocType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Z_StkStatusDocType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Z_StkStatusDocType[")
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

	/** DocBaseType AD_Reference_ID=183 */
	public static final int DOCBASETYPE_AD_Reference_ID=183;
	/** GL Journal = GLJ */
	public static final String DOCBASETYPE_GLJournal = "GLJ";
	/** GL Document = GLD */
	public static final String DOCBASETYPE_GLDocument = "GLD";
	/** AP Invoice = API */
	public static final String DOCBASETYPE_APInvoice = "API";
	/** AP Payment = APP */
	public static final String DOCBASETYPE_APPayment = "APP";
	/** AR Invoice = ARI */
	public static final String DOCBASETYPE_ARInvoice = "ARI";
	/** AR Receipt = ARR */
	public static final String DOCBASETYPE_ARReceipt = "ARR";
	/** Sales Order = SOO */
	public static final String DOCBASETYPE_SalesOrder = "SOO";
	/** AR Pro Forma Invoice = ARF */
	public static final String DOCBASETYPE_ARProFormaInvoice = "ARF";
	/** Material Delivery = MMS */
	public static final String DOCBASETYPE_MaterialDelivery = "MMS";
	/** Material Receipt = MMR */
	public static final String DOCBASETYPE_MaterialReceipt = "MMR";
	/** Material Movement = MMM */
	public static final String DOCBASETYPE_MaterialMovement = "MMM";
	/** Purchase Order = POO */
	public static final String DOCBASETYPE_PurchaseOrder = "POO";
	/** Purchase Requisition = POR */
	public static final String DOCBASETYPE_PurchaseRequisition = "POR";
	/** Material Physical Inventory = MMI */
	public static final String DOCBASETYPE_MaterialPhysicalInventory = "MMI";
	/** AP Credit Memo = APC */
	public static final String DOCBASETYPE_APCreditMemo = "APC";
	/** AR Credit Memo = ARC */
	public static final String DOCBASETYPE_ARCreditMemo = "ARC";
	/** Bank Statement = CMB */
	public static final String DOCBASETYPE_BankStatement = "CMB";
	/** Cash Journal = CMC */
	public static final String DOCBASETYPE_CashJournal = "CMC";
	/** Payment Allocation = CMA */
	public static final String DOCBASETYPE_PaymentAllocation = "CMA";
	/** Material Production = MMP */
	public static final String DOCBASETYPE_MaterialProduction = "MMP";
	/** Match Invoice = MXI */
	public static final String DOCBASETYPE_MatchInvoice = "MXI";
	/** Match PO = MXP */
	public static final String DOCBASETYPE_MatchPO = "MXP";
	/** Project Issue = PJI */
	public static final String DOCBASETYPE_ProjectIssue = "PJI";
	/** Maintenance Order = MOF */
	public static final String DOCBASETYPE_MaintenanceOrder = "MOF";
	/** Manufacturing Order = MOP */
	public static final String DOCBASETYPE_ManufacturingOrder = "MOP";
	/** Quality Order = MQO */
	public static final String DOCBASETYPE_QualityOrder = "MQO";
	/** Payroll = HRP */
	public static final String DOCBASETYPE_Payroll = "HRP";
	/** Distribution Order = DOO */
	public static final String DOCBASETYPE_DistributionOrder = "DOO";
	/** Manufacturing Cost Collector = MCC */
	public static final String DOCBASETYPE_ManufacturingCostCollector = "MCC";
	/** Warehouse Management Order = WMO */
	public static final String DOCBASETYPE_WarehouseManagementOrder = "WMO";
	/** Manufacturing Planned Order = MPO */
	public static final String DOCBASETYPE_ManufacturingPlannedOrder = "MPO";
	/** AP Payment Selection = APS */
	public static final String DOCBASETYPE_APPaymentSelection = "APS";
	/** Sales Commission = SOC */
	public static final String DOCBASETYPE_SalesCommission = "SOC";
	/** Fixed Assets Addition = FAA */
	public static final String DOCBASETYPE_FixedAssetsAddition = "FAA";
	/** Fixed Assets Disposal = FAD */
	public static final String DOCBASETYPE_FixedAssetsDisposal = "FAD";
	/** Fixed Assets Depreciation = FDP */
	public static final String DOCBASETYPE_FixedAssetsDepreciation = "FDP";
	/** PLV Precios de Proveedor = PLV */
	public static final String DOCBASETYPE_PLVPreciosDeProveedor = "PLV";
	/** Retail Confirmacion Etiquetas = RCE */
	public static final String DOCBASETYPE_RetailConfirmacionEtiquetas = "RCE";
	/** PVP Actualizacion Precios de Venta = PVP */
	public static final String DOCBASETYPE_PVPActualizacionPreciosDeVenta = "PVP";
	/** RCP Retail Comunicacion POS = RCP */
	public static final String DOCBASETYPE_RCPRetailComunicacionPOS = "RCP";
	/** RGU Emision de Resguardos = RGU */
	public static final String DOCBASETYPE_RGUEmisionDeResguardos = "RGU";
	/** RGC Emision de Contra-Resguardos = RGC */
	public static final String DOCBASETYPE_RGCEmisionDeContra_Resguardos = "RGC";
	/** OPG Generacion de Ordenes de Pago = OPG */
	public static final String DOCBASETYPE_OPGGeneracionDeOrdenesDePago = "OPG";
	/** OOP Ordenes de Pago = OOP */
	public static final String DOCBASETYPE_OOPOrdenesDePago = "OOP";
	/** EMP Emision Medio Pago = EMP */
	public static final String DOCBASETYPE_EMPEmisionMedioPago = "EMP";
	/** RMP Reemplazo Medio de Pago = RMP */
	public static final String DOCBASETYPE_RMPReemplazoMedioDePago = "RMP";
	/** NCG Generacion de Notas de Cŕedito = NCG */
	public static final String DOCBASETYPE_NCGGeneracionDeNotasDeCŕedito = "NCG";
	/** ODV Orden de Devolución a Proveedor = ODV */
	public static final String DOCBASETYPE_ODVOrdenDeDevoluciónAProveedor = "ODV";
	/** OFP Oferta Periódica Retail = OFP */
	public static final String DOCBASETYPE_OFPOfertaPeriódicaRetail = "OFP";
	/** PPD Pago Proveedor = PPD */
	public static final String DOCBASETYPE_PPDPagoProveedor = "PPD";
	/** PPR Recibo de Pagos Emitidos = PPR */
	public static final String DOCBASETYPE_PPRReciboDePagosEmitidos = "PPR";
	/** PPA Anticipo a Proveedor = PPA */
	public static final String DOCBASETYPE_PPAAnticipoAProveedor = "PPA";
	/** RDI Remito Diferencia Factura = RDI */
	public static final String DOCBASETYPE_RDIRemitoDiferenciaFactura = "RDI";
	/** CCD Cobranza a Cliente = CCD */
	public static final String DOCBASETYPE_CCDCobranzaACliente = "CCD";
	/** PCV Pauta Comercial Venta = PCV */
	public static final String DOCBASETYPE_PCVPautaComercialVenta = "PCV";
	/** RCG Generador de Remitos = RCG */
	public static final String DOCBASETYPE_RCGGeneradorDeRemitos = "RCG";
	/** SCG Generador de Reservas = SCG */
	public static final String DOCBASETYPE_SCGGeneradorDeReservas = "SCG";
	/** RVT Reserva de Venta = RVT */
	public static final String DOCBASETYPE_RVTReservaDeVenta = "RVT";
	/** ATR Asignación Transporte Logistica = ATR */
	public static final String DOCBASETYPE_ATRAsignaciónTransporteLogistica = "ATR";
	/** PIK Picking de Mercaderia = PIK */
	public static final String DOCBASETYPE_PIKPickingDeMercaderia = "PIK";
	/** CEX Carga Extractos Bancarios = CEX */
	public static final String DOCBASETYPE_CEXCargaExtractosBancarios = "CEX";
	/** CIM Carga Inicial Medios de Pago = CIM */
	public static final String DOCBASETYPE_CIMCargaInicialMediosDePago = "CIM";
	/** CIP Carga Inicial de Pagoa / Cobros = CIP */
	public static final String DOCBASETYPE_CIPCargaInicialDePagoaCobros = "CIP";
	/** DPT Deposito Medio Pago Tercero = DPT */
	public static final String DOCBASETYPE_DPTDepositoMedioPagoTercero = "DPT";
	/** IMC Ingreso Medio Pago Caja = IMC */
	public static final String DOCBASETYPE_IMCIngresoMedioPagoCaja = "IMC";
	/** TSP Transferencia Saldo a Pagar = TSP */
	public static final String DOCBASETYPE_TSPTransferenciaSaldoAPagar = "TSP";
	/** GEN General = GEN */
	public static final String DOCBASETYPE_GENGeneral = "GEN";
	/** LCB Carga Informacion Core = LCB */
	public static final String DOCBASETYPE_LCBCargaInformacionCore = "LCB";
	/** CII Carga Inicial Invoices = CII */
	public static final String DOCBASETYPE_CIICargaInicialInvoices = "CII";
	/** AJI Asiento de Apertura de Ejercicio = AJI */
	public static final String DOCBASETYPE_AJIAsientoDeAperturaDeEjercicio = "AJI";
	/** CIJ Carga Inicial Asientos Contables = CIJ */
	public static final String DOCBASETYPE_CIJCargaInicialAsientosContables = "CIJ";
	/** CJD Cierre de Cuentas Diferenciales = CJD */
	public static final String DOCBASETYPE_CJDCierreDeCuentasDiferenciales = "CJD";
	/** CJI Cierre de Cuentas Integrales = CJI */
	public static final String DOCBASETYPE_CJICierreDeCuentasIntegrales = "CJI";
	/** DFC Diferencia de Cambio = DFC */
	public static final String DOCBASETYPE_DFCDiferenciaDeCambio = "DFC";
	/** AVG Generacion de Asientos de Venta POS = AVG */
	public static final String DOCBASETYPE_AVGGeneracionDeAsientosDeVentaPOS = "AVG";
	/** AVR Asiento de Reclasificación de Medios de Pago = AVR */
	public static final String DOCBASETYPE_AVRAsientoDeReclasificaciónDeMediosDePago = "AVR";
	/** CSC Carga Scanntech Comprobantes = CSC */
	public static final String DOCBASETYPE_CSCCargaScanntechComprobantes = "CSC";
	/** DPS Desafectacion de Productos de Socio = DPS */
	public static final String DOCBASETYPE_DPSDesafectacionDeProductosDeSocio = "DPS";
	/** FME Formulario Movimiento Efectivo = FME */
	public static final String DOCBASETYPE_FMEFormularioMovimientoEfectivo = "FME";
	/** RDC Remito Diferencia Cantidad = RDC */
	public static final String DOCBASETYPE_RDCRemitoDiferenciaCantidad = "RDC";
	/** CCA Anticipo de Cliente = CCA */
	public static final String DOCBASETYPE_CCAAnticipoDeCliente = "CCA";
	/** STT Transferencia de Stock = STT */
	public static final String DOCBASETYPE_STTTransferenciaDeStock = "STT";
	/** BDR Debito Bancario = BDR */
	public static final String DOCBASETYPE_BDRDebitoBancario = "BDR";
	/** BCR Credito Bancario = BCR */
	public static final String DOCBASETYPE_BCRCreditoBancario = "BCR";
	/** DPP Deposito Medio Pago Propio = DPP */
	public static final String DOCBASETYPE_DPPDepositoMedioPagoPropio = "DPP";
	/** TCP Transferencia Cuentas Propias = TCP */
	public static final String DOCBASETYPE_TCPTransferenciaCuentasPropias = "TCP";
	/** CDR Salida de Caja = CDR */
	public static final String DOCBASETYPE_CDRSalidaDeCaja = "CDR";
	/** CCR Entrada a Caja = CCR */
	public static final String DOCBASETYPE_CCREntradaACaja = "CCR";
	/** Set Document BaseType.
		@param DocBaseType 
		Logical type of document
	  */
	public void setDocBaseType (String DocBaseType)
	{

		set_Value (COLUMNNAME_DocBaseType, DocBaseType);
	}

	/** Get Document BaseType.
		@return Logical type of document
	  */
	public String getDocBaseType () 
	{
		return (String)get_Value(COLUMNNAME_DocBaseType);
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

	/** Set Z_StkStatusDocType ID.
		@param Z_StkStatusDocType_ID Z_StkStatusDocType ID	  */
	public void setZ_StkStatusDocType_ID (int Z_StkStatusDocType_ID)
	{
		if (Z_StkStatusDocType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Z_StkStatusDocType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Z_StkStatusDocType_ID, Integer.valueOf(Z_StkStatusDocType_ID));
	}

	/** Get Z_StkStatusDocType ID.
		@return Z_StkStatusDocType ID	  */
	public int getZ_StkStatusDocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Z_StkStatusDocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}