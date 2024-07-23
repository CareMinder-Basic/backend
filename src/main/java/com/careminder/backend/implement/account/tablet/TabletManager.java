package com.careminder.backend.implement.account.tablet;

import com.careminder.backend.global.annotation.Implement;
import com.careminder.backend.model.account.Tablet;
import com.careminder.backend.repository.account.TabletRepository;
import org.springframework.transaction.annotation.Transactional;

@Implement
public class TabletManager {
    private final TabletRepository tabletRepository;

    public TabletManager(final TabletRepository tabletRepository) {
        this.tabletRepository = tabletRepository;
    }

    @Transactional(readOnly = true)
    public Tablet getById(final Long tabletId){
        return tabletRepository.getById(tabletId);
    }


}
