package edu.unsw.comp9321.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
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


/**
 * Servlet implementation class Controller
 */
public class Owner extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Controller.class.getName());
	private HotelDerbyDAOImpl owner;
	   
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public Owner() throws ServletException {
    	// TODO Auto-generated constructor stub
        super();
        try {
			owner = new HotelDerbyDAOImpl();
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
		
		List<OwnerDTO> OwnerInformation = owner.getOwnerInfo();
		
		//set session attribute
		HttpSession session = request.getSession();
		session.setAttribute("ownerinformation",OwnerInformation);
		
		
		try{
			if(action==null){
				ArrayList<OwnerDTO> OwnerInformations = (ArrayList<OwnerDTO>) session.getAttribute("ownerinformation");
				request.setAttribute("ownerInfo",OwnerInformations);
				
				forwardPage = "ownerlogin.jsp";
			}
			
			else if(action.equals("ownerlogin")){
				int selectedId = Integer.parseInt(request.getParameter("selectedId"));
				System.out.println(selectedId);
				
				String password = request.getParameter("ownerpassword");
				System.out.println(password);
				
				//get session attribute
				ArrayList<OwnerDTO> OwnerInformations = (ArrayList<OwnerDTO>) session.getAttribute("ownerinformation");
				
				//if the user input the incorrect password
				if(!OwnerInformations.get(0).getOwnerpassword().equals(password)){
					System.out.println("No");
					forwardPage = "ownerlogin.jsp";
					request.setAttribute("invalidpassword",1);
					request.setAttribute("ownerInfo",OwnerInformations);
							
				}
				//the user input the correct password
				else{
					int[] occupiedRoom = new int[5];
					int[] availableRoom = new int[5];
					
					for (int i = 0; i < 5; i++) {
						occupiedRoom[i] = owner.getOccupiedRoom(i + 1);
						availableRoom[i] = owner.getAvailableRoom(i + 1);
					}
					
					List<OwnerresDTO> ownerroominfo = new ArrayList<OwnerresDTO>();
					ownerroominfo.add(new OwnerresDTO("Izmailovo Hotel", "Sydney", occupiedRoom[0], availableRoom[0]));
					ownerroominfo.add(new OwnerresDTO("MGM Grand Las Vegas", "Brisbane", occupiedRoom[1], availableRoom[1]));
					ownerroominfo.add(new OwnerresDTO("First World Hotel", "Melbourne", occupiedRoom[2], availableRoom[2]));
					ownerroominfo.add(new OwnerresDTO("Borgata", "Adelaide", occupiedRoom[3], availableRoom[3]));
					ownerroominfo.add(new OwnerresDTO("Marina Bay Sands", "Hobart", occupiedRoom[4], availableRoom[4]));
					
					session.setAttribute("ownerroominfo",ownerroominfo);
					
					
					
					request.setAttribute("ownerroominfo",ownerroominfo);
					
					
					//System.out.println("yes");
					forwardPage = "allroominfo.jsp";
					
				}
				
			}
			else if (action.equals("ownerroom")) {
				String button = request.getParameter("button");
				if (button.equals("refresh")) {
					int[] occupiedRoom = new int[5];
					int[] availableRoom = new int[5];
					
					for (int i = 0; i < 5; i++) {
						occupiedRoom[i] = owner.getOccupiedRoom(i + 1);
						availableRoom[i] = owner.getAvailableRoom(i + 1);
					}
					
					List<OwnerresDTO> ownerroominfo = new ArrayList<OwnerresDTO>();
					ownerroominfo.add(new OwnerresDTO("Izmailovo Hotel", "Sydney", occupiedRoom[0], availableRoom[0]));
					ownerroominfo.add(new OwnerresDTO("MGM Grand Las Vegas", "Brisbane", occupiedRoom[1], availableRoom[1]));
					ownerroominfo.add(new OwnerresDTO("First World Hotel", "Melbourne", occupiedRoom[2], availableRoom[2]));
					ownerroominfo.add(new OwnerresDTO("Borgata", "Adelaide", occupiedRoom[3], availableRoom[3]));
					ownerroominfo.add(new OwnerresDTO("Marina Bay Sands", "Hobart", occupiedRoom[4], availableRoom[4]));
					
					session.setAttribute("ownerroominfo",ownerroominfo);
					
					
					
					request.setAttribute("ownerroominfo",ownerroominfo);
					
					//System.out.println("yes");
					forwardPage = "allroominfo.jsp";
				}
				else if (button.equals("set discount")) {
					forwardPage = "discount.jsp";
				}
				
			}
			else if (action.equals("discount")) {
				String button = request.getParameter("button");
				if (button.equals("set")) {
					if(request.getParameter("cityoption").isEmpty()||request.getParameter("dis").isEmpty()||request.getParameter("roomtype").isEmpty()||request.getParameter("startdate").isEmpty() || request.getParameter("enddate").isEmpty()){
	                    //System.out.println("1111111111111111111111111111111111");
	                    request.setAttribute("invalidinput", 1);
	                    String result = "empty form!";
	                    request.setAttribute("input", result);
	                    forwardPage = "discount.jsp";
	                }
					else{
						//System.out.println("set");
						String city = request.getParameter("cityoption");
						int hotelid = 0;
						if (city.equals("sydney")) {
							hotelid = 1;
						}
						else if (city.equals("brisbane")) {
							hotelid = 2;
						}
						else if (city.equals("melbourne")) {
							hotelid = 3;
						}
						else if (city.equals("adelaide")) {
							hotelid = 4;
						}
						else if (city.equals("hobart")) {
							hotelid = 5;
						}
						
						String tmp_dis = request.getParameter("dis");
						
						
						
						
						
						Boolean checkdis = owner.checkdiscount(tmp_dis);
						String type = request.getParameter("roomtype");
						String tmp_start = request.getParameter("startdate");
						String tmp_end = request.getParameter("enddate");
						DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); //change string to date
						Boolean checkInCheck = owner.checkValidDate(tmp_start, "yyyy-MM-dd");
	                    Boolean checkOutCheck = owner.checkValidDate(tmp_end, "yyyy-MM-dd");
	                    if(checkInCheck == false){
	                    	
	                        System.out.println("checkincheck");
	                        request.setAttribute("invalidinput", 1);
	                        String result = "Check in Date";
	                        request.setAttribute("input", result);
	                        forwardPage = "discount.jsp";
	                    }
	                    else if(checkOutCheck == false){
	                        System.out.println("checkoutcheck");
	                        request.setAttribute("invalidinput", 1);
	                        String result = "Check out Date";
	                        request.setAttribute("input", result);
	                        forwardPage = "discount.jsp";
	                    }
	                    else if(checkdis == false){
	                    	System.out.println("discountcheck");
                            request.setAttribute("invalidinput",1);
                            String result = "discount";
                            request.setAttribute("input", result);
                            forwardPage = "discount.jsp";
	                    }
	                    else{
							Date start_date = fmt.parse(tmp_start);
							Date end_date = fmt.parse(tmp_end);
							Boolean timeScope = owner.checkValidScope(start_date,end_date);
							Boolean discountCheck = owner.checkdiscount(tmp_dis);
							float discount = Float.parseFloat(request.getParameter("dis"));
							
							if(timeScope == false){
	                            System.out.println("timescope");
	                            request.setAttribute("invalidinput", 1);
	                            String result = "Time Scope";
	                            request.setAttribute("input", result);
	                            forwardPage = "discount.jsp";
	                        }
							else if (discountCheck == false) {
								System.out.println("discountcheck");
	                            request.setAttribute("invalidinput",1);
	                            String result = "discount";
	                            request.setAttribute("input", result);
	                            forwardPage = "discount.jsp";
							}
							else {
								List<DiscountDTO> validdisc = owner.discountvalid(hotelid, discount, type, start_date, end_date);
								//System.out.println(" aa"+validdisc.get(0).getHotelid());
								session.setAttribute("validdisc",validdisc);
								if (validdisc.get(0).getDiscountid() == -1) {
									System.out.println("not");
									forwardPage = "invaliddiscount.jsp";
								}
								else {
									System.out.println("yes");
									request.setAttribute("validdisc",validdisc);
									forwardPage = "confirmdiscount.jsp";
								}
							}
	                    }
					}
				}
				else if (button.equals("back to room info")) {
					int[] occupiedRoom = new int[5];
					int[] availableRoom = new int[5];
					
					for (int i = 0; i < 5; i++) {
						occupiedRoom[i] = owner.getOccupiedRoom(i + 1);
						availableRoom[i] = owner.getAvailableRoom(i + 1);
					}
					
					List<OwnerresDTO> ownerroominfo = new ArrayList<OwnerresDTO>();
					ownerroominfo.add(new OwnerresDTO("Izmailovo Hotel", "Sydney", occupiedRoom[0], availableRoom[0]));
					ownerroominfo.add(new OwnerresDTO("MGM Grand Las Vegas", "Brisbane", occupiedRoom[1], availableRoom[1]));
					ownerroominfo.add(new OwnerresDTO("First World Hotel", "Melbourne", occupiedRoom[2], availableRoom[2]));
					ownerroominfo.add(new OwnerresDTO("Borgata", "Adelaide", occupiedRoom[3], availableRoom[3]));
					ownerroominfo.add(new OwnerresDTO("Marina Bay Sands", "Hobart", occupiedRoom[4], availableRoom[4]));
					
					session.setAttribute("ownerroominfo",ownerroominfo);
					
					
					
					request.setAttribute("ownerroominfo",ownerroominfo);
					forwardPage = "allroominfo.jsp";
				}
				
			}
			else if (action.equals("invaliddiscount")) {
				String button = request.getParameter("button");
				if (button.equals("back to set discount")) {
					forwardPage = "discount.jsp";
				}
				if (button.equals("set")) {
					List<DiscountDTO> validdisc = new ArrayList<DiscountDTO>();
					validdisc =	(ArrayList<DiscountDTO>) session.getAttribute("validdisc");
					
					int hotelid = validdisc.get(0).getHotelid();
					float discount = validdisc.get(0).getDiscount();
					String type = validdisc.get(0).getType();
					Date sdate = validdisc.get(0).getStartdate();
					Date edate = validdisc.get(0).getEnddate();
					
					owner.delsetdiscount(hotelid, discount, type, sdate, edate);
					
					request.setAttribute("validdisc",validdisc);
					forwardPage = "setdone.jsp";
				}
			}
			else if (action.equals("confirmdiscount")) {
				String button = request.getParameter("button");
				if (button.equals("back to set discount")) {
					forwardPage = "discount.jsp";
				}
				if (button.equals("Confirm")) {
					
					List<DiscountDTO> validdisc = new ArrayList<DiscountDTO>();
					validdisc =	(ArrayList<DiscountDTO>) session.getAttribute("validdisc");
					
					int hotelid = validdisc.get(0).getHotelid();
					float discount = validdisc.get(0).getDiscount();
					String type = validdisc.get(0).getType();
					Date sdate = validdisc.get(0).getStartdate();
					Date edate = validdisc.get(0).getEnddate();
					
					
					owner.setdiscount(hotelid, discount, type, sdate, edate);
					
					request.setAttribute("validdisc",validdisc);
					forwardPage = "setdone.jsp";
				}
			}
			else if (action.equals("setdone")) {
				String button = request.getParameter("button");
				if (button.equals("back to room info")) { 
					int[] occupiedRoom = new int[5];
					int[] availableRoom = new int[5];
					
					for (int i = 0; i < 5; i++) {
						occupiedRoom[i] = owner.getOccupiedRoom(i + 1);
						availableRoom[i] = owner.getAvailableRoom(i + 1);
					}
					
					List<OwnerresDTO> ownerroominfo = new ArrayList<OwnerresDTO>();
					ownerroominfo.add(new OwnerresDTO("Izmailovo Hotel", "Sydney", occupiedRoom[0], availableRoom[0]));
					ownerroominfo.add(new OwnerresDTO("MGM Grand Las Vegas", "Brisbane", occupiedRoom[1], availableRoom[1]));
					ownerroominfo.add(new OwnerresDTO("First World Hotel", "Melbourne", occupiedRoom[2], availableRoom[2]));
					ownerroominfo.add(new OwnerresDTO("Borgata", "Adelaide", occupiedRoom[3], availableRoom[3]));
					ownerroominfo.add(new OwnerresDTO("Marina Bay Sands", "Hobart", occupiedRoom[4], availableRoom[4]));
					
					session.setAttribute("ownerroominfo",ownerroominfo);
					
					
					
					request.setAttribute("ownerroominfo",ownerroominfo);
					forwardPage = "allroominfo.jsp";
				}
			}
		}catch(Exception e) {
			
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
		dispatcher.forward(request, response);
	}
	

}
