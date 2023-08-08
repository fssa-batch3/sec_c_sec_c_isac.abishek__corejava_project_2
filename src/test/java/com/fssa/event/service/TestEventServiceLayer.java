package com.fssa.event.service;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fssa.event.dao.EventDao;
import com.fssa.event.exceptions.DaoException;
import com.fssa.event.exceptions.DaoExceptionErrors;
import com.fssa.event.exceptions.ValidatorInitializationException;
import com.fssa.event.model.Event;
import com.fssa.event.validator.EventValidator;

 class TestEventServiceLayer {

    public Event getEvent() {
    	LocalDate input=LocalDate.of(2023, 10, 10);
        Event event = new Event( "Gem Event", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert by ar");

        return event;
    }

    public EventServiceLayer getEventLayer() {
        EventValidator eventValidator = new EventValidator();
        EventDao eventDao = new EventDao();

        EventServiceLayer eventServiceLayer = new EventServiceLayer(eventValidator, eventDao);
        return eventServiceLayer;
    }

    @Test
    void testServiceAdd() throws  ValidatorInitializationException, SQLException, DaoException {
        Event event1 = getEvent();
        EventServiceLayer eventServiceLayer = getEventLayer();

        Assertions.assertTrue(eventServiceLayer.addEvent(event1));
    }

    
   
    @Test
    void testServiceAddNull() throws   DaoException {
        Event event1 = null;
        EventServiceLayer eventServiceLayer = getEventLayer();
        try {
            eventServiceLayer.addEvent(event1);
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(), DaoExceptionErrors.INVALID_INPUT);
        }
    }

    @Test
    void testServiceAddInvalid() throws  ValidatorInitializationException,DaoException,  SQLException {
    	LocalDate input=LocalDate.of(2023, 10, 10);
    	Event event1 = new Event("12345", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert");
        EventServiceLayer eventServiceLayer = getEventLayer();

        Assertions.assertFalse(eventServiceLayer.addEvent(event1));
    }

    @Test
   
    void testServiceUpdate() throws IllegalArgumentException, ValidatorInitializationException, SQLException, DaoException {
    	LocalDate input=LocalDate.of(2023, 10, 10);
    	Event eventNew = new Event( "Appolo Event", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert");
        Event updateEvent = new Event( "Appolo Event", "North Street, Taramani", "FreshTrust", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert by ar");
        EventServiceLayer eventServiceLayer1 = getEventLayer();
        eventServiceLayer1.addEvent(eventNew);
        Assertions.assertTrue(eventServiceLayer1.updateEvent(updateEvent));
    }

    @Test
    void testServiceUpdateNull() throws IllegalArgumentException, ValidatorInitializationException, SQLException, DaoException {
        Event event1 = null;
        EventServiceLayer eventServiceLayer = getEventLayer();
        try {
            eventServiceLayer.updateEvent(event1);
        } catch (Exception e) {
            Assertions.assertEquals( DaoExceptionErrors.INVALID_INPUT,e.getMessage());
        }
    }

    @Test
    void testServiceUpdateInvalid() throws IllegalArgumentException, ValidatorInitializationException, SQLException, DaoException {
    	LocalDate input=LocalDate.of(2023, 10, 10);
    	Event event1 = new Event( "12345", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert");
        EventServiceLayer eventServiceLayer = getEventLayer();

        Assertions.assertFalse(eventServiceLayer.updateEvent(event1));
    }

    @Test
    @Order(3)
    void testReadEvents() throws DaoException, SQLException, IllegalArgumentException, ValidatorInitializationException {
    	LocalDate input=LocalDate.of(2023, 10, 10);
    	Event event1 = new Event( "Balaji Event", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert ");
        EventServiceLayer eventServiceLayer1 = getEventLayer();
        eventServiceLayer1.addEvent(event1);
        Assertions.assertNotEquals(0, eventServiceLayer1.readEvents().size());

    }

    @Test
    void deleteEvent() throws SQLException, DaoException, ValidatorInitializationException {

        EventServiceLayer eventServiceLayer1 = getEventLayer();
        Assertions.assertTrue(eventServiceLayer1.deleteEvent("Balaji Event"));

    }

    @Test
    void deleteEventNull() throws SQLException, DaoException, ValidatorInitializationException {

        EventServiceLayer eventServiceLayer1 = getEventLayer();
        try {
            eventServiceLayer1.deleteEvent(null);
        } catch (Exception e) {
            Assertions.assertEquals(DaoExceptionErrors.INVALID_INPUT,e.getMessage() );
        }

    }

    @Test
    void deleteEventInvalid() throws SQLException, DaoException, ValidatorInitializationException {

        EventServiceLayer eventServiceLayer1 = getEventLayer();
        try {
            eventServiceLayer1.deleteEvent("InvalidName");
        } catch (Exception e) {
            Assertions.assertEquals(DaoExceptionErrors.ROW_AFFECTED,e.getMessage() );
        }

    }

    @Test
    void testFindByName() throws SQLException, DaoException, ValidatorInitializationException {
    	LocalDate input=LocalDate.of(2023, 10, 10);
    	Event event1 = new Event( "Gokul Event", "North Street, Taramani", "Organizer", "9751328805",
                "https://freeimage.host/i/HNRzLYJ.jpg",input, "Concert by gokul singer");

        EventServiceLayer eventServiceLayer1 = getEventLayer();
        eventServiceLayer1.addEvent(event1);
        Assertions.assertEquals(eventServiceLayer1.findEventByName(event1.getEventName()), event1);
    }

    @Test
    void testServiceFindByNameNull() throws IllegalArgumentException, ValidatorInitializationException, SQLException, DaoException {
        String event1 = null;
        EventServiceLayer eventServiceLayer = getEventLayer();
        try {
            eventServiceLayer.findEventByName(event1);
        } catch (Exception e) {
            Assertions.assertEquals( DaoExceptionErrors.INVALID_INPUT,e.getMessage());
        }
    }

}

