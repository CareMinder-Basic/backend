package com.careminder.backend.dto.account;

import com.careminder.backend.model.account.Tablet;

public record TabletCrateRequest(
        String tabletName,
        long areaId
) {
    public Tablet toEntity(final Long wardId) {
        Tablet.TabletBuilder tabletBuilder = Tablet.builder()
                .tabletName(tabletName)
                .areaId(areaId)
                .wardId(wardId);

        return tabletBuilder.build();
    }
}
