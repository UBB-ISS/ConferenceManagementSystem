package com.iss.cms.core.service;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.domain.Role;
import com.iss.cms.core.domain.UserConference;

import java.util.List;

public interface IUserConferenceService {
    List<UserConference> getAllUserConferences();

    List<AppUser> getAllUsersFromAGivenConference(int conferenceId);

    void addUserToConference(int userId, int conferenceId, Role role, boolean paid);

    List<String> getAllRolesForAGivenUser(int userId);

    List<String> getAllRolesForAGivenUserInAGivenConference(int userId, int conferenceId);
}
