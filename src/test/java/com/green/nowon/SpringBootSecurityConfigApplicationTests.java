package com.green.nowon;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Role;
import com.green.nowon.domain.repository.MemberEntityRepository;

@SpringBootTest
class SpringBootSecurityConfigApplicationTests {

	@Autowired
	MemberEntityRepository mRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//@Test
	void 관리자사원등록() {
		MemberEntity entity=MemberEntity.builder()
				.email("test01@test.com")
				.pass(passwordEncoder.encode("1234"))
				.name("어드민01")
				.build()//Set<Role> roles=new HashSet<>()
				.addRole(Role.EMP)
				.addRole(Role.MGR)
				.addRole(Role.ADMIN)
				;// Set<Role> roles
		
		mRepository.save(entity);
		
	}
	
	//@Test
	void 매니저사원등록() {
		MemberEntity entity=MemberEntity.builder()
				.email("test02@test.com")
				.pass(passwordEncoder.encode("1234"))
				.name("테스트02")
				.build()//Set<Role> roles=new HashSet<>()
				.addRole(Role.EMP)
				.addRole(Role.MGR)
				;// Set<Role> roles
		
		mRepository.save(entity);
		
	}
	

	//@Test
	void 일반사원등록() {
		MemberEntity entity=MemberEntity.builder()
				.email("test03@test.com")
				.pass(passwordEncoder.encode("1234"))
				.name("테스트03")
				.build()//Set<Role> roles=new HashSet<>()
				.addRole(Role.EMP)
				;// Set<Role> roles
		
		mRepository.save(entity);
		
	}

}
