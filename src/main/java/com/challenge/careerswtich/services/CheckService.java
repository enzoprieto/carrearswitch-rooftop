package com.challenge.careerswtich.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.challenge.careerswtich.DTOs.RequestCheckDTO;
import com.challenge.careerswtich.DTOs.ResponseBloksDTO;
import com.challenge.careerswtich.DTOs.ResponseCheckDTO;
import com.challenge.careerswtich.DTOs.ResponseStatsDTO;
import com.challenge.careerswtich.utils.ClienteWebService;
import com.challenge.careerswtich.utils.DatesUtil;
import com.challenge.careerswtich.utils.Textos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service
public class CheckService {

    @Value("${host_careerswitch}")
    private String host;

    @Value("${token_careerswitch}")
    private String token;

    protected Log log = LogFactory.getLog(getClass());

    public ResponseStatsDTO check(){
        
        ResponseStatsDTO stats = new ResponseStatsDTO();
        stats.setStart(DatesUtil.dateTimeToString(new Date()));
        ResponseBloksDTO blocksDto = getBlocks();
        if(blocksDto == null || (blocksDto.getData() == null || blocksDto.getData().isEmpty())){
            stats.setError(Textos.ERROR_BLOCKS);
            return stats;
        }
        
        List<String> blocks = blocksDto.getData();
        List<String> ordered = new ArrayList();
        String last = "";
        
        do{
            stats = findCombination(blocks, ordered, stats, last);
            
            if(!ordered.isEmpty()) last = ordered.get(ordered.size()-1);
            
        }while(blocks.size() > 1);

        if(blocks.size() == 1) ordered.add(blocks.get(blocks.size()-1));
        validResponseOfValidator(stats, ordered);        
        
        return stats;
    }
    
    private ResponseStatsDTO validResponseOfValidator(ResponseStatsDTO stats, List<String> ordered) {

		String charFinal = "";
		for(String item : ordered) {
			charFinal = charFinal.concat(item); 
		}
		
		RequestCheckDTO requestCheckDTO = new RequestCheckDTO(null, charFinal);
		ResponseCheckDTO valid = validator(requestCheckDTO);

		if (valid == null) {
			stats.setError(Textos.ERROR_LABEL + " : " + Textos.ERROR_CALL_VALIDATE);
		} else {
			stats.setError(Textos.SUCCESS_LABEL + " : " + valid.getMessage());
			stats.setResult(charFinal);
			stats.setEnd(DatesUtil.dateTimeToString(new Date()));
		}
		return stats;
	}

    private ResponseStatsDTO findCombination(List<String> blocks, List<String> peers, ResponseStatsDTO stats, String last){

        List<String> toValidate = new ArrayList();
        if(peers.isEmpty()){
            toValidate.add(blocks.get(0));
            peers.add(blocks.get(0));
            blocks.remove(0);
        }else {
        	toValidate.add(last);
        }
        
        for(String block  : blocks){

    		toValidate.add(block);
            RequestCheckDTO resquestCheckDto = new RequestCheckDTO(toValidate, null);
            ResponseCheckDTO valid = validator(resquestCheckDto);
            
            stats.setRequests(stats.getRequests() + 1);
            
            if(valid == null || valid.getMessage() == null){
                stats.setError(Textos.ERROR_LABEL + " : " + Textos.ERROR_CALL_VALIDATE);
                return stats;
            }else if(!valid.getMessage()){
                toValidate.remove(block);
            }else{
            	blocks.removeAll(toValidate);
            	peers.add(block);
                break;
            }		
        }
        return stats;
    }

    public ResponseBloksDTO getBlocks(){
        ResponseBloksDTO response = null;
        ClienteWebService cliente = new ClienteWebService(Textos.BLOCKS);
        try{
            response = cliente.getAndReceiveObjectTokenPathVariable(ResponseBloksDTO.class, host, token);
            return   response;
        }catch(IOException ex){
            return response;
        }
    }

    public ResponseCheckDTO validator(RequestCheckDTO requestCheckDTO){
        ResponseCheckDTO response = null;
        ClienteWebService cliente = new ClienteWebService(Textos.CHECK);
        try{
            response = cliente.postObjectInJsonAndReceiveObjectTokenPathVariable(requestCheckDTO, host, ResponseCheckDTO.class, token);
            return   response;
        }catch(IOException ex){
            return response;
        }
    }
    
}



