package com.careminder.backend.controller.hospital;

import com.careminder.backend.dto.hospital.area.request.AreaCreateRequest;
import com.careminder.backend.dto.hospital.area.response.AreaIdResponse;
import com.careminder.backend.service.hospital.AreaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/area")
@RestController
public class AreaController {
    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public AreaIdResponse create(@RequestBody AreaCreateRequest areaCreateRequest){
        return areaService.createArea(areaCreateRequest);
    }
}
