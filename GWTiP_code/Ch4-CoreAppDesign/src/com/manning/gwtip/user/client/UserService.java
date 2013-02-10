package com.manning.gwtip.user.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface UserService extends RemoteService {
    
    public void createUser(User user) throws UserServiceException;
    
}
