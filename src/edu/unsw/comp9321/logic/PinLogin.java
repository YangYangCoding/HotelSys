package edu.unsw.comp9321.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.exception.InvalidActionException;
import edu.unsw.comp9321.jdbc.*;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class PinLogin
 */
public class PinLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	    static Logger logger = Logger.getLogger(PinLogin.class.getName());
	    private HotelDerbyDAOImpl hotel;
	    private BookingDAO bookingHandler;//smj
	 
	    /**
	     * @throws ServletException 
	     * @see HttpServlet#HttpServlet()
	     */
	    public PinLogin() throws ServletException {
	    	// TODO Auto-generated constructor stub
	        super();
	        try {
	            hotel = new HotelDerbyDAOImpl();
	            bookingHandler = new BookingDAO();//smj
	        } catch (ServiceLocatorException e) {
	            logger.severe("Trouble connecting to database "+e.getStackTrace());
	            throw new ServletException();
	        } catch (SQLException e) {
	            logger.severe("Trouble connecting to database "+e.getStackTrace());
	            throw new ServletException();
	        }
	    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}
	
	 private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String forwardPage = "";
	        String action = request.getParameter("action");
	        HttpSession session = request.getSession();
	        
	        if(action == null){
	        	forwardPage = "PinLogin.jsp";
	        }
	        else if (action.equals("customerbookingdetail")){
	        	String pin = request.getParameter("pin");
	        	String on_click = request.getParameter("button");
	        	if(on_click==null){
	        		forwardPage = "PinLogin.jsp";
	        	}
	        	else {
	        		if(pin == null){
	        			request.setAttribute("notfound",1);
	        			forwardPage = "PinLogin.jsp";
	        		}
	        		else{
	        			
	        				
	        		
	        		}
	        		
	        	}
	        	
	        }
	        else if (action.equals("pinLogin")) {
        		String button = request.getParameter("button");
        		String pin = request.getParameter("pin");
        		if (button.equals("Login")) {
        			//try{
        				//Integer.parseInt(pin);
        				ArrayList<CustomerDTO> customerInfo = hotel.getCustomerInfoByPin(pin);
        				if(customerInfo.isEmpty()){
        					request.setAttribute("notfound",1);
        					forwardPage = "PinLogin.jsp";
        				}else{
        					System.out.println("Found!");
        					
        					int customerId = customerInfo.get(0).getCustomerID();
        					ArrayList<BookingDTO> BookingInfo = bookingHandler.getCustomerBookingOrder(customerId);
        					ArrayList<BookingDTO> couldCheckBookingInfo = new ArrayList<BookingDTO>();
        					
        					
        					session.setAttribute("customerId",customerId);
        					
        					for(int a=0;a<BookingInfo.size();a++){
        						if(checkTwoDaysBefore(BookingInfo.get(a).getCheck_IN()))
        							couldCheckBookingInfo.add(BookingInfo.get(a));
        					}
        					
        					//if all the booking info is within 2 days
        					if(couldCheckBookingInfo.isEmpty()){
        						request.setAttribute("alarm",1);
        						forwardPage = "PinLogin.jsp";
        						
        					}
        					
        					//if user's booking is not within 2 days
        					else{
        						 request.setAttribute("doubleCheckItem", couldCheckBookingInfo);
        						 
	        					//request.setAttribute("", arg1);
	        					//request.setAttribute("", arg1);
	        					
	        					
	        					forwardPage = "DoubleCheck.jsp";
        					}
        				}
        				
        			//}catch(NumberFormatException e){
        				
        				
        			//	request.setAttribute("notfound",1);
        			//	forwardPage = "PinLogin.jsp";
        			//}
        		}
        	}
	        else if (action.equals("doubleCheck")) {
	        	String button = request.getParameter("button");
	        	int customerId = (Integer) session.getAttribute("customerId");
	        	
	        	if (button.equals("Add Room")) {
	        		session.setAttribute("customerId",customerId);
	        		forwardPage = "addroomagain.jsp";
	        	}
	        	else if (button.equals("cancel booking")) {
	        		hotel.cancelbooking(customerId);
	        		forwardPage = "bookingcancelled.jsp";
	        	}
	        	else if (button.equals("finish booking")) {
	        		forwardPage = "FinishBooking.jsp";
	        	}
	        }
	        else if (action.equals("searchhotelagain")) {
	        	String button = request.getParameter("button");
	        	if (button.equals("Search")) {
	        		
	        		
	        		if(hotel.checkVaildRoomNum(request.getParameter("price"))==false){
	        			System.out.println("cccccccccccccccccccccccccccccccccc");
	        			forwardPage = "addroomagain.jsp";
	        			request.setAttribute("invalidinput", 1);
	        		}
	        		else{
	        			
		        		int customerId = (Integer) session.getAttribute("customerId");
		        		ArrayList<BookingDTO> cusinfo = bookingHandler.getCustomerBookingOrder(customerId);
		        		
		        		int hotelid = cusinfo.get(0).getHotelID();
		        		Date checkin = cusinfo.get(0).getCheck_IN();
		        		Date checkout = cusinfo.get(0).getCheck_out();
		        		float price = Float.parseFloat(request.getParameter("price"));
		        		int nbroom = 1;
		        		
		        		String hotelname = "";
		    			if (hotelid == 1) {
		    				hotelname = "Izmailovo Hotel";
		    	        }
		    	        else if (hotelid == 2) {
		    	        	hotelname = "MGM Grand Las Vegas";
		    	        }
		    	        else if (hotelid == 3) {
		    	        	hotelname = "First World Hotel";
		    	        }
		    	        else if (hotelid == 4) {
		    	        	hotelname = "Borgata";
		    	        }
		    	        else if (hotelid == 5) {
		    	        	hotelname = "Marina Bay Sands";
		    	        }
		        		
		        		List<HotelDTO> hotelroom_num = hotel.findRoomNum(hotelid);
	                    int[] TotalRoomNum = new int[5];
	                    for (HotelDTO roomnum: hotelroom_num) {
	                        TotalRoomNum[0] = roomnum.getSingleroomNum();
	                        TotalRoomNum[1] = roomnum.getTwinbedNum();
	                        TotalRoomNum[2] = roomnum.getQueenNum();
	                        TotalRoomNum[3] = roomnum.getExecutiveNum();
	                        TotalRoomNum[4] = roomnum.getSuiteNum();
	                    }
		        		
		        		// return all unavailable rooms
	                    List<BookingDTO> notava_room = hotel.BookingCount(hotelid, price, checkin, checkout);
	                    for (BookingDTO unava_room: notava_room) {
	                        if (unava_room.getType().equals("Single Room") && TotalRoomNum[0] > 0) {
	                            TotalRoomNum[0]--;
	                        }
	                        else if (unava_room.getType().equals("Twin Bed") && TotalRoomNum[1] > 0) {
	                            TotalRoomNum[1]--;
	                        }
	                        else if (unava_room.getType().equals("Queen") && TotalRoomNum[2] > 0) {
	                            TotalRoomNum[2]--;
	                        }
	                        else if (unava_room.getType().equals("Executive") && TotalRoomNum[3] > 0) {
	                            TotalRoomNum[3]--;
	                        }
	                        else if (unava_room.getType().equals("Suite") && TotalRoomNum[4] > 0) {
	                            TotalRoomNum[4]--;
	                        }
	                    }
		        		
		        		List<RoomResDTO> final_room = new ArrayList<RoomResDTO>();
		        		
		        		Boolean peak = hotel.checkPeak(checkin, checkout);
	                    // To see if it is peak time or not
	                    float[] priceof_room = new float[5];
	                    priceof_room[0] = 70;
	                    priceof_room[1] = 120;
	                    priceof_room[2] = 120;
	                    priceof_room[3] = 180;
	                    priceof_room[4] = 300;
	                    if (peak) {
	                    	for (int i = 0; i < 5; i++) {
	                    		priceof_room[i] = (float) (priceof_room[i] * 1.4);
	                    	}
	                    } 
	                    if (TotalRoomNum[0] >= nbroom && priceof_room[0] <= price) {
	                        final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[0], "Single Room", priceof_room[0], checkin, checkout));
	                    }
	                    if (TotalRoomNum[1] >= nbroom && priceof_room[1] <= price) {
	                        final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[1], "Twin Bed", priceof_room[1], checkin, checkout));
	                    }
	                    if (TotalRoomNum[2] >= nbroom && priceof_room[2] <= price) {
	                        final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[2], "Queen", priceof_room[2], checkin, checkout));
	                    }
	                    if (TotalRoomNum[3] >= nbroom && priceof_room[3] <= price) {
	                        final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[3], "Executive", priceof_room[3], checkin, checkout));
	                    }
	                    if (TotalRoomNum[4] >= nbroom && priceof_room[4] <= price) {
	                        final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[4], "Suite", priceof_room[4], checkin, checkout));
	                    }
	                    
	                    session.setAttribute("customerId",customerId);
	                    
	                    if (final_room.isEmpty()) {
	                        System.out.println("null");
	                        forwardPage = "noresagain.jsp";
	                    }
	                    else {
	                        request.setAttribute("hotel", final_room);
	                        forwardPage = "resagain.jsp";
	                    }
	        		}
	        	}
	        	
	        }
	        else if (action.equals("noresagain")) {
        		String button = request.getParameter("button");
	        	if (button.equals("Confirm")) {
	        		forwardPage = "FinishBooking.jsp";
	        	}
	        	else if (button.equals("cancel booking")) {
	        		
	        		int customerId = (Integer) session.getAttribute("customerId");
	        		session.setAttribute("customerId",customerId);
	        		
	        		hotel.cancelbooking(customerId);
	        		forwardPage = "bookingcancelled.jsp";
	        	}
        	}
	        else if (action.equals("resagain")) {
	        	int customerId = (Integer) session.getAttribute("customerId");
	        	System.out.println("iddddddddd      "+customerId);
	        	String button = request.getParameter("button");
	        	
	        	if (button.equals("Confirm")) {
	        		// insert new booking info
	        		ArrayList<BookingDTO> cusInfo = bookingHandler.getCustomerBookingOrder(customerId);
	        		String type = request.getParameter("chose");
	        		int bookingid = hotel.genr_new_bookingid();
            		Date chin = cusInfo.get(0).getCheck_IN();
            		Date chout = cusInfo.get(0).getCheck_out();
            		int hotelid = cusInfo.get(0).getHotelID();
            		float price = 0;
            		if (type.equals("Single Room")) {
            			price = 70;
            		}
            		else if (type.equals("Twin Bed")) {
            			price = 120;
            		}
            		else if (type.equals("Queen")) {
            			price = 120;
            		}
            		else if (type.equals("Executive")) {
            			price = 180;
            		}
            		else if (type.equals("Suite")) {
            			price = 300;
            		}
            		
            		
            			
            		hotel.insertBooking(bookingid, customerId, chin, chout, 
            				type, price, hotelid);
            			
            			
            		
	        		
	        		forwardPage = "FinishBooking.jsp";
	        	}
	        	else if (button.equals("cancel booking")) {
	        		
	        		
	        		hotel.cancelbooking(customerId);
	        		forwardPage = "bookingcancelled.jsp";
	        	}
	        }
		 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
	        dispatcher.forward(request, response);
	        
	 }
	 
	 public boolean checkTwoDaysBefore(Date checkDate){
		 
		//Date today = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DAY_OF_MONTH,2);
		Date threshold  = cal.getTime();
		
		if(threshold.after(checkDate))
			return false;
		else
			return true;
		 
	 }
	 
	/* public static toddMMyy(Date day){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String date = formatter.format(day);
		 return date;
	 }*/
}
	
	
	
	
	
	
	
	

