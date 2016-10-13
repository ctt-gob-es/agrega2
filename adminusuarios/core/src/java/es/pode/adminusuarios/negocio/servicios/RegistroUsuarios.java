package es.pode.adminusuarios.negocio.servicios;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;



public class RegistroUsuarios
{
	//TODO Gestionar la caducidad de las sesiones, al recoger o introducir una nueva sesión habra que eliminar las caducadas
	//este codigo tiene que estar sincronizado
	
	private Map usuarios = Collections.synchronizedMap(new HashMap());
	private Long timeout = null; //5 horas en milisegundos 
	private static Logger logger = Logger.getLogger(RegistroUsuarios.class);
	
	public Object[] getAllPrincipals() {
        return usuarios.keySet().toArray();
    }
	
	public Boolean registerNewSession(String loginUsuario, String a)
    {
    
		//Obtenemos la fecha en la que caducará la sesion, sera la fecha actual mas el timeout
		Boolean resultado = true;
		try
		{
			removeExpiredSession();
			logger.debug("Eliminamos las sesiones de los usuarios antiguas");
			//timeout = new Long(Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_IDENTIDAD_FEDERADA));
			timeout = new Long(a);
			Date fechaCaducidad = new Date(System.currentTimeMillis()+timeout.longValue());
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String fechaCadena = formatoDeFecha.format(fechaCaducidad);
			logger.debug("La fecha de caducidad "+fechaCadena);
			usuarios.put(loginUsuario,fechaCadena);
			
			
		}catch(Exception e)
		{
			logger.error("Error al añadir un usuario "+resultado,e);
			resultado = false;
		}
		return resultado;

    }
	
	 public String getUserInformation(String loginUsuario) {
		 
		 String informacionUsuario = null;
		 try
		 {
			removeExpiredSession();
			logger.debug("Eliminamos las sesiones de usuarios caducadas"); 
		    informacionUsuario = (String)usuarios.get(loginUsuario);
		    logger.debug("informacionUsuario "+informacionUsuario);
		 }catch(Exception e)
		 {
			 logger.error("Error al obtener informacion del usuario "+e);
		 }
		 
	        return informacionUsuario;
	 }
	 
	 public Boolean removeSessionInformation(String loginUsuario) {

		 Boolean resultado = true;
		 synchronized (usuarios) {
			 try
			 {
				 removeExpiredSession();
				 logger.debug("Eliminamos las sesiones de usuarios caducadas"); 
				 String info = getUserInformation(loginUsuario);
				 if (!(info == null)) {
					 usuarios.remove(loginUsuario);
		     	          
				 }
			 }catch(Exception e)
			 {
				logger.error("Error al eliminar sesion de usuario - ",e); 
				resultado = false;
			 }
		 }
		 return resultado;
	    }
	 
	 
	 private void removeExpiredSession()
	 {
		 //logger.debug("removeExpiredSession");
		 try
		 {
			 Set claves = usuarios.keySet();
			 Iterator iter = claves.iterator();
			 logger.debug("claves "+claves.size());
			 SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			 GregorianCalendar fechaActual = new GregorianCalendar();
			 while(iter.hasNext())
			 {
				String loginUsuario = (String)iter.next(); 
				logger.debug("loginUsuario "+loginUsuario);
				String caducidad = (String)usuarios.get(loginUsuario);
				logger.debug("caducidad "+caducidad);
				Date fechaCaducidad = formatoDeFecha.parse(caducidad);
				GregorianCalendar gcCaducidad = new GregorianCalendar();
				gcCaducidad.setTime(fechaCaducidad);
				if(fechaActual.after(gcCaducidad))
				{
					logger.debug("La fecha de caducidad ha pasado");
					usuarios.remove(loginUsuario);
					logger.debug("Elimino la informacion de "+loginUsuario);
				}
				
			 }
		 }catch(Exception e)
		 {
			logger.error("Error al borrar las sesiones caducadas de usuarios - ",e); 
		 }
	 }
	 
}
