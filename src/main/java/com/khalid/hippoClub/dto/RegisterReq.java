package com.khalid.hippoClub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
