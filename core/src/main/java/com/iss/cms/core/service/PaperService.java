package com.iss.cms.core.service;

import com.iss.cms.core.domain.*;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.ReviewerPaperRepository;
import com.iss.cms.core.repository.UserConferenceRepository;
import com.iss.cms.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaperService implements IPaperService{
    private static final Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    UserConferenceRepository userConferenceRepository;

    @Autowired
    ReviewerPaperRepository reviewerPaperRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Paper> getPapers() {
        logger.trace("PaperService - getPapers(): method entered");
        List<Paper> papers = paperRepository.findAll();
        logger.trace("PaperService - getPapers(): method finished");
        return papers;
    }

    @Override
    public Paper findPaperById(int id) throws CMSException {
        logger.trace("PaperService - findPaperById(): method entered -> id");
        Optional<Paper> paper = paperRepository.findById(id);
        if(paper.isEmpty()) {
            logger.trace("PaperService: Paper id does not exist!");
            throw new CMSException("Paper id does not exist!");
        }
        logger.trace("PaperService - getPapers(): method finished");
        return paper.get();
    }

    public void addPaper(int userConferenceId, String title, String keywords,String paperText, String abstractText, boolean finalized, boolean accepted) throws CMSException {
        logger.trace("PaperService - addPaper: method entered ->" +
                "conferenceId = " + userConferenceId + ", title = " + title + ", keywords = " + keywords +
                ", paperText = " + paperText + ", abstractText = " + abstractText + ", finalized" +
                finalized + ", accepted = " + accepted);

        Optional<UserConference> userConference = userConferenceRepository.findById(userConferenceId);
        if(userConference.isEmpty()) {
            logger.trace("PaperService: UserConference id does not exist!");
            throw new CMSException("UserConference id does not exist!");
        }
        List<Paper> papers = paperRepository.findAll();
        for(Paper paper: papers) {
            if(paper.getTitle().equals(title)){

                logger.trace("PaperService: Paper title already taken!");
                throw new CMSException("Paper title already taken!");
            }
        }

        Paper paper = new Paper(userConferenceId, title, keywords, paperText, abstractText, finalized, accepted);
        System.out.println(paper.toString());
        paperRepository.save(paper);

        logger.trace("PaperService - addPaper: method finished");
    }

    @Override
    public List<Paper> getPapersOfAUserInAConference(int userId, int conferenceId) {
        logger.trace("PaperService - getPapersOfAUserInAConference: method entered");

        int userConferenceId = 0;
        for(UserConference userConference: userConferenceRepository.findAll()) {
            if(userConference.getUserID() == userId && userConference.getConferenceID() == conferenceId
                    && userConference.getRole() == Role.AUTHOR) {
                userConferenceId = userConference.getId();
            }
        }

        List<Paper> papers = new ArrayList<>();
        for(Paper paper: paperRepository.findAll()) {
            if(paper.getUserConferenceId() == userConferenceId) {
                papers.add(paper);
            }
        }

        logger.trace("PaperService - getPapersOfAUserInAConference: method finished");
        return papers;
    }

    @Override
    public List<Paper> getFinalPapersFromAConference(int userId, int conferenceId) {
        logger.trace("PaperService - getFinalPapersFromAConference: method entered");

        UserConference uc = null;

        List<Integer> userConferenceIds = new ArrayList<>();
        for(UserConference userConference: userConferenceRepository.findAll()) {
            if(userConference.getConferenceID() == conferenceId) {
                userConferenceIds.add(userConference.getId());

                if(userConference.getUserID() == userId && userConference.getRole() == Role.AUTHOR) {
                    uc = userConference;
                }
            }
        }

        List<Paper> papers = new ArrayList<>();
        for(int id: userConferenceIds) {
            for(Paper paper: paperRepository.findAll()) {
                if(paper.getUserConferenceId() == id && paper.isFinalized()) {
                    if(uc != null) {
                        if(uc.getId() != paper.getUserConferenceId()) {
                            papers.add(paper);
                        }
                    } else {
                        papers.add(paper);
                    }
                }
            }
        }

        logger.trace("PaperService - getFinalPapersFromAConference: method finished");
        return papers;
    }

    @Override
    @Transactional
    public void updatePaper(int id, String title, String keywords, String paperText, String abstractText, boolean finalized, boolean accepted) {
        paperRepository.findById(id)
                .ifPresentOrElse(
                        (updatedPaper) -> {
                            updatedPaper.setTitle(title);
                            updatedPaper.setKeywords(keywords);
                            updatedPaper.setPaperText(paperText);
                            updatedPaper.setAbstractText(abstractText);
                            updatedPaper.setFinalized(finalized);
                            updatedPaper.setAccepted(accepted);
                        }
                        , () -> {
                            logger.trace("PaperService: UpdatePaper -> Paper does not exist!");
                            throw new CMSException("Paper does not exist!");
                        }
                );
    }

    @Override
    public List<Paper> getPapersReadyForReview(int userId, int conferenceId) {
        List<Paper> papers = new ArrayList<>();

        /*for(ReviewerPaper rp: reviewerPaperRepository.findAll()) {
           if(rp.isAssigned() && rp.getUserId() == userId) {
               AppUser appUser = userRepository.findById(rp.getUserId()).get();
               List<UserConference> userConferences = userConferenceRepository.findAllByUserID(appUser.getId());

               for(UserConference uc: userConferences) {
                   if(uc.getConferenceID() == conferenceId) {
                       papersReadyForReview.add(paperRepository.findById(rp.getPaperId()).get());
                   }
               }
           }
        }*/

        for(ReviewerPaper rp: reviewerPaperRepository.findAll()) {
            if(rp.isAssigned() && rp.getUserId() == userId) {
                papers.add(paperRepository.findById(rp.getPaperId()).get());
            }
        }

        List<Paper> papersReadyForReview = new ArrayList<>();
        for(Paper paper: papers) {
            UserConference userConference = userConferenceRepository.findById(paper.getUserConferenceId()).get();
            if(userConference.getConferenceID() == conferenceId) {
                papersReadyForReview.add(paper);
            }
        }

        System.out.println("Service: " + papersReadyForReview);
        return papersReadyForReview;
    }

    @Override
    public String getAuthorForAGivenPaper(int paperId) {
        String author = null;

        Paper paper = findPaperById(paperId);
        UserConference userConference = userConferenceRepository.findById(paper.getUserConferenceId()).get();
        AppUser user = userRepository.findById(userConference.getUserID()).get();

        author = user.getUsername();

        return "\"" + author + "\"";
    }

    @Override
    public int getAuthorIDForAGivenPaper(int paperId) {
        int authorID = -1;

        Paper paper = findPaperById(paperId);
        UserConference userConference = userConferenceRepository.findById(paper.getUserConferenceId()).get();
        AppUser user = userRepository.findById(userConference.getUserID()).get();

        authorID = user.getId();

        return authorID;
    }
}
