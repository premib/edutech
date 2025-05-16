package com.nameless.edutech.services.impl;

import com.nameless.edutech.models.ExternalHuman;
import com.nameless.edutech.repositories.ExternalHumanRepository;
import org.springframework.stereotype.Service;

@Service
public class ExternalHumanServiceImpl {
    private final ExternalHumanRepository externalHumanRepository;

    ExternalHumanServiceImpl(ExternalHumanRepository externalHumanRepository) {
        this.externalHumanRepository = externalHumanRepository;
    }

    ExternalHuman findExternalHumanById(Long id) {
        return externalHumanRepository.findById(id).orElseGet(ExternalHuman::new);
    }
}
