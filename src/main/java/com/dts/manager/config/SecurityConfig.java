package com.dts.manager.config;

import com.dts.manager.authentication.DpsReactiveUserDetailsService;
import com.dts.manager.authentication.DpsServerAuthenticationFailureHandler;
import com.dts.manager.authentication.DpsServerAuthenticationSuccessHandler;
import com.google.common.collect.Lists;
import java.util.LinkedList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  private static final String[] AUTH_WHITELIST = new String[]{"/login", "/actuator/**"};

  @Bean
  ReactiveAuthenticationManager reactiveAuthenticationManager() {
    final ReactiveUserDetailsService detailsService = userDetailsService();
    LinkedList<ReactiveAuthenticationManager> managers = Lists.newLinkedList();
    managers.add(authentication -> {
      // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
      return Mono.empty();
    });
    // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
    managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(detailsService));
    return new DelegatingReactiveAuthenticationManager(managers);
  }

  /**
   * 将登陆后的用户及权限信息存入session中
   */
  @Bean
  ServerSecurityContextRepository serverSecurityContextRepository() {
    return new WebSessionServerSecurityContextRepository();
  }

  /**
   * 密码加密工具
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 自定义获取用户信息，此处使用mysql基于RBAC模式
   */
  @Bean
  public ReactiveUserDetailsService userDetailsService() {
    return new DpsReactiveUserDetailsService();
  }

  /**
   * 此处的代码会放在SecurityConfig类中，此处只是摘要下
   */
  @Bean
  SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
    ServerHttpSecurity.FormLoginSpec formLoginSpec = http.formLogin();
    formLoginSpec.authenticationSuccessHandler(new DpsServerAuthenticationSuccessHandler())
        .loginPage("/login")
        .authenticationFailureHandler(new DpsServerAuthenticationFailureHandler());
    return formLoginSpec.and()
        .csrf().disable()
        .httpBasic().disable()
        .authorizeExchange()
        .pathMatchers(AUTH_WHITELIST).permitAll()
        .anyExchange().authenticated()
        .and().build();
  }


}