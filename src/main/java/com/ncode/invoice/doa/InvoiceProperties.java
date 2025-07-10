package com.ncode.invoice.doa;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "invoice")
public class InvoiceProperties 
{
	private String certFilePath;
    private String tokenPassword;
    private String inFolder;
    private String outFolder;
    private String rectangle1;
    private String rectangle2;
    private String rectangle3;
    private String rectangle4;
    private String signingOption;
    private String reason;
    private String location;
    private String certSerial;
	public String getCertFilePath() {
		return certFilePath;
	}
	public void setCertFilePath(String certFilePath) {
		this.certFilePath = certFilePath;
	}
	public String getTokenPassword() {
		return tokenPassword;
	}
	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}
	public String getInFolder() {
		return inFolder;
	}
	public void setInFolder(String inFolder) {
		this.inFolder = inFolder;
	}
	public String getOutFolder() {
		return outFolder;
	}
	public void setOutFolder(String outFolder) {
		this.outFolder = outFolder;
	}
	public String getRectangle1() {
		return rectangle1;
	}
	public void setRectangle1(String rectangle1) {
		this.rectangle1 = rectangle1;
	}
	public String getRectangle2() {
		return rectangle2;
	}
	public void setRectangle2(String rectangle2) {
		this.rectangle2 = rectangle2;
	}
	public String getRectangle3() {
		return rectangle3;
	}
	public void setRectangle3(String rectangle3) {
		this.rectangle3 = rectangle3;
	}
	public String getRectangle4() {
		return rectangle4;
	}
	public void setRectangle4(String rectangle4) {
		this.rectangle4 = rectangle4;
	}
	public String getSigningOption() {
		return signingOption;
	}
	public void setSigningOption(String signingOption) {
		this.signingOption = signingOption;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCertSerial() {
		return certSerial;
	}
	public void setCertSerial(String certSerial) {
		this.certSerial = certSerial;
	}
	public InvoiceProperties(String certFilePath, String tokenPassword, String inFolder, String outFolder,
			String rectangle1, String rectangle2, String rectangle3, String rectangle4, String signingOption,
			String reason, String location, String certSerial) {
		super();
		this.certFilePath = certFilePath;
		this.tokenPassword = tokenPassword;
		this.inFolder = inFolder;
		this.outFolder = outFolder;
		this.rectangle1 = rectangle1;
		this.rectangle2 = rectangle2;
		this.rectangle3 = rectangle3;
		this.rectangle4 = rectangle4;
		this.signingOption = signingOption;
		this.reason = reason;
		this.location = location;
		this.certSerial = certSerial;
	}
	public InvoiceProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

}
