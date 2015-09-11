package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class BookingDAO {
	static Logger logger = Logger.getLogger(BookingDAO.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public BookingDAO() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public ArrayList<BookingDTO> getBookingOrder(int hotelId){
		ArrayList<BookingDTO> bookingOrder=new ArrayList<BookingDTO>();
		try {
			
			String query_cast = "SELECT * FROM Booking where HotelID = ? and Assign = 1";
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			bookorder.setInt(1, hotelId);
			ResultSet res = bookorder.executeQuery();
				
			
			
			while(res.next()){
				int bookingID = res.getInt("BookingID");
				logger.info(" "+bookingID);
				int customerID = res.getInt("CustomerID");
				logger.info(" "+customerID);
				Date check_In = res.getDate("Check_IN");
				logger.info(" "+check_In);
				Date check_out = res.getDate("Check_out");
				logger.info(" "+check_out);
				String type = res.getString("Type");
				float price = res.getFloat("Price");
				int hotelID = res.getInt("HotelID");
				String cityname = res.getString("Cityname");
				int assign = res.getInt("Assign");
				int emailsend = res.getInt("Emailsend");
				bookingOrder.add(new BookingDTO(bookingID,customerID,check_In,check_out,type,price,hotelID,cityname,assign, emailsend));
				
				
			}
			res.close();
			bookorder.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookingOrder;
		
	}
	
	public ArrayList<BookingDTO> getBookedOrder(int hotelId){
		ArrayList<BookingDTO> bookedOrder=new ArrayList<BookingDTO>();
		try {
			
			String query_cast = "SELECT * FROM Booking where HotelID = ? and Assign = 0";
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			bookorder.setInt(1, hotelId);
			ResultSet res = bookorder.executeQuery();
				
			while(res.next()){
				int bookingID = res.getInt("BookingID");
				logger.info(" "+bookingID);
				int customerID = res.getInt("CustomerID");
				logger.info(" "+customerID);
				Date check_In = res.getDate("Check_IN");
				logger.info(" "+check_In);
				Date check_out = res.getDate("Check_out");
				logger.info(" "+check_out);
				String type = res.getString("Type");
				float price = res.getFloat("Price");
				int hotelID = res.getInt("HotelID");
				String cityname = res.getString("Cityname");
				int assign = res.getInt("Assign");
				int emailsend = res.getInt("Emailsend");
				bookedOrder.add(new BookingDTO(bookingID,customerID,check_In,check_out,type,price,hotelID,cityname,assign,emailsend));
				
				
			}
			res.close();
			bookorder.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return bookedOrder;
	}
	
	
	
	
	public void bookingUpateAssign(int bookingID){
		String query_cast = "UPDATE Booking SET Assign = 0 WHERE BookingID = ?";
		try {
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,bookingID);
			 bookorder.executeUpdate();
				bookorder.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void bookingUpdateCheckout(int bookingID){
		String query_cast = "DELETE FROM Booking WHERE BookingID = ?";
		try {
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,bookingID);
			 bookorder.executeUpdate();
			 
				bookorder.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<BookingDTO> getCustomerBookingOrder(int customerId){
		ArrayList<BookingDTO> customerBookingOrder=new ArrayList<BookingDTO>();
		try {
			
			String query_cast = "SELECT * FROM Booking where CustomerID = ? and Assign = 1";
			
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			bookorder.setInt(1, customerId);
			ResultSet res = bookorder.executeQuery();
				
			
			while(res.next()){
				int bookingID = res.getInt("BookingID");
				logger.info(" "+bookingID);
				int customerID = res.getInt("CustomerID");
				logger.info(" "+customerID);
				Date check_In = res.getDate("Check_IN");
				logger.info(" "+check_In);
				Date check_out = res.getDate("Check_out");
				logger.info(" "+check_out);
				String type = res.getString("Type");
				float price = res.getFloat("Price");
				int hotelID = res.getInt("HotelID");
				String cityname = res.getString("Cityname");
				int assign = res.getInt("Assign");
				System.out.println("caonima a a a a a "+customerID);
				int emailsend = res.getInt("Emailsend");
				customerBookingOrder.add(new BookingDTO(bookingID,customerID,check_In,check_out,type,price,hotelID,cityname,assign,emailsend));
				
				
			}
			res.close();
			bookorder.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerBookingOrder;
		
	}
	
	

}
