package com.iss.cms.core.service;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.domain.Conference;
import com.iss.cms.core.domain.Role;
import com.iss.cms.core.domain.UserConference;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.ConferenceRepository;
import com.iss.cms.core.repository.UserConferenceRepository;
import com.iss.cms.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserConferenceService implements IUserConferenceService {
    private static final Logger logger = LoggerFactory.getLogger(UserConferenceService.class);

    @Autowired
    private UserConferenceRepository userConferenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public List<UserConference> getAllUserConferences() {
        logger.trace("UserConferenceService - getAllUserConferences(): method entered");
        List<UserConference> userConferences = userConferenceRepository.findAll(Sort.by(Sort.Direction.ASC, "userID"));
        logger.trace("UserConferenceService - getAllUserConferences(): method finished -> " + userConferences.toString());
        return userConferences;
    }

    @Override
    public List<AppUser> getAllUsersFromAGivenConference(int conferenceId) {
        logger.trace("UserConferenceService - getAllUsersFromAGivenConference(): method entered -> conferenceId = " + conferenceId);

        List<UserConference> userConferences = userConferenceRepository.findAllByConferenceID(conferenceId);

        List<AppUser> users = new ArrayList<>();
        for( UserConference userConference: userConferences) {
            Optional<AppUser> appUser = userRepository.findById(userConference.getUserID());
            appUser.ifPresent(users::add);
        }

        logger.trace("UserConferenceService - getAllUsersFromAGivenConference(): method finished -> " + users.toString());
        return users;
    }

    @Override
    public void addUserToConference(int userId, int conferenceId, Role role, boolean paid) throws CMSException{
        logger.trace("UserConferenceService - addUserToConference(): method entered -> " +
                "userId = " + userId + ", conferenceId = " + conferenceId + ", role = " + role + ", paid = " + paid);

        Optional<AppUser> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            logger.trace("UserConferenceService: User id is invalid!");
            throw new CMSException("User id is invalid!");
        }
        Optional<Conference> conference = conferenceRepository.findById(conferenceId);
        if(conference.isEmpty()) {
            logger.trace("UserConferenceService: Conference id is invalid!");
            throw new CMSException("Conference id is invalid!");
        }

        UserConference userConference = new UserConference(userId, conferenceId, role, paid);
        System.out.println(userConference.toString());
        userConferenceRepository.save(userConference);

        logger.trace("UserConferenceService = addUserToConference(): method finished");
    }

    @Override
    public List<Role> getAllRolesForAGivenUser(int userId) {
        logger.trace("UserConferenceService - getAllRolesForAGivenUser(): method entered -> userId = " + userId);

        List<UserConference> userConferences = userConferenceRepository.findAllByUserID(userId);

        List<Role> roles = new ArrayList<>();
        for(UserConference userConference: userConferences) {
            roles.add(userConference.getRole());
        }

        logger.trace("UserConferenceService - getAllRolesForAGivenUser(): method finished -> " + roles.toString());
        return roles;
    }

    public void payFeeForUser(int userId, int conferenceId) {
        logger.trace("UserConferenceService - payFeeForUser(): method entered -> userId = " + userId);

        List<UserConference> userConferences = userConferenceRepository.findAllByUserID(userId);
        for(UserConference userConference: userConferences) {
            if(userConference.getConferenceID() == conferenceId) {
                userConference.setPaid(true);
            }
        }
        logger.trace("UserConferenceService - payFeeForUser(): method entered");
    }
}
