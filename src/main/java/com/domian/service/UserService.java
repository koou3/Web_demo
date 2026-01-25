package com.domian.service;

import com.domian.entity.LoginInfo;
import com.domian.model.UserInfo;
import com.domian.repository.UserRepository;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public long maxIdCase(){
        long id = userRepository.selectMaxId();
        return id;
    }

    @Transactional
    public UserInfo findUserCase(String username) {
        List<UserInfo> rst = userRepository.findByUser(username);
        if (rst.size() == 0) {
            return null;
        } else {
            return rst.get(0);
        }
    }

    @Transactional
    public void createUserCase(LoginInfo loginInfo) {
        userRepository.insertByUser(loginInfo.getUsername(),loginInfo.getPassword());
    }
}
