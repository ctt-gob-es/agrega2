/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.instalador.negocio.servicios;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import es.pode.instalador.negocio.buscarysustituir.Sed;
import es.pode.instalador.negocio.dominio.NombrePropiedadCriteria;
import es.pode.instalador.negocio.dominio.NombresPropiedadCriteria;
import es.pode.instalador.negocio.dominio.Propiedad;
import es.pode.instalador.negocio.dominio.PropiedadDao;
import es.pode.instalador.negocio.ficherosconfiguracion.AgregaPropertiesFile;
import es.pode.instalador.negocio.ficherosconfiguracion.AuthBackendFile;
import es.pode.instalador.negocio.ficherosconfiguracion.InstalacionVariables;
import es.pode.instalador.negocio.ficherosconfiguracion.OpmlFile;
import es.pode.instalador.negocio.ficherosconfiguracion.Robots;
import es.pode.instalador.negocio.ficherosconfiguracion.RptdesignFile;
import es.pode.instalador.negocio.ficherosconfiguracion.SearchPluginFile;
import es.pode.instalador.negocio.ficherosconfiguracion.SitemapPortada;
import es.pode.instalador.negocio.ficherosconfiguracion.SpringLdapFile;


/**
 * @see es.pode.instalador.negocio.servicios.SrvConfiguracionService
 */

public class SrvConfiguracionServiceImpl
    extends es.pode.instalador.negocio.servicios.SrvConfiguracionServiceBase
{
	
	
	
	/** 
	 * Metodo auxiliar a handleGetAllProperties que sirve para saber cuando una propiedad es aplicable a un Jboss.
	 * Una propiedad es aplicable a un JBoss cuando lleva el prefijo del Jboss o no lleva ningun prefijo.
	 * 
     * @param tipoJboss
     * 				Si su valor es InstalacionVariables.JBOSS_TYPE_DEF, InstalacionVariables.JBOSS_TYPE_ALT o 
     * 				InstalacionVariables.JBOSS_TYPE_NODEX se revisara si la propiedad petenece al al tipo de jboss.
     * 
     * @param prop
     * 				propiedad a revisar. 
     * 
	 * @return Devuelve false si la propiedad no es aplicable al Jboss. 
	 */
    private boolean esPropiedadDelTipoJboss(PropiedadVO prop, String tipoJboss) {
    	if (prop==null)
    		return false;
    	if (InstalacionVariables.esUnTipoJboss(tipoJboss)==false)
    		return false;
    	String tipo;
    	for(int i=0; i<InstalacionVariables.tipos_jboss.length; i++) {
    		tipo = InstalacionVariables.tipos_jboss[i];
    		if(tipo.contentEquals(tipoJboss)==false)
    			if(prop.getNombre().startsWith(tipo))
    				return false;
    	}
    	return true;
    }
	

	/**
	 * Metodo que devuelve la lista de las que hay en la BD. Devolvera todas las variables (comunes y no comunes a los dos JBoss)
	 * Si el parametro tipoJboss se pasa a vacio, nulo o un valor no valido. En caso contrario devuelve los parametros comunes
	 * y los referentes a un JBoss.
	 * 
     * @param tipoJboss
     * 				Si su valor es InstalacionVariables.JBOSS_TYPE_DEF, InstalacionVariables.JBOSS_TYPE_ALT o 
     * 				InstalacionVariables.JBOSS_TYPE_NODEX
     * 				filtrara los parametros mostrados devolviendo solo los de un JBoss y los comunes, sin incluir
     * 				en el nombre de las variables el prefijo del JBoss. En caso	contrario se devuelven todos los 
     * 				parametros contenidos en la DB incluyendo los prefijos en los nombres de las variables.
     * 
     * @param devolverVariablesConDefaultValue
     * 				Si su valor es false no se devolveran aquellas variables que en la instalacion ya tienen un valor por defecto.
     * 				Este tipo de variables no son necesarias mostrarlas al usuario ya que se configuran con su valor por defecto.
     * 
	 * @return Se devuelve la lista de objetos de valor de parametros de configuracion de Agrega que hay almacenados 
	 * 				en la BD. 
	 * 
	 * @throws Exception
	 * 
	 */
    protected es.pode.instalador.negocio.servicios.PropiedadVO[] handleGetAllProperties(String tipoJboss, boolean devolverVariablesConDefaultValue)
        throws java.lang.Exception
    {
    	Collection<PropiedadVO> resultado = null;
    	
    	//Recopilamos todas las propiedades
    	resultado = this.getPropiedadDao().loadAll(PropiedadDao.TRANSFORM_PROPIEDADVO);
    	if (resultado == null) return null;
    	
    	//Eliminamos las variables con valor por defecto si es necesario
    	if (!devolverVariablesConDefaultValue) {
			Iterator<PropiedadVO> itr = resultado.iterator(); 
			while(itr.hasNext()) 
				if(InstalacionVariables.isVariableComun_con_default_value(itr.next().getNombre()))
					itr.remove();
    	}
    		
    	if (tipoJboss==null || tipoJboss.equals("")) 
    		return resultado.toArray(new PropiedadVO[0]);
    	
		//Filtramos por tipo
		Iterator<PropiedadVO> itr = resultado.iterator(); 
		while(itr.hasNext()) {
			if(esPropiedadDelTipoJboss(itr.next(), tipoJboss)==false)
				itr.remove();
/*			if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_ALT)) {
				if(itr.next().getNombre().startsWith(InstalacionVariables.JBOSS_TYPE_DEF))
					itr.remove();
			} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_DEF)) {
				if(itr.next().getNombre().startsWith(InstalacionVariables.JBOSS_TYPE_ALT))
					itr.remove();
			}
*/
		} 
    	return InstalacionVariables.eliminarPrefijoTipoJboss(resultado.toArray(new PropiedadVO[0]), tipoJboss);
    }
    
    
    //TODO por revisar
	/**
	 * Metodo que devuelve la lista de todas las variables que hay que modificar.
	 * 
	 * @return Se devuelve la lista de objetos de valor de parametros de configuracion que se necesitan para la instalacion de agrega..
	 * 
	 * @throws Exception
	 * 
	 */
    //TODO no se usa
    protected es.pode.instalador.negocio.servicios.PropiedadVO[] handleGetFileProperties()
        throws java.lang.Exception
    {
    	Collection<PropiedadVO> resultado  = null;
//    	Cogemos todos los nombres de las variables que vamos a tener en juego. Se resume a las que van al agregaproperties y al authbackendproperties
    	ArrayList<String> listaNombresVariables = new ArrayList<String>();
    	listaNombresVariables.addAll(Arrays.asList(AuthBackendFile.variables));  //las variables de authbakend
    	listaNombresVariables.addAll(Arrays.asList(AgregaPropertiesFile.variables)); // las variables de agrega
    	NombresPropiedadCriteria criterio = new NombresPropiedadCriteria(listaNombresVariables.toArray(new String[0]));
    	resultado = this.getPropiedadDao().buscarPropiedadPorNombres(PropiedadDao.TRANSFORM_PROPIEDADVO,criterio);
//    	resultado.add(jboss_home);
//    	resultado.add(uploads);
//    	resultado.add(jboss_type);
//    	loadAll(PropiedadDao.TRANSFORM_PROPIEDADVO);
    	if (resultado != null)
    		return resultado.toArray(new PropiedadVO[0]);
    	return null;
    }

    
	/**
	 * Metodo que actualiza el valor del parametro de configuracion de Agrega que le pasan.
	 * Actualiza el valor solo en el caso de que la variable ya exista en la BD. Actualiza el valor tanto en la BD como en los ficheros de configuracion
	 * donde esta sea necesaria.
	 * 
	 * @param propiedad
	 *            Objeto de valor con el valor nuevo del parametro. Solo se hace modificacion del valor del parametro, no se modifica ni su descripcion ni su ejemplo.
	 *            
	 * @return No devuelve nada. 
	 * @throws Excepcion en el caso de no poder realizarse la operacion.
	 * 
	 */
    protected void handleUpdatePropiedad(es.pode.instalador.negocio.servicios.PropiedadVO propiedad)
        throws java.lang.Exception
    {

    	/*CRM: NO HACEMOS NADA, EN UN PRINCIPIO, DEBERÍAMOS SOLAMENTE PODER ACTUALIZAR TODAS LAS VARIABLES, NO SOLO UNA
    	 * PORQUE EL IMPACTO DE UNA VARIABLE PUEDE SER EN MUCHOS FICHEROS, Y NECESITARIAMOS TODAS LAS VARIABLES PARA PODER SUSTITUIRLAS
    	 * EN LAS PLANTILLAS*/
////		HIGIENE DE DATOS
//    	if (propiedad == null || propiedad.getNombre() == null || propiedad.getNombre().equals("") ||
//    			propiedad.getValor() == null || propiedad.getValor().equals("")) {
//			throw new Exception("Error modificando propiedad vacia o con contenido vacio. La propiedad["+propiedad+"], el nombre["+propiedad==null?"null":propiedad.getNombre()+"] de la propiedad o el valor["+propiedad==null?"null":propiedad.getValor()+"]  son vacíos.");
//		}
////    	SELECCIONO LA VARIABLE DE LA BD Y MIRO QUE SOLO HAYA UNA
//    	Collection<Propiedad> listaPropiedades = this.getPropiedadDao().buscarPropiedadPorNombre(new NombrePropiedadCriteria(propiedad.getNombre()));
////    		Hay más propiedades o no existen propiedades con ese nombre
//    	if (listaPropiedades == null || listaPropiedades.size() < 1)
//    		throw new Exception("Error modificando propiedad["+propiedad.getNombre()+"]. La propiedad a modificar["+propiedad.getNombre()+"] o no existe[existe?["+(listaPropiedades==null?"false":"true")+"]] o esta almacenada mas de una vez en BD[["+(listaPropiedades!=null?listaPropiedades.size():0)+"]veces] y solo deberia aparecer una vez.");
////    		Existe en BD y solo aparece una vez => la cojo.
//    	Propiedad prop = listaPropiedades.toArray(new Propiedad[0])[0];
////    		Modifico el valor de la propiedad con lo que haya en la variable que nos pasan.
//    	prop.setValor(propiedad.getValor());
////    	Si la propiedad va a disco, la escribo en el fichero o ficheros que sean necesarios.
//    	try{
//    		escribePropiedadEnFichero(mapToPropiedadVO(prop));
//    	}catch(Exception e)
//    	{
//    		System.out.println("Excepcion evaluando escritura de propiedad["+propiedad.getNombre()+"] y valor["+propiedad.getValor()+"] en disco: "+ e.getCause());
//    		e.printStackTrace(System.out);
//    		throw new Exception("Error evaluando escritura de propiedad["+propiedad.getNombre()+"] y valor["+propiedad.getValor()+"] en disco: "+ e.getCause());
//    	}
////    	SE HA ESCRITO EN DISCO (SI ERA NECESARIO) => MODIFICAMOS LA BD
//    	this.getPropiedadDao().update(prop);
////    	CRM: en principio, estamos confiando que la insercion en BD vaya a ir bien.
////    	Estamos suponiendo que los problemas de escribir en disco sean mas probables que los de escribir en BD. Si por algun motivo se escribiese en disco y 
////    	surgiese una excepcion en BD, habria que establecer un mecanismo de rollback para la parte de disco.
    	return;
    }
    
    
	/**
	 * Metodo que actualiza el valor de la lista de parametros de configuracion de Agrega que le pasan.
	 * Actualiza el valor solo en el caso de que las variables ya existan en la BD. Actualiza el valor tanto en la BD como en los ficheros de configuracion
	 * donde esta sea necesaria.
	 * 
	 * @param propiedades
	 * 				Lista de objetos de valor con el valor nuevo del parametro. Solo se hace modificacion del valor del parametro, no se modifica ni su descripcion ni su ejemplo.
	 *            
     * @param tipoJboss
     * 				Su valor debe ser InstalacionVariables.JBOSS_TYPE_DEF, InstalacionVariables.JBOSS_TYPE_ALT o 
     * 				InstalacionVariables.JBOSS_TYPE_NODEX
     * 
	 * @return No devuelve nada. 
	 * @throws Exception Excepcion en el caso de no poder realizarse la operacion.
	 * 
	 */
    protected void handleUpdatePropiedades(es.pode.instalador.negocio.servicios.PropiedadVO[] propiedades, String tipoJboss)
        throws java.lang.Exception
    {
    	if (propiedades == null || propiedades.length == 0)
    		throw new Exception("Error modificando la lista de propiedades. La lista de propiedades a modificar es vacía["+propiedades==null?"true":"false"+"] o no tiene contenido[size"+propiedades==null?"null":"0"+"].");

    	//Verifico que en el nombre de todas las variables se especifica el mismo prefijo que indica el tipo de 
    	//jboss o que en caso contrario se especifica en el parametro tipoJboss
    	//Esto solo es util para introducir los valores y la info en la DB
    	if(tipoJboss==null || tipoJboss.equals("")) {
    		throw new Exception("ERROR: no se ha especificado para que jboss son los parametros."); 
    	} else if (InstalacionVariables.esUnTipoJboss(tipoJboss)==false) {
    		throw new Exception("ERROR: no se ha especificado correctamente para que jboss son los parametros."); 
    	} 

		propiedades=InstalacionVariables.agregarPrefijoTipoJboss(propiedades,tipoJboss);
    			
    	//RECOPILO TODOS LOS NOMBRES DE VARIABLES: PIDO QUE TENGAN CONTENIDO Y VALOR
    	ArrayList<String>  listaNombresVariabes = new ArrayList<String>();
    	for (int i = 0; i < propiedades.length; i++) {
    		//Si por un casual los nombres de las variables son nulas no las selecciono.
    		if (propiedades[i] == null || propiedades[i].getNombre() == null || 
    				propiedades[i].getNombre().equals("") || propiedades[i].getValor() == null)
    			continue;
			listaNombresVariabes.add(propiedades[i].getNombre());
		}

    	if (listaNombresVariabes == null || listaNombresVariabes.size() == 0) {
    		throw new Exception("Error modificando la lista de propiedades. La lista de propiedades a modificar contiene["+propiedades.length+"] propiedades, pero con nombre vacio o valor vacio. No hay nada que modificar.");
    	}

    	//PARA CADA VARIABLE, SELECCIONO LAS QUE ESTAN EN BD. LUEGO LAS ESCRIBIREMOS EN BD Y DISCO
    	ArrayList<Propiedad> listaPropiedadesBD = (ArrayList<Propiedad>) this.getPropiedadDao().buscarPropiedadPorNombres(new NombresPropiedadCriteria(listaNombresVariabes.toArray(new String[0])));
    	
    	//SI LAS VARIABLES QUE ME PASAN NO TIENEN REFLEJO EN BD, NO SIGO
    	if (listaPropiedadesBD == null || listaPropiedadesBD.size() == 0) {
    		throw new Exception("Error modificando la lista de propiedades. La lista de propiedades a modificar contiene["+propiedades.length+"] propiedades, pero ninguna existe en la BD, asegurese que se ha hecho el volcado y existe la tabla de variables.");	
		}
    	
    	//TENGO VARIABLES, QUE ESTAN EN BD Y QUE TIENEN CONTENIDO (LAS CANTIDADES PUEDEN SER != ENTRE LAS PASADAS AL METODO Y LAS QUE HAY REFLEJADAS EN BD)
    	//MODIFICO LAS VARIABLES DE BD CON LOS VALORES DE LAS QUE ME HAN ENTRADO EN EL METODO
    	Collection<Propiedad> listaPropiedadesActualizada = actualizaObjetosPropiedadConLista(listaPropiedadesBD, propiedades);
    	
    	//LAS INSERTO EN LOS FICHEROS DE DISCO DONDE TENGAN QUE ESTAR.
    	try {
			this.escribePropiedadEnFichero(mapToPropiedadVOArray(listaPropiedadesActualizada.toArray(new Propiedad[0])),tipoJboss);
		} catch (Exception e) {
			System.out.println("Excepcion modificando lista de propiedades con["+propiedades.length+"] elementos en sus ficheros de disco: "+ e.getCause());
    		e.printStackTrace(System.out);
    		throw new Exception("Excepcion modificando lista de propiedades con["+propiedades.length+"] elementos en sus ficheros de disco: "+ e.getCause());
    	}
    	
//    		LAS MODIFICO EN LA BD.
    	this.getPropiedadDao().update(listaPropiedadesActualizada);
//    	CRM: en principio, estamos confiando que la insercion en BD vaya a ir bien.
//    	Estamos suponiendo que los problemas de escribir en disco sean mas probables que los de escribir en BD. Si por algun motivo se escribiese en disco y 
//    	surgiese una excepcion en BD, habria que establecer un mecanismo de rollback para la parte de disco.
    	return;
    }
    
    
	/**
     * Este metodo actualiza los objetos de la lista de propiedadesBD con los valores de la lista de propiedades.
     * Las dos listas pueden ser de longitudes distintas.
     * Solo se actualizan los campos "valor" de las propiedades de la primera lista con los valroes de los de la segunda.
     * Solo se actualizan las propiedades de la lista de propiedadesBD cuando existe en la lista una variable con el mismo nombre.
     * @param listaPropiedadesBD Lista de propiedades de BD, donde se van a modificar los campos valor.
     * @param propiedades Lista de propiedades con los valores nuevos.
     * @return La lista original de propiedadesBD con los campos valor modificados con los campos valor de la lista[] si coinciden los valores de "nombre".
     */
	private Collection<Propiedad> actualizaObjetosPropiedadConLista(
			ArrayList<Propiedad> listaPropiedadesBD, PropiedadVO[] propiedades) {
		
		//Metemos la primera lista en un hashmap, para poder buscar por nombre de variable
		HashMap<String, Propiedad> mapaPropiedadesBD = new HashMap<String, Propiedad>();
		java.util.Iterator iter = listaPropiedadesBD.iterator();
		while (iter.hasNext()) {
			Propiedad propBD = (Propiedad) iter.next();
			mapaPropiedadesBD.put(propBD.getNombre(), propBD);
		}
		//Ahora iteramos por la lista de propiedades (la lista de VO) y buscando por nombre de variable en la segunda, seteamos el valor de la variable
		for (int i = 0; i < propiedades.length; i++) {
			if (mapaPropiedadesBD.containsKey(propiedades[i].getNombre())) {
				Propiedad propTemp = mapaPropiedadesBD.get(propiedades[i].getNombre());
				propTemp.setValor(propiedades[i].getValor());
				mapaPropiedadesBD.put(propiedades[i].getNombre(), propTemp);
			}
		}
		return mapaPropiedadesBD.values();
	}

	
	/**
	 * Metodo que devuelve el parametro de configuracion que esta guardado en la BD con el nombre que le pasan.
	 * 
	 * @param nombre
	 *            Nombre del parametro del que se quiere recuperar la informacion de la BD.
	 *            
	 * @return Se devuelve el objeto de valor con la informacion almacenada en BD para ese parametro: nombre, valor, descripcion y ejemplo. 
	 * @throws Exception
	 * 
	 */
    protected es.pode.instalador.negocio.servicios.PropiedadVO[] handleGetPropiedadByName(java.lang.String nombre)
        throws java.lang.Exception
    {
    	Collection<PropiedadVO> resultados = this.getPropiedadDao().buscarPropiedadPorNombre(PropiedadDao.TRANSFORM_PROPIEDADVO, new NombrePropiedadCriteria(nombre));
    	
    	if (resultados == null) {
    		System.out.println("WARNING: la consulta a la DB preguntando por la variable con nombre "+nombre+" devolvio null.");
    		return null;
    	}
    	
    	//Si hay un resultado único que devolver, lo devolvemos en una lista.
    	if (resultados.size() > 0)
    		return resultados.toArray(new PropiedadVO[0]);

		System.out.println("WARNING: la consulta a la DB preguntando por la variable con nombre "+nombre+" no devolvio ningun resultado.");
    	return null;
    }

    
	private PropiedadVO[] agregarVariablesConDefaultValue (PropiedadVO[] prop) throws Exception {
		ArrayList<PropiedadVO> resultado = new ArrayList<PropiedadVO>();
		
		//Agregamos todas las recibidas como entrada
    	for(int i=0; i<prop.length; i++)
    		resultado.add(prop[i]);
    	
    	//Agregamos las variables con valor por defecto
		PropiedadVO[] tmp = null;
    	for(int i=0; i<InstalacionVariables.variables_comunes_con_default_value.length; i++) {
			tmp=this.handleGetPropiedadByName(InstalacionVariables.variables_comunes_con_default_value[i]);
    		resultado.add(tmp[0]);
    	}
		return resultado.toArray(new PropiedadVO[0]);
	}
    
	
	/**
	 * Metodo que modifica la lista de variables que le pasan en el fichero de disco que le corresponde. Asume que las variables
	 * estaran en algun fichero de configuracion de los que se utilizaban en el install.sh
	 * Si alguna de las variables es tiene el nombre o el valor vacios, no hace nada con ella.
	 * @param propiedades
	 *            Lista de objetos de valor con la informacion de los parametros.
	 *            
	 * @return  
	 * @throws Exception
	 * 
	 */
	private void escribePropiedadEnFichero(PropiedadVO[] propiedades, String tipoJboss) throws Exception {
		//En un principio:
		//1º) Se tipifica cual es el fichero que se tiene que modificar segun la variable suministrada.
		//2º) Se implementa el mecanismo de sustitucion de las variables de una plantilla por las que modifican en este metodo.
				
		if (propiedades == null || propiedades.length == 0)
			return;
		
		PropiedadVO[] uploadPath = null;
		PropiedadVO[] jbossHome = null;
		
    	if (InstalacionVariables.esUnTipoJboss(tipoJboss)==false)
    		throw new Exception("ERROR: no se ha especificado para que jboss son los parametros");    

		if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_ALT)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.ALT_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.ALT_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_DEF)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.DEF_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.DEF_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE1)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE1_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE1_UPLOADS);
			
    	}  else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE2)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE2_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE2_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE3)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE3_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE3_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE4)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE4_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE4_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE5)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE5_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE5_UPLOADS);
			
    	} else if (tipoJboss.equals(InstalacionVariables.JBOSS_TYPE_NODE6)) {
			jbossHome=this.handleGetPropiedadByName(InstalacionVariables.NODE6_JBOSS_HOME);
			uploadPath=this.handleGetPropiedadByName(InstalacionVariables.NODE6_UPLOADS);
    	}
		
		if(jbossHome==null || jbossHome.length==0 || uploadPath==null || uploadPath.length==0) {
    		throw new Exception("ERROR al obtener el directorio uploads y/o el JBOSSHOME.");     		
		}
		
		PropiedadVO[] propsAgregaProperties = agregarVariablesConDefaultValue(propiedades);
		
		//VALIDAMOS LAS VARIABLES LOCALES: JBOSS_HOME Y UPLOADS
		modificaVariables(uploadPath[0], jbossHome[0]);
		//MODIFICACION SOBRE AGREGA.PROPERTIES (realmente, no se modifica fichero ninguno, pero exigimos de esta forma las variables que iban en este fichero)
		AgregaPropertiesFile.modificaVariables(propsAgregaProperties, jbossHome[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE AUTHBACKEND.PROPERTIES	
		AuthBackendFile.modificaVariables(propiedades, jbossHome[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE SPRING.LDAP
		SpringLdapFile.modificaVariables(propiedades, jbossHome[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE SEARCHPLUGIN.XML
		SearchPluginFile.modificaVariables(propiedades, jbossHome[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE *RPTDESIGN
		RptdesignFile.modificaVariables(propiedades, jbossHome[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE *OPML
		OpmlFile.modificaVariables(propiedades, uploadPath[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE SITEMAPPORTADA.XML
		SitemapPortada.modificaVariables(propiedades, uploadPath[0].getValor(), tipoJboss);
		//MODIFICACION SOBRE ROBOTS.TXT
		Robots.modificaVariables(propiedades, uploadPath[0].getValor(), tipoJboss);
	}
	

	private void modificaVariables(PropiedadVO uploads, PropiedadVO jboss_home)throws Exception {
		Sed.chequearDirectorioConPermisos(uploads.getValor());
		Sed.chequearDirectorioConPermisos(jboss_home.getValor());
	}


	/**
	 * Nos vemos obligados a hacer esto aqui porque en el DAO, la operacion de mapeo sobre una lista de entities la devuelve sobre la misma lista y me
	 * fastidia la lista de entities original.
	 * @param prop
	 * @return
	 */
	PropiedadVO mapToPropiedadVO(Propiedad prop) {
		return (PropiedadVO) this.getBeanMapper().map(prop, es.pode.instalador.negocio.servicios.PropiedadVO.class, "DEF_MAPPING_PROPIEDAD_PROPIEDADVO");
	}
	
	
	PropiedadVO[] mapToPropiedadVOArray(Propiedad[] props) {
		ArrayList<PropiedadVO> lista = new ArrayList<PropiedadVO>();
		for (int i = 0; i < props.length; i++) {
			lista.add(mapToPropiedadVO(props[i]));
		}
		return (PropiedadVO[]) lista.toArray(new PropiedadVO[0]);
	}
}