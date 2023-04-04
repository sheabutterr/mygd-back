package project.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import project.mapper.UserMapper;
import project.service.UserService;


@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	private UserService userService;
	private UserMapper userMapper;
	private BCryptPasswordEncoder passwordEncoder;
	private Environment env;
	private JwtTokenUtil jwtTokenUtil;
	private JwtRequestFilter jwtRequestFilter;
	
	public WebSecurity(UserService userService, UserMapper userMapper, 
			BCryptPasswordEncoder passwordEncoder, Environment env, JwtTokenUtil jwtTokenUtil, JwtRequestFilter jwtRequestFilter) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.env = env;
		this.jwtTokenUtil = jwtTokenUtil;
		this.jwtRequestFilter = jwtRequestFilter; 
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/**").permitAll();
//		http.authorizeRequests()
//			.antMatchers("/api/board/**").authenticated()
//			.and().addFilter(getAuthenticationFilter());
		
		http.authorizeRequests()
		.antMatchers("/login", "/regist", "/category", "/classList", "/classList/**", "/img/**","/level","/latestList/**").permitAll()
		.anyRequest().authenticated()
		.and().addFilter(getAuthenticationFilter())
		.addFilterBefore(jwtRequestFilter, AuthenticationFilter.class).cors();
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userMapper, env, jwtTokenUtil);
		authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	

}
