package cat.uvic.teknos.m06.gamestore.utilities;

import cat.uvic.teknos.m06.gamestore.utilities.exceptions.SchemaLoaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class CommandSchemaLoader implements SchemaLoader {

    private final String schemaPath;
    private final ConnectionProperties connectionProperties;

    public CommandSchemaLoader(String schemaPath, ConnectionProperties connectionProperties) {
        this.schemaPath = schemaPath;
        this.connectionProperties = connectionProperties;
    }

    @Override
    public void load() {
        try (var connection = DriverManager.getConnection(
                connectionProperties.getUrl(), connectionProperties.getUsername(), connectionProperties.getPassword());
             var statement = connection.createStatement();
             var inputStream = new BufferedReader(new FileReader(schemaPath, Charset.forName("utf-8")))
        ) {
            String command = "";
            int r =0;
            String comment = "";
            while ((r=inputStream.read())!=-1){
                while ((char)r == ' '){
                    r=inputStream.read();
                }
                comment += (char)r ;
                if (comment== "/*"){
                    while (comment.indexOf("/*")!= -1) {
                        r = inputStream.read();
                        comment += (char) r;
                    }
                }
                while ((char)r != ';'){
                    command +=(char)r;
                    r=inputStream.read();
                }
                statement.executeUpdate(command+";");
                command= "";
                comment = "";
            }
        } catch (SQLSyntaxErrorException e){
            throw new SchemaLoaderException("SyntaxError Exception!", e);
        } catch (SQLException e) {
            throw new SchemaLoaderException("Sql Exception!", e);
        } catch (FileNotFoundException e) {
            throw new SchemaLoaderException("The file" + schemaPath + "doesn't exist", e);
        } catch (IOException e) {
            throw new SchemaLoaderException("IO Exception!", e);
        }
    }
}