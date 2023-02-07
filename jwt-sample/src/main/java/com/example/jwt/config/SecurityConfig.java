package com.example.jwt.config;

import com.example.jwt.config.jwt.JwtAccessDeniedHandler;
import com.example.jwt.config.jwt.JwtAuthenticationEntryPoint;
import com.example.jwt.config.jwt.JwtSecurityConfig;
import com.example.jwt.config.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security 설정
 *
 * <p>authorizeRequests() : 요청에 대한 권한 설정<p/>
 * <p>antMatchers() : 특정 경로를 설정<p/>
 * <p>anyRequest() : 설정한 경로 외에 모든 경로<p/>
 * <p>authenticated() : 인증된 사용자만 접근<p/>
 * <p>permitAll() : 모든 사용자가 접근<p/>
 * <p>csrf() : CSRF 보안에 대한 설정<p/>
 * <p>disable() : 기능을 해제<p/>
 */
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final TokenProvider tokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  /**
   * WebSecurity 설정
   */
  @Override
  public void configure(WebSecurity webSecurity) {
    webSecurity
        .ignoring()
        .antMatchers(
            "/h2-console/**"
            , "/favicon.ico"
        );
  }

  /**
   * HttpSecurity 설정
   */
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()

        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .accessDeniedHandler(jwtAccessDeniedHandler)

        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeRequests()
        .antMatchers("/api/sample").permitAll()
        .antMatchers("/api/hello").permitAll()
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers("/api/signup").permitAll()
        .anyRequest().authenticated()

        .and()
        .apply(new JwtSecurityConfig(tokenProvider));
  }

  /**
   * 비밀번호의 단방향 암호화를 지원하는 PasswordEncoder를 빈으로 등록
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}