package com.vsu.app.service;

import com.vsu.app.repository.CriteriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CriteriaService {
    private CriteriaRepository criteriaRepository;
}
