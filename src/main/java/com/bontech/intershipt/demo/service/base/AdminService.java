package com.bontech.intershipt.demo.service.base;

import com.bontech.intershipt.demo.dto.DisableServiceDateModel;
import com.bontech.intershipt.demo.dto.ServiceActivationDateModel;
import com.bontech.intershipt.demo.dto.ServiceModel;
import com.bontech.intershipt.demo.dto.UserModel;
import com.bontech.intershipt.demo.models.Service;
import com.bontech.intershipt.demo.models.ServiceUsesHistory;
import com.bontech.intershipt.demo.models.usr.NormalUser;

import java.util.List;


public interface AdminService {

    /**
     * persist a user into database
     * @param user given user to be saved into db
     * @return the same user after saving into db
     */
    public NormalUser saveUser(UserModel user);



    /**
     * update a user by userId
     * @param userModel include new information about user
     * @param id for search user in database
     */
    public void updateNormalUserById(UserModel userModel, Long id);


    /**
     * delete a user from database
     * @param id for search user and delete it
     */
    public void deleteUserById(Long id);


    /**
     * find a user from database and return that
     * @param id for search user
     * @return normalUser after find it from database
     */
    public NormalUser findUserById(Long id);




    /**
     * persist a service into database
     * @param service given service to be saved into db
     * @return service after saving into db
     */
    public Service saveService(ServiceModel service);



    /**
     * update a service by userId
     * @param serviceModel include new information about service
     * @param id for search service in database
     */
    public void updateServiceById(ServiceModel serviceModel,Long id);


    /**
     * delete a service from database
     * @param id for search service and delete it
     */
    public void deleteServiceById(Long id);


    /**
     * find a service from database and return that
     * @param id for search service
     * @return Service after find it from database
     */
    public Service findServiceById(Long id);

    /**
     * granting a service for user and save relation into NormalUserService table in db
     * @param serviceId for search service in db
     * @param UserId for search user in db
     */
    public void grantingServiceForUser(Long serviceId, Long UserId);

    /**
     * revoking a service for user and delete relation from NormalUserService table in exists
     * @param serviceId for search service in db
     * @param userId for search user in db
     */
    public void revokingServiceForUser(Long serviceId, Long userId);

    /**
     * activate a service for a specified time
     * @param serviceId search service in db
     * @param sadm for save info into ServiceActivationDate table
     * @return serviceActivationModel
     */
    public ServiceActivationDateModel activateService(Long serviceId,ServiceActivationDateModel sadm);


    /**
     * disable a service for a specified time
     * @param serviceId search service in db
     * @param dsm for save info into ServiceActivationDate table
     */
    public void disableService(Long serviceId, DisableServiceDateModel dsm);


    /**
     * find a user in db and increase user balance
     * @param amount amount
     * @param userId search user in db
     */
    public void increaseUserBalance(Long amount, Long userId);

    /**
     * find a user in db and set user balance
     * @param amount amount
     * @param userId search user in db
     */
    public void setUserBalance(Long amount, Long userId);


    /**
     * search in ServiceUsesHistory table and report them
     * @return list of serviceUsageHistory
     */
    public List<ServiceUsesHistory> getReportOfServiceUsage();

}
