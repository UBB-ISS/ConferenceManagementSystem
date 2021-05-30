package com.iss.cms.core.service;

import com.iss.cms.core.domain.Qualifier;
import com.iss.cms.core.domain.Result;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IResultService {
    List<Result> getAllForAuthor(int authorId);

    void addResult(int authorId, int paperId, Qualifier qualifier) throws CMSException;

    Result getResultByAuthorAndPaper(int authorId, int paperId, Qualifier qualifier);

    List<Result> getAllForAuthorAndConference(int authorId, int conferenceId);
}
