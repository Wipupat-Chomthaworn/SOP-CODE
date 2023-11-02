package com.example.core.query;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    @QueryHandler
    public User findPaymentDeatail(FetchUserPaymentDetailsQuery query){
        
    }

}
