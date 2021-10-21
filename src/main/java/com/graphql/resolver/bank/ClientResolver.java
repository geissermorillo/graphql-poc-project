package com.graphql.resolver.bank;

import com.graphql.domain.bank.BankAccount;
import com.graphql.domain.bank.Client;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Builder
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    /**
     * DataFetcherResult Allows us to include additional information like Erro(s) and local context into response
     */
    public DataFetcherResult<Client> client(BankAccount bankAccount) {
        log.info("Requesting client data for bank account id {}",  bankAccount.getId());


        return DataFetcherResult.<Client>newResult()
            .data(Client.builder()
                .id(UUID.randomUUID())
                .firstName("Geisser")
                .lastName("Morillo1")
                .build())
//            .error(new GenericGraphQLError("Could not get sub-client id"))
            .build();

//        return Client.builder()
//                .id(UUID.randomUUID())
//                .firstName("Geisser")
//                .lastName("Morillo1")
//                .build();

    }
}
