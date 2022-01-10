package com.solvd.airport.runner;

import com.solvd.airport.models.Users;
import com.solvd.airport.services.impl.UserService;
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
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema userSchema = schemaFactory.newSchema(new File("src/main/resources/users.xsd"));
            unmarshaller.setSchema(userSchema);

            UserService userService = new UserService();
            Users users = new Users();
            users.setUsers(userService.getAllUsers());
            logger.info(users.getUsers());
            marshaller.marshal(users, new File("src/main/resources/userTestOutput.xml"));
            userService.deleteUserById(14);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
