package com.green.nowon.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.repository.MemberEntityRepository;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {

	
	private final MemberEntityRepository repository;
	private final PasswordEncoder pe;
	
	//회원가입-롤에 따라 롤이 가변적으로 적용되어야함.
	@Override
	public void signupProcess(MemberSaveDTO dto) {
		
		repository.save(dto.toEntity(pe));
		
	}
	

}
