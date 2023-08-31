package com.example.refmonolithicserver.common;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage{
    public String signinMessage(Long msg){
        return "Successfully logged in as user <" + msg +">";
    }

    public String signupMessage(Long msg){
        return "Successful sign-up as user <" + msg +">";
    }

    public String createMessage(Long msg){
        return "Successful create <" + msg +">";
    }

    public String removeMessage(Long msg){
        return "Successful remove <" + msg +">";
    }
}
