package com.careminder.backend.service.account;

import com.careminder.backend.model.account.constant.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BaseAuthServiceTest {

    @Autowired
    private BaseAuthService baseAuthService;

    private final String expectedStaffName = "간호사-1";
    private final String expectedWardName = "병동-1";

//    TODO: 실제 DB 조회하는 테스트 개선
    @Test
    public void testGetName() {
        // getName 메소드 호출
        String actualStaffName = baseAuthService.getName(1L, Role.STAFF);
        String actualWardName = baseAuthService.getName(1L, Role.WARD);

        // 예상된 이름과 실제 반환된 이름이 같은지 확인
        assertEquals(expectedStaffName, actualStaffName);
        assertEquals(expectedWardName, actualWardName);
    }
}