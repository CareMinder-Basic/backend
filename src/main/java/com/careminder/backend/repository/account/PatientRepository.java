package com.careminder.backend.repository.account;

import com.careminder.backend.model.account.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository {
    private final PatientJpaRepository patientJpaRepository;

    public PatientRepository(PatientJpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }

    public Patient save(final Patient patient){
        return patientJpaRepository.save(patient);
    }

//    public Tablet getByLoginId(final String loginId){
//        return tabletJpaRepository.findByLoginId(loginId).orElseThrow(() ->
//                new NotFoundException(WARD_NOT_FOUND_ERROR.message()));
//    }
//
//    public Tablet getById(final Long id){
//        return tabletJpaRepository.findById(id).orElseThrow(() ->
//                new NotFoundException(WARD_NOT_FOUND_ERROR.message()));
//    }
}
