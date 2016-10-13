package es.agrega.soporte.serverProperties;


/**
 * Permite el acceso a propiedades de configuracion dependientes de servidor.
 * 
 * @author aleal
 * @deprecated Usar es.agrega.soporte.es.agrega.soporte.agregaProperties.AgregaProperties en su lugar
 */
public interface DependentServerPropertiesItf 
{
	public static final String PROPERTIES_FILE_NAME = "dependentServer"; 
	
	/**
	 * @deprecated Usar es.agrega.soporte.agregaProperties.PROPERTY_CCAA
	 */
	public static final String PROPERTY_CCAA = "server.ccaa";
	
	/**
	 *  @deprecated Usar es.agrega.soporte.agregaProperties.PROPERTY_NODO
	 */
	public static final String PROPERTY_NODO = "server.nodo";
	
	/**
	 *  @deprecated Usar es.agrega.soporte.agregaProperties.PROPERTY_NODOS
	 */
	public static final String PROPERTY_NODOS = "nodos";
	
	/**
	 *  @deprecated Usar es.agrega.soporte.agregaProperties.PROPERTY_CCAAS
	 */
	public static final String PROPERTY_CCAAS = "ccaas";
	
	/**
	 *  @deprecated Usar es.agrega.soporte.agregaProperties.PROPERTY_URLCONSEJERIANODO
	 */
	public static final String PROPERTY_URLCONSEJERIANODO = "urlConsejeriaNodo";
	
	/**
	 * @param sKey
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getProperty(String sKey);
		
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getServerOn();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getCCAA();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getNodo();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getNodos();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getCCAAs();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getUrlNodo();
	
	/**
	 * @return String
	 * @deprecated Leer propiedades usando la clase AgregaProperties
	 */
	public String getUrlConsejeriaNodo();
	
	/**
	 * Accede a la ruta absoluta que es la raiz para la galeria de imagenes
	 * @return String
	 */
	public String getServerPath();
}
