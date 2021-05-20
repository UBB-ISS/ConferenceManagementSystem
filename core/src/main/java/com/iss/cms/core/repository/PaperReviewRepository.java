package com.iss.cms.core.repository;

import com.iss.cms.core.domain.PaperReview;
import com.iss.cms.core.domain.UserConference;

import java.util.List;

public interface PaperReviewRepository extends Repository<PaperReview, Integer> {
    List<PaperReview> findAllByPaperId(int paperId);
}
