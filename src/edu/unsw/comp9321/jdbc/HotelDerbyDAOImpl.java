package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.List;
import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.EmptyResultException;

public class HotelDerbyDAOImpl {
	static Logger logger = Logger.getLogger(HotelDerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public HotelDerbyDAOImpl() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public 	List<HotelDTO> findRoomNum(int hotelid) {
		List<HotelDTO> hotel = new ArrayList<HotelDTO>();
		try{
			//Statement stmnt = connection.createStatement();
			String query_roomnum = "SELECT * FROM Hotel WHERE HotelID = ?";
			PreparedStatement roomnum_stmnt = connection.prepareStatement(query_roomnum);
			roomnum_stmnt.setInt(1, hotelid);
			ResultSet res = roomnum_stmnt.executeQuery();
			res.next();
			
			//logger.info("The result set size is "+res.getFetchSize());
			
			int hotelID = res.getInt("HotelID");
			logger.info(" "+hotelID);
			String hotelname = res.getString("Hotelname");
			logger.info(hotelname);
			int cityID = res.getInt("CityID");
			logger.info(" "+cityID);
			int singleroomNum = res.getInt("SingleroomNum");
			logger.info(" "+singleroomNum);
			int twinbedNum = res.getInt("TwinbedNum");
			logger.info(" "+twinbedNum);
			int queenNum = res.getInt("QueenNum");
			logger.info(" "+queenNum);
			int executiveNum = res.getInt("ExecutiveNum");
			logger.info(" "+executiveNum);
			int suiteNum = res.getInt("SuiteNum");
			logger.info(" "+suiteNum);
			hotel.add(new HotelDTO(hotelID, hotelname, cityID, singleroomNum, twinbedNum, 
				        queenNum, executiveNum, suiteNum));
			
			
			res.close();
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return hotel;
		
	}
	
	public 	List<BookingDTO> BookingCount(int hotelid, float room_price, Date checkin, Date checkout) {
		List<BookingDTO> not_ava_room = new ArrayList<BookingDTO>();
		//System.out.println("go booking");
		try{
			//Statement stmnt = connection.createStatement();
			if (room_price < 70) {
				return not_ava_room;
			}
			String query_noava_room = "SELECT * FROM Booking WHERE HotelID = ? AND Price <= ?";
			PreparedStatement roomnum_stmnt = connection.prepareStatement(query_noava_room);
			roomnum_stmnt.setInt(1, hotelid);
			roomnum_stmnt.setFloat(2, room_price);
			ResultSet res = roomnum_stmnt.executeQuery();
			
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				
				Date check_IN = fmt.parse(res.getString("Check_IN"));
				logger.info(" "+check_IN);
				Date check_OUT = fmt.parse(res.getString("Check_out"));
				logger.info(" "+check_OUT);
				//System.out.println(" "+check_IN);
				//System.out.println(" "+check_OUT);
				if ((check_IN.compareTo(checkin) <= 0 && check_OUT.compareTo(checkout) >= 0) || 
					(check_OUT.compareTo(checkout) <= 0 && check_OUT.compareTo(checkin) >= 0)	||
					(check_IN.compareTo(checkout) <= 0 && check_IN.compareTo(checkin) >= 0)) {
				
					String type = res.getString("Type");
					logger.info(type);
					float price = res.getFloat("Price");
					logger.info(" "+price);
					int hotelID = res.getInt("HotelID");
					logger.info(" "+hotelID);
					String cityname = res.getString("Cityname");
					logger.info(" "+cityname);
					int assign = res.getInt("Assign");
					logger.info(" "+assign);
					int bookingID = res.getInt("BookingID");
					logger.info(" "+bookingID);
					int customerID = res.getInt("CustomerID");
					logger.info(" "+customerID);
					int emailsend = res.getInt("Emailsend");
					not_ava_room.add(new BookingDTO(bookingID, customerID, check_IN, check_OUT, type, 
							price, hotelID, cityname, assign, emailsend));
				}
			}
			res.close();
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return not_ava_room;
		
	}
	
	
	public 	List<RoomTypePriceDTO> getTypePrice() {
		List<RoomTypePriceDTO> typeprice = new ArrayList<RoomTypePriceDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM TypeRoom";
			ResultSet res = stmnt.executeQuery(query_cast);
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				String roomtype = res.getString("RoomType");
				logger.info(roomtype);
				float price = res.getInt("Price");
				logger.info(" "+price);
				typeprice.add(new RoomTypePriceDTO(roomtype, price));
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return typeprice;
	}
	
	public 	List<OwnerDTO> getOwnerInfo() {
		List<OwnerDTO> ownerinfo = new ArrayList<OwnerDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM Owner";
			ResultSet res = stmnt.executeQuery(query_cast);
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				String owneraccount = res.getString("Owneraccount");
				logger.info(owneraccount);
				String ownerpassword = res.getString("Ownerpassword");
				logger.info(ownerpassword);
				int ownerid = res.getInt("OwnerID");
				logger.info(" "+ownerid);
				ownerinfo.add(new OwnerDTO(ownerid, owneraccount, ownerpassword));
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return ownerinfo;
	}
	
	public 	int getOccupiedRoom(int i) {
		int nb_of_occp = 0;
		try{
			String query_cast = "SELECT Available FROM Room WHERE HotelID = ?";
			PreparedStatement stmnt = connection.prepareStatement(query_cast);
			stmnt.setInt(1, i);
			ResultSet res = stmnt.executeQuery();
			
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int ava = res.getInt("Available");
				if (ava == 0) {
					nb_of_occp++;
				}
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return nb_of_occp;
	}
	
	public 	int getAvailableRoom(int i) {
		int nb_of_ava = 0;
		try{
			String query_cast = "SELECT Available FROM Room WHERE HotelID = ?";
			PreparedStatement stmnt = connection.prepareStatement(query_cast);
			stmnt.setInt(1, i);
			ResultSet res = stmnt.executeQuery();
				
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int ava = res.getInt("Available");
				if (ava == 1) {
					nb_of_ava++;
				}
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return nb_of_ava;
	}
	
	public List<DiscountDTO> discountvalid(int hotelid, float discount, String type, Date sdate, Date edate) {
		List<DiscountDTO> discountinfo = new ArrayList<DiscountDTO>();
		try{
			boolean conflict = false;
			int discountID;
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM Discount";
			ResultSet res = stmnt.executeQuery(query_cast);
			
			while(res.next()) {
				
				Date start_date = fmt.parse(res.getString("Startdate"));
				logger.info(" "+start_date);
				Date end_date = fmt.parse(res.getString("Enddate"));
				logger.info(" "+end_date);
				//System.out.println(" db"+start_date+"add"+sdate);
				//System.out.println("db "+end_date+"add"+edate);
				int hotelID = res.getInt("HotelID");
				logger.info(" "+hotelID);
				String roomtype = res.getString("Type");
				logger.info(roomtype);
				float disc = res.getFloat("Discount");
				logger.info(" "+disc);
				//System.out.println("coming hid"+hotelid);
				if (hotelid == hotelID && type.equals(roomtype)) {
					if (edate.compareTo(start_date) < 0 || sdate.compareTo(end_date) > 0) {
						System.out.println("next");
						continue;
					}
					else {
						// cant add discount info
						//System.out.println("conflict!!! ");
						conflict = true;
					}
				}
				else {
					//System.out.println("dame next");
					continue;
				}
			}
			if (conflict) {
				discountID = -1;	
			}
			else {
				discountID = 0;
			}
			res.close();
			
			String query_name = "SELECT Hotelname FROM Hotel WHERE HotelID = ?";
			PreparedStatement name_stmnt = connection.prepareStatement(query_name);
			name_stmnt.setInt(1, hotelid);
			ResultSet name_res = name_stmnt.executeQuery();
			name_res.next();
			String hotelname = name_res.getString("Hotelname");
			
			discountinfo.add(new DiscountDTO(discountID, hotelid, hotelname, discount, type, sdate, edate));
			name_res.close();
			
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return discountinfo;
	}
	
	public void setdiscount(int hotelid, float discount, String type, Date sdate, Date edate) {
		List<DiscountDTO> discountinfo = new ArrayList<DiscountDTO>();
		try{
			int discountID = 1;
			//get the largest discountID
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT DiscountID FROM Discount";
			ResultSet res = stmnt.executeQuery(query_cast);
			while(res.next()) {
				int cur_id = res.getInt("DiscountID");
				if (discountID <= cur_id) {
					discountID = cur_id + 1;
				}
			}
			
			
			res.close();
			// insert info into table Discount
			String query_dis = "insert into Discount values (?, ?, ?, ?, ?, ?)";
			PreparedStatement dis_stmnt = connection.prepareStatement(query_dis);
			System.out.println(" did "+discountID);
			dis_stmnt.setInt(1, discountID);
			dis_stmnt.setInt(2, hotelid);
			dis_stmnt.setFloat(3, discount);
			dis_stmnt.setString(4, type);
			
			//java.util.Date utilDate = affiliate.getDate();
			java.sql.Date sql_sdate = new java.sql.Date(sdate.getTime());
			java.sql.Date sql_edate = new java.sql.Date(edate.getTime());
			dis_stmnt.setDate(5, sql_sdate);
			dis_stmnt.setDate(6, sql_edate);
			
			dis_stmnt.executeUpdate();
			dis_stmnt.close();
			
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}
	
	public void delsetdiscount(int hotelid, float discount, String type, Date sdate, Date edate) {
		List<DiscountDTO> discountinfo = new ArrayList<DiscountDTO>();
		try{
			// delete conflict discount first
			Statement cf_stmnt = connection.createStatement();
			String query_cf = "SELECT * FROM Discount";
			ResultSet res = cf_stmnt.executeQuery(query_cf);
			
			while(res.next()) {
				
				Date start_date = fmt.parse(res.getString("Startdate"));
				logger.info(" "+start_date);
				Date end_date = fmt.parse(res.getString("Enddate"));
				logger.info(" "+end_date);
				//System.out.println(" db"+start_date+"add"+sdate);
				//System.out.println("db "+end_date+"add"+edate);
				int hotelID = res.getInt("HotelID");
				logger.info(" "+hotelID);
				int discountID = res.getInt("DiscountID");
				logger.info(" "+discountID);
				String roomtype = res.getString("Type");
				logger.info(roomtype);
				float disc = res.getFloat("Discount");
				logger.info(" "+disc);
				//System.out.println("coming hid"+hotelid);
				if (hotelid == hotelID && type.equals(roomtype)) {
					if (edate.compareTo(start_date) < 0 || sdate.compareTo(end_date) > 0) {
						//System.out.println("next");
						continue;
					}
					else {
						// cant add discount info
						//System.out.println("conflict!!! ");
						String query_del = "delete from Discount where DiscountID = ?";
						PreparedStatement del_stmnt = connection.prepareStatement(query_del);
						//System.out.println(" did "+discountID);
						del_stmnt.setInt(1, discountID);
						
						del_stmnt.executeUpdate();
					}
				}
				else {
					//System.out.println("dame next");
					continue;
				}
			}
			res.close();
			
			int discountID = 1;
			//get the largest discountID
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT DiscountID FROM Discount";
			ResultSet insert_res = stmnt.executeQuery(query_cast);
			while(insert_res.next()) {
				int cur_id = insert_res.getInt("DiscountID");
				if (discountID <= cur_id) {
					discountID = cur_id + 1;
				}
			}
			
			
			insert_res.close();
			// insert info into table Discount
			String query_dis = "insert into Discount values (?, ?, ?, ?, ?, ?)";
			PreparedStatement dis_stmnt = connection.prepareStatement(query_dis);
			System.out.println(" did "+discountID);
			dis_stmnt.setInt(1, discountID);
			dis_stmnt.setInt(2, hotelid);
			dis_stmnt.setFloat(3, discount);
			dis_stmnt.setString(4, type);
			
			//java.util.Date utilDate = affiliate.getDate();
			java.sql.Date sql_sdate = new java.sql.Date(sdate.getTime());
			java.sql.Date sql_edate = new java.sql.Date(edate.getTime());
			dis_stmnt.setDate(5, sql_sdate);
			dis_stmnt.setDate(6, sql_edate);
			
			dis_stmnt.executeUpdate();
			dis_stmnt.close();
			
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}
	
	public boolean checkValidDate(String checkDate,String dateFormat){
		if(checkDate == null){
			return false;
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			
			
			DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = new Date();
			String currentDate = dateFormat2.format(date2);
			//System.out.println("aaaaaaaaaaaa"+currentDate);
			Date date;
			try {
				//if not valid, it will throw ParseException
				date = sdf.parse(checkDate);
				//System.out.println("zhuanhuawan"+date);
				
			} catch (ParseException e) {
				//System.out.println("parsefail");
				e.printStackTrace();
				return false;
			}
			
			
			Date current;
			
			
			try {
				current = sdf.parse(currentDate);
				if(date.before(current)){
					return false;
				}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return true;
		}
	}
	
	
	
	public boolean checkValidScope(Date input,Date output){
		
		if(input.after(output)){
			return false;
		}
		return true;
	}
	
	
	public boolean checkVaildRoomNum(String roomNum){
		int roomNum2;
		try{
			roomNum2 = Integer.parseInt(roomNum);
		}catch(NumberFormatException e){
			 e.printStackTrace();
			 return false;
		}
		
		if(roomNum2<=0)
			return false;
		return true;
	}
	
	
	
	public boolean checkPrice(String price){
		int price2;
		try{
			price2 = Integer.parseInt(price);
		}catch(NumberFormatException e){
			 e.printStackTrace();
			 return false;
		}
		
		if(price2 <=0)
			return false;
		return true;
	}
	
	public boolean checkdiscount(String discount){
		float discount2;
		try{
			discount2 = Float.parseFloat(discount);
		}catch(NumberFormatException e){
			 e.printStackTrace();
			 return false;
		}
		if(discount2 <=0 || discount2 >= 1)
			return false;
		return true;
	}
	
	public boolean checkPeak(Date checkin, Date checkout) {
		List<PeakDTO> peakdate = new ArrayList<PeakDTO>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM Peak";
			ResultSet res = stmnt.executeQuery(query_cast);
			//logger.info("The result set size is "+res.getFetchSize());
			int change = 0;
			while(res.next()){
				change++;
				Date peakstart = fmt.parse(res.getString("Peakstart"));
				Date peakend = fmt.parse(res.getString("Peakend"));
				peakstart.setYear(checkin.getYear());
				if (change == 2) { 
					//System.out.println("lala1 "+peakend);
					peakend.setYear(checkout.getYear() + 1);
				}
				else {
					//System.out.println("lala "+peakend);
					peakend.setYear(checkout.getYear());
				}
				//System.out.println(" db"+peakstart);
				//System.out.println("db "+peakend);
				//start check
				if (checkin.compareTo(peakend) > 0 || checkout.compareTo(peakstart) < 0) {
					continue;
				}
				else {
					return true;
				}
			}
			
			res.close();
			stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	    return false;
	}
	
	public float checkdiscountnow (int hotelid, String type, Date checkin, Date checkout) {
		List<DiscountDTO> discountinfo = new ArrayList<DiscountDTO>();
		float discount = 1;
		try{
			
			String query_cast = "SELECT * FROM Discount WHERE HotelID = ? AND Type = ?";
			PreparedStatement stmnt = connection.prepareStatement(query_cast);
			stmnt.setInt(1, hotelid);
			stmnt.setString(2, type);
			ResultSet res = stmnt.executeQuery();
			
			while(res.next()) {
				
				Date start_date = fmt.parse(res.getString("Startdate"));
				Date end_date = fmt.parse(res.getString("Enddate"));
				
				//System.out.println(" db"+start_date+"add"+sdate);
				//System.out.println("db "+end_date+"add"+edate);
				if (checkin.compareTo(end_date) > 0 || checkout.compareTo(start_date) < 0) {
					continue;
				}
				else {
					discount = res.getFloat("Discount");
					return discount;
				}
				
			}
			res.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return discount;
	}
	
	public long getdiffbetweendates (Date startdate, Date enddate) {
		long days = 0;
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = startdate;
			d2 = enddate;
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			days = diff / (24 * 60 * 60 * 1000);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return days;
	}
	
	public String toddMMyy(Date day){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String date = formatter.format(day);
		 return date;
	}
	
	public void insertBooking (int bookingid, int customerid, Date checkin, Date checkout, String type,
			float price, int hotelid) {
		try{
			int assign = 1;
			int emailsend = 0;
			String query_booking = "insert into Booking values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement dis_stmnt = connection.prepareStatement(query_booking);
			//System.out.println(" did "+discountID);
			String cityname = "";
			if (hotelid == 1) {
	            cityname = "Sydney";
	        }
	        else if (hotelid == 2) {
	            cityname = "Brisbane";
	        }
	        else if (hotelid == 3) {
	            cityname = "Melbourne";
	        }
	        else if (hotelid == 4) {
	            cityname = "Adelaide";
	        }
	        else if (hotelid == 5) {
	            cityname = "Hobart";
	        }
			
			dis_stmnt.setInt(1, bookingid);
			dis_stmnt.setInt(2, customerid);
			dis_stmnt.setFloat(6, price);
			dis_stmnt.setString(5, type);
			
			//java.util.Date utilDate = affiliate.getDate();
			java.sql.Date sql_sdate = new java.sql.Date(checkin.getTime());
			java.sql.Date sql_edate = new java.sql.Date(checkout.getTime());
			dis_stmnt.setDate(3, sql_sdate);
			dis_stmnt.setDate(4, sql_edate);
			dis_stmnt.setInt(7, hotelid);
			dis_stmnt.setString(8, cityname);
			dis_stmnt.setInt(9, assign);
			dis_stmnt.setInt(10, emailsend);
			
			dis_stmnt.executeUpdate();
			dis_stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
	
	public int genr_new_bookingid() {
		int bookingid = 1;
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT BookingID FROM Booking";
			ResultSet res = stmnt.executeQuery(query_cast);
			while(res.next()) {
				int cur_id = res.getInt("BookingID");
				if (bookingid <= cur_id) {
					bookingid = cur_id + 1;
				}
			}
			res.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}

		return bookingid;
	}
	
	public int genr_new_customerid() {
		int customerid = 1;
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT CustomerID FROM Customer";
			ResultSet res = stmnt.executeQuery(query_cast);
			while(res.next()) {
				int cur_id = res.getInt("CustomerID");
				if (customerid <= cur_id) {
					customerid = cur_id + 1;
				}
			}
			res.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}

		return customerid;
	}
	
	public String genr_new_pin() {
		
		
		//String pin = UUID.randomUUID().toString();
		String pin = "123";
		return pin;
	}
	
	public void insertCustomer (int customerid, String name, String pin, String email, String cardnum) {
		try{
			
			String query_booking = "insert into Customer values (?, ?, ?, ?, ?)";
			PreparedStatement dis_stmnt = connection.prepareStatement(query_booking);
		
		
			
			dis_stmnt.setInt(1, customerid);
			dis_stmnt.setString(3, pin);
			dis_stmnt.setString(2, name);
			dis_stmnt.setString(4, email);

			dis_stmnt.setString(5, cardnum);			
			
			dis_stmnt.executeUpdate();
			dis_stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
	
	public 	ArrayList<CustomerDTO> getCustomerInfoByPin(String pin) {
		ArrayList<CustomerDTO> customerinfo = new ArrayList<CustomerDTO>();
		try{
			
			//Statement stmnt = connection.createStatement();
			//String query_cast = "SELECT * FROM Customer";
			//ResultSet res = stmnt.executeQuery(query_cast);
			
			String query_cast = "SELECT * FROM Customer where Pin = ?";
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			bookorder.setString(1, pin);
			ResultSet res = bookorder.executeQuery();
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int customerID = res.getInt("CustomerID");
				logger.info(" "+customerID);
				String customerName = res.getString("Name");
				logger.info(customerName);
				String pin2 = res.getString("Pin");
				logger.info(" "+pin);
				String email = res.getString("Email");
				logger.info(" "+email);
				String customerCardNum = res.getString("Cardnum");
				logger.info(" "+customerCardNum);
				
				customerinfo.add(new CustomerDTO(customerID, customerName, pin2, email, customerCardNum));
				
			}
			
			res.close();
			bookorder.close();
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return customerinfo;
	}
	
	
	public 	ArrayList<CustomerDTO> getCustomerInfo(int customerId) {
		ArrayList<CustomerDTO> customerinfo = new ArrayList<CustomerDTO>();
		try{
			
			//Statement stmnt = connection.createStatement();
			//String query_cast = "SELECT * FROM Customer";
			//ResultSet res = stmnt.executeQuery(query_cast);
			
			String query_cast = "SELECT * FROM Customer where CustomerID = ?";
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			bookorder.setInt(1, customerId);
			ResultSet res = bookorder.executeQuery();
			//logger.info("The result set size is "+res.getFetchSize());
			while(res.next()){
				int customerID = res.getInt("CustomerID");
				logger.info(" "+customerID);
				String customerName = res.getString("Name");
				logger.info(customerName);
				String pin = res.getString("Pin");
				logger.info(" "+pin);
				String email = res.getString("Email");
				logger.info(" "+email);
				String customerCardNum = res.getString("Cardnum");
				logger.info(" "+customerCardNum);
				
				customerinfo.add(new CustomerDTO(customerID, customerName, pin, email, customerCardNum));
				
			}
			
			res.close();
			bookorder.close();
			//stmnt.close();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return customerinfo;
	}
	
	public void cancelbooking (int cusid) {
		
		try{
			
			String query_del = "delete from Booking where CustomerID = ?";
			PreparedStatement del_stmnt = connection.prepareStatement(query_del);
			//System.out.println(" did "+discountID);
			del_stmnt.setInt(1, cusid);
			del_stmnt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
	
	public void setemailsendtoOne (int cusid) {
		String query_cast = "UPDATE Booking SET Emailsend = 1 WHERE CustomerID = ?";
		try {
			PreparedStatement bookorder = connection.prepareStatement(query_cast);
			 bookorder.setInt(1,cusid);
			 bookorder.executeUpdate();
				bookorder.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
