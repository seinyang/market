package com.example.swagger.controller;

import com.example.swagger.dto.gada.ClickWorkDto;
import com.example.swagger.dto.gada.HomeDto;
import com.example.swagger.service.WorkerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "메인")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/home")
    public ResponseEntity<List<HomeDto>> home(){

        List<HomeDto> homeData = workerService.home();
        return ResponseEntity.ok(homeData);

    }

    @GetMapping("/worker")
    public ResponseEntity<ClickWorkDto> getWorker(@RequestParam int id){

        ClickWorkDto workerData = workerService.getWorker(id);
        return ResponseEntity.ok(workerData);
    }
}
