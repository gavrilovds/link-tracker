package edu.java.scrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseMigrationTest extends IntegrationEnvironment {

    @Test
    @SneakyThrows
    public void chatMigration_shouldCreateTable() {
        Connection connection = POSTGRES.createConnection("");
        PreparedStatement statement = connection.prepareStatement("SELECT * from chat");
        Assertions.assertThat(statement.executeQuery().getMetaData().getColumnName(1)).isEqualTo("id");
    }

    @Test
    @SneakyThrows
    public void linkMigration_shouldCreateTable() {
        Connection connection = POSTGRES.createConnection("");
        PreparedStatement statement = connection.prepareStatement("SELECT * from link");
        Assertions.assertThat(statement.executeQuery().getMetaData().getColumnName(2)).isEqualTo("url");
    }
}
