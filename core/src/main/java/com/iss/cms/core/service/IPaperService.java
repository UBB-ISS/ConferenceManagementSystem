package com.iss.cms.core.service;

import com.iss.cms.core.domain.Paper;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IPaperService {
    List<Paper> getPapers();

    Paper findPaperById(int id) throws CMSException;

    void addPaper(
            int userConferenceId,
            String title,
            String keywords,
            String paperText,
            String abstractText,
            boolean finalized,
            boolean accepted) throws CMSException;

    List<Paper> getPapersOfAUserInAConference(int userId, int conferenceId);

    List<Paper> getFinalPapersFromAConference(int conferenceId);
}
