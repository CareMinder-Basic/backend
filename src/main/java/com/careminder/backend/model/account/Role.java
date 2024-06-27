package com.careminder.backend.model.account;

public enum Role {
    NURSE("Nurse"),
    ASSISTANT_NURSE("Assistant Nurse"),
    DOCTOR("Doctor"),
    STAFF("Staff");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
