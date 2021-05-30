package com.iss.cms.web.controller;

import com.iss.cms.core.domain.Qualifier;
import com.iss.cms.core.domain.Result;
import com.iss.cms.core.service.IResultService;
import com.iss.cms.web.converter.ResultConverter;
import com.iss.cms.web.dto.ResultDTO;
import com.iss.cms.web.dto.ResultsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResultController {

    @Autowired
    private IResultService resultService;

    @Autowired
    private ResultConverter resultConverter;

    @RequestMapping(value="/allResultsForAuthor/{authorId}")
    public ResultsDTO getAllForAuthor(@PathVariable int authorId) {
        return new ResultsDTO(resultConverter.convertModelsToDTOs(resultService.getAllForAuthor(authorId)));
    }

    @RequestMapping(value="/addResult", method=RequestMethod.POST)
    public void addResult(@RequestBody ResultDTO resultDTO) {
        Result result = resultConverter.convertDTOToModel(resultDTO);
        resultService.addResult(result.getAuthorId(), result.getPaperId(), result.getQualifier());
    }

    @RequestMapping(value="/getResultByAuthorAndPaper/{authorId}/{paperId}/{qualifier}")
    public ResultDTO getResultByAuthorAndPaper(@PathVariable int authorId, @PathVariable int paperId, @PathVariable Qualifier qualifier) {
        ResultDTO resultDTO = null;
        Result result = resultService.getResultByAuthorAndPaper(authorId, paperId, qualifier);
        if(result != null) resultDTO = resultConverter.convertModelToDTO(result);
        return resultDTO;
    }

    @RequestMapping(value="/getAllForAuthorAndConference/{authorId}/{conferenceId}")
    public ResultsDTO getAllForAuthorAndConference(@PathVariable int authorId, @PathVariable int conferenceId) {
        return new ResultsDTO(resultConverter.convertModelsToDTOs(resultService.getAllForAuthorAndConference(authorId, conferenceId)));
    }
}
