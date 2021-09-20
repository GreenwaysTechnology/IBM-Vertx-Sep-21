package com.ibm.fp.lambdas.higherorderfun;

@FunctionalInterface
interface HttpHandler<T> {
    void handle(T payload);
}