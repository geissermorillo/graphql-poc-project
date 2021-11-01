package com.graphql.domain.bank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BankAccount {
    UUID id;
    Client client;
    Currency currency;
    List<Asset> assets;
    ZonedDateTime createdAt;
    LocalDate createdOn;
    BigDecimal balance;

}
