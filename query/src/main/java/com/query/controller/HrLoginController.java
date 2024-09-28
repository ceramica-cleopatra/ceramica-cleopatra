package com.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.query.service.LoginService;

@Controller
public class HrLoginController {
	@Autowired
	private LoginService loginService;
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET)
	public ResponseEntity<Integer> validateUser(String usercode,String password){
		return ResponseEntity.ok(loginService.validateUser(usercode, password));
	}
}
