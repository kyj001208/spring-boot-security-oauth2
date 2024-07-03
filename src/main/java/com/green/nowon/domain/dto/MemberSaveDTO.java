package com.green.nowon.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Role;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class MemberSaveDTO {
	
	private String email;
	private String pass;
	private String name;
	private String role;

	
	public MemberEntity toEntity(PasswordEncoder pe) {

		return MemberEntity.builder()
				.email(email)
				.pass(pe.encode(pass))
				.name(name)
				.build()//roles 콜렉션 객체 생성됌 
				.addRoleByRange(role);
	}

}
