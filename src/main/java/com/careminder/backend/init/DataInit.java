package com.careminder.backend.init;

import com.careminder.backend.implement.account.StaffAuthManager;
import com.careminder.backend.model.account.Staff;
import com.careminder.backend.model.account.Ward;
import com.careminder.backend.repository.account.StaffRepository;
import com.careminder.backend.repository.account.WardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final StaffRepository staffRepository;
    private final WardRepository wardRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(final StaffRepository staffRepository,
                    final WardRepository wardRepository,
                    final PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.wardRepository = wardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(final String... args) throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() {
        Staff staff = Staff.builder()
                .name("간호사-1")
                .loginId("1")
                .password(passwordEncoder.encode("1"))
                .phoneNumber("010-1234-5678")
                .build();

        staffRepository.save(staff);

        Ward ward = Ward.builder()
                .wardName("병동-1")
                .loginId("1")
                .password(passwordEncoder.encode("1"))
                .managerName("병동관리자 이름")
                .build();

        wardRepository.save(ward);
    }
}
