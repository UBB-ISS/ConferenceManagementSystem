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
public class Paper implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private int authorId;
    private int userConferenceId;
    private String title;
    private String keywords;
    private String paperText;
    private String abstractText;
    private boolean finalized;
    private boolean accepted;
}
