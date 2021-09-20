package com.ibm.fp.lambdas.higherorderfun;

class Socket {
    //receive function as parameter
    public void requestHandler(Handler handler) {
        handler.handle();
    }
}

