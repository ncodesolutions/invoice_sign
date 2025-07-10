package com.ncode.invoice.dto;
 
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 

@JsonIgnoreProperties(ignoreUnknown = true)
public class TenderInvoiceSummaryDto {
 
	Integer invoicesummaryid;
	String addressofcustomer;
	String irn;
	String sono;
	String custpono;
	String invoiceno;
	String invoicedate;
	String natureofsupply;
	String customertype;
	String natureoftransaction;
	String natureofinvoice;
	String reversechargeno;
	String acknowledgenumber;
	String acknowledgedate;
	String description;
	Double rate;
	Integer qty;
	Double invoiceamount;
	Double cgst;
	Double sgst;
	Double igst;
	Double tax1;
	Double tax2;
	Double tax3;
	Double tax4;
	Double tax5;
	String islocal;
	Double totaltax;
	Double amount;
	Double coinadjust;
	Double totalamount;
	String totalamountinwords;
	Integer transcode;
	Integer flag;
	String fromdate;
	String todate;
	String createddate;
	Integer groupid;
	String newinvoicename;
	private Double commisionamount;
	
	String gstno;
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
	public Double getTax1() {
		return tax1;
	}
	public void setTax1(Double tax1) {
		this.tax1 = tax1;
	}
	public Double getTax2() {
		return tax2;
	}
	public void setTax2(Double tax2) {
		this.tax2 = tax2;
	}
	public Double getTax3() {
		return tax3;
	}
	public void setTax3(Double tax3) {
		this.tax3 = tax3;
	}
	public Double getTax4() {
		return tax4;
	}
	public void setTax4(Double tax4) {
		this.tax4 = tax4;
	}
	public Double getTax5() {
		return tax5;
	}
	public void setTax5(Double tax5) {
		this.tax5 = tax5;
	}
	public String getIslocal() {
		return islocal;
	}
	public void setIslocal(String islocal) {
		this.islocal = islocal;
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
	public String getGstno() {
		return gstno;
	}
	public void setGstno(String gstno) {
		this.gstno = gstno;
	}
	public String getTotalamountinwords() {
		return totalamountinwords;
	}
	public void setTotalamountinwords(String totalamountinwords) {
		this.totalamountinwords = totalamountinwords;
	}
	public Integer getTranscode() {
		return transcode;
	}
	public void setTranscode(Integer transcode) {
		this.transcode = transcode;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getFromdate() {
		return fromdate;
	}
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public String getNewinvoicename() {
		return newinvoicename;
	}
	public void setNewinvoicename(String newinvoicename) {
		this.newinvoicename = newinvoicename;
	}
	public Double getCommisionamount() {
		return commisionamount;
	}
	public void setCommisionamount(Double commisionamount) {
		this.commisionamount = commisionamount;
	}
	
	
	
 
 
}
