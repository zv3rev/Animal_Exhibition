package com.vsu.app.service;

import com.vsu.app.repository.ExhibitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExhibitionService {
    private ExhibitionRepository exhibitionRepository;
}
