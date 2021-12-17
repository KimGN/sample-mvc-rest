package com.breezelab.sample.service;

import org.springframework.http.ResponseEntity;

import com.breezelab.sample.common.ResultMessage;

public interface SampleService {

	public ResponseEntity<ResultMessage> SampleServiceRestCall();

}
