package com.elouissi.hunters_league.domain.enums;

public enum Permission {
    // Permissions pour les administrateurs
    CAN_PARTICIPATE,
    CAN_VIEW_RANKINGS,

    // Permissions pour les membres
    CAN_VIEW_COMPETITIONS,
    CAN_SCORE,

    // Permissions pour les jurys
    CAN_MANAGE_COMPETITIONS,
    CAN_MANAGE_USERS,
    CAN_MANAGE_SPECIES,
    CAN_MANAGE_SETTINGS
}
