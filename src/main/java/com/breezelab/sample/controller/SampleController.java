package com.breezelab.sample.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.breezelab.sample.common.ResultMessage;
import com.breezelab.sample.model.SampleModel;
import com.breezelab.sample.service.SampleService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @Desc     : sample Controller
 *
 */
@Controller
public class SampleController {

	@Autowired
	SampleService sampleService;
	
	SampleModel sampleModel = new SampleModel();
	
	ResponseEntity<ResultMessage> responseEntity_result;
	
	@RequestMapping(value= "/html_mvc", method=RequestMethod.GET)
	public ModelAndView html_mvc() {
		
		ModelAndView model = new ModelAndView();
		
		sampleModel.setSampleNo("12345");
		sampleModel.setSampleSeqNo(67890);
		sampleModel.setSampleYn("Y");
		sampleModel.setRgstDttm(LocalDateTime.now());
		sampleModel.setSampleDescription("return html_mvc result");
		
		model.addObject("html_mvc_sample_model", sampleModel);
		
		model.setViewName("mvc");
		
		return model;
	}
	
	@RequestMapping(value= "/html_mvc_rest", method=RequestMethod.GET)
	public ModelAndView html_mvc_rest() {
		
		ModelAndView model = new ModelAndView();
		
		sampleModel.setSampleNo("12345");
		
		responseEntity_result = sampleService.SampleServiceRestCall();

		model.addObject("responseEntity_result", responseEntity_result);
		
		model.setViewName("mvc_rest");
				
		return model;
	}
	
	@RequestMapping(value= "/ajax_mvc_json", method=RequestMethod.GET)
	public ResponseEntity<ResultMessage> ajax_mvc_json() {
		
		ResultMessage result = null;
		
		Map<String, Object> map = new HashMap<String, Object>();

		sampleModel.setSampleNo("12345");
		sampleModel.setSampleSeqNo(67890);
		sampleModel.setSampleYn("Y");
		sampleModel.setRgstDttm(LocalDateTime.now());
		sampleModel.setSampleDescription("return ajax_mvc_json result");
		
		map.put("ajax_mvc_json_sample_model1", sampleModel);
		
		sampleModel.setSampleNo("67890");
		sampleModel.setSampleSeqNo(12345);
		sampleModel.setSampleYn("N");
		sampleModel.setRgstDttm(LocalDateTime.now());
		sampleModel.setSampleDescription("return ajax_mvc_json result");
		
		map.put("ajax_mvc_json_sample_model2", sampleModel);
		
		result = new ResultMessage("Y", "biz_code", map, "성공메세지");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value= "/ajax_mvc_string", method=RequestMethod.GET)
	public String ajax_mvc_string() {
		
		ResultMessage result = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<String, Object>();

		sampleModel.setSampleNo("12345");
		sampleModel.setSampleSeqNo(67890);
		sampleModel.setSampleYn("Y");
		sampleModel.setRgstDttm(LocalDateTime.now());
		sampleModel.setSampleDescription("return ajax_mvc_json result");
		
		map.put("ajax_mvc_json_sample_model1", sampleModel);
		
		sampleModel.setSampleNo("67890");
		sampleModel.setSampleSeqNo(12345);
		sampleModel.setSampleYn("N");
		sampleModel.setRgstDttm(LocalDateTime.now());
		sampleModel.setSampleDescription("return ajax_mvc_json result");
		
		map.put("ajax_mvc_json_sample_model2", sampleModel);
		
		result = new ResultMessage("Y", "biz_code", map, "성공메세지");
		
		return result.toString();
	}
	
}