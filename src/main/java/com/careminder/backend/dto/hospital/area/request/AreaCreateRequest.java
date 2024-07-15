package com.careminder.backend.dto.hospital.area.request;

import com.careminder.backend.model.hospital.Area;
import com.careminder.backend.model.hospital.Area.AreaBuilder;
import lombok.Builder;

@Builder
public record AreaCreateRequest(
        String name,
        long wardId
) {

    public Area toEntity() {

        AreaBuilder AreaBuilder = Area.builder()
                .wardId(wardId)
                .name(name);

        return AreaBuilder.build();
    }
}
