package com.green.nowon.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Role;
import com.green.nowon.domain.repository.MemberEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberEntityRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(">>>>>>>> username :" + email);
		//MemberEntity entity=repository.findByEmail(email).orElseThrow(); 
		
		/* 1번방법
		MemberEntity entity=repository.findByEmail(email).orElseThrow(); //pk처럼 사용하기 위해 메서드 쿼리 생성 (entityrepository에 있음)
		String pass=entity.getPass();
	
		Set<Role>roles=entity.getRoles();
		Set<SimpleGrantedAuthority> authorities=new HashSet<>();
		
		
		for(Role role:roles) {
			
			authorities.add(new  SimpleGrantedAuthority("ROLE_"+role));
		}
		
		return new User(email,pass,authorities);
		*
		*/
		
		//return new MyUserDetails(repository.findByEmail(email).orElseThrow());
		
		return new CustomUserDetails(repository.findByEmail(email).orElseThrow());
		
		 /*2번 방법 
		return new User(email, entity.getPass(), entity.getRoles().stream()
				.map(role->new SimpleGrantedAuthority("ROLE_"+role))
				.collect(Collectors.toSet()));
		*/
}

}