package com.ibm.fp.lambdas.args;

public class NoArgsAndParmsMain {
    public static void main(String[] args) {
        //no args ,params
        Welcome welcome = null;
        welcome = () -> {
            System.out.println("Welcome");
        };
        welcome.sayWelcome();
        //code refactoring: remove {}
        welcome = () -> System.out.println("Welcome");
        welcome.sayWelcome();

        //object args
        Logger loggerService = null;

        loggerService = (Log logger) -> {
            logger.info("Something new");
        };
        loggerService.log(new Log());

        loggerService = logger -> {
            logger.info("Something new");
        };
        loggerService.log(new Log());

    }
}
