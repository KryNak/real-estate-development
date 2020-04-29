package com.exception;

public class ProblematicTenantException extends Exception{

    public ProblematicTenantException(String announcement ) {
        super(announcement);
    }
}