package com.ibm.fp.lambdas.higherorderfun;


public class HigherOrderMainAdvanced {
    public static void main(String[] args) {
        Socket socket = new Socket();
        socket.requestHandler(() -> System.out.println("Socket handler"));

        //
        HttpServer httpServer = new HttpServer();
        httpServer.startServer(response -> System.out.println(response));

        //login
        Auth auth = new Auth();
        auth.login("admin", "admin", response -> System.out.println(response), error -> {
            System.out.println(error.getMessage());
        });
        auth.login("foo", "bar", response -> System.out.println(response), error -> {
            System.out.println(error.getMessage());
            //error.printStackTrace();
        });

    }
}
