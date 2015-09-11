package edu.unsw.comp9321.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.InvalidActionException;
import edu.unsw.comp9321.jdbc.BookedInfoBean;
import edu.unsw.comp9321.jdbc.BookedInfoHandler;
import edu.unsw.comp9321.jdbc.BookingDAO;
import edu.unsw.comp9321.jdbc.BookingDTO;
import edu.unsw.comp9321.jdbc.HotelDTO;
import edu.unsw.comp9321.jdbc.HotelDerbyDAOImpl;
import edu.unsw.comp9321.jdbc.ManagerInfoBean;
import edu.unsw.comp9321.jdbc.ManagerInfoHandler;
import edu.unsw.comp9321.jdbc.RoomBean;
import edu.unsw.comp9321.jdbc.RoomHandler;

/**
 * Servlet implementation class Manager
 */
public class Manager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Manager.class.getName());
	private ManagerInfoHandler ManagerHandler;
	private RoomHandler RoomHandler;
	private BookingDAO BookingHandler;
	private BookedInfoHandler BookedInfoHandler;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager() throws ServletException{
        super();
        
			try {
				ManagerHandler = new ManagerInfoHandler();
				RoomHandler = new RoomHandler();
				BookingHandler = new BookingDAO();
				BookedInfoHandler = new BookedInfoHandler();
				
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				logger.severe("Trouble connecting to database "+e.getStackTrace());
				e.printStackTrace();
				throw new ServletException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.severe("Trouble connecting to database "+e.getStackTrace());
				e.printStackTrace();
				throw new ServletException();
			}
		
        // TODO Auto-generated constructor stub
        
    }

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
		ArrayList<ManagerInfoBean> ManagerInformation = ManagerHandler.getManagerInfo();
		
		//set session attribute
		HttpSession session = request.getSession();
		session.setAttribute("all_manager_information",ManagerInformation);
		
		if(ManagerInformation != null)
			System.out.println("11111111111111111111111");
		try{
			if(action==null){
				System.out.println("null page");
				//get session attribute;
				ArrayList<ManagerInfoBean> ManagerInformations = (ArrayList<ManagerInfoBean>) session.getAttribute("all_manager_information");
				request.setAttribute("managerInfo",ManagerInformations);
				forwardPage = "ManagerLogin.jsp";
			}
			else if(action.equals("managerlogin")){
				System.out.println("loginpages");
				
				int selectedId = Integer.parseInt(request.getParameter("selectedId"));
				//put this manager ID in this session
				session.setAttribute("managerID",selectedId);
				
				System.out.println(selectedId);
				
				String password = request.getParameter("managerpassword");
				System.out.println(password);
				
				//get session attribute
				ArrayList<ManagerInfoBean> ManagerInformations = (ArrayList<ManagerInfoBean>) session.getAttribute("all_manager_information");
				
				//if the user input the incorrect password
				if(!ManagerInformations.get(selectedId-1).getManagerPassword().equals(password)){
					System.out.println("No");
					forwardPage = "ManagerLogin.jsp";
					request.setAttribute("invalidpassword",1);
					request.setAttribute("managerInfo",ManagerInformations);
							
				}
				//the user input the correct password
				else{
					ArrayList<RoomBean> occupiedRoom = RoomHandler.getOccupiedRoom(selectedId,"all");
					ArrayList<RoomBean> availableRoom = RoomHandler.getAvailableRoom(selectedId,"all");
					
					int managerBelongedHotel = ManagerInformations.get(selectedId-1).getHotelId();
					
					ArrayList<BookingDTO> bookedOrder = BookingHandler.getBookedOrder(managerBelongedHotel);
					ArrayList<BookingDTO> bookingOrder = BookingHandler.getBookingOrder(managerBelongedHotel);
							
					session.setAttribute("occupiedRoom",occupiedRoom);
					session.setAttribute("availableRoom",availableRoom);
					
					session.setAttribute("bookedOrder", bookedOrder);
					session.setAttribute("bookingOrder", bookingOrder);
					
					request.setAttribute("occupied",occupiedRoom);
					request.setAttribute("bookedOrder",bookingOrder);
					System.out.println("yes");
					forwardPage = "DisplayRoomsBooking.jsp";
					
				}
				
			}
			else if(action.equals("displayToManager")){
				String on_click = request.getParameter("button");
				//the manager want to help assign room to customer
				if(on_click.equals("Assign")){
					
					if(request.getParameter("assign")==null){
						ArrayList<RoomBean> occupiedRoom = (ArrayList<RoomBean>) session.getAttribute("occupiedRoom");
						ArrayList<BookingDTO> bookingOrder = (ArrayList<BookingDTO>) session.getAttribute("bookingOrder");
						request.setAttribute("occupied",occupiedRoom);
						request.setAttribute("bookedOrder",bookingOrder);
						forwardPage = "DisplayRoomsBooking.jsp";
						
					}
					else{
						int bookingID=Integer.parseInt(request.getParameter("assign"));
					//put bookingID into sessiong
					session.setAttribute("bookingID_to_assign", bookingID);
					
					ArrayList<BookingDTO> bookingOrder = (ArrayList<BookingDTO>) session.getAttribute("bookingOrder");
					int hotelID =0;
					String type=null;
					for(int a=0;a<bookingOrder.size();a++){
						if(bookingOrder.get(a).getBookingID() == bookingID){
							hotelID = bookingOrder.get(a).getHotelID();
							type = bookingOrder.get(a).getType();
							break;
						}
					}
					System.out.println("hotelID"+hotelID);
					System.out.println("type"+type);
					
					
					ArrayList<RoomBean> availableRoom = RoomHandler.getAvailableRoom(hotelID,type);
					request.setAttribute("roomschoice", availableRoom);
					forwardPage = "RoomChoice.jsp";
					}
				
				}
				//the manager want to help customer check out 
				else{
					
					//update the Room Table
					if(request.getParameter("checkout")==null){
						ArrayList<RoomBean> occupiedRoom = (ArrayList<RoomBean>) session.getAttribute("occupiedRoom");
						ArrayList<BookingDTO> bookingOrder = (ArrayList<BookingDTO>) session.getAttribute("bookingOrder");
						request.setAttribute("occupied",occupiedRoom);
						request.setAttribute("bookedOrder",bookingOrder);
						forwardPage = "DisplayRoomsBooking.jsp";
						
					}
					else{
					int roomID = Integer.parseInt(request.getParameter("checkout"));
					RoomHandler.roomCheckout(roomID);
					int managerID=(Integer) session.getAttribute("managerID");
					ArrayList<RoomBean> occupiedRoom = RoomHandler.getOccupiedRoom(managerID,"all");
					ArrayList<RoomBean> availableRoom = RoomHandler.getAvailableRoom(managerID,"all");
					session.setAttribute("occupiedRoom",occupiedRoom);
					session.setAttribute("availableRoom",availableRoom);
					
					//update the Booking Table and BookedInfo Table
					ArrayList<BookedInfoBean> bookedInfo = BookedInfoHandler.getAllBookedInfo();
					for(int a=0;a<bookedInfo.size();a++){
						if(bookedInfo.get(a).getRoomID() == roomID){
							BookedInfoHandler.bookedInfoDelet(roomID);
							System.out.println("bookingID"+bookedInfo.get(a).getBookingID());
							BookingHandler.bookingUpdateCheckout(bookedInfo.get(a).getBookingID());
							break;
						}
					}
					
					ArrayList<BookingDTO> bookedOrder = BookingHandler.getBookedOrder(managerID);
					ArrayList<BookingDTO> bookingOrder = BookingHandler.getBookingOrder(managerID);
					session.setAttribute("bookedOrder", bookedOrder);
					session.setAttribute("bookingOrder", bookingOrder);
					
					request.setAttribute("occupied",occupiedRoom);
					request.setAttribute("bookedOrder",bookingOrder);
					
					forwardPage = "DisplayRoomsBooking.jsp";
					}
					
				}
			}
			//the manager want to help assign;
			else if(action.equals("roomsavailable")){
				String on_click = request.getParameter("button");
				int assignRoomID = Integer.parseInt(request.getParameter("assign"));
				if(on_click.equals("Confirm")){
					
					//update the room table
					RoomHandler.roomAssign(assignRoomID);
					int managerID=(Integer) session.getAttribute("managerID");
					ArrayList<RoomBean> occupiedRoom = RoomHandler.getOccupiedRoom(managerID,"all");
					ArrayList<RoomBean> availableRoom = RoomHandler.getAvailableRoom(managerID,"all");
					session.setAttribute("occupiedRoom",occupiedRoom);
					session.setAttribute("availableRoom",availableRoom);
					
					
					//update the booking and booked table;
					int bookingID = (Integer) session.getAttribute("bookingID_to_assign");
					BookedInfoHandler.bookedInfoInsert(bookingID, assignRoomID);
					
					
					BookingHandler.bookingUpateAssign(bookingID);
					ArrayList<BookingDTO> bookedOrder = BookingHandler.getBookedOrder(managerID);
					ArrayList<BookingDTO> bookingOrder = BookingHandler.getBookingOrder(managerID);
					session.setAttribute("bookedOrder", bookedOrder);
					session.setAttribute("bookingOrder", bookingOrder);
					
					
					
					request.setAttribute("occupied",occupiedRoom);
					request.setAttribute("bookedOrder",bookingOrder);
					
					forwardPage = "DisplayRoomsBooking.jsp";
				}
			
			}
						
				
				
				
			
			
			
			
			/*if(action.equals("searchhotel")){	
				//List<HotelDTO> hotelres = hotel.findAll();
				//request.setAttribute("hotel", hotelres);
				forwardPage = "hotelresult.jsp";
			}
			else if(action.equals("checkout")){
				forwardPage = "checkout.jsp";
			}
			else forwardPage = "error.jsp";*/
			
		} catch (Exception e) {
			String message = "Action or character was not selected. Please go back and try again.";
			request.setAttribute("message", message);
			forwardPage = "error.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
		dispatcher.forward(request, response);
	}

		
		
	
	
	
	
	
	
	
	
	

}
