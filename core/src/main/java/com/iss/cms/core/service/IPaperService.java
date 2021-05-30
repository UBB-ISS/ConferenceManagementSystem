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

    List<Paper> getFinalPapersFromAConference(int userId, int conferenceId);

    void updatePaper(
            int id,
            String title,
            String keywords,
            String paperText,
            String abstractText,
            boolean finalized,
            boolean accepted);

    List<Paper> getPapersReadyForReview(int userId, int conferenceId);

    String getAuthorForAGivenPaper(int paperId);

    int getAuthorIDForAGivenPaper(int paperId);
}
