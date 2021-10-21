package com.graphql.resolver.bank;

import com.graphql.domain.bank.Asset;
import com.graphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class AssetResolver implements GraphQLResolver<BankAccount> {

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<List<Asset>> assets(BankAccount bankAccount) {
        log.info("Getting assets for banl accound id {}");
        return CompletableFuture.supplyAsync(() -> {
            log.info("Getting assets for banl accound id {}", bankAccount.getId());
            return List.of();
        }, executorService);
    }
}
