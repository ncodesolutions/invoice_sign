package com.ncode.invoice.dto;


import java.util.Map;

public class AppConstants {

	public static final String ALIVE_MSG = "ALIVE";

	public static final String JWT_TOKEN = "jwtToken";

	public static final String SUCCESS = "success";

	public static final String FAIL = "fail";
	public static final String FAIL_NOT = "Not fail";

	public static final String ERROR = "error";

	public static final String NO_RECODE_FOUND = "No Recode Found";

	public static final String ACTIVE = "Active";
	public static final String EDWORKINGKEY = "Ncodenpayinvoice"; 

	public static final String DEACTIVE = "De-Active";
	public static final String MENU_URL_LINK = "/home/";

	public static final String CREDIT = "CREDIT";
	public static final String NOCREDIT = "NOCREDIT";
	// public static final String ET = "ET";
	public static final String ET = "11";
//	public static final String DSCCRT = "DSCCRT";
	public static final String DSC = "DSC";
	public static final String AMOUNT = "AMOUNT";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String IRNDATA = "IRNData";
	public static final String GSTDATA = "GSTData";
	public static final String CONVFEE = "CONVFEE";
	public static final String NEW_DSC = "121C";

	public static final String GST_NO_SELF = "24AAACG8372Q1Z2";

	public static final String NEW_CONVF = "122C";
	public static final String DSC_KYC = "DSC KYC";

	public static final String ETENDERING = "E-TENDERING";
	public static final String ETENDERINGSUB = "E-TENDERING_SUB";
	public static final String NEW_INVOICE_NOT = "New Invoice is not generated.";

	public static final String TITLE_INVOICE = "Invoice PDF";
	public static final String TITLE_CREDIT = "Credit Invoice PDF";
	public static final String TITLE_SUPPORTCHARGE = "Support Charge PDF";
	public static final String TITLE1_INVOICE = "Tax Invoice";
	public static final String TITLE1_CREDIT = "Tax Credit";
	public static final String TITLE1_SUPPORTCHARGE = "Tax Support Charge";
	public static final String INVOICETITLE_INVOICE = "Invoice";
	public static final String INVOICETITLE_CREDIT = "Credit";
	public static final String INVOICETITLE_SUPPORTCHARGE = "Support Charge";
	public static final String SERIES = "5";

	public static final String DSC_KYC_TITLE_CREDIT = "DscKyc Credit Invoice PDF";
	public static final String DSC_KYC_TITLE_SUPPORTCHARGE = "DscKyc Charge PDF";
	public static final String DSC_KYC_TITLE_INVOICE = "DscKyc PDF";
	public static final String DSC_KYC_TITLE1_CREDIT = "DscKyc Tax Credit";
	public static final String DSC_KYC_TITLE1_SUPPORTCHARGE = "Tax DscKyc Charge";
	public static final String DSC_KYC_TITLE1_INVOICE = "DscKyc Tax Invoice";
	public static final String DSC_KYC_INVOICETITLE_CREDIT = "DscKyc Credit";
	public static final String DSC_KYC_INVOICETITLE_SUPPORTCHARGE = "DscKyc Charge";
	public static final String DSC_KYC_INVOICETITLE_INVOICE = "DscKyc";
	
	public static final String AUCTION = "Auction KYC";
	public static final String AUCTION_TITLE_CREDIT = "Auction Credit Invoice PDF";
	public static final String AUCTION_TITLE_SUPPORTCHARGE = "Auction Charge PDF";
	public static final String AUCTION_TITLE_INVOICE = "Invoice PDF";
	public static final String AUCTION_TITLE1_CREDIT = "Auction Tax Credit";
	public static final String AUCTION_TITLE1_SUPPORTCHARGE = "Tax Charge";
	public static final String AUCTION_TITLE1_INVOICE = "Tax Invoice";
	public static final String AUCTION_INVOICETITLE_CREDIT = "Auction Credit";
	public static final String AUCTION_INVOICETITLE_SUPPORTCHARGE = "Auction Charge";
	public static final String AUCTION_INVOICETITLE_INVOICE = " Invoice";
	
	public static final String TOKEN_TITLE_CREDIT = "Token Credit Invoice PDF";
	public static final String TOKEN_TITLE_SUPPORTCHARGE = "Token Charge PDF";
	public static final String TOKEN_TITLE_INVOICE = "Token PDF";
	public static final String TOKEN_TITLE1_CREDIT = "Token Tax Credit";
	public static final String TOKEN_TITLE1_SUPPORTCHARGE = "Tax Token Charge";
	public static final String TOKEN_TITLE1_INVOICE = "Token Tax Invoice";
	public static final String TOKEN_INVOICETITLE_CREDIT = "Token Credit";
	public static final String TOKEN_SUPPORT = "Token Support";
	
	public static final String TOKEN_INVOICETITLE_SUPPORTCHARGE = "Token Charge";
	public static final String TOKEN_INVOICETITLE_INVOICE = "Token";

	public static final String SUCCESS_STATUS = "200 OK";

	public static final String INVOICE_ERROR = "Error occurred while processing invoice.";
	public static final String INVOICE_SUMMARY_ERROR = "Error occurred while processing invoice summary.";
	public static final String INVOICE_GENERATE_ERROR = "Error occurred while processing generate invoice pdf.";
	public static final String INVOICE_ERROR_LOG = "Get all Invoice details form error log";
	public static final String ISSIGN_COMPLETED = "Is SignCompeted Comepletd : ";
	public static final String ISSIGN_COMPLETED_ERROR = "Is SignCompeted not Comepletd : ";
	public static final String GST_AUTH_ERROR = "Error in validted gst authentication ...!!! ";
	public static final String GET_ALL_FILE_PATH_COMPLETED = "Get all file path Comepletd : ";
	public static final String GET_ALL_FILE_PATH_ERROR = "Error get all file path  : ";
	public static final String INVOICE_SUCCESS = "Pull Invoice data successfully.";
	public static final String INVOICE_SUMMARY_SUCCESS = "Push Invoice Summary data successfully.";
	public static final String INVOICE_GENERATE_SUCCESS = "Invoice Summary PDF generated successfully.";
	public static final String ISSIGN_COMPLETED_DATA = "Data is get successfully : ";
	public static final String ISSIGN_COMPLETED_DATA_ERROR = "Data is not get Comepletd : ";

	public static final Integer B2B = 9;
	public static final Integer B2C = 10;
	public static final String B2B_STR = "B2B";
	public static final String B2C_STR = "B2C";
//	public static final String EDEC = "EDEC";
//	public static final String ECONV = "ECONV";
	public static final String ECONV = "122";
	public static final String EDEC = "121";

	public static final String AUTHORIZATION = "Authorization";
	public static final String CONTENT_TYPE_ = "Content-Type";
	public static final Integer ZERO = 0;
	public static final Integer ONE = 1;
	public static final String CONTENT_TYPE = "application/json";
	public static final String NEW_INVOICE_NUMBER_SERIES = "C3";
	public static final String INVOICE_NUMBER_SERIES = "5200";

	public static final String INVOICE_NUMBER = "11";

	public static final String NEW_INVOICE_NUMBER = "11C";

	public static final String DSC_KYC_INVOICE_NUMBER_SERIES = "DC";
	public static final String DSC_KYC_NUMBER = "31";
	public static final String DSC_KYC_NUMBER_COMMISSION = "32";
	public static final String NEW_DSC_KYC_NUMBER_COMMISSION = "32C";
	public static final String NEW_DSC_KYC_NUMBER = "31C";
	public static final String NEW_DSC_KYC_INVOICE_NUMBER_SERIES = "C3";
	
	public static final String TOKEN_CREDIT_INVOICE_NUMBER_SERIES = "TOKEN1";
	public static final String TOKEN_CREDIT_NUMBER = "41";
	public static final String NEW_TOKEN_CREDIT_NUMBER = "41C";
	public static final String NEW_TOKEN_CREDIT_INVOICE_NUMBER_SERIES = "TOKEN1";

	
	public static final String AUCTION_KYC_INVOICE_NUMBER_SERIES = "AC";
	public static final String AUCTION_KYC_NUMBER = "51";
	public static final String AUCTION_DSC_KYC_NUMBER = "51C";
	public static final String AUCTION_DSC_KYC_INVOICE_NUMBER_SERIES = "AC";
	public static final Object NOT_REQUEST_FOR_ETCRT = "For ETCRT invoices You are not request for credit note";
	public static final Object NOT_REQUEST_FOR_DSC_KYC = "For DscKyc CRT invoices You are not request for credit note";
	public static final String STATE_NAME = "Gujarat";
	public static final String SEPARATOR_GST = "<#invoice>";

	// Masters Massage Common :: Start

	public static final String DOCUMENT_SAVE_MSG = "Document Type saved successfully";
	public static final String INVOICE_SAVE_MSG = "Invoice Type saved successfully";
	public static final String PRODUCT_SAVE_MSG = "Product Type saved successfully";
	public static final String PURPOSE_SAVE_MSG = "Purpose Type saved successfully";
	public static final String STATE_SAVE_MSG = "State saved successfully";
	public static final String SUPPLY_TYPE_SAVE_MSG = "Supply Type saved successfully";
	public static final String TAX_SAVE_MSG = "Tax Type saved successfully";
	public static final String TRANSACTION_SAVE_MSG = "Transaction Type saved successfully";

	public static final String DOCUMENT_UPDATE_MSG = "Document Type Update successfully";
	public static final String INVOICE_UPDATE_MSG = "Invoice Type Update successfully";
	public static final String PRODUCT_UPDATE_MSG = "Product Type Update successfully";
	public static final String PURPOSE_UPDATE_MSG = "Purpose Type Update successfully";
	public static final String STATE_UPDATE_MSG = "State  Update successfully";
	public static final String SUPPLY_TYPE_UPDATE_MSG = "Supply Type Update successfully";
	public static final String TAX_TYPE_UPDATE_MSG = "Tax Type Update successfully";
	public static final String TRANSACTION_UPDATE_MSG = "Transaction Type Update successfully";

	public static final String DOCUMENT_ACTIVE_DEACTIVE_MSG = "Document Type Active-Deactive successfully";
	public static final String INVOICE_ACTIVE_DEACTIVE_MSG = "Invoice Active-Deactive successfully";
	public static final String PRODUCT_ACTIVE_DEACTIVE_MSG = "Product Type Active-Deactive successfully";
	public static final String PURPOSE_ACTIVE_DEACTIVE_MSG = "Purpose Type Active-Deactive successfully";
	public static final String STATE_ACTIVE_DEACTIVE_MSG = "State Type Active-Deactive successfully";
	public static final String SUPPLY_TYPE_ACTIVE_DEACTIVE_MSG = "Supply Type Active-Deactive successfully";
	public static final String TAX_TYPE_ACTIVE_DEACTIVE_MSG = "Tax Type Active-Deactive successfully";
	public static final String TRANSACTION_ACTIVE_DEACTIVE_MSG = "Transaction Type Active-Deactive successfully";

	public static final String DOCUMENT_ERROR_MSG = "Error in Document Type saved ";
	public static final String INVOICE_ERROR_MSG = "Error in Invoice Type saved ";
	public static final String PRODUCT_ERROR_MSG = "Error in Product Type saved ";
	public static final String PURPOSE_ERROR_MSG = "Error in Purpose Type saved ";
	public static final String STATE_ERROR_MSG = "Error in State saved ";
	public static final String SUPPLY_TYPE_ERROR_MSG = "Error in Supply Type saved ";
	public static final String TAX_ERROR_MSG = "Error in Tax Type saved ";
	public static final String TRANSACTION_ERROR_MSG = "Error in Transaction Type saved ";

	public static final String DOCUMENT_GET_MSG = "Document Type Get Data successfully";
	public static final String INVOICE_GET_MSG = "Invoice Type Get Data successfully";
	public static final String PRODUCT_GET_MSG = "Product Type Get Data successfully";
	public static final String PURPOSE_GET_MSG = "Purpose Type Get Data successfully";
	public static final String STATE_GET_MSG = "State Get Data successfully";
	public static final String SUPPLY_TYPE_GET_MSG = "Supply Type Get Data successfully";
	public static final String TAX_GET_MSG = "Tax Type Get Data successfully";
	public static final String TRANSACTION_GET_MSG = "Transaction Type Get Data successfully";

	public static final String DOCUMENT_GET_ERROR_MSG = "Error in Document Type Getting Data";
	public static final String INVOICE_GET_ERROR_MSG = "Error in Invoice Type Getting Data";
	public static final String PRODUCT_GET_ERROR_MSG = "Error in Product Type Getting Data";
	public static final String PURPOSE_GET_ERROR_MSG = "Error in Purpose Type Getting Data";
	public static final String STATE_GET_ERROR_MSG = "Error in State Getting Data";
	public static final String SUPPLY_TYPE_GET_ERROR_MSG = "Error in Supply Type Getting Data";
	public static final String TAX_GET_ERROR_MSG = "Error in Tax Type Getting Data";
	public static final String TRANSACTION_GET_ERROR_MSG = "Error in Transaction Type Getting Data";

	public static final String NO_RECORD_FOUND = "No record found.";

	public static final String DOCUMENT_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Document Type Active-Deactive ";
	public static final String INVOICE_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Invoice Active-Deactive ";
	public static final String PRODUCT_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Product Type Active-Deactive ";
	public static final String PURPOSE_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Purpose Type Active-Deactive ";
	public static final String STATE_ACTIVE_DEACTIVE_ERROR_MSG = "Error in State Type Active-Deactive ";
	public static final String SUPPLY_TYPE_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Supply Type Active-Deactive ";
	public static final String TAX_TYPE_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Tax Type Active-Deactive ";
	public static final String TRANSACTION_ACTIVE_DEACTIVE_ERROR_MSG = "Error in Transaction Type Active-Deactive ";

	public static final String DOCUMENT_UPDATE_ERROR_MSG = "Error in Document Type Update ";
	public static final String INVOICE_UPDATE_ERROR_MSG = "Error in Invoice Type Update ";
	public static final String PRODUCT_UPDATE_ERROR_MSG = "Error in Product Type Update ";
	public static final String PURPOSE_UPDATE_ERROR_MSG = "Error in Purpose Type Update ";
	public static final String STATE_UPDATE_ERROR_MSG = "Error in State  Update ";
	public static final String SUPPLY_TYPE_UPDATE_ERROR_MSG = "Error in Supply Type Update ";
	public static final String TAX_TYPE_UPDATE_ERROR_MSG = "Error in Tax Type Update ";
	public static final String TRANSACTION_UPDATE_ERROR_MSG = "Error in Transaction Type Update ";

	public static final Object ROLE_ACTIVE_SUCCESS = "Role Type Active-Deactive successfully";
	public static final Object ROLE_ACTIVE_ERROR = "Error in Role Type Active-Deactive";
	public static final Object ROLE_SAVE_SUCCESS = "Role Type saved successfully";
	public static final Object ROLE_SAVE_ERROR = "Error in Save Role Type";
	public static final Object ROLE_UPDATE_MSG = "Role Type Update Successfully ";
	public static final Object ROLE_UPDATE_ERROR_MSG = "Error In Update Role Type Data";

	public static final Object PRIVILEGE_SAVE_SUCCESS = "Privilege Type saved successfully";
	public static final Object PRIVILEGE_SAVE_ERROR = "Error in Save Privilege Type";
	public static final Object PRIVILEGE_ACTIVE_SUCCESS = "Privilege Type Active-Deactive successfully";
	public static final Object PRIVILEGE_ACTIVE_ERROR = "Error in Privilege Type Active-Deactive";
	public static final Object PRIVILEGE_UPDATE_MSG = "Privilege Type Update Successfully ";
	public static final Object PRIVILEGE_UPDATE_ERROR_MSG = "Error In Update Privilege Type Data";

	public static final Object USER_PRIVILEGE_SAVE_SUCCESS = "User Privilege saved successfully";
	public static final Object USER_PRIVILEGE_SAVE_ERROR = "Error in Save User Privilege";
	public static final Object USER_PRIVILEGE_ACTIVE_SUCCESS = "User Privilege Active-Deactive successfully";
	public static final Object USER_PRIVILEGE_ACTIVE_ERROR = "Error in User Privilege Active-Deactive";
	public static final Object USER_PRIVILEGE_UPDATE_MSG = "User Privilege Update Successfully ";
	public static final Object USER_PRIVILEGE_UPDATE_ERROR_MSG = "Error In Update User Privilege Data";

	public static final Object INVOICE_REQ_RES_SAVE_SUCCESS = "Invoice Request Or Response saved successfully";
	public static final Object INVOICE_REQ_RES_SAVE_ERROR = "Error Invoice Request Or Response saved";
	public static final Object INVOICE_REQ_RES_GET = "Get all Invoice Request Or Response data";
	public static final Object INVOICE_REQ_RES_GET_ERROR = "Error In Get all Invoice Request Or Response data";
	public static final String MISSMATCH = "ClientId , gstno and depatment are not matches";
	public static final String INVOICE_DATA_NOT_AVAILABLE = "Some data not available for Groupd id : ";
	public static final Object PINCODE_INVAILD = "Pincode is not valid";
	public static final String ENTER_GST = "Please enter gstno or gsttanno";

	public static final Object PAYMENT_RECEVIED_SAVE_SUCCESS = "payment recevied saved successfully";
	public static final Object PAYMENT_RECEVIED_SAVE_ERROR = "Error recevied saved";
	public static final Object PAYMENT_RECEVIED_GET = "Get all recevied data";
	public static final Object PAYMENT_RECEVIED_ERROR = "Error recevied data";
	public static final Object USER_ERROR_MSG = "User Not Found";
	public static final Object COMPANY_GET_ERROR_MSG = "Error In Get all Company data";
	public static final Object GST_LIST_ERROR = "Error In Get GST Data ";
	public static final Object PAYMENT_TYPE_ERROR = "Error In Get Payment Type";
	public static final Object COMPANY_ADD = "Add Company successfully";
	public static final Object COMPANY_ADD_ERROR = "Company not add successfully";
	public static final Object COMPANY_UPdATE = "Update Company successfully";
	public static final Object COMPANY_UPDATE_ERROR = "Company not Update successfully";

	public static final Object BANK_SAVE_SUCCESS = "Bank Data Save Successfully";
	public static final Object BANK_SAVE_ERROR = "Error in saving bank data";
	public static final Object BANK_ACTIVE_SUCCESS = "Bank Status change successfully";
	public static final Object BANK_ACTIVE_ERROR = "Error in chages Bank Status ";
	public static final Object BANK_UPDATE_MSG = "Bank Data Update Successfully";
	public static final Object BANK_UPDATE_ERROR_MSG = "error in Update Bank data";

	public static final Object DSC_PUSH_SAVE_SUCCESS = "Dsc data saved successfully";
	public static final Object DSC_PUSH_SAVE_ERROR = "Error Dsc data saved";
	public static final Object DSC_DATA_GET = "Get all Dsc data";
	public static final Object DSC_DATA_GET_ERROR = "Error In Get all Dsc data";
	public static final Object DSC_DATA_ENCRYPT_DCRYPT_ERROR = "Error In Dsc data encrypt decrypt";

	public static final Object DSC_SUPPORT_DATA_PULL_ERROR = "Dsc Support data is empty, cannot proceed with invoice application";
	public static final Object DSC_SUPPORT_DATA_PULL_ERROR_EXCEPTION = "Data not get fron the npay side, cannot proceed with invoice application";
	public static final Object FORWARD_EXCEPTION = "Data not avaiable for forward";
	public static final Object FORWARD_EXCEPTION_LOG = "Data not avaiable for forward  | Dsc Support Data failed:forwardData()";
	public static final String FORWARD_EXCEPTION1 = "forward Dsc Support Data success: but not updated in Db updatedstatus";
	public static final String FORWARD_EXCEPTION_LOG1 = "forward Dsc Support Data success: but not updated in Db updatedstatus forwardData() ";
	public static final String NOT_FORWARD_EXCEPTION = "forward Dsc Support Data failed";

	public static final String NOT_FORWARD_EXCEPTION_LOG = "Not forward Dsc Support Data forwardData() ";
	public static final String MSG_NEW_INVOICE = "newInvoiceGeneration(),new invoice generation";
	public static final String MSG_NEW_PDF = "newInvoiceGeneration(),new pdf generation";
	public static final Object STATIC_MOBILE_NO = "9876543210";
	public static final Object STATIC_ADDRESS = "Ahmedabad";
	public static final Object NPAY_ID_NOT_PRESENT = "Npay id is not preset";
	public static final Object IRN_TO_NO_GET_DATA = "IRN to not get data from gst side";
	public static final Object PDF_NOT_GENERATE = "pdf is not generated for the invoice no : ";
	public static final Object PDF_IS_GENERATE = "pdf is generated for the invoice no : ";

	public static final Object INVOICE_NOT_GENERATE = "Invoice not generated so not process for pdf generation";

	public static final Object CREDITED_DATA_GET = "Get all Credited data";
	public static final Object CREDITED_DATA_GET_DATA_GET_ERROR = "Error In Get all Credited data";
	public static final Object CREDITED_DATA_SAVE_SUCCESS = "Credited data saved successfully";
	public static final Object CREDITED_DATA_SAVE_ERROR = "Error Credited data saved";
	public static final Object CREDIT_SUMMARY_ALREDY = "Alredy you request for the credit note!";
	public static final String CREDIT_PROCESS_NOT = "You not request for the credit note for the invoce no : ";
	public static final Object GET_INVOICE_DETAILS_ERROR = "Error In Get invoice details";
	public static final Object UPDATION_ERROR = "Error In Updation";
	public static final Object BULID_NEW_SUMMARY_ERROR = "Error In Build new Summary";
	public static final Object TEDNER_ERROR = "Error in find tender details by groupid";
	public static final Object NEW_INVOCIE_GENERATION_ERROR = "Error in new invoice generation";
	public static final Object TEDNER_UPATION = "Error in tender updation by tender id";
	public static final Object CREDITED_DATA_GET_ERROR_ = "Error In Get Credited data fof invoice";

	public static final Object CREDIT_DSCSUPPORT_SUMMARY_DATA_GET = "Get all Credit DscSupport Summary data";
	public static final Object CREDIT_DSCSUPPORT_SUMMARY_GET_ERROR = "Error In Get all Credit DscSupport Summary data";
	public static final Object CREDIT_DSCSUPPORT_SUMMARY_SAVE_SUCCESS = "Credit DscSupport Summary data saved successfully";
	public static final Object CREDIT_DSCSUPPORT_SUMMARY_SAVE_ERROR = "Error Credit DscSupport Summary data saved";

	public static final Object CREDIT_SUMMARY_DATA_GET = "Get all Credit  Summary data";
	public static final Object CREDIT_SUMMARY_GET_ERROR = "Error In Get all Credit Summary data";
	public static final Object CREDIT_SUMMARY_SAVE_SUCCESS = "Credit Summary data saved successfully";
	public static final Object CREDIT_SUMMARY_SAVE_ERROR = "Error Credit Summary data saved";

	public static final Object TENDER_DATA_UPDATE_SUCESS = "Tender Data Update Successfully";
	public static final Object TENDER_DATA_UPDATE_ERROR = "Error In Update Tender Data";

	public static final Object USER_REGISTER_SAVE_SUCCESS = "Register new user successfully ";
	public static final Object USER_REGISTER_SAVE_ERROR = "Error New User Register ";
	public static final Object USER_GET = "Get User data ";
	public static final Object USER_GET_ERROR = "Error In Get User data ";
	public static final Object USER_PASSWORD_UPDATE_SUCCESS = "User password update successfully ";
	public static final Object USER_PASSWORD_UPDATE_ERROR = "Error user password update ";
	public static final Object USER_PASSWORD_FORGOT_SUCCESS = "User password forgot successfully ";
	public static final Object USER_PASSWORD_FORGOT_ERROR = "Error user password forgot ";
	public static final String USER_NOT_FOUND = "User not found";

	public static final Object MENU_DATA_GET = "Get all Menu data";
	public static final Object MENU_DATA_GET_DATA_GET_ERROR = "Error In Get all Menu data";
	public static final Object MENU_DATA_SAVE_SUCCESS = "Menu data saved successfully";
	public static final Object MENU_DATA_SAVE_ERROR = "Error Menu data saved";
	public static final Object MENU_NAME_NULL_ERROR = "Please fill the menu name";
	public static final Object MENU_NAME_PRESET_ERROR = "Alredy present menu";
	public static final Object MENU_NOT_FOUND_ERROR = "Menu not present";
	public static final Object MENU_DATA_UPDATE_SUCCESS = "Menu data update successfully";
	public static final Object MENU_DATA_UPDATE_ERROR = "Error Menu data updation";
	public static final Object MENU_DATA_ACTIVE_DEACTIVE_SUCCESS = "Menu status update successfully";
	public static final Object MENU_DATA_ACTIVE_DEACTIVE_ERROR = "Error Menu status updation";
	public static final Object MENU_NAME_NULL_ERROR_CHECK = "Menu name is missing";
	public static final Object MENU_PRIORITY_UPDATE_SUCCESS = "Menu priority update successfully";
	public static final Object MENU_PRIORITY_UPDATE_ERROR = "Error Menu priority updation";
	public static final Object SUB_MENU_DATA_GET = "Get all Sub Menu data";
	public static final Object SUB_MENU_DATA_GET_DATA_GET_ERROR = "Error In Get all Sub Menu data";
	public static final Object SUB_MENU_DATA_SAVE_SUCCESS = "Sub Menu data saved successfully";
	public static final Object SUB_MENU_DATA_SAVE_ERROR = "Error Sub Menu data saved";
	public static final Object SUB_MENU_NAME_NULL_ERROR = "Please fill the sub menu name";
	public static final Object SUB_MENU_NAME_PRESET_ERROR = "Alredy present sub menu";
	public static final Object SUB_MENU_IN_PERENT_ID_ERROR = "Please select the menu name";
	public static final Object SUB_MENU_NAME_NULL_ERROR_CHECK = "Sub menu name is missing";
	public static final Object SUB_MENU_PERENT_NULL_ERROR_CHECK = "Perent menu missing";
	public static final Object SUB_MENU_NOT_FOUND_ERROR = "Sub menu not present";
	public static final Object SUB_MENU_DATA_UPDATE_SUCCESS = "Sub Menu data update successfully";
	public static final Object SUB_MENU_DATA_UPDATE_ERROR = "Error Sub Menu data updation";
	public static final Object SUB_MENU_PRIORITY_UPDATE_SUCCESS = "Sub Menu priority update successfully";
	public static final Object SUB_MENU_PRIORITY_UPDATE_ERROR = "Error Sub Menu priority updation";

	public static final Object PRIVILEGE_DATA_GET = "Get all Privilege data";
	public static final Object PRIVILEGE_DATA_GET_DATA_GET_ERROR = "Error In Get all Privilege data";
	public static final Object PRIVILEGE_DATA_SAVE_SUCCESS = "Privilege data saved successfully";
	public static final Object PRIVILEGE_DATA_SAVE_ERROR = "Error Privilege data saved";

	// Masters Massage Common :: End

	public static final Object BRANCH_SAVE_SUCCESS = "Branch saved successfully";
	public static final Object BRANCH_SAVE_ERROR = "Error in Save Branch";
	public static final Object BRANCH_ACTIVE_SUCCESS = "Branch Active-Deactive successfully";
	public static final Object BRANCH_ACTIVE_ERROR = "Error in Branch Active-Deactive";
	public static final Object BRANCH_UPDATE_MSG = "Branch Update Successfully ";
	public static final Object BRANCH_UPDATE_ERROR_MSG = "Error In Update Branch Data";

	public static final Object ACCOUNTHO_SAVE_SUCCESS = "Account Head saved successfully";
	public static final Object ACCOUNTHO_SAVE_ERROR = "Error in Save Account Head";
	public static final Object ACCOUNTHO_ACTIVE_SUCCESS = "Account Head Active-Deactive successfully";
	public static final Object ACCOUNTHO_ACTIVE_ERROR = "Error in Account Head Active-Deactive";
	public static final Object ACCOUNTHO_UPDATE_MSG = "Account Head Update Successfully ";
	public static final Object ACCOUNTHO_UPDATE_ERROR_MSG = "Error In Update Account Head Data";

	public static final String ACCOUNTHO_GET_MSG = "Account Head Data Get successfully";
	public static final String BRANCH_GET_MSG = "Branch Data Get successfully";

	public static final Object TOKEN_DATA_GET = "Get all Token data";
	public static final Object TOKEN_DATA_GET_DATA_GET_ERROR = "Error In Get all Token data";
	public static final Object TOKEN_DATA_SAVE_SUCCESS = "Token data saved successfully";
	public static final Object TOKEN_DATA_SAVE_ERROR = "Error Token data saved";
	public static final String TOKEN_NAME_NOT_FOUND = "Please enter the model name";
	public static final String TOKEN_NOT_FOUND = "Token not found";
	public static final Object TOKEN_DATA_UPDATE_SUCCESS = "Sub Token data update successfully";
	public static final Object TOKEN_DATA_UPDATE_ERROR = "Error Token data updation";
	public static final Object TOKEN_DATA_ACTIVE_DEACTIVE_SUCCESS = "Token status update successfully";
	public static final Object TOKEN_DATA_ACTIVE_DEACTIVE_ERROR = "Error Token status updation";
	public static final Object TOKEN_NAME_PRESET_ERROR = "Alredy present token";

	public static final Object TOKEN_OPENING_BALANCE = "Get Opening balance is available ";
	public static final Object TTOKEN_OPENING_BALANCE_ERROR = "Get Opening balance not available";
	public static final Object TOKEN_NULL = "Please fill the token";
	public static final Object LOCATION_NULL = "Please fill the location";
	public static final Object STOCK_NULL = "Please fill the stock";
	
	public static final Object TOKEN_ASSIGN_ERROR = "Alredy token is assign";
	public static final String TOKEN_ASSIGN_NOT_FOUND = "Token assign not found";
	public static final Object TOKEN_ASSIGN_DATA_SAVE_SUCCESS = "Token assign data saved successfully";
	public static final Object TOKEN_ASSIGN_DATA_SAVE_ERROR = "Error Token assign data saved";
	public static final Object TOKEN_ASSIGN_DATA_GET = "Get all Token assign data";
	public static final Object TOKEN_ASSIGN_DATA_GET_DATA_GET_ERROR = "Error In Get all Token assign data";
	public static final Object TOKEN_DATA_ASSIGN_UPDATE_SUCCESS = "Token assign data update successfully";
	public static final Object TOKEN_DATA_ASSIGN_UPDATE_ERROR = "Error Token assign data updation";

	public static final Object DSC_KYC_NEW_INVOICE_BUILD_ALREADY_DONE = "Dsc Kyc new invoice build already done";
	public static final Object DSC_KYC_NEW_INVOICE_BUILD_ALREADY_ERROR = "Error Kyc new invoice build already done";

	public static final Object INVOICE_BUILD_ALREADY_DONE = "New invoice build already done";
	public static final Object INVOICE_BUILD_ALREADY_ERROR = "Error new invoice build already done";
	
	public static final Object DSC_KYC_FORWARD_STATUS_ERROR = "Forward status is not updated";
	public static final Object DSC_KYC_FORWARD_STR_STATUS_ERROR = "Forward str status is not updated";

	public static final Object BPCODE_ALREDAY_PRESENT = "Already bp code preset.";
	public static final Object COMMISION_DATA_GET = "Get all Token data";
	public static final Object COMMISION_DATA_GET_DATA_GET_ERROR = "Error In Get all Commission data";
	public static final Object COMMISION_DATA_SAVE_SUCCESS = "Commission data saved successfully";
	public static final Object COMMISION_DATA_SAVE_ERROR = "Error Commission data saved";
	public static final String COMMISION_BPCODE_NOT_FOUND = "Please enter the bp code";
	public static final String COMMISION_PERCENTAGE_NOT_FOUND = "Please enter the Commission percentage";

	public static final String COMMISION_NOT_FOUND = "Commission not found";
	public static final Object COMMISION_DATA_UPDATE_SUCCESS = "Commission data update successfully";
	public static final Object COMMISION_DATA_UPDATE_ERROR = "Error Commission data updation";
	
	public static final Object YEAR_UPDATE_MSG = "Finacial Year Upadet Successfully";
	public static final Object YEAR_ERROR_MSG = "Error in Finacial Year ";
	public static final Object YEAR_SAVE_MSG = "Finacial Year Save Successfully";



	public static final String LOC_DATA_ACTIVE_DEACTIVE_SUCCESS = "Location Active-DeActive Successfully";
	public static final String LOC_DATA_ACTIVE_DEACTIVE_ERROR = "Error In Active-deActive Data";
	public static final String LOC_DATA_UPDATE_SUCCESS = "Tokaen Data Successfyllu Upadted";
	public static final String LOC_DATA_UPDATE_ERROR = "Error In Update Location Data";
	public static final String LOC_DATA_GET = "Location Data get succesfully ";
	public static final String LOC_DATA_GET_DATA_GET_ERROR = "Error in Get Location Data";
	public static final String LOC_DATA_SAVE_SUCCESS = "Location Data svae succesfully";
	public static final String LOC_DATA_SAVE_ERROR = "error in save Location data";
	public static final String LOC_NOT_FOUND = "Location Not Found";
	public static final String LOC_NAME_PRESET_ERROR = "Alredy Location Presented ";
	public static final String LOC_NAME_NOT_FOUND = "Location Name Not Found";
	public static final String LOC_SHORT_NAME_NOT_FOUND = "Short Location Name Not Found";

	public static final String PULL_COMPLETED = "Pull Completed";
	public static final String PULL_COMPLETED_AUCTION = "Pull Completed But Group number not assign";
	public static final String PUSH_COMPLETED = "Push Completed";

	public static final String PULL_COMPLETED_WITH_GROUP_ASSIGN = "Pull Completed with Group number";
	public static final String DATA_NOT_FOUND = "Data Not Found";
	public static final String SOMETHING_WRONG = "Something went worng when pull data";
	
	public static final Object TOKEN_FORWARD_STATUS_ERROR = "Token Forward status is not updated";
	public static final Object TOKEN_FORWARD_STR_STATUS_ERROR = "Token Forward str status is not updated";
	public static final Object TOKEN_FETCH_SAVE_SUCCESS = "Successfully Fetch Token Data";
	public static final Object TOKEN_FETCH_SAVE_ERROR = "Error in Fetch Token Data";
	public static final Object TOKEN_SUMMARY_SAVE_SUCCESS = "Summaries Token Data Successfully";
	public static final Object TOKEN_SUMMARY_SAVE_ERROR = " Error In Summaries Token Data";
	public static final Object TOKEN_FORWARD_SAVE_SUCCESS = "SUccessfully Token Data Forwared";
	public static final Object TOKEN_FORWARD_SAVE_ERROR = "Error in Token Data Forwared";

	public static final String DATA_SAVE_SUCCESS = "Data Save Successfully";
	public static final String DATA_SAVE_ERROR = "Error In Save Data";
	public static final String DATA_GET_SUCCESS = "Getting Data Successfully";
	public static final String DATA_GET_ERROR = "Error In Getting The Data";
	public static final String STATUS_UPDATE_SUCCESS = "Status Update Successfully";
	public static final String STATUS_UPDATE_ERROR = "Error In Updateing Status";
	public static final String DATA_UPDATE_SUCCESS = "Successfully Update Data";
	public static final String DATA_UPDATE_ERROR = "Error In Update Data";

	public static final String SOMETHING_WRONG_ON_SAVE_IN_DB = "Something went worng when data saving in database";

	public static final String PASSWORD_NOT_MATCH = "Password not match.";

	public static final String TOKEN_STOCK = "Please fill the stock";

	public static final String TOKEN_YEAR = "Please fill the year";

	public static final String TOKEN_LOCATION_NAME = "Please fill the location name";

	public static final String TOKEN_NAME_NULL = "Please fill the token name";

	public static final String YEAR_NAME_NULL = "Please fill the year name";

	public static final String YEAR_SHORT_NAME = "Please fill the year short name";

	public static final String START_YEAR = "Please fill the start year";

	public static final String START_DATE = "Please fill the start date";

	public static final String END_DATE = "Please fill the end date";

	public static final String END_YEAR = "Please fill the end year";

	public static final String INVENTORY_TOKEN_NAME_NULL = "Please enter the token name";

	public static final String INVENTORY_TOKEN_SHORT_NAME_NULL = "Please enter the token short name";

	public static final String SOMETHING_ISSUE_OCCUR_IN_GETTING_OPENING_STOCK = "Issue when getting opening stock balance";

	public static final String DSC_KYC_COMMISSION = "DSC KYC COMM";

	public static final String EMPTY_FROMDATE = "Please enter fromdate";

	public static final String EMPTY_TODATE = "Please enter todate";

	public static final String EMPTY_PASSWPRD = "Please enter password";

	public static final String CHECK_FROMDATE = "Please check fromadate";

	public static final String CHECK_TODATE = "Please check toDate";
	
	public static final String REMARK_NOT_FOUND = "Please enter the Remark";
	public static final String ADJUST_TYPE_NOT_FOUND = "Please enter the Adjust Type";
	public static final String ADJUSTDATE_NOT_FOUND = "Please enter the Adjust Date";
	public static final String TOKEN_ID_ERROR_RECODE = "Token id is null";
	public static final String PURCHASE_NOT_FOUND = "Purchase Data Not Found";
	public static final String INVENTORY_DATE_NOT_FOUND = "Inventory Date Not Found";
	public static final String LOCATION_DATA_NOT_FOUND = "Location Data Not Found";
	public static final String PURCHASE_DATE_NOT_FOUND = "Purchase Date Not Found";
	public static final String PURCHASE_NO_NOT_FOUND = "Purchase Number Not Found";
	public static final String TOKEN_DATA_NOT_FOUND = "Token Data Not Found";
	public static final String CHECK_ALREADY_PULL = "Already pull data";


}