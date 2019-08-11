package com.dts.manager.dao;

import com.dts.manager.model.SystemUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author jgs
 * @date 2019/8/11 16:46
 */
public interface SystemUserRepository extends ReactiveCrudRepository<SystemUser, String> {

  Mono<SystemUser> findByUserName(String name);
}
