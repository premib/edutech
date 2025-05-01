package com.nameless.edutech.services.impl;

import com.nameless.edutech.models.DTO.PupilDTO;
import com.nameless.edutech.models.Pupil;
import com.nameless.edutech.repositories.PupilRepository;
import com.nameless.edutech.services.PupilService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PupilServiceImpl implements PupilService {

    private final PupilRepository pupilRepository;

    public PupilServiceImpl(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
    }

    public List<PupilDTO> getAllPupils() {
        return pupilRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PupilDTO> getPupilById(Long id) {
        return pupilRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public PupilDTO savePupil(PupilDTO pupilDTO) {
        Pupil pupil = convertToEntity(pupilDTO);
        Pupil savedPupil = pupilRepository.save(pupil);

        return convertToDTO(savedPupil);
    }

    @Override
    public PupilDTO updatePupil(Long id, PupilDTO pupilDTO) {
        Pupil pupil = pupilRepository.findById(id).orElseThrow();

        pupil.setFirstName(pupilDTO.firstName());
        pupil.setLastName(pupilDTO.lastName());
        pupil.setDob(pupilDTO.dob());
        pupil.setClassroom(pupilDTO.classroom());

        Pupil updatedPupil = pupilRepository.save(pupil);

        return convertToDTO(updatedPupil);
    }

    @Override
    public void deletePupil(Long id) {
        pupilRepository.deleteById(id);
    }

    private PupilDTO convertToDTO(Pupil pupil) {
        return new PupilDTO(pupil.getId(), pupil.getFirstName(), pupil.getLastName(), pupil.getDob(), pupil.getClassroom());
    }

    private Pupil convertToEntity(PupilDTO pupilDTO) {
        Pupil pupil = new Pupil();
        pupil.setFirstName(pupilDTO.firstName());
        pupil.setLastName(pupilDTO.lastName());
        pupil.setDob(pupilDTO.dob());
        pupil.setClassroom(pupilDTO.classroom());

        return pupil;
    }
}
