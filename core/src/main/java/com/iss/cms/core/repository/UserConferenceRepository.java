package com.iss.cms.core.repository;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.domain.UserConference;

import java.util.List;

public interface UserConferenceRepository extends Repository<UserConference, Integer> {
    List<UserConference> findAllByConferenceID(int conferenceId);

    List<UserConference> findAllByUserID(int userId);
}
