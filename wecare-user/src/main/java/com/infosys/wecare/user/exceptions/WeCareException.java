package com.infosys.wecare.user.exceptions;

public class WeCareException extends Exception {
    private static final long serialVersionUID = 1L;
    public WeCareException(String errors){
        super(errors);
    }
}
