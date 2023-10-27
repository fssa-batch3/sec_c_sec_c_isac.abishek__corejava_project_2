package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.EventDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.logger.Logger;
import com.fssa.charitytrust.model.Event;
import com.fssa.charitytrust.validator.EventValidator;

class TestEventServiceLayer {

	/**
     * Creates an Event object for testing purposes.
     *
     * @return An Event object.
     */
    public Event createTestEvent() {
        LocalDate input = LocalDate.of(2023, 10, 10);
        return new Event(
            "Marina",
            "Marina beach Chennai",
            "Freshtrust",
            "9751328805",
            "https://iili.io/HUfsTgV.jpg",
            input,
            "Product Donation and distribution"
        );
    }

    /**
     * Creates an instance of EventServiceLayer for testing.
     *
     * @return An instance of EventServiceLayer.
     */
    public EventService createTestEventLayer() {
        EventValidator eventValidator = new EventValidator();
        EventDao eventDao = EventDao.getEventDao();
        return new EventService(eventValidator, eventDao);
    }

    /**
     * Test case to check the addEvent method with a valid event.
     *
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testAddEvent() throws  ServiceException, ValidatorInitializationException, SQLException, DaoException, ConnectionException{
        Event event1 = createTestEvent();
        EventService eventServiceLayer = createTestEventLayer();
        Assertions.assertTrue(eventServiceLayer.addEvent(event1));
    }

    /**
     * Test case to check the addEvent method with a null event.
     *
     * @throws DaoException If there's an issue with the DAO.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testAddNullEvent() throws ServiceException {
        Event event1 = null;
        EventService eventServiceLayer = createTestEventLayer();
        try {
            eventServiceLayer.addEvent(event1);
        } catch (ServiceException e) {
            Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
        }
    }

    /**
     * Test case to check the addEvent method with an invalid event.
     *
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws DaoException If there's an issue with the DAO.
     * @throws SQLException If there's an SQL related issue.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testAddInvalidEvent() throws ServiceException {
        LocalDate input = LocalDate.of(2023, 10, 10);
        Event event1 = new Event(
            "12345",
            "North Street, Taramani",
            "Organizer",
            "9751328805",
            "https://freeimage.host/i/HNRzLYJ.jpg",
            input,
            "Concert"
        );
        EventService eventServiceLayer = createTestEventLayer();
        Assertions.assertFalse(eventServiceLayer.addEvent(event1));
    }

    /**
     * Test case to check the updateEvent method with valid data.
     *
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testUpdateEvent() throws ServiceException {
        LocalDate input = LocalDate.of(2023, 10, 10);
        Event eventNew = new Event(
            "IslandGround",
            "North Street, Taramani",
            "Organizer",
            "9751328805",
            "https://iili.io/H8lK1MQ.jpg",
            input,
            "Product Donating And Distributions"
        );
        Event updateEvent = new Event(
            "IslandGround",
            "North Street, Taramani",
            "FreshTrust",
            "9751328805",
            "https://iili.io/H8lK1MQ.jpg",
            input,
            "Product Donating And Distributions"
        );
        EventService eventServiceLayer = createTestEventLayer();
        eventServiceLayer.addEvent(eventNew);
        Assertions.assertTrue(eventServiceLayer.updateEvent(updateEvent));
    }

    /**
     * Test case to check the updateEvent method with a null event.
     *
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testUpdateNullEvent() throws ServiceException {
        Event event1 = null;
        EventService eventServiceLayer = createTestEventLayer();
        try {
            eventServiceLayer.updateEvent(event1);
        } catch (ServiceException e) {
            Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
        }
    }

    /**
     * Test case to check the updateEvent method with an invalid event.
     *
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testUpdateInvalidEvent() throws ServiceException {
        LocalDate input = LocalDate.of(2023, 10, 10);
        Event event1 = new Event(
            "12345",
            "North Street, Taramani",
            "Organizer",
            "9751328805",
            "https://freeimage.host/i/HNRzLYJ.jpg",
            input,
            "Concert"
        );
        EventService eventServiceLayer = createTestEventLayer();
        Assertions.assertFalse(eventServiceLayer.updateEvent(event1));
    }


    /**
     * Test case to check the readEvents method.
     *
     * @throws DaoException If there's an issue with the DAO.
     * @throws SQLException If there's an SQL related issue.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testReadEvents() throws ServiceException {
        LocalDate input = LocalDate.of(2023, 10, 10);
        Event event1 = new Event(
            "Pachayppas",
            "Government college in Chennai, Tamil Nadu",
            "Pachayppas college",
            "9751328805",
            "https://iili.io/HytGoVs.jpg",
            input,
            "Product Donation"
        );
        EventService eventServiceLayer = createTestEventLayer();
        eventServiceLayer.addEvent(event1);
        Assertions.assertNotEquals(0, eventServiceLayer.readEvents().size());
    }

    /**
     * Test case to check the viewEvent method.
     *
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testViewEvents() throws ServiceException {
        EventService eventServiceLayer = createTestEventLayer();
        Assertions.assertNotNull(eventServiceLayer.viewEvent());
        Logger.info(eventServiceLayer.viewEvent());
    }

    /**
     * Test case to check the deleteEvent method with valid data.
     *
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testDeleteEvent() throws ServiceException {
    	 LocalDate input = LocalDate.of(2023, 10, 10);
         Event event1 = new Event(
             "BalajiiEvent",
             "Global info North Street, Taramani",
             "Freshtrust",
             "9751328805",
             "https://freeimage.host/i/HNRzLYJ.jpg",
             input,
             "Concert"
         );
         EventService eventServiceLayer = createTestEventLayer();
         eventServiceLayer.addEvent(event1);
        Assertions.assertTrue(eventServiceLayer.deleteEvent("BalajiiEvent"));
    }

    /**
     * Test case to check the deleteEvent method with a null event name.
     *
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testDeleteNullEvent() throws ServiceException {
        EventService eventServiceLayer = createTestEventLayer();
        try {
            eventServiceLayer.deleteEvent(null);
        } catch (ServiceException e) {
            Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
        }
    }

    /**
     * Test case to check the deleteEvent method with an invalid event name.
     *
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testDeleteInvalidEvent() throws ServiceException {
        EventService eventServiceLayer = createTestEventLayer();
        try {
            eventServiceLayer.deleteEvent("InvalidName");
        } catch (ServiceException e) {
            Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED, e.getMessage());
        }
    }

    /**
     * Test case to check the findEventByName method.
     *
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testFindByName() throws ServiceException {
        LocalDate input = LocalDate.of(2023, 10, 10);
        Event event1 = new Event(
            "Chepak",
            "A Stand ,MA Chidambaram Stadium, MA CHIDAMBARAM STADIUM, Victoria Hostel Rd",
            "Freshtrust",
            "9751328805",
            "https://iili.io/HyOu1Ve.jpg",
            input,
            "Product distribution in Chepak stadium"
        );

        EventService eventServiceLayer = createTestEventLayer();
        eventServiceLayer.addEvent(event1);
        Assertions.assertEquals(eventServiceLayer.findEventByName(event1.getEventName()), event1);
    }

    /**
     * Test case to check the findEventByName method with a null event name.
     *
     * @throws IllegalArgumentException If an invalid argument is provided.
     * @throws ValidatorInitializationException If there's an issue initializing the validator.
     * @throws SQLException If there's an SQL related issue.
     * @throws DaoException If there's an issue with the DAO.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    @Test
    void testFindByNameNull() throws ServiceException {
        String eventName = null;
        EventService eventServiceLayer = createTestEventLayer();
        try {
            eventServiceLayer.findEventByName(eventName);
        } catch (ServiceException e) {
            Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT, e.getMessage());
        }
    }

}


