package com.solvd.airport.runner;

import com.solvd.airport.models.User;
import com.solvd.airport.models.Users;
import com.solvd.airport.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String args[]) throws InterruptedException,
            JAXBException, ParseException {
        /*
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection con1 = connectionPool.getConnection();
        Connection con2 = connectionPool.getConnection();
        Connection con3 = connectionPool.getConnection();
        connectionPool.releaseConnection(con1);
        connectionPool.releaseConnection(con2);
        connectionPool.releaseConnection(con3);
        Connection con4 = connectionPool.getConnection();
        connectionPool.releaseConnection(con4);
        */

        User user = new User();
        user.setId(123L);
        user.setFirstName("Jean-Luc");
        user.setLastName("Picard");
        user.setBirthday(LocalDate.of(2305,07,13));
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(user, new File("src/main/resources/userTest.xml"));

        jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Users users = (Users) unmarshaller.unmarshal(new File("src/main/resources/users.xml"));
        logger.info(users.getUsers());
    }
}
