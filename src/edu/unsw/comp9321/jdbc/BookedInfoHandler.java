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

public class BookedInfoHandler {
	static Logger logger = Logger.getLogger(BookedInfoHandler.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public BookedInfoHandler() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	public ArrayList<BookedInfoBean> getAllBookedInfo(){
		ArrayList<BookedInfoBean> bookedInfo = new ArrayList<BookedInfoBean>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM BookedInfo";
			ResultSet res = stmnt.executeQuery(query_cast);
	
			while(res.next()){		
				int bookingID = res.getInt("BookingID");
				logger.info(" "+bookingID);
				int roomID = res.getInt("RoomID");
				logger.info(" "+roomID);
				bookedInfo.add(new BookedInfoBean(bookingID,roomID));
			}
			
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}

		return bookedInfo;
	}
	
	public void bookedInfoInsert(int bookingID,int roomID){
		String query_cast = "INSERT INTO BookedInfo values(?,?)";
		PreparedStatement bookorder;
		try {
			bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,bookingID);
			 bookorder.setInt(2,roomID);
			 bookorder.executeUpdate();
			 bookorder.close();
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void bookedInfoDelet(int roomID){
		String query_cast = "DELETE FROM BookedInfo where RoomID = ?";
		PreparedStatement bookorder;
		try {
			bookorder = connection.prepareStatement(query_cast);
			bookorder.setInt(1,roomID);
			bookorder.executeUpdate();
			bookorder.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
