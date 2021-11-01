package com.graphql.resolver.bank.query;

import com.graphql.context.dataloader.DataLoaderRegistryFactory;
import com.graphql.domain.bank.BankAccount;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLResolver<BankAccount> {
    public CompletableFuture<BigDecimal> balance(BankAccount bankAccount, DataFetchingEnvironment environment) throws InterruptedException {
        /*
         * GraphQL first execute classes thta implments GraphQLQueryResolver and then the ones tha implement
         * GraphQLResolver for class, So for each node returned in a query it will execute all resolvers adding latency
         * for each request (this what is called N+1 problem).
         *
         * So, the best solution is execute only one batch request with all the Id's, for this it is necessary
         * use a DataLoader
         *
         */
        log.info("Getting balance for {}", bankAccount.getId());

        DataLoader<UUID, BigDecimal> dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER);

        //This code allows us to load the dataLoader with all the ids and then execute only one batch request
        return dataLoader.load(bankAccount.getId());
    }
}
