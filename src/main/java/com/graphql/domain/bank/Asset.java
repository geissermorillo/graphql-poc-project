package com.graphql.domain.bank;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Asset {
    UUID id;
}
