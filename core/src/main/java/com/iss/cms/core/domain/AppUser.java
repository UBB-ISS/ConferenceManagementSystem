package com.iss.cms.core.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class AppUser implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;
    private String username;
    private String website;
    private String affiliation;
    private String password;

    public AppUser(String name, String email, String username, String website, String affiliation, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.website = website;
        this.affiliation = affiliation;
        this.password = password;
    }
}
