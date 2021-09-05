package com.bontech.intershipt.demo.controller;
import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.ServiceUsesHistory;
import com.bontech.intershipt.demo.models.dto.ServiceModel;
import com.bontech.intershipt.demo.models.dto.UserIdServiceIdModel;
import com.bontech.intershipt.demo.models.response.FailureBody;
import com.bontech.intershipt.demo.models.response.SuccessBody;
import com.bontech.intershipt.demo.service.base.UserService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/grantedService/{id}")
    public void grantedService(HttpServletResponse response,@PathVariable Long id) throws IOException {
        response.setContentType("application/json");
        try{
            List<Service> grantingService = userService.findGrantingService(id);
            SuccessBody<Object> body = new SuccessBody<>(grantingService);
            response.setStatus(200);
            new ObjectMapper().writeValue(response.getOutputStream(),body);
        }catch (RuntimeException e){
            response.setStatus(404);
            FailureBody<Object> body = new FailureBody<>(Map.of("code","404","message",e.getMessage()));
            new ObjectMapper().writeValue(response.getOutputStream(),body);
        }
    }

    @GetMapping(value = "/activeService/{id}")
    public ResponseEntity<Object> activeService(@PathVariable Long id){
        try {
            List<Service> activeService = userService.findActiveService(id);
            return ResponseEntity.ok(new SuccessBody<>(activeService));
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("code","404","message",e.getMessage())));
        }
    }

    @GetMapping(value = "/reportOfServiceUsage/{id}")
    public ResponseEntity<Object> getReportOfServiceUsage(@PathVariable Long id){
        try{
            List<ServiceUsesHistory> reportOfServiceUsage = userService.getReportOfServiceUsage(id);
            return ResponseEntity.ok(new SuccessBody<>(reportOfServiceUsage));
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(new FailureBody<>(Map.of("code","404","message",e.getMessage())));
        }
    }

    @PostMapping(value = "/useService")
    public ResponseEntity<Object> useService(@RequestBody UserIdServiceIdModel uisim){
        try{
            ServiceModel serviceModel = userService.useService(uisim.getServiceId(), uisim.getUserId());
            return ResponseEntity.ok(new SuccessBody<>(serviceModel));
        }catch (RuntimeException e){
            return ResponseEntity.status(400).body(new FailureBody<>(Map.of("code","400","message",e.getMessage())));
        }
    }
}
