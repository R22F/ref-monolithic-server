package com.example.refmonolithicserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "SalesController",description = "SalesAPI-판매 정보")
public class SalesController {
}
