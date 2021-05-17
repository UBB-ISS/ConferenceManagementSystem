package com.iss.cms.core.service;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppUser> getAllUsers() {
        logger.trace("UserService - getAllUsers(): method entered");
        List<AppUser> users = userRepository.findAll();
        logger.trace("UserService - getAllUsers(): method finished -> " + users.toString());
        return users;
    }

    @Override
    public AppUser login(String username, String password) throws CMSException {
        logger.trace("UserService - login(): method entered");

        AppUser appUser = new AppUser();
        boolean ok = false;
        List<AppUser> allUsers = userRepository.findAll();
        for(AppUser user: allUsers) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                appUser = user;
                ok = true;
                break;
            }
        }

        if(!ok) {
            /*appUser = null;*/
            logger.trace("UserService: The user does not exist!");
            throw new CMSException("The user does not exist!");
        }

        logger.trace("UserService - login(): method finished -> " + appUser.toString());

        return appUser;
    }

    @Override
    public void createAccount(String name, String email, String username, String website, String affiliation, String password) throws CMSException {
        logger.trace("UserService - createAccount(): method entered -> " +
                "name = " + name + ", email = " + email + ", username = " + username + ", website = " + website +
                ", affiliation = " + affiliation + ", password = " + password);

        List<AppUser> allUsers = userRepository.findAll();
        for(AppUser user: allUsers) {
            if(user.getUsername().equals(username)) {
                logger.trace("UserService: This username is already used!");
                throw new CMSException("This username is already used!");
            }
        }

        AppUser appUser = new AppUser(name, email, username, website, affiliation, password);
        userRepository.save(new AppUser(name, email, username, website, affiliation, password));

        logger.trace("UserService - createAccount(): method finished -> " + appUser.toString());
    }
}
