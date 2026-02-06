package com.empresa.reverse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.reverse.model.Plan;
import com.empresa.reverse.repository.PlanRepository;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
public class PlanController {

    private final PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @GetMapping
    public ResponseEntity<List<Plan>> listarPlanes() {
        List<Plan> planes = planRepository.findAllActivosConServicios();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> obtenerPlan(@PathVariable Long id) {
        return planRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}