package com.solvd.airport.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.User;
import com.solvd.airport.utils.Wrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String args[]) throws IOException {
        // JAXB with XML
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class, User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema userSchema = schemaFactory.newSchema(new File("src/main/resources/users.xsd"));
            unmarshaller.setSchema(userSchema);

            Wrapper<User> users = (Wrapper<User>) unmarshaller.unmarshal(
                    new StreamSource("src/main/resources/users.xml"), Wrapper.class).getValue();

            for (User u : users.getAll())
            {
                //new UserDAO().createEntity(u);
                LOGGER.info(u);
            }
            marshaller.marshal(users, new File("src/main/resources/userTestOutput.xml"));
        } catch (Exception e) {
            LOGGER.error(e);
        }

        // JSON
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        // File json = FileUtils.getFile("src/main/resources/users.json");
        // List<User> users = objectMapper.readValue(json, new TypeReference<ArrayList<User>>(){});
        List<User> users = new UserDAO().getAllEntities();

        for (User u : users)
            LOGGER.info(u);

        objectMapper.writeValue(new File("src/main/resources/output.json"), users);
    }
}
