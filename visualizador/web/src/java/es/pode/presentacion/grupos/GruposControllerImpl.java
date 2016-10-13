// license-header java merge-point
package es.pode.presentacion.grupos;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO;
import es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO;
import es.pode.adminusuarios.negocio.servicios.OdeConGruposVO;
import es.pode.adminusuarios.negocio.servicios.OdeGrupoVO;
import es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO;
import es.pode.presentacion.OdeSession;
import es.pode.presentacion.VisualizadorSession;
import es.pode.presentacion.exceptions.NoAutenticadoException;
import es.pode.presentacion.utils.GrupoAsociadoSeleccion;
import es.pode.presentacion.utils.OdeSessionUtils;



/**
 * @see es.pode.presentacion.grupos.GruposController
 */
public class GruposControllerImpl extends GruposController
{
	private static Logger logger = Logger.getLogger(GruposControllerImpl.class);

	
	
	public void cargarDatos(
			ActionMapping mapping, 
			CargarDatosForm form,
			HttpServletRequest request, 
			HttpServletResponse response)
	throws Exception 
	{
    	VisualizadorSession sesion = this.getVisualizadorSession(request); 
    	
    	String identificador=null;
		if(form.getIdentificador()==null) {
			//Esto no se puede hacer ya que da problemas al abrir dos ODEs de forma simultanea
			//identificador = sesion.getIdentificador();
	    	GruposSession gruposSesion = getGruposSession(request);
	    	identificador = gruposSesion.getIdentificador();
		} else {
			identificador = form.getIdentificador();
	    	GruposSession gruposSesion = getGruposSession(request);
	    	gruposSesion.setIdentificador(identificador);
	    	setGruposSession(request, gruposSesion);
		}    	
    	
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	if(	!sesion.isVerItinerarios())
    	{
    		logger.error("El ODE no se puede visualizar porque el usuario no esta logado.");
			request.setAttribute("codigo_error", "noautenticado"); 
    		throw new NoAutenticadoException("Usuario No Autenticado");
    	}
    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////RETORNO			////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	odeSesion.setRetorno("Itinerarios");

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		
//////////////ITINERARIO DE APRENDIZAJE
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    	
		GrupoPublicoAdminVO[] grupos;
		List<GrupoAsociadoSeleccion> gruposSeleccionados = new ArrayList<GrupoAsociadoSeleccion>();
		if(sesion.isVerItinerarios())
		{
			try{
				grupos =this.getSrvPerfilPublico().obtenerGrupoUsuario(sesion.getUsuario()); 
				String[] ids= {identificador};
				OdeConGruposVO[] odeConGrupos=  this.getSrvPerfilPublico().listadoGruposConAsociacionPorUsuarioYOde(sesion.getUsuario(), ids);
				
				if(odeConGrupos.length>0)
				{
					GrupoPublicoVO[] gruposSel =  odeConGrupos[0].getGruposAsociados() ;
					for (int i = 0; i < grupos.length; i++) {
						boolean asociado= false;
						for (int j = 0; j < gruposSel.length  && !asociado; j++) {
							if(grupos[i].getNombre().equals(gruposSel[j].getNombre()) )
							{
								asociado= true;
							}
						}
					
						gruposSeleccionados.add(new GrupoAsociadoSeleccion( grupos[i] , asociado));
					}
				}else
				{
					for (int i = 0; i < grupos.length; i++) {
						gruposSeleccionados.add(new GrupoAsociadoSeleccion( grupos[i] , false));
				}
			}
			
			}catch(Exception e){
				logger.error("error al cargar los grupos asociados seleccionados", e);
			}
		}
		form.setListadoGrupos( gruposSeleccionados);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////// DESTACADO		////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(sesion.isVerFavorito())
		{
			boolean esFavorito= false;
			try{
				esFavorito = this.getSrvPerfilPublico().existeFavoritoEnUsuario(identificador, sesion.getUsuario());
			}catch (Exception e) {
				logger.error("error al acceder al servicio PerfilPublico ", e);
			}
			sesion.setFavorito(esFavorito);
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		valoración
/////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		
		if(sesion.isVerValorar())
		{
			String usuario="";
			if(sesion.isIdentidadFederada())
			{
	    		URL urlServer= new URL(sesion.getNodoOrigen());
				usuario = sesion.getUsuarioOrigen() + "@" + urlServer.getHost();
			}else
			{
				usuario= sesion.getUsuario();
			}
			
			try{
				odeSesion.setValorado(this.getSrvValoracionService().tieneValoracion(usuario, identificador, odeSesion.getIdiomaCat()));
				odeSesion.setValoracion("" + this.getSrvValoracionService().consultarValoracion(identificador));
			}catch (Exception e) {
				logger.error("error al acceder al servicio de valoración " , e);
				odeSesion.setValoracion("0.0");
				odeSesion.setValorado(true);
			}
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//CARGAR VALORES EN EL FORMULARIO PARA MOSTRAR EL MENU
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			form.setDatosSalida(odeSesion.getOrganizaciones());
			form.setLocalizacion(odeSesion.getLocalizadorCont());
			form.setIdSeleccionado(odeSesion.getIdSeleccionado());
			form.setRutaSeleccionado(odeSesion.getRutaSeleccionado());
			form.setIdentificador(identificador);
			form.setIdiomaCat(odeSesion.getIdiomaCat());
			form.setTituloOde(odeSesion.getTituloOde());
			form.setSecuencia(odeSesion.isSecuencia());
			form.setEstaValorado(odeSesion.isValorado());
	}

    public final void asociarAGrupo(
    		ActionMapping mapping, 
    		AsociarAGrupoForm form, 
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    throws Exception
    {
		if(form.getSeleccionadoRowSelection()==null ||  form.getSeleccionadoRowSelection().isEmpty())
		{
			throw new ValidatorException("{itinerarios.error.vacios}");
		}
		
		String[] seleccionados= form.getSeleccionadoRowSelectionAsArray();
    	VisualizadorSession sesion= this.getVisualizadorSession(request);
    	GruposSession gruposSesion = getGruposSession(request);
    	String identificador = gruposSesion.getIdentificador();
    	
//    	String identificador = form.getIdentificador();
    	OdeSession odeSesion = OdeSessionUtils.getOdeSession(sesion, identificador);
    	
    	OdeGrupoVO odeGrupo= new OdeGrupoVO();
    	odeGrupo.setId_mec(identificador);
    	odeGrupo.setIdioma(odeSesion.getIdiomaCat());
    	odeGrupo.setTitulo(odeSesion.getTituloOde());
    	
    	
    	ResultadoOperacionVO[] resultados = new ResultadoOperacionVO[0];
    	try{
    		resultados=  this.getSrvPerfilPublico().asociarOdeAGrupo(odeGrupo, seleccionados);
    	}catch (Exception e) {
    		this.saveErrorMessage(request, "itinerarios.error.servicio");
		}
    	
    	for (int i = 0; i < resultados.length; i++) {
    		String[] nombre= {resultados[i].getNombre()};
    		if(resultados[i].getResultado().booleanValue())
    		{
    			this.saveSuccessMessage(request, "itinerarios.error.exito" ,nombre);
    		}else{
    			this.saveErrorMessage(request, "itinerarios.error.fallo" ,nombre);
    		}
		}
    }

	@Override
	public void borraSesion(ActionMapping mapping, BorraSesionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		removeGruposSession(request);
		
	}

	@Override
	public void borraSesionFavorito(ActionMapping mapping,
			BorraSesionFavoritoForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		form.setRetorno(form.getRetorno());
		removeGruposSession(request);
	}

	@Override
	public void borraSesionValorar(ActionMapping mapping,
			BorraSesionValorarForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		form.setRetorno(form.getRetorno());
		form.setValor(form.getValor());
		removeGruposSession(request);
		
	}

}