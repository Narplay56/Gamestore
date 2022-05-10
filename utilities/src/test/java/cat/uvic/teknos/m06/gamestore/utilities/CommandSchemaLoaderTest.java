package cat.uvic.teknos.m06.gamestore.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandSchemaLoaderTest {

    @Test
    void load() {
        var conectionProperties = new ConnectionProperties();

        conectionProperties.setUrl("jdbc:mysql://localhost:3306/gamestore");
        conectionProperties.setUsername("root");

        var schemaLoader = new CommandSchemaLoader("src/test/resources/schema.sql",conectionProperties);

        schemaLoader.load();

        assertDoesNotThrow(() -> {
            schemaLoader.load();
        });
    }
}