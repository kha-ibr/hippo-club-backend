package com.khalid.fakebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReq {
    private String imgUrl;
    private String postDescription;
}
