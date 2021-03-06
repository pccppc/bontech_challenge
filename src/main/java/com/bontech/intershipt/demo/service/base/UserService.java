package com.bontech.intershipt.demo.service.base;


import com.bontech.intershipt.demo.models.dto.ServiceModel;
import com.bontech.intershipt.demo.models.db.Service;
import com.bontech.intershipt.demo.models.db.ServiceUsesHistory;

import java.util.List;

public interface UserService {

    /**
     * find granted service for a user
     * @param userId search a user
     * @return list of service
     */
    public List<Service> findGrantingService(Long userId);

    /**
     * find active user service for use
     * @param userId search a user
     * @return list of service
     */
    public List<Service> findActiveService(Long userId);

    /**
     * use a service and save info into database if service activated and granted for user
     * @param service_id search service in db
     * @param user_id search user in db
     * @return ServiceModel
     */
    public ServiceModel useService(Long service_id,Long user_id);


    /**
     * search in ServiceUsesHistory table and report service history of user
     * @param userId search history with userId
     * @return list of serviceUsageHistory
     */
    public List<ServiceUsesHistory> getReportOfServiceUsage(Long userId);


}
