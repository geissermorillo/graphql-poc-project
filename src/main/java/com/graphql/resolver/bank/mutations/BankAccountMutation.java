package com.graphql.resolver.bank.mutations;

import com.graphql.domain.bank.BankAccount;
import com.graphql.domain.bank.Client;
import com.graphql.domain.bank.Currency;
import com.graphql.domain.bank.input.CreateBankAccountInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@Validated
public class BankAccountMutation implements GraphQLMutationResolver {

    private final Clock clock;

    public BankAccount createBankAccount(@Valid CreateBankAccountInput input) {
        log.info("Creation bank account for {}", input);
        return BankAccount.builder()
            .id(UUID.randomUUID())
            .currency(Currency.USD)
            .client(Client.builder()
                .id(UUID.randomUUID())
                .firstName(input.getFirstName())
                .build()
            )
            .createdAt(ZonedDateTime.now(clock))
            .createdOn(LocalDate.now(clock))
            .build();
    }
}
