package com.ncode.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ncode.invoice.entity.TblInvoiceSummary;
import com.ncode.invoice.entity.TblMstGstParam;

import jakarta.transaction.Transactional;

public interface InvoiceRepository  extends JpaRepository<TblInvoiceSummary, Integer>  
{
	@Query(value = "SELECT CAST(ROW_TO_JSON(a) as text) as details FROM (select * from tblfilepaths where status = '0' ) as a ", nativeQuery = true)
	List<String> getAllFilePaths();


	@Transactional
	@Modifying
	@Query(value = "update tblinvoicesummary set isesigncompleted = 'Y' where invoiceno = :invoiceNumber and ispdfgenerated = 'Y' ",nativeQuery = true)
	void isSignCompetedMarkInvoice(String invoiceNumber);

	
	@Transactional
	@Modifying
	@Query(value = "update tbldscsupportinvoice set isesign1completed = 'Y',modifieddate = now(),modifiedby = :id where invno1_temp  = :invoiceNumber and pdfgenerated1  = 'Y'",nativeQuery = true)
	void isSignCompetedMarkConvffDSC(String invoiceNumber);
	
	@Transactional
	@Modifying
	@Query(value = "update tbldscsupportinvoice set isesigncompleted  = 'Y',modifieddate = now(),modifiedby = :id where invno_temp  = :invoiceNumber and pdfgenerated = 'Y'",nativeQuery = true)
	void isSignCompetedMarkAmountDSC(String invoiceNumber);
	
	
	@Transactional
	@Modifying
	@Query(value = "update tbldscsupportinvoice set isesigncompleted  = 'Y',modifieddate = now(),modifiedby = :id where invno_temp  = :invoiceNumber and pdfgenerated = 'Y'",nativeQuery = true)
	void isSignCompetedMarkNewConvffDSC(String invoiceNumber);
	
	@Transactional
	@Modifying
	@Query(value = "update tbldscsupportinvoice set isesigncompleted  = 'Y',modifieddate = now(),modifiedby = :id where invno_temp  = :invoiceNumber and pdfgenerated = 'Y'",nativeQuery = true)
	void isSignCompetedMarkNewAmountDSC(String invoiceNumber);
	
	@Query(value = "SELECT CAST(ROW_TO_JSON(a) as text) as details FROM (select * from tblinvoicesummary where ispdfgenerated = 'N') as a ",nativeQuery = true)
	List<String> summaryList();
	
	@Query(value = "SELECT CAST(ROW_TO_JSON(a) as text) as details FROM (select * from tblmstgstparam  where status = '1' and username = 'GNFCBHARUCH') as a ", nativeQuery = true)
	List<String> getGstDetailsCompanyDetails();

	

}
