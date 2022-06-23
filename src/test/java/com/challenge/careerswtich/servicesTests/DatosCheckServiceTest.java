package com.challenge.careerswtich.servicesTests;

import java.util.ArrayList;
import java.util.List;

import com.challenge.careerswtich.DTOs.ResponseBloksDTO;
import com.challenge.careerswtich.DTOs.ResponseCheckDTO;
import com.challenge.careerswtich.DTOs.ResponseStatsDTO;
import com.challenge.careerswtich.utils.Textos;

public class DatosCheckServiceTest {

	public static final ResponseBloksDTO getBlocks() {
		
		List<String> blocks = new ArrayList<>();
		blocks.add("asd");
		blocks.add("dfg");
		blocks.add("ghj");
		blocks.add("yui");
		blocks.add("wer");
		
		ResponseBloksDTO response = new ResponseBloksDTO();
		response.setChunkSize(10);
		response.setLength(10);
		response.setData(blocks);
		return response;
	}
	
	public static final ResponseStatsDTO getResponse() {
		ResponseStatsDTO response = new ResponseStatsDTO();
		response.setError(Textos.SUCCESS_LABEL + " : true");
		return response;
	}
	
	public static final ResponseCheckDTO getValidation() {
		ResponseCheckDTO response = new ResponseCheckDTO();
		response.setMessage(true);
		return response;
	}
	
	
}
