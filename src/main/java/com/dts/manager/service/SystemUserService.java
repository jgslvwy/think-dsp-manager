package com.dts.manager.service;

import com.dts.manager.model.SystemUser;
import reactor.core.publisher.Mono;

/**
 * @author jgs
 * @date 2019/8/11 16:35
 */
public interface SystemUserService {

  /**
   * 通过userName查找
   * @param userName
   * @return
   */
  Mono<SystemUser> findByUserName(String userName);
}
