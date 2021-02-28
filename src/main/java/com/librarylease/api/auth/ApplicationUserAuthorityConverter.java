package com.librarylease.api.auth;

import javax.persistence.AttributeConverter;

public class ApplicationUserAuthorityConverter implements AttributeConverter<ApplicationUserAuthority, String> {
    @Override
    public String convertToDatabaseColumn(ApplicationUserAuthority applicationUserAuthority) {
        return applicationUserAuthority.getRole();
    }

    @Override
    public ApplicationUserAuthority convertToEntityAttribute(String s) {
        return ApplicationUserAuthority.fromRoleName(s);
    }
}

