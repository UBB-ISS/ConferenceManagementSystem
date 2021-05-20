package com.iss.cms.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PaperDTO {
    private int id;
    private int userConferenceId;
    private String title;
    private String keywords;
    private String paperText;
    private String abstractText;
    private boolean finalized;
    private boolean accepted;
}
