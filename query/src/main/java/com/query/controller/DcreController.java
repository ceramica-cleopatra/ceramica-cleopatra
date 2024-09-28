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

import com.query.dto.CrmkCrmkQueryDefaultDlistDTO;
import com.query.dto.CrmkCrmkQueryDefaultPlistDTO;
import com.query.dto.CrmkDcreQueryDefaultDlistDTO;
import com.query.dto.CrmkDcreQueryDefaultPlistDTO;
import com.query.dto.CrmkDekalaDTO;
import com.query.dto.CrmkItemDetailDTO;
import com.query.dto.CrmkItemGovDTO;
import com.query.dto.CrmkSearchResultDTO;
import com.query.dto.CrmkSizeDTO;
import com.query.dto.CrmkTypeDTO;
import com.query.dto.DcreDekalaDTO;
import com.query.dto.DcreItemGovDTO;
import com.query.dto.DcreSearchResultDTO;
import com.query.dto.DcreSizeDTO;
import com.query.dto.DcreTypeDTO;
import com.query.dto.GroupDTO;
import com.query.dto.PriceDTO;
import com.query.dto.ResponseDTO;
import com.query.dto.StatusDTO;
import com.query.exception.GeneralException;
import com.query.service.CrmkQueryService;
import com.query.service.DcreQueryService;
import com.query.utility.StatusEnum;
import com.query.validation.QueryValidation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DcreController {
	@Autowired
	private DcreQueryService dcreQueryService;

	@Autowired
	private QueryValidation queryValidation;

	@RequestMapping(value = "/getDcreTypes", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getDcreTypes(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<DcreTypeDTO> dcreTypes = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			dcreTypes = dcreQueryService.getDcreTypes(id == null || id.equals("") ? null : Long.parseLong(id),"%"+(name==null?"":name)+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(dcreTypes.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(dcreTypes.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(dcreTypes);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getDcreDekala", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDekala(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<DcreDekalaDTO> dcreDekalas = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			dcreDekalas = dcreQueryService.getDcreDekala(id ==null || id.equals("") ? null : Long.parseLong(id),"%"+name+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(dcreDekalas.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(dcreDekalas.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(dcreDekalas);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/getDcreSizes", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSize(@RequestParam(required=false) String id,@RequestParam(required=false) String name) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<DcreSizeDTO> dcreSizeDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			dcreSizeDTOs = dcreQueryService.getDcreSize(id ==null || id.equals("") ? null : Long.parseLong(id),"%"+name+"%");
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(dcreSizeDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(dcreSizeDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(dcreSizeDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	
    @RequestMapping(value = "/getDcreSearch", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSearchReult(@RequestParam(required=false) String typeId,@RequestParam(required=false) String typeName
    		,@RequestParam(required=false) String sizeId,@RequestParam(required=false) String sizeName,@RequestParam(required=false) String factoryNo,
    		@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String dekalaName,@RequestParam(required=false) String frz,
    		@RequestParam(required=false) String colorId,@RequestParam(required=false) String colorName,@RequestParam(required=false) String tablow,
    		@RequestParam(required=false,defaultValue="0")String page,@RequestParam(required=false,defaultValue="10")String perPage) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<DcreSearchResultDTO> dcreSearchResultDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			
			System.out.println(">>>>>>>>>>>>>>>start getDcreSearchResult<<<<<<<<<<<<");
			System.out.println("typeId>>"+typeId+"typeName>>"+typeName+",sizeId>>"+sizeId+",sizeName>>"+sizeName+",factoryNo>>"+factoryNo
					+",dekalaId>>"+dekalaId+",dekalaName>>"+dekalaName+",frz>>"+frz+",colorId>>"+colorId+",colorName>>"+colorName+",tablow>>"+tablow);
			dcreSearchResultDTOs = dcreQueryService.getDcreSearchResult(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId),
					typeName ==null || typeName.equals("") || typeName.equals("null")? null : typeName,sizeId == null || sizeId.equals("") || sizeId.equals("null") 
					? null : Long.parseLong(sizeId),sizeName ==null || sizeName.equals("") || sizeName.equals("null")? null : sizeName, factoryNo == null || factoryNo.equals("") 
					|| factoryNo.equals("null") ? null : factoryNo	, dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId),dekalaName == null || dekalaName.equals("") || dekalaName.equals("null") ? null : dekalaName, frz == null || 
							frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz),colorId == null || colorId.equals("null") || colorId.equals("") ?
							null : Long.parseLong(colorId),colorName == null || colorName.equals("null") || colorName.equals("") ? null : colorName,tablow == null || 
									tablow.equals("null") || tablow.equals("") ? null : Long.parseLong(tablow),page,perPage);
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(dcreSearchResultDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(dcreSearchResultDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(dcreSearchResultDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
    
    
    
    @RequestMapping(value = "/getDcreDetails", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDetails(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz
    		,@RequestParam(required=false) String colorId,@RequestParam(required=false) String tablow,@RequestParam(required=true) String govId
    		,@RequestParam(required=false) String pList,@RequestParam(required=false) String dList) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<DcreItemGovDTO> dcreItemGovDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			System.out.println("typeId>>>>"+typeId+"sizeId>>>>>"+sizeId+"factoryNo>>>>"+factoryNo+"dekalaId>>>>>"+dekalaId+"frz>>>>>"+frz+"govId>>>>"+govId);
			dcreItemGovDTOs = dcreQueryService.getDcreSearchResultDetails(typeId ==null || typeId.equals("") || typeId.equals("null")? 0 : Long.parseLong(typeId),
					sizeId == null || sizeId.equals("") || sizeId.equals("null") ? 0 : Long.parseLong(sizeId), factoryNo == null || factoryNo.equals("") || 
					factoryNo.equals("null") ? 0 : Long.parseLong(factoryNo), dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? 0 
							: Long.parseLong(dekalaId), frz == null || frz.equals("null") || frz.equals("") ? 0 : Long.parseLong(frz),Long.parseLong(govId)
							,colorId == null || colorId.equals("null") || colorId.equals("") ? 0 : Long.parseLong(colorId),tablow == null || tablow.equals("null")
							|| tablow.equals("") ? 0 : Long.parseLong(tablow),pList == null || pList.equals("") || pList.equals("null") ? 0 : Long.parseLong(pList),
									dList == null || dList.equals("") || dList.equals("null") ? 0 : Long.parseLong(dList));
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(dcreItemGovDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(dcreItemGovDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(dcreItemGovDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/getDcrePlist", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkPlist(){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkDcreQueryDefaultPlistDTO> crmkDcreQueryDefaultPlistDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
    	try{
    		crmkDcreQueryDefaultPlistDTOs = dcreQueryService.getDcreQueryDefaultPlist();	
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkDcreQueryDefaultPlistDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkDcreQueryDefaultPlistDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkDcreQueryDefaultPlistDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getDcrePrice", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getDcrePrice(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz
    		,@RequestParam(required=false) String colorId,@RequestParam(required=false) String tablow
    		,@RequestParam(required=false) String pList,@RequestParam(required=false) String dList){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		ResponseDTO responseDTO = new ResponseDTO();
		PriceDTO priceDTO = null;
		try{
    		priceDTO = dcreQueryService.getDcreItemPrice(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId),
					sizeId == null || sizeId.equals("") || sizeId.equals("null") ? 0 : Long.parseLong(sizeId), factoryNo == null || factoryNo.equals("") || 
					factoryNo.equals("null") ? null : factoryNo, dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId), frz == null || frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz),
									colorId == null || colorId.equals("null") || colorId.equals("") ? null : Long.parseLong(colorId),tablow == null || tablow.equals("null")
									|| tablow.equals("") ? null : Long.parseLong(tablow)
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
    
    
    @RequestMapping(value = "/getDcreDlist", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkDlist(){
    	Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<CrmkDcreQueryDefaultDlistDTO> crmkDcreQueryDefaultDlistDTOs = null;
		ResponseDTO responseDTO = new ResponseDTO();
    	try{
    		crmkDcreQueryDefaultDlistDTOs = dcreQueryService.getDcreQueryDefaultDlist();	
		}catch (Exception e) {
			e.printStackTrace();
			status.setCode(StatusEnum.Exception.getStatusCode());
			status.setMessage(StatusEnum.Exception.getStatusMessage());
			status.setResponseTime(System.currentTimeMillis()-startTime);
			throw new GeneralException(status);
		}
		if(crmkDcreQueryDefaultDlistDTOs.size()>0){
			status.setCode(StatusEnum.Ok.getStatusCode());
			status.setMessage(StatusEnum.Ok.getStatusMessage());
		}else{
			status.setCode(StatusEnum.NoResultFound.getStatusCode());
			status.setMessage(StatusEnum.NoResultFound.getStatusMessage());
		}
		status.setResponseTime(System.currentTimeMillis()-startTime);
		status.setResultCount(Long.parseLong(crmkDcreQueryDefaultDlistDTOs.size()+""));
		responseDTO.setStatus(status);
		responseDTO.setPayload(crmkDcreQueryDefaultDlistDTOs);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
    
    @RequestMapping(value = "/getDcreGroups", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> getCrmkSearchReult(@RequestParam(required=false) String typeId,@RequestParam(required=false) String sizeId,
    		@RequestParam(required=false) String factoryNo,@RequestParam(required=false) String dekalaId,@RequestParam(required=false) String frz,
    		@RequestParam(required=false) String tablow,@RequestParam(required=false) String colorId) {
		Long startTime = System.currentTimeMillis();
		StatusDTO status = new StatusDTO();
		List<GroupDTO> groupList = null;
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			groupList = dcreQueryService.getDcreGroups(typeId ==null || typeId.equals("") || typeId.equals("null")? null : Long.parseLong(typeId)
					,sizeId == null || sizeId.equals("") || sizeId.equals("null") ? null : Long.parseLong(sizeId), dekalaId == null || dekalaId.equals("") || dekalaId.equals("null") ? null 
							: Long.parseLong(dekalaId), factoryNo == null || factoryNo.equals("") || factoryNo.equals("null") ? null : factoryNo, 
									frz == null || frz.equals("null") || frz.equals("") ? null : Long.parseLong(frz),tablow == null || tablow.equals("null") || tablow.equals("") ? null : tablow,
											colorId == null || colorId.equals("null") || colorId.equals("") ? null : colorId);
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
	
}
