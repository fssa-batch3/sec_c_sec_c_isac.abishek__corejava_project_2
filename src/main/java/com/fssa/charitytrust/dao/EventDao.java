package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.DaoExceptionErrors;
//import com.fssa.charitytrust.logger.Logger;
import com.fssa.charitytrust.model.Event;

//class for doing CRUD on event table
public class EventDao {
	public EventDao() {
		
	} 
	
	public static EventDao getEventDao(){
		return new EventDao();
	}

	static final String EVENTID = "event_id";

	public static boolean addEvent(Event event) throws SQLException, DaoException, ConnectionException {

		final String query = "INSERT INTO events (event_name, event_location, organizer_name, contact_number, event_date, image_url, about_event) VALUES (?, ?, ?, ?, ?, ?, ? )";
		// query for adding the values in the table
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				// setting the values in the question mark

				pst.setString(1, event.getEventName());
				pst.setString(2, event.getEventLocation());
				pst.setString(3, event.getOrganizerName());
				pst.setString(4, event.getContactNumber());
				pst.setDate(5, java.sql.Date.valueOf(event.getEventDate()));
				pst.setString(6, event.getImageUrl());
				pst.setString(7, event.getAboutEvent());

				int rs = pst.executeUpdate(); // executing the query and it returns the number of rows affected
				if (rs == 0) {

					throw new DaoException(DaoExceptionErrors.ROW_AFFECTED);
				}
			} // closing the prepared statement
		}
		return true;
	}

	public static boolean deleteEvent(String name) throws SQLException, DaoException, ConnectionException {

		final String query = "DELETE FROM events WHERE event_name = ?";
		// query for deleting the value in the table

		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				// setting the values in the question mark
				pst.setString(1, name);
				int rs = pst.executeUpdate();// executing the query and it returns the number of rows affected

				if (rs == 0) {
					throw new DaoException(DaoExceptionErrors.ROW_AFFECTED); // if no rows are affected, throw an
																				// exception
				}
			}
		}
		return true;
	}

	public static Event findEventByName(String name) throws SQLException, ConnectionException {
		final String query = "SELECT event_id,event_name,event_location,organizer_name,contact_number,image_url,about_event,event_date FROM events WHERE event_name=?";
		// query for finding an event by name in the table
		Event result = new Event(); // object created
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				pst.setString(1, name); // setting the values in the question mark

				try (ResultSet rs = pst.executeQuery()) { // executing the query

					if (rs.next()) { // Setting all the values to the object

						result.setEventId(rs.getInt(EVENTID));
						result.setEventName(rs.getString("event_name"));
						result.setEventLocation(rs.getString("event_location"));
						result.setOrganizerName(rs.getString("organizer_name"));
						result.setContactNumber(rs.getString("contact_number"));
						result.setImageUrl(rs.getString("image_url"));
						result.setAboutEvent(rs.getString("about_event"));
						result.setEventDate(rs.getDate("event_date"));
					}
					return result; // returning the result object	// connections are closed
				}
			}
			
		}
		

	}

	public static int getId(String name) throws SQLException, ConnectionException {

		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT event_id FROM events WHERE event_name='" + name + "'";
			try (Statement preparedStatement = con.createStatement()) {
				try (ResultSet id = preparedStatement.executeQuery(query)) {
					int id1 = 0;
					while (id.next()) {
						
						id1 = id.getInt(EVENTID);
					}

					
					System.out.println(id1);
					return id1;
				}
			} catch (SQLException ex) {
				throw new SQLException("ERROR");
			}
		}
	}

	public static boolean update(Event event) throws SQLException, ConnectionException {
		System.out.println("no ");
		try (Connection con = ConnectionUtil.getConnection()) {
			System.out.println("no ");
			final String query = "UPDATE events SET  event_name = ?, organizer_name = ?, event_location = ?, contact_number = ?,image_url = ?,event_date=?,about_event = ? WHERE event_id = ?";
			// query for updating the value in the table
			System.out.println("no ");
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, event.getEventName());
				System.out.println("no ");
				preparedStatement.setString(2, event.getOrganizerName());
				System.out.println("no ");
				preparedStatement.setString(3, event.getEventLocation());
				System.out.println("no ");
				preparedStatement.setString(4, event.getContactNumber());
				System.out.println("no ");
				preparedStatement.setString(5, event.getImageUrl());
				System.out.println("no ");
				preparedStatement.setDate(6, java.sql.Date.valueOf(event.getEventDate()));
				System.out.println("no ");
				preparedStatement.setString(7, event.getAboutEvent());
				System.out.println("no ");
				preparedStatement.setInt(8, getId(event.getEventName()));
				System.out.println("no ");
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

			
				return rowsAffected > 0;

			}

		}

	}

	public static List<Event> readFullEventList() throws SQLException, ConnectionException {
		
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			final String query = "SELECT event_id,event_name,event_location,organizer_name,contact_number,image_url,about_event,event_date FROM events";
			ArrayList<Event> resultlist = new ArrayList<>(); // arraylist declared
			try (PreparedStatement pst = con.prepareStatement(query)) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Event result = new Event();
						result.setEventId(rs.getInt(EVENTID));
						result.setEventName(rs.getString("event_name"));
						result.setEventLocation(rs.getString("event_location"));
						result.setOrganizerName(rs.getString("organizer_name"));
						result.setContactNumber(rs.getString("contact_number"));
						result.setImageUrl(rs.getString("image_url"));
						result.setAboutEvent(rs.getString("about_event"));
						result.setEventDate(rs.getDate("event_date"));
						resultlist.add(result); // objects are pushed
					}
				} // result set is closed
				return resultlist; // arraylist is returned
			}

		}

	}
	public static List<Event> viewEvents() throws SQLException,  ConnectionException {
		List<Event> resultList;
		resultList = readFullEventList();
		return resultList;
//		Logger.info(resultList);
//		return true;
	}
	
	

}
