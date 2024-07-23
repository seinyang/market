package com.example.swagger.controller;

import com.example.swagger.dto.gada.*;
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
    public ResponseEntity<SupportResponse> getWorker(@RequestParam("userId") String userId, @RequestParam("homeId") int homeId){

        try {
            workerService.applySupport(userId, homeId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("User not found")) {
                return ResponseEntity.status(404).build();
            } else if (e.getMessage().equals("User is not certified")) {
                return ResponseEntity.status(403).build();
            } else if (e.getMessage().equals("User has already applied for this homeId")) {
                return ResponseEntity.status(409).build();
            } else {
                return ResponseEntity.status(500).build();
            }
        }
    }

    @Operation(summary = "지원내역", description = "지원내역의 API")
    @GetMapping("/support")
    public ResponseEntity<List<SupportResponse>> getSupport(@RequestParam("userName") String userName){

        if (userName.isEmpty()){
            return ResponseEntity.status(400).body(null); // 403 Forbidden
        }
        List<SupportResponse> supportList = workerService.support(userName);

        if (supportList.isEmpty()) {
            return ResponseEntity.status(404).body(null); // 404 Not Found
        }

        return ResponseEntity.ok(supportList);
    }

    @Operation(summary = "마이페이지 조회", description = "사용자의 마이페이지 정보를 조회하는 API")
    @GetMapping("/mypage")
    public ResponseEntity<MyPageDTO> getMyPage(@RequestParam("userName") String userName) {
        if ((userName.isEmpty())){
            return ResponseEntity.status(400).body(null); // 403 Forbidden
        }
        try {
            MyPageDTO myPageInfo = workerService.getMyPage(userName);
            return ResponseEntity.ok(myPageInfo);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("User not found")) {
                return ResponseEntity.status(404).build(); // 404 Not Found
            } else {
                return ResponseEntity.status(500).build(); // 500 Internal Server Error
            }
        }
    }




}
