package com.domian.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LoginInfo {

    private String username;

    private String password;

    private String passwords;

    private Date create_time;

    private Date last_login_time;

}
