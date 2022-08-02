package com.luctt.KnifeWorld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.luctt.KnifeWorld.service.UserService;
import com.luctt.KnifeWorld.utilities.AppConstraint;

@EnableWebSecurity
public class SpringSecurityConfiguration implements UserDetailsService {
	@Autowired
	private UserService service;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeHttpRequests(authz -> authz.antMatchers("/","/home","/product/**").permitAll()
				.antMatchers("/api/admin/**").hasRole("0")
				.anyRequest().hasRole("1")
				).formLogin().defaultSuccessUrl("/home").usernameParameter("username").permitAll().and()
			.logout().permitAll();
		;
		// @formatter:on
		return http.build();
	}

//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().antMatchers("/resources/static/**");
//	}

//	@Bean
//	public UserDetailsManager userDetailsService() {
//	  UserDetails user1 = User.withDefaultPasswordEncoder()
//		  .username("lucuser")
//		  .password("123456")
//		  .roles("1")
//		  .build();
//	  UserDetails user2 = User.withDefaultPasswordEncoder()
//	      .username("lucadmin")
//		  .password("123456")
//		  .roles("0")
//		  .build();
//
//	  return new InMemoryUserDetailsManager(user1, user2);
//	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.luctt.KnifeWorld.entities.User u = service.getByEmail(username);
		if (u == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails user = User.builder().username(u.getEmail()).password(u.getPassword())
					.roles(u.getRole() == 0 ? AppConstraint.ROLE_ADMIN : AppConstraint.ROLE_USER)
					.disabled(u.getStatus() == 0 ? false : true).build();
			return user;
		}
	}
}
