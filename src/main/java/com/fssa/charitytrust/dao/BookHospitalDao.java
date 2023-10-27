package com.fssa.charitytrust.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.charitytrust.connection.ConnectionException;
import com.fssa.charitytrust.connection.ConnectionUtil;
import com.fssa.charitytrust.exceptions.BookingErrors;
import com.fssa.charitytrust.exceptions.DaoException;
import com.fssa.charitytrust.exceptions.UserServiceErrors;
import com.fssa.charitytrust.model.BookHospital;
import com.fssa.charitytrust.model.User;
import com.fssa.charitytrust.model.UserRole;

public class BookHospitalDao {
	public static boolean addBooking(BookHospital bookhospital) throws DaoException, ConnectionException {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "insert into hospitalbookings(username,hospital_name,contact,email,bookdate) values (?,?,?,?,?)";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, bookhospital.getUsername());
				pst.setString(2, bookhospital.getHospitalname());
				pst.setString(3, bookhospital.getContactNumber());
				pst.setString(4, bookhospital.getEmail());
				pst.setDate(5, java.sql.Date.valueOf( bookhospital.getBookdate()));
				

				int rows = pst.executeUpdate();

				return rows > 0;
			}
		} catch (Exception e) {
                e.printStackTrace();
			throw new DaoException(BookingErrors.UNABLE_TO_ADD_FORM);
		}
	}
	
	public static List<BookHospital> getBookingByEmail(String email) throws DaoException, ConnectionException {
		ArrayList<BookHospital> userArray = new ArrayList<>();
		BookHospital bookhospital = null;

		try (Connection con = ConnectionUtil.getConnection()) {
			String query = "SELECT id,contact,username,hospital_name,email,bookdate FROM hospitalbookings where email = ?";

			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {

					bookhospital = new BookHospital();
					bookhospital.setId(rs.getInt("id"));
					bookhospital.setContactNumber(rs.getString("contact"));
					bookhospital.setUsername(rs.getString("username"));
					bookhospital.setHospitalname(rs.getString("hospital_name"));
					bookhospital.setEmail(rs.getString("email"));
					bookhospital.setBookdate(rs.getDate("bookdate").toLocalDate());
					userArray.add(bookhospital);
				}
			}
		} catch (SQLException e) {
			throw new DaoException(BookingErrors.UNABLE_TO_VIEW_BOOKING);
		}

		return userArray;

	}
}
