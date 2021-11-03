package com.graphql.resolver.bank.query;

import com.graphql.GraphqlPocProjectApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Configuration
@Import(GraphqlPocProjectApplication.class)
public class TestApplication {

    @Bean
    @Primary
    public Clock testClock() {
        return Clock.fixed(LocalDate.of(2020, 10, 17).atStartOfDay(UTC).toInstant(), UTC);
    }


}
