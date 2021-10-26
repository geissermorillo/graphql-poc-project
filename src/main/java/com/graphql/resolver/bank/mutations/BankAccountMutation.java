package com.graphql.resolver.bank.mutations;

import com.graphql.domain.bank.BankAccount;
import com.graphql.domain.bank.Client;
import com.graphql.domain.bank.Currency;
import com.graphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BankAccountMutation implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput input) {
        log.info("Creation bank account for {}", input);
        return BankAccount.builder()
            .id(UUID.randomUUID())
            .currency(Currency.USD)
            .client(Client.builder()
                .id(UUID.randomUUID())
                .firstName(input.getFirstName())
                .build()
            )
            .build();
    }
}
