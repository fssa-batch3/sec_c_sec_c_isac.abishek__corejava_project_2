package com.fssa.charitytrust.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.dao.EventDao;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
import com.fssa.charitytrust.exceptions.ServiceException;
import com.fssa.charitytrust.exceptions.ValidatorInitializationException;
import com.fssa.charitytrust.model.Event;
import com.fssa.charitytrust.validator.EventValidator;

/**
 * The service layer class that acts as an intermediary between the
 * application's business logic and data access layer.
 */
public class EventServiceLayer {


	private  EventValidator eventValidator;
	public EventValidator getEventValidator() {
		return eventValidator;
	}

	private  EventDao eventDao;

	public EventDao getEventDao() {
		return eventDao;
	}



	// Constructor to initialize the service layer with EventValidator and EventDao
	// instances.
	public EventServiceLayer(EventValidator eventValidator, EventDao eventDao) {
		this.eventValidator = eventValidator;
		this.eventDao = eventDao;
	}
	
	

	/**
	 * Adds a new event to the system if it passes validation checks.
	 * 
	 * @param event The event object to be added.
	 * @return true if the event is successfully added, false otherwise.
	 * @throws IllegalArgumentException         if the event object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public boolean addEvent(Event event)
			throws  ServiceException {

		if (event == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (EventValidator.validate(event)) {

				return EventDao.addEvent(event);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Updates an existing event in the system if it passes validation checks.
	 *
	 * @param event The updated event object.
	 * @return true if the event is successfully updated, false otherwise.
	 * @throws IllegalArgumentException         if the event object is null.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public boolean updateEvent(Event event)
			throws ServiceException {
		
		if (event == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (EventValidator.validate(event)) {
				return EventDao.update(event);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retrieves a list of all events from the system.
	 *
	 * @return An ArrayList containing all events in the system.
	 * @throws IllegalArgumentException         if there is an issue with
	 *                                          initializing the validator.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ConnectionException 
	 */
	public List<Event> readEvents()
			throws ServiceException {
		try {
			return EventDao.readFullEventList();
		} catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Deletes an event from the system by its name.
	 *
	 * @param name The name of the event to be deleted.
	 * @return true if the event is successfully deleted, false otherwise.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws ConnectionException 
	 */
	public boolean deleteEvent(String name) throws ServiceException {
		if (name == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (EventValidator.validateEventName(name)) {
				return EventDao.deleteEvent(name);
			} else {
				return false;
			}
		} catch (ValidatorInitializationException | SQLException | DaoException | ConnectionException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Finds an event in the system by its name.
	 *
	 * @param name The name of the event to be found.
	 * @return The Event object if found, null otherwise.
	 * @throws SQLException                     if there is an issue with the SQL
	 *                                          database operation.
	 * @throws DaoException                     if there is an issue with the data
	 *                                          access layer.
	 * @throws ValidatorInitializationException if there is an issue with
	 *                                          initializing the validator.
	 * @throws ConnectionException 
	 */
	public Event findEventByName(String name) throws ServiceException {
		if (name == null) {
			throw new  ServiceException(DaoExceptionErrors.INVALID_INPUT);
		}
		try {
			if (EventValidator.validateEventName(name)) {
				return EventDao.findEventByName(name);
			} else {
				return null;
			}
		} catch (ValidatorInitializationException | SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Event> viewEvent() throws ServiceException {

		try {
			return EventDao.viewEvents();
		} catch (SQLException | ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
