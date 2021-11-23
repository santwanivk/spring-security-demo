package com.santwanivk.springsecurity.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	//static final  String Constant_Password = "system123#";

	@Autowired
	private DataSource securityDataSource;
	
	//	@Override
	//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//		//add users for in memory authentication
	//
	//		UserBuilder users = User.withDefaultPasswordEncoder();
	//
	//		auth.inMemoryAuthentication()
	//		.withUser(users.username("john").password(Constant_Password).roles("Employee"))
	//		.withUser(users.username("mary").password(Constant_Password).roles("Employee","Manager"))
	//		.withUser(users.username("susan").password(Constant_Password).roles("Employee","Admin"));
	//	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//		http.authorizeRequests().anyRequest().authenticated().and()
		//		.formLogin().loginPage("/showMyLoginPage")
		//		.loginProcessingUrl("/authenticateTheUser")
		//		.permitAll().and().logout().permitAll();

		http.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.and().formLogin().loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll().and().logout().permitAll().and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
