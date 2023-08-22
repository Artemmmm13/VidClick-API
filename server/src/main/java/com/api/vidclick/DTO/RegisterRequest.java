package com.api.vidclick.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private String email;
    private String creatorProfileImage;

}
