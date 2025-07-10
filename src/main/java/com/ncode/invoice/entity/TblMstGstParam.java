package com.ncode.invoice.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "tblmstgstparam")

public class TblMstGstParam {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "tblmstgstparam_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")

	@Column(name = "id")
	private Integer id;

	@Column(name = "url")
	private String url;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "gstnumber")
	private String gstnumber;

	@Column(name = "clientid")
	private String clientid;

	@Column(name = "clientsecret")
	private String clientsecret;

	@Column(name = "env")
	private String env;

	@Column(name = "requesturl")
	private String requesturl;

	@Column(name = "status")
	private String status;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "createdon")
	private Date createdOn;

	@Column(name = "publickey")
	private String publickey;

	@Column(name = "lglnm")
	private String lglnm;

	@Column(name = "trdnm")
	private String trdnm;

	@Column(name = "addr1")
	private String addr1;

	@Column(name = "addr2")
	private String addr2;

	@Column(name = "loc")
	private String loc;

	@Column(name = "pin")
	private String pin;

	@Column(name = "stcd")
	private String stcd;

	@Column(name = "ph")
	private String ph;

	@Column(name = "em")
	private String em;

	@Column(name = "pa")
	private String pa;

	@Column(name = "mc")
	private String mc;

	@Column(name = "fulladdress")
	private String fulladdress;

	@Column(name = "terms")
	private String terms;

	@Column(name = "custpono")
	private String custpono;

	@Column(name = "customertype")
	private String customertype;

	@Column(name = "natureofinvoice")
	private String natureofinvoice;

	@Column(name = "natureofsupply")
	private String natureofsupply;

	@Column(name = "natureoftransaction")
	private String natureoftransaction;
	
	@Column(name = "advanceterms")
	private String advanceterms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGstnumber() {
		return gstnumber;
	}

	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientsecret() {
		return clientsecret;
	}

	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getRequesturl() {
		return requesturl;
	}

	public void setRequesturl(String requesturl) {
		this.requesturl = requesturl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPublickey() {
		return publickey;
	}

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	public String getLglnm() {
		return lglnm;
	}

	public void setLglnm(String lglnm) {
		this.lglnm = lglnm;
	}

	public String getTrdnm() {
		return trdnm;
	}

	public void setTrdnm(String trdnm) {
		this.trdnm = trdnm;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getEm() {
		return em;
	}

	public void setEm(String em) {
		this.em = em;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getFulladdress() {
		return fulladdress;
	}

	public void setFulladdress(String fulladdress) {
		this.fulladdress = fulladdress;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getCustpono() {
		return custpono;
	}

	public void setCustpono(String custpono) {
		this.custpono = custpono;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public String getNatureofinvoice() {
		return natureofinvoice;
	}

	public void setNatureofinvoice(String natureofinvoice) {
		this.natureofinvoice = natureofinvoice;
	}

	public String getNatureofsupply() {
		return natureofsupply;
	}

	public void setNatureofsupply(String natureofsupply) {
		this.natureofsupply = natureofsupply;
	}

	public String getNatureoftransaction() {
		return natureoftransaction;
	}

	public void setNatureoftransaction(String natureoftransaction) {
		this.natureoftransaction = natureoftransaction;
	}

	public String getAdvanceterms() {
		return advanceterms;
	}

	public void setAdvanceterms(String advanceterms) {
		this.advanceterms = advanceterms;
	}
	
	
	

}
