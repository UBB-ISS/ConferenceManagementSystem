package com.iss.cms.web.converter;

import com.iss.cms.core.domain.Paper;
import com.iss.cms.web.dto.PaperDTO;
import org.springframework.stereotype.Component;

@Component
public class PaperConverter extends BaseConverter<Paper, PaperDTO> {
    @Override
    public Paper convertDTOToModel(PaperDTO dto) {
        Paper model = new Paper();

        model.setId(dto.getId());
        model.setUserConferenceId(dto.getUserConferenceId());
        model.setTitle(dto.getTitle());
        model.setKeywords(dto.getKeywords());
        model.setPaperText(dto.getPaperText());
        model.setAbstractText(dto.getAbstractText());
        model.setFinalized(dto.isFinalized());
        model.setAccepted(dto.isAccepted());

        return model;
    }

    @Override
    public PaperDTO convertModelToDTO(Paper model) {
        PaperDTO dto = new PaperDTO();

        dto.setId(model.getId());
        dto.setUserConferenceId(model.getUserConferenceId());
        dto.setTitle(model.getTitle());
        dto.setKeywords(model.getKeywords());
        dto.setPaperText(model.getPaperText());
        dto.setAbstractText(model.getAbstractText());
        dto.setFinalized(model.isFinalized());
        dto.setAccepted(model.isAccepted());

        return dto;
    }
}
