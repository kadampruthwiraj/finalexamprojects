package com.bajaj.services;

import com.bajaj.beans.ErrorBean;
import com.bajaj.beans.LoginRequestBean;
import com.bajaj.beans.SignUpRequestBean;
import com.bajaj.common.CustomeException;
import com.bajaj.entities.UserDetailsEntity;
import com.bajaj.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository userRepository;

    public UserDetailsEntity getUser(String  email) {


        return userRepository.findByEmail(email).get(0);

    }

    public UserDetailsEntity saveUser(SignUpRequestBean signUpRequestBean) {
        UserDetailsEntity userEntity = mappingSignUpRequestToUserEntity(signUpRequestBean);
        userEntity.setId(userRepository.findAll().size() + 1);

        return userRepository.save(userEntity);

    }

    UserDetailsEntity mappingSignUpRequestToUserEntity(SignUpRequestBean signUpRequestBean) {
        UserDetailsEntity obj = new UserDetailsEntity();
        obj.setFull_name(signUpRequestBean.getFull_name());
        obj.setEmail(signUpRequestBean.getEmail());
        obj.setPassword(String.valueOf(Base64.getEncoder().encodeToString(signUpRequestBean.getPassword().getBytes())));
        obj.setUsername(signUpRequestBean.getUsername());
        obj.setPhone_number(signUpRequestBean.getPhone_number());
        obj.setGender(signUpRequestBean.getGender());
        return obj;
    }


    public UserDetailsEntity getUser(LoginRequestBean loginRequestBean) throws CustomeException {

        try {


            System.out.print("##### "+loginRequestBean.getLoginId()+" "+ String.valueOf(Base64.getEncoder().encode(loginRequestBean.getPassword().getBytes())));
            List<UserDetailsEntity> list = userRepository.findByEmailAndPassword(loginRequestBean.getLoginId(), Base64.getEncoder().encodeToString(loginRequestBean.getPassword().getBytes()) );
            return list.get(list.size() - 1);
        } catch (Exception e) {
            ErrorBean errorBean = new ErrorBean();
            errorBean.setErrorCode("001");
            errorBean.setErrorMessage(e.getMessage());
            throw new CustomeException(errorBean);
        }

    }
}
