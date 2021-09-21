package com.ibm.rx.service;

public class MainStreamApp {
    public static void main(String[] args) {
        MessageService messageService = new MessageService(new MessageStream());
        messageService.processStream();
    }
}
