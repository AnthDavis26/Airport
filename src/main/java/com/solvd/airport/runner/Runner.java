package com.solvd.airport.runner;

import com.solvd.airport.models.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String args[]) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema userSchema = schemaFactory.newSchema(new File("src/main/resources/users.xsd"));
            unmarshaller.setSchema(userSchema);

            Users users = (Users) unmarshaller.unmarshal(new File("src/main/resources/users.xml"));
            logger.info(users.getUsers());

            marshaller.marshal(users, new File("src/main/resources/userTestOutput.xml"));
            userSchema = schemaFactory.newSchema(new File("src/main/resources/users.xsd"));
            unmarshaller.setSchema(userSchema);

            users = (Users) unmarshaller.unmarshal(new File("src/main/resources/userTestOutput.xml"));
            logger.info(users.getUsers());
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
