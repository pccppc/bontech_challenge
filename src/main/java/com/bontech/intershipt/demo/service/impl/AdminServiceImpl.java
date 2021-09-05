package com.bontech.intershipt.demo.service.impl;

import com.bontech.intershipt.demo.models.dto.*;
import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.ServiceActivationDate;
import com.bontech.intershipt.demo.models.db.ServiceUsesHistory;
import com.bontech.intershipt.demo.models.db.usr.NormalUser;
import com.bontech.intershipt.demo.repositories.*;
import com.bontech.intershipt.demo.service.base.AdminService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final NormalUserRepository userRepository;
    private final NormalUserServiceRepository userServiceRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceUsesHistoryRepository usesHistoryRepository;
    private final ServiceActivationDateRepository activationDateRepository;

    public AdminServiceImpl(NormalUserRepository userRepository, NormalUserServiceRepository userServiceRepository, ServiceRepository serviceRepository, ServiceUsesHistoryRepository usesHistoryRepository, ServiceActivationDateRepository activationDateRepository) {
        this.userRepository = userRepository;
        this.userServiceRepository = userServiceRepository;
        this.serviceRepository = serviceRepository;
        this.usesHistoryRepository = usesHistoryRepository;
        this.activationDateRepository = activationDateRepository;
    }

    @Override
    public NormalUser saveUser(UserModel user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            NormalUser normalUser = NormalUser.builder().username(user.getUsername()).password(user.getPassword())
                    .balance(user.getBalance()).build();
            NormalUser save = userRepository.save(normalUser);
            log.info("Adding user with id : " + save.getId());
            return normalUser;
        }
        else throw new RuntimeException("this username already exists");
    }

    @Override
    public void updateNormalUserById(UserUpdateModel userModel, Long id) {
        if (userRepository.existsById(id)) {
            userRepository.updateUserById(userModel.getUsername(), userModel.getPassword(), id);
            log.info("update user with id : " + id);
        }else throw new RuntimeException("user not found");
    }

    @Override
    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            log.info("Deleting user services with userID : " + id);
            userServiceRepository.deleteAllByUserId(id);
            userRepository.deleteNormalUserById(id);
            log.info("delete user with id :" + id);
        }else throw new RuntimeException("user not found");
    }

    @Override
    public Optional<NormalUser> findUserById(Long id) {
        log.info("fetching user with id : " + id);
        return userRepository.findById(id);
    }

    @Override
    public Service saveService(ServiceModel service) {
        Service save = serviceRepository.save(Service.builder().name(service.getName()).fee(service.getFee()).build());
        log.info("Adding service with id : " + save.getId());
        return save;
    }

    @Override
    public void updateServiceById(ServiceModel serviceModel, Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.updateServiceById(serviceModel.getName(), serviceModel.getFee(), id);
            log.info("update service with id : " + id);
        }else throw new RuntimeException("service not found");
    }

    @Override
    public void deleteServiceById(Long id) {
        if (serviceRepository.existsById(id)) {
            log.info("deleting service from NormalUserService table with id : " + id);
            userServiceRepository.deleteAllByUserId(id);
            log.info("delete service with id : " + id);
            serviceRepository.deleteById(id);
        }else throw new RuntimeException("service not found");
    }

    @Override
    public Optional<Service> findServiceById(Long id) {
        log.info("fetching service with id : " + id);
        return serviceRepository.findById(id);
    }

    @Override
    public void grantingServiceForUser(Long serviceId, Long userId) {
        if (userRepository.existsById(userId) && serviceRepository.existsById(serviceId)){
            userServiceRepository.grantServiceForUser(userId,serviceId);
            log.info("service with id : " + serviceId + " granted for user with id : " + userId );
        }else throw new RuntimeException("user or service not found");
    }

    @Override
    public void revokingServiceForUser(Long serviceId, Long userId) {
        if (userRepository.existsById(userId) && serviceRepository.existsById(serviceId)){
            userServiceRepository.revokeServiceForUser(userId,serviceId);
            log.info("service with id : " + serviceId + " revoked for user with id : " + userId );
        }else throw new RuntimeException("user or service not found");
    }

    @Override
    public ServiceActivationDateModel activateService(Long serviceId, ServiceActivationDateModel sadm) {
        //I don't validate date and time
        Optional<Service> byId = serviceRepository.findById(serviceId);
        if (sadm.getEndTime() - sadm.getStartTime() <= 12)
            if (byId.isPresent()){
                Service service = byId.get();
                ServiceActivationDate serviceActivationDate = ServiceActivationDate.builder()
                        .isActive(true)
                        .maximumNumberOfUses(sadm.getMaximumNumberOfUses())
                        .date(sadm.getDate())
                        .startTime(sadm.getStartTime())
                        .endTime(sadm.getEndTime())
                        .service(service).build();

                activationDateRepository.save(serviceActivationDate);
                log.info("activate service with name : " + service.getName() + ", in " + sadm.getDate() +
                        " ," + sadm.getStartTime() + ":" + sadm.getEndTime());

                return ServiceActivationDateModel.builder()
                        .endTime(sadm.getEndTime())
                        .startTime(sadm.getStartTime())
                        .date(sadm.getDate())
                        .maximumNumberOfUses(sadm.getMaximumNumberOfUses()).build();
            }
            else throw new RuntimeException("service not found");
        else throw new RuntimeException("maximum activate timespan is 12 hour");
    }

    @Override
    public void disableService(Long serviceId, DisableServiceDateModel dsm) {
        //I don't validate date and time
        Optional<Service> byId = serviceRepository.findById(serviceId);
        if (byId.isPresent()){
            Service service = byId.get();
            Optional<ServiceActivationDate> sadOptional = activationDateRepository
                    .findByServiceIdAndDateAndStartTimeAndEndTime(serviceId, dsm.getDate(),
                            dsm.getStartTime(), dsm.getEndTime());
            if (sadOptional.isPresent()){
                ServiceActivationDate serviceActivationDate = sadOptional.get();
                serviceActivationDate.setIsActive(false);
                activationDateRepository.save(serviceActivationDate);
            }else throw new RuntimeException("this service does not active for this date and time");

        }
        else throw new RuntimeException("service not found");
    }

    @Override
    public void increaseUserBalance(Long amount, Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.increaseBalance(amount,userId);
            log.info("balance was increased for user with id : " + userId + ", amount : " + amount);
        }else throw new RuntimeException("user not found");
    }

    @Override
    public void setUserBalance(Long amount, Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.setBalance(amount,userId);
            log.info("balance was updated for user with id : " + userId + ", amount : " + amount);
        }else throw new RuntimeException("user not found");
    }

    @Override
    public List<ServiceUsesHistory> getReportOfServiceUsage() {
        log.info("fetch all service usage history");
        return usesHistoryRepository.findAll();
    }
}
