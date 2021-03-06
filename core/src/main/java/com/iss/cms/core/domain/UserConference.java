package com.iss.cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserConference implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int conferenceID;
    private int userID;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean paid;

    public UserConference(int userId, int conferenceId, Role role, boolean paid) {
        this.userID = userId;
        this.conferenceID = conferenceId;
        this.role = role;
        this.paid = paid;
    }
}
