// license-header java merge-point
package es.pode.empaquetador.presentacion.basico.carpeta;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.empaquetador.negocio.servicio.GrupoVO;
import es.pode.empaquetador.negocio.servicio.OrganizacionVO;
import es.pode.empaquetador.presentacion.EmpaquetadorSession;
import es.pode.empaquetador.presentacion.GestorSesion;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.uuid.PodeUUIDGenerator;



/**
 * @see es.pode.empaquetador.presentacion.basico.carpeta.CrearCarpetaBasicoController
 */
public class CrearCarpetaBasicoControllerImpl extends CrearCarpetaBasicoController
{

	private static Logger logger = Logger.getLogger(CrearCarpetaBasicoControllerImpl.class);
	private GestorSesion gs = new GestorSesion();
    /**
     * @see es.pode.empaquetador.presentacion.basico.carpeta.CrearCarpetaBasicoController#recuperarDatos(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.basico.carpeta.RecuperarDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarDatos(ActionMapping mapping, es.pode.empaquetador.presentacion.basico.carpeta.RecuperarDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	if(form.isEsModificar()) {
    		if(logger.isDebugEnabled()) logger.debug("Recuperar Datos: Modificando carpeta : " + form.getIdentifier());
    		List idCollection = this.getEmpaquetadorSession(request).getIdCollection();
    		Object obj = idCollection.get(idCollection.size() - 1);
    		GrupoVO[] grupos = null;
    		if (obj instanceof OrganizacionVO) {
				OrganizacionVO org = (OrganizacionVO) obj;
				grupos = org.getGrupos();
			} else {
				GrupoVO grupo = (GrupoVO) obj;
				grupos = grupo.getGrupos();
			}
    		if(grupos!=null) {
	    		for(int i=0;i<grupos.length && form.getTitle()==null;i++) {
	    			if(grupos[i].getIdentifier().equals(form.getIdentifier())) {
	    				form.setTitle(grupos[i].getTitle());
	    			}
	    		}
    		}
    	} else {
    		if(logger.isDebugEnabled()) logger.debug("Creando nueva carpeta");
    		form.setIdentifier(PodeUUIDGenerator.getItemUUID(String.valueOf(java.lang.Math.random())));
    	}
    	if(logger.isDebugEnabled()) logger.debug("Terminando crear carpeta basico-recuperar datos con: identifier = " + form.getIdentifier() + " : title = " + form.getTitle() + " : esModificar = " + form.isEsModificar());
     }


    /**
     * @see es.pode.empaquetador.presentacion.basico.carpeta.CrearCarpetaBasicoController#crear(org.apache.struts.action.ActionMapping, es.pode.empaquetador.presentacion.basico.carpeta.CrearForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void crear(ActionMapping mapping, es.pode.empaquetador.presentacion.basico.carpeta.CrearForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		
		java.util.Locale locale = (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
		ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
		
    	String accion = form.getAction();
    	
    	if (accion==null||accion.equals(i18n.getString("nuevo_grupo.aceptar")))
    	{
    		// Validamos el nuevo titulo introducido
    		if(form.getTitle()== null || "".equals(form.getTitle().trim())) {
    			logger.error("El titulo " + form.getTitle() + " no ha pasado la validacion");
    			throw new ValidatorException("{gestor_basico.carpeta.validacion.titulo.obligatorio}");
    		}
    		// Comprobamos carpetas repetidas
    		GrupoVO[] hijos = gs.buscarHijosIdCollection(request);
    		for(int i=0;i<hijos.length;i++) {
    			if(form.getTitle().equals(hijos[i].getTitle())) {
    				logger.error("Error de titulo repetido en crear / modificar carpeta");
    				throw new ValidatorException("{gestor_basico.carpeta.validacion.titulo.repetido}");
    			}
    		}
    		
	 	   	EmpaquetadorSession sesEmpaq = this.getEmpaquetadorSession(request);
	 	    List idCollection = sesEmpaq.getIdCollection();
			String identificador=form.getIdentifier();
			String identificadorUltimo = sesEmpaq.getIdLocalizador();
			
		   	Object ultimoIdCollection = idCollection.get(idCollection.size()-1);
		   	String identificadorPadre="";
	    	GrupoVO[] grupos=null;
			if(idCollection.size()==1)
	    	{
	  	    		
	    		OrganizacionVO organizacion = (OrganizacionVO)ultimoIdCollection;
	    		grupos=organizacion.getGrupos();
	    		identificadorPadre=organizacion.getIdentifier();
	    		
	    	}
	    	else if(idCollection.size()>1)
	    	{
	    		GrupoVO grupo = (GrupoVO)ultimoIdCollection;
	    		grupos=grupo.getGrupos();
	    		identificadorPadre=grupo.getIdentifier();
	    	}	
			
			
			boolean encontrado=false;
			GrupoVO grupoBuscar=null;
			for(int i=0;(encontrado==false&& i<grupos.length);i++)
			{
				if(grupos[i].getIdentifier().equals(identificador))
				{
					encontrado=true;
					grupoBuscar=grupos[i];
				}
			}
			if(form.isEsModificar()==true)
			{
				grupoBuscar.setTitle(form.getTitle());
				grupoBuscar.setIdentifier(identificador);
				this.getSrvGestorManifestService().modificarGrupo(identificadorUltimo, grupoBuscar);
			}
			else if(form.isEsModificar()==false)
			{
				grupoBuscar=new GrupoVO();
				grupoBuscar.setTitle(form.getTitle());
				grupoBuscar.setIdentifier(identificador);
				this.getSrvGestorManifestService().crearGrupo(identificadorUltimo, grupoBuscar, identificadorPadre);
			}	
	     //se ha creado o modificado
		this.getEmpaquetadorSession(request).setModificado(true);
    	}
    }
    


}