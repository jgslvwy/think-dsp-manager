package com.dts.manager.authentication;

import com.dts.core.exception.DtsException;
import com.dts.manager.model.SystemUser;
import com.dts.manager.service.SystemUserService;
import com.google.common.collect.Sets;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

/**
 * dps查询服务层
 *
 * @author jgs
 * @date 2019/8/11 16:33
 */
public class DpsReactiveUserDetailsService implements ReactiveUserDetailsService {

  @Autowired
  private SystemUserService userService;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    Mono<SystemUser> systemUserMono = userService.findByUserName(username);
    Set<GrantedAuthority> authoritie = Sets.newLinkedHashSet();
    Mono<User> userMono = systemUserMono
        .switchIfEmpty(Mono.error(() -> new DtsException("用户名[{}]在系统中不存在", username)))
        .map(
            systemUser -> {
              return new User(systemUser.getUserName(), systemUser.getPassword(), authoritie);
            });
    return userMono.ofType(UserDetails.class);
  }
}
