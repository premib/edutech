package com.nameless.edutech.controllers;

import com.nameless.edutech.models.DTO.PupilDTO;
import com.nameless.edutech.services.PupilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pupils")
public class PupilController {

    private final PupilService pupilService;

    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping
    public List<PupilDTO> getAllPupils() {
        return pupilService.getAllPupils();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PupilDTO> getPupilById(@PathVariable Long id) {
        Optional<PupilDTO> pupil = pupilService.getPupilById(id);
        return pupil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PupilDTO createPupil(@RequestBody PupilDTO pupilDTO) {
        return pupilService.savePupil(pupilDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PupilDTO> updatePupil(@PathVariable Long id, @RequestBody PupilDTO pupilDTO) {
        try {
            PupilDTO updatedPupil = pupilService.updatePupil(id, pupilDTO);
            return ResponseEntity.ok(updatedPupil);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePupil(@PathVariable Long id) {
        pupilService.deletePupil(id);
        return ResponseEntity.noContent().build();
    }
}