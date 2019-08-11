package com.dts.manager.contoller;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * dps用户登录
 *
 * @author jgs
 * @date 2019/8/10 16:51
 */
@RestController
public class DpsLoginController {


  @GetMapping("/")
  public String index(Model model,
      @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,

      @AuthenticationPrincipal OAuth2User oauth2User) {

    model.addAttribute("userName", oauth2User.getName());

    model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());

    model.addAttribute("userAttributes", oauth2User.getAttributes());

    return "index";
  }

  @GetMapping("/login")
  public void login() {
    Authentication authentication = new TestingAuthenticationToken("user", "password", "ROLE_USER");
    Mono<String> messageByUsername = ReactiveSecurityContextHolder.getContext()
        .map(SecurityContext::getAuthentication)
        .map(Authentication::getName)
        .flatMap(this::findMessageByUsername)
        // In a WebFlux application the `subscriberContext` is automatically setup using `ReactorContextWebFilter`
        .subscriberContext(ReactiveSecurityContextHolder.withAuthentication(authentication));
    StepVerifier.create(messageByUsername)
        .expectNext("Hi user")
        .verifyComplete();
  }

  Mono<String> findMessageByUsername(String username) {
    return Mono.just("Hi " + username);
  }
}
