package com.example.chatapp.custom.configurations;

import com.example.chatapp.custom.mappers.CustomModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfigurations {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public CustomModelMapper customModelMapper()
    {
        return new CustomModelMapper();
    }

}
