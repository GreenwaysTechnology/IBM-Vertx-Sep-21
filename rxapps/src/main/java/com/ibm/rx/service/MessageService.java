package com.ibm.rx.service;

public class MessageService {
    private MessageStream messageStream;

    public MessageService(MessageStream messageStream) {
        this.messageStream = messageStream;
    }

    public MessageService() {
    }

    //process the stream
    public void processStream() {
        this.messageStream.streamMessage().subscribe(data -> {
            System.out.println(data);
        }, err -> {
            System.out.println(err);
        }, () -> {
            System.out.println("Stream done!!");
        });
    }

    public MessageStream getMessageStream() {
        return messageStream;
    }

    public void setMessageStream(MessageStream messageStream) {
        this.messageStream = messageStream;
    }
}
