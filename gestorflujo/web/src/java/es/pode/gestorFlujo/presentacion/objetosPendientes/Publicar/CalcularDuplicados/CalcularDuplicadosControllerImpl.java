// license-header java merge-point
package es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.publicacion.negocio.servicios.OdeSimilarVO;
import es.pode.soporte.url.ImagenODE;
import es.pode.soporte.url.ODEPublico;



/**
 * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosController
 */
public class CalcularDuplicadosControllerImpl extends CalcularDuplicadosController
{
	public final static String OBJETOS_PENDIENTES = "objetosPendientes";
	public final static String OBJETOS_DESPUBLICADOS = "objetosDespublicados";
	private Logger logger = Logger.getLogger(CalcularDuplicadosControllerImpl.class);




    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosController#calcularDuplicados(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void calcularDuplicados(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	String titulo = form.getTitulo();
    	String idOde = form.getIdODE();
    	String idUsuario = form.getIdUsuario();
    	Boolean esDespublicado = form.getEsDespublicado();
    	if (logger.isDebugEnabled())logger.debug("CalculandoDuplicados con titulo["+titulo+"]idODE["+idOde+"]esDespublicado["+esDespublicado+"]idUsuario["+idUsuario+"], el retorno es: ["+form.getRetorno()+"]");
//    	Limpieza de datos. Si no hay id ODE, no hay forma de calcular los duplicados.
    	if (idOde == null || idOde.equals(""))
    	{
    		logger.error("Error al calcular los duplicados. El identificador del ODE que se quiere publicar es vacio.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
//    	Si no hay titulo, me enfado porque no hay parametro para luego pasarlo.
    	/*
    	if (titulo == null)
    	{
    		logger.error("Error al calcular los duplicados. El titulo del ODE["+idOde+"] es nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
    	*/
//    	Sin el usuario que quiere publicar, no hago nada
    	if (idUsuario == null || idUsuario.equals(""))
    	{
    		logger.error("Error al calcular los duplicados. El usuario que quiere publicar del ODE["+idOde+"] y titulo["+titulo+"] es nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
//    	Sin el valor de es despublicado no hago nada
    	if (esDespublicado == null)
    	{
    		logger.error("Error al calcular los duplicados. No sabemso si el ODE["+idOde+"] con titulo["+titulo+"] con el usuario["+idUsuario+"] es despublicado. Valor a nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
    	OdeSimilarVO[] similares = null;
    	try {
//			Llamamos al servicio de publicacion para que nos diga si hay duplicados para el ode que nos pasan
			similares = this.getSrvPublicacionService().calcularDuplicadosPublicados(idOde);
//			Tenga o no similares, los meto dentro de la sesion para que se analice a donde hay que ir.
			DuplicadosSession sesionDuplicados = this.getDuplicadosSession(request); //cojo el objeto de sesion
			if (sesionDuplicados == null) sesionDuplicados = new DuplicadosSession(); //si es vacio, lo instancio, si no lo cojo.
//			Mapeo los similares a los objetos de valor que se van a pintar en la lista de similares
			sesionDuplicados.setDuplicados(Arrays.asList(mapeaSimilaresPublicacion(similares))); //actualizo la lista de similares con los que tenga.
			if (logger.isDebugEnabled()) logger.debug("El ODE con idOde["+idUsuario+"] titulo["+titulo+"] idUsuario["+idUsuario+"] esDespublicado["+esDespublicado+"] tiene ["+sesionDuplicados.getDuplicados().size()+"]duplicados en sesion");
		} catch (RuntimeException e) {
			logger.error("Error al calcular los duplicados con idOde["+idUsuario+"] titulo["+titulo+"] idUsuario["+idUsuario+"] esDespublicado["+esDespublicado+"].",e);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
     }

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosController#cargarDuplicados(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CargarDuplicadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void cargarDuplicados(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CargarDuplicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
//		Este metodo tiene que listar los ODEs que estan en sesion de duplicados en la JSP
    	logger.debug("El origen es: ["+ form.getRetorno()+"]");
    	String titulo = form.getTitulo();
    	String idOde = form.getIdODE();
    	String idUsuario = form.getIdUsuario();
    	Boolean esDespublicado = form.getEsDespublicado();
    	if (logger.isDebugEnabled())logger.debug("CargandoDuplicados con titulo["+titulo+"]idODE["+idOde+"]esDespublicado["+esDespublicado+"]idUsuario["+idUsuario+"]");
//    	Limpieza de datos. Si no hay id ODE, no puedo saltar al siguiente paso si se acepta.
    	if (idOde == null || idOde.equals(""))
    	{
    		logger.error("Error al cargar pantalla de listar los duplicados. El identificador del ODE que se quiere publicar es vacio.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
//    	Si no hay titulo, me enfado porque no hay parametro para luego pasarlo.
    	/*
    	if (titulo == null)
    	{
    		logger.error("Error al cargar pantalla de listar los duplicados. El titulo del ODE["+idOde+"] es nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
    	*/
//    	Sin el usuario que quiere publicar, no hago nada
    	if (idUsuario == null || idUsuario.equals(""))
    	{
    		logger.error("Error al cargar pantalla de listar los duplicados. El usuario que quiere publicar del ODE["+idOde+"] y titulo["+titulo+"] es nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
//    	Sin el valor de es despublicado no hago nada
    	if (esDespublicado == null)
    	{
    		logger.error("Error al cargar pantalla de listar los duplicados. No sabemso si el ODE["+idOde+"] con titulo["+titulo+"] con el usuario["+idUsuario+"] es despublicado. Valor a nulo.");
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}

    	DuplicadosSession sesionDuplicados = this.getDuplicadosSession(request);
    	if (sesionDuplicados == null || sesionDuplicados.getDuplicados() == null || sesionDuplicados.getDuplicados().isEmpty()) {
    		logger.error("Error al cargar pantalla de listar los duplicados. El ODE["+idOde+"] con titulo["+titulo+"] con el usuario["+idUsuario+"] esDespublicado["+esDespublicado+"] no tiene lista de similares en sesion.");
    		throw new ValidatorException("{gestorFlujo.error.inesperado}");
    	}
    	form.setListaDuplicados(sesionDuplicados.getDuplicados());
    	logger.info("Cargando lista de duplicados para ode con id["+idOde+"] con titulo["+titulo+"] con el usuario["+idUsuario+"] esDespublicado["+esDespublicado+"] y ["+sesionDuplicados.getDuplicados().size()+"]duplicados");
    }

    /**
     * @see es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.CalcularDuplicadosController#existenDuplicados(org.apache.struts.action.ActionMapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.ExistenDuplicadosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.Boolean existenDuplicados(ActionMapping mapping, es.pode.gestorFlujo.presentacion.objetosPendientes.Publicar.CalcularDuplicados.ExistenDuplicadosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	try {
//    		Comprobamos si existen similares, tan sencillo como mirar en la sesion y ver si la coleccion no es vacía.
    		DuplicadosSession sesionDuplicados = this.getDuplicadosSession(request); //cojo el objeto de sesion
    		Boolean resultado = null;
    		resultado = (sesionDuplicados!=null?
    				(sesionDuplicados.getDuplicados()!=null?!sesionDuplicados.getDuplicados().isEmpty():false):
    					false);
    		if (logger.isDebugEnabled()) logger.debug("Existen duplicados["+resultado+"]");
    		return resultado;
		} catch (RuntimeException e) {
			logger.error("Error al determinar si existen duplicados.",e);
			throw new ValidatorException("{gestorFlujo.error.inesperado}");
		}
    }
    
    /*
     * Este metodo mapea los ODEs similares que se devuelven del servicio de publicacion a los ODEs similares que se pintan en la lista de similares, que son iguales
     * pero incluyen la url de previsualizacion y la url de la imagen.
     * */
    private OdeDuplicado[] mapeaSimilaresPublicacion(OdeSimilarVO[] similares)
    {
    	if (similares == null || similares.length == 0)
    		return new OdeDuplicado[0];
    	ArrayList<OdeDuplicado> duplicados = new ArrayList<OdeDuplicado>();
    	for (int i = 0; i < similares.length; i++) {

			duplicados.add(new OdeDuplicado(similares[i].getIdODE(),  	// el id del ODE
    				similares[i].getSimilitud(),						// el tipo de similitud
    				similares[i].getIdioma(),							// el idioma de publicacion
    				ImagenODE.urlImagenODE(similares[i].getIdODE()),	// la url de la imagen del ODE
    				ODEPublico.urlPrevisualizaODEPublicado(similares[i].getIdODE(), similares[i].getIdioma()), // la url de previsualizacion
    				similares[i].getTitulo())); // el titulo
		}
    	return duplicados.toArray(new OdeDuplicado[0]);
    }

    
	public String comprobarOrigen(ActionMapping mapping, ComprobarOrigenForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retorno="";
		logger.debug("El origen es ["+form.getRetorno()+"]");
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_DESPUBLICADOS)){
			logger.debug("Si el origen es ["+form.getRetorno()+"] == objetosDespublicados");
			retorno = "objetosDespublicados";
		}
		if(form.getRetorno()!= null && form.getRetorno().equals(this.OBJETOS_PENDIENTES)){
			retorno = "objetosPendientes";
		}
		logger.debug("Calculamos el duplicado, y volvemos a: ["+ retorno + "]");
		return retorno;
    }
}