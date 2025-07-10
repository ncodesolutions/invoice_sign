package com.ncode.invoice.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ncode.invoice.dto.TenderInvoiceSummaryDto;
import com.ncode.invoice.entity.TblMstGstParam;
import com.ncode.invoice.esign.AppConstants;
import com.ncode.invoice.repository.InvoiceRepository;

import jakarta.transaction.Transactional;

@Service("EsignService")
@Transactional
public class EsignServiceImpl implements EsignService {
	@Autowired
	public InvoiceRepository invoiceRepository;

	@Autowired
	GeneratePdf generatePdf;

	@Autowired
	ObjectMapper mapper;

	@Override
	public List<String> getAllFilePaths() {
		List<String> getAllFilePath = invoiceRepository.getAllFilePaths();
		return getAllFilePath;
	}

	@Override
	public Boolean isSignCompetedMark(String invoiceNumber) throws Exception {
		Boolean flag = Boolean.FALSE;

		if (!invoiceNumber.isBlank() || !invoiceNumber.isEmpty()) {
			String lettersOnly = invoiceNumber;

			String productType = lettersOnly.startsWith(AppConstants.NEW_INVOICE_NUMBER) ? AppConstants.ETENDERINGSUB
					: lettersOnly.startsWith(AppConstants.INVOICE_NUMBER) ? AppConstants.ETENDERING
							: lettersOnly.startsWith(AppConstants.EDEC) ? AppConstants.DSC
									: lettersOnly.startsWith(AppConstants.ECONV) ? AppConstants.DSC
											: AppConstants.NEW_DSC;

			String subType = lettersOnly.startsWith(AppConstants.EDEC) ? AppConstants.AMOUNT
					: lettersOnly.startsWith(AppConstants.ECONV) ? AppConstants.CONVFEE
							: lettersOnly.startsWith(AppConstants.NEW_DSC) ? AppConstants.AMOUNT
									: lettersOnly.startsWith(AppConstants.NEW_CONVF) ? AppConstants.CONVFEE : null;

			if (productType.equals(AppConstants.ETENDERING)) {

				try {
					invoiceRepository.isSignCompetedMarkInvoice(invoiceNumber);
					flag = Boolean.TRUE;
				} catch (Exception e) {
					flag = Boolean.FALSE;
				}

			} else if (productType.equals(AppConstants.ETENDERINGSUB)) {
				try {
					invoiceRepository.isSignCompetedMarkInvoice(invoiceNumber);
					flag = Boolean.TRUE;
				} catch (Exception e) {
					flag = Boolean.FALSE;
				}

			} else {
				// Dsc Support Charges
				if (productType.equals(AppConstants.DSC)) {

					if (subType.equals(AppConstants.CONVFEE)) {
						try {
							invoiceRepository.isSignCompetedMarkConvffDSC(invoiceNumber);
							flag = Boolean.TRUE;
						} catch (Exception e) {
							flag = Boolean.FALSE;
						}
					} else {
						try {
							invoiceRepository.isSignCompetedMarkAmountDSC(invoiceNumber);
							flag = Boolean.TRUE;
						} catch (Exception e) {
							flag = Boolean.FALSE;
						}
					}
				}
				if (productType.equals(AppConstants.NEW_DSC)) {

					if (subType.equals(AppConstants.CONVFEE)) {
						try {
							invoiceRepository.isSignCompetedMarkNewConvffDSC(invoiceNumber);
							flag = Boolean.TRUE;
						} catch (Exception e) {
							flag = Boolean.FALSE;
						}
					} else {
						try {
							invoiceRepository.isSignCompetedMarkNewAmountDSC(invoiceNumber);
							flag = Boolean.TRUE;
						} catch (Exception e) {
							flag = Boolean.FALSE;
						}
					}
				}
			}

		} else {
			flag = Boolean.FALSE;
		}
		return flag;
	}

	@Override
	public String generatePdf() {
		String invNo = null;
		List<String> summaryList = invoiceRepository.summaryList();
		List<String> gstParams1 = invoiceRepository.getGstDetailsCompanyDetails();
		JSONObject gstParamsObj = new JSONObject(gstParams1.get(0));
		TblMstGstParam gstParams = null;
		try {
			gstParams = mapper.readValue(gstParamsObj.toString(), TblMstGstParam.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < summaryList.size(); i++) {

			try {
				JSONObject jsonObject = new JSONObject(summaryList.get(i));

				String gstNo = jsonObject.get("gstno").toString();
				String invoiceNumber = jsonObject.getString("invoiceno");
				invNo = invoiceNumber;
				String addressOfCustomer = jsonObject.getString("addressofcustomer");
				addressOfCustomer = addressOfCustomer.replaceAll("<br>", "\n");
				String[] parts = addressOfCustomer.split("\n");
				String depatmentName = parts[11].trim().replace("departmentname :", "").trim();
				String clientId = parts[12].trim().replace("clientid :", "").trim();
				String statePincode = parts[2].trim();
				String pinCodeStr = statePincode.replaceAll("\\D", "");
				Integer pinCode = 0;

				if (pinCodeStr.length() > 6) {
					String newPinCode = pinCodeStr.substring(6);
					pinCode = Integer.parseInt(newPinCode);
				} else {
					pinCode = Integer.parseInt(pinCodeStr);
				}
				String stateName = parts[3].trim();
				pdfGeneratePart(summaryList.get(0), generatePdf, gstParams, i);

			} catch (Exception e) {

			}
		}
		return null;
	}

	public Boolean pdfGeneratePart(String record, GeneratePdf generatePdf, TblMstGstParam gstParams, int i)
			throws Exception {

		Boolean flag = false;

		Map<String, String> response = new HashMap<String, String>();

		TenderInvoiceSummaryDto summaryOut = new Gson().fromJson(record, TenderInvoiceSummaryDto.class);
		String Acknowledgeno = null;
		String AcknowledgeDt = null;
		String irn = null;
		String qrCodeData = null;

//		if (response != null) {
		Acknowledgeno = summaryOut.getAcknowledgenumber();
		AcknowledgeDt = summaryOut.getAcknowledgedate();
		irn = summaryOut.getIrn();
			qrCodeData = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNCRTE3RTUxNDE5MjUyMjY0N0YwMUZEQkZGNTI3MUFENTI2OEQ3MzUiLCJ4NXQiOiJPLUYtVVVHU1VpWkg4Ql9iXzFKeHJWSm8xelUiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJOSUMgU2FuZGJveCIsImRhdGEiOiJ7XCJTZWxsZXJHc3RpblwiOlwiMjRBQUFDRzgzNzJRMVoyXCIsXCJCdXllckdzdGluXCI6XCIyNEFBQUxTMDY3OFExWkVcIixcIkRvY05vXCI6XCI1MjAwMVwiLFwiRG9jVHlwXCI6XCJJTlZcIixcIkRvY0R0XCI6XCIwNC8wNy8yMDI1XCIsXCJUb3RJbnZWYWxcIjo2NjM3NSxcIkl0ZW1DbnRcIjoxLFwiTWFpbkhzbkNvZGVcIjpcIjg2MDI5MFwiLFwiSXJuXCI6XCI4MDQzNjc0Yjc4MmYzNjhiNmRmOTI4OTMyZjg5ZDY4ZjJiYjVmNjI5YzY1YWUxNjVjYzBmYmVjZTg0MDNkNjgyXCIsXCJJcm5EdFwiOlwiMjAyNS0wNy0wNCAxMzowMDoxNlwifSJ9.YAIFRUXV4UN8V-SSYNFwDvIX44Jbl0eVNEFcM-Xxr6eVMytumsiEtbiOk6uY7QF6PUbxfsdY_lYsGCR69jOusGx8VvvQL0fkTM4lmI7Pze0op5ePrIjEnDQeya-_bGu-ig890qJ_eVeJ0GP8AGsbh2eepAi5YnDxG2JkmCUvQRFagk740YwJtFCbzZumMh7kzF7FjgNknQs4pK7M82RnfUHtmjXSpydWtGB_Iww0YEEZG_h4F1Hc6w0ywi__VCWbomFef_jIUWUql9yDm40L6c4aa0dAagHtadKNAyPxTeWekRhaTO4o_Z7HihbThcxSBQC71w06_DfPN5QoqLFf4A";
//		}

		response.put("Acknowledgeno", Acknowledgeno.toString());
		response.put("AcknowledgeDt", AcknowledgeDt);
		response.put("Irn", irn);
//			response.put("SignedInvoice", SignedInvoice);
			response.put("QrCode", qrCodeData);
		String storeGstNo = summaryOut.getGstno();

		try {
			flag = pdfGenerate(response, summaryOut, generatePdf, storeGstNo, gstParams, "NOCREDIT");
		} catch (Exception e) {
		}

		return flag;

	}

	public boolean pdfGenerate(Map<String, String> response, TenderInvoiceSummaryDto summaryOut,
			GeneratePdf generatePdf, String gstNo, TblMstGstParam gstParams, String checkCall) throws IOException {
//		
		boolean flag = false;
		String uniqueNumber = getUniqueNumberForB2C();

		if (gstNo != null) {
			if (gstNo.startsWith("24") && response != null) { // B2B
				String invoiceNo = generatePdf.generatePdfFile(summaryOut, response, gstParams, uniqueNumber,
						checkCall);

			} else {
				if (!gstNo.startsWith("24") && response == null) {
					String invoiceNo = generatePdf.generatePdfFile(summaryOut, response, gstParams, uniqueNumber,
							checkCall);

				}
			}
		} else {// B2C
			String invoiceNo = generatePdf.generatePdfFile(summaryOut, response, gstParams, uniqueNumber, checkCall);

		}
		return flag;
	}

	public String getUniqueNumberForB2C() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
		String datetime = ft.format(dNow);
		return datetime;
	}

}
