// license-header java merge-point

package es.pode.publicacion.negocio.servicios;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import es.agrega.galeriaimg.rmi.GaleriaImgServer;
 

/**
 * @see es.pode.publicacion.negocio.servicios.SrvGaleriaImgService
 * Generated file: es/pode/publicacion/negocio/servicios/SrvGaleriaImgService
 */
public class SrvGaleriaImgServicioImpl
     implements es.pode.publicacion.negocio.servicios.SrvGaleriaImgServicio, MessageListener 
    		
	
{
   
	protected static Logger logger = Logger.getLogger(SrvGaleriaImgServicioImpl.class);

	private es.pode.publicacion.negocio.servicios.SrvPublicacionService srvPublicacionService;
      
	private GaleriaImgServer imgServer = null;
   
  // --------- Default Constructor ----------
    
    public SrvGaleriaImgServicioImpl()
    {
        super();
        logger.debug("Constructor de " + this.getClass().toString() + "; valor de imgServer = " + imgServer);
    }
    /**
	 * Este metodo trata el mensaje que le llega.
	 * @param message El mensaje a tratar
	 * @throws RuntimeException
	 */
     public  void onMessage(Message message) throws RuntimeException 
     {
    	 logger.debug("onMessage; valor de imgServer = " + imgServer);
     	 try
    	 {
    		 if (logger.isDebugEnabled())
    			 logger.debug("Begin:onMessage");
	         // Implementation
	    	 if (message instanceof ObjectMessage)
	    	 {
	    		 if (logger.isDebugEnabled())
	    			 logger.debug("Transformo el mensaje");
	    		 ObjectMessage obj = (ObjectMessage) message;
	    		 OdeVO ode = (OdeVO)obj.getObject();
	    		 if (logger.isDebugEnabled())
	    			 logger.debug("El ode es id ["+ode.getIdentificadorMEC()+"] MainFile ["+ode.getMainFile()+"]");
	    		 //Si todo ha ido bien hago la petici�n RMI pasando el Objeto
	    		 if (logger.isDebugEnabled())
	    			 logger.debug("Solicitud RMI");
	    		 this.generateImage4Id(ode);
	    		 
	    		 if (logger.isDebugEnabled())
	    			 logger.debug("Fin Solicitud RMI");
	    	 }
	    	 else
	    	 {
	    		 if (logger.isDebugEnabled())
	    			 logger.debug("No hay m�s mensajes que tratar");
	    	 } 
	    	 //session.close();
    		 if (logger.isDebugEnabled())
    			 logger.debug("End:onMessage");
    	 }
    	 catch (Exception ex)
    	 {
    		 logger.error("Se ha producido un error al leer el mensaje ["+message+"]", ex);
    		 throw new RuntimeException(ex);
    	 }
    }
      /**
       * Este metodo genera la imagen 
     * @param ode El ode al que hay que insertarle la imagen
       */
      public void generateImage4Id(OdeVO ode)
      {
      	try
      	{
      		if (logger.isDebugEnabled())
      			logger.debug("Begin:generateImage4Id");
      		if (logger.isDebugEnabled())
      			logger.debug("El ode es id ["+ode.getIdentificadorMEC()+"] MainFile ["+ode.getMainFile()+"] y el servidor es ["+ode.getServerOn()+"]");
      		//Si todo ha ido bien hago la petici�n RMI pasando el Objeto
      		if (logger.isDebugEnabled())
      			logger.debug("Solicitud RMI");
      		this.getImgServer().getImageUrlForId(ode.getIdentificadorMEC(), ode.getMainFile(), ode.getServerOn());
      		if (logger.isDebugEnabled())
      			logger.debug("End:generateImage4Id");
      	}
      	catch (Exception ex)
      	{
      		logger.error("Se ha producido un error al SOLICITAR rmi ["+ode.getIdentificadorMEC()+"]", ex);
      		throw new CreateImageException("Se ha producido un error al SOLICITAR rmi ["+ode.getIdentificadorMEC()+"]",ex);
      	}
      }

      /**
       * Este metodo hace la llamada al servicio de publicacion
     * @param srvPublicacionService Servicio de publicacion
       */
    public void setSrvPublicacionService(es.pode.publicacion.negocio.servicios.SrvPublicacionService srvPublicacionService)
    {
        this.srvPublicacionService = srvPublicacionService;
    }


    /**
     * Este metodo hace la llamada al servicio de publicacion
     */
    protected es.pode.publicacion.negocio.servicios.SrvPublicacionService getSrvPublicacionService()
    {
        return this.srvPublicacionService;
    }
    
	public GaleriaImgServer getImgServer() {
		return imgServer;
	}


	public void setImgServer(GaleriaImgServer imgServer) {
		this.imgServer = imgServer;
	}

    

}