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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(Controller.class.getName());
    private HotelDerbyDAOImpl hotel;
    private BookingDAO bookingHandler;
	   
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public Controller() throws ServletException {
    	// TODO Auto-generated constructor stub
        super();
        try {
            hotel = new HotelDerbyDAOImpl();
            bookingHandler = new BookingDAO();
        } catch (ServiceLocatorException e) {
            logger.severe("Trouble connecting to database "+e.getStackTrace());
            throw new ServletException();
        } catch (SQLException e) {
            logger.severe("Trouble connecting to database "+e.getStackTrace());
            throw new ServletException();
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPage = "";
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
		
        try{
            if(action==null){
                forwardPage = "Welcome.jsp";
            }
            if(action.equals("searchhotel")){	
			
                if(request.getParameter("price").isEmpty()||request.getParameter("nbroom").isEmpty()||request.getParameter("checkin").isEmpty()||request.getParameter("checkout").isEmpty()){
                    //System.out.println("1111111111111111111111111111111111");
                    request.setAttribute("invalidinput", 1);
                    String result = "empty form!";
                    request.setAttribute("input", result);
                    forwardPage = "Welcome.jsp";
                }
				
                else{
                  
                    String city = request.getParameter("cityoption");
                    int nb_of_room;
                    String hotelname = "no city";
                    int hotelid = 0;
                    String tmp_ch_in = request.getParameter("checkin");
                    String tmp_ch_out = request.getParameter("checkout");
				
                    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); //change string to date

                    String tmpprice = request.getParameter("price");
                    float price;
                   
                    Boolean checkInCheck = hotel.checkValidDate(tmp_ch_in, "yyyy-MM-dd");
                    Boolean checkOutCheck = hotel.checkValidDate(tmp_ch_out, "yyyy-MM-dd");
                    if(checkInCheck == false){
                        System.out.println("checkincheck");
                        request.setAttribute("invalidinput", 1);
                        String result = "Check in Date";
                        request.setAttribute("input", result);
                        forwardPage = "Welcome.jsp";
                    }
                    else if(checkOutCheck == false){
                        System.out.println("checkoutcheck");
                        request.setAttribute("invalidinput", 1);
                        String result = "Check out Date";
                        request.setAttribute("input", result);
                        forwardPage = "Welcome.jsp";
                    }
                    else{
				
                        Boolean priceCheck = hotel.checkPrice(tmpprice);
                        Boolean roomNumberCheck = hotel.checkVaildRoomNum(request.getParameter("nbroom"));
                        Date ch_in = fmt.parse(tmp_ch_in);
                        Date ch_out= fmt.parse(tmp_ch_out);
                        Boolean timeScope = hotel.checkValidScope(ch_in,ch_out);
                       
                        if(roomNumberCheck == false){
                            System.out.println("roomnumbercheck");
                            request.setAttribute("invalidinput", 1);
                            String result = "Number of Room";
                            request.setAttribute("input", result);
                            forwardPage = "Welcome.jsp";
						
                        }
                        else if(priceCheck == false){
                            System.out.println("pricecheck");
                            request.setAttribute("invalidinput",1);
                            String result = "price";
                            request.setAttribute("input", result);
                            forwardPage = "Welcome.jsp";
                        }
                        else if(timeScope == false){
                            System.out.println("timescope");
                            request.setAttribute("invalidinput", 1);
                            String result = "Time Scope";
                            request.setAttribute("input", result);
                            forwardPage = "Welcome.jsp";
                        }
					
					
                        else{
                            nb_of_room = Integer.parseInt(request.getParameter("nbroom"));
                            session.setAttribute("nbroom", nb_of_room);
                            price = Float.parseFloat(tmpprice);
                            if (city.equals("sydney")) {
                                hotelname = "Izmailovo Hotel";
                                hotelid = 1;
                            }
                            else if (city.equals("brisbane")) {
                                hotelname = "MGM Grand Las Vegas";
                                hotelid = 2;
                            }
                            else if (city.equals("melbourne")) {
                                hotelname = "First World Hotel";
                                hotelid = 3;
                            }
                            else if (city.equals("adelaide")) {
                                hotelname = "Borgata";
                                hotelid = 4;
                            }
                            else if (city.equals("hobart")) {
                                hotelname = "Marina Bay Sands";
                                hotelid = 5;
                            }
                            //System.out.println(hotelname);
                            //System.out.println(" "+hotelid+" aa");
				
                            List<HotelDTO> hotelroom_num = hotel.findRoomNum(hotelid);
                            int[] TotalRoomNum = new int[5];
                            for (HotelDTO roomnum: hotelroom_num) {
                                TotalRoomNum[0] = roomnum.getSingleroomNum();
                                TotalRoomNum[1] = roomnum.getTwinbedNum();
                                TotalRoomNum[2] = roomnum.getQueenNum();
                                TotalRoomNum[3] = roomnum.getExecutiveNum();
                                TotalRoomNum[4] = roomnum.getSuiteNum();
                            }
                            //System.out.println(" "+TotalRoomNum[0]+" aa");
                            //System.out.println(" "+price+" price");
                            //System.out.println(" "+hotelid+" id");
                            //System.out.println(ch_in);
                            //System.out.println(ch_out);
				
                            // return all unavailable rooms
                            List<BookingDTO> notava_room = hotel.BookingCount(hotelid, price, ch_in, ch_out);
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
                            // Now we have the number of available room of each type.
                            List<RoomResDTO> final_room = new ArrayList<RoomResDTO>();
                            
                            
                            
                            Boolean peak = hotel.checkPeak(ch_in, ch_out);
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
                            
                            
                            if (TotalRoomNum[0] >= nb_of_room && priceof_room[0] <= price) {
                                final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[0], "Single Room", priceof_room[0], ch_in, ch_out));
                            }
                            if (TotalRoomNum[1] >= nb_of_room && priceof_room[1] <= price) {
                                final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[1], "Twin Bed", priceof_room[1], ch_in, ch_out));
                            }
                            if (TotalRoomNum[2] >= nb_of_room && priceof_room[2] <= price) {
                                final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[2], "Queen", priceof_room[2], ch_in, ch_out));
                            }
                            if (TotalRoomNum[3] >= nb_of_room && priceof_room[3] <= price) {
                                final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[3], "Executive", priceof_room[3], ch_in, ch_out));
                            }
                            if (TotalRoomNum[4] >= nb_of_room && priceof_room[4] <= price) {
                                final_room.add(new RoomResDTO(hotelname, hotelid, TotalRoomNum[4], "Suite", priceof_room[4], ch_in, ch_out));
                            }
                            
                            session.setAttribute("final_room",final_room);
                            
                           
                            if (final_room.isEmpty()) {
                                System.out.println("null");
                                forwardPage = "nores.jsp";
                            }
                            else {
                                request.setAttribute("hotel", final_room);
                               
                                forwardPage = "hotelresult.jsp";
                            }
                        }
                    }
                }
            }
            else if(action.equals("nores")){
                forwardPage = "nores.jsp";
            }
            else if(action.equals("hasroomres")){
            	String button = request.getParameter("button");
            	if (button.equals("Back to search")) {
            		forwardPage = "Welcome.jsp";
            	}
            	else if (button.equals("Confirm")) {
            		if (request.getParameterValues("chose")==null) {
            			
            			List<RoomResDTO> info_room = new ArrayList<RoomResDTO>();
                		info_room = (ArrayList<RoomResDTO>) session.getAttribute("final_room");
            			request.setAttribute("hotel", info_room);
                        forwardPage = "hotelresult.jsp";
            		}
            		else {
            			// qian mian bu kong
            			// check dui ying
            			
            			
            			
	            		List<RoomResDTO> info_room = new ArrayList<RoomResDTO>();
	            		info_room = (ArrayList<RoomResDTO>) session.getAttribute("final_room");
	            		int hotelid = info_room.get(0).getHotelid();
	            		
	            		String hotelname = info_room.get(0).getHotelname();
	            		
	            		String[] type = request.getParameterValues("chose");
	            		int type_nb = 0;
	            		int no_brain = 0;
	            		for (int i = 0; i< type.length; i++) {
	            			if (!request.getParameter(type[i]).isEmpty()) {
	            				type_nb++;
	            			}
	            		}
	            		/*
	            		if (request.getParameter("Single Room") != null ) {
	            			no_brain++;
	            		}
	            		if (request.getParameter("Twin Bed") != null) {
	            			no_brain++;
	            		}
	            		if (request.getParameter("Queen") != null ) {
	            			no_brain++;
	            		}
	            		if (request.getParameter("Executive") != null ) {
	            			no_brain++;
	            		}
	            		if (request.getParameter("Suite") != null) {
	            			
	            			no_brain++;
	            		}*/
	            		
	            		if (type_nb == type.length) {
	            		
	            		
		            		Date check_in = info_room.get(0).getCheckin();
		        			Date check_out = info_room.get(0).getCheckout();
		        			
		            		List<CheckpaymentDTO> check_room = new ArrayList<CheckpaymentDTO>();
		            		float total_price = 0;
		            		int j = 0;
		            		if (type[0].equals("Single Room")) {
		            			j = 0;
		            		}
		            		else if (type[0].equals("Twin Bed")) {
		            			j = 1;
		            		}
		            		else if (type[0].equals("Queen")) {
		            			j = 2;
		            		}
		            		else if (type[0].equals("Executive")) {
		            			j = 3;
		            		}
		            		else if (type[0].equals("Suite")) {
		            			j = 4;
		            		}
		            		System.out.println("jjjjjjjjjjjjjjjj"+j);
		            		System.out.println("type lengggggggg"+type.length);
		            		int total_room = 0;
		            		Boolean isright = true;
		            		for (int i = 0; i< type.length; i++) {
		            			System.out.println("eqweqweqeq");
		            			String nbroom = request.getParameter(type[i]);
		            			
		            			isright = hotel.checkVaildRoomNum(nbroom);
		            			if (!isright) {
		            				break;
		            			}
		            			
		            			int nb_of_room = Integer.parseInt(nbroom);
		            			
		            
		            			
		            			total_room = total_room + nb_of_room;
		            			
		            		
				            	//check if the room has discount
				            	float discount = hotel.checkdiscountnow(hotelid, type[i], check_in,check_out);
				            	float room_price = info_room.get(i + j).getPrice() * discount;
				            	total_price = total_price + room_price * nb_of_room;
				            		
				            	check_room.add(new CheckpaymentDTO(hotelid, hotelname, type[i], nb_of_room, room_price, check_in, check_out));
		            			
		            		}
		            		
		            		if (isright) {
		            		
			            		// check nb of room valid.
			            		int real_nb_room = (Integer)session.getAttribute("nbroom");
			            		if (real_nb_room == total_room) {
			            		
				            		long diff_dates = hotel.getdiffbetweendates(check_in, check_out);
				            		total_price = total_price * diff_dates;
				            		
				            		session.setAttribute("check_room",check_room);
				            		request.setAttribute("hotel", check_room);
				            		
				            		
				            		request.setAttribute("total", total_price);
				            		
				            		forwardPage = "checkpayment.jsp";
			            		}
			            		else {
			            			request.setAttribute("alarm", 1);
			            			request.setAttribute("hotel", info_room);
			                        forwardPage = "hotelresult.jsp";
			                       
			                    
			            		}
		            		}
		            		else {
		            			request.setAttribute("hotel", info_room);
		                        forwardPage = "hotelresult.jsp";
		            		}
	            		}
	            		else {
	            			request.setAttribute("alarm", 1);
	            			request.setAttribute("hotel", info_room);
	                        forwardPage = "hotelresult.jsp";
	            		}
            		}
            		
            	}
            }
            else if (action.equals("checkpayment")) {
            	String button = request.getParameter("button");
            	if (button.equals("Back to search")) {
            		forwardPage = "Welcome.jsp";
            	}
            	else if (button.equals("Confirm")) {
            		List<CheckpaymentDTO> payment_room = new ArrayList<CheckpaymentDTO>();
            		payment_room = (ArrayList<CheckpaymentDTO>) session.getAttribute("check_room");
            		
            		session.setAttribute("payment_room",payment_room);
            		
            		Date checkin = null;
            		Date checkout = null;
            		
            		request.setAttribute("hotel", payment_room);
            		float total_price = 0;
            		for (CheckpaymentDTO get_price: payment_room) {
            			total_price = total_price + get_price.getPrice();
            			checkin = get_price.getCheckin();
            			checkout = get_price.getCheckout();
            		}
            		
            		// Check which room has add extra bed
            		String[] extrabed = request.getParameterValues("extrabed");
            		
            		long day = hotel.getdiffbetweendates(checkin, checkout);
            		if (extrabed != null) {
	            		for (int i = 0; i < extrabed.length; i++) {
	            		
	            			total_price = total_price + 35;
	            		}
            		}
            			total_price = total_price * day;
            		
            		request.setAttribute("total", total_price);
            		session.setAttribute("tpr", total_price);
            		forwardPage = "payment.jsp";
            	}
            }
            else if (action.equals("payment")) {
            	String button = request.getParameter("button");
            	if (button.equals("Back to search")) {
            		forwardPage = "Welcome.jsp";
            	}
            	else if (button.equals("Confirm")) {
            		// Check empty click
            		String email = request.getParameter("email");
            		Pattern pattern;
            		Matcher matcher;
            		String EMAIL_PATTERN = 
            				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            		pattern = Pattern.compile(EMAIL_PATTERN);
            		matcher = pattern.matcher(email);
            		
            		if(request.getParameter("email").isEmpty() || !matcher.matches()){
	                    //System.out.println("1111111111111111111111111111111111");
	                    request.setAttribute("invalidinput", 1);
	                    String result = "empty form!";
	                    request.setAttribute("input", result);
	                    
	                    List<CheckpaymentDTO> payment_room = new ArrayList<CheckpaymentDTO>();
	            		payment_room = (ArrayList<CheckpaymentDTO>) session.getAttribute("check_room");
	            		
	            		session.setAttribute("payment_room",payment_room);
	            		
	            		Date checkin = null;
	            		Date checkout = null;
	            		
	            		request.setAttribute("hotel", payment_room);
	            		float total_price = 0;
	            		for (CheckpaymentDTO get_price: payment_room) {
	            			total_price = total_price + get_price.getPrice();
	            			checkin = get_price.getCheckin();
	            			checkout = get_price.getCheckout();
	            		}
	            		
	            		// Check which room has add extra bed
	            		String[] extrabed = request.getParameterValues("extrabed");
	            		
	            		long day = hotel.getdiffbetweendates(checkin, checkout);
	            		if (extrabed != null) {
		            		for (int i = 0; i < extrabed.length; i++) {
		            		
		            			total_price = total_price + 35;
		            		}
	            		}
	            			total_price = total_price * day;
	            		
	            		request.setAttribute("total", total_price);
	                    request.setAttribute("alarm2", 1);
	                    forwardPage = "payment.jsp";
	                }
            		
            		else{
            		
            		
            		// Check name Valid
            		
            		// Check email
            		
            		// Check card
            		
            		// insert Booking table
	            		List<CheckpaymentDTO> payment_room = new ArrayList<CheckpaymentDTO>();
	            		payment_room = (ArrayList<CheckpaymentDTO>) session.getAttribute("check_room");
	            		
	            		session.setAttribute("payment_room",payment_room);
	            		
	            		
	            		
	            		int customerid = hotel.genr_new_customerid();
	            		for (int i = 0; i < payment_room.size(); i++) {
	            			
	            			int loop = payment_room.get(i).getNbofroom();
	            			System.out.println("looooooooooooooop "+ loop);
	            			for (int j = 0; j < loop; j++) {
	            				int bookingid = hotel.genr_new_bookingid();
	            				hotel.insertBooking(bookingid, customerid, payment_room.get(i).getCheckin(), payment_room.get(i).getCheckout(), 
	            					payment_room.get(i).getType(), payment_room.get(i).getPrice(), payment_room.get(i).getHotelid());
	            				
	            			}
	            		
	            		}
	            		// insert Customer table
	            		String name = request.getParameter("name");
	            		
	            		
	          
	            		
		            		String cardnum = request.getParameter("card");
		            		String pin = hotel.genr_new_pin();
		            		
		            		hotel.insertCustomer(customerid, name, pin, email, cardnum);
		            		
		            		// pay done, send email
		            		/*
		            		
		            		
		            		ArrayList<BookingDTO> customerBookingInfo = new ArrayList<BookingDTO>();
		                    ArrayList<CustomerDTO> customerInfo = new ArrayList<CustomerDTO>();
		               
		                    customerBookingInfo = bookingHandler.getCustomerBookingOrder(customerid);
		                    customerInfo = hotel.getCustomerInfo(customerid);
		                    
		                    int emailsend = customerBookingInfo.get(0).getEmailsend();
		                    if (emailsend == 0) {
		                    
			                    System.out.println("constoer id: "+ customerInfo.get(0).getCustomerID());
			                    System.out.println("constoer email: "+ customerInfo.get(0).getEmail());
			                    
			                    request.setAttribute("Customer",customerInfo);
			                    request.setAttribute("bookedOrder", customerBookingInfo);
			                    
			                    Email Email = new Email();
			                    Email.setEmailSubject("Booking Info");
			            		String content = "";
			            		//for(a=0;a<customerInfo2.size();a++){
			            		
			            		//content+=Integer.toString(customerInfo2.get(0).getCustomerID());
			            		//content+="Name: "+customerInfo.get(0).getName()+"\n";
			            		content+="PIN: "+customerInfo.get(0).getPin()+"\n";
			            		content+="Email: "+customerInfo.get(0).getEmail()+"\n";
			            		//content+="CardNumber: "+customerInfo.get(0).getCardNum()+"\n";
			            		content+="\n";
			            		
			            		
			            		content +=" Type  Price  CityName  CheckIn  Checkout"+"\n";
			            		for(int a=0;a<customerBookingInfo.size();a++){
			            			//content += Integer.toString(customerBookingInfo.get(a).getCustomerID());
			            			content += " "+customerBookingInfo.get(a).getType();
			            			content += " "+Integer.toString(((int)customerBookingInfo.get(a).getPrice()));
			            			content += " "+customerBookingInfo.get(a).getCityname();
			            			content += " "+customerBookingInfo.get(a).getCheck_IN();
			            			content += " "+customerBookingInfo.get(a).getCheck_out();
			            			content += "\n";
			            		}
			            		
			            		Email.setEmailText(content);
			            		Email.setEmailTo(customerInfo.get(0).getEmail());
			            		MailSender.sendEmail(Email);
			            		// set emailsend to 1
			            		hotel.setemailsendtoOne(customerInfo.get(0).getCustomerID());
			            		
		                    }*/
		                    
		            		
		            		request.setAttribute("cusID", customerid);
		            		forwardPage = "DisplayCustomerBooking.jsp";
	            		
	            	}
            	}
            }
        }
        
        catch(ParseException ex){
            ex.printStackTrace();
    	}
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
        dispatcher.forward(request, response);
    }
	

}
