package com.query.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.query.model.CrmkColor;
import com.query.model.HrQueryAppLog;

public interface HrLoginRepository extends CrudRepository<HrQueryAppLog, Integer> {
	@Query(nativeQuery=true,value="select hr.validate_user(?,?) from dual" )
	public Integer validateUser(@Param("usercode") String usercode,@Param("password") String password);

}
