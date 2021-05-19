package com.iss.cms.web.controller;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.service.IUserService;
import com.iss.cms.web.converter.UserConverter;
import com.iss.cms.web.dto.UserDTO;
import com.iss.cms.web.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/users")
    UsersDTO getAllUsers() {
        logger.trace("UserController - getAllUsers(): method entered");
        List<AppUser> users = userService.getAllUsers();
        UsersDTO usersDTO = new UsersDTO(userConverter.convertModelsToDTOs(users));
        logger.trace("UserController - getAllUsers(): method finished -> " + usersDTO.toString());
        return usersDTO;
    }

    @RequestMapping(value = "/users/{id}")
    public UserDTO findUserById(@PathVariable("id") int id){
        return userConverter.convertModelToDTO(userService.findUserById(id));
    }

    @GetMapping(value = "/user/isExistent/{username}")
    public boolean isUsernameExistent(@PathVariable("username") String username){
        return userService.isUsernameExistent(username);
    }

    @RequestMapping(value = "/login/{username}/{password}")
    UserDTO login(@PathVariable String username, @PathVariable String password) throws CMSException {
        logger.trace("UserController - login(): method entered -> username = " + username + ", password = " + password);
        AppUser appUser = userService.login(username, password);
        UserDTO userDTO;
        if(appUser == null) userDTO = null;
        else userDTO = userConverter.convertModelToDTO(appUser);

        logger.trace("UserController - login(): method finished");
        return userDTO;
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    void createAccount(@RequestBody UserDTO userDTO) throws CMSException {
        logger.trace("UserController - createAccount(): method entered -> " + userDTO.toString());
        System.out.println(userDTO.toString());

        AppUser appUser = userConverter.convertDTOToModel(userDTO);
        userService.createAccount(appUser.getName(), appUser.getEmail(), appUser.getUsername(), appUser.getWebsite(), appUser.getAffiliation(), appUser.getPassword());

        logger.trace("UserController - createAccount(): method finished");
    }
}
