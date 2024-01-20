package com.singularity.home.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class HomeRestController {
    @GetMapping("/")
    public ResponseEntity<String> get() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        log.debug("## now: {}", now);
        return ResponseEntity.ok(now);
    }
}
