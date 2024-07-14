package com.careminder.backend.dto.hospital.area.response;

public record AreaIdResponse(
        long areaId
) {

    public static AreaIdResponse from(final long areaId) {
        return new AreaIdResponse(areaId);
    }
}