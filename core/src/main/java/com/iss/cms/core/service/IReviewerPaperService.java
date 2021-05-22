package com.iss.cms.core.service;

import com.iss.cms.core.domain.Availability;
import com.iss.cms.core.domain.ReviewerPaper;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IReviewerPaperService {
    List<ReviewerPaper> getAll();

    List<ReviewerPaper> getAllFromAGivenConference(int conferenceId);

    void addAvailability(int userId, int paperId, boolean assigned, Availability availability) throws CMSException;

    void changeStatus(int id) throws CMSException;

    ReviewerPaper findByUserIdAndPaperId(int userId, int paperId);
}
