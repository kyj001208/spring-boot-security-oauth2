package com.green.nowon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//springsecurity6 & boot 3.0 ~

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//순서가 있기에 아래 순서에 맞게 진행해야함 
	
	 @Bean
	     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(Customizer.withDefaults()) 
	            //authorizeHttpRequests: 요청하는 url에 대한 보안
	            .authorizeHttpRequests(authorize -> authorize
	            		.requestMatchers("/").permitAll() //인증 없이 접속 가능하다는 뜻 ("/"):경우 인덱스를 말하는것 
	            		//.requestMatchers(HttpMethod.GET,"").permitAll()
	            		.requestMatchers("/hr/**").hasRole("HR") // 인사팀에 들어가기 위해서는 hasRole권한 -> HR 권한이 있어야함 
	            		.requestMatchers("/admin/**").hasRole("ADMIN") //authorize=권한(role)
	                .anyRequest().authenticated() //위에 설정을 제외한 나머지는 인증이 필요하다는 뜻_ 순서상 제일 마지막 
	            )
	            .httpBasic(Customizer.withDefaults())
	            .formLogin(Customizer.withDefaults());
	        return http.build();
	    }

}
