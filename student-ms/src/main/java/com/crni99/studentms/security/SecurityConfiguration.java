package com.crni99.studentms.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated());
		http.httpBasic(withDefaults());
		http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**", "/h2-console/**"));
		http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
		return http.build();
	}

}
