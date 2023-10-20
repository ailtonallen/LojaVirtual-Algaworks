package com.farmacia.lojavirtual.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AppUserDetailsService userDetailsService() {
		return new AppUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
		jsfLoginEntry.setLoginFormUrl("/index2.xhtml");
		jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

		JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
		jsfDeniedEntry.setLoginPath("/AcessoNegado.xhtml");
		jsfDeniedEntry.setContextRelative(true);

		http.csrf()
				.disable()
				.headers()
				.frameOptions()
				.sameOrigin()
				.and()

				.authorizeRequests()
				.antMatchers("/Login.xhtml", "/Erro.xhtml", "/index2.xhtml",
						"/javax.faces.resource/**")
				.permitAll()
				.antMatchers("/index.xhtml", "/AcessoNegado.xhtml",
						"/dialogos/**").authenticated()
				.antMatchers("/pedidos/**").hasAnyRole("LOJA", "ADMINISTRADOR")
				.antMatchers("/produtos/**")
				.hasAnyRole("LOJA", "ADMINISTRADOR")
				.antMatchers("/relatorios/**").hasRole("ADMINISTRADOR").and()

				.formLogin().loginPage("/Login.xhtml")
				.failureUrl("/Login.xhtml?invalid=true").and()

				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()

				.exceptionHandling().accessDeniedPage("/AcessoNegado.xhtml")
				.authenticationEntryPoint(jsfLoginEntry)
				.accessDeniedHandler(jsfDeniedEntry);
	}

}