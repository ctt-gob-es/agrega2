// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.configuracionPlataforma.negocio.servicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;


import es.pode.configuracionPlataforma.negocio.dominio.Propiedad;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadDao;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadPorNombreEInstanciaJbossCriteria;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadesModificablesPorCategoria;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadesPorCategoriaEInstanciaJbossCriteria;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadesPorFicheroEInstanciaJbossCriteria;
import es.pode.configuracionPlataforma.negocio.dominio.PropiedadesPorInstanciaJbossCriteria;


/**
 * Cuando en el nombre de un metodo no se especifica a que instancia de JBoss afecta es por que 
 * se opera con las propiedades aplicables al nodo local. En otras palabras, las propiedades
 * de la instancia JBoss local junto con las globales 
 * 
 * @see es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService
 */
public class SrvPropiedadServiceImpl
    extends es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadServiceBase
{

	/* Instancia a la que pertenecen las propiedades globales; aplicables en todas las instancias de Jboss */
	private String INSTANCIA_GLOBAL="global";
	
	/* Tabla hash que hace de cache que guardara las propiedades aplicables en el nodo local; las locales y las globales*/
	private Hashtable<String, String> CACHE_PROPIEDADES=null;
	/* Lista de las diferentes categorias en las que estan clasificadas las propiedades modificables */
	private ArrayList<String> LISTA_CATEGORIAS=null;
	/* Lista de las diferentes instancias Jboss registradas en la DB */
	private ArrayList<String> LISTA_INSTANCIAS_JBOSS=null;

	
	/************************* METODOS DE LA CACHE DE PROPIEDADES ***********************/
	private void cargarCachePropiedades() {
		borrarCachePropiedades();
		CACHE_PROPIEDADES = new Hashtable<String, String>();
		LISTA_CATEGORIAS = new ArrayList<String>();
		PropiedadVO[] propsLocales;
		PropiedadVO[] propsGlobales;
		try {
			propsLocales=handleGetPropiedadesLocales();
			propsGlobales=handleGetPropiedadesJboss(INSTANCIA_GLOBAL);
			PropiedadDao dao = this.getPropiedadDao();
		} catch (Exception e) {
			logger.error("Problema al cargar las propiedades en la cache");
			borrarCachePropiedades();
			return;
		}
		//Cargamos las propiedades locales
		for(int i=0; i<propsLocales.length; i++) {
			CACHE_PROPIEDADES.put(propsLocales[i].getNombre(),propsLocales[i].getValor());
			
			if(LISTA_CATEGORIAS.indexOf(propsLocales[i].getCategoria())==-1)
				if(propsLocales[i].isModificable())
					LISTA_CATEGORIAS.add(propsLocales[i].getCategoria());
		}
		//Cargamos las propiedades globales
		for(int i=0; i<propsGlobales.length; i++) {
			CACHE_PROPIEDADES.put(propsGlobales[i].getNombre(),propsGlobales[i].getValor());
			
			if(LISTA_CATEGORIAS.indexOf(propsGlobales[i].getCategoria())==-1)
				if(propsGlobales[i].isModificable())
					LISTA_CATEGORIAS.add(propsGlobales[i].getCategoria());
		}
	}
	
	private String getValorPropiedadFromCache(String nombrePropiedad) throws Exception{
		String valor=null;
		if(CACHE_PROPIEDADES==null) 
			cargarCachePropiedades();
		valor=CACHE_PROPIEDADES.get(nombrePropiedad);
		if(valor==null)
			logger.error("No se encontro la propiedad " + nombrePropiedad + " para las instancias "+obtenerInstanciaJbossLocal()+" y "+INSTANCIA_GLOBAL);
		return valor;		
	}
	
	private void borrarCachePropiedades() {
		CACHE_PROPIEDADES=null;
		LISTA_CATEGORIAS=null;
	}

	/* Actualiza el valor de una propiedad */
	private void setValorPropiedadFromCache(String nombrePropiedad, String valor) {
		if(CACHE_PROPIEDADES==null) return;
		CACHE_PROPIEDADES.remove(nombrePropiedad);
		CACHE_PROPIEDADES.put(nombrePropiedad,valor);
	}
	/******************** FIN METODOS DE LA CACHE DE PROPIEDADES ***********************/


	@Override
	protected void handleResetCachePropiedadesLocales() throws Exception {
		borrarCachePropiedades();
		cargarCachePropiedades();
	}
	
	
	/**
	 * Devuelve el nombre de la carpeta en la que esta la instancia de JBoss donde se
	 * ejecuta el codigo
	 */
	private String obtenerInstanciaJbossLocal() throws Exception {
		String nombreInstancia = "";
		try {
			nombreInstancia = System.getProperty("jboss.server.name");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		return nombreInstancia;
	}


	/**
	 * Devuelve el path en el que esta la carpeta server de JBoss
	 */
	private String obtenerDirectorioServerJboss() throws Exception {
		String serverJboss = "";
		try {
			serverJboss = System.getProperty("jboss.server.base.dir");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el el path de la carpeta server de Jboss");
		}
		return serverJboss;
	}

	
	/**
	 * Devuelve la propiedad perteneciente a una instancia JBoss 
	 * determinada; global, default, node1, node2, node3, node4, node5 o node6
	 */
	@Override
	protected PropiedadVO handleGetPropiedadJBoss(String nombrePropiedad,
			String instanciaJboss) throws Exception {

		PropiedadPorNombreEInstanciaJbossCriteria c = new PropiedadPorNombreEInstanciaJbossCriteria(nombrePropiedad, instanciaJboss);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadPorNombreEInstanciaJboss(PropiedadDao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontro la propiedad " + nombrePropiedad + " para la instancia jboss "+instanciaJboss);
			return null;
		}
		return l.get(0);
	}

	
	/**
	 * Devuelve el valor de una propiedad perteneciente a una instancia JBoss 
	 * determinada; global, default, node1, node2, node3, node4, node5 o node6
	 */
	@Override
	protected String handleGetValorPropiedadJboss(String nombrePropiedad,
			String instanciaJboss) throws Exception {

		PropiedadPorNombreEInstanciaJbossCriteria c = new PropiedadPorNombreEInstanciaJbossCriteria(nombrePropiedad, instanciaJboss);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadPorNombreEInstanciaJboss(PropiedadDao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontro la propiedad " + nombrePropiedad + " para la instancia jboss "+instanciaJboss);
			return null;
		}
		return l.get(0).getValor();
	}


	/**
	 * Devuelve el valor de una propiedad perteneciente a la instancia JBoss local. 
	 * La instancia JBoss local se ajustara automaticamente.
	 */
	@Override
	protected String handleGetValorPropiedadLocal(String nombrePropiedad)
			throws Exception {
		return handleGetValorPropiedadJboss(nombrePropiedad, obtenerInstanciaJbossLocal());
	}
	

	/**
	 * Devuelve el valor de una propiedad aplicable al nodo local por lo que estara asignada
	 * a la instancia local o global. Este metodo usa una cache.
	 */
	@Override
	protected String handleGetValorPropiedad(String nombrePropiedad)
			throws Exception {
		/*
		String valor=null;
		try {
			valor=handleGetValorPropiedadLocal(nombrePropiedad);
		} catch (Exception e) {
			valor=handleGetValorPropiedadJboss(nombrePropiedad, INSTANCIA_GLOBAL);
		}
		if(valor==null)
			valor=handleGetValorPropiedadJboss(nombrePropiedad, INSTANCIA_GLOBAL);
		return valor;
		*/
		return getValorPropiedadFromCache(nombrePropiedad);
	}


	@Override
	protected Propiedad handleGetPropiedadJboss(String nombrePropiedad,
			String instanciaJboss) throws Exception {
		
		PropiedadPorNombreEInstanciaJbossCriteria c = new PropiedadPorNombreEInstanciaJbossCriteria(nombrePropiedad, instanciaJboss);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<Propiedad> l = dao.obtenerPropiedadPorNombreEInstanciaJboss(c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontro la propiedad " + nombrePropiedad + " para la instancia jboss "+instanciaJboss);
			//return dao.fromPropiedadVO(new PropiedadVO());
			return null;
		}
		return l.get(0);
	}


	@Override
	protected Propiedad handleGetPropiedadLocal(String nombrePropiedad)
			throws Exception {
		return handleGetPropiedadJboss(nombrePropiedad, obtenerInstanciaJbossLocal());
	}


	/**
	 * Ajusta el valor de una propiedad de una instancia de JBoss determinada
	 */
	@Override
	protected boolean handleSetValorPropiedadJboss(String nombrePropiedad,
			String valor, String instanciaJboss) throws Exception {
		
		Propiedad p = handleGetPropiedadJboss(nombrePropiedad, instanciaJboss);
		
		String valorAnterior = p.getValor(); 
		
		p.setValor(valor);
		this.getPropiedadDao().update(p);
		
		this.getPropiedadHistoricoDao().create(valorAnterior, Calendar.getInstance(), "", valor, nombrePropiedad, instanciaJboss);
		//Actualizamos la cache si se actualizan propiedades aplicables a la instancia local; las locales y las globales
		if(instanciaJboss.contentEquals(obtenerInstanciaJbossLocal()) || instanciaJboss.contentEquals(INSTANCIA_GLOBAL))
			setValorPropiedadFromCache(nombrePropiedad,valor);
		return true;
	}


	/**
	 * Ajusta el valor de una propiedad local
	 */
	@Override
	protected boolean handleSetValorPropiedadLocal(String nombrePropiedad,
			String valor) throws Exception {
		return handleSetValorPropiedadJboss(nombrePropiedad, valor, obtenerInstanciaJbossLocal());
	}


	/**
	 * Ajusta el valor de una propiedad. Primero intenta localizarla como local y si falla la busca como global.
	 */
	@Override
	protected boolean handleSetValorPropiedad(String nombrePropiedad,
			String valor) throws Exception {

		PropiedadVO[] propiedades=null;

		//Buscamos si es local
		propiedades=handleGetPropiedadesLocales();
		for(int i=0; i<propiedades.length; i++)
			if(propiedades[i].getNombre().contentEquals(nombrePropiedad)) {
				return handleSetValorPropiedadJboss(nombrePropiedad, valor, obtenerInstanciaJbossLocal());
			}

		//Buscamos si es global
		propiedades=handleGetPropiedadesJboss(INSTANCIA_GLOBAL);
		for(int i=0; i<propiedades.length; i++)
			if(propiedades[i].getNombre().contentEquals(nombrePropiedad)) {
				return handleSetValorPropiedadJboss(nombrePropiedad, valor, INSTANCIA_GLOBAL);
			}
		
		logger.error("No se encontro la propiedad " + nombrePropiedad + " para las instancias "+obtenerInstanciaJbossLocal()+" y "+INSTANCIA_GLOBAL);
		return false;
	}
	

	/**
	 * Ajusta el valor de un conjunto de propiedades locales o globales
	 */
	@Override
	protected boolean handleSetValorPropiedades(PropiedadVO[] propiedades)
			throws Exception {
		for(int i=0; i<propiedades.length; i++) {
			if(!handleSetValorPropiedad(propiedades[i].getNombre(),	propiedades[i].getValor()))
				return false;
		}
		return true;
	}


	/**
	 * Devuelve las propiedades pertenecientes a una instancia Jboss. 
	 * La instancia jboss puede ser global, default, node1, node2, node3, node4, node5, o node6
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesJboss(String instanciaJboss)
			throws Exception {

		PropiedadesPorInstanciaJbossCriteria c = new PropiedadesPorInstanciaJbossCriteria(instanciaJboss);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadesPorInstanciaJboss(dao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontraron propiedades para la instancia jboss "+instanciaJboss);
			return new PropiedadVO[0];
		}
		PropiedadVO[] propiedades = l.toArray(new PropiedadVO[l.size()]);
		return propiedades;
	}


	/**
	 * Devuelve las propiedades pertenecientes a la instancia Jboss local. La instancia JBoss local se ajustara automaticamente.
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesLocales() throws Exception {
		return handleGetPropiedadesJboss(obtenerInstanciaJbossLocal());
	}


	/**
	 * Devuelve una propiedad. Primero intenta localizarla como local y si falla la busca como global.
	 */
	@Override
	protected PropiedadVO handleGetPropiedad(String nombrePropiedad)
			throws Exception {

		PropiedadVO[] propiedades=null;

		propiedades=handleGetPropiedadesLocales();
		for(int i=0; i<propiedades.length; i++)
			if(propiedades[i].getNombre().contentEquals(nombrePropiedad))
				return propiedades[i];

		propiedades=handleGetPropiedadesJboss(INSTANCIA_GLOBAL);
		for(int i=0; i<propiedades.length; i++)
			if(propiedades[i].getNombre().contentEquals(nombrePropiedad))
				return propiedades[i];
		
		logger.error("No se encontro la propiedad " + nombrePropiedad + " para las instancias "+obtenerInstanciaJbossLocal()+" y "+INSTANCIA_GLOBAL);
		return new PropiedadVO();
	}

	
	/**
	 * Devuelve las propiedades que son modificable independientemente de 
	 * la instancia a la que pertenezcan filtrandolas por categoria
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesModificablesDeTodasInstanciasPorCategoria(String categoria)
			throws Exception {
		
		PropiedadesModificablesPorCategoria c = new PropiedadesModificablesPorCategoria(true,categoria);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadesModificablesPorCategoria(dao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontraron propiedades modificables con categoria "+categoria);
			return new PropiedadVO[0];
		}
		PropiedadVO[] propiedades = l.toArray(new PropiedadVO[l.size()]);
		return propiedades;
	}


	/**
	 * Devuelve las propiedades pertenecientes a una instancia Jboss filtrandolas por categoria. 
	 * La instancia jboss puede ser global, default, node1, node2, node3, node4, node5, o node6
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesJbossPorCategoria(
			String instanciaJboss, String categoria) throws Exception {

		PropiedadesPorCategoriaEInstanciaJbossCriteria c = new PropiedadesPorCategoriaEInstanciaJbossCriteria(instanciaJboss, categoria);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadesPorCategoriaEInstanciaJboss(dao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontraron propiedades con categoria "+categoria+" para la instancia jboss "+instanciaJboss);
			return new PropiedadVO[0];
		}
		PropiedadVO[] propiedades = l.toArray(new PropiedadVO[l.size()]);
		return propiedades;
	}


	/**
	 * Devuelve las propiedades pertenecientes a la instancia Jboss local filtrandolas por categoria. 
	 * La instancia JBoss local se ajustara automaticamente.
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesLocalesPorCategoria(
			String categoria) throws Exception {
		return handleGetPropiedadesJbossPorCategoria(obtenerInstanciaJbossLocal(), categoria);
	}


	/**
	 * Devuelve las propiedades pertenecientes a una instancia Jboss filtrandolas por el campo ficherosAfectados. 
	 * La instancia jboss puede ser global, default, node1, node2, node3, node4, node5, o node6
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesJbossPorFichero(
			String nombreFichero, String instanciaJboss) throws Exception {

		PropiedadesPorFicheroEInstanciaJbossCriteria c = new PropiedadesPorFicheroEInstanciaJbossCriteria(nombreFichero, instanciaJboss);
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = dao.obtenerPropiedadesPorFicheroEInstanciaJboss(dao.TRANSFORM_PROPIEDADVO, c);
		if (l == null || l.size() == 0) {
			logger.error("No se encontraron propiedades del fichero "+nombreFichero+" para la instancia jboss "+instanciaJboss);
			return new PropiedadVO[0];
		}
		PropiedadVO[] propiedades = l.toArray(new PropiedadVO[l.size()]);
		return propiedades;
	}


	/**
	 * Devuelve las propiedades pertenecientes a la instancia Jboss local filtrandolas por el campo ficherosAfectados. 
	 * La instancia JBoss local se ajustara automaticamente.
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesLocalesPorFichero(
			String nombreFichero) throws Exception {
		return handleGetPropiedadesJbossPorFichero(nombreFichero, obtenerInstanciaJbossLocal());
	}

	
	/**
	 * Devuelve las propiedades globales y las locales filtrandolas por el campo ficherosAfectados
	 */
	@Override
	protected PropiedadVO[] handleGetPropiedadesPorFichero(String nombreFichero)
			throws Exception {

		PropiedadDao dao = this.getPropiedadDao();

		//Obtenemos las propiedades locales
		PropiedadesPorFicheroEInstanciaJbossCriteria c = new PropiedadesPorFicheroEInstanciaJbossCriteria(nombreFichero, obtenerInstanciaJbossLocal());
		List<PropiedadVO> locales = dao.obtenerPropiedadesPorFicheroEInstanciaJboss(dao.TRANSFORM_PROPIEDADVO, c);
		if (locales == null || locales.size() == 0) {
			logger.debug("No se encontraron propiedades del fichero "+nombreFichero+" para la instancia jboss "+obtenerInstanciaJbossLocal());
			locales=new ArrayList<PropiedadVO>();
		}

		//Obtenemos las propiedades globales
		c.setInstanciaJboss(INSTANCIA_GLOBAL);
		List<PropiedadVO> globales = dao.obtenerPropiedadesPorFicheroEInstanciaJboss(dao.TRANSFORM_PROPIEDADVO, c);
		if (globales == null || globales.size() == 0) {
			logger.debug("No se encontraron propiedades del fichero "+nombreFichero+" para la instancia jboss "+INSTANCIA_GLOBAL);
			globales=new ArrayList<PropiedadVO>();
		}
		
		//Merge
		globales.addAll(locales);
		PropiedadVO[] propiedades = globales.toArray(new PropiedadVO[globales.size()]);
		return propiedades;
	}

	
	/**
	 * DEPRECATED
	 * Viene de la implementacion antigua de config plataforma, previa a la unificacion.
	 */
	@Override
	protected String handleGetUrlNodo() throws Exception { 
		return null;
	/*	
		String propertyValue = null;
		
		String host = handleGetValorPropiedadLocal(Propiedades.host.getNombre());
		String subdominio = handleGetValorPropiedadLocal(Propiedades.subdominio.getNombre());
		
		propertyValue = (subdominio == null || "".equals(subdominio))?"http://"+host:"http://"+host+subdominio;
		if(logger.isDebugEnabled()) logger.debug("getUrlNodo() devuelve : " + propertyValue);
		return propertyValue;
		*/
	}

	
	/**
	 * Devuelve todas las categorias existentes en las que se clasifican 
	 * las propiedades locales y globales que sean editables
	 */
	@Override
	protected String[] handleGetCategoriasPropiedadesModificables() throws Exception {
		if(LISTA_CATEGORIAS==null) 
			cargarCachePropiedades();
		return LISTA_CATEGORIAS.toArray(new String[LISTA_CATEGORIAS.size()]);
	}

	
	/**
	 * Devuelve la lista de instancias JBoss activas incluida la global
	 * Las instancias activas seran aquellas aquellas que tienen alguna variable no vacia
	 * o aquellas que su directorio fisico existe en el host local
	 */
	@Override
	protected String[] handleGetInstanciasJbossActivas() throws Exception {
		
		ArrayList<String> listaInstanciasActivas = new ArrayList<String>();
		listaInstanciasActivas.add(INSTANCIA_GLOBAL);
		

		PropiedadDao dao = this.getPropiedadDao();
		ArrayList<PropiedadVO> propiedades = new ArrayList<PropiedadVO>();
		propiedades.addAll(dao.loadAll(dao.TRANSFORM_PROPIEDADVO));
			
		for(int i=0; i<propiedades.size(); i++) {
			//Agregamos a la lista de instancias aquellas que tienen alguna variable no vacia
			if(propiedades.get(i).getValor()!=null && !propiedades.get(i).getValor().isEmpty())
				if(listaInstanciasActivas.indexOf(propiedades.get(i).getInstanciaJboss())==-1)
					listaInstanciasActivas.add(propiedades.get(i).getInstanciaJboss());	
		}
		
		if(LISTA_INSTANCIAS_JBOSS==null) {
			LISTA_INSTANCIAS_JBOSS = new ArrayList<String>();
			for(int i=0; i<propiedades.size(); i++) {
				if(LISTA_INSTANCIAS_JBOSS.indexOf(propiedades.get(i).getInstanciaJboss())==-1)
					LISTA_INSTANCIAS_JBOSS.add(propiedades.get(i).getInstanciaJboss());
			}
		}
			
		String serverJboss=obtenerDirectorioServerJboss();		
		File f=null;
		
		//Agregamos a la lista de instancias aquellas que su directorio fisico existe en el host local
		for(int i=0; i<LISTA_INSTANCIAS_JBOSS.size(); i++) {
			String rutaInstancia=serverJboss+File.separator+LISTA_INSTANCIAS_JBOSS.get(i);
			f = new File(rutaInstancia);
			if(f.exists()&&f.isDirectory())
				if(listaInstanciasActivas.indexOf(LISTA_INSTANCIAS_JBOSS.get(i))==-1)
					listaInstanciasActivas.add(LISTA_INSTANCIAS_JBOSS.get(i));
		}
		
		return listaInstanciasActivas.toArray(new String[listaInstanciasActivas.size()]);
	}

	
	@Override
	protected PropiedadVO[] handleGetAllProperties() throws Exception {
		PropiedadDao dao = this.getPropiedadDao();
		
		List<PropiedadVO> l = (List<PropiedadVO>) dao.loadAll(dao.TRANSFORM_PROPIEDADVO);
		if (l == null || l.size() == 0) {
			logger.error("No se encontraron propiedades en la DB");
			return new PropiedadVO[0];
		}
		PropiedadVO[] propiedades = l.toArray(new PropiedadVO[l.size()]);
		return propiedades;
	}
	
}