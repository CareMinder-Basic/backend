package com.careminder.backend.controller.account;

import com.careminder.backend.dto.account.PatientCreateRequest;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.service.account.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/patients")
@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public void createPatient(
            @CurrentUser final CustomUserDetails customUserDetails,
            @RequestBody final PatientCreateRequest patientCreateRequest){
        patientService.createPatient(customUserDetails, patientCreateRequest);
    }

    @PostMapping("/discharge")
    public void dischargePatient(
            @CurrentUser final CustomUserDetails customUserDetails){
        patientService.dischargePatient(customUserDetails);
    }

}
