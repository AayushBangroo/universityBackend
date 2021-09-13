package com.practice.springboot.dto;

import com.practice.springboot.entity.Guardian;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardianDTO {

    private String name;

    private String email;

    private String mobile;

    public GuardianDTO(Guardian guardian) {
        this.name = guardian.getName();
        this.email = guardian.getEmail();
        this.mobile = guardian.getMobile();
    }

    public Guardian toEntity(){
        Guardian guardian = new Guardian();
        guardian.setEmail(this.getEmail());
        guardian.setMobile(this.getMobile());
        guardian.setName(this.getName());

        return guardian;
    }
}
