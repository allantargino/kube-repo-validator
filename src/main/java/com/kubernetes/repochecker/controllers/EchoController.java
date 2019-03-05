package com.kubernetes.repochecker.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class EchoController {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/**", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
		method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
	public ResponseEntity<Map<String, Object>> echoBack(@RequestBody(required = false) byte[] rawBody) throws IOException {

		Map<String, String> headers = new HashMap<String, String>();
		for (String headerName : Collections.list(request.getHeaderNames())) {
			headers.put(headerName, request.getHeader(headerName));
		}

		Map<String, Object> responseMap = new HashMap<String,Object>();
		responseMap.put("protocol", request.getProtocol());
		responseMap.put("method", request.getMethod());
		responseMap.put("headers", headers);
		responseMap.put("cookies", request.getCookies());
		responseMap.put("parameters", request.getParameterMap());
		responseMap.put("path", request.getServletPath());
		responseMap.put("body", rawBody != null ? Base64.getEncoder().encodeToString(rawBody) : null);
		// LOG.info("REQUEST: " + request.getParameterMap());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
	}	

}