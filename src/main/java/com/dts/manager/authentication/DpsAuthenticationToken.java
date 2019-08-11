package com.dts.manager.authentication;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author jgs
 * @date 2019/8/10 17:30
 */
public class DpsAuthenticationToken  extends AbstractAuthenticationToken {

  public DpsAuthenticationToken(
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
