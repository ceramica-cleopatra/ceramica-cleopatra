package com.query.generation.qr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.query.generation.qr.service.QRGenerationService;

@RestController
public class QRController {
	@Autowired
	QRGenerationService qrGenerationService;
	
	@GetMapping("/generateQR")
	public String generateQR(@RequestParam(required=false) Long typeId,@RequestParam(required=false) Long sizeId,@RequestParam(required=false) Long dekalaId
			,@RequestParam(required=false) Long factoryNo,@RequestParam(required=false) Long colorId,@RequestParam(required=false) Long tablow,@RequestParam(required=false) Long nameId
			, @RequestParam(required=false) String crmkSehy,@RequestParam(required=false) String showType,@RequestParam(required=false) Long grpId,@RequestParam(required=false) Long floor,
			@RequestParam(required=false) Long brnId,@RequestParam(required=false) String standNo, @RequestParam(defaultValue="200") Integer height,@RequestParam(defaultValue="200") Integer width,
			@RequestParam(defaultValue="235") Integer imageHeight,@RequestParam(defaultValue="235") Integer imageWidth,@RequestParam(defaultValue="13") Integer productFontSize,
			@RequestParam(defaultValue="13") Integer priceFontSize,@RequestParam(defaultValue="25") Integer productFarFromTop,@RequestParam(defaultValue="15") Integer priceFarFromBottom,
			@RequestParam(required=false) Long how2show,@RequestParam(required=false) Long sehyGrpId) {
		
			return qrGenerationService.generateQRForProducts(typeId, sizeId, dekalaId, factoryNo,colorId,tablow,nameId,crmkSehy,showType,grpId,floor,brnId,standNo,height,width,
				imageHeight,imageWidth,productFontSize,priceFontSize,productFarFromTop,priceFarFromBottom,how2show,sehyGrpId);
	}
}
