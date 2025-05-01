package com.nameless.edutech.services;

import com.nameless.edutech.models.DTO.PupilDTO;

import java.util.List;
import java.util.Optional;

public interface PupilService {
    List<PupilDTO> getAllPupils();
    Optional<PupilDTO> getPupilById(Long id);
    PupilDTO savePupil(PupilDTO pupilDTO);
    PupilDTO updatePupil(Long id, PupilDTO pupilDTO);
    void deletePupil(Long id);
}
