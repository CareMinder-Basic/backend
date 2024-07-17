package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Role;
import lombok.Builder;

@Builder
public record CurrentUserResponse(
        String name,
        Long accountId,
        Role role
) {
}
