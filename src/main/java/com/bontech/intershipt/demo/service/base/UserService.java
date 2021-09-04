package com.bontech.intershipt.demo.service.base;


import com.bontech.intershipt.demo.dto.ServiceModel;
import com.bontech.intershipt.demo.models.Service;

import java.util.List;

public interface UserService {

    /**
     * find granted service for a user
     * @return list of service
     */
    public List<Service> findGrantingService();

    /**
     * find active user service for use
     * @return list of service
     */
    public List<Service> findActivateService();

    /**
     * use a service and save info into database if service activated and granted for user
     * @param service_id search service in db
     * @param user_id search user in db
     * @return ServiceModel
     */
    public ServiceModel useService(Long service_id,Long user_id);
}
