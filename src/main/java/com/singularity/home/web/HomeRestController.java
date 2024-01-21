package com.singularity.home.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class HomeRestController {
    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> get() throws UnknownHostException {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        Map<String, Object> res = new HashMap<>();
        log.debug("## now: {}", now);
        res.put("reqTime", now);

        InetAddress ipAddress = InetAddress.getLocalHost();
        res.put("ip", ipAddress.getHostAddress());
        return ResponseEntity.ok(res);
    }
    @GetMapping("/wait")
    public ResponseEntity<Map<String, Object>> wait(@RequestParam(defaultValue = "1000") Long time) throws InterruptedException {
        Map<String, Object> res = new HashMap<>();
        res.put("reqTime", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        log.debug("## wait-time: {}", time);
        Thread.sleep(time);
        res.put("resTime", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        res.put("time", time);
        return ResponseEntity.ok(res);
    }
}
