package com.elouissi.hunters_league.web.rest.controller;

import com.elouissi.hunters_league.web.rest.VM.RegisterVM;
import jakarta.servlet.RequestDispatcher;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    public ResponseEntity<String> register(@RequestBody @Valid RegisterVM registerVM){


        return new ResponseEntity<>(userDTO , HttpStatus.CREATED);

        a
    }

}
