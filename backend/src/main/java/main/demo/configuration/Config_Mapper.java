package main.demo.configuration;


import main.demo.domain.dto.response.Response_User;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config_Mapper {

    private final org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();


    @Bean
    public org.modelmapper.ModelMapper modelMapper() {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new Response_User.UserMap());

        return modelMapper;
    }

}
