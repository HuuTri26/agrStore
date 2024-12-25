package agrStore.utility;

import java.util.Set;

import org.apache.log4j.Logger;

public class ServerLogger {
	
	public static final Logger logger = Logger.getLogger(ServerLogger.class);
	private static final Set<String> LOG_ACTIONS = Set.of("ADD", "UPDATE", "DELETE");
	
	public static Boolean shouldLog(String usrRole, String action) {
		System.out.println("writeLog");
		// Nếu role là admin luôn luôn ghi log
		if ("Admin".equalsIgnoreCase(usrRole))
			return true;

		return LOG_ACTIONS.contains(action.toUpperCase());
	}

	public static void writeActionLog(String usrName, String usrRole, String action, Object entity) {
	    if (entity == null) {
	        logger.warn("Entity not found! Cancel writing log!");
	        return;
	    }

	    // Ghi log thông tin cơ bản về hành động
	    StringBuilder messageLog = new StringBuilder();
	    messageLog.append("User: ").append(usrName)
	              .append(" with role '").append(usrRole)
	              .append("' performed action '").append(action)
	              .append("' on entity '")
	              .append(entity).append("' successfully!");

	    // Kiểm tra điều kiện ghi log (nếu cần)
	    if (shouldLog(usrRole, action)) {
	        logger.info(messageLog.toString());
	    }
	}
	
	public static void writeErrorLog(String usrName, String usrRole, String action, Exception e) {

		    // Ghi log thông tin cơ bản về hành động
		    StringBuilder messageLog = new StringBuilder();
		    messageLog.append("User: ").append(usrName)
		              .append(" with role '").append(usrRole)
		              .append("' performed action '").append(action)
		              .append("' failed! [").append(e.toString() + "]");
		    
		    logger.error(messageLog.toString());
		    
	}
	

}
