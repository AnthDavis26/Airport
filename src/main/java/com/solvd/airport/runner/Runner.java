package com.solvd.airport.runner;

import com.solvd.airport.dao.mysqlimpl.UserDAO;
import com.solvd.airport.models.Users;
import com.solvd.airport.services.impl.UserService;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.sql.Connection;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String args[]) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema userSchema = schemaFactory.newSchema(new File("src/main/resources/users.xsd"));
            unmarshaller.setSchema(userSchema);

            Users users = new Users();
            UserDAO userDAO = new UserDAO();
            users.setUsers(userDAO.getAllEntities());
            LOGGER.info(users.getUsers());
            marshaller.marshal(users, new File("src/main/resources/userTestOutput.xml"));
            userDAO.deleteEntityById(14);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
