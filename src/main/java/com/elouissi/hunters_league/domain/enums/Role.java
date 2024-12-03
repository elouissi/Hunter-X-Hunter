package com.elouissi.hunters_league.domain.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN(Permission.CAN_MANAGE_SETTINGS, Permission.CAN_MANAGE_COMPETITIONS,
            Permission.CAN_MANAGE_SPECIES, Permission.CAN_MANAGE_USERS,
            Permission.CAN_SCORE, Permission.CAN_PARTICIPATE,
            Permission.CAN_VIEW_RANKINGS, Permission.CAN_VIEW_COMPETITIONS),

    JURY(Permission.CAN_SCORE, Permission.CAN_PARTICIPATE,
            Permission.CAN_VIEW_RANKINGS, Permission.CAN_VIEW_COMPETITIONS),

    MEMBER(Permission.CAN_PARTICIPATE, Permission.CAN_VIEW_RANKINGS,
            Permission.CAN_VIEW_COMPETITIONS);


    private final Set<Permission> permissions;

    Role(Permission... permissions) {
        this.permissions = new HashSet<>(Arrays.asList(permissions));
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}



