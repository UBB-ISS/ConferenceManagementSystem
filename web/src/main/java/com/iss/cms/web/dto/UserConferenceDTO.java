package com.iss.cms.web.dto;

import com.iss.cms.core.domain.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserConferenceDTO {
    private int id;
    private int conferenceID;
    // private int conferenceSectionID;
    private int userID;
    private Role role;
    // private int paperID;
    private boolean paid;
}
