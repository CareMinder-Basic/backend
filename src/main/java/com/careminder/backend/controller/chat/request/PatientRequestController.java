package com.careminder.backend.controller.chat.request;

import com.careminder.backend.dto.chat.request.PatientRequestResponse;
import com.careminder.backend.dto.chat.subscription.SubscriptionRequest;
import com.careminder.backend.dto.chat.subscription.UnsubscribeRequest;
import com.careminder.backend.dto.chat.request.PatientRequestAppendRequest;
import com.careminder.backend.global.annotation.CurrentUser;
import com.careminder.backend.global.auth.CustomUserDetails;
import com.careminder.backend.global.response.CollectionApiResponse;
import com.careminder.backend.service.chat.subscription.SubscriptionService;
import com.careminder.backend.service.chat.request.PatientRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/patient-request")
@RestController
public class PatientRequestController {

    private final PatientRequestService patientRequestService;
    private final SubscriptionService subscriptionService;

    public PatientRequestController(PatientRequestService patientRequestService,
                                    SubscriptionService subscriptionService) {
        this.patientRequestService = patientRequestService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public void createPatientRequest(@CurrentUser final CustomUserDetails customUserDetails,
                                     @RequestBody final PatientRequestAppendRequest patientRequestAppendRequest){
        patientRequestService.append(customUserDetails.getAccountId(), patientRequestAppendRequest);
    }

    @GetMapping
    public CollectionApiResponse<PatientRequestResponse> getAllPatientRequests(){
        return CollectionApiResponse.from(patientRequestService.getAllPatientRequest());
    }

    @GetMapping("/check-subscribe")
    public ResponseEntity<Boolean> checkSubscribe(@CurrentUser final CustomUserDetails customUserDetails,
                                                  @RequestParam final long patientRequestId){
        return ResponseEntity.ok(subscriptionService.isSubscribed(customUserDetails.getAccountId(), patientRequestId));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@CurrentUser final CustomUserDetails customUserDetails, @RequestBody final SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribe(customUserDetails, subscriptionRequest);
        return ResponseEntity.ok("채팅방 입장 완료.");
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@CurrentUser final CustomUserDetails cu, @RequestBody final UnsubscribeRequest unsubscribeRequest) {
        subscriptionService.unsubscribe(cu, unsubscribeRequest);
        return ResponseEntity.ok("채팅방 탈퇴 완료.");
    }
}
