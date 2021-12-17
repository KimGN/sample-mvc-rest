package com.breezelab.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.breezelab.sample.common.ResultMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl  implements SampleService {

	public ResponseEntity<ResultMessage> SampleServiceRestCall() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResultMessage resultMessage = new ResultMessage();
		
		ResponseEntity<ResultMessage> responseEntity = null;
		
		ResponseEntity<String> responseEntity_rest = restTemplate.getForEntity("https://www.pegaxchange.com/ProductCatalogService.php?operation=search&category=electronics&priceFrom=10&priceTo=30", String.class);
		
		int status_code = responseEntity_rest.getStatusCodeValue() / 100;
		if (status_code == 2) {
			resultMessage.setSuccessYn("Y");
			resultMessage.setBizcode("000");
			resultMessage.setData(responseEntity_rest.getBody());
			resultMessage.setMessage(responseEntity_rest.getBody());
			responseEntity = ResponseEntity.ok(resultMessage);
		} else {
			resultMessage.setSuccessYn("N");
			resultMessage.setBizcode("000");
			resultMessage.setData(responseEntity_rest.getBody());
			resultMessage.setMessage(responseEntity_rest.getBody());
			responseEntity = ResponseEntity.ok(resultMessage);
		}
		
		
		System.out.println("===========responseEntity>"+responseEntity);
		
		return responseEntity;
	}
}