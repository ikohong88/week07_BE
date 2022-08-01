package com.example.catchtable.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class SignUpRequestDto {

    private String id;

    @NotBlank
    @Length(min = 8, max = 16, message = " 최소 8 ~ 최대 16자")
    @Pattern(regexp = "^[A-Za-z]" ,message = "영문자 + 특수문자 필수 포함")
    private String pw;

    private String username;
}
