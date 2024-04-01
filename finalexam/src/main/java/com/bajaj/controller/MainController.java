package com.bajaj.controller;

import com.bajaj.beans.ErrorBean;
import com.bajaj.beans.LoginRequestBean;
import com.bajaj.beans.ResponseBean;
import com.bajaj.beans.SignUpRequestBean;
import com.bajaj.entities.UserDetailsEntity;
import com.bajaj.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MainController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "getUserDetails", path = "getUserDetails")
    public UserDetailsEntity getEmployee(@RequestParam String email) {
        ResponseBean responseBean = new ResponseBean();
        try {
            return userService.getUser(email);
        }catch (Exception e){
            ErrorBean errorBean = new ErrorBean();
            errorBean.setErrorCode("000");
            errorBean.setErrorMessage(e.getMessage());
            List<ErrorBean> list = new ArrayList<>();
            list.add(errorBean);
            responseBean.setErrorBean(list);
        }

return new UserDetailsEntity();
    }

    @PostMapping(value = "login", path = "login")
    public ResponseBean login(@RequestBody @Valid LoginRequestBean requestBean) {
        ResponseBean responseBean = new ResponseBean();
        try {
            responseBean.setPayload(userService.getUser(requestBean));

        } catch (Exception e) {
            ErrorBean errorBean = new ErrorBean();
            errorBean.setErrorCode("000");
            errorBean.setErrorMessage(e.getMessage());
            List<ErrorBean> list = new ArrayList<>();
            list.add(errorBean);
            responseBean.setErrorBean(list);
        }
        return responseBean;

    }


    @PostMapping(value = "signup", path = "signup")
    public ResponseBean signUp(@RequestBody @Valid SignUpRequestBean requestBean) {
        ResponseBean responseBean = new ResponseBean();
        try {

            responseBean.setPayload(userService.saveUser(requestBean));

        } catch (Exception e) {
            ErrorBean errorBean = new ErrorBean();
            errorBean.setErrorCode("000");
            errorBean.setErrorMessage(e.getMessage());
            List<ErrorBean> list = new ArrayList<>();
            list.add(errorBean);
            responseBean.setErrorBean(list);
        }
        return responseBean;
    }
}
