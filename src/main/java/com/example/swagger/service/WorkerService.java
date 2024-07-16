package com.example.swagger.service;

import com.example.swagger.dto.gada.ClickWorkDto;
import com.example.swagger.dto.gada.HomeDto;
import com.example.swagger.dto.gada.SendInfo;
import com.example.swagger.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final Mapper mapper;


    public List<HomeDto> home (){
        return mapper.workerHome();
    }

    public ClickWorkDto getWorker(int homeId){
        return mapper.workerApply(homeId);
    }

    public SendInfo apply(String name){
        return mapper.sendInfo(name);
    }

}
