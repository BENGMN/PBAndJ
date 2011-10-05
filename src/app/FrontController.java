package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;


public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		prepareDbRegistry("");
	}

	public static void prepareDbRegistry(String db_id) {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id+"mySqlTablePrefix");
		} catch (Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}
	
	protected Class getCommandClass(HttpServletRequest request){
		Class result;
		final String commandClassname = 
			"app." +(String)request.getParameter("command")+ "Command";
		try{
			result = Class.forName(commandClassname);
		}
		catch(ClassNotFoundException e){
			result = UnknownCommand.class;
		}
		return result;
	}

	protected FrontCommand getCommand(HttpServletRequest request){
		FrontCommand fCom = null;
		try{
			fCom =  (FrontCommand) getCommandClass(request).newInstance();
		}
		catch(Exception e){
			fCom = (FrontCommand) new UnknownCommand();
		}
		return fCom;
	}	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		FrontCommand fCom = getCommand(request);
		fCom.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preProcessRequest(request, response);
		try {
			processRequest(request, response);
		} finally {
			postProcessRequest(request, response);
		}
		
		
	}

	protected void preProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void postProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DbRegistry.closeDbConnectionIfNeeded();
		} catch (Exception e) {}
		ThreadLocalTracker.purgeThreadLocal();
	}
	
}
