package com.query.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.query.dto.CrmkColorDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.ResponseDTO;
import com.query.dto.SehyDekalaDTO;
import com.query.dto.SehyItemGovDTO;
import com.query.dto.SehyNameDTO;
import com.query.dto.SehySearchResultDTO;
import com.query.dto.SehyTypeDTO;
import com.query.dto.StatusDTO;
import com.query.exception.GeneralException;
import com.query.service.CrmkQueryService;
import com.query.service.SehyQueryService;
import com.query.utility.StatusEnum;
import com.query.validation.QueryValidation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SehyController {
	@Autowired
	private SehyQueryService sehyQueryService;

	@Autowired
	private QueryValidation queryValidation;

	@RequestMapping(value = "/getSehyTypes", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getSehyTypes(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<SehyTypeDTO> sehyTypes = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			sehyTypes = sehyQueryService.getSehyTypes(id == null || id.equals("") ? null : Long.parseLong(id),"%"+(name==null?"":name)+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(sehyTypes.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(sehyTypes.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(sehyTypes);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getSehyNames", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getSehyNames(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<SehyNameDTO> sehyNames = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			sehyNames = sehyQueryService.getSehyNames(id == null || id.equals("") ? null : Long.parseLong(id),"%"+(name==null?"":name)+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(sehyNames.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(sehyNames.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(sehyNames);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getSehyDekalas", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getSehyDekalas(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<SehyDekalaDTO> sehyDekalas = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			sehyDekalas = sehyQueryService.getSehyDekala(id == null || id.equals("") ? null : Long.parseLong(id),"%"+(name==null?"":name)+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(sehyDekalas.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(sehyDekalas.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(sehyDekalas);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
    @RequestMapping(value = "/getSehySearch", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSearchReult(@RequestParam(required=false) String typeId,@RequestParam(required=false) String typeName
    		,@RequestParam(required=false) String nameId,@RequestParam(required=false) String name,
    		@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String dekalaName,
    		@RequestParam(required=false) String colorId,@RequestParam(required=false) String colorName,
    		@RequestParam(required=false) String frz,
    		@RequestParam(required=false,defaultValue="0")String page,@RequestParam(required=false,defaultValue="10")String perPage) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<SehySearchResultDTO> searchResultDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			searchResultDTOs = sehyQueryService.getSehySearchResult(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId),
					typeName ==null || typeName.equals("") || typeName.equals("null")? null : typeName,nameId == null || nameId.equals("") || nameId.equals("null") 
					? null : Long.parseLong(nameId),name ==null || name.equals("") || name.equals("null")? null : name, 
					dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null : Long.parseLong(dekalaId),
					dekalaName == null || dekalaName.equals("") || dekalaName.equals("null") ? null : dekalaName,
					colorId == null || colorId.equals("") || colorId.equals("null") ? null : Long.parseLong(colorId),
					colorName == null || colorName.equals("") || colorName.equals("null") ? null : colorName,
					frz == null || frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz),page,perPage);
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(searchResultDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(searchResultDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(searchResultDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    
    
    
    @RequestMapping(value = "/getSehyTakmDetails", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDetails(@RequestParam(required=true) String typeId,@RequestParam(required=false) String nameId,@RequestParam(required=false) String dekalaId
    		,@RequestParam(required=false) String colorId,@RequestParam(required=false) String frz) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		SehyItemGovDTO sehyItemGovDTO = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			if(typeId!= null && Long.parseLong(typeId) >= 1 && Long.parseLong(typeId) <= 40){
			sehyItemGovDTO = sehyQueryService.getSehyTakmSearchDetails(typeId == null || typeId.equals("") || typeId.equals("0") || typeId.equals("null") ? null : Long.parseLong(typeId),
					nameId == null || nameId.equals("") || nameId.equals("0") || nameId.equals("null") ? null : Long.parseLong(nameId),dekalaId == null || dekalaId.equals("") || dekalaId.equals("0")
					|| dekalaId.equals("null") ? null : Long.parseLong(dekalaId), colorId ==null || colorId.equals("") || colorId.equals("0") || colorId.equals("null") ? null 
					: Long.parseLong(colorId),frz == null || frz.equals("") || frz.equals("0") || frz.equals("null") ? null : Long.parseLong(frz));
			}else if(typeId!= null && Long.parseLong(typeId) > 40){
			sehyItemGovDTO = sehyQueryService.getSehySingleSearchDetails(typeId == null || typeId.equals("") || typeId.equals("0") || typeId.equals("null") ? null : Long.parseLong(typeId),
					nameId == null || nameId.equals("") || nameId.equals("0") || nameId.equals("null") ? null : Long.parseLong(nameId),dekalaId == null || dekalaId.equals("") || dekalaId.equals("0")
					|| dekalaId.equals("null") ? null : Long.parseLong(dekalaId), colorId ==null || colorId.equals("") || colorId.equals("0") || colorId.equals("null") ? null 
					: Long.parseLong(colorId),frz == null || frz.equals("") || frz.equals("0") || frz.equals("null") ? null : Long.parseLong(frz));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(sehyItemGovDTO.getDistinctItems().size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(sehyItemGovDTO.getDistinctItems().size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(sehyItemGovDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
}
