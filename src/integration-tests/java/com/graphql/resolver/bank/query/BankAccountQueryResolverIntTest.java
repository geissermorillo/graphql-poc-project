package com.graphql.resolver.bank.query;

import com.graphql.GraphqlPocProjectApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.micrometer.core.instrument.util.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class BankAccountQueryResolverIntTest {

    private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/request/%s.graphql";
    private static final String GRAPHQL_QUERY_RESPONSE_PATH ="graphql/resolver/query/response/%s.json";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void bankAccountAreReturned() throws IOException, JSONException {

        var testName = "bank_account";
        GraphQLResponse graphQLResponse =
                graphQLTestTemplate.postForResource(String.format(GRAPHQL_QUERY_REQUEST_PATH, testName));

        var expectedResponseBody = read(String.format(GRAPHQL_QUERY_RESPONSE_PATH, testName));
        
        Assertions.assertEquals(graphQLResponse.getStatusCode(),HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
