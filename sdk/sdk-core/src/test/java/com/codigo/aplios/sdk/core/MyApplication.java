package com.codigo.aplios.sdk.core;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
// import javax.inject.Singleton;

public class MyApplication {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new AppInjector());

        final MyApplication app = injector.getInstance(MyApplication.class);

        app.sendMessage("Hi Pankaj", "pankaj@abc.com");
    }

    private MyApplication service;

    // constructor based injector
    // @Inject
    // public MyApplication(MessageService svc){
    // this.service=svc;
    // }
    // setter method injector
    @Inject
    public void setService(MyApplication svc) {
        this.service = svc;
    }

    public boolean sendMessage(String msg, String rec) {
        // some business logic here
        return this.service.sendMessage(msg, rec);
    }

}

interface MessageService {

    boolean sendMessage(String msg, String receipient);

}

@Singleton
class EmailService
        implements MessageService {

    @Override
    public boolean sendMessage(String msg, String receipient) {
        // some fancy code to send email
        System.out.println("Email Message sent to " + receipient + " with message=" + msg);
        return true;
    }

}

@Singleton
class FacebookService
        implements MessageService {

    @Override
    public boolean sendMessage(String msg, String receipient) {
        // some complex code to send Facebook message
        System.out.println("Message sent to Facebook user " + receipient + " with message=" + msg);
        return true;
    }

}

class AppInjector
        extends AbstractModule {

    @Override
    protected void configure() {
        // bind the service to implementation class
        // bind(MessageService.class).to(EmailService.class);

        // bind MessageService to Facebook Message implementation
        this.bind(MessageService.class)
                .to(FacebookService.class);
    }

}
