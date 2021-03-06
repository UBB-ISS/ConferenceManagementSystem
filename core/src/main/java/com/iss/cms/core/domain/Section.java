package com.iss.cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Section implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int conferenceID;

    public Section(String name, int conferenceId) {
        this.name = name;
        this.conferenceID = conferenceId;
    }
}
