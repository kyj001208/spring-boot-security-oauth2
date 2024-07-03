package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

//springsecurity6 & boot 3.0 ~

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	//순서가 있기에 아래 순서에 맞게 진행해야함 
	 @Bean
	     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	        
	        	//토큰발행은 security가 해줍니다.
	        	//csrf 경우 post로 진행해야 하며 로그아웃시 post로 진행해야함, get으로 할경우 에러 ,form태그 또는 javascript 사용
	            //.csrf(csrf->csrf.disable())
	            .csrf(Customizer.withDefaults()) //표기하지 않아도 기본으로 crsf 보안이 적용-get 요청을 제외한 모든 요청에 대해 post, put, delete 등 발행 필요
	            //authorizeHttpRequests: 요청하는 url에 대한 보안
	            .authorizeHttpRequests(authorize -> authorize
	            		.requestMatchers("/css/**","/js/**","/images/**").permitAll()
	            		.requestMatchers("/", "/signup", "/boards").permitAll() //인증 없이 접속 가능하다는 뜻 ("/"):경우 인덱스를 말하는것 
	            		.requestMatchers("/emp/**","/boards/new").hasRole("EMP")
	            		//.requestMatchers(HttpMethod.GET,"").permitAll()
	            		//.requestMatchers("/hr/**").hasRole("HR") // 인사팀에 들어가기 위해서는 hasRole권한 -> HR 권한이 있어야함 
	            		.requestMatchers("/admin/**").hasRole("ADMIN") //authorize=권한(role)
	                    .anyRequest().authenticated() //위에 설정을 제외한 나머지는 인증이 필요하다는 뜻_ 순서상 제일 마지막 
	                //권한 없이 로그인만 성공하면 접근가능 
	            )
	            //.httpBasic(Customizer.withDefaults())
	            //UsernameAndPasswordAuthenticationToken 에 매핑
	            .formLogin(login->login
	            		.loginPage("/login")
	            		.loginProcessingUrl("/login-action")
	            		.permitAll()//설정하지 않으면 리다렉션한 횟수가 너무 많습니다.
	            		 //해당 메서드를 작성시에는 해당 이름에 맞게 html form태그 이름 맞게 진행
	            		
	            		.usernameParameter("email") //default:username //html name과 일치해야함
	            		.passwordParameter("pass")  //Defaultis "password" //html name과 일치해야함
	            		.successHandler(customAuthenticationSuccessHandler) //로그인 성공 이후에 처리할 내용을 정의하는 클래스 생성 
	            		
	            )
	            .logout(logout->logout
	            		.logoutSuccessUrl("/")//default //로그 아웃 시 인덱스 페이지로 넘어가게끔 설정
	            		.invalidateHttpSession(true)//로그아웃 시 세션에 저장된 데이터를 제거하고, 해당 세션을 더 이상 유효하지 않도록 만듭니다. 
	            )
	            
	            .userDetailsService(customUserDetailsService)
	            ;
	        
	        return http.build();
	    }
	 


	@Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder(14);
	 }

}
