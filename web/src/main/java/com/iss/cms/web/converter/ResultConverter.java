package com.iss.cms.web.converter;

import com.iss.cms.core.domain.Result;
import com.iss.cms.web.dto.ResultDTO;
import org.springframework.stereotype.Component;

@Component
public class ResultConverter extends BaseConverter<Result, ResultDTO> {
    @Override
    public Result convertDTOToModel(ResultDTO dto) {
        Result model = new Result();

        model.setId(dto.getId());
        model.setAuthorId(dto.getAuthorId());
        model.setPaperId(dto.getPaperId());
        model.setQualifier(dto.getQualifier());

        return model;
    }

    @Override
    public ResultDTO convertModelToDTO(Result model) {
        ResultDTO dto = new ResultDTO();

        dto.setId(model.getId());
        dto.setAuthorId(model.getAuthorId());
        dto.setPaperId(model.getPaperId());
        dto.setQualifier(model.getQualifier());

        return dto;
    }
}
