package com.dkob.revenue.controllers;

import com.dkob.revenue.model.RevenueRequest;
import com.dkob.revenue.services.RevenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RevenueController {

    private RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @PostMapping("calculate")
    public ResponseEntity<?> calculateRevenue(@Valid @RequestBody RevenueRequest revenueRequest) {
        return ResponseEntity.ok(revenueService.calculateRevenue(revenueRequest));
    }

    @GetMapping
    public void test() {
        revenueService.calculateRevenue(new RevenueRequest());
    }
}
