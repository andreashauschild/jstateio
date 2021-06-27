package io.jprocess.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class DockerDatabaseTestResource implements QuarkusTestResourceLifecycleManager {

    private final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("jprocess").withUsername("jprocess").withPassword("jprocess");

    @Override
    public Map<String, String> start() {
        this.postgreSQLContainer.start();
        return Map.of(
                "quarkus.datasource.db-kind", "postgresql",
                "quarkus.datasource.jdbc.url", postgreSQLContainer.getJdbcUrl(),
                "quarkus.datasource.username", "jprocess",
                "quarkus.datasource.password", "jprocess",
                "quarkus.hibernate-orm.database.generation", "drop-and-create"
        );
    }

    @Override
    public void stop() {
        this.postgreSQLContainer.stop();
    }
}
