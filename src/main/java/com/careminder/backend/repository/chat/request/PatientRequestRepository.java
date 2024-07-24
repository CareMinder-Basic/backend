package com.careminder.backend.repository.chat.request;

import com.careminder.backend.dto.chat.request.PatientRequestResponse;
import com.careminder.backend.dto.chat.request.QPatientRequestResponse;
import com.careminder.backend.model.chat.request.PatientRequest;
import com.careminder.backend.model.chat.request.QPatientRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRequestRepository {

    private final PatientRequestJpaRepository patientRequestJpaRepository;
    private final JPAQueryFactory queryFactory;

    public PatientRequestRepository(PatientRequestJpaRepository patientRequestJpaRepository, JPAQueryFactory queryFactory) {
        this.patientRequestJpaRepository = patientRequestJpaRepository;
        this.queryFactory = queryFactory;
    }

    public void save(final PatientRequest patientRequest){
        patientRequestJpaRepository.save(patientRequest);
    }

    public List<PatientRequestResponse> getPatientRequests() {
        QPatientRequest patientRequest = QPatientRequest.patientRequest;

        return queryFactory
                .select(new QPatientRequestResponse(
                        patientRequest.id,
                        patientRequest.status,
                        patientRequest.content,
                        patientRequest.staffId,
                        patientRequest.tabletId,
                        patientRequest.createdAt
                ))
                .from(patientRequest)
                .orderBy(patientRequest.createdAt.desc())
                .fetch();
    }
}
