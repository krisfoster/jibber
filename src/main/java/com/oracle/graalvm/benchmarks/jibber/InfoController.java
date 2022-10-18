package com.oracle.graalvm.benchmarks.jibber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;


import jdk.jfr.Event;
import jdk.jfr.Description;
import jdk.jfr.Label;

/**
 * REST Controller which serves as an entry-point for requests for jibber nonsense verse.
 *
 */
@RestController
@RequestMapping("/info")
@Timed
public class InfoController {

    @RequestMapping
    ResponseEntity<String> jibber() {
        return ResponseEntity.ok("Version : 1.1");
    }
}

