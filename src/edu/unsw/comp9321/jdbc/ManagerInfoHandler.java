package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class ManagerInfoHandler {
	
	static Logger logger = Logger.getLogger(ManagerInfoHandler.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	
	public ManagerInfoHandler() throws ServiceLocatorException, SQLException{
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public ArrayList<ManagerInfoBean> getManagerInfo(){
		ArrayList<ManagerInfoBean> managerInfo = new ArrayList<ManagerInfoBean>();
		try{
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM Manager";
			ResultSet res = stmnt.executeQuery(query_cast);
			while(res.next()){		
				int managerId = res.getInt("ManagerID");
				logger.info(" "+managerId);
				String managerAccount = res.getString("Manageraccount");
				logger.info(managerAccount);
				String managerPassword = res.getString("Managerpassword");
				logger.info(" "+managerPassword);
				int belongedHotel = res.getInt("hotelId");
				logger.info(" "+belongedHotel);
				
				managerInfo.add(new ManagerInfoBean(managerId,managerAccount,managerPassword,belongedHotel));
			}
			res.close();
			stmnt.close();
		}catch(Exception e){
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return managerInfo;
	}

}
