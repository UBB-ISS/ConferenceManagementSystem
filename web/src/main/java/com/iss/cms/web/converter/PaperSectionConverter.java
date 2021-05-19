package com.iss.cms.web.converter;

import com.iss.cms.core.domain.PaperSection;
import com.iss.cms.web.dto.PaperSectionDTO;

public class PaperSectionConverter extends BaseConverter<PaperSection, PaperSectionDTO> {
    @Override
    public PaperSection convertDTOToModel(PaperSectionDTO dto) {
        PaperSection model = new PaperSection();

        model.setId(dto.getId());
        model.setPaperId(dto.getPaperId());
        model.setSectionId(dto.getSectionId());

        return model;
    }

    @Override
    public PaperSectionDTO convertModelToDTO(PaperSection model) {
        PaperSectionDTO dto = new PaperSectionDTO();

        dto.setId(model.getId());
        dto.setPaperId(model.getPaperId());
        dto.setSectionId(model.getSectionId());

        return null;
    }
}
