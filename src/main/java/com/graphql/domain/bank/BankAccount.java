package com.graphql.domain.bank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Data
@Builder
public class BankAccount {
    UUID id;
    Client client;
    Currency currency;
}
