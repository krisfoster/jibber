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
@RequestMapping("/jibber")
@Timed
public class JibberController {

    /**
     * Custom JFR Event
     */
    @Label("JibberwockyEvent")
    @Description("Used for recording events in our Jaberwocky Controller")
    static class JabberwockydEvent extends Event {
        // Message.
        @Label("Message")
        String message;
    }

    @Autowired
    Jabberwocky j;

    @RequestMapping
    ResponseEntity<String> jibber() {
        JabberwockydEvent event = new JabberwockydEvent();
        event.message = "Poem being generated";
        event.commit();
        return ResponseEntity.ok(j.generate());
    }

    @RequestMapping(value = "/{number}")
    ResponseEntity<String> jibberN(@PathVariable int number) {
        return ResponseEntity.ok(j.generate(number));
    }
}

