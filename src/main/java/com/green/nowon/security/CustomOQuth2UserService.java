package com.green.nowon.security;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOQuth2UserService extends DefaultOAuth2UserService {
	
	
	private final PasswordEncoder pw;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User=super.loadUser(userRequest);
		
		//db에 저장하기 위한 정보 조회
		String registrationId=userRequest.getClientRegistration().getRegistrationId();
		System.out.println(registrationId);
		//인증정보를 제공하는 폼이 다르다 그러기위해 아이디로 구별
		
		
		Map<String, Object> attributes=oAuth2User.getAttributes(); //구글기준임 (네이버랑 카카오는 다를수 있어요)
		
		
		System.out.println(">>>>>>>");
		for(String attributeName:attributes.keySet()) {
			System.out.print(attributeName+":");	
			System.out.println(attributes.get(attributeName));	
		}
		
		System.out.println("<<<<<<<");
		

		
		return socialUser(oAuth2User,registrationId ); //커스텀 유저 디테일즈에 포함됌
	}
	

		private OAuth2User socialUser(OAuth2User oAuth2User, String registrationId) {
		
		
		String email=null;
		String name=null;
		if(registrationId.equals("google")) { //구글
			
			email=oAuth2User.getAttribute("email"); //이게 이메일 이래요
			name=oAuth2User.getAttribute("name");
			
		}else if(registrationId.equals("naver")) {//네이버
			
			Map<String, Object>response=oAuth2User.getAttribute("response");
			email=(String) response.get("email");
			name=(String) response.get("name");
		}
		
		//소셜-회원가입되지 않고 소셜정보만 잇는 회원(이건 회원가입이 아닌 그냥 로그인만)
		MemberEntity entity=MemberEntity.builder()
				.name(email)
				//소셜 유저는 pass워드가 의미가 없기에 이렇게 만듬(임의의 pass정보를 부여) 그리고 저장하고 싶으면 따로 만드셈 
				//디비에 넣을 경우 소셜 유저와 일반인증에서 제외 시킨 후에 진행해야함 (회원가입 시킬 경우 소셜유저인지 일반유저인지 구분자를 만들어줘야한다)
				.pass(pw.encode(String.valueOf(System.currentTimeMillis())))
				.email(name)
				.build().addRole(Role.SOCIALUSER);
		
		
		return new CustomUserDetails(entity) ;
	}

}
