package com.cdt.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * AppConfig.
 * This class is responsible for generating the definition of a bean at runtime.
 *
 * @author Emely Daniela Vera Villamizar, emelydaniivera@gmail.com
 */
@Configuration
public class AppConfig {

    /**
     * modelMapper.
     *
     * @return {@link ModelMapper} obj.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEnconder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
