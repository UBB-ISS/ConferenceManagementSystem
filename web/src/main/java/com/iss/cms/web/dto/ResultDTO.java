package com.iss.cms.web.dto;

import com.iss.cms.core.domain.Qualifier;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class ResultDTO {
    private int id;
    private int authorId;
    private int paperId;
    private Qualifier qualifier;
}
