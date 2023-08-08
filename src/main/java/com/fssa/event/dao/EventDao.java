package com.fssa.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.connection.dao.ConnectionException;
import com.fssa.connection.dao.ConnectionUtil;
import com.fssa.event.exceptions.DaoException;
import com.fssa.event.exceptions.DaoExceptionErrors;
import com.fssa.event.model.Event;
import com.fssa.logger.Logger;

//class for doing CRUD on event table
public class EventDao {
	

	static final String EVENTID = "event_id";

	public static boolean addEvent(Event event) throws SQLException, DaoException, ConnectionException {

		final String query = "INSERT INTO EventList (event_name, event_location, organizer_name, contact_number, event_date, image_url, about_event) VALUES (?, ?, ?, ?, ?, ?, ? );";
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

		final String query = "DELETE FROM EventList WHERE event_name = ?";
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
		final String query = "SELECT * FROM EventList WHERE event_name=?";
		// query for finding an event by name in the table
		Event result = new Event(); // object created
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection

			try (PreparedStatement pst = con.prepareStatement(query)) { // prepare statement for query update
				pst.setString(1, name); // setting the values in the question mark

				try (ResultSet rs = pst.executeQuery()) { // executing the query

					while (rs.next()) { // Setting all the values to the object

						result.setEventId(rs.getInt(EVENTID));
						result.setEventName(rs.getString("event_name"));
						result.setEventLocation(rs.getString("event_location"));
						result.setOrganizerName(rs.getString("organizer_name"));
						result.setContactNumber(rs.getString("contact_number"));
						result.setImageUrl(rs.getString("image_url"));
						result.setAboutEvent(rs.getString("about_event"));
						result.setEventDate(rs.getDate("event_date"));
					}
					// connections are closed
				}
			}
		}
		return result; // returning the result object

	}

	public static int getId(String name) throws SQLException, ConnectionException {

		try (Connection con = ConnectionUtil.getConnection()) {
			final String query = "SELECT event_id FROM EventList WHERE event_name='" + name + "'";
			try (Statement preparedStatement = con.createStatement()) {
				try (ResultSet id = preparedStatement.executeQuery(query)) {
					int id1 = 0;
					while (id.next()) {
						id1 = id.getInt(EVENTID);
					}

					
					Logger.info("Last ID: " + id1);
					return id1;
				}
			} catch (SQLException ex) {
				throw new SQLException("ERROR");
			}
		}
	}

	public static boolean update(Event event) throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) {

			final String query = "UPDATE EventList SET  event_name = ?, organizer_name = ?, event_location = ?, contact_number = ?,image_url = ?,event_date=?,about_event = ? WHERE event_id = ?;";
			// query for updating the value in the table
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, event.getEventName());
				preparedStatement.setString(2, event.getOrganizerName());
				preparedStatement.setString(3, event.getEventLocation());
				preparedStatement.setString(4, event.getContactNumber());
				preparedStatement.setString(5, event.getImageUrl());
				preparedStatement.setDate(6, java.sql.Date.valueOf(event.getEventDate()));
				preparedStatement.setString(7, event.getAboutEvent());
				preparedStatement.setInt(8, getId(event.getEventName()));
				// Execute the update
				int rowsAffected = preparedStatement.executeUpdate();

				return rowsAffected > 0;

			}

		}

	}

	public static List<Event> readFullEventList() throws SQLException, ConnectionException {
		try (Connection con = ConnectionUtil.getConnection()) { // getting connection
			final String query = "SELECT * FROM EventList";
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

}
