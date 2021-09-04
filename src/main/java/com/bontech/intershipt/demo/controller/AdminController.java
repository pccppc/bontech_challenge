package com.bontech.intershipt.demo.controller;

import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.usr.NormalUser;
import com.bontech.intershipt.demo.models.dto.ServiceModel;
import com.bontech.intershipt.demo.models.dto.UserModel;
import com.bontech.intershipt.demo.models.response.FailureBody;
import com.bontech.intershipt.demo.models.response.SuccessBody;
import com.bontech.intershipt.demo.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserModel user){
        try{
            NormalUser normalUser = adminService.saveUser(user);
            return ResponseEntity.ok(new SuccessBody<>(Map.of("message","user created successfully","user",normalUser)));
        }catch (RuntimeException e){
            FailureBody<Object> body = new FailureBody<>(Map.of("code","409","message","username already exists"));
            return ResponseEntity.status(409).body(body);
        }
    }

    @PostMapping("/addService")
    public ResponseEntity<Object> addService(@RequestBody ServiceModel service){
        Service service1 = adminService.saveService(service);
        return ResponseEntity.ok(new SuccessBody<>(Map.of("message","service created successfully","service",service1)));
    }


}
