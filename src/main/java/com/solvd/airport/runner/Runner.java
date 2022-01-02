package com.solvd.airport.runner;

import com.solvd.airport.models.User;
import com.solvd.airport.models.Users;
import com.solvd.airport.services.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String args[]) throws InterruptedException,
            JAXBException, SQLException {
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

        UserService us = new UserService();
        logger.info(us.getUserById(4));

        us.createUser(user);

        logger.info(us.getUserById(10));
    }
}
