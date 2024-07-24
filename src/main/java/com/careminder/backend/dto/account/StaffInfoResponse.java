package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Staff;
import com.careminder.backend.model.account.constant.StaffRole;

public record StaffInfoResponse(
        Long staffId,
        String name,
        StaffRole staffRole
) {

    public static StaffInfoResponse from(final Staff staff){
        return new StaffInfoResponse(staff.getId(), staff.getName(), staff.getStaffRole());
    }
}
