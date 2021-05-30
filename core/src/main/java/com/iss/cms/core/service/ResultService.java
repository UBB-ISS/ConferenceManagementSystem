package com.iss.cms.core.service;

import com.iss.cms.core.domain.*;
import com.iss.cms.core.exceptions.CMSException;
import com.iss.cms.core.repository.PaperRepository;
import com.iss.cms.core.repository.ResultRepository;
import com.iss.cms.core.repository.UserConferenceRepository;
import com.iss.cms.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService implements IResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConferenceRepository userConferenceRepository;

    @Override
    public List<Result> getAllForAuthor(int authorId) {
        List<Result> results = new ArrayList<>();
        for(Result result: resultRepository.findAll()) {
            if(result.getAuthorId() == authorId) {
                results.add(result);
            }
        }

        return results;
    }

    @Override
    public void addResult(int authorId, int paperId, Qualifier qualifier) throws CMSException {
        Optional<Paper> paper = paperRepository.findById(paperId);
        if(paper.isEmpty()) {
            throw new CMSException("This paper does not exist!");
        }

        Optional<AppUser> user = userRepository.findById(authorId);
        if(user.isEmpty()) {
            throw new CMSException("This user does not exist!");
        }

        Result result = new Result(authorId, paperId, qualifier);
        resultRepository.save(result);
    }

    @Override
    public Result getResultByAuthorAndPaper(int authorId, int paperId, Qualifier qualifier) throws CMSException {
        Result result = null;

        for(Result r: resultRepository.findAll()) {
            if(r.getAuthorId() == authorId && r.getPaperId() == paperId && r.getQualifier() == qualifier) {
                result = r;
            }
        }

        return result;
    }

    @Override
    public List<Result> getAllForAuthorAndConference(int authorId, int conferenceId) {
        List<Result> results = new ArrayList<>();
        for(Result result: resultRepository.findAll()) {
            if(result.getAuthorId() == authorId) {
                Paper paper = paperRepository.findById(result.getPaperId()).get();
                UserConference userConference = userConferenceRepository.findById(paper.getUserConferenceId()).get();

                if(userConference.getConferenceID() == conferenceId) {
                    results.add(result);
                }
            }
        }

        return results;
    }
}
