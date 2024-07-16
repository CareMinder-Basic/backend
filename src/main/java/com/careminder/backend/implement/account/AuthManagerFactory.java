package com.careminder.backend.implement.account;

import com.careminder.backend.model.account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 전략적 패턴을 이용한 이용할 빈 정하기
@Component
public class AuthManagerFactory {

    // 어떤 BaseAuthManger 구현체 빈을 가져올지 모호하기 때문에 Map 을 이용해 오류를 피함
    private final Map<Role, BaseAuthManager> authManagerMap = new HashMap<>();

    @Autowired
    public AuthManagerFactory(final ApplicationContext context) {
        // 모든 BaseAuthManager 빈을 가져와서 맵에 저장
        Map<String, BaseAuthManager> beans = context.getBeansOfType(BaseAuthManager.class);
        beans.forEach((name, bean) -> {
            if (bean instanceof WardAuthManager) {
                authManagerMap.put(Role.WARD, bean);
            } else if (bean instanceof StaffAuthManager) {
                authManagerMap.put(Role.STAFF, bean);
            }
        });
    }

    public BaseAuthManager getAuthManager(final Role role) {
        return authManagerMap.get(role);
    }
}
