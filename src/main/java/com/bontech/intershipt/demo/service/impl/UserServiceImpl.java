package com.bontech.intershipt.demo.service.impl;

import com.bontech.intershipt.demo.dto.ServiceModel;
import com.bontech.intershipt.demo.models.Service;
import com.bontech.intershipt.demo.models.ServiceUsesHistory;
import com.bontech.intershipt.demo.service.base.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<Service> findGrantingService() {
        return null;
    }

    @Override
    public List<Service> findActivateService() {
        return null;
    }

    @Override
    public ServiceModel useService(Long service_id, Long user_id) {
        return null;
    }

    @Override
    public List<ServiceUsesHistory> getReportOfServiceUsage(Long userId) {
        return null;
    }
}
