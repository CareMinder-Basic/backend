package com.careminder.backend.init;

import com.careminder.backend.implement.account.StaffAuthManager;
import com.careminder.backend.model.account.Staff;
import com.careminder.backend.repository.account.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final StaffRepository staffRepository;

    public DataInit(final StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        initializeDatabase();
    }

    private void initializeDatabase() {
        Staff staff = Staff.builder()
                .name("간호사-1")
                .loginId("1")
                .password("1")
                .phoneNumber("010-1234-5678")
                .build();

        staffRepository.save(staff);
    }
}
