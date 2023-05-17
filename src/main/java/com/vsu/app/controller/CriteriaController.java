package com.vsu.app.controller;

import com.vsu.app.service.CriteriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CriteriaController {
    private CriteriaService criteriaService;
}
