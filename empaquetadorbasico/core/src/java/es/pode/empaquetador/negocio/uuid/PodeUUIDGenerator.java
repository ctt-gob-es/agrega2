package es.pode.empaquetador.negocio.uuid;

import org.safehaus.uuid.UUID;
import org.safehaus.uuid.UUIDGenerator;

/**
 * Generador de identificadores únicos para los elementos que forman parte de ODE.
 * 
 * @author INDRA
 */
public class PodeUUIDGenerator {

	/**
	 * Genera un identificador único para el String que se le pasa.
	 * 
	 * @param id
	 * @param prefijo
	 * @return
	 */
	private static String getUUID(String id, String prefijo){
		String result = null;
		
		if(id != null)
		{
			long time = System.currentTimeMillis();
			id += Long.toString(time);
	
			UUID uuid = UUIDGenerator.getInstance().generateNameBasedUUID( new UUID(UUID.NAMESPACE_DNS), id);
	
			if (uuid != null)
			{
				result = uuid.toString();
				if(prefijo != null)
					result = prefijo + result;
			}
		}
		return result;
	}
	
	/**
	 * Devuelve un identificador único.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, "");
		}
		return null;
	}

	/**
	 * Devuelve un identificador único para un objeto de tipo ODE.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getOdeUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, UUIDConstantes.ODE);
		}
		return null;
	}

	/**
	 * Devuelve un identificador único para un objeto de tipo RECURSO.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getRecursoUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, UUIDConstantes.RESURSO);
		}
		return null;
	}

	/**
	 * Devuelve un identificador único para un objeto de tipo ITEM.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getItemUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, UUIDConstantes.ITEM);
		}
		return null;
	}

	/**
	 * Devuelve un identificador único para un objeto de tipo ORGANIZACION.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getOrganizacionUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, UUIDConstantes.ORGANIZACION);
		}
		return null;
	}

	/**
	 * Devuelve un identificador único para un objeto de tipo SUBMANIFIESTO.
	 * 
	 * @param nombre Nombre a partir del cual se genera el identificador único.
	 * @return String
	 */
	public static String getSubmanifiestoUUID(String nombre){
		if(nombre != null)
		{
			return getUUID(nombre, UUIDConstantes.SUBMANIFIESTO);
		}
		return null;
	}

}
