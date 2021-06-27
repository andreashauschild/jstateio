package io.jprocess.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class LocalDatabaseTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        return Map.of(
                "quarkus.datasource.db-kind", "postgresql",
                "quarkus.datasource.jdbc.url", "jdbc:postgresql://localhost:15000/jprocess",
                "quarkus.datasource.username", "jprocess",
                "quarkus.datasource.password", "jprocess",
                "quarkus.hibernate-orm.database.generation", "drop-and-create"
        );
    }

    @Override
    public void stop() {

    }
}
