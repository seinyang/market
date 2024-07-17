package com.example.swagger.controller;

import com.example.swagger.dto.gada.ClickWorkDto;
import com.example.swagger.dto.gada.HomeDto;
import com.example.swagger.dto.gada.SendInfo;
import com.example.swagger.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "홈화면", description = " 앱에 들어오자마자 보여주는 홈 API")
    @GetMapping("/home")
    public ResponseEntity<List<HomeDto>> home(){

        List<HomeDto> homeData = workerService.home();
        return ResponseEntity.ok(homeData);

    }
    @Operation(summary = "일자리 클릭", description = "클릭했을때 클릭한 일자리 보여주기")
    @GetMapping("/worker")
    public ResponseEntity<ClickWorkDto> getWorker(@RequestParam("homeId") int homeId){

        ClickWorkDto workerData = workerService.getWorker(homeId);

        if (workerData == null) { // 또는 workerData.isEmpty() 등으로 체크 가능
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }

        return ResponseEntity.ok(workerData); // 200 OK
    }

    @Operation(summary = "지원하기", description = "지원하기 클릭했을때의 API")
    @PostMapping("/apply")
    public ResponseEntity<SendInfo> getWorker(@RequestBody SendInfo sendInfo){
        System.out.println(sendInfo); // 디버깅용

        // 인증서 여부 체크
        if (!sendInfo.isCertification()) {  // certification이 false일 경우
            return ResponseEntity.status(403).body(null); // 403 Forbidden
        }

        SendInfo applyData = workerService.apply(sendInfo.getName());

        if (applyData == null) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }

        return ResponseEntity.ok(applyData); // 200 OK
    }
}
