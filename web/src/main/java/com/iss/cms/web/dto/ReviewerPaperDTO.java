package com.iss.cms.web.dto;

import com.iss.cms.core.domain.Availability;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ReviewerPaperDTO {
    private int id;
    private int userId;
    private int paperId;
    private boolean assigned;
    private Availability availability;
}
