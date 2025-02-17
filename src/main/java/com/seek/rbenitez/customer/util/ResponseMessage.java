package com.seek.rbenitez.customer.util;

public final class ResponseMessage {
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final String WARNING_MESSAGE = "WARNING";
    public static final String FAILED_MESSAGE = "FAILED";

    private ResponseMessage() {
        throw new IllegalStateException("Utility class");
    }
}
