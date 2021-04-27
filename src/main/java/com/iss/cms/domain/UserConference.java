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
    private int id;

    private int conferenceID;
    private int conferenceSectionID;
    private int userID;
    private Role role;
    private int paperID;
    private boolean paid;
}