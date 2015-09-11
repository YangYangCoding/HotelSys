package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;


public class RoomHandler {
	
	static Logger logger = Logger.getLogger(RoomHandler.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public RoomHandler() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public ArrayList<RoomBean> getOccupiedRoom(int hotelID,String type){
		ArrayList<RoomBean> occupiedRoom = new ArrayList<RoomBean>();
		try {
			
			if(type.equals("all")){
				String query_cast = "SELECT * FROM Room where Available = 0 and HotelID = ?";
				PreparedStatement bookorder = connection.prepareStatement(query_cast);
				 bookorder.setInt(1,hotelID);
				 ResultSet res = bookorder.executeQuery();
				 while(res.next()){
						int roomId = res.getInt("roomId");
						logger.info(" "+roomId);
						int hotelId = res.getInt("hotelId");
						logger.info(" "+hotelId);
						String roomType = res.getString("type");
						logger.info(" "+roomType);
						int numberBeds = res.getInt("numBeds");
						logger.info(" "+numberBeds);
						float price = res.getFloat("price");
						logger.info(" "+price);
						int available = res.getInt("available");
						logger.info(" "+available);
						occupiedRoom.add(new RoomBean(roomId,hotelId,roomType,numberBeds,price,available));
					}
				 	res.close();
					bookorder.close();
					
				} 
				
			else{
				String query_cast = "SELECT * FROM Room where Available = 0 and HotelID = ? and Type = ?";
				PreparedStatement bookorder = connection.prepareStatement(query_cast);
				 bookorder.setInt(1,hotelID);
				 bookorder.setString(2, type);
				 ResultSet res = bookorder.executeQuery();
				while(res.next()){
					int roomId = res.getInt("roomId");
					logger.info(" "+roomId);
					int hotelId = res.getInt("hotelId");
					logger.info(" "+hotelId);
					String roomType = res.getString("type");
					logger.info(" "+roomType);
					int numberBeds = res.getInt("numBeds");
					logger.info(" "+numberBeds);
					float price = res.getFloat("price");
					logger.info(" "+price);
					int available = res.getInt("available");
					logger.info(" "+available);
					occupiedRoom.add(new RoomBean(roomId,hotelId,roomType,numberBeds,price,available));
				}
				res.close();
				bookorder.close();
			}
		}
			
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return occupiedRoom;
	
	
	}
		
			

	
	public ArrayList<RoomBean> getAvailableRoom(int hotelID,String type){
		ArrayList<RoomBean> availableRoom = new ArrayList<RoomBean>();
		try {
			
			if(type.equals("all")){
				String query_cast = "SELECT * FROM Room where Available = 1 and HotelID = ?";
				PreparedStatement bookorder = connection.prepareStatement(query_cast);
				 bookorder.setInt(1,hotelID);
				 ResultSet res = bookorder.executeQuery();
				 while(res.next()){
						int roomId = res.getInt("roomId");
						logger.info(" "+roomId);
						int hotelId = res.getInt("hotelId");
						logger.info(" "+hotelId);
						String roomType = res.getString("type");
						logger.info(" "+roomType);
						int numberBeds = res.getInt("numBeds");
						logger.info(" "+numberBeds);
						float price = res.getFloat("price");
						logger.info(" "+price);
						int available = res.getInt("available");
						logger.info(" "+available);
						availableRoom.add(new RoomBean(roomId,hotelId,roomType,numberBeds,price,available));
					}
				 	res.close();
					bookorder.close();
					
				} 
				
			else{
				String query_cast = "SELECT * FROM Room where Available = 1 and HotelID = ? and Type = ?";
				PreparedStatement bookorder = connection.prepareStatement(query_cast);
				 bookorder.setInt(1,hotelID);
				 bookorder.setString(2, type);
				 ResultSet res = bookorder.executeQuery();
				while(res.next()){
					int roomId = res.getInt("roomId");
					logger.info(" "+roomId);
					int hotelId = res.getInt("hotelId");
					logger.info(" "+hotelId);
					String roomType = res.getString("type");
					logger.info(" "+roomType);
					int numberBeds = res.getInt("numBeds");
					logger.info(" "+numberBeds);
					float price = res.getFloat("price");
					logger.info(" "+price);
					int available = res.getInt("available");
					logger.info(" "+available);
					availableRoom.add(new RoomBean(roomId,hotelId,roomType,numberBeds,price,available));
				}
				res.close();
				bookorder.close();
			}
		}
			
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return availableRoom;
		
		
	}
	public void roomAssign(int roomID){
		String query_cast = "UPDATE Room SET Available = 0 WHERE RoomID = ?";
		try {
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,roomID);
			 bookorder.executeUpdate();
				bookorder.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void roomCheckout(int roomID){
		String query_cast = "UPDATE Room SET Available = 1 WHERE RoomID = ?";
		try {
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,roomID);
			 bookorder.executeUpdate();
				bookorder.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

