package com.careminder.backend.model.account;

public enum Role {
    ADMIN("Admin"),
    WARD("Assistant Nurse"),
    STAFF("Staff");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
