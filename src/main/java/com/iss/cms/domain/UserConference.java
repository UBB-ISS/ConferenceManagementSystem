package com.iss.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserConference {
    @Id
    @GeneratedValue
    private Long id;

    private Long userID;
    private Long conferenceID;
    private Role role;
    private boolean paid;
    private Long paperID;
    private String conferenceSection;
}
