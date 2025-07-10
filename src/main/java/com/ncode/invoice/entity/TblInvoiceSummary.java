package com.ncode.invoice.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "tblinvoicesummary")
public class TblInvoiceSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoicesummaryid")
	private Integer invoicesummaryid;

	@Column(name = "addressofcustomer")
	private String addressofcustomer;

	@Column(name = "irn")
	private String irn;

	@Column(name = "sono")
	private String sono;

	@Column(name = "custpono")
	private String custpono;

	@Column(name = "invoiceno")
	private String invoiceno;

	@Column(name = "invoicedate")
	private String invoicedate;

	@Column(name = "natureofsupply")
	private String natureofsupply;

	@Column(name = "customertype")
	private String customertype;

	@Column(name = "natureoftransaction")
	private String natureoftransaction;

	@Column(name = "natureofinvoice")
	private String natureofinvoice;

	@Column(name = "reversechargeno")
	private String reversechargeno;

	@Column(name = "acknowledgenumber")
	private String acknowledgenumber;

	@Column(name = "acknowledgedate")
	private String acknowledgedate;

	@Column(name = "description")
	private String description;

	@Column(name = "rate")
	private Double rate;

	@Column(name = "qty")
	private Integer qty;

	@Column(name = "invoiceamount")
	private Double invoiceamount;

	@Column(name = "cgst")
	private Double cgst;

	@Column(name = "sgst")
	private Double sgst;
	
	@Column(name = "igst")
	private Double igst;

	@Column(name = "totaltax")
	private Double totaltax;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "coinadjust")
	private Double coinadjust;

	@Column(name = "totalamount")
	private Double totalamount;

	@Column(name = "totalamountinwords")
	private String totalamountinwords;

	@Column(name = "flag")
	private Integer flag;

	@Column(name = "fromdate")
	private Date fromdate;

	@Column(name = "todate")
	private Date todate;

	@Column(name = "createddate")
	private Date createddate;

	@Column(name = "error_log")
	private String errorlog;

	@Column(name = "isgstvalidated")
	private String isgstvalidated;

	@Column(name = "isesigncompleted")
	private String isesigncompleted;

	@Transient
	private String companyName;

	@Column(name = "settled")
	private Boolean settled;

	@Column(name = "os")
	private Double os;

	@Column(name = "gstno")
	private String gstNo;
	
	@Column(name = "tanno")
	private String tanno;
	
	@Transient
	private Double balancetds;
	
	@Transient
	private Double balanceittds;
	
	@Transient
	private Double balancecgsttds;
	
	@Transient
	private Double balancsgsttds;
	
	@Transient
	private Double balanceigsttds;
	
	@Transient
	private Double balanceincodetaxtds;
	
	@Column(name = "crediteddate")
	private Date crediteddate;
	
	@Column(name = "iscredited")
	private String iscredited;

	@Column(name = "ispdfgenerated")
	private String ispdfgenerated;
	
	@Column(name = "groupid")
	private Integer groupid;
	
	@Column(name = "qrcode")
	private String qrcode;

	public Integer getInvoicesummaryid() {
		return invoicesummaryid;
	}

	public void setInvoicesummaryid(Integer invoicesummaryid) {
		this.invoicesummaryid = invoicesummaryid;
	}

	public String getAddressofcustomer() {
		return addressofcustomer;
	}

	public void setAddressofcustomer(String addressofcustomer) {
		this.addressofcustomer = addressofcustomer;
	}

	public String getIrn() {
		return irn;
	}

	public void setIrn(String irn) {
		this.irn = irn;
	}

	public String getSono() {
		return sono;
	}

	public void setSono(String sono) {
		this.sono = sono;
	}

	public String getCustpono() {
		return custpono;
	}

	public void setCustpono(String custpono) {
		this.custpono = custpono;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getNatureofsupply() {
		return natureofsupply;
	}

	public void setNatureofsupply(String natureofsupply) {
		this.natureofsupply = natureofsupply;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getNatureoftransaction() {
		return natureoftransaction;
	}

	public void setNatureoftransaction(String natureoftransaction) {
		this.natureoftransaction = natureoftransaction;
	}

	public String getNatureofinvoice() {
		return natureofinvoice;
	}

	public void setNatureofinvoice(String natureofinvoice) {
		this.natureofinvoice = natureofinvoice;
	}

	public String getReversechargeno() {
		return reversechargeno;
	}

	public void setReversechargeno(String reversechargeno) {
		this.reversechargeno = reversechargeno;
	}

	public String getAcknowledgenumber() {
		return acknowledgenumber;
	}

	public void setAcknowledgenumber(String acknowledgenumber) {
		this.acknowledgenumber = acknowledgenumber;
	}

	public String getAcknowledgedate() {
		return acknowledgedate;
	}

	public void setAcknowledgedate(String acknowledgedate) {
		this.acknowledgedate = acknowledgedate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getInvoiceamount() {
		return invoiceamount;
	}

	public void setInvoiceamount(Double invoiceamount) {
		this.invoiceamount = invoiceamount;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getIgst() {
		return igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public Double getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(Double totaltax) {
		this.totaltax = totaltax;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCoinadjust() {
		return coinadjust;
	}

	public void setCoinadjust(Double coinadjust) {
		this.coinadjust = coinadjust;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getTotalamountinwords() {
		return totalamountinwords;
	}

	public void setTotalamountinwords(String totalamountinwords) {
		this.totalamountinwords = totalamountinwords;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodate() {
		return todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getErrorlog() {
		return errorlog;
	}

	public void setErrorlog(String errorlog) {
		this.errorlog = errorlog;
	}

	public String getIsgstvalidated() {
		return isgstvalidated;
	}

	public void setIsgstvalidated(String isgstvalidated) {
		this.isgstvalidated = isgstvalidated;
	}

	public String getIsesigncompleted() {
		return isesigncompleted;
	}

	public void setIsesigncompleted(String isesigncompleted) {
		this.isesigncompleted = isesigncompleted;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Boolean getSettled() {
		return settled;
	}

	public void setSettled(Boolean settled) {
		this.settled = settled;
	}

	public Double getOs() {
		return os;
	}

	public void setOs(Double os) {
		this.os = os;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getTanno() {
		return tanno;
	}

	public void setTanno(String tanno) {
		this.tanno = tanno;
	}

	public Double getBalancetds() {
		return balancetds;
	}

	public void setBalancetds(Double balancetds) {
		this.balancetds = balancetds;
	}

	public Double getBalanceittds() {
		return balanceittds;
	}

	public void setBalanceittds(Double balanceittds) {
		this.balanceittds = balanceittds;
	}

	public Double getBalancecgsttds() {
		return balancecgsttds;
	}

	public void setBalancecgsttds(Double balancecgsttds) {
		this.balancecgsttds = balancecgsttds;
	}

	public Double getBalancsgsttds() {
		return balancsgsttds;
	}

	public void setBalancsgsttds(Double balancsgsttds) {
		this.balancsgsttds = balancsgsttds;
	}

	public Double getBalanceigsttds() {
		return balanceigsttds;
	}

	public void setBalanceigsttds(Double balanceigsttds) {
		this.balanceigsttds = balanceigsttds;
	}

	public Double getBalanceincodetaxtds() {
		return balanceincodetaxtds;
	}

	public void setBalanceincodetaxtds(Double balanceincodetaxtds) {
		this.balanceincodetaxtds = balanceincodetaxtds;
	}

	public Date getCrediteddate() {
		return crediteddate;
	}

	public void setCrediteddate(Date crediteddate) {
		this.crediteddate = crediteddate;
	}

	public String getIscredited() {
		return iscredited;
	}

	public void setIscredited(String iscredited) {
		this.iscredited = iscredited;
	}

	public String getIspdfgenerated() {
		return ispdfgenerated;
	}

	public void setIspdfgenerated(String ispdfgenerated) {
		this.ispdfgenerated = ispdfgenerated;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public TblInvoiceSummary(Integer invoicesummaryid, String addressofcustomer, String irn, String sono,
			String custpono, String invoiceno, String invoicedate, String natureofsupply, String customertype,
			String natureoftransaction, String natureofinvoice, String reversechargeno, String acknowledgenumber,
			String acknowledgedate, String description, Double rate, Integer qty, Double invoiceamount, Double cgst,
			Double sgst, Double igst, Double totaltax, Double amount, Double coinadjust, Double totalamount,
			String totalamountinwords, Integer flag, Date fromdate, Date todate, Date createddate, String errorlog,
			String isgstvalidated, String isesigncompleted, String companyName, Boolean settled, Double os,
			String gstNo, String tanno, Double balancetds, Double balanceittds, Double balancecgsttds,
			Double balancsgsttds, Double balanceigsttds, Double balanceincodetaxtds, Date crediteddate,
			String iscredited, String ispdfgenerated, Integer groupid, String qrcode) {
		super();
		this.invoicesummaryid = invoicesummaryid;
		this.addressofcustomer = addressofcustomer;
		this.irn = irn;
		this.sono = sono;
		this.custpono = custpono;
		this.invoiceno = invoiceno;
		this.invoicedate = invoicedate;
		this.natureofsupply = natureofsupply;
		this.customertype = customertype;
		this.natureoftransaction = natureoftransaction;
		this.natureofinvoice = natureofinvoice;
		this.reversechargeno = reversechargeno;
		this.acknowledgenumber = acknowledgenumber;
		this.acknowledgedate = acknowledgedate;
		this.description = description;
		this.rate = rate;
		this.qty = qty;
		this.invoiceamount = invoiceamount;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.totaltax = totaltax;
		this.amount = amount;
		this.coinadjust = coinadjust;
		this.totalamount = totalamount;
		this.totalamountinwords = totalamountinwords;
		this.flag = flag;
		this.fromdate = fromdate;
		this.todate = todate;
		this.createddate = createddate;
		this.errorlog = errorlog;
		this.isgstvalidated = isgstvalidated;
		this.isesigncompleted = isesigncompleted;
		this.companyName = companyName;
		this.settled = settled;
		this.os = os;
		this.gstNo = gstNo;
		this.tanno = tanno;
		this.balancetds = balancetds;
		this.balanceittds = balanceittds;
		this.balancecgsttds = balancecgsttds;
		this.balancsgsttds = balancsgsttds;
		this.balanceigsttds = balanceigsttds;
		this.balanceincodetaxtds = balanceincodetaxtds;
		this.crediteddate = crediteddate;
		this.iscredited = iscredited;
		this.ispdfgenerated = ispdfgenerated;
		this.groupid = groupid;
		this.qrcode = qrcode;
	}

	public TblInvoiceSummary() {
		super();
		// TODO Auto-generated constructor stub
	}
}