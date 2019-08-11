package com.dts.manager.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jgs
 * @date 2019/8/11 16:36
 */
@Data
@Document
public class SystemUser {
  /**
   * 用户名
   */
  private String userName;
  /**
   * 密码
   */
  private String password;
  /**
   * 手机号
   */
  private String phone;
  /**
   * 邮箱
   */
  private String mail;

}
