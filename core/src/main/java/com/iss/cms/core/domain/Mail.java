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
public class Mail implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    String fromUser;
    String toUser;
    String subject;
    String message;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setupCreatedAccountMail(String toUser, String username, String password) {
        this.fromUser = "conference.management.system2021@gmail.com";
        this.toUser = toUser;
        this.subject = "Successfully created Conference Management System account";
        this.message = "Hi,\n \t You have successfully created an account." +
                "\n\n\t" +"Username: " + username + " \n \tPassword: " + password + "\n\n With regards, CMS team";
    }
}
