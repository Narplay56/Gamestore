package cat.uvic.teknos.m06.gamestore.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlSchemaLoaderTest {

    @Test
    void load() {
        var loader = new XmlSchemaLoader("src/test/resources/schema.xml");
        loader.load();
        assertDoesNotThrow(() -> {
            loader.load();
        });
    }

}