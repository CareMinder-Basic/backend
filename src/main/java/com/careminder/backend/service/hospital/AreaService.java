package com.careminder.backend.service.hospital;

import com.careminder.backend.dto.hospital.area.request.AreaCreateRequest;
import com.careminder.backend.dto.hospital.area.response.AreaIdResponse;
import com.careminder.backend.model.hospital.Area;
import com.careminder.backend.repository.hospital.AreaRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public AreaIdResponse createArea(AreaCreateRequest areaCreateRequest){
        Area savedArea = areaRepository.save(areaCreateRequest.toEntity());
        return AreaIdResponse.from(savedArea.getId());
    }
}
