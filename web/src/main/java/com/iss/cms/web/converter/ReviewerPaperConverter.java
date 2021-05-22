package com.iss.cms.web.converter;

import com.iss.cms.core.domain.ReviewerPaper;
import com.iss.cms.web.dto.ReviewerPaperDTO;
import org.springframework.stereotype.Component;

@Component
public class ReviewerPaperConverter extends BaseConverter<ReviewerPaper, ReviewerPaperDTO> {
    @Override
    public ReviewerPaper convertDTOToModel(ReviewerPaperDTO dto) {
        ReviewerPaper model = new ReviewerPaper();

        model.setId(dto.getId());
        model.setPaperId(dto.getPaperId());
        model.setUserId(dto.getUserId());
        model.setAssigned(dto.isAssigned());
        model.setAvailability(dto.getAvailability());

        return model;
    }

    @Override
    public ReviewerPaperDTO convertModelToDTO(ReviewerPaper model) {
        ReviewerPaperDTO dto = new ReviewerPaperDTO();

        dto.setId(model.getId());
        dto.setPaperId(model.getPaperId());
        dto.setUserId(model.getUserId());
        dto.setAssigned(model.isAssigned());
        dto.setAvailability(model.getAvailability());

        return dto;
    }
}
