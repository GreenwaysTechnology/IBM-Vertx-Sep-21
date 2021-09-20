package com.ibm.fp.lambdas.higherorderfun;

@FunctionalInterface
public interface Reject {
    void reject(Throwable error);
}
