package com.dts.manager.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

/**
 * DPS 验证成功处理器
 *
 * @author jgs
 * @date 2019/8/11 16:52
 */
public class DpsServerAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

  @Override
  public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
      Authentication authentication) {
    return null;
  }
}
