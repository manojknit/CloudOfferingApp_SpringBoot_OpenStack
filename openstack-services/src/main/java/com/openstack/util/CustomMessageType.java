package com.openstack.util;
/**
 * 
 * @author sunder
 *  This class was created to be used for Custom Error Message written as part of REST API
 *
 */
public class CustomMessageType {

    private String successMessage;

    public CustomMessageType(String successMessage){
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return successMessage;
    }

}
