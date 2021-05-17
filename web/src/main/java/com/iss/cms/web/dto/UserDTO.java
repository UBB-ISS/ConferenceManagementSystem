package com.iss.cms.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String username;
    private String website;
    private String affiliation;
    private String password;
}
