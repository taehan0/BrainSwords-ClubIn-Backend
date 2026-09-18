package com.brainswords.clubin.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "로그인 아이디는 필수입니다.")
    @Size(max = 50, message = "로그인 아이디는 50자 이하여야 합니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
