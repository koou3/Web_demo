package com.domian.control;

import com.domian.entity.LoginInfo;
import com.domian.model.UserInfo;
import com.domian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
public class ControllerFirstPage {

    @Autowired
    UserService userService;

    // ログイン画面初期化
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String init(Model model, LoginInfo loginInfo){
        return "login";
    }

    // ログイン処理
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginEvent(Model modelin, LoginInfo loginInfo){
        ModelAndView model = new ModelAndView();
        UserInfo userInfo = userService.findUserCase(loginInfo.getUsername());
        // ユーザ名存在チェック
        if (userInfo == null) {
            model.addObject("errorFalg", true);
            model.addObject("errorMessage", "这个账号还没有哦,去注册一下嘛(●ˇ∀ˇ●)");
            model.setViewName("login");
            return model;
        }
        if (!StringUtils.equals(loginInfo.getPassword(), userInfo.getPassword())){
            model.addObject("errorFalg", true);
            model.addObject("errorMessage", "密码错啦!你是🐖咩?！");
            model.setViewName("login");
            return model;
        }
        loginInfo.setUsername(userInfo.getUsername());
        model.addObject("loginInfo", loginInfo);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String gotoRegister(Model model, LoginInfo loginInfo) {
        return "register";
    }

    /**
     * メニューをクリックする
     */
    @RequestMapping(value = "", params = "transitionTo", method = RequestMethod.POST)
    public String transitionTo(Model model, @RequestParam String transitionTo) {
        // 画面データ初期化
        return "redirect:" + transitionTo;
    }

    // 新規登録処理
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView registerEvent(Model modelin, LoginInfo loginInfo){
        ModelAndView model = new ModelAndView();
        // パスワード二重確認
        if (!StringUtils.equals(loginInfo.getPassword(), loginInfo.getPasswords())) {
            model.addObject("errorFalg", true);
            model.addObject("errorMessage", "密码不一样哦,小脑瓜子记不住嘛！");
            model.setViewName("register");
            return model;
        }
        // ユーザ名存在チェック
        UserInfo userInfo = userService.findUserCase(loginInfo.getUsername());
        if (userInfo != null) {
            model.addObject("errorFalg", true);
            model.addObject("errorMessage", "已经有这个账号名了,重新想一个！");
            model.setViewName("register");
            return model;
        }
        userService.createUserCase(loginInfo);
        model.addObject("loginInfo", loginInfo);
        model.setViewName("home");
        return model;
    }
}