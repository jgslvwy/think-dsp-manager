package com.dts.manager.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import reactor.core.publisher.Mono;

/**
 * DPS 验证失败处理器
 *
 * @author jgs
 * @date 2019/8/11 16:53
 */
public class DpsServerAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {

  @Override
  public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange,
      AuthenticationException e) {
    return null;
  }
}
