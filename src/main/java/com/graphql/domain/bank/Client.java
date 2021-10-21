package com.graphql.domain.bank;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Client {
    UUID id;
    String firstName;
    List<String> middleNames;
    String lastName;
    Client client;
}
