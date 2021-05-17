package com.iss.cms.core.service;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IUserService {
    List<AppUser> getAllUsers();

    void createAccount(String name, String email, String username, String website,
                       String affiliation, String password) throws CMSException;

    AppUser login(String username, String password) throws CMSException;
}
