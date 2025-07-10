package com.ncode.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ncode.invoice.esign.Response;
import com.ncode.invoice.service.EsignService;

import jakarta.servlet.http.HttpServletRequest;

public class EsignController 
{
	@Autowired
	EsignService esignService;
	
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllFilePath(HttpServletRequest request) throws Exception 
	{
		Response response = new Response();
		try {
			List<String> flag = esignService.getAllFilePaths();
			if (!flag.isEmpty()) {
				response.setStatus("SUCCESS");
				response.setErrorMessage("Get File Path Successfully");
				response.setResponseBody(flag);
			} else {
				response.setStatus("ERROR");
				response.setErrorMessage("Error in Get File Path ");
				response.setResponseBody(flag);
			}
		} catch (Exception e) {
			response.setStatus("ERROR");
			response.setErrorMessage("Error in Getting is getAllFilePath...!!!");
			response.setResponseBody("Not Avaliable Data");
		}
		return ResponseEntity.status(org.springframework.http.HttpStatus.OK).body(response);
	}

}
