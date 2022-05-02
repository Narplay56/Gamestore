package cat.uvic.teknos.m06.gamestore.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLineCommandSchemaLoaderTest {

    @Test
    void load() {
        var conectionProperties = new ConnectionProperties();

        conectionProperties.setUrl("jdbc:mysql://localhost:3306/mysql");
        conectionProperties.setUsername("root");

        var schemaLoader = new SingleLineCommandSchemaLoader("src/test/resources", conectionProperties);

        schemaLoader.load();

        assertDoesNotThrow(() -> {
            schemaLoader.load();
        });
    }
}