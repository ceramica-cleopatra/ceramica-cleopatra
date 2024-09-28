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
import com.query.dto.CrmkCrmkQueryDefaultDlistDTO;
import com.query.dto.CrmkCrmkQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.GroupDetailsDTO;
import com.query.dto.PriceDTO;
import com.query.dto.ResponseDTO;
import com.query.dto.StatusDTO;
import com.query.exception.GeneralException;
import com.query.service.CrmkQueryService;
import com.query.utility.StatusEnum;
import com.query.validation.QueryValidation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CrmkController {
	@Autowired
	private CrmkQueryService crmkQueryService;

	@Autowired
	private QueryValidation queryValidation;

	@RequestMapping(value = "/getCrmkTypes", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkTypes(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkTypeDTO> crmkTypes = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			crmkTypes = crmkQueryService.getCrmkTypes(id == null || id.equals("") ? null : Long.parseLong(id),"%"+(name==null?"":name)+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkTypes.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkTypes.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkTypes);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getCrmkDekala", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDekala(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkDekalaDTO> crmkDekalas = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			crmkDekalas = crmkQueryService.getCrmkDekala(id ==null || id.equals("") ? null : Long.parseLong(id),"%"+name+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkDekalas.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkDekalas.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkDekalas);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getCrmkColor", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkColor(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkColorDTO> crmkColors = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			crmkColors = crmkQueryService.getCrmkColor(id ==null || id.equals("") ? null : Long.parseLong(id),"%"+name+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkColors.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkColors.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkColors);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getCrmkSizes", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSize(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkSizeDTO> crmkSizeDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			crmkSizeDTOs = crmkQueryService.getCrmkSize(id ==null || id.equals("") ? null : Long.parseLong(id),"%"+name+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkSizeDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkSizeDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkSizeDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
    @RequestMapping(value = "/getCrmkSearch", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSearchReult(@RequestParam(required=false) String typeId,@RequestParam(required=false) String typeName
    		,@RequestParam(required=false) String sizeId,@RequestParam(required=false) String sizeName,@RequestParam(required=false) String factoryNo,
    		@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String dekalaName,@RequestParam(required=false) String frz,
    		@RequestParam(required=false,defaultValue="0")String page,@RequestParam(required=false,defaultValue="10")String perPage) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkSearchResultDTO> crmkSearchResultDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			System.out.println(">>>>>>>>>>>>>>>>>>>>start getCrmkSearch<<<<<<<<<<<<<<<<<<<<");
			crmkSearchResultDTOs = crmkQueryService.getCrmkSearchResult(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId),
					typeName ==null || typeName.equals("") || typeName.equals("null")? null : typeName,sizeId == null || sizeId.equals("") || sizeId.equals("null") 
					? null : Long.parseLong(sizeId),sizeName ==null || sizeName.equals("") || sizeName.equals("null")? null : sizeName, factoryNo == null || factoryNo.equals("") 
					|| factoryNo.equals("null") ? null : factoryNo	, dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId),dekalaName == null || dekalaName.equals("") || dekalaName.equals("null") ? null : dekalaName, frz == null || 
							frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz),page,perPage);
			System.out.println("<<<<<<<<<<<<<<<<<<<<end getCrmkSearch>>>>>>>>>>>>>>>>>>>>>>>"+crmkSearchResultDTOs.size());
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkSearchResultDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkSearchResultDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkSearchResultDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
    
    
    
    @RequestMapping(value = "/getCrmkDetails", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDetails(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz
    		,@RequestParam(required=true) String govId,@RequestParam(required=false) String pList,@RequestParam(required=false) String dList) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkItemGovDTO> crmkItemGovDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			System.out.println("typeId>>>>"+typeId+"sizeId>>>>>"+sizeId+"factoryNo>>>>"+factoryNo+"dekalaId>>>>>"+dekalaId+"frz>>>>>"+frz+"govId>>>>"+govId);
			crmkItemGovDTOs = crmkQueryService.getCrmkSearchResultDetails(typeId ==null || typeId.equals("") || typeId.equals("null")? 0 : Long.parseLong(typeId),
					sizeId == null || sizeId.equals("") || sizeId.equals("null") ? 0 : Long.parseLong(sizeId), factoryNo == null || factoryNo.equals("") || 
					factoryNo.equals("null") ? 0 : Long.parseLong(factoryNo), dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? 0 
							: Long.parseLong(dekalaId), frz == null || frz.equals("null") || frz.equals("") ? 0 : Long.parseLong(frz),Long.parseLong(govId),
									pList == null || pList.equals("") || pList.equals("null") ? 0 : Long.parseLong(pList),
											dList == null || dList.equals("") || dList.equals("null") ? 0 : Long.parseLong(dList));
			
			
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkItemGovDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkItemGovDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkItemGovDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getCrmkPlist", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkPlist(){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkCrmkQueryDefaultPlistDTO> crmkCrmkQueryDefaultPlistDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
    	try{
    		crmkCrmkQueryDefaultPlistDTOs = crmkQueryService.getCrmkQueryDefaultPlist();	
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkCrmkQueryDefaultPlistDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkCrmkQueryDefaultPlistDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkCrmkQueryDefaultPlistDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getCrmkPrice", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkPrice(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz,@RequestParam(required=false) String pList,@RequestParam(required=false) String dList){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		ResponseDTO responseDTO = new ResponseDTO();
		PriceDTO priceDTO = null;
		try{
    		priceDTO = crmkQueryService.getCrmkItemPrice(typeId ==null || typeId.equals("") || typeId.equals("null")? 0 : Long.parseLong(typeId),
					sizeId == null || sizeId.equals("") || sizeId.equals("null") ? 0 : Long.parseLong(sizeId), factoryNo == null || factoryNo.equals("") || 
					factoryNo.equals("null") ? null : factoryNo, dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId), frz == null || frz.equals("null") || frz.equals("") ? 0 : Long.parseLong(frz)
									, pList == null || pList.equals("null") || pList.equals("") ? 0L : Long.parseLong(pList)
    				, dList == null || dList.equals("null") || dList.equals("") ? 0L : Long.parseLong(dList));
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		
		if(priceDTO!=null && priceDTO.getPrice()!=null){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(1L);
		responseDTO.setStatus(status);
		responseDTO.setPayload(priceDTO);
    	return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/getCrmkDlist", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDlist(){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkCrmkQueryDefaultDlistDTO> crmkCrmkQueryDefaultDlistDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
    	try{
    		crmkCrmkQueryDefaultDlistDTOs = crmkQueryService.getCrmkQueryDefaultDlist();	
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkCrmkQueryDefaultDlistDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkCrmkQueryDefaultDlistDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkCrmkQueryDefaultDlistDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/getCrmkGroups", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSearchReult(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<GroupDTO> groupList = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			groupList = crmkQueryService.getCrmkGroups(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId)
					,sizeId == null || sizeId.equals("") || sizeId.equals("null") ? null : Long.parseLong(sizeId), dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId), factoryNo == null || factoryNo.equals("") || factoryNo.equals("null") ? null : factoryNo, 
									frz == null || frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz));
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(groupList.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(groupList.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(groupList);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/getGroupDetails", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getGroupDetails(@RequestParam(required=true) String bathId,@RequestParam(required=true) String govId) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<GroupDetailsDTO> groupDetailsDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			groupDetailsDTOs = crmkQueryService.getGroupDetails(bathId ==null || bathId.equals("") || bathId.equals("null")? null : Long.parseLong(bathId)
					,govId == null || govId.equals("") || govId.equals("null") ? null : Long.parseLong(govId));
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(groupDetailsDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(groupDetailsDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(groupDetailsDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
}
