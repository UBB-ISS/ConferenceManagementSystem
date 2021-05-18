package com.iss.cms.web.controller;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.domain.UserConference;
import com.iss.cms.core.service.UserConferenceService;
import com.iss.cms.web.converter.UserConferenceConverter;
import com.iss.cms.web.converter.UserConverter;
import com.iss.cms.web.dto.UserConferencesDTO;
import com.iss.cms.web.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserConferenceController {
    private static final Logger logger = LoggerFactory.getLogger(UserConferenceConverter.class);

    @Autowired
    private UserConferenceService userConferenceService;

    @Autowired
    private UserConferenceConverter userConferenceConverter;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(value="/userConferences")
    UserConferencesDTO getAllUserConferences() {
        logger.trace("UserConferenceController - getAllUserConferences(): method entered");
        List<UserConference> userConferences = userConferenceService.getAllUserConferences();
        UserConferencesDTO userConferencesDTO = new UserConferencesDTO(userConferenceConverter.convertModelsToDTOs(userConferences));
        logger.trace("UserConferenceController - getAllUserConferences(): method finished -> " + userConferencesDTO.toString());
        return userConferencesDTO;
    }

    @RequestMapping(value="/allUsersFromAGivenConference/{conferenceId}")
    UsersDTO getAllUsersFromAGivenConference(@PathVariable int conferenceId) {
        logger.trace("UserConferenceController - getAllUsersFromAGivenConference(): method entered");
        List<AppUser> users = userConferenceService.getAllUsersFromAGivenConference(conferenceId);
        UsersDTO usersDTO = new UsersDTO(userConverter.convertModelsToDTOs(users));
        logger.trace("UserConferenceController - getAllUsersFromAGivenConference(): method finished -> " + usersDTO.toString());
        return usersDTO;
    }

    @RequestMapping(value="/allRolesForAGivenUser/{userId}")
    List<String>  getAllRolesForAGivenUser(@PathVariable int userId) {
        logger.trace("UserConferenceController - getAllRolesForAGivenUser: method entered -> userId = " + userId);
        List<String> roles = this.userConferenceService.getAllRolesForAGivenUser(userId);
        logger.trace("UserConferenceController - getAllRolesForAGivenUser: method finished -> " + roles.toString());
        return roles;
    }

    @RequestMapping(value="/allRolesForAGivenUserInAGivenConference/{userId}/{conferenceId}")
    List<String>  getAllRolesForAGivenUserInAGivenConference(@PathVariable int userId, @PathVariable int conferenceId) {
        logger.trace("UserConferenceController - getAllRolesForAGivenUserInAGivenConference: method entered");
        List<String> roles = this.userConferenceService.getAllRolesForAGivenUserInAGivenConference(userId, conferenceId);
        logger.trace("UserConferenceController - getAllRolesForAGivenUserInAGivenConference: method finished");
        return roles;
    }
}
