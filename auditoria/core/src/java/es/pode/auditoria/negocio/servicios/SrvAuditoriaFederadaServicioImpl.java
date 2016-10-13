// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */


//TODO añadir un método de generación del informe federado planificado, sería semejante al método SrvAuditoriaServicioImpl.handleCrearGuardarInforme
//Los informes federados se guardarán en otro directorio para que luego en el listado de informes aparezca otra pestaña con los informes federados
//Se deberán crear plantillas nuevas para cada uno de los informes federados.

package es.pode.auditoria.negocio.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.Unmarshaller;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.buscar.negocio.administrar.nodo.castor.NodoConf;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonConRutaVO;
import es.pode.indexador.negocio.servicios.auditoria.CoberturaVO;
import es.pode.indexador.negocio.servicios.auditoria.ParametroAuditIndexadorVO;
import es.pode.indexador.negocio.servicios.busqueda.ResultadosCountVO;
import es.pode.indexador.negocio.servicios.busqueda.ResultadoRepositorioVO;
import es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO;
import es.pode.publicacion.negocio.servicios.ODEsEstadoVO;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;



/**
 * @see es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaServicio
 */

public class SrvAuditoriaFederadaServicioImpl
    extends es.pode.auditoria.negocio.servicios.SrvAuditoriaFederadaServicioBase
{

	private Logger logger = Logger.getLogger(this.getClass());
	private java.util.Properties pAuditoriaProperties = null;
	private static String localhost = "localhost";
	private SrvPropiedadService prop = null;
	public static final int NIVELES_AGREGACION = 4;
	public static final int TERCER_NIVEL_ARBOL_CURRICULAR = 3;
	
	
	 /**
	 * Obtiene el informe de Nivel de Agregación federado. Devuelve el número de odes existentes en cada nivel de agregación para cada nodo de la federación 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeAgregacionFederadaVO[]
	 * @throws Exception
	 */
    protected NivelAgregacionVO[] handleInformeNivelAgregacionFederado(ParametrosInformeFederadoVO parametros)
        throws Exception
    {
    	logger.debug("handleInformeNivelAgregacionFederado begins");
       	NivelAgregacionVO[] resultado = null;
		
		parametros.setFechaDesde(new GregorianCalendar(new Integer("2007").intValue(), new Integer("1").intValue() - 1, new Integer("1").intValue(), new Integer("1").intValue(), new Integer("0").intValue()));
		parametros.setFechaHasta(new GregorianCalendar()); 
		parametros.setIdioma(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
		parametros.setTipoInforme("nivelAgregacionFederada");
	
		File directorioIndicesRemotos = null;
		
		try {
			directorioIndicesRemotos = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS));
		} catch (Exception ex) {
			logger.warn("no se pudo leer el directorio de indices");
			throw new Exception("No existe el directorio que deberia contener los ficheros de indices");
		}
		
    	if (directorioIndicesRemotos.list() != null && directorioIndicesRemotos.list().length > 0) {
    		resultado = new NivelAgregacionVO[directorioIndicesRemotos.list().length];
    		
    		int i = 0;
    		for (String directorioIndicesNodoActual : directorioIndicesRemotos.list()) {
    			String nodoActual = directorioIndicesNodoActual;

    			NivelAgregacionContenido[] nivelAgregacionContenidos = new NivelAgregacionContenido[NIVELES_AGREGACION];
    			
    			try {
	    			for (int j = 0; j < NIVELES_AGREGACION; j++) {
	    				ResultadosCountVO resultadosCountVO = this.getSrvBuscadorService().busquedaDocsRangoFechaNivelAgregacionIndices(String.valueOf(j+1), parametros.getFechaDesde(), parametros.getFechaHasta(), nodoActual, parametros.getIdioma());
	    				nivelAgregacionContenidos[j] = new NivelAgregacionContenido(String.valueOf(j+1), resultadosCountVO.getDocumentosCount());
			    	}
    			} catch (Exception ex) {
    				for (int j = 0; j < NIVELES_AGREGACION; j++) {
	    				ResultadosCountVO resultadosCountVO = new ResultadosCountVO();
	    				resultadosCountVO.setDocumentosCount(0);
	    				nivelAgregacionContenidos[j] = new NivelAgregacionContenido(String.valueOf(j+1), resultadosCountVO.getDocumentosCount());
			    	}
    			}

    			NivelAgregacionPublicacion nivelAgregacionPublicacion = new NivelAgregacionPublicacion(Calendar.getInstance(), nivelAgregacionContenidos);
    			
    			NivelAgregacionPublicacion[] nivelAgregacionPublicaciones = new NivelAgregacionPublicacion[1];
    			nivelAgregacionPublicaciones[0] = nivelAgregacionPublicacion;

    			NivelAgregacionVO nivelAgregacionVO = new NivelAgregacionVO(nivelAgregacionPublicaciones, nombreNodoFicheroConf(nodoActual));
    			resultado[i] = nivelAgregacionVO;    			
    			i++;
			}
    	} else {
			logger.warn("El directorio de indices o no existe o no tiene carpetas");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de indices");
    	}
    	
    	logger.debug("handleInformeNivelAgregacionFederado ends");
  		return resultado;
    }
    
    /**
	 * Obtiene el informe de Nivel de Agregación local. Devuelve el número de odes existentes en cada nivel de agregación para un nodo en concreto 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeAgregacionFederadaVO. VO con los odes existentes por nivel de agregación
	 * @throws Exception
	 */
	protected NivelAgregacionVO handleNivelAgregacionFederadoLocal(ParametrosInformeFederadoVO parametros) throws Exception
	{		
		NivelAgregacionVO nivelAgregacionVO= null;
		final Integer nivelAgregacionCatalogo = 1;
		Calendar fechaAct = new GregorianCalendar();
		Date dateAct = fechaAct.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); 
		String fechaActual = sdf.format(dateAct);
		Calendar fechaNivelAgrega=parametros.getFechaNivelAgregacion();
		int anioNivelAgregacion2 = new Integer(parametros.getFechaNivelAgregacion().get(Calendar.YEAR));
		int mesNivelAgregacion2 = new Integer(parametros.getFechaNivelAgregacion().get(Calendar.MONTH)+1);
		String mesNivelAgregacion2Str=null;
		if (mesNivelAgregacion2<10){
			mesNivelAgregacion2Str ="0"+ String.valueOf(mesNivelAgregacion2);
		}else{
			mesNivelAgregacion2Str = String.valueOf(mesNivelAgregacion2);
		}
		
//		String fechaNivelAgregacion=sdf.format(dateNivelAgregacion);
		String fechaNivelAgregacion=String.valueOf(anioNivelAgregacion2)+mesNivelAgregacion2Str+"10";

		

		int mesNivelAgregacion=new Integer (Integer.parseInt(fechaNivelAgregacion.substring(4, 6)));
		int anioNivelAgregacion=new Integer (Integer.parseInt(fechaNivelAgregacion.substring(0, 4)));
		int mesActual=new Integer (Integer.parseInt(fechaActual.substring(4,6)));
		int anioActual= new Integer (Integer.parseInt(fechaActual.substring(0,4)));
		int diferenciaMeses=this.diferenciaMeses(fechaNivelAgrega, fechaAct);
		logger.debug("fecha NivelAgregacion: <" + fechaNivelAgregacion+">");
		String fechaString = String.valueOf(anioActual)+String.valueOf(mesActual)+"10";
		logger.debug("fecha Actual: <" + fechaAct+"> Diferencia Meses: <"+ diferenciaMeses+">");
		String mesNivelAgregacionStr=null;
		if (mesNivelAgregacion<10){
			mesNivelAgregacionStr = "0"+String.valueOf(mesNivelAgregacion);
		 }else{
			 mesNivelAgregacionStr=String.valueOf(mesNivelAgregacion);
		 }
		StringBuffer buffer= new StringBuffer();
		buffer.append(mesNivelAgregacionStr).append(anioNivelAgregacion);
		
		StringBuffer buffer2= new StringBuffer();
		buffer2.append(mesActual).append(anioActual);
		
		
		
		ResultadoRepositorioVO[] resultadoRepositorio = this.getSrvBuscadorService().obtenerCatalogoRepositorioParam(nivelAgregacionCatalogo);
		logger.debug("El resultado del repositorio tiene: <" + resultadoRepositorio.length+">");
		
		
		//Primero creamos una HasMap con los meses
		
		HashMap informeNivelAgregacionMensual = new HashMap();
		HashMap nivelAgregacionOri = null;
		String valor = "";
		 int mes=Integer.parseInt(buffer.substring(0,2));
		 int anio=Integer.parseInt(buffer.substring(2, 6));
		//creo ela HasMap de un tamaño igual a la diferencia de meses
//		 logger.debug("Creo la HasMap inicial");
		 for (int k=0; k<=diferenciaMeses; k++){
//			logger.debug("HasMap con los niveles creado e inicializado para el mes:" + k);
			nivelAgregacionOri = new HashMap();
			nivelAgregacionOri.put(1,0);
			nivelAgregacionOri.put(2,0);
			nivelAgregacionOri.put(3,0);
			nivelAgregacionOri.put(4,0);
			 informeNivelAgregacionMensual.put(buffer.toString(), nivelAgregacionOri);
			 mes=Integer.parseInt(buffer.substring(0,2));
			 anio=Integer.parseInt(buffer.substring(2, 6));
			 mes++;
			 if (mes>12){
				 anio++;
				 mes=1;
			 }
			 if (mes<10){
				 valor="0"+String.valueOf(mes);
			 }else{
				 valor=String.valueOf(mes);
			 }
			 buffer = new StringBuffer();
			 buffer.append(valor).append(anio);
		}
		
		
		//Luego creamos otro hasmap con los niveles de agregación posibles y lo inicializamos

		


			//Recorro el repositorio
			for (int j=0; j < resultadoRepositorio.length; j++ ){
				ResultadoRepositorioVO resultadoRepositorioVO = resultadoRepositorio[j];
				int nivelagregacion = Integer.parseInt(resultadoRepositorioVO.getNivelAgregacion());
				//Obtengo el mes de publicacion del ODE
				String mesPublicacionRepositorioStr = resultadoRepositorioVO.getFechaPublicacion().substring(4,6);
				int mesPublicacionRepositorio = new Integer (Integer.parseInt(mesPublicacionRepositorioStr));
				//Obtengo el año de publicacion del ODE
				String anioPublicacionRepositorioStr = resultadoRepositorioVO.getFechaPublicacion().substring(0,4);
				int anioPublicacionRepositorio = new Integer (Integer.parseInt(anioPublicacionRepositorioStr));
				
//			
				//obtengo el valor que hay en el HasMap de nivelAgregacion
//				if ((fechaPublicacion.after(dateNivelAgregacion) || fechaPublicacion.equals(dateNivelAgregacion))
//						|| (fechaPublicacion.before(dateAct) || fechaPublicacion.equals(dateAct))){				
//				if ((anioPublicacionRepositorio<anioActual && anioPublicacionRepositorio>anioNivelAgregacion)
//						|| (anioPublicacionRepositorio==anioActual && mesPublicacionRepositorio<=mesActual)){
					
				
				if ((anioPublicacionRepositorio>=anioNivelAgregacion && mesPublicacionRepositorio>=mesNivelAgregacion)
					|| (anioPublicacionRepositorio<anioActual && anioPublicacionRepositorio>=anioNivelAgregacion && mesPublicacionRepositorio<=mesActual)
					|| anioPublicacionRepositorio==anioActual && mesPublicacionRepositorio>=mesActual){
					
					
						if(informeNivelAgregacionMensual.get(mesPublicacionRepositorioStr+anioPublicacionRepositorioStr)!=null){
							
							 HashMap nivelAgregacionTemp = (HashMap) informeNivelAgregacionMensual.get(mesPublicacionRepositorioStr+anioPublicacionRepositorioStr);
							 int numero = (Integer) nivelAgregacionTemp.get(new Integer (Integer.parseInt(resultadoRepositorioVO.getNivelAgregacion())));
							 nivelAgregacionTemp.put(resultadoRepositorioVO.getNivelAgregacion(), numero+1);
							 informeNivelAgregacionMensual.put(mesPublicacionRepositorioStr+anioPublicacionRepositorioStr, nivelAgregacionTemp);
							 nivelAgregacionTemp = null;
						}else{
							HashMap nivelAgregacion = new HashMap();
							
							nivelAgregacion.put(1,0);
							nivelAgregacion.put(2,0);
							nivelAgregacion.put(3,0);
							nivelAgregacion.put(4,0);
							logger.debug("Nivel de agregacion es <"+nivelAgregacion.get(resultadoRepositorioVO.getNivelAgregacion())+">");
							nivelAgregacion.put(resultadoRepositorioVO.getNivelAgregacion(), 1);
							informeNivelAgregacionMensual.put(mesPublicacionRepositorioStr+anioPublicacionRepositorioStr, nivelAgregacion);
						}
						
					}
				
			}
			Set claves = informeNivelAgregacionMensual.keySet();
			Iterator clavesIte = claves.iterator();
			NivelAgregacionPublicacion[] nivelAgregacionPublicacion = new NivelAgregacionPublicacion[claves.size()];
			NivelAgregacionContenido[] nivelAgregacionContenido = new NivelAgregacionContenido[4];
			int j=0;
			int i=0;
			//Collection<NivelAgregacionVO> coleccionAgregacion=new ArrayList();
			while (clavesIte.hasNext()){
				String clave = (String) clavesIte.next();
				logger.debug("Recorremos todos los meses: <" + clave+">");
				nivelAgregacionPublicacion[j] = new NivelAgregacionPublicacion();
				//Transformo el mes que me viene en formato numero a palabra
				
				String fecha2Str=clave.substring(2,6)+clave.substring(0,2)+"25";
				
				 Date date = null;
			     SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			     try {
			            date = df.parse(fecha2Str);
			        } catch (ParseException e) {
			          logger.error("Error parseando la fecha "+fecha2Str+" - "+e);
			        }
			        

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				
				HashMap nivelAgregacion2 = (HashMap) informeNivelAgregacionMensual.get(clave);
				Collection<NivelAgregacionContenido> coleccion=new ArrayList();
				for (i = 0; i < 4; i++)
				{
//					logger.debug("Le ponemos el nivel" + i);
					int totalNivel = (Integer) nivelAgregacion2.get(i+1);
					NivelAgregacionContenido contenido=new NivelAgregacionContenido();
					contenido.setId(String.valueOf(i+1));
					contenido.setNumDoc(totalNivel);
					coleccion.add(contenido);
					
				}
				nivelAgregacionContenido =coleccion.toArray(new NivelAgregacionContenido[0]);
				nivelAgregacionPublicacion[j].setFechaPublicacion(calendar);
				nivelAgregacionPublicacion[j].setNivelAgregacionContenidoVO(nivelAgregacionContenido);
								
				j++;
				
			}
			
			if(!(nivelAgregacionPublicacion == null) && (nivelAgregacionPublicacion.length > 0))
			{
//				logger.debug("Ya está calculado el HasMap y lo metemos en el VO");
				nivelAgregacionVO = new NivelAgregacionVO();
				nivelAgregacionVO.setNodo(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST));
				logger.debug("Se mete en el VO. Host: <" + getAgregaPropertyValue(AgregaProperties.HOST)+">");
				nivelAgregacionVO.setNivelAgregacionPublicacionVO(nivelAgregacionPublicacion);
				logger.debug("Se mete en el VO. HashMap: <" + nivelAgregacionPublicacion+">");
			}else
			{
				logger.warn("Error calculando los odes por nivel de agregacion. Sin resultados");
				throw new Exception("Error calculando los odes por nivel de agregacion. No se ha obtenido ningun resultado.");
			}
			informeNivelAgregacionMensual=null;
			nivelAgregacionOri=null;
			return nivelAgregacionVO;
		
		
	}
    
	/**
	 * Obtiene el informe de cobertura curricular federado. Devuelve el número de odes existentes en cada rama del árbol curricular para un nodo concreto 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeCoberturaFederadoVO[] 
	 * @throws Exception
	 */	
	protected InformeCoberturaFederadoVO[] handleInformeCoberturaFederado(ParametrosInformeFederadoVO parametros) throws Exception
	{
		logger.debug("handleInformeCoberturaFederado begins");
		
       	InformeCoberturaFederadoVO[] resultado = null;
       	File directorioIndicesRemotos = null;
       	String[] idNodos;
       	String[] idNombresArbol;
       	String[] nodosValidos;
       	
		//Metemos la fecha desde el 1/1/2007 y la fecha hasta la actual
		parametros.setFechaDesde(new GregorianCalendar(new Integer("2007").intValue(), new Integer("1").intValue() - 1, new Integer("1").intValue(), new Integer("1").intValue(), new Integer("0").intValue()));
		parametros.setFechaHasta(new GregorianCalendar());
		parametros.setIdioma(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
		parametros.setTipoInforme("coberturaCurricularFederada");
		
		try {
			ArbolCurricularVO[] arbolCurricular = this.obtenerArbolCurricular(parametros.getIdioma());
			
			if (arbolCurricular == null) {
				logger.warn("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
				throw new Exception("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
			}
			
			nodosValidos = this.obtenerIdArbolCurricularLimitadoNivel(arbolCurricular,TERCER_NIVEL_ARBOL_CURRICULAR);
			
			StringTokenizer st = null;
			
			idNodos = new String[nodosValidos.length];
			idNombresArbol = new String[nodosValidos.length];
			
			for (int i = 0; i < nodosValidos.length; i++) {
				String nodoArbol = nodosValidos[i];
				st = new StringTokenizer(nodoArbol,"_");
				idNodos[i]=st.nextToken();
				idNombresArbol[i]=st.nextToken();
			}
			
		} catch (Exception ex) {
			logger.warn("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
			throw new Exception("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
		}
		
		try {
			directorioIndicesRemotos = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS));
		} catch (Exception ex) {
			logger.warn("no se pudo leer el directorio de indices");
			throw new Exception("No existe el directorio que deberia contener los ficheros de indices");
		}
		
    	if (directorioIndicesRemotos.list() != null && directorioIndicesRemotos.list().length > 0) {
    		
    		resultado = new InformeCoberturaFederadoVO[directorioIndicesRemotos.list().length];
    		    		
    		int i = 0;
    		    		
    		String[] aNodo = new String[1];
    		for (String directorioIndicesNodoActual : directorioIndicesRemotos.list()) {
    			if (!directorioIndicesNodoActual.startsWith("1")
    					&&!directorioIndicesNodoActual.startsWith("2")
    					&&!directorioIndicesNodoActual.startsWith("3")
    					&&!directorioIndicesNodoActual.startsWith("4")
    					&&!directorioIndicesNodoActual.startsWith("5")
    					&&!directorioIndicesNodoActual.startsWith("6")
    					&&!directorioIndicesNodoActual.startsWith("7")
    					&&!directorioIndicesNodoActual.startsWith("8")
    					&&!directorioIndicesNodoActual.startsWith("9")) {
	    			InformeCoberturaFederadoVO informeCoberturaFederadoVO = new InformeCoberturaFederadoVO();

	    			CoberturaCurricularContenido[] coberturaCurricularContenidos = new CoberturaCurricularContenido[idNodos.length];
	    			
	    			aNodo[0]=directorioIndicesNodoActual;
	    			
	    			int j = 0;
	    			for (String idNodoCobertura : idNodos) {
	    				try {
	    					CoberturaCurricularContenido coberturaCurricularContenido = new CoberturaCurricularContenido();
	    					coberturaCurricularContenido.setCoberturaCurricular(idNodoCobertura+ " " +idNombresArbol[j]);	    					
	    					ResultadosCountVO resultadoTemp = getSrvBuscadorService().solicitudDocsTaxonomiaNodo(directorioIndicesNodoActual,"acloe2006/" + idNodoCobertura, parametros.getIdioma());	    		    		
	    		    		coberturaCurricularContenido.setTotalOdes(resultadoTemp.getDocumentosCount());	    		    		
	    					coberturaCurricularContenidos[j] = coberturaCurricularContenido;
	    				} catch (Exception ex) {
	    					logger.warn("Fallo recuperando el numero de odes para cada una de las coberturas curriculares");
	    					throw new Exception("Fallo recuperando el numero de odes para cada una de las coberturas curriculares");
	    				}
	    					j++;
	    			}
	    			informeCoberturaFederadoVO.setNodo(nombreNodoFicheroConf(directorioIndicesNodoActual));
	    			informeCoberturaFederadoVO.setCoberturaCurricular(coberturaCurricularContenidos);

	    			resultado[i] = informeCoberturaFederadoVO;
	    			
	    			i++;
    			}
			}
    	} else {
			logger.warn("El directorio de indices o no existe o no tiene carpetas");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de indices");
    	}

		logger.debug("handleInformeCoberturaFederado ends");
    	return resultado;
	}

	/**
	 * Obtiene el informe de cobertura curricular local. Devuelve el número de odes existentes en cada nivel de agregación para un nodo en concreto 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeAgregacionFederadaVO. VO con los odes existentes por nivel de agregación
	 * @throws Exception
	 */
	protected InformeCoberturaFederadoVO handleCoberturaFederadoLocal(ParametrosInformeFederadoVO parametros) throws Exception
	{
		InformeCoberturaFederadoVO informeCoberturaFederadoVO = null;
		CoberturaCurricularContenido[] coberturaCurricularContenido = null;
		ParametroAuditIndexadorVO parametroAuditIndexadorVO = new ParametroAuditIndexadorVO();
		GregorianCalendar gcDesde = new GregorianCalendar();
		GregorianCalendar gcHasta = new GregorianCalendar();
		String idioma = "";
		CoberturaVO[] coberturaVO = null;
		
//		logger.info("informeCoberturaCurricular");
		
		idioma = parametros.getIdioma();
		
		//Validamos las fechas
		
		if(!(this.validarFechas(parametros.getFechaDesde(), parametros.getFechaHasta())).booleanValue())
		{
			logger.warn("Las fechas no son correctas.");
			throw new Exception("Error calculando operaciones realizadas. Las fechas no son correctas.");
		}
		
		
		gcDesde.setTime((parametros.getFechaDesde()).getTime());
		parametroAuditIndexadorVO.setFechaDesde(gcDesde);
		gcHasta.setTime((parametros.getFechaHasta()).getTime());
		parametroAuditIndexadorVO.setFechaHasta(gcHasta);
		logger.debug("Parametros del informe cobertura curricular: FechaInicio <" + parametroAuditIndexadorVO.getFechaDesde() + "> FechaFin: <" + parametroAuditIndexadorVO.getFechaHasta()+">");
		ArbolCurricularVO[] arbolCurricular = this.obtenerArbolCurricular(idioma);
		if (arbolCurricular == null)
		{
			logger.warn("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
			throw new Exception("Error obteniendo todo el arbol curricular para el idioma que se pasa por parametro");
		}else
		{
			logger.debug("El arbolCurricular obtenido es <" + arbolCurricular.length+">");
		}
		String[] idNodos = this.obtenerIdArbolCurricular(arbolCurricular);
		logger.debug("El arbol curricular tiene <" + idNodos.length+"> nodos, el primero es: <" + idNodos[0]+">");
		parametroAuditIndexadorVO.setIdNodo(idNodos);
		coberturaVO = this.getSrvAuditoriaIndexadorService().coberturaArbolCurricular(parametroAuditIndexadorVO);
		
		logger.debug("se ha obtenido el VO  coberturaVO" + coberturaVO);
		if(coberturaVO == null)
		{
			logger.warn("El VO coberturaVO es null");
			throw new Exception("Error obteniendo el informe de la cobertura por arbol curricular");
		}else
		{
			coberturaCurricularContenido = new CoberturaCurricularContenido[coberturaVO.length];
			informeCoberturaFederadoVO = new InformeCoberturaFederadoVO();
			for (int i = 0; i < coberturaVO.length; i++)
			{
				coberturaCurricularContenido[i] = new CoberturaCurricularContenido();
				coberturaCurricularContenido[i].setCoberturaCurricular(coberturaVO[i].getId()+" "+arbolCurricular[i].getNombreNodo());
				coberturaCurricularContenido[i].setTotalOdes(coberturaVO[i].getNumDoc());
				
			}
			informeCoberturaFederadoVO.setCoberturaCurricular(coberturaCurricularContenido);
			informeCoberturaFederadoVO.setNodo(getAgregaPropertyValue(AgregaProperties.HOST));
			if (informeCoberturaFederadoVO == null)
			{
				logger.warn("Error obteniendo el informe de la cobertura por arbol curricular");
				throw new Exception("Error obteniendo el informe de la cobertura por arbol curricular");
			}else
			{
				logger.debug("El VO informeCoberturaCurricularVO tiene un nodo <" + informeCoberturaFederadoVO.getNodo()+">");
				logger.debug("El VO informeCoberturaCurricularVO tiene un tamanio <" + informeCoberturaFederadoVO.getCoberturaCurricular().length+">");
			}
		}
		return informeCoberturaFederadoVO;
	}

	/**
	 * Obtiene el informe de cobertura curricular federado. Devuelve el número de odes existentes en cada rama del árbol curricular para cada nodo de la federación 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeCoberturaFederadoVO[] 
	 * @throws Exception
	 */
	protected InformeOdesIdiomaFederadoVO[] handleInformeOdesIdiomaFederado(ParametrosInformeFederadoVO parametros) throws Exception
	{
		logger.debug("handleInformeOdesIdiomaFederado begins");
    	
       	InformeOdesIdiomaFederadoVO[] resultado = null;
		File directorioIndicesRemotos = null;
		
		parametros.setFechaDesde(new GregorianCalendar(new Integer("2007").intValue(), new Integer("1").intValue() - 1, new Integer("1").intValue(), new Integer("1").intValue(), new Integer("0").intValue()));
		parametros.setFechaHasta(new GregorianCalendar()); 
		parametros.setIdioma(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
		
		try {
			directorioIndicesRemotos = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS));
		} catch (Exception ex) {
			logger.warn("no se pudo leer el directorio de indices");
			throw new Exception("No existe el directorio que deberia contener los ficheros de indices");
		}
		
    	if (directorioIndicesRemotos.list() != null && directorioIndicesRemotos.list().length > 0) {
    		resultado = new InformeOdesIdiomaFederadoVO[directorioIndicesRemotos.list().length];

    		int i = 0;
    		for (String directorioIndicesNodoActual : directorioIndicesRemotos.list()) {
    			File directorioIndicesIdiomaActual = null;
    			InformeOdesIdiomaFederadoVO informeOdesIdiomaFederadoVO = new InformeOdesIdiomaFederadoVO();
    			
    			try {
    				directorioIndicesIdiomaActual = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)
    						+ "/" + directorioIndicesNodoActual + "/index");
    			} catch (Exception ex) {
    				logger.warn("no se pudo leer el directorio de indices de idiomas");
    			}
    			
    			if (directorioIndicesIdiomaActual.list() != null && directorioIndicesIdiomaActual.list().length > 0) {
	    			
    				OdesPorIdioma[] odesPorIdiomas = new OdesPorIdioma[directorioIndicesIdiomaActual.list().length];
	    			int j = 0;
	    			for (String directorioIdiomaActual : directorioIndicesIdiomaActual.list()) {
	    				OdesPorIdioma odesPorIdioma = new OdesPorIdioma();
	    				long numeroOdes = 0;
	    				String idioma = directorioIdiomaActual.substring(7);
	    				try {
	    					for (int k=0; k<4; k++) {
	    						numeroOdes += this.getSrvBuscadorService().busquedaDocsRangoFechaNivelAgregacionIndices(String.valueOf(k), parametros.getFechaDesde(), parametros.getFechaHasta(), directorioIndicesNodoActual, idioma).getDocumentosCount();
	    					}
	    				} catch (Exception ex) {
	    					logger.warn("Error al recuperar el numero total de odes para el idioma: " + idioma + ex.getMessage());
	    					throw new Exception("Error al recuperar el numero total de odes para el idioma");
	    				}
	    				
	    				String idiomaTraducido = "";
	    				if (directorioIdiomaActual.endsWith("es")){idioma = "es"; idiomaTraducido = "Español";}
	    				else if (directorioIdiomaActual.endsWith("en")){idioma = "en"; idiomaTraducido = "Inglés";}
	    				else if (directorioIdiomaActual.endsWith("eu")){idioma = "eu"; idiomaTraducido = "Euskera";}
	    				else if (directorioIdiomaActual.endsWith("gl")){idioma = "gl"; idiomaTraducido = "Gallego";}
	    				else if (directorioIdiomaActual.endsWith("va")){idioma = "va"; idiomaTraducido = "Valenciano";}
	    				else if (directorioIdiomaActual.endsWith("ca")){idioma = "ca"; idiomaTraducido = "Catalán";}
	    				else if (directorioIdiomaActual.endsWith("fr")){idioma = "fr"; idiomaTraducido = "Francés";}
	    				
	    				odesPorIdioma.setIdioma(idioma);
	    				odesPorIdioma.setIdiomaTraducido(idiomaTraducido);
	    				odesPorIdioma.setNumeroOdes(numeroOdes);
	    				
	    				odesPorIdiomas[j] = odesPorIdioma;
	    				
	    				j++;
	    			}
	    			informeOdesIdiomaFederadoVO.setNodo(nombreNodo(directorioIndicesNodoActual));
        			informeOdesIdiomaFederadoVO.setOdesIdioma(odesPorIdiomas);
    			} else {
    				logger.warn("El directorio de indices de idiomas del nodo:" + directorioIndicesNodoActual + "o no existe o no tiene carpetas");
    				throw new Exception("El directorio de indices de idiomas del nodo:" + directorioIndicesNodoActual + "o no existe o no tiene carpetas");
    			}

    			resultado[i] = informeOdesIdiomaFederadoVO;
    			
    			i++;
			}
    	} else {
			logger.warn("El directorio de indices o no existe o no tiene carpetas");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de indices");
    	}
		
    	logger.debug("handleInformeOdesIdiomaFederado ends");
  		return resultado;
	}

	/**
     * Obtiene los idiomas de los nodos federados. Devuelve el número de odes existentes de cada idioma para cada nodo de la federación 
     * @param parametros VO con los parámetros de generación del informe
     * @return InformeOdesIdiomaFederadoVO 
     * @throws Exception
     */
	protected InformeOdesIdiomaFederadoVO handleInformeOdesIdiomaLocal(ParametrosInformeFederadoVO parametros) throws Exception
	{
		InformeOdesIdiomaFederadoVO informeOdesIdiomaFederadoVO = null;
		OdesPorIdioma[] odesPorIdioma = null;
		
		String idioma = "";
		ODEsEstadoIdiomaVO[] odesEstadoIdiomaVO = null;
		
//		logger.info("informeOdesIdioma");
		
		idioma = parametros.getIdioma();
		
		odesEstadoIdiomaVO = this.getSrvAuditaPublicacionService().odesPublicadosPorIdioma();
		
		logger.debug("se ha obtenido el VO de odesEstadoIdiomaVO" + odesEstadoIdiomaVO);
		if(odesEstadoIdiomaVO == null)
		{
			logger.warn("El VO coberturaVO es null");
			throw new Exception("Error obteniendo el informe de idioma por nodo");
		}else
		{
			odesPorIdioma = new OdesPorIdioma[odesEstadoIdiomaVO.length];
			informeOdesIdiomaFederadoVO = new InformeOdesIdiomaFederadoVO();
			for (int i = 0; i < odesEstadoIdiomaVO.length; i++)
			{
				odesPorIdioma[i] = new OdesPorIdioma();
				odesPorIdioma[i].setIdioma(odesEstadoIdiomaVO[i].getIdioma());
				odesPorIdioma[i].setNumeroOdes(odesEstadoIdiomaVO[i].getNumODES());
				I18n i18n = new I18n();
				odesPorIdioma[i].setIdiomaTraducido(i18n.obtenerIdiomaTraducido(odesEstadoIdiomaVO[i].getIdioma(), idioma));
				
			}
			informeOdesIdiomaFederadoVO.setOdesIdioma(odesPorIdioma);
			informeOdesIdiomaFederadoVO.setNodo(getAgregaPropertyValue(AgregaProperties.HOST));
			if (informeOdesIdiomaFederadoVO == null)
			{
				logger.warn("Error obteniendo el informe de idioma por nodos");
				throw new Exception("Error obteniendo el informe de idioma por nodos");
			}else
			{
				logger.debug("El VO informeOdesIdiomaFederadoVO tiene un nodo " + informeOdesIdiomaFederadoVO.getNodo());
				logger.debug("El VO informeOdesIdiomaFederadoVO tiene un tamanio " + informeOdesIdiomaFederadoVO.getOdesIdioma().length);
			}
		}
		return informeOdesIdiomaFederadoVO;
	}

	/**
	 * Obtiene los odes publicados en los nodos federados. Devuelve el número de odes publicados en cada nodo federado
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeOdesPublicadosFederadaVO[] 
	 * @throws Exception
	 */
	protected InformeOdesPublicadosFederadaVO[] handleInformeOdesPublicadosFederado(ParametrosInformeFederadoVO parametros) throws Exception
	{
		logger.debug("handleInformeOdesPublicadosFederado begins");
    	
		InformeOdesPublicadosFederadaVO[] resultado = null;
		File directorioIndicesRemotos = null;
		
		try {
			directorioIndicesRemotos = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS));
		} catch (Exception ex) {
			logger.warn("no se pudo leer el directorio de indices");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de indices");
		}

    	if (directorioIndicesRemotos.list() != null && directorioIndicesRemotos.list().length > 0) {
    		resultado = new InformeOdesPublicadosFederadaVO[directorioIndicesRemotos.list().length];

    		int i = 0;
    		for (String directorioIndicesNodoActual : directorioIndicesRemotos.list()) {

    			long numTotal = 0;
    			try {
    				numTotal = this.getSrvBuscadorService().obtenerCatalogoRepositorioParamIndices(0, directorioIndicesNodoActual, I18n.getInstance().obtenerIdiomaDefectoPlataforma());
    			} catch (Exception ex) {
    				logger.warn("Error al recuperar el numero total de odes publicados para el nodo: " + directorioIndicesNodoActual);
    				throw new Exception("Error al recuperar el numero total de odes publicados para el nodo: " + directorioIndicesNodoActual);
    			}
    			
    			InformeOdesPublicadosFederadaVO informeOdesPublicadosFederadaVO = new InformeOdesPublicadosFederadaVO(nombreNodo(directorioIndicesNodoActual), numTotal);
    			resultado[i] = informeOdesPublicadosFederadaVO;

    			i++;
    		}
    	} else {
			logger.warn("El directorio de indices o no existe o no tiene carpetas");
			throw new Exception("No existe el dicrectorio que deberia contener los ficheros de indices");
    	}
    	
    	logger.debug("handleInformeOdesPublicadosFederado ends");
  		return resultado;
	}

	/**
	 * Obtiene los odes publicados en el nodo local. 
	 * @param parametros VO con los parámetros de generación del informe
	 * @return InformeOdesPublicadosFederadaVO 
	 * @throws Exception
	 */
	protected InformeOdesPublicadosFederadaVO handleOdesPublicadosFederadoLocal(ParametrosInformeFederadoVO parametros) throws Exception
	{
		InformeOdesPublicadosFederadaVO informeOdesPublicadosFederadaVO = null;
		
		String idioma = "";
		ODEsEstadoVO odesEstadoVO = null;
		
		logger.info("informeOdesEstado");
		
		idioma = parametros.getIdioma();
		
		odesEstadoVO = this.getSrvAuditaPublicacionService().odesPublicados();
		
		logger.debug("se ha obtenido el VO de odesEstadoIdiomaVO" + odesEstadoVO);
		if(odesEstadoVO == null)
		{
			logger.error("El VO odesEstadoVO es null");
			throw new Exception("Error obteniendo el informe de estado de odes federado");
		}else
		{
			informeOdesPublicadosFederadaVO = new InformeOdesPublicadosFederadaVO();
			
			informeOdesPublicadosFederadaVO.setNumeroOdes(odesEstadoVO.getCantidad());
			informeOdesPublicadosFederadaVO.setNodo(getAgregaPropertyValue(AgregaProperties.HOST));
			if (informeOdesPublicadosFederadaVO == null)
			{
				logger.error("Error obteniendo el informe de estado de odes federado");
				throw new Exception("Error obteniendo el informe de estado de odes federado");
			}else
			{
				logger.debug("El VO informeOdesIdiomaFederadoVO tiene un nodo " + informeOdesPublicadosFederadaVO.getNodo());
				logger.debug("El VO informeOdesIdiomaFederadoVO tiene un tamanio " + informeOdesPublicadosFederadaVO.getNumeroOdes());
			}
		}
		return informeOdesPublicadosFederadaVO;
		
	}
	
	/**
	 * Obtiene la lista de nodos federados con el nodo en el que se ejecuta el informe 
	 * @return List lista de nodos federados
	 * @throws Exception
	 */
	private List obtenerComunidades() throws Exception
    {
//    	logger.debug("ObtenerComunidades");
    	List listaNodos = new ArrayList();
    	    	
    	
    	
		NodoVO[] nodoLista = this.getSrvNodoService().listarNodos();
		logger.debug("Nodos obtenidos <" + nodoLista+">");
		if(!(nodoLista == null))
		{
			logger.debug("Longitud <" + nodoLista.length+">");
			for(int i=0;i<nodoLista.length;i++)
			{
				
				logger.debug("Nodo de la lista: <" + i + "> - <"
						+ nodoLista[i].getNodo() + "> puerto: <"
						+ nodoLista[i].getPuerto() + "> urlWS: <"
						+ nodoLista[i].getUrlWS());
				listaNodos.add(nodoLista[i]);
				
			}
		}
		
//		logger.debug("Devuelvo la listaNodos");
		
		/*
		 // Para hacer pruebas en local, así se federa únicamente con localhost y desarrollo
    	NodoVO nodo = new NodoVO();
    	nodo.setNodo("desarrollo.agrega.indra.es");
    	nodo.setPuerto("8080");
    	nodo.setUrl("desarrollo.agrega.indra.es");
    	nodo.setUrlWS("desarrollo.agrega.indra.es");
    	listaNodos.add(nodo);
    	*/
    	
		return listaNodos;
	}

	/**
	 * Obtiene el valor almacenado en el fichero properties agrega de determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
     * @throws Exception 
	 */
	private String getAgregaPropertyValue(String sKey) throws IOException
	{
		AgregaProperties properties = AgregaPropertiesImpl.getInstance();
		// devolvemos la propiedad
		return properties.getProperty(sKey);
	}
	
	/**
	 * Obtiene el valor almacenado en el fichero properties auditoria de una determinada cadena 
	 * @param  sKey cadena de la que se quiere obtener el valor
	 * @return String
	 * @throws IOException
	 */
	private String getPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/auditoria.properties");
		if (this.pAuditoriaProperties == null)
		{
			pAuditoriaProperties = new java.util.Properties();
			pAuditoriaProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pAuditoriaProperties.getProperty(sKey);
	}
	
	/**
	 * Comprueba si la fechas son correctas 
	 * @param  fechaDesde 
	 * @param  fechaHasta
	 * @return Boolean
	 */
	private Boolean validarFechas(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta)
	{
		
		Boolean resultado = true;
		if (fechaDesde == null || fechaHasta == null)
		{
			logger.warn("Error calculando el informe federado. Fechas desde [" + fechaDesde == null ? null : fechaDesde + "] y hasta [" + fechaHasta == null ? null : fechaHasta + "] vacias.");
			resultado = false;
			
		}else if(fechaDesde.after(fechaHasta))
		{
			logger.warn("Error calculando el informe federado. Fechas desde [" + fechaDesde + "] y hasta [" + fechaHasta + "] incoherentes.");
			resultado = false;
			
		}
		return resultado;
	}

	/**
	 * Comprueba si los parámetros para la generación del informe federado son correctos.
	 * @param  parametros VO con los parámetros de ejecución,
	 * @param  tipoInforme nombre del informe federado que se ejecutará
	 * @return ParametrosInformeFederadoVO VO con los valores correctos de los parámetros o los valores por defecto en el caso de que no sean correctos
	 * @throws Exception
	 */
	private ParametrosInformeFederadoVO validarParametros(ParametrosInformeFederadoVO parametros,String tipoInforme) throws Exception
	{
		
		if(parametros == null)
		{
			logger.debug("no se pasan parámetros");
			parametros = new ParametrosInformeFederadoVO();
			
			//parametros.setFechaDesde(new GregorianCalendar());
			//logger.debug(new GregorianCalendar().getTime());
			//parametros.setFechaHasta(new GregorianCalendar());
			//El idioma será el del usuario que lanza la tarea
			String idioma = LdapUserDetailsUtils.getIdioma();
			if(idioma == null)
			{
				idioma = "es";
			}
			parametros.setIdioma(idioma);
			parametros.setTipoInforme(tipoInforme);
		}else
		{
			logger.debug("Se pasan parametros");
			/*
			 if(parametros.getFechaDesde() == null)
			{
				parametros.setFechaDesde(new GregorianCalendar());
			}
			if(parametros.getFechaHasta() == null)
			{
				parametros.setFechaHasta(new GregorianCalendar());
			}
			*/
			if(parametros.getIdioma() == null)
			{
				String idioma = LdapUserDetailsUtils.getIdioma();
				if(idioma == null)
				{
					idioma = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
				}
				parametros.setIdioma(idioma);
			}
			if(parametros.getTipoInforme() == null)
			{
				parametros.setTipoInforme(tipoInforme);
			}
		}
		return parametros;
	}
	
	/**
	 * Obtiene un array de ArbolCurricularVO con la información del árbol curricular: el identificador y el nombre del nodo 
	 * @param  idioma del árbol curricular
	 * @return ArbolCurricularVO[] 
	 * @throws Exception
	 */
	private es.pode.auditoria.negocio.servicios.ArbolCurricularVO[] obtenerArbolCurricular(java.lang.String idioma) throws java.lang.Exception
	{
		SrvTaxonomiaService srvTaxonomiaService = this.getSrvTaxonomiaService();
		logger.debug("Nombre taxonomia <" + this.getPropertyValue("nombreTaxonomia")+">");
		TaxonConRutaVO[] taxonConRutaVO = srvTaxonomiaService.obtenerTaxonomiaCompletaPreorden(this.getPropertyValue("nombreTaxonomia"), idioma);
		logger.debug("taxonConRutaVO vale <" + taxonConRutaVO.length+">");
		ArbolCurricularVO[] arbolCurricularVO = new ArbolCurricularVO[taxonConRutaVO.length - 1];
		logger.debug("arbolCurricularVO.length <" + arbolCurricularVO.length+">");
		if (!(taxonConRutaVO == null))
		{

			for (int i = 0; i < taxonConRutaVO.length - 1; i++)
			{
				arbolCurricularVO[i] = new ArbolCurricularVO();
				TaxonConRutaVO taxon = taxonConRutaVO[i + 1];
				arbolCurricularVO[i].setIdNodo(taxon.getId());
				arbolCurricularVO[i].setNombreNodo(taxon.getValorTax());

			}
		}
		logger.debug("devuelvo arbolCurricularVO.length <" + arbolCurricularVO.length+">");
		return arbolCurricularVO;
	}
	
	/**
	 * Obtiene un array de String  con los identificadores de los nodos del árbol curricular 
	 * @param  arbolCurricular ArbolCurricularVO[]
	 * @return String[] 
	 */
	private String[] obtenerIdArbolCurricular(ArbolCurricularVO[] arbolCurricular)
	{

		String[] idNodos = null;
		idNodos = new String[arbolCurricular.length];
		for (int i = 0; i < arbolCurricular.length; i++)
		{
			idNodos[i] = arbolCurricular[i].getIdNodo();
		}
		logger.debug("El tamanio del array de id nodos es <" + idNodos.length+">");
		return idNodos;
	}		
	
	/**
	 * Obtiene un array de String  con los identificadores de los nodos del árbol curricular limitados al nivel que se indique
	 * @param  arbolCurricular ArbolCurricularVO[]
	 * @param  nivelLimite 	int
	 * @return String[] 
	 */
	private String[] obtenerIdArbolCurricularLimitadoNivel(ArbolCurricularVO[] arbolCurricular, int nivelLimite)
	{
				
		List<String> lNodos = new ArrayList<String>();
		for (int i = 0; i < arbolCurricular.length; i++)
		{
			if (nivelMenorLimite(arbolCurricular[i].getIdNodo(),nivelLimite))
			{
				lNodos.add(arbolCurricular[i].getIdNodo()+ "_"+arbolCurricular[i].getNombreNodo());		
			}
		}
		
		String[] idNodos = new String[lNodos.size()];
		idNodos = lNodos.toArray(idNodos);
		
		logger.debug("El tamanio del array de id nodos es :  <" + idNodos.length+">");
		return idNodos;
	}	
	/**
	 * Metodo que calcula la diferencia entre dos fechas. 
	 * @param g1
	 * @param g2
	 * @return elapsed. Contiene los meses de diferencia
	 */
	private static int diferenciaMeses(Calendar g1, Calendar g2) {
        Date startDate=g1.getTime();
        Date endDate=g2.getTime();
		Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        int startYear = -1, startMonth = -1;
        int endYear = -1, endMonth = -1;
        int months = 0;
        int factor = 1;
       
        if (startDate.after(endDate)) {
            factor = -1;
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        } else {
            startCal.setTime(startDate);
            endCal.setTime(endDate);
        }
       
        startYear = startCal.get(Calendar.YEAR);
        startMonth = startCal.get(Calendar.MONTH) + 1;
        endYear = endCal.get(Calendar.YEAR);
        endMonth = endCal.get(Calendar.MONTH) + 1;
       
        if (startYear == endYear) {
            months = endMonth - startMonth;
        } else {
            months = 12 - startMonth;
            months += endMonth;
            --endYear;
            if (endYear - startYear > 0) {
                months += (endYear - startYear)*12;
            }
        }
        months *= factor;
        return months;
    }

	private String nombreNodo(String nombreDirIndices) {
		if (nombreDirIndices.contains("_"))
			return nombreDirIndices.split("_")[1];
		else
		{
			String nombreCorrecto="";
			for (int i = 0; i < nombreDirIndices.length(); i++) 
				if (!Character.isDigit(nombreDirIndices.charAt(i)))				
					nombreCorrecto+=nombreDirIndices.charAt(i);																					
			
			return nombreCorrecto;
		}
	}
	
	private String nombreNodoFicheroConf(String nombreIndiceActual) throws IOException {
		//Parseamos XML
		Unmarshaller unmarshaller = new Unmarshaller(NodoConf.class);
		unmarshaller.setValidation(false);
		
		File fichero = new File(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.INDICES_REMOTOS)+"/"+ nombreIndiceActual +"/" + getPropertyValue("nodoConf.fichero"));

		FileInputStream fis=null;
		InputStreamReader isr = null;
		NodoConf nodoConf=null;		

		try {
			fis = new FileInputStream(fichero);
			isr = new InputStreamReader(fis);
			nodoConf=(NodoConf)unmarshaller.unmarshal(isr);

		} catch (Exception e) {
			logger.warn("Error en parseo a XML de configuración de nodo: - " +e.getCause());
			logger.debug("",e);			
		} finally {
			if(fis!=null) fis.close();
			if(isr!=null) isr.close();
		}
		//Dar de alta cada nodo si no es nodo propio!
		if(nodoConf!=null && nodoConf.getCcaa()!=null && !nodoConf.getCcaa().equals("")) {
			if (logger.isDebugEnabled())
				logger.debug("Obtenemos el nombre del nodo del fichero nodoConf.xml");
			String descCCAA = nodoConf.getCcaa();
			descCCAA =descCCAA.replace("&aacute;", "á");
			descCCAA =descCCAA.replace("&eacute;", "é");
			descCCAA =descCCAA.replace("&iacute;", "í");
			descCCAA =descCCAA.replace("&oacute;", "ó");
			descCCAA =descCCAA.replace("&uacute;", "ú");

			return descCCAA;
		}
		else
		{	
			if (logger.isDebugEnabled())
				logger.error("No es posible obtener el nombre del nodo del fichero nodoConf.xml. Se obtendrá del nombre del directorio");
			return nombreNodo(nombreIndiceActual); 
		}
	}	
	private boolean nivelMenorLimite(String idArbolCurricular, int nivelLimite) {
		
		boolean valido = false;
		int nPuntos=0;
		
		int num=idArbolCurricular.indexOf(".");
					
		while (num!=-1){
			nPuntos=nPuntos+1;
			num=idArbolCurricular.indexOf(".",num+1);						
		}
		
		if (nPuntos < nivelLimite)
			valido=true;
		
		return valido;
	}
}