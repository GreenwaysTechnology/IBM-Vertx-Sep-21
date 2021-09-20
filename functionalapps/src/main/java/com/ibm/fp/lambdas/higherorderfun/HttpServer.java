package com.ibm.fp.lambdas.higherorderfun;

public class HttpServer {
    //higher order function
    public void startServer(HttpHandler<String> handler) {
        handler.handle("HTTP Server");
    }
}
