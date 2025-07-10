package com.ncode.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncode.invoice.repository.InvoiceRepository;

@Service
public interface EsignService 
{

	public List<String> getAllFilePaths() ;
	public Boolean isSignCompetedMark(String invocieNumber)throws Exception;
	
	public String generatePdf();

}
