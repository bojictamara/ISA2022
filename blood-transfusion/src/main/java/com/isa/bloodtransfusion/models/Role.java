package com.isa.bloodtransfusion.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;

@Data
public class Role implements GrantedAuthority {
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
