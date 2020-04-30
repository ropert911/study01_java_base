package com.study.demo2jsonrpc.client.controller;

import com.study.demo2jsonrpc.client.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {
    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("{a}/{b}")
    public int add(@PathVariable(value = "a") int a, @PathVariable(value = "b") int b) {
        return calculatorService.add(a, b);
    }
}
