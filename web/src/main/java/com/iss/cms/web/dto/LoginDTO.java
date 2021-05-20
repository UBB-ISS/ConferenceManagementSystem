package com.iss.cms.web.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class LoginDTO implements Serializable {
    private String username;
    private String password;
}
