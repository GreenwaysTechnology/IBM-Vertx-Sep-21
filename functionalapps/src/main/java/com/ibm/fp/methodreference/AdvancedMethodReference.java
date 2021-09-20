package com.ibm.fp.methodreference;

//
class MicroTask {
    public static void startMicroTaskV2() {
        System.out.println(Thread.currentThread().getName());
    }

    public void startMicroTask() {
        System.out.println(Thread.currentThread().getName());
    }
}

class Task {
    private void process() {
        System.out.println(Thread.currentThread().getName());
    }

    public void startTask() {
        Thread thread = null;
        //anonymous inner class
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
        //lambda
        thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
        //lambda logic into separate method
        thread = new Thread(() -> {
            this.process();
        });
        thread.start();

        //lambda logic into separate method but how to reduce lambda expression
        thread = new Thread(this::process);
        thread.start();

        //lambda logic into separate class
        MicroTask microTask = new MicroTask();
        thread = new Thread(() -> {
            microTask.startMicroTask();
        });
        thread.start();

        //lambda logic into separate class
        thread = new Thread(microTask::startMicroTask);
        thread.start();

        //lambda logic into separate class
        thread = new Thread(MicroTask::startMicroTaskV2);
        thread.start();

    }
}


public class AdvancedMethodReference {
    public static void main(String[] args) {
        Task task = new Task();
        task.startTask();
    }
}
