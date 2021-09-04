package com.bontech.intershipt.demo.service.impl;

import com.bontech.intershipt.demo.models.dto.ServiceModel;
import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.ServiceUsesHistory;
import com.bontech.intershipt.demo.repositories.*;
import com.bontech.intershipt.demo.service.base.UserService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    private final NormalUserServiceRepository userServiceRepository;
    private final NormalUserRepository userRepository;
    private final ServiceUsesHistoryRepository usesHistoryRepository;
    private final ServiceActivationDateRepository activationDateRepository;
    private final ServiceRepository serviceRepository;

    public UserServiceImpl(NormalUserServiceRepository userServiceRepository, NormalUserRepository userRepository, ServiceUsesHistoryRepository usesHistoryRepository, ServiceActivationDateRepository activationDateRepository, ServiceRepository serviceRepository) {
        this.userServiceRepository = userServiceRepository;
        this.userRepository = userRepository;
        this.usesHistoryRepository = usesHistoryRepository;
        this.activationDateRepository = activationDateRepository;
        this.serviceRepository = serviceRepository;
    }


    @Override
    public List<Service> findGrantingService(Long userId) {
        if (userRepository.existsById(userId)){
            log.info("fetch all granting service for user with id : " + userId);
            return userServiceRepository.findAllByUserId(userId);
        }
        else throw new RuntimeException("user not found");
    }

    @Override
    public List<Service> findActiveService(Long userId) {
        if (userRepository.existsById(userId)) {
            LocalDate now = LocalDate.now();
            LocalTime time = LocalTime.now();
            float hour = (float) time.getHour() + (float) time.getMinute() / 60 + (float) time.getSecond() / 3600;
            List<Service> grantingService = findGrantingService(userId);
            List<Long> allActiveServiceId = activationDateRepository
                    .findAllIdByDateAndTimeAndIsActiveTrue(now, hour).stream().distinct().collect(Collectors.toList());
            log.info("fetch all active service for user with id : " + userId);
            return grantingService.stream().filter(a -> {
                return allActiveServiceId.contains(a.getId());
            }).collect(Collectors.toList());
        }else throw new RuntimeException("user not found");
    }

    @Override
    public ServiceModel useService(Long service_id, Long user_id) {
        if (userRepository.existsById(user_id) && serviceRepository.existsById(service_id)){
            List<Service> activeService = findActiveService(user_id);
            for (Service service : activeService){
                if (service.getId().equals(service_id)){
                    ServiceUsesHistory serviceUsesHistory = ServiceUsesHistory.builder()
                                    .serviceId(service_id).date(new Date()).userId(user_id).build();
                    ServiceUsesHistory save = usesHistoryRepository.save(serviceUsesHistory);
                    log.info("user with id : " + user_id + " use service with id : " + service_id);
                    return ServiceModel.builder().fee(service.getFee()).name(service.getName()).build();
                }
            }
            throw new RuntimeException("probably service does not active for this user");
        }else throw new RuntimeException("user or service not found");
    }

    @Override
    public List<ServiceUsesHistory> getReportOfServiceUsage(Long userId) {
        log.info("fetching all service usage history for user with id : " + userId);
        return usesHistoryRepository.findAllByUserId(userId);
    }
}
