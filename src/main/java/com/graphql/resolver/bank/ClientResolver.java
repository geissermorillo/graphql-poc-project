package com.graphql.resolver.bank;

import com.graphql.domain.bank.BankAccount;
import com.graphql.domain.bank.Client;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Builder
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {

    private final ExecutorService executorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<Client> client(BankAccount bankAccount) {
        log.info("Stop me debugging");

        return CompletableFuture.supplyAsync(
            () -> {
                log.info("Requesting client data for bank account id {}",  bankAccount.getId());
                return Client.builder()
                    .id(UUID.randomUUID())
                    .firstName("Geisser")
                    .lastName("Morillo1")
                    .build();
            },
                executorService
        );
    }
}
