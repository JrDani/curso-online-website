package br.com.fabricaon.cursos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.fabricaon.cursos.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/perfil/**").hasRole("USER")
			.antMatchers("/comentario/**").hasRole("USER")
			.antMatchers("/usuario/**").hasRole("USER")
			.antMatchers("/matricula/**").hasRole("USER")
			.antMatchers("/**/videos/**").hasRole("USER")
			.antMatchers("/gerenciar-curso/**").hasRole("TEACHER")
			.antMatchers("/categoria/**").hasRole("ADMIN")			
			.antMatchers("/**").permitAll()
			.antMatchers("/curso/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/usuario/login").permitAll()
			.defaultSuccessUrl("/", false).failureUrl("/usuario/login?error=true")
			.and().exceptionHandling().accessDeniedPage("/error/access-denied")
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/usuario/login?logout=true").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioDAO)
		.passwordEncoder(new BCryptPasswordEncoder());
	}	
	
}
