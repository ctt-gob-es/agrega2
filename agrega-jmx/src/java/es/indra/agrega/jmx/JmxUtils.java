/**
 * 
 */
package es.indra.agrega.jmx;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.Notification;

/**
 * @author jlhuertas
 *
 */
public class JmxUtils {

	public static String DEFAULT_APP_NAME = "IBuilderApp";


	/**
	 * Constructs the ObjectName for this MBean.
	 * It follows a simple name convention: [domainName]:property=value[,property=value]*
	 * In our case, service MBeans will look:
	 * appName:type=service,service=NameOfTheService,name=NameOfTheService
	 * 
	 * Operation MBeans will look like:
	 * appName:type=service,subtype=operation,service=NameOfTheService,name=NameOfTheOperation
	 * 
	 * 
	 * @param appName
	 * @param bean
	 * @param operation
	 * @return
	 */
	public static String buildObjectName(String appName, Object bean, String operation){
		StringBuffer name = new StringBuffer();
		//service name
		String serviceName = "";
		if (bean != null){
			serviceName = bean.getClass().getName();
		}

		//domainName
		name.append(appName);
		name.append(":");
		//Properties
		if (operation == null){
			//it is a service MBean
			name.append("type=service,service=");
			name.append(serviceName);
			name.append(",name=");
			name.append(serviceName);
		}else{
			//it is an operation MBean
			name.append("type=service,subtype=operation,service=");
			name.append(serviceName);
			name.append(",name=");
			name.append(operation);
		}
		return name.toString();
	}
	
	public static String notificationToString(Notification notification){
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("\n--------------------------------------------------------------------------------------\n");
    	buffer.append("SLA Notification. \n\n\t MBean: ");
    	buffer.append(notification.getSource());
    	buffer.append("\n\t Message: ");
    	buffer.append(notification.getMessage());
    	buffer.append("\n\t Type: ");
    	buffer.append(notification.getType());
		Date timestamp = new Date(notification.getTimeStamp());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		buffer.append("\n\t Timestamp: ");
		buffer.append(formatter.format(timestamp));
		if (notification.getUserData() != null){
			buffer.append("\n\t User Data: ");
			buffer.append(notification.getUserData());
		}
		buffer.append("\n--------------------------------------------------------------------------------------\n");
    	
        return buffer.toString();
	}
	
	public static String notificationToReducedString(Notification notification){
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("SLA Notification. \n\n\t MBean: ");
    	buffer.append(extractShortName(notification.getSource().toString()));
    	buffer.append("\n\t Message: ");
    	buffer.append(notification.getMessage());
        return buffer.toString();
	}

	public static String extractShortName(String objectName){
/*		Pattern pattern = Pattern.compile("name=(.*),?");
		Matcher matcher = pattern.matcher(objectName);
		if (matcher.find()){
			return matcher.group(1);	
		} else {
			return objectName;
		}*/
		String[] split1 = objectName.split(":");
		String[] split2 = split1[1].split(",");
		String name="";
		String service="";
		boolean operation = false;
		for (int i = 0; i < split2.length; i++){
			if (split2[i].startsWith("name")){
				name = (split2[i].split("="))[1];
				String[] packets = name.split("\\.");  
				name = packets[packets.length-1];
			}
		
			if (split2[i].startsWith("service")){
				service = (split2[i].split("="))[1];
				String[] packets = service.split("\\.");  
				service = packets[packets.length-1];
			}

			if (split2[i].startsWith("subtype")){
				if ("operation".equals((split2[i].split("="))[1])){
					operation=true;
				}
			}
		}
		StringBuffer strBuffer =new StringBuffer();
		strBuffer.append("srv=" + service);
		if (operation){
			strBuffer.append(", op=" + name);
		}
		return strBuffer.toString();
		
	}
	
}
