package com.careminder.backend.model.account;

public enum StaffRole {
    NURSE("Nurse"),
    ASSISTANT_NURSE("Assistant Nurse"),
    DOCTOR("Doctor"),
    WORKER("Worker");

    private final String displayName;

    StaffRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
