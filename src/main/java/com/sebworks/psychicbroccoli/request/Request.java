package com.sebworks.psychicbroccoli.request;

/**
 * Common interface for all requests
 * Created by seb on 23.04.2017.
 */
public interface Request<T> {
    T ask();
}
