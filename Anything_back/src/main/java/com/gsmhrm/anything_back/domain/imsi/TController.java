package com.gsmhrm.anything_back.domain.imsi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/list")
public class TController {

    @GetMapping
    public ResponseEntity<String> returnList() {
        return new ResponseEntity<>("1" , HttpStatus.OK);
    }
}
