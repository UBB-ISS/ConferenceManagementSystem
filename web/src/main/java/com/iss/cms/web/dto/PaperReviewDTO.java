package com.iss.cms.web.dto;

import com.iss.cms.core.domain.Qualifier;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PaperReviewDTO {
    private int id;
    private int reviewerId;
    private int paperId;
    private String recommendation;
    private Qualifier qualifier;
}
