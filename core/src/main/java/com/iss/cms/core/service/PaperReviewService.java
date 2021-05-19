package com.iss.cms.core.service;

import com.iss.cms.core.domain.*;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.PaperReviewRepository;
import com.iss.cms.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaperReviewService implements IPaperReviewService{
    private static final Logger logger = LoggerFactory.getLogger(PaperReviewService.class);
    @Autowired
    PaperReviewRepository paperReviewRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<PaperReview> getPaperReviews() {
        logger.trace("PaperReviewService - getPaperReviews(): method entered");
        List<PaperReview> paperReviews = paperReviewRepository.findAll();
        logger.trace("PaperReviewService - getPaperReviews(): method finished");
        return paperReviews;
    }

    @Override
    public PaperReview findPaperReviewByID(int id) {
        logger.trace("PaperReviewService - findPaperReviewById(): method entered");
        Optional<PaperReview> paperReview = paperReviewRepository.findById(id);
        if(paperReview.isEmpty()) {
            logger.trace("PaperReviewService: Paper review id does not exist!");
            throw new CMSException("Paper review id does not exist!");
        }
        logger.trace("PaperReviewService - findPaperReviewById(): method finished");
        return paperReview.get();
    }

    @Override
    public void addPaperReview(int reviewerId, int paperId, String review, Qualifier qualifier) {
        logger.trace("PaperReviewService - addPaperReview(): method entered ->" + "reviewerId = " +
                reviewerId + ", paperId = " + paperId + ", review = " + review +
                ", qualifier = " + qualifier);

        Optional<AppUser> reviewer = userRepository.findById(reviewerId);
        if(reviewer.isEmpty()) {
            logger.trace("PaperReviewService: Reviewer id does not exist!");
            throw new CMSException("Reviewer id does not exist!");
        }
        Optional<Paper> paper = paperRepository.findById(paperId);
        if(paper.isEmpty()) {
            logger.trace("PaperReviewService: Paper id does not exist!");
            throw new CMSException("Paper id does not exist!");
        }

        PaperReview paperReview = new PaperReview(reviewerId, paperId, review, qualifier);
        System.out.println(paperReview.toString());
        paperReviewRepository.save(paperReview);

        logger.trace("PaperReviewService - addPaperReview(): method finished");
    }

    @Override
    public List<AppUser> getReviewersForPaper(int paperId) {
        logger.trace("PaperReviewService - getReviewersForPaper(): method entered -> paperId = " + paperId);

        List<PaperReview> paperReviews = paperReviewRepository.findAllByPaperId(paperId);

        List<AppUser> reviewers = new ArrayList<>();
        for(PaperReview paperReview: paperReviews) {
            Optional<AppUser> appUser = userRepository.findById(paperReview.getReviewerId());
            appUser.ifPresent(reviewers::add);
        }

        logger.trace("UserConferenceService - getReviewersForPaper(): method finished -> " + reviewers.toString());
        return reviewers;
    }
}
