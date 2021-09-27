package org.pepo.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@Scope("request")
public class DemoController {

    @Autowired
    BuildProperties buildProperties;

    @GetMapping("/version")
    public ResponseEntity<BuildProperties> getVersion() {
        ResponseEntity<BuildProperties> _response = new ResponseEntity<>(buildProperties, HttpStatus.OK);
        return _response;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> getCalculation() {
        Random ran = new Random();
        int x = ran.nextInt(10000000) + 10000000;
        int i=0;
        double y=0;
        for (i=0;i<x;i++) {
            y+=Math.sqrt(i)+1.0;
        }
        ResponseEntity<Double> _response = new ResponseEntity<>(y, HttpStatus.OK);
        return _response;
    }
}
