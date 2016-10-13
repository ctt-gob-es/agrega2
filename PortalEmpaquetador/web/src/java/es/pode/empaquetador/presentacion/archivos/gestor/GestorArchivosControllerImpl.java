// license-header java merge-point
package es.pode.empaquetador.presentacion.archivos.gestor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.ArchivoVO;
import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.empaquetador.presentacion.archivos.GestorArchivosSession;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController
 */
public class GestorArchivosControllerImpl extends GestorArchivosController
{

private static final String ACEPTAR = "Aceptar";
private static final String CANCELAR = "Cancelar";
private static final String ERROR_GENERICO = "{comunes.error.generico}";
private static final String CREAR_ARCHIVO_STR = "CrearArchivo";
private static final String CREAR_CARPETA_STR = "CrearCarpeta";
private static final String CREAR_ARCHIVO = "presentacion.archivos.gestor.archivos.submit.crear.archivo";
private static final String CREAR_CARPETA = "presentacion.archivos.gestor.archivos.submit.crear.carpeta";
private static final String ELIMINAR_STR = "Eliminar";
private static final String PEGAR_STR = "Pegar";
private static final String CORTAR_STR = "Cortar";
private static final String COPIAR_STR = "Copiar";
private static final String ARCHIVOS = "archivos";
private static final String PORTAL_EMPAQUETADOR_EXCEPTION = "{portal_empaquetado.exception.session}";
private static final String VACIA = "";
private static final String NORMAL = "Normal";
private static final String PEGAR = "presentacion.archivos.gestor.archivos.submit.pegar";
private static final String APPLICATION_RESOURCES = "application-resources";
private static final String ELIMINAR = "presentacion.archivos.gestor.archivos.submit.eliminar";
private static final String CORTAR = "presentacion.archivos.gestor.archivos.submit.cortar";
private static final String COPIAR = "presentacion.archivos.gestor.archivos.submit.copiar";
private static final String BARRA = "/";
private GestorSesion gestorSesion =  new GestorSesion();
private static final Logger logger = Logger.getLogger(GestorArchivosControllerImpl.class);
	/**
	 * @return the gestorSesion
	 */
	public GestorSesion getGestorSesion() {
		return gestorSesion;
	}

	/**
	 * @param gestorSesion the gestorSesion to set
	 */
	public void setGestorSesion(GestorSesion gestorSesion) {
		this.gestorSesion = gestorSesion;
	}

    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#cargarDatos(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.CargarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDatos(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.CargarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = getGestorArchivosSession(request);
    	String ideID=this.getEmpaquetadorSession(request).getIdLocalizador();
		
    	if ((request.getParameter("asistente")==null) || !(request.getParameter("asistente").equals("si"))){
    		//inicializamos session
    		this.getEmpaquetadorSession(request).setMensajeAsistente("");
    	}
		ArchivoVO recuperado = this.getSrvGestorArchivosService().recuperarArbol(ideID);
		//relleno
		
    	if (sesArch==null || sesArch.getPath() == null){
      		List portapapeles=new ArrayList();
    		List pathList=new ArrayList();
    		pathList.add(recuperado);

    		sesArch = new GestorArchivosSession();
    		sesArch.setAccion(NORMAL);
    		sesArch.setModoPegar(false);
    		sesArch.setPath(pathList);
    		sesArch.setPortapapeles(portapapeles);
    		setGestorArchivosSession(request, sesArch);
    		
    	} else {
    		List listaPath = sesArch.getPath();
    		listaPath.set(0, recuperado);
    		for(int i=1; i<listaPath.size();i++)
    		{
    			ArchivoVO elemento = (ArchivoVO) listaPath.get(i);
    			String nombre=elemento.getNombre();
    			ArchivoVO elementoAnterior=(ArchivoVO) listaPath.get(i-1);
    			ArchivoVO[] arrayContenidos = elementoAnterior.getContenidos();
    			
    			ArchivoVO hijo=null;
    			for(int j=0; j<arrayContenidos.length && hijo == null;j++)
    			{
    				if (arrayContenidos[j].getNombre().equals(nombre))
    				{
    					hijo=arrayContenidos[j];
    				}
    			}
    			listaPath.set(i,hijo);
    		}
    	}
    	ArchivoVO[] lista =	null;
    	ArchivoVO ultimoEnPath= new ArchivoVO();
    	if(sesArch.getPath().size()!=1) 
    	{	
    		ultimoEnPath = (ArchivoVO)sesArch.getPath().get(sesArch.getPath().size()-1);
    		if(ultimoEnPath.getCarpetaPadre()==null) {
    			lista = this.getSrvGestorArchivosService().listar(ideID, ultimoEnPath.getNombre());
    		} else {
        		lista = this.getSrvGestorArchivosService().listar(ideID, ultimoEnPath.getCarpetaPadre()+BARRA+ultimoEnPath.getNombre());
        	}
    	} else /*if (sesArch.getPath().size()==1)*/
    	{
    		lista = this.getSrvGestorArchivosService().listar(ideID, null);
    	}
     	
    	//transformo el AchivoVO[] a un arrayList
    	List listado=Arrays.asList(lista);
    	
    	//inserto la lista en el fomulario
    	form.setListado(listado);
		
		//relleno el portapapeles
		form.setPortapapeles(sesArch.getPortapapeles());
 		
		//paso la lista de path
        form.setPath(sesArch.getPath());
        
        form.setArbol((ArchivoVO)sesArch.getPath().get(0));
        
        EmpaquetadorSession sesion = getEmpaquetadorSession(request);   
		logger.debug("Ruta es "+"uploads"+sesion.getOde().getLocalizadorURL()+"/imsmanifest.xml");
		boolean permiteObraDerivada=true;
		try {
			permiteObraDerivada = getSrvGruposLicencias().permiteObraDerivada("uploads"+sesion.getOde().getLocalizadorURL()+"/imsmanifest.xml", sesion.getOde().getIdentifier());
		} catch (Exception e) {
			logger.error("Se ha producido un error: "+e,e);
		}

		form.setMostrarAvisoLicencia(false);
		if (!permiteObraDerivada) {	
			logger.debug("El ODE a modificar no permite permite obra derivada.");
			if(sesion.getAvisadoLicencia()==null || !sesion.getAvisadoLicencia()) {
				sesion.setAvisadoLicencia(true);
				logger.debug("El ODE a modificar no permite permite obra derivada -> mostramos mensaje");
				form.setMostrarAvisoLicencia(true);
//				throw new ValidatorException("{comunes.licenciaNoPermiteObraDerivada}");
			}
		}
		
		logger.debug("Mensaje compatibilidad licencias es "+sesion.getMensajeCompatibilidadLicencias());
		form.setMostradoMensajeCompatibilidadLicencia(true);
		if(!sesion.isMostradoMensajeCompatibilidadLicencia()) {
			logger.debug("No se ha mostrado el mensaje de compatibilidad de licencias");
			form.setMostradoMensajeCompatibilidadLicencia(false);
			sesion.setMostradoMensajeCompatibilidadLicencia(true);
		}
     }

    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#submit(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void submit(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	//actualizaremos el mensaje del asistente
    	 this.getEmpaquetadorSession(request).setMensajeAsistente("");
    	java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,locale);
		
    	String accion = form.getAction();
    	if ((accion.equals(i18n.getString(COPIAR))) 
    			||(accion.equals(i18n.getString(CORTAR)))
    			||(accion.equals(i18n.getString(ELIMINAR))))
    	{
    		List archivosARecuperar=new ArrayList();
    		List elementos = form.getNombreRowSelection();
    		if (elementos!=null && elementos.size()>0)
    		{
    			/*
    			 * voy a recorrer la lista recuperando los nombres
    			 * para recuperar los ArchivosVO
    			 */
    			for (int i=0; i<elementos.size();i++)
    			{
    				String nombre = (String) elementos.get(i);
    				//hijos del ultimo elemento del path
    				
    				List path = sesArch.getPath();
    				
    				//obtengo el ultimo elemento
    				ArchivoVO ultimoElemento=(ArchivoVO) path.get(path.size()-1);
    				
    				ArchivoVO[] hijosUltimoElemento = ultimoElemento.getContenidos();
    				
    				boolean encontrado=false;
    				//comparo los nombres de los archivosVO y si es =, lo inserto en la lista "archivosARecuperar"
    				for(int j=0;(encontrado==false && j<hijosUltimoElemento.length);j++)
    				{
    					if (hijosUltimoElemento[j].getNombre().equals(nombre))
    					{
    						ArchivoVO voEncontrado = hijosUltimoElemento[j];
    						archivosARecuperar.add(voEncontrado);
    						encontrado=true;
    					}
    				}
    			}//fin_for (int i=0; i<elementos.size();i++)
    			
    			//inserto la lista de ArchivosVO que he recuperado
    			form.setArchivosVO(archivosARecuperar);
    			
    			Logger.getLogger(this.getClass()).info("Archivos añadidos al portapapeles");
    		} else {
    			
    			Logger.getLogger(this.getClass()).error("Lanzando excepcion de validacion.");
    			
    	    	if (accion.equals(i18n.getString(COPIAR)))
    	    		throw new ValidatorException("{presentacion.archivos.gestor.archivos.error.copiar.noseleccionado}");
    	    	else if ((accion.equals(i18n.getString(CORTAR))))
    	    		throw new ValidatorException("{presentacion.archivos.gestor.archivos.error.cortar.noseleccionado}");
      	    	else if ((accion.equals(i18n.getString(ELIMINAR))))
    	    		throw new ValidatorException("{presentacion.archivos.gestor.archivos.error.eliminar.noseleccionado}");
    		}
    		
    	}
    	
    	
    	else if(accion.equals("portal_empaquetado.pegar"))
    	{
    		if(sesArch.getPortapapeles()==null || sesArch.getPortapapeles().size()==0)
    		{
    			throw new ValidatorException("{presentacion.archivos.gestor.archivos.error.pegar}");
    		}
    	}
    	
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#selectAction(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.SelectActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String selectAction(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.SelectActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	/*
		 * Metodo de decision para el action. Analiza los parametros
		 * actionSubmit (value de los botones submit) para redirigir al caso de uso
		 * correspondiente. El actionSubmit llegara
		 * internacionalizado, por lo que es necesario acceder al ResouceBundle
		 * para obtener el valor correcto en la comparacion.
		 */
    	
		String result = null;
		String actionSubmit = form.getAction();
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES,locale);
		
		if (form.getAction()== (null))
		{
			throw new ValidatorException(ERROR_GENERICO);
		}
			
		else if (actionSubmit.equals(i18n.getString(COPIAR)) )
		{
			result = COPIAR_STR;
		} 
		else if (actionSubmit.equals(i18n.getString(CORTAR))) 
		{
			result = CORTAR_STR;
		} 
		else if (actionSubmit.equals(i18n.getString(PEGAR))) 
		{
			result = PEGAR_STR;
		} 
		else if (actionSubmit.equals(i18n.getString(ELIMINAR))) {
			result = ELIMINAR_STR;
		} 
		else if (actionSubmit.equals(i18n.getString(CREAR_CARPETA))) 
		{
			result = CREAR_CARPETA_STR;
		} 
		else if (actionSubmit.equals(i18n.getString(CREAR_ARCHIVO))) 
		{
			result = CREAR_ARCHIVO_STR;
		} 
		
         return result;
    }

    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#navegar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.NavegarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void navegar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.NavegarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorSesion gs= new GestorSesion();
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	String referencia = form.getHref();
    	List listaPath = sesArch.getPath();
    	
    	List ruta=new ArrayList();
    	//path es el primer ArchivoVO
    	ArchivoVO path = (ArchivoVO) listaPath.get(0);
    	
    	//si el hrfe está aki, se acaba, si no se hace recursividad
    	if(path.getHref().equals(referencia))
    	{
    		ruta.add(path);
    	}
    	else
    	{
    		//llama a un método recursivo para calcular la ruta
    		List tmpList = gs.rellenarBarraNavegacion(path.getContenidos(), referencia);
    		ruta.add(path);
	    	if (tmpList != null && tmpList.size() > 0) 
	    	{
	    		ruta.addAll(tmpList);
	    	}
	    }
    	sesArch.setPath(ruta);
    	if(!NORMAL.equals(sesArch.getAccion()))
    	{
    		sesArch.setModoPegar(true);
    	}
     }

    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#recuperaVO(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.RecuperaVOForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperaVO(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.RecuperaVOForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	String nombre=form.getNombre();
    	List listado = sesArch.getPath();
    	ArchivoVO ultimoElemento = (ArchivoVO) listado.get(listado.size()-1);
    	
    	for(int i=0; i<ultimoElemento.getContenidos().length;i++)
    	{
    		if(ultimoElemento.getContenidos()[i].getNombre().equals(nombre))
    		{
				 form.setArchivoVO(ultimoElemento.getContenidos()[i]);
				 String fullName=ultimoElemento.getContenidos()[i].getNombre();
				 String extension=VACIA;
				 int index=fullName.lastIndexOf('.');
				 // Las extensiones solo se comprueban si el elemento es fichero.
				 // Las carpetas se dejan como estan
				 if(logger.isDebugEnabled()) logger.debug("Analizando extension de " + ultimoElemento.getContenidos()[i] + " (esFichero ? " + ultimoElemento.getContenidos()[i].getEsFichero().booleanValue() + ")");
				 if(ultimoElemento.getContenidos()[i].getEsFichero().booleanValue()==true && index!=-1) {
					 String nombreTemp=fullName.substring(0, index);
					 ultimoElemento.getContenidos()[i].setNombre(nombreTemp);
					 extension=fullName.substring(index+1);
					 //El punto se pierde
				 }
				 request.getSession().setAttribute("archivoVO", ultimoElemento.getContenidos()[i]);
				 form.setExtension(extension);
				 break;
    		}
    	}
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#cambiarVista(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.CambiarVistaForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cambiarVista(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.CambiarVistaForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	
    	boolean vista = form.isVistaCarpeta();
    	
    	Boolean vistaCarp=Boolean.valueOf(vista);
    	
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
     	sesEmpaq.setVistaCarpeta(vistaCarp);
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#eliminarDePortapapeles(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.EliminarDePortapapelesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminarDePortapapeles(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.EliminarDePortapapelesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	
    	List portapapeles = sesArch.getPortapapeles();
    	
    	String nombreEliminar=form.getNombre();
    	
    	//voy recorriendo el portapapeles comprobando el nombre a eliminar
    	if(portapapeles!=null && portapapeles.size()>0)
    	{
	    	for(int i=0; i<portapapeles.size();i++)
	    	{
	    		ArchivoVO porta=(ArchivoVO) portapapeles.get(i);
	    		if (porta.getNombre().equals(nombreEliminar))
	    		{
	    			portapapeles.remove(i);
	    		}
	    	}
	    	
	    	if(portapapeles.size()==0)
	    	{
	    		sesArch.setAccion(NORMAL);
	    		sesArch.setModoPegar(false);
	    	}
	    	 // inserto el portapapeles sin el elemento a eliminar
	        sesArch.setPortapapeles(portapapeles);
	        //Informamos de la modificación del ode
	        this.getEmpaquetadorSession(request).setModificado(true);
    	}
    	else
    	{
    		throw new ValidatorException(PORTAL_EMPAQUETADOR_EXCEPTION);
    	}
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#vaciarPortapapeles(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.VaciarPortapapelesForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void vaciarPortapapeles(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.VaciarPortapapelesForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	List portapapeles=sesArch.getPortapapeles();
    	if(portapapeles!=null && portapapeles.size()>0)
    	{
    		portapapeles.clear();
    		sesArch.setPortapapeles(portapapeles);
    		sesArch.setAccion(NORMAL);
    		sesArch.setModoPegar(false);
    		//informamos de la accion
    		this.getEmpaquetadorSession(request).setModificado(true);
    	}
    	else
    	{
    		throw new ValidatorException(PORTAL_EMPAQUETADOR_EXCEPTION);
    	}
    }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#copiar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.CopiarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void copiar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.CopiarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	List archivosACopiar = form.getArchivosVO();
    	List portapapeles = sesArch.getPortapapeles();
    	
    	if(portapapeles!=null)
    	{
    			portapapeles.addAll(archivosACopiar);
    			sesArch.setAccion(COPIAR_STR);
    	
    	}
    	else
    	{
    		throw new ValidatorException(PORTAL_EMPAQUETADOR_EXCEPTION);
    	}
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#cortar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.CortarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cortar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.CortarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	List archivosACortar = form.getArchivosVO();
    	List portapapeles = sesArch.getPortapapeles();
    	
    	if(portapapeles!=null)
    	{
    			portapapeles.addAll(archivosACortar);
    			sesArch.setAccion(CORTAR_STR);
    	}
    	else
    	{
    		throw new ValidatorException(PORTAL_EMPAQUETADOR_EXCEPTION);
    	}
     }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#pegar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.PegarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void pegar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.PegarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
    	List<ArchivoVO> portapapeles = sesArch.getPortapapeles();
    	if(sesArch.isModoPegar()==true)
    	{
	    	if(portapapeles!=null && portapapeles.size()>0)
	    	{
    			
    			//obtengo el identificador el ultimo ODEVO
    			String identificador=sesEmpaq.getIdLocalizador();
    			List<ArchivoVO> path = sesArch.getPath();
    			ArchivoVO ultimoPath = path.get(path.size()-1);
    			//obtengo la carpetaDestino
    			String carpetaDestino = VACIA;
    			if(path.size()>1 && ultimoPath.getCarpetaPadre()==null) 
    			{
    				carpetaDestino = ultimoPath.getNombre();
    			} 
    			else if (path.size()>1&& ultimoPath.getCarpetaPadre()!=null) 
    			{
    				carpetaDestino=ultimoPath.getCarpetaPadre().concat(BARRA).concat(ultimoPath.getNombre());
    			}
    			
    			//ficheros a copiar del portapapeles
    			ArchivoVO[] ficheros = portapapeles.toArray(new ArchivoVO[]{});
    			
    			//Controlamos que las rutas origen/destino no coincidan
    			for (int i = 0; i < ficheros.length; i++) {
					ArchivoVO archivoVO = ficheros[i];
					//Realmente vale con comprobar sólo carpetas
					if(!archivoVO.getEsFichero()) {
						 String rutaCompleta=(archivoVO.getCarpetaPadre()!=null?archivoVO.getCarpetaPadre()+BARRA:VACIA)+archivoVO.getNombre();
						if(carpetaDestino.startsWith(rutaCompleta)) {
							throw new ValidatorException("{portal_empaquetado.exception.recursivo}");
						}
					}
				}
    			
    			
				if(sesArch.getAccion().equals(CORTAR_STR))
	    		{
	    			this.getSrvEmpaquetadorBasicoService().cortar(identificador, carpetaDestino, ficheros);
	    		}
	    		else if(sesArch.getAccion().equals(COPIAR_STR)) 
	    		{
	    			this.getSrvGestorArchivosService().copiar(identificador, carpetaDestino, ficheros);
	    		}
				//Si se ha llegado aqui, y no se ha producido ningun error, es que se ha realizado la acción de Pegar
				sesEmpaq.setModificado(true);
	    	}
	    	else
	    	{
	    		throw new ValidatorException(PORTAL_EMPAQUETADOR_EXCEPTION);
	    	}
	    }
	    else
	    {
	    	throw new ValidatorException("{portal_empaquetado.exception}");
	    }
    }







    /**
     * @see es.pode.empaquetador.presentacion.archivos.gestor.GestorArchivosController#eliminar(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.archivos.gestor.EliminarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void eliminar(ActionMapping mapping, es.pode.empaquetador.presentacion.archivos.gestor.EliminarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
    	boolean checked=true;
    	
    	ArchivoVO[] ficheros = (ArchivoVO[]) form.getArchivosVO().toArray(new ArchivoVO[]{});
    	
		//obtengo el identificador el ultimo ODEVO
		String identificador=sesEmpaq.getIdLocalizador();
		
    	if(ficheros!=null && ficheros.length>0)
    	{
    		this.getSrvEmpaquetadorBasicoService().eliminarFicheros(identificador, ficheros, checked);
    		//Se ha eliminado un archivo o carpeta del ode, informamos de la modificación
    		sesEmpaq.setModificado(true);
    	}
    	else
    	{
    		throw new ValidatorException("{portalempaquetado.archivos.eliminar.error.noseleccionado}");
    	}
     }


	public String advertenciaSubmit(ActionMapping mapping, AdvertenciaSubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = form.getAction();
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES);
		String result = CANCELAR;
		List archivos = (List)request.getSession().getAttribute(ARCHIVOS);
		logger.debug("Archivos recuperados tras advertencia = " + archivos);
		form.setArchivosVO(archivos);
		try {
			if(action==null) {
				logger.error("El submit pulsado es null");
			} else if(action.equals(i18n.getString("presentacion.archivos.gestor.advertencia.submit.aceptar"))) {
				result = ACEPTAR;
			} else if(action.equals(i18n.getString("presentacion.archivos.gestor.advertencia.submit.cancelar"))) {
				result = CANCELAR;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		logger.debug("Se ha pulsado " + result);
		return result;
	}


	public String comprobarReferencias(ActionMapping mapping, ComprobarReferenciasForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Llama al servicio para comprobar si hay referencias a los ficheros que se desean eliminar
		String idOde = this.getEmpaquetadorSession(request).getIdLocalizador();
		List archivos = form.getArchivosVO();
		String result = "No";
		try {
			GrupoVO[] referenciantes = new GrupoVO[0];
			logger.debug("Archivos.length = " + archivos.size() + " y referenciantes.length = " + referenciantes.length);
			for(int i=0;i<archivos.size() && referenciantes.length==0;i++) {
				referenciantes = this.getSrvEmpaquetadorBasicoService().referenciadoPor(idOde, (ArchivoVO)archivos.get(i));
				if(referenciantes!=null && referenciantes.length>0) {
					if(logger.isDebugEnabled()) logger.debug("El archivo " + ((ArchivoVO)archivos.get(i)).getHref() + " esta referenciado por " + referenciantes.length + " grupos");
					result = "Si";
					// Almaceno el objeto en la sesion para evitar perderlo en la pantalla de advertencia
					request.getSession().setAttribute(ARCHIVOS, archivos);
				}
			}
		} catch (Exception e) {
			logger.error("Error comprobando las referencias de los ficheros",e);
		}
		return result;
	}

	private boolean hayArchivo(ArchivoVO ultArch){
		boolean archivo=false;
		if ((ultArch.getContenidos()!=null) && (ultArch.getContenidos().length>0)){
			int iA=0;
			while (!archivo && (iA<ultArch.getContenidos().length)){
				if ((!ultArch.getContenidos()[iA].getEsProtegido()) && (ultArch.getContenidos()[iA].getEsFichero())){
					archivo=true;
				}
				iA++;
			}
		}
		
		return archivo;
	}
	private boolean hayCarpeta(ArchivoVO ultCarp){
		boolean carpeta=false;
		if ((ultCarp.getContenidos()!=null) && (ultCarp.getContenidos().length>0)){
			int iA=0;
			while (!carpeta && (iA<ultCarp.getContenidos().length)){
				if ((!ultCarp.getContenidos()[iA].getEsProtegido()) && (!ultCarp.getContenidos()[iA].getEsFichero())){
					carpeta=true;
				}
				iA++;
			}
		}
		
		return carpeta;
	}


	
	
	/*
	@Override
	public void consultarAsistente(ActionMapping mapping, ConsultarAsistenteForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResourceBundle i18n = ResourceBundle.getBundle(APPLICATION_RESOURCES);
		//todos los datos de comprobaciones se cogen de la session
		EmpaquetadorSession empSes= this.getEmpaquetadorSession(request);
		AsistenteEmpaquetador asBasico = new AsistenteEmpaquetador();
		String resultadoAsistente="";
		if (empSes!=null && "Avanzado".equals(empSes.getTipoEmpaquetador())){
//			Actualizamos los objetos de session
			 GestorSesion gestorSesion =  new GestorSesion();
			gestorSesion.refrescarObjetosSesion(request);
			empSes= this.getEmpaquetadorSession(request);
			GestorArchivosSession sesArch = this.getGestorArchivosSession(request);
			resultadoAsistente=asBasico.asistenteAvanzado(empSes, sesArch);
		} else {
			empSes= this.getEmpaquetadorSession(request);
			GestorArchivosSession sesArch = this.getGestorArchivosSession(request);			
			resultadoAsistente=asBasico.asistenteBasico(empSes, sesArch);			
		}
		form.setConsultaAsistente(resultadoAsistente!=null?resultadoAsistente:"");
		
	}
	*/
	


}