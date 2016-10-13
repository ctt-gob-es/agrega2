// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.indexador.negocio.servicios.indexado;

import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import es.pode.indexador.negocio.compass.GestorCompass;
import es.pode.indexador.negocio.compass.ThreadOptimizar;
import es.pode.indexador.negocio.dominio.IdentificadorCriteria;
import es.pode.indexador.negocio.dominio.IdentificadoresCriteria;
import es.pode.indexador.negocio.dominio.IdiomaCriteria;
import es.pode.indexador.negocio.dominio.IndiceBusqueda;
import es.pode.indexador.negocio.dominio.IndiceBusquedaImpl;
import es.pode.indexador.negocio.dominio.UsuarioEnContribucion;

 

/**
 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService
 */

public class SrvIndexadorServiceImpl extends
		es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceBase {
	
//	private Logger logger = Logger.getLogger(this.getClass());
	private Properties props = null;

	//string asignado a los publicadores en el campo contribuciones de la tabla INDICE_BUSQUEDA
	public static String ROL_PUBLICADOR="publisher";

	//string asignado a los autores en el campo contribuciones de la tabla INDICE_BUSQUEDA
	public static String ROL_AUTOR="author";
	
	public static String SPELL_CHECKER = "spell";
	
	//Delimitador de los datos de usuarios en el campo contribuciones de la tabla INDICE_BUSQUEDA
	public static String SEPARADOR_CAMPOS_MULTIVALUADOS;

	//Delimitador de los datos de usuarios en el campo contribuciones de la tabla INDICE_BUSQUEDA
	public static String SEPARADOR_CAMPOS_MULTIVALUADOS_MARSHALL;
	
	public static String INDEXACION_CORRECTA ="Indexacion correcta.";
	public static String INDEXACION_INCORRECTA ="Indexacion incorrecta.";
	public static String REINDEXACION_CORRECTA = "Reindexación correcta.";
	public static String REINDEXACION_INCORRECTA = "Reindexación incorrecta.";
	public static String ELIMINACION_INCORRECTA = "Eliminación incorrecta";
	public static String ELIMINACION_CORRECTA = "Eliminación correcta";
	public static String NO_EXISTE_ODE = "No existe ode";
	
	//Formato que se le dara a aquellos ODEs que tengan el formato en blanco
	private static String UNKNOWN_FORMAT = "unknown";
	

//	private static String campo_recurso=null;

	private GestorCompass gestorCompass;
	private static ThreadOptimizar threadOptimizar = null;
	
	public SrvIndexadorServiceImpl() {
		
		super();
		try {
			props = new Properties();
			props.load(this.getClass().getResourceAsStream("/indexador.properties"));
//			campo_recurso=props.getProperty("campo_recurso");
			SEPARADOR_CAMPOS_MULTIVALUADOS= props.getProperty("separadorCamposMultivaluados");
			SEPARADOR_CAMPOS_MULTIVALUADOS_MARSHALL= props.getProperty("separadorCamposMultivaluadosMarshall");
			threadOptimizar = new ThreadOptimizar();
		} catch (IOException e) {
			logger.error("ERROR: fichero no encontrado: indexador.properties", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Indexa un array de Objetos Digitales Educativos
	 * 
	 * @param odes
	 *            Objeto donde le indicamos la información crítica del ODE a indexar.
	 * @return IndexadorVO[] Se devuelve un objeto de valor con el exito o el fracaso de la indexación de cada ODE.
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService#IndexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[])
	 */
 	
		protected IndexadorVO[] handleIndexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes)
		throws java.lang.Exception {
			if (odes != null && odes.length > 0) {
				ArrayList<IndexadorVO> respuestas = new ArrayList<IndexadorVO>();
				ArrayList<IndiceBusquedaImpl> doc = new ArrayList<IndiceBusquedaImpl>();
				logger.debug("La longitud de la lista que queremos insertar en la BD es["+ odes.length + "].");
//				Mapeamos la lista de ODEs que nos pasan a objetos de dominio
				for (int i = 0; i < odes.length; i++) {
					IndiceBusquedaImpl nuevoIndiceBusqueda = new IndiceBusquedaImpl();
					this.getBeanMapper().map(odes[i], nuevoIndiceBusqueda);
					doc.add(nuevoIndiceBusqueda);
				}
//				Para cada nuevo objeto de dominio, lo inserto. Podríamos invocar la creación masiva, pero el ultima instancia
//				plantea una inserción unitaria igualmente y pierdo el detalle de cada uno de los ODEs.
				for (int i = 0; i < doc.size(); i++) {
					try {
//						this.getIndiceBusquedaDao().create((IndiceBusquedaImpl)doc.get(i));
//						Ya no llamamos al dao, lo hacemos a través de otro metodo con la esperanza de que si ocurre una
//						excepción trajinando con la BD se realice un rollback completo con la operacion en BD y en compass
//						de manera que no queden desincronizados.
						this.handleIndexarODE(doc.get(i));
//						Si todo va bien, pues lo indico
						respuestas.add(new IndexadorVO(doc.get(i).getLocalizador(),0, INDEXACION_CORRECTA));
//						logger.debug("ODE indexado con identificador["+doc.get(i).getIdentificador()+"] y localizador["+doc.get(i).getLocalizador()+"] indexado.");
					}
					catch (IndexaODEException e1)
					{
//						Casco la inserción directa en BD por algun motivo, lo indico y sigo.
						logger.warn("Error insertando el ODE con identificador["+doc.get(i).getIdentificador()+"] y localizador["+doc.get(i).getLocalizador()+"], sigo indexando el resto de elementos.",e1);
						respuestas.add(new IndexadorVO(doc.get(i).getLocalizador(),1, INDEXACION_INCORRECTA));
					}
					catch (Exception e) {
//						Si hay problemas insertando lo indico con el ode que toque pero sigo adelante con el resto.
						logger.warn("Error general insertando el ODE con identificador["+doc.get(i).getIdentificador()+"] y localizador["+doc.get(i).getLocalizador()+"], sigo indexando el resto de elementos.",e);
						respuestas.add(new IndexadorVO(doc.get(i).getLocalizador(),1, INDEXACION_INCORRECTA));
					}
					
				}
				return respuestas.toArray(new IndexadorVO[0]);
			}
			logger.error("Lista de ODEs a indexar nula o vacía["+(odes==null?null:0)+"]. No hay ODEs para indexar.");
			throw new Exception("Lista de ODEs a indexar nula o vacía["+(odes==null?null:0)+"]. No hay ODEs para indexar.");
		}
	
		



	 /**
	 * Indexa un Objeto Digital Educativo
	 * 
	 * @param ode
	 *            Objeto donde le indicamos la información crítica del ODE a indexar.
	 * @return IndexadorVO Se devuelve un objeto de valor con el exito o el fracaso de la indexación del ODE.
	 * @throws java.lang.Exception
	 * @see @see es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceBase#handleIndexarODE(es.pode.indexador.negocio.dominio.IndiceBusqueda)
	 */

	protected IndexadorVO handleIndexarODE(IndiceBusqueda ode)
	throws IndexaODEException, java.lang.Exception {
		if (ode == null)
		{
			logger.error("ODE a indexar nulo. No hay ODE que indexar.");
			throw new Exception("ODE a indexar nulo. No hay ODE que indexar.");
		}
		IndexadorVO respuesta /*= new IndexadorVO()*/;
		logger.debug("Queremos insertar ODE con identificador["+ode.getIdentificador()+"] y localizador["+ode.getLocalizador()+"]");
//		Para cada nuevo objeto de dominio, lo inserto.
		try {
			if(ode.getFormato()==null || ode.getFormato().isEmpty() || ode.getFormato().equals("")) {
				ode.setFormato(UNKNOWN_FORMAT);
				logger.debug("SrvIndexadorServiceImpl - handleIndexarODE: se detecto formato vacio en el ODE a indexar. Formato se cambia a '"+UNKNOWN_FORMAT+"'");
			}
			this.getIndiceBusquedaDao().create(ode);
//			Si todo va bien, pues lo indico
			respuesta = new IndexadorVO(ode.getLocalizador(),0, INDEXACION_CORRECTA);
			logger.debug("ODE indexado con identificador["+ode.getIdentificador()+"] y localizador["+ode.getLocalizador()+"] indexado.");
		} catch (Exception e) {
//			Si hay problemas insertando lo indico en la excepcion, ya que puede haberse complicado la inserción
//			y tener que recurrir a lanzar una excepción para provocar el rollback de la operación y que no haya
//			discrepancias entre el compass y la BD
			logger.error("Error insertando el ODE con identificador["+ode.getIdentificador()+"] y localizador["+ode.getLocalizador()+"]",e);
			throw new IndexaODEException(INDEXACION_INCORRECTA);
		}
		return respuesta;
	}

		
	/**
	 * Reindexado de ODEs. Este método recibe una lista de ODEs con toda la información necesaria para su reindexación y refresca la información
	 * 			    	   indexada de cada ode suministrado por la que se le pasa. En el caso de que en la lista aparezcan ODEs no indexados, 
	 * 					   estos no se insertan.
	 * 					   Se devuelve un objeto de valor con el exito o el fracaso de la reindexación de cada ODE.
	 * @param odes
	 *       	Lista de objetos donde le indicamos la información crítica de los ODEs a reindexar.
	 * @return IndexadorVO[] Se devuelve un objeto de valor con el exito o el fracaso de la reindexación de cada ODE.
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService#reindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[])
	 */
	protected IndexadorVO[] handleReindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes)
	throws java.lang.Exception {
		// El objeto debe estar indexado.
		// Para reindexar un documento recuperamos el identificador, y buscamos si ya existía en la BD
		// La reindexación puede ser unitaria o múltiple.
		ArrayList<IndexadorVO> respuestas = new ArrayList<IndexadorVO>();

		if (odes == null || odes.length == 0) {
			logger.error("Lista de ODEs para reindexar nula o vacia["+ (odes==null?null:0) + "]. No hay ODEs a reindexar.");
			throw new Exception("Lista de ODEs para reindexar nula o vacia["+ (odes==null?null:0) + "]. No hay ODEs a reindexar.");
		}
		//Si solo tengo un ODE que reindexar, lo planteo de forma unitaria
		if (odes.length == 1) { 
			return new IndexadorVO[]{reindexacionUnitaria(odes[0])};
			//Planteamos la reindexación múltiple
		}
		//Recopilamos la lista de id's
		List<String> listaIdentificadores = new ArrayList<String>();
		for (int i = 0; i < odes.length; i++) {
			listaIdentificadores.add(odes[i].getIdODE());
		}
		String[] idOdes = listaIdentificadores.toArray(new String[0]);
		// Buscamos en la BD, todos los objetos cuyo identificador sea igual
		// a uno de los de la lista de odes que queremos reindexar.
		IdentificadoresCriteria criterio = new IdentificadoresCriteria(idOdes);
		List listaODEsBD = this.getIndiceBusquedaDao().obtenODEsPorIdentificador(criterio);

		//Si no hay odes indexados en la lista de ODEs que me pasan, lo que hay que plantear es una indexación, no un reindexado
		if (listaODEsBD == null || listaODEsBD.size() == 0)
		{
			logger.error("De la lista de ODEs a reindexar de["+odes.length+"] elementos, no hay ninguno indexado. No se puede reindexar ningún ODE.");
			throw new Exception("De la lista de ODEs a reindexar de["+odes.length+"] elementos, no hay ninguno indexado. No se puede reindexar ningún ODE.");				
		}

		// Creo un TreeMap, para pasarle la lista de odes que he recuperado de la BD.
		TreeMap<String, IndiceBusquedaImpl> mapa = new TreeMap<String, IndiceBusquedaImpl>();
		for (int j = 0; j < listaODEsBD.size(); j++) {
			mapa.put(((IndiceBusquedaImpl) listaODEsBD.get(j)).getIdentificador(), (IndiceBusquedaImpl) listaODEsBD.get(j));
		}
		for (int k = 0; k < odes.length; k++) {
			// Tengo que ver si los ODE que me pasan estan todos en la BD o hay alguno nuevo,
			// en cuyo caso, sería una indexación.
			if (mapa.containsKey(odes[k].getIdODE())) {
				this.getBeanMapper().map(odes[k],mapa.get(odes[k].getIdODE()));
				try {
					this.getIndiceBusquedaDao().update(mapa.get(odes[k].getIdODE()));
					respuestas.add(new IndexadorVO(odes[k].getLocalizadorODE(),0, REINDEXACION_CORRECTA));
					logger.debug("Reindexado el ODE con id["+odes[k].getIdODE()+"].");
				} catch (Exception e) {
					respuestas.add(new IndexadorVO(odes[k].getLocalizadorODE(),1, REINDEXACION_INCORRECTA));
					logger.warn("Error reindexando el ODE con identificador["+odes[k].getIdODE()+"] y localizador["+odes[k].getLocalizadorODE()+"]. Seguimos con el resto de reindexaciones.",e);
				}

			} else {// El ODE que me pasan en la lista no esta en el resultado de la select a BD, no existe indexado, no lo reindexo
				respuestas.add(new IndexadorVO(odes[k].getLocalizadorODE(),1, REINDEXACION_INCORRECTA));
				logger.warn("Error reindexando el ODE con identificador["+odes[k].getIdODE()+"] y localizador["+odes[k].getLocalizadorODE()+"]. No esta en la BD, debe indexarlo. Seguimos con el resto de reindexaciones.");
			}
		}
		// Ya he hecho la reindexación, devuelvo resultados, con el exito o fracaso de la operación.
		return respuestas.toArray(new IndexadorVO[respuestas.size()]);
	}

	/**
	 * Eliminacion de ODEs de los indices.
	 * 					Este método recibe una lista de identificadores de ODEs que se quieren eliminar.			    	
	 * 
	 * @param idODE
	 *         Array de identificadores alfanumericos de los ODEs que se  quieren eliminar.
	 * @return IndexadorVO[] Se devuelve un objeto de valor con el éxito o el fracaso de la eliminación de cada ODE.
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService#eliminarODE(java.lang.String[])
	 */

	protected IndexadorVO[] handleEliminarODE(String[] idODE)throws java.lang.Exception {

		ArrayList<IndexadorVO> respuestas = new ArrayList<IndexadorVO>();
		// Si la lista de identificadores es nula o vacia no puedo hacer nada.
		if (idODE == null || idODE.length == 0){
			logger.error("Lista de ODEs para eliminar nula o vacia["+ (idODE==null?null:0) + "]. No hay ODEs a eliminar.");
			throw new Exception("Lista de ODEs para eliminar nula o vacia["+ (idODE==null?null:0) + "]. No hay ODEs a eliminar.");
		}
		if (idODE.length == 1)
			//Eliminacion Unitaria.
			return new IndexadorVO[]{eliminacionUnitaria(idODE[0])};
		//Eliminación múltiple de ODEs
		IdentificadoresCriteria criterio = new IdentificadoresCriteria(idODE);
		List odes = this.getIndiceBusquedaDao().obtenODEsPorIdentificador(criterio);
//			Planteamos la eliminacion unitaria para no perder el detalle de lo ocurrido con la eliminacion de cada ODE.
//			Creo un TreeMap, para pasarle la lista de odes que he recuperado de la BD.
		TreeMap<String, IndiceBusquedaImpl> mapa = new TreeMap<String, IndiceBusquedaImpl>();
		for (int j = 0; j < odes.size(); j++) {
			mapa.put(((IndiceBusquedaImpl) odes.get(j)).getIdentificador(), (IndiceBusquedaImpl) odes.get(j));
		}
		for (int k = 0; k < idODE.length; k++) {
			// Tengo que ver si los ODEs que me pasan estan en la BD o hay alguno que no existe en la BD
			if (mapa.containsKey(idODE[k])) {
				try {
					respuestas.add(new IndexadorVO(mapa.get(idODE[k]).getLocalizador(),0, ELIMINACION_CORRECTA));
					this.getIndiceBusquedaDao().remove(mapa.get(idODE[k]));
					logger.debug("Eliminado el ODE con id["+idODE[k]+"]");
				} catch (Exception e) {
					respuestas.add(new IndexadorVO(mapa.get(idODE[k]).getLocalizador(),1, ELIMINACION_INCORRECTA));
					logger.warn("Error eliminando el ODE con identificador["+idODE[k]+"] y localizador["+mapa.get(idODE[k])+"]. Seguimos con el resto de eliminaciones.",e);
				}
			} else {// El ODE que me pasan en la lista no esta en el resultado de la select a BD, no existe indexado, no lo puedo eliminar
				respuestas.add(new IndexadorVO(mapa.get(idODE[k]).getLocalizador(),1, ELIMINACION_INCORRECTA));
				logger.warn("Error eliminando el ODE con identificador["+idODE[k]+"] y localizador["+mapa.get(idODE[k])+"]. El ODE no esta en la BD, imposible eliminarlo.");
			}
		}
		//Ya he eliminado los odes, devuelvo resultados con el exito o fracaso de la operación.
		return respuestas.toArray(new IndexadorVO[respuestas.size()]);
	}

	/**
	 * Se regenera el indice con la lista de ODEs que se le pasa. Se borra el indice del idioma indicado y se
	 * puebla con los ODEs suministrados. Se supone que los ODEs que se suministran sirven para poblar odes en el idioma
	 * @param idioma Codigo de idioma correspondiente al indice que se quiere regenerar.
	 * @param odes Contenido de los ODEs que se quieren indexar.
	 * @return IndexadorVO[] Se devuelve un array con el exito o fracaso de la regeneracion para cada ode afectado.
	 * @throws Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService#RegenerarIndice(java.lang.String,es.pode.indexador.negocio.servicios.indexado.IdODEVO[])
	 */
	protected IndexadorVO[] handleRegenerarIndice(String idioma, IdODEVO[] odes) throws Exception {
		//Si no hay idioma, no hay indice que borrar
		if (idioma == null || idioma.equals("")) {
			logger.error("Idioma para regenerar indice vacio o nulo["+ (idioma==null?null:"") + "]. No hay idioma del indice que regenerar.");
			throw new Exception("Idioma para regenerar indice vacio o nulo["+ (idioma==null?null:"") + "]. No hay idioma del indice que regenerar.");
		}
		//Si no hay ODEs que indexar, no hay información con la que poblar el indice si lo borro.
		if (odes == null || odes.length == 0) {
			logger.error("Lista de ODEs para regenerar indice de idioma["+idioma+"]nula o vacia["+ (odes==null?null:0) + "]. No hay ODEs a reindexar.");
			throw new Exception("Lista de ODEs para regenerar indice de idioma["+idioma+"]nula o vacia["+ (odes==null?null:0) + "]. No hay ODEs a reindexar.");
		}
		//Eliminamos los ODEs indexados por el idioma que nos pasan. Esto elimina todos los ODEs de este idioma.
		this.handleBorrarPorIdioma(idioma);
		logger.info("Borrados los ODEs indexados para el idioma["+idioma+"]. Regenerando indice["+idioma+"]");

		//Indexamos los ODEs que nos pasan. 
		IndexadorVO[] respuesta = this.handleIndexarODE(odes);
		logger.info("Indexados ["+odes.length+"] ODEs. Regenerado indice["+idioma+"]");
		return respuesta; //Devolvemos el exito/fracaso de la operación.
	}

	/**
	 * Este método regenera todos los indices del repositorio con los ODEs que se le pasan en el momento. 
	 * Toma como parametro un identificador de tarea donde escribe el resultado de la ejecucion. Cada ODE se 
	 * introduce en el indice del idioma al que pertenece.
	 * Se devuelve un array con el exito o fracaso de la regeneracion para cada ode.
	 * @param idTarea Identificador de la tarea.
	 * @param odes ODEs que se utilizan para la regeneracion de los indices.
	 * @return IndexadorVO[] Se devuelve un array con el exito o fracaso de la regeneracion para cada ode afectado.
	 * @throws Exception 
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService#RegenerarIndices(java.lang.Long)
	 * @deprecated se deja de usar este método, en su lugar llamaremos a RegenarIndice(String idioma, IdODEVO[] odes).
	 */
	protected IndexadorVO[] handleRegenerarIndices(Long idTarea, IdODEVO[] odes) throws Exception {
		return null;
//		Recuperamos todos los indices
//		ArrayList resultado = (ArrayList) this.getIndiceDao().loadAll(IndiceDao.TRANSFORM_INDICEVO);
//		if (resultado == null || resultado.size() ==0)
//		{
//			if(logger.isDebugEnabled())logger.debug("Error obteniendo indices. Tabla vacia. No se regeneran los indices.");
//			throw new Exception("Error obteniendo indices. Tabla vacia. No se regeneran los indices.");
//		}
//		try {
//			for (int i = 0; i < resultado.size(); i++) {
////				Eliminamos todos los documetos de este indice.
//				this.borradoTodosDocumentos(((IndiceVO)resultado.get(i)).getIdentificador(),odes);
//			}
//		} catch (Exception e) {
//			logger.error("Error regenerando todos los indices["+e.getCause()+"]");
//			throw new Exception("Error regenerando todos los indices",e);
//		}
////		Indexamos los ODEs. La indexacion se hace para cada ode en su indice dependiendo del idioma de catalogacion
////		Se supone que el invocador de este metodo es consciente de que borra el indice del idioma dado y que los
////		ODEs pertenecen a este idioma.
//		return this.handleIndexarODE(odes);
	}

	
	/**
	 * 	Este metodo recibe un ODE para reindexarlo. Verifica que existe en la BD indexado y modifica su información con la que
	 * 	le pasan. Si no existe, no lo inserta.
	 * 	Devuelve un objeto de valor con el exito o el fracaso de la operación.
	 */
	private IndexadorVO reindexacionUnitaria(es.pode.indexador.negocio.servicios.indexado.IdODEVO ode)
	throws java.lang.Exception {

//		Si no hay ODE que reindexar no hay que hacer nada
		if (ode == null || ode.getIdODE() == null || ode.getIdODE().equals("")) {
			logger.error("Error reindexando ODE vacio o sin identificador["+ (ode==null?null:ode.getIdODE()) + "] y localizador["+(ode==null?null:ode.getLocalizadorODE())+"]. No hay ODE a reindexar.");
			throw new Exception("Error reindexando ODE vacio o sin identificador["+ (ode==null?null:ode.getIdODE()) + "] y localizador["+(ode==null?null:ode.getLocalizadorODE())+"]. No hay ODE a reindexar.");
		} 
		IdentificadorCriteria criterio = new IdentificadorCriteria(ode.getIdODE());
		ArrayList indiceBusq = (ArrayList) this.getIndiceBusquedaDao().busquedaIndicePorIdentificador(criterio);
//		Si el ODE no esta indexado, no hay nada que hacer.
		if (indiceBusq == null || indiceBusq.size() == 0|| indiceBusq.size() > 1) {
			logger.error("Error buscando informacion indexada para ode con id["+ode.getIdODE()+"] y localizador["+ode.getLocalizadorODE()+"]. El ODE no esta indexado o existen["+(indiceBusq != null?indiceBusq.size():0)+"]instancias. No se puede reindexar.");
			return new IndexadorVO(NO_EXISTE_ODE,1,REINDEXACION_INCORRECTA);
		}
//		Mapeamos el valor nuevo al viejo indexado
		IndiceBusquedaImpl antiguo = (IndiceBusquedaImpl) indiceBusq.get(0);
		this.getBeanMapper().map(ode, antiguo);
		try {
			this.getIndiceBusquedaDao().update(antiguo);
			logger.info("Reindexado el ODE con id["+ode.getIdODE()+"].");
			return new IndexadorVO(ode.getLocalizadorODE(), 0,REINDEXACION_CORRECTA);
		} catch (RuntimeException e) {
			logger.warn("Error reindexando el ODE con identificador["+ode.getIdODE()+"] y localizador["+ode.getLocalizadorODE()+"].",e);
			return new IndexadorVO(ode.getLocalizadorODE(),1, REINDEXACION_INCORRECTA);
		}
	}

	/**
	 * 	Este metodo recibe un ODE para eliminarlo. Verifica que existe en la BD indexado y elimina su información.
	 * 	Devuelve un objeto de valor con el exito o el fracaso de la operación.
	 */
	private IndexadorVO eliminacionUnitaria(String idODE)throws java.lang.Exception{

//		Si no hay idioma, no hay indice que borrar
		if (idODE == null || idODE.equals("")) {
			logger.error("Error eliminando ODE vacio o sin identificador["+ (idODE==null?null:idODE) + "]. No hay ODE a eliminar");
			throw new Exception("Error eliminando ODE vacio o sin identificador["+ (idODE==null?null:idODE) + "]. No hay ODE a eliminar");
		}
		//Eliminación unitaria
		IdentificadorCriteria criterio = new IdentificadorCriteria(idODE);
//		List listaIdsBD = new ArrayList();
		List listaIdsBD;
		listaIdsBD = (List) this.getIndiceBusquedaDao().busquedaIndicePorIdentificador(criterio);
		// listaIdsBD tiene el ODE que hay de la BD, con identificador idODE. Si la lista tiene más de un 
		// elemento me dará un error.
		if (listaIdsBD == null || listaIdsBD.size() == 0|| listaIdsBD.size() > 1) {
			logger.error("Error buscando informacion indexada para ode con id["+idODE+"]. El ODE no esta indexado o existen["+(listaIdsBD != null?listaIdsBD.size():0)+"] instancias. No se puede eliminar.");
			return new IndexadorVO(NO_EXISTE_ODE,1, ELIMINACION_INCORRECTA);
		}
		IndiceBusqueda indLista = (IndiceBusqueda) listaIdsBD.get(0);
		try {
			this.getIndiceBusquedaDao().remove(indLista.getId());
			logger.debug("Eliminado el ODE con id["+idODE+"]");
			return new IndexadorVO(indLista.getLocalizador(), 0, ELIMINACION_CORRECTA);
		} catch (RuntimeException e) {
			logger.debug("Error eliminando el ODE con identificador["+idODE+"] y localizador["+indLista.getLocalizador()+"].",e);
			return new IndexadorVO(indLista.getLocalizador(),1, ELIMINACION_INCORRECTA);
		}
		
	}
	
	/**
	 * Borrado de ODEs Por Idioma. 
	 * 				Este metodo recibe un idioma, busca en la BD todos los odes que esten indexados en ese idioma
	 *  			y los elimina.
	 * @param idioma
	 *       	idioma de los ODEs que queremos eliminar.
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService# borrarPorIdioma(java.lang.String idioma);
	 * @deprecated
	 */
	protected void handleBorrarPorIdioma(String idioma)throws java.lang.Exception{
		handleBorrarOdesPorIdioma(idioma, 0);
	}

	public GestorCompass getGestorCompass() {
		return gestorCompass;
	}

	public void setGestorCompass(GestorCompass gestorCompass) {
		this.gestorCompass = gestorCompass;
	}

	/**
	 * Optimizado de indices. 
	 * 				Este metodo optimiza el subindice en el idioma indicado 
	 * @param idioma
	 *       	idioma del subindice que queremos optimizar, si su valor es null se optimizan todos los subindices.
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService# optimizarIndice(java.lang.String[] idioma);
	 */
	protected void handleOptimizarIndice(String idioma) throws Exception {
		try{
			
			logger.info("El estado del hilo ["+ threadOptimizar.getName()+"] es --> " +threadOptimizar.getState().name());
			
			if(threadOptimizar==null) threadOptimizar = new ThreadOptimizar();
			
			threadOptimizar.setIdioma(idioma);
			threadOptimizar.setGestorCompass(this.getGestorCompass());
			
			if((threadOptimizar.getState()).equals(State.NEW)){
//				threadOptimizar.setFechaUltEjecucionMilis(System.currentTimeMillis());
				logger.info("Lanzamos el hilo que optimizará el indice [" + (idioma!=null?idioma:"todos") +"]");
				threadOptimizar.start();
				
			}
			else if((threadOptimizar.getState()).equals(State.TERMINATED)){
//				threadOptimizar.setFechaUltEjecucionMilis(System.currentTimeMillis());
				logger.debug("Ejecutamos la optimización del indice [" + (idioma!=null?idioma:"todos") +"]");
				threadOptimizar.run();
			}else{
				logger.debug("El hilo ya esta ejecuntado una optimización.");
			}
			 
		}catch(Exception e){
			logger.error("Error optimizando indice [" + (idioma!=null?idioma:"todos") +"]",e);
		}

	}

	@Override
	/**
	 * Borrado de ODEs Por Idioma. 
	 * 				Este metodo recibe un idioma, busca en la BD todos los odes que esten indexados en ese idioma
	 *  			y los elimina.
	 * @param idioma
	 *       	idioma de los ODEs que queremos eliminar.
	 * @param num numero de ODEs a borrar 
	 * @throws java.lang.Exception
	 * @see es.pode.indexador.negocio.servicios.SrvIndexadorService# borrarPorIdioma(java.lang.String idioma);
	 */
	protected void handleBorrarOdesPorIdioma(String idioma, int num)
			throws Exception {
//		if(idioma == null || idioma == ""){
		if(idioma == null || idioma.equals("")){
//			Si no hay idioma, no hay odes que borrar
			logger.error("Error borrando el ODE por idioma: ["+idioma==null?null:"" + "].");
			throw new Exception("Error borrando el ODE por idioma: ["+idioma==null?null:"" + "].");
		}
		//Eliminamos los ODEs por idioma.
		IdiomaCriteria criterio = new IdiomaCriteria(idioma);
		if (num!=0) {
			criterio.setMaximumResultSize(num);
		}
		List odes = this.getIndiceBusquedaDao().obtenODEsPorIdioma(criterio);
		//Si no hay odes indexados en ese idioma, no hago nada.
		if (odes == null || odes.size() == 0){
			logger.warn("Por el idioma ["+ idioma + "] no hay ODEs indexados. No se elimina nada.");
			return;				
		}
		//Como no nos interesa el detalle, vamos a hacer un borrado múltiple de odes
		try{
			logger.info("Hay ["+odes.size()+"] ODEs indexados en idioma["+idioma+"]. Procedemos a eliminarlos.");
			this.getIndiceBusquedaDao().remove(odes);
			logger.info("Eliminados ["+odes.size()+"] ODEs indexados en idioma["+idioma+"].");
		}catch (Exception e) {
			logger.error("Error eliminando ["+odes.size()+"]ODEs por idioma ["+idioma+"].",e);
			throw new Exception("Error eliminando ["+odes.size()+"]ODEs por idioma ["+idioma+"].",e);
		}
	
	}

	/*
	 * Este servicio sirve para sincronizar la informacion de la base de datos y el indice.
	 * En concreto vuelca la informacion de la DB al indice. La informacion que contenga el indice con anterioridad se desecha.
	 * @see es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceBase#handleSincronizarIndiceCompass()
	 */
	@Override
	protected boolean handleSincronizarIndiceCompass() throws Exception {
		logger.info("handleSincronizarIndiceCompass - Ejecutamos sincronizar.");
		return this.getGestorCompass().sincronizar();
	}

	
	/**
	 * Devuelve los ODEs que tienen como autor o publicador a un usuario determinado
	 */
	@Override
	protected String[] handleObtenerOdesConUsuarioEnContribucion(
			String idUsuario) throws Exception {
		
		ArrayList<String> resultado = new ArrayList<String>(); 
		
		//Seleccionamos aquellos ODEs en los que el usuario aparece en la contribuciones
		UsuarioEnContribucion c1 = new UsuarioEnContribucion(idUsuario);
		List odes = this.getIndiceBusquedaDao().obtenODEsPorUsuarioEnContribucion(c1);

		for(int i=0; i<odes.size(); i++) {

			String idOde=((IndiceBusquedaImpl) odes.get(i)).getIdentificador();
			
			//Solo nos vale si la contribucion es como autor o publicador 
			IdentificadorCriteria c2 = new IdentificadorCriteria(idOde);
			ArrayList indiceBusq = (ArrayList) this.getIndiceBusquedaDao().busquedaIndicePorIdentificador(c2);
			IndiceBusqueda ind = (IndiceBusqueda) indiceBusq.get(0);
			String campoContribucion = ind.getContribucion();			
			String contribuciones[] = campoContribucion.split(SEPARADOR_CAMPOS_MULTIVALUADOS_MARSHALL);
						
			for(int c=0; c<contribuciones.length; c++) {
				if(contribuciones[c].contains(ROL_PUBLICADOR) || contribuciones[c].contains(ROL_AUTOR)) {
					if(contribuciones[c].contains(idUsuario)) {
						resultado.add(idOde);
						break;
					}
				}
			}
		}
		return (String[])resultado.toArray(new String[0]);
	}

}