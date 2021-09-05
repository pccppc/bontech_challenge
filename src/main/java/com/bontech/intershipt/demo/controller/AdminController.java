package com.bontech.intershipt.demo.controller;

import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.usr.NormalUser;
import com.bontech.intershipt.demo.models.dto.IdModel;
import com.bontech.intershipt.demo.models.dto.ServiceModel;
import com.bontech.intershipt.demo.models.dto.UserModel;
import com.bontech.intershipt.demo.models.dto.UserUpdateModel;
import com.bontech.intershipt.demo.models.response.FailureBody;
import com.bontech.intershipt.demo.models.response.SuccessBody;
import com.bontech.intershipt.demo.service.base.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserModel user){
        try{
            NormalUser normalUser = adminService.saveUser(user);
            return ResponseEntity
                    .ok(new SuccessBody<>(Map.of("message","user created successfully","user",normalUser)));
        }catch (RuntimeException e){
            FailureBody<Object> body = new FailureBody<>(Map.of("code","409","message","username already exists"));
            return ResponseEntity.status(409).body(body);
        }
    }


    @PutMapping(value = "/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateModel user,@RequestParam Long id){
        try {
            adminService.updateNormalUserById(user, id);
            return ResponseEntity
                    .ok(new SuccessBody<>(Map.of("code","200","message","user was updated successfully")));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(404).body(new FailureBody<>(Map.of("code","404","message","user not found")));
        }
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<Object> deleteUser(@RequestBody IdModel idModel){
        try{
            adminService.deleteUserById(idModel.getId());
            return ResponseEntity
                    .ok(new SuccessBody<>(Map.of("code","200","message","user successfully removed")));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(404).body(new FailureBody<>(Map.of("code","404","message","user not found")));
        }
    }

    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        Optional<NormalUser> userById = adminService.findUserById(id);
        if (userById.isPresent()){
            return ResponseEntity.ok(new SuccessBody<>(Map.of("code", "200", "user", userById.get())));
        }
        else return ResponseEntity.status(404).body(new FailureBody<>(Map.of("code", "404", "message", "user not found")));
    }

    @PostMapping(value = "/addService")
    public ResponseEntity<Object> addService(@RequestBody ServiceModel service){
        Service service1 = adminService.saveService(service);
        return ResponseEntity.ok(new SuccessBody<>(Map.of("message","service created successfully","service",service1)));
    }




    @PutMapping(value = "/updateService")
    public ResponseEntity<Object> updateService(@RequestBody ServiceModel service,@RequestParam Long id){
        try {
            adminService.updateServiceById(service,id);
            return ResponseEntity
                    .ok(new SuccessBody<>(Map.of("code","200","message","service was updated successfully")));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(404).body(new FailureBody<>(Map.of("code","404","message","service not found")));
        }
    }

    @DeleteMapping(value = "/deleteService")
    public ResponseEntity<Object> deleteService(@RequestBody IdModel idModel){
        try{
            adminService.deleteServiceById(idModel.getId());
            return ResponseEntity
                    .ok(new SuccessBody<>(Map.of("code","200","message","service successfully removed")));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(404).body(new FailureBody<>(Map.of("code","404","message","service not found")));
        }
    }

    @GetMapping(value = "/getService/{id}")
    public ResponseEntity<Object> getService(@PathVariable Long id){
        Optional<Service> serviceById = adminService.findServiceById(id);
        return serviceById.<ResponseEntity<Object>>map(service ->
                ResponseEntity.ok(Map.of("code", "200", "service", service)))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(Map.of("code", "404", "message", "service not found")));
    }

}
