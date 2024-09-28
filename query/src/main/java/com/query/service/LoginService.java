package com.query.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.query.dao.HrLoginRepository;
import com.query.model.HrQueryAppLog;

@Service
public class LoginService {
	@Autowired
	private HrLoginRepository hrLoginRepository;

	public Integer validateUser(String usercode, String password) {
		Integer val= hrLoginRepository.validateUser(usercode, password);
		if(val > 0){
			HrQueryAppLog hrQueryAppLog = new HrQueryAppLog();
			hrQueryAppLog.setEmpNo(Long.parseLong(usercode));
			hrQueryAppLog.setTrnsDate(new Date());
			hrLoginRepository.save(hrQueryAppLog);
		}
		return val;
	}
}
