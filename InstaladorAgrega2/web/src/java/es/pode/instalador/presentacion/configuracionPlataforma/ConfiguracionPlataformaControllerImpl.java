// license-header java merge-point
package es.pode.instalador.presentacion.configuracionPlataforma;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.validator.ValidatorException;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.configuracionPlataforma.negocio.servicios.ResultadoOperacionVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;


/**
 * @see es.pode.administracion.presentacion.configuracionPlataforma.ConfiguracionPlataformaController
 */
public class ConfiguracionPlataformaControllerImpl extends ConfiguracionPlataformaController
{
	private static Logger logger = Logger.getLogger(ConfiguracionPlataformaControllerImpl.class);
	private SrvPropiedadService srvPropiedad = null;
	
	/* 
	 * Todas las tipologias de las propiedades. Sirven para comprobar
	 * que el usuario introduce valores coherentes.
	 */
	private String TIPO_DATO_STRING 	= "string";
	private String TIPO_DATO_INTEGER 	= "integer";
	private String TIPO_DATO_EMAIL 		= "email";
	private String TIPO_DATO_BOOLEAN 	= "boolean";

	/* Instancia a la que pertenecen las propiedades globales; aplicables en todas las instancias de Jboss */
	private String INSTANCIA_GLOBAL="global";
	
	private ResourceBundle properties = null;
	private static AgregaProperties agregaProperties = null;
		
	
	/**
	 * Inicializa un objeto único con las propiedades de configuracion cargadas en memoria.
	 */
	private void cargarPropiedades()
	{	
		properties = ResourceBundle.getBundle("application-resources");
		if (logger.isDebugEnabled())
			logger.debug("Se han cargado los properties correctamente");
	}

	
	private SrvPropiedadService obtieneSrvPropiedad() throws Exception {
		if (srvPropiedad==null) srvPropiedad = this.getSrvPropiedadService();
		return srvPropiedad;
	}
	

    /**
     * Metodo eficiente que devuelve la lista de propiedades ordenada alfabeticamente por subcategoria
     */
	private ArrayList<PropiedadVO> ordenarPropiedadesPorSubcategoria(ArrayList<PropiedadVO> propiedades) {
    	if (propiedades==null || propiedades.size()<2) return propiedades;
    	PropiedadVO tmp;

    	for(int i=0; i<propiedades.size(); i++) {
        	for(int j=0; j+1<propiedades.size(); j++) {
        		if(propiedades.get(j).getSubcategoria().compareTo(propiedades.get(j+1).getSubcategoria())>0){
        			tmp = propiedades.get(j);
        			propiedades.set(j, propiedades.get(j+1));
        			propiedades.set(j+1, tmp);
        		}
        	}
    	}
    	return propiedades;
	}

	
	/**
	 * Devuelve el nombre de la carpeta en la que esta la instancia de JBoss donde se
	 * ejecuta el codigo
	 */
	private String obtenerInstanciaJbossLocal() throws Exception {
		String homeInstancia = "";
		try {
			homeInstancia = System.getProperty("jboss.server.name");
		} catch (Exception e) {
			logger.error("Hubo un problema al recuperar el directorio HOME de la instancia de JBoss");
		}
		return homeInstancia;
	}
	
	
	/**
	 * Metodo que dada una lista de propiedades elimina las que pertenecen a otras instancias JBoss
	 */
	private ArrayList<PropiedadVO> eliminarPropiedadesDeOtrasInstancias(ArrayList<PropiedadVO> propiedades) throws Exception {
		
		String instanciaLocal=obtenerInstanciaJbossLocal();
		
    	for(int i=0; i<propiedades.size(); i++) {
    		
    		String instanciaPropiedad=propiedades.get(i).getInstanciaJboss();
    		
    		if(!instanciaPropiedad.contentEquals(instanciaLocal) && 
    				!instanciaPropiedad.contentEquals(INSTANCIA_GLOBAL)) {
        		propiedades.remove(i);
        		i--;
        	} 
    	}
    	return propiedades;
	}
	
	
	/*
	 * Devuelve una lista con las propiedades que se mostraran en cada pestaña de categoria
	 */
	private ArrayList<PropiedadVO> obtenerPropiedadesAMostrarPorCategoria(String categoria) throws Exception {
				
		//Obtenemos la lista de propiedades de la categoria a mostrar
		PropiedadVO[] propiedades = null;
		try {
			propiedades=obtieneSrvPropiedad().getPropiedadesModificablesDeTodasInstanciasPorCategoria(categoria);
		} catch (Exception e) {
			logger.error("Error al obtener las propiedades de configuracion de la plataforma",e);
			throw new ValidatorException("{error.obtencion.parametros}");			
		}

		ArrayList<PropiedadVO> props = new ArrayList<PropiedadVO>();
		for(int i=0; i<propiedades.length; i++) 
			props.add(propiedades[i]);

		props=eliminarPropiedadesDeOtrasInstancias(props);
		return props;
	}
	

	@Override
	public void cargarDatos(ActionMapping mapping, CargarDatosForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//Obtenemos las categorias
		form.setListaCategorias(obtieneSrvPropiedad().getCategoriasPropiedadesModificables());

		String categoria=form.getCategoria();
		if(categoria==null||categoria.isEmpty()) {
			categoria=form.getListaCategorias()[0];
			form.setCategoria(categoria);
		}

		ArrayList<PropiedadVO> props = new ArrayList<PropiedadVO>();
		props=obtenerPropiedadesAMostrarPorCategoria(categoria);
		props=ordenarPropiedadesPorSubcategoria(props);
		form.setListaPropiedades(props);
		form.setListaNuevosValores(props);
	}

	
	private boolean esValido (String s) {
		if(s==null)	return false;
		return true;
	}
	
	
	private boolean esUnEnteroPositivo (String num) {
		if (num.isEmpty()) return true;
		if(!esValido(num)) return false;
		int n;
		try {
			n = Integer.valueOf(num);
		} catch (Exception e) {
			return false;
		}
		if(n<0) return false;
		return true;
	}
	

	private boolean esUnPuerto (String port) {
		if(!esValido(port)) return false;
		int puerto;
		try {
			puerto = Integer.valueOf(port);
		} catch (Exception e) {
			return false;
		}
		if(puerto>19 && puerto<65537) 
			return true;
		return false;
	}
	
	
	private boolean esUnMail (String mail) {
		if(!esValido(mail))	return false;
		if(mail.contains("@")) {
			String[] email = mail.split("@");
			if(email.length!=2) return false;
			if(!email[1].contains(".")) return false;
			String[] emailDomain = email[1].split("\\.");
			if(emailDomain.length<2) return false;
			return true;
		}
		return false;
	}
	
	
	private boolean esUnBooleano (String bool) {
		if(!esValido(bool)) return false;
		if(bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false"))
			return true;
		if(bool.equalsIgnoreCase("si") || bool.equalsIgnoreCase("no"))
			return true;
		return false;
	}


	/*
	 * Metodo que se encarga de revisar que el nuevo valor introducido es coherente con el tipo de dato segun la propiedad.
	 * Devuelve false si no valida algun valor.
	 */
	private boolean validarDatos(CambiosRequierenReinicioForm form, HttpServletRequest request, ArrayList<PropiedadVO> propiedadesMostradas) throws Exception {
		
		ArrayList<PropiedadVO> props = propiedadesMostradas;
		
		if(form.getNuevosValores().length!=form.getNombresPropiedades().length) {
			logger.error("No se han recibido el mismo numero de valores que de propiedades");
			throw new Exception("No se han recibido el mismo numero de valores que de propiedades");	
		}
		if(form.getNuevosValores().length!=form.getInstanciaJboss().length) {
			logger.error("No se han recibido el mismo numero de valores que de instancias");
			throw new Exception("No se han recibido el mismo numero de valores que de instancias");	
		}		
		
		for(int i=0; i<form.getNuevosValores().length; i++) {
			
			String nuevoValor=form.getNuevosValores()[i];
			String instanciaJboss=form.getInstanciaJboss()[i];
			String nombre=form.getNombresPropiedades()[i];
			
			String[] nombrePropiedad = new String[1];
			if(instanciaJboss.contentEquals(INSTANCIA_GLOBAL))
				nombrePropiedad[0]=nombre;
			else
				nombrePropiedad[0]=nombre+" ("+instanciaJboss+")";

			for(int j=0; j<props.size(); j++) {
				if(props.get(j).getNombre().contentEquals(nombre) && props.get(j).getInstanciaJboss().contentEquals(instanciaJboss)) {
					
					if(props.get(j).getTipologia().contentEquals(TIPO_DATO_INTEGER)) {
						if(!esUnEnteroPositivo(nuevoValor)) {
							saveErrorMessage(request, "errors.natural", nombrePropiedad);
							return false;
						}
					} else if(props.get(j).getTipologia().contentEquals(TIPO_DATO_EMAIL)) {
						if(!esUnMail(nuevoValor)) {
							saveErrorMessage(request, "errors.email", nombrePropiedad);
							return false;
						}
					} else if(props.get(j).getTipologia().contentEquals(TIPO_DATO_BOOLEAN)) {
						if(!esUnBooleano(nuevoValor)) {
							saveErrorMessage(request, "errors.bool", nombrePropiedad);
							return false;
						}
					} else {
						if(!esValido(nuevoValor)) {
							saveErrorMessage(request, "errors.generico", nombrePropiedad);
							return false;
						}						
					}

					break;
				}	
			}
		}
		return true;
	}
	

	@Override
	public void actualizarDatos(ActionMapping mapping,
			ActualizarDatosForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		boolean bRequiereReinicio = false;
		
		List<PropiedadVO> lPropsModificadas = new ArrayList<PropiedadVO>();
		
	//	Hashtable<String, String> htPropsPantalla = new Hashtable<String, String>();
		// Obtenemos las propiedades modificadas en pantalla
		for(int i=0; i<form.getNombresPropiedades().length; i++) {					
		//	htPropsPantalla.put(form.getNombresPropiedades()[i] + "_" + form.getInstanciaJboss()[i],form.getNuevosValores()[i]);
			PropiedadVO propOrig = getSrvPropiedadService().getPropiedadJBoss(form.getNombresPropiedades()[i],form.getInstanciaJboss()[i]);
			propOrig.setValor(form.getNuevosValores()[i]);
			lPropsModificadas.add(propOrig);
			
			if (propOrig.isRequiereReinicioJboss())
				bRequiereReinicio=true;
		}
									
		PropiedadVO[] propMod =lPropsModificadas.toArray(new PropiedadVO[0]);
		ResultadoOperacionVO res = getSrvGestorConfiguracionService().modificarPropiedadesLocal(propMod);
			
		if (res.getCodigoResultado()==1)
			if (bRequiereReinicio)
			{
				saveSuccessMessage(request, "configuracionPlataforma.actualizacion.correcta");
				saveSuccessMessage(request, "configuracionPlataforma.actualizacionReinicio");
			}
			else
				saveSuccessMessage(request, "configuracionPlataforma.actualizacion.correcta");
		else
		{
			String[] argsError = new String[1]; 
			argsError[0] = res.getMensaje();
			saveErrorMessage(request,"configuracionPlataforma.actualizacion.erronea", argsError);
		}
	}


	@Override
	public String cambiosRequierenReinicio(ActionMapping mapping,
			CambiosRequierenReinicioForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ArrayList<PropiedadVO> props = new ArrayList<PropiedadVO>();
		props=obtenerPropiedadesAMostrarPorCategoria(form.getCategoria());
		
		if(!validarDatos(form, request, props)) return "NO_VALIDA";
		
		PropiedadVO propOriginal=null;
		
		Hashtable<String, String> htPropsPantalla = new Hashtable<String, String>();
		
		for(int i=0; i<form.getNombresPropiedades().length; i++) {					
			htPropsPantalla.put(form.getNombresPropiedades()[i] + "_" + form.getInstanciaJboss()[i],form.getNuevosValores()[i]);			
		}
		
		List<PropiedadVO> lPropsModificadas = new ArrayList<PropiedadVO>();
		
		boolean bRequiereReinicio = false;
		
		for (Iterator<PropiedadVO> iterator = props.iterator(); iterator
				.hasNext();) {
			propOriginal = (PropiedadVO) iterator.next();
			
			if (htPropsPantalla.get(propOriginal.getNombre() +"_"+propOriginal.getInstanciaJboss())!=null)
			{
				String valorProp = htPropsPantalla.get(propOriginal.getNombre() +"_"+propOriginal.getInstanciaJboss());
				
				
				if (!valorProp.equals(propOriginal.getValor()))
				{
					propOriginal.setValor(valorProp);
					lPropsModificadas.add(propOriginal);
					
					if (propOriginal.isRequiereReinicioJboss())
						bRequiereReinicio=true;
					
				}
				
			}
		}
		
		if (lPropsModificadas.size()==0)
		{
			saveErrorMessage(request, "configuracionPlataforma.actualizacion.sin.cambios");
			return "NO_VALIDA";
		}else
		{
			String[] propNombre = new String[lPropsModificadas.size()];
			String[] propValores = new String[lPropsModificadas.size()];
			String[] propInstancia = new String[lPropsModificadas.size()];
			boolean[] propReinicio = new boolean[lPropsModificadas.size()];
	
			int i=0;
			
			for (Iterator<PropiedadVO> iterator = lPropsModificadas.iterator(); iterator
					.hasNext();) {
				PropiedadVO propiedadVO = (PropiedadVO) iterator.next();
				propNombre[i]=propiedadVO.getNombre();
				propInstancia[i]=propiedadVO.getInstanciaJboss();
				propValores[i]=propiedadVO.getValor();
				propReinicio[i]=propiedadVO.isRequiereReinicioJboss();
				
				i++;
				
			}
			
			form.setInstanciaJboss(propInstancia);
			form.setNombresPropiedades(propNombre);
			form.setNuevosValores(propValores);
			form.setRequiereReinicio(propReinicio);
			
		}
		
		if (bRequiereReinicio)
			return "SI";
		else
			return "NO";
	}


	@Override
	public String analizarRespuesta(ActionMapping mapping,
			AnalizarRespuestaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String result = "Cancelar";
		Locale loc = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		String aceptar = I18n.getInstance().getResource("comun.aceptar", "application-resources", loc);
		
		if(logger.isDebugEnabled()) logger.debug("Action = " + form.getAction());
		
		if(aceptar!=null && form.getAction()!=null && aceptar.equals(form.getAction())) {
			result = "Aceptar";
		}
		return result;		
	}

}