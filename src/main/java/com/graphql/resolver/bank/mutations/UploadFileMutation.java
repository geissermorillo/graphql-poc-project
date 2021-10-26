package com.graphql.resolver.bank.mutations;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class UploadFileMutation implements GraphQLMutationResolver {

    UUID uploadFile(DataFetchingEnvironment environment) {
        log.info("uploading file...");

        DefaultGraphQLServletContext context = environment.getContext();

        context.getFileParts().forEach(part -> {
            log.info("Uploading: {}, size: {}", part.getSubmittedFileName(), part.getSize());
        });

        return UUID.randomUUID();
    }
}
