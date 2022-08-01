package com.luctt.KnifeWorld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeHttpRequests(authz -> authz.antMatchers("/","/home","/product/**").permitAll()
				.antMatchers("/api/admin/**").hasRole("0")
				.anyRequest().hasRole("1")
				).formLogin().defaultSuccessUrl("/home").permitAll().and()
		.logout().permitAll();
		;
		// @formatter:on
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/resources/**");
	}
	@Bean
	public UserDetailsManager userDetailsService() {
	  UserDetails user1 = User.withDefaultPasswordEncoder()
		  .username("khanh")
		  .password("123456")
		  .roles("1")
		  .build();
	  UserDetails user2 = User.withDefaultPasswordEncoder()
	      .username("thanh")
		  .password("123456")
		  .roles("0")
		  .build();

	  return new InMemoryUserDetailsManager(user1, user2);
	}
}
