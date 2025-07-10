package com.ncode.invoice.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.ncode.invoice.dto.AppConstants;
import com.ncode.invoice.dto.TenderInvoiceSummaryDto;
import com.ncode.invoice.entity.TblMstGstParam;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Transactional
@Component
public class GeneratePdf {

	private static String fileStorePathFinal;
	private static String imgPathFinal;

	private final String FILENAME = "GeneratePdf.java";

	@Autowired
	B2CPdfGeneration b2cPdfGeneration;

	//
//	@Value("${spring.datasource.driver-class-name}")
//	private String dbDriverClass;

//	@Autowired 
//	private PropertiesGet getPropertiesGet;

	public static String createPdfFile(TenderInvoiceSummaryDto summaryDto, String checkCall, TblMstGstParam gstParam) {
		StringBuffer str = new StringBuffer();
		try {
			str.append("<html>");
			str.append("<head>");
			String title = checkCall.equals(AppConstants.CREDIT) ? AppConstants.TITLE_CREDIT

					: checkCall.equals(AppConstants.DSC) ? AppConstants.TITLE_SUPPORTCHARGE
							: AppConstants.TITLE_INVOICE;

			String title1 = checkCall.equals(AppConstants.CREDIT) ? AppConstants.TITLE1_CREDIT

					: checkCall.equals(AppConstants.DSC) ? AppConstants.TITLE1_SUPPORTCHARGE
							: AppConstants.TITLE1_INVOICE;

			String invoiceTitle = checkCall.equals(AppConstants.CREDIT) ? AppConstants.INVOICETITLE_CREDIT

					: checkCall.equals(AppConstants.DSC) ? AppConstants.INVOICETITLE_SUPPORTCHARGE
							: AppConstants.INVOICETITLE_INVOICE;

			str.append("	<title>" + title + "</title>");
			str.append(
					"<style>td, th { padding: 10px !important;border: 0.5px solid black; margin:10px !important;}</style>");
			str.append("</head>");
			str.append("<body style='font-family: Arial;'>");
			str.append(
					"	<table cellpadding='2' cellspacing='0' width='100%' style='font-size:11.5px; border-collapse: collapse; border: 0.5px solid black;'>");
			str.append("		<tr>");
			str.append("			<td colspan='3'>");
			str.append("				<img src='D:/images/header-design-3.jpg'> </img>");
			str.append("			</td>");
			str.append("			<td rowspan='3' style='width: 40%; text-align: center;'></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td  colspan='3' style='text-align: center;'><b>" + title1
					+ "(Original for Recipient)</b></td>");
			str.append("		</tr>");
			str.append("<tr><td colspan='3'>" + gstParam.getFulladdress() + "</td></tr>");
			str.append("		</table>");
			str.append(
					"		<table cellpadding='2' cellspacing='0' width='100%' style='font-size:11.5px; border-collapse: collapse; border: 0.5px solid black;'>");
			str.append("		<tr>");
			str.append("			<td><b>Bill To and SHIP To Address of Customer</b></td>");
			str.append("			<td colspan='3'>IRN :<#irn></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td rowspan='6' style='width: 48%;'><#BillToShipToCustomerAddress></td>");
			str.append("			<td colspan='2' style='width: 17%;'>S.O. No.: <#sono> </td>");
			str.append("			<td>Cust.P.O.No.:GR No.: <#customerpono></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td colspan='2' style='width: 17%;'>" + invoiceTitle + " No:<#invoiceNo> </td>");
			str.append("			<td style='width: 17%;'>" + invoiceTitle + " Date:<#invoiceDate></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td colspan='2' style='width: 17%;'>Nature Of Supply:<#natureOfSupply> </td>");
			str.append("			<td style='width: 17%;'>Customer Type: <#customerType></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='2' style='width: 17%;'>Nature Of Transaction: <#natureOfTransaction></td>");
			str.append("			<td style='width: 17%;'>Nature Of Invoice:<#natureOfInvoice></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td colspan='3' style='width: 17%;'>Reverse Charge: <#reverseCharge></td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td colspan='2' style='width: 17%;'>Acknowledge Number :<#acknowledgeNumber></td>");
			str.append("			<td style='width: 17%;'>Acknowledge Date : <#acknowledgeDate></td>");
			str.append("		</tr>");
			str.append("		</table>");
			str.append(
					"		<table cellpadding='2' cellspacing='0' width='100%' style='font-size:11.5px; border-collapse: collapse; border: 0.5px solid black;'>");
			str.append("		<tr>");
			str.append("			<td style='width: 50%;'><b>Description</b></td>");
			str.append("			<td><b>Rate</b></td>");
			str.append("			<td><b>Qty.(UOM:Nos.)</b></td>");
			str.append("			<td><b>Amount (in Rs.)</b></td>");
			str.append("		</tr>");
			// code for set dynamically value set in above HTML Code
			Double cgst = 0.0, igst = 0.0, sgst = 0.0, totalTax = 0.0, amount = 0.0, totalAmount = 0.0,
					coinAdjust = 0.0;
			String word = null;
			//

			str.append("		<tr>");
			str.append("			<td>" + summaryDto.getDescription() + "</td>");
			str.append("			<td>" + summaryDto.getRate() + "</td>");
			str.append("			<td>" + summaryDto.getQty() + "</td>");
			str.append("			<td>" + summaryDto.getInvoiceamount() + "</td>");
			str.append("		</tr>");
			cgst = summaryDto.getCgst();
			igst = summaryDto.getIgst();
			sgst = summaryDto.getSgst();
			totalTax = summaryDto.getTotaltax();
			amount = summaryDto.getInvoiceamount();
			totalAmount = summaryDto.getTotalamount();
			coinAdjust = summaryDto.getCoinadjust();
			word = summaryDto.getTotalamountinwords();

			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-bottom: 15px; padding-top: 10px;'>IGST (18%)</td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + igst + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-bottom: 15px; padding-top: 10px;'>CGST (9%)</td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + cgst + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-bottom: 15px; padding-top: 10px;'>SGST (9%)</td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + sgst + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-bottom: 15px; padding-top: 10px;'><b>Total Tax (18%) :</b></td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + totalTax + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-right: 15px; padding-top: 10px;'><b>Amount :</b></td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + amount + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-right: 15px; padding-top: 10px; margin-top: 10px;'><b>Coin Adjust. :</b></td>");
			str.append("			<td style='padding-left: 15px;'>Rs. " + coinAdjust + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append(
					"			<td colspan='3' style='text-align: right; padding-right: 15px; padding-top: 10px; margin-top: 10px;'><b>Total Amount :</b></td>");
			str.append("			<td style='padding-left: 15px;'>" + totalAmount + "</td>");
			str.append("		</tr>");
			str.append("		<tr>");
			str.append("			<td colspan='4' style='padding-top: 10px;'><b>In words: " + word + "</b></td>");
			str.append("		</tr>");
			str.append("		</table>");
			str.append(
					"		<table cellpadding='2' cellspacing='0' width='100%' style='font-size:11.5px; border-collapse: collapse; border: 0.5px solid black;'>");
			str.append(gstParam.getTerms());
			str.append("	</table>");

			str.append("</body>");
			str.append("</html>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();

	}

	public String generatePdfFile(TenderInvoiceSummaryDto summaryDto, Map<String, String> response,
			TblMstGstParam gstParams, String uniqueNumber, String checkCall) throws IOException {

		B2CPdfGeneration B2cPdfGenrete = new B2CPdfGeneration();
		String newAddress[] = summaryDto.getAddressofcustomer().split("departmentname");
//		List<TblMstGstParam> mstGstParams = gstParamRepository.getGstDetails();

		Document document = new Document();
		OutputStream file = null;
		PdfWriter writer = null;
		String finalString = null;
		String errorMessage = null;
		Boolean flagCheck = true;
		String storeFileName = null;
		String Acknowledgeno = null;
		String AcknowledgeDt = null;
		String irn = null;
		String qrCodeData = null;

		String path = "\\\\192.168.40.208\\Common_Share\\Invoice_Documents\\TEST";
		if (response != null) {
			Acknowledgeno = response.get("Acknowledgeno");
			AcknowledgeDt = response.get("AcknowledgeDt");
			irn = response.get("Irn");
			qrCodeData = response.get("QrCode");
		}
		try {
			String str = createPdfFile(summaryDto, checkCall, gstParams);
			finalString = str.replaceAll("<#BillToShipToCustomerAddress>", newAddress[0])
					.replaceAll("<#irn>", irn == null ? "-" : irn)
					.replaceAll("<#sono>", summaryDto.getSono() == null ? "" : summaryDto.getSono())
					.replaceAll("<#customerpono>", gstParams.getCustpono())
					.replaceAll("<#invoiceNo>", summaryDto.getInvoiceno())
					.replaceAll("<#invoiceDate>", summaryDto.getInvoicedate())
					.replaceAll("<#natureOfSupply>", gstParams.getNatureofsupply())
					.replaceAll("<#customerType>", gstParams.getCustomertype())
					.replaceAll("<#natureOfTransaction>", gstParams.getNatureoftransaction())
					.replaceAll("<#natureOfInvoice>", gstParams.getNatureofinvoice())
					.replaceAll("<#reverseCharge>",
							summaryDto.getReversechargeno() == null ? "" : summaryDto.getReversechargeno())
					.replaceAll("<#acknowledgeNumber>", Acknowledgeno == null ? "-" : Acknowledgeno)
					.replaceAll("<#acknowledgeDate>", AcknowledgeDt == null ? "-" : AcknowledgeDt)
					.replaceAll("<#igst>", summaryDto.getIgst() == null ? "0" : summaryDto.getIgst().toString())
					.replaceAll("<#cgst>", summaryDto.getCgst() == null ? "0" : summaryDto.getCgst().toString())
					.replaceAll("<#sgst>", summaryDto.getSgst() == null ? "0" : summaryDto.getSgst().toString())
					.replaceAll("<#totalTax>", summaryDto.getTotaltax().toString())
					.replaceAll("<#amount>", summaryDto.getInvoiceamount().toString())
					.replaceAll("<#coinAdjust>", summaryDto.getCoinadjust().toString())
					.replaceAll("<#totalAmount>", summaryDto.getTotalamount().toString())
					.replaceAll("<#inWords>", summaryDto.getTotalamountinwords()).replaceAll("<br>", "<br/>");
			System.out.println("finalString {} " + finalString);
			if (finalString.contains("<#")) {
				flagCheck = false;
				errorMessage = "Error TO Genrating PDF Some Field Are Missing";
			} else {
				flagCheck = true;
			}
			float qrCodePosX = 430f;
			float qrCodePosY = 655f;

			InputStream is = new ByteArrayInputStream(finalString.toString().getBytes());
			if (flagCheck.equals(Boolean.TRUE)) {
				String fileName = null;
				String invoiceNumber = null;
				String networkPath = null;

				if (summaryDto.getNewinvoicename() != null) {
					fileName = summaryDto.getNewinvoicename() + ".pdf";
//					networkPath = fileStorePathFinal + fileName;
					networkPath = path + fileName;
					invoiceNumber = summaryDto.getNewinvoicename();
				} else {
					fileName = summaryDto.getInvoiceno() + ".pdf";
//					networkPath = fileStorePathFinal + fileName;
					networkPath = path + "\\" + fileName;

					invoiceNumber = summaryDto.getInvoiceno();

				}

				file = new FileOutputStream(new File(networkPath));
				writer = PdfWriter.getInstance(document, file);
				document.open();

				// new one
				Image qrCodeImage = null;
				if (response == null) {
					qrCodeImage = B2cPdfGenrete.getQrCodeForB2C(summaryDto.getTotalamount(), invoiceNumber, gstParams,
							uniqueNumber);
				} else {
					qrCodeImage = generateQRCodeImage(qrCodeData);
				}
				// End new one
				qrCodeImage.setAbsolutePosition(qrCodePosX, qrCodePosY);
				document.add(qrCodeImage);

				storeFileName = summaryDto.getNewinvoicename() == null ? summaryDto.getInvoiceno()
						: summaryDto.getNewinvoicename();
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
				return storeFileName;
			} else {
				Integer id = summaryDto.getInvoicesummaryid();
				return "";
			}
		} catch (Exception e) {
			document.close();
			file.close();

			storeFileName = storeFileName + ".pdf";
			Path filePath = Paths.get(path.toString(), "", storeFileName);
			File file1 = filePath.toFile();
			if (file1.exists()) {
				file1.delete();
			}
			return "";
		} finally {
			document.close();
			if (flagCheck) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Image generateQRCodeImage(String content) throws Exception {
		int size = 120;
		String fileType = "png";

		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);

		BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				bufferedImage.setRGB(x, y, (bitMatrix.get(x, y) ? 0 : 0xFFFFFF));
			}
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, fileType, byteArrayOutputStream);
		byte[] imageBytes = byteArrayOutputStream.toByteArray();
		return Image.getInstance(imageBytes);
	}
}