package com.dts.manager.service.impl;

import com.dts.manager.dao.SystemUserRepository;
import com.dts.manager.model.SystemUser;
import com.dts.manager.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 系统用户服务层
 *
 * @author jgs
 * @date 2019/8/11 16:37
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

  @Autowired
  private SystemUserRepository systemUserRepository;

  @Override
  public Mono<SystemUser> findByUserName(String userName) {
    return systemUserRepository.findByUserName(userName);
  }
}
