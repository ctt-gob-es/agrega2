// license-header java merge-point
package es.pode.buscador.presentacion.basico.listarComentarios;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.valoracion.negocio.servicios.ComentarioVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.buscador.presentacion.basico.listarComentarios.ListarComentariosController
 */
public class ListarComentariosControllerImpl extends ListarComentariosController
{

	private static final long serialVersionUID = 6786491288970950374L;

	private static Logger logger = Logger.getLogger(ListarComentariosControllerImpl.class);	
	
	private final static String ERROR_OBTENIENDO_COMENTARIOS = "introducir.comentarios.ode.errorObteniendoComentarios";


    /**
     * @see es.pode.buscador.presentacion.basico.listarComentarios.ListarComentariosController#recuperarComentarios(org.apache.struts.action.ActionMapping, es.pode.buscador.presentacion.basico.listarComentarios.RecuperarComentariosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void recuperarComentarios(ActionMapping mapping, es.pode.buscador.presentacion.basico.listarComentarios.RecuperarComentariosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {       
    	log("ListarComentariosControllerImpl - recuperarComentarios.");
        String idODE = form.getIdODE();       
        Comentario_VO_date[] comentariosDate= new Comentario_VO_date[0];
        ComentarioVO[] comentarios = null;
        try {
        	
//    		Tenemos que recuperar todos los comentarios que haya asociados a este ODE.
//        	Llamamos al servicio de valoracion para esta tarea.    
        	comentarios= this.getSrvValoracionService().obtenerComentarios(idODE);
        	
        	//Se comprueba el numero de comentarios        	
        	if(comentarios != null && comentarios.length >0){
        		comentariosDate = new Comentario_VO_date[comentarios.length];
        	}
        	for (int i = 0; comentarios != null && i < comentarios.length ; i++) {
        		comentariosDate[i]= map_comentario(comentarios[i]);
			}
		} catch (Exception e) {
			logger.error("Error obteniendo comentarios del ODE con id["+idODE+"].["+ e.getCause()+"]");
			saveErrorMessage(request, ERROR_OBTENIENDO_COMENTARIOS);
		}
		
		form.setComentarios(java.util.Arrays.asList(comentariosDate));   		
       
     }

    private Comentario_VO_date map_comentario (ComentarioVO tr)
    {
    	Comentario_VO_date tr_date = new Comentario_VO_date();    	
    	tr_date.setComentario(tr.getComentario());
    	tr_date.setUsuario(tr.getUsuario());
    	tr_date.setFecha(tr.getFecha().getTime());
    	tr_date.setIdODE(tr.getIdODE());    	 	
    	tr_date.setId(tr.getId());
    	return tr_date;
    	
    }
    
    private void log(String traza){
		if (logger.isDebugEnabled())logger.debug(traza);
	}

    public class Comentario_VO_date {
    	
  	  /* Identificador del ODE al que hace referencia la valoracion. */
      private java.lang.String idODE;
      
      /* Usuario que ha creado el comentario*/
      private java.lang.String usuario;

      /* El comentario de la valoracion. */
      private java.lang.String comentario;

      /* La fecha de la valoracion. */
      private java.util.Date fecha;      

      /* Id del comentario*/
      private java.lang.Long id;
      
		public java.lang.String getComentario() {
			return comentario;
		}

		public void setComentario(java.lang.String comentario) {
			this.comentario = comentario;
		}

		public java.util.Date getFecha() {
			return fecha;
		}

		public void setFecha(java.util.Date fecha) {
			this.fecha = fecha;
		}

		public java.lang.String getIdODE() {
			return idODE;
		}

		public void setIdODE(java.lang.String idODE) {
			this.idODE = idODE;
		}				

		public java.lang.Long getId() {
			return id;
		}

		public void setId(java.lang.Long id) {
			this.id = id;
		}
		
		public java.lang.String getUsuario() 
		{
			return usuario;
		}
		public void setUsuario(java.lang.String usuario)
		{
			this.usuario = usuario;
		}
  }

}