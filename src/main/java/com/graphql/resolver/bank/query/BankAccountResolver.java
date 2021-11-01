package com.graphql.resolver.bank.query;

import com.graphql.BankAccountRepository;
import com.graphql.connection.CursorUtil;
import com.graphql.context.CustomGraphQLContext;
import com.graphql.domain.bank.BankAccount;
import com.graphql.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.relay.RelayConnectionFactory;
import graphql.relay.*;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankAccountResolver implements GraphQLQueryResolver {

    private final BankAccountRepository bankAccountRepository;
    private final CursorUtil cursorUtil;

    public BankAccount bankAccount (UUID id, DataFetchingEnvironment environment) {

        CustomGraphQLContext context = environment.getContext();

        log.info("User id: {}", context.getUserId());

        environment.getSelectionSet().getFields().forEach(selectedField -> {
            log.info("Field: {}", selectedField.getName());
        });

        log.info("Retrieving bank account id: {}", id);
        return BankAccount.builder()
                .id(id)
                .currency(Currency.USD)
                .build();
    }

    public Connection<BankAccount> bankAccounts(int first, @Nullable String cursor) {

        List<Edge<BankAccount>> edges = getBankAccounts(cursor)
            .stream()
            .map(bankAccount -> new DefaultEdge<>(bankAccount, cursorUtil.createCursorWithId(bankAccount.getId())))
            .limit(first)
            .collect(Collectors.toUnmodifiableList());

        var pageInfo = new DefaultPageInfo(
                cursorUtil.getFirstCursorFrom(edges),
                cursorUtil.getLastCursorFrom(edges),
                cursor != null,
                edges.size() >= first);

        return new DefaultConnection<>(edges, pageInfo);
    }

    public List<BankAccount> getBankAccounts(String cursor) {
        if (!StringUtils.hasLength(cursor)) {
            return bankAccountRepository.getBankAccounts();
        }

        return bankAccountRepository.getBankAccountsAfter(cursorUtil.decodeCursorWithId(cursor));
    }
}
