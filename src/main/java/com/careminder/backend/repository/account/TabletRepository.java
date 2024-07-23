package com.careminder.backend.repository.account;

import com.careminder.backend.global.error.exception.NotFoundException;
import com.careminder.backend.model.account.Tablet;
import org.springframework.stereotype.Repository;

import static com.careminder.backend.global.constant.exception.TabletExceptionConstant.TABLET_NOT_FOUND_ERROR;

@Repository
public class TabletRepository {

    private final TabletJpaRepository tabletJpaRepository;

    public TabletRepository(TabletJpaRepository tabletJpaRepository) {
        this.tabletJpaRepository = tabletJpaRepository;
    }

    public Tablet save(final Tablet tablet){
         tabletJpaRepository.save(tablet);
        return tablet;
    }

//    public Tablet getByLoginId(final String loginId){
//        return tabletJpaRepository.findByLoginId(loginId).orElseThrow(() ->
//                new NotFoundException(WARD_NOT_FOUND_ERROR.message()));
//    }
//
    public Tablet getById(final Long id){
        return tabletJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException(TABLET_NOT_FOUND_ERROR.message()));
    }
}
