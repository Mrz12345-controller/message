package com.zmy.message.controller;

import com.zmy.message.dto.AccessTokenDTO;
import com.zmy.message.dto.GithubUser;
import com.zmy.message.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {
    @Autowired
    GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.sercet}")
    private String clientsercet;
    @Value("${github.redirect.uri}")
    private String redirecturi;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsercet);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirecturi);
        accessTokenDTO.setState(state);
        String asseccToken = githubProvider.getAsseccToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(asseccToken);
        System.out.println(user.getName());
        return "index";
    }

}
