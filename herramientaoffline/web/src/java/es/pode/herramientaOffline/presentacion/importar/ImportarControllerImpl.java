// license-header java merge-point
package es.pode.herramientaOffline.presentacion.importar;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.pode.herramientaOffline.negocio.soporte.DescompriveYvalidaVO;
import es.pode.soporte.constantes.ConstantesAgrega;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @see es.pode.herramientaOffline.presentacion.importar.ImportarController
 */
public class ImportarControllerImpl extends ImportarController
{

	private static Logger logger = Logger.getLogger(ImportarControllerImpl.class);


    /**
     * @see es.pode.herramientaOffline.presentacion.importar.ImportarController#submit(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.importar.SubmitForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final java.lang.String submit(ActionMapping mapping, es.pode.herramientaOffline.presentacion.importar.SubmitForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        String action = form.getAction();
        String result = "Cancelar";
        if(logger.isDebugEnabled())
        	logger.debug("Action : " 
        			+ action 
        			+ " con ficheros : {"
        			+form.getFichero1()+","
        			+form.getFichero2()+","
        			+form.getFichero3()+","
        			+form.getFichero4()+","
        			+form.getFichero5()+"}");
        if(i18n.getString("comun.Aceptar").equals(action)) {
        	if((form.getFichero1()==null || form.getFichero1().getFileSize()==0) 
        			&& (form.getFichero2()==null || form.getFichero2().getFileSize()==0) 
        			&& (form.getFichero3()==null || form.getFichero3().getFileSize()==0) 
        			&& (form.getFichero4()==null || form.getFichero4().getFileSize()==0) 
        			&& (form.getFichero5()==null || form.getFichero5().getFileSize()==0) ) {
        		throw new ValidatorException("{importar.error.seleccione}");
        	} else result = "Aceptar";
        }
        return result;
    }

    /**
     * @see es.pode.herramientaOffline.presentacion.importar.ImportarController#importar(org.apache.struts.action.ActionMapping, es.pode.herramientaOffline.presentacion.importar.ImportarForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void importar(ActionMapping mapping, es.pode.herramientaOffline.presentacion.importar.ImportarForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	java.util.Locale locale = (java.util.Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE);
        ResourceBundle i18n = ResourceBundle.getBundle("application-resources",locale);
        FormFile[] ficheros = new FormFile[5];
        ficheros[0] = form.getFichero1();
        ficheros[1] = form.getFichero2();
        ficheros[2] = form.getFichero3();
        ficheros[3] = form.getFichero4();
        ficheros[4] = form.getFichero5();
        logger.debug("importando ficheros : {"
    			+form.getFichero1()+","
    			+form.getFichero2()+","
    			+form.getFichero3()+","
    			+form.getFichero4()+","
    			+form.getFichero5()+"}");
        ArrayList<ResultadoImportacionBean> resultados = new ArrayList<ResultadoImportacionBean>();
        DataHandler dh = null;
        ResultadoImportacionBean resultadoBean =null;
        for(int i=0;i<ficheros.length;i++) {
        	if(ficheros[i]!=null && ficheros[i].getFileSize()>0) {
        		try {
        			resultadoBean = new ResultadoImportacionBean();
	        		logger.info("Importando fichero " + i + " con titulo " + ficheros[i].getFileName());
	        		dh = generarDatahandler(ficheros[i]);
	        		DescompriveYvalidaVO resultado = getDescomprimeYvalida().importarOde(dh, ficheros[i].getFileName());
	        		resultadoBean.setIdOde(resultado.getIdOde());
	        		resultadoBean.setTitulo(ficheros[i].getFileName());
//	        		resultadoBean.setValido(resultado.getMensajes().length==0?true:false);
	        		resultadoBean.setValido(resultado.getValido());
	        		if(resultado.getMensajes().length>0) resultadoBean.setMensajes(resultado.getMensajes());
	        		resultados.add(resultadoBean);
        		} catch (Exception e) {
					logger.error("Error importando fichero " + ficheros[i].getFileName(),e);
					resultadoBean.setTitulo(ficheros[i].getFileName());
					resultadoBean.setMensajes(new String[]{i18n.getString("importar.error.inesperado")});
					resultadoBean.setValido(false);
					resultados.add(resultadoBean);
				}
        	}
        }
        logger.debug("Numero de beans de resultado : " + resultados.size());
        form.setResultado(resultados);
    }

    private DataHandler generarDatahandler(FormFile file) throws Exception{
    	InternetHeaders ih = new InternetHeaders();
		MimeBodyPart mbp = new MimeBodyPart(ih, file.getFileData());

		DataSource dsource = new MimePartDataSource(mbp);
		return new DataHandler(dsource);
    }


}