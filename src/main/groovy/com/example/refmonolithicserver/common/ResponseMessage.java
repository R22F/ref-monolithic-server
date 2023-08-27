package com.example.refmonolithicserver.common;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage<T> {
    public String signinMessage(T msg){
        return "Successfully logged in as user <" + msg +">";
    }

    public String signupMessage(T msg){
        return "Successful sign-up as user <" + msg +">";
    }

    public String createMessage(T msg){
        return "Successful create <" + msg +">";
    }

    public String removeMessage(T msg){
        return "Successful remove <" + msg +">";
    }
}
