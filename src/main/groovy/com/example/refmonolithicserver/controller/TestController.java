package com.example.refmonolithicserver.controller;

import com.example.refmonolithicserver.dto.DummyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1")
@ApiResponse(description = "Test Controller")
public class TestController {

    @GetMapping(value = "/test")
    @Operation(summary = "Return dummy response")
    public ResponseEntity<DummyDto> getDummyDtoResponseEntity(){
        DummyDto res = new DummyDto(
                1L,
                "q1w2e3r4",
                "q1w2e3r4",
                "sungwon",
                LocalDate.now(),
                "csw",
                "sungwon@sungwon"
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @PostMapping(value = "/test")
    @Operation(summary = "Return the request verbatim")
    public ResponseEntity<String> createDummyDtoResponseEntity(@RequestBody DummyDto dummyDto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Entity Id "+dummyDto.id()+" created");
    }

    @PutMapping(value = "/test")
    @Operation(summary = "Return the modified entity id")
    public ResponseEntity<String> putDummyDtoResponseEntity(@RequestBody DummyDto dummyString){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Entity Id "+dummyString.id()+" modified");
    }

    @DeleteMapping(value = "/test")
    @Operation(summary = "Return the deleted entity id")
    public ResponseEntity<?> dummyDtoResponseEntity(@RequestBody Long id){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Entity Id "+id+" deleted");
    }
}
