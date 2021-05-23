package com.iss.cms.web.converter;

import com.iss.cms.core.domain.PaperReview;
import com.iss.cms.web.dto.PaperReviewDTO;
import org.springframework.stereotype.Component;

@Component
public class PaperReviewConverter extends BaseConverter<PaperReview, PaperReviewDTO>{
    @Override
    public PaperReview convertDTOToModel(PaperReviewDTO dto) {
        PaperReview model = new PaperReview();

        model.setId(dto.getId());
        model.setPaperId(dto.getPaperId());
        model.setReviewerId(dto.getReviewerId());
        model.setRecommendation(dto.getRecommendation());
        model.setQualifier(dto.getQualifier());

        return model;
    }

    @Override
    public PaperReviewDTO convertModelToDTO(PaperReview model) {
        PaperReviewDTO dto = new PaperReviewDTO();

        dto.setId(model.getId());
        dto.setPaperId(model.getPaperId());
        dto.setReviewerId(model.getReviewerId());
        dto.setRecommendation(model.getRecommendation());
        dto.setQualifier(model.getQualifier());

        return dto;
    }
}
