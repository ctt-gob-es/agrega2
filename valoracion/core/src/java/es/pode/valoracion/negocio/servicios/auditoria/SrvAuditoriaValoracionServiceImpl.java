// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.valoracion.negocio.servicios.auditoria;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import es.pode.soporte.utiles.date.DateManager;



/**
 * @see es.pode.valoracion.negocio.servicios.auditoria.SrvAuditoriaValoracionService
 */

public class SrvAuditoriaValoracionServiceImpl
    extends es.pode.valoracion.negocio.servicios.auditoria.SrvAuditoriaValoracionServiceBase
{

	private Logger logger = Logger.getLogger(this.getClass());
    /**
     * @see es.pode.valoracion.negocio.servicios.auditoria.SrvAuditoriaValoracionService#contenidosMasValorados(es.pode.valoracion.negocio.servicios.auditoria.ParametroAuditValoracionVO)
     */
    protected es.pode.valoracion.negocio.servicios.auditoria.ValoracionODEVO[] handleContenidosMasValorados(es.pode.valoracion.negocio.servicios.auditoria.ParametroAuditValoracionVO parametro)
        throws java.lang.Exception
    {
        //higiene de parametros
    	if (parametro == null)
    	{
    		logger.error("Error calculando contenidos mas valorados. No hay parametros.");
    		throw new Exception("Error calculando contenidos mas valorados. No hay parametros.");
    	}
    	Date fechaDesde = parametro.getFechaDesde().getTime();
    	if (logger.isDebugEnabled()) logger.debug("El valor de fecha desde es ["+fechaDesde+"]");
    	Date fechaHasta = parametro.getFechaHasta().getTime();
    	if (logger.isDebugEnabled()) logger.debug("El valor de fecha hasta es ["+fechaHasta+"]");
    	Integer numMaxRes = parametro.getRango();
    	if (logger.isDebugEnabled()) logger.debug("El numero maximo de resultados es ["+numMaxRes+"]");
    	
    	if (numMaxRes == null || numMaxRes.intValue() == 0)
    	{
    		logger.error("Error calculando contenidos mas valorados. El numero de resulados a devolver es nulo o 0.");
    		throw new Exception("Error calculando contenidos mas valorados. El numero de resulados a devolver es nulo o 0.");
    	}
    	if (fechaDesde == null || fechaHasta == null)
    	{
    		logger.error("Error calculando contenidos mas valorados. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    		throw new Exception("Error calculando contenidos mas valorados. Fechas desde["+fechaDesde==null?null:fechaDesde.toString()+"] y hasta["+fechaHasta==null?null:fechaHasta.toString()+"] vacias.");
    	}
    	if (fechaDesde.after(fechaHasta))
    	{
    		logger.error("Error calculando contenidos mas valorados. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");
    		throw new Exception("Error calculando contenidos mas valorados. Fechas desde["+fechaDesde.toString()+"] y hasta["+fechaHasta.toString()+"] incoherentes.");    		
    	}
//    	Recuperamos todos los identificadores de ODEs que han tenido actividad de valoracion dentro del periodo dado
    	String[] identificadores = (String[])this.getValoracionDao().listarODEsValoradosDesdeHasta(DateManager.dateToCalendar(fechaDesde), 
    			DateManager.dateToCalendar(fechaHasta)).toArray(new String[0]);  
    	if (logger.isDebugEnabled()) logger.debug("El tamanho del array de los identificadores obtenidos es ["+identificadores.length+"]");
    	if (identificadores == null || identificadores.length == 0)
    	{
    		logger.info("Calculando contenidos mas valorados. No existen ODEs valorados dentro del periodo desde["+fechaDesde.toString()+"]->hasta["+fechaHasta.toString()+"]");
    		return null;
    	}
//    	Para cada identificador rescatado tengo que ir calculando su valoracion particular dentro del periodo comprendido entre las fechas
//    	desde y hasta
    	Float valoracion = new Float(0);
    	ValoracionODEVO valoracionVO= null;
    	HashMap mapaValores = new HashMap();
    	for (int i = 0; i < identificadores.length; i++) {
			try {
				if(!identificadores[i].equals(""))//solo nos interesan los identificadores con contenido
				{
					if (logger.isDebugEnabled()) logger.debug("Obtenemos el valoracion vo del identificador ["+identificadores[i]+"]");
//					valoracion = this.getSrvValoracionService().consultaValoracionDesdeHasta(identificadores[i], fechaDesde, fechaHasta);
					valoracionVO = this.getSrvValoracionService().obtenValoracionODEDesdeHasta(identificadores[i],DateManager.dateToCalendar(fechaDesde), DateManager.dateToCalendar(fechaHasta));
					if (logger.isDebugEnabled()) logger.debug("El vo de valoracion es ["+valoracionVO+"] con valores idioma ["+valoracionVO.getIdiomaODE()+"], identificador ["+valoracionVO.getIdODE()+"] y valoracion ["+valoracionVO.getValoracion()+"]");
					
					if (valoracionVO != null && !valoracionVO.getValoracion().equals(-1.0f))  // si tiene valoracion y no es "sin valorar"
						mapaValores.put(identificadores[i], valoracionVO); // utilizamos los VO el el array
					
				}
			} catch (Exception e) {
				logger.error("Calculando contenidos mas valorados. Error obteniendo valoracion para el idODE["+identificadores[i]+"]. Continuamos el calculo.",e);
			}
		}
    	if (mapaValores.isEmpty())
    	{
    		logger.error("Error calculando contenidos mas valorados. No hay valoraciones disponibles para ["+identificadores.length+"] ODEs!! desde["+fechaDesde.toString()+"]->hasta["+fechaHasta.toString()+"]");
    		return null;
    	}
//    	Construimos el array de valores que devolveremos sin ordenar
    	ValoracionODEVO[] arrayTemp = new ValoracionODEVO[mapaValores.size()];
//    	Recogemos la lista de ids 
    	String[] ids = (String[])mapaValores.keySet().toArray(new String[0]); 
    	for (int i = 0; i < ids.length; i++) {
			arrayTemp[i] = new ValoracionODEVO(ids[i],((ValoracionODEVO)mapaValores.get(ids[i])).getValoracion(), ((ValoracionODEVO)mapaValores.get(ids[i])).getIdiomaODE());
		}
//    	Con los odes que hemos calculado tenemos que ordenarlos de mayor a menor y devolver solo la cantidad pedida.
    	arrayTemp = ordenaDescendente(arrayTemp);
    	if (arrayTemp.length <= numMaxRes.intValue()){
    		return arrayTemp;
    	}
		ValoracionODEVO[] devuelto = new ValoracionODEVO[numMaxRes.intValue()];
		for (int i = 0; i < numMaxRes.intValue(); i++) {
			devuelto[i]= arrayTemp[i];
		}
		return devuelto;
    }
    
    private ValoracionODEVO[] ordenaDescendente(ValoracionODEVO[] array)
    {
    	ValoracionODEVO tmp;
    	int i, j, pos_max;
    	int N = array.length;
    	for (i = 0; i < N-1; i++) {
//			Mayor elemento del vector
    		pos_max= i;
    		for (j = i+1;  j< N; j++) {
				if (array[j].getValoracion().floatValue()>array[pos_max].getValoracion().floatValue())
					pos_max = j;
			}
//    		coloca el maximo en la posicion i
    		tmp = array[i];
    		array[i]= array[pos_max];
    		array[pos_max] = tmp;
		}
        return array;
    }
}