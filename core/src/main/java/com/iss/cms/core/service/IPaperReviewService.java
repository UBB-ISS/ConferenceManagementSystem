package com.iss.cms.core.service;

import com.iss.cms.core.domain.AppUser;
import com.iss.cms.core.domain.PaperReview;
import com.iss.cms.core.domain.Qualifier;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IPaperReviewService {
    List<PaperReview> getPaperReviews();

    PaperReview findPaperReviewByID(int id) throws CMSException;

    void addPaperReview(int reviewerId, int paperId, String review, Qualifier qualifier) throws CMSException;

    List<AppUser> getReviewersForPaper(int paperId);
}
