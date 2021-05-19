package com.iss.cms.core.service;

import com.iss.cms.core.domain.Paper;
import com.iss.cms.core.domain.Role;
import com.iss.cms.core.domain.UserConference;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.UserConferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaperService implements IPaperService{
    private static final Logger logger = LoggerFactory.getLogger(PaperService.class);

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    UserConferenceRepository userConferenceRepository;

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
}
