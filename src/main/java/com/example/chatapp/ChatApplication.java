package com.example.chatapp;

import com.example.chatapp.enums.utils.LogMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class ChatApplication {

    public static void main(String[] args) {

        SpringApplication.run(ChatApplication.class, args);

        log.info(LogMessages.APPLICATION_START.getDescription());

    }

}
