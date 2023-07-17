package com.gsmhrm.anything_back.domain.member.presentation.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInfoRequest {

    @Column(length = 30)
    private String intro;
}
