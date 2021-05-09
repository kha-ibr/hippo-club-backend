package com.khalid.fakebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String avatar;
    private Instant createdDate;
    private Boolean enabled;
}
