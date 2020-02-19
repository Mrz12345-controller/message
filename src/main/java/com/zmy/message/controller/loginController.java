package com.zmy.message.controller;

import com.zmy.message.dto.AccessTokenDTO;
import com.zmy.message.dto.GithubUser;
import com.zmy.message.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @Autowired
    GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("267f9a0cfe7cc0e3c4e6");
        accessTokenDTO.setClient_secret("5d49f30af5b6c09cfdfa9a7859310cf0d90aaa74");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String asseccToken = githubProvider.getAsseccToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(asseccToken);
        System.out.println(user);
        return "index";
    }

}
