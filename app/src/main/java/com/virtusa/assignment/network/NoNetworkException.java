package com.virtusa.assignment.network;

import java.io.IOException;

/*Custom Exception to throw no network exception*/
public class NoNetworkException extends IOException {
    public NoNetworkException(String message) {
        super(message);
    }
}

