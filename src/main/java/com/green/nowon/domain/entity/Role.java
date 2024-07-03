package com.green.nowon.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Getter
@RequiredArgsConstructor
public enum Role{
	
	EMP("사원"),
	MGR("매니저"),
	ADMIN("관리자")
	;

	private  final String roleName;
	
	
	public final String roleName() { //@getter 대신
		return roleName;
	}
	
	
}
