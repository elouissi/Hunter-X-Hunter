package com.elouissi.hunters_league.domain.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN(Permission.ADMIN_READ, Permission.ADMIN_WRITE,
            Permission.MEMBER_READ, Permission.MEMBER_WRITE,
            Permission.JURY_READ, Permission.JURY_WRITE),

    MEMBER(Permission.MEMBER_READ, Permission.MEMBER_WRITE),

    JURY(Permission.JURY_READ, Permission.JURY_WRITE);

    private final Set<Permission> permissions;

    Role(Permission... permissions) {
        this.permissions = new HashSet<>(Arrays.asList(permissions));
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}



