package com.iss.cms.core.service;

import com.iss.cms.core.domain.Availability;
import com.iss.cms.core.domain.Paper;
import com.iss.cms.core.domain.ReviewerPaper;
import com.iss.cms.core.domain.UserConference;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.ReviewerPaperRepository;
import com.iss.cms.core.repository.UserConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewerPaperService implements IReviewerPaperService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewerPaperService.class);

    @Autowired
    private ReviewerPaperRepository reviewerPaperRepository;

    @Autowired
    private UserConferenceRepository userConferenceRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public List<ReviewerPaper> getAll() {
        logger.trace("ReviewerPaper - getAll: method entered");

        List<ReviewerPaper> all = reviewerPaperRepository.findAll();

        logger.trace("ReviewerPaper - getAll: method finished");
        return all;
    }

    @Override
    public List<ReviewerPaper> getBidPapersFromAGivenConference(int conferenceId) {
        logger.trace("ReviewerPaper - getAllFromAGivenConference: method entered");

        List<UserConference> userConferences = userConferenceRepository.findAllByConferenceID(conferenceId);

        List<Paper> papers = new ArrayList<>();
        for(UserConference us: userConferences) {
            for(Paper p: paperRepository.findAll()) {
                if(p.isFinalized() && p.getUserConferenceId() == us.getId() && !papers.contains(p)) {
                    papers.add(p);
                }
            }
        }

        List<ReviewerPaper> all = new ArrayList<>();
        for(Paper p: papers) {
            for(ReviewerPaper rp: reviewerPaperRepository.findAll()) {
                if(rp.getPaperId() == p.getId() && !all.contains(rp)) {
                    all.add(rp);
                }
            }
        }

        logger.trace("ReviewerPaper - getAllFromAGivenConference: method finished");
        return all;
    }

    @Override
    public void addAvailability(int userId, int paperId, boolean assigned, Availability availability) throws CMSException {
        logger.trace("ReviewerPaper - addAvailability: method entered");

        List<ReviewerPaper> reviewerPaper = reviewerPaperRepository.findAll();
        for(ReviewerPaper rp: reviewerPaper) {
            if(rp.getUserId() == userId && rp.getPaperId() == paperId) {
                logger.trace("ReviewerPaper - addAvailability: This user has already bid for this paper!");
                throw new CMSException("This user has already bid for this paper!");
            }
        }

        ReviewerPaper newReviewerPaper = new ReviewerPaper(userId, paperId, assigned, availability);
        System.out.println("service: " + newReviewerPaper.toString());
        reviewerPaperRepository.save(newReviewerPaper);

        logger.trace("ReviewerPaper - addAvailability: method finished");
    }

    @Override
    @Transactional
    public void changeStatus(int id) throws CMSException {
        logger.trace("ReviewerPaper - changeStatus: method entered");

        reviewerPaperRepository.findById(id)
                .ifPresentOrElse(
                        (reviewerPaper) -> {
                            reviewerPaper.setAssigned(true);
                        }, () -> {
                            logger.trace("ReviewerPaper - changeStatus: This availability does not exist!");
                            throw new CMSException("This availability does not exist!");
                        }
                );

        System.out.println("Service: " + reviewerPaperRepository.findById(id).toString());

        logger.trace("ReviewerPaper - changeStatus: method finished");
    }

    @Override
    public ReviewerPaper findByUserIdAndPaperId(int userId, int paperId) {
        logger.trace("ReviewerPaper - findByUserIdAndPaperId: method entered");

        List<ReviewerPaper> all = reviewerPaperRepository.findAll();
        ReviewerPaper reviewerPaper = null;
        for(ReviewerPaper rp: all) {
            if(rp.getUserId() == userId && rp.getPaperId() == paperId) {
                reviewerPaper = rp;
            }
        }

        logger.trace("ReviewerPaper - findByUserIdAndPaperId: method finished");
        return reviewerPaper;
    }
}
