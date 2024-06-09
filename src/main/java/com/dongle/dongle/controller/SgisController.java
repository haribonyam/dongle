package com.dongle.dongle.controller;

import com.dongle.dongle.service.SgisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
@RequiredArgsConstructor
public class SgisController {

    private final SgisService sgisService;

    @GetMapping("/sido-info")
    public String getSidoInfo() {
        return sgisService.getSidoInfo();
    }

    @GetMapping("/sgg-info")
    public String getSggInfo(@RequestParam String cd) {
        return sgisService.getSggInfo(cd);
    }
}
