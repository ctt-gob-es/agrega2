package es.pode.administracion.presentacion.planificador.comun;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Utiles {
	
	private static Logger log = Logger.getLogger(Utiles.class);
	private final static String PLANIFICADOR_PROP = "/portaladministracion.properties";
	private final static String ZONA_HORARIA = "zona_horaria";
	private static TimeZone tz = null;
	
	private static final Pattern HHMM = Pattern.compile("^(\\d|[0-1]\\d|2[0-3]):(\\d|[0-5]\\d)$");
	//private static final Pattern HHMM = Pattern.compile("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$");
	private static final Pattern caracteres = Pattern.compile(".*[#%&\\+\\<>'/].*");
	
    /** 
     * Método para asignar la zona horaria a partir del fichero de properties
     * 
     * @return zona horaria
     */
    public static TimeZone asignarZonaHoraria()
    {
    	Properties props = null;
    	TimeZone zonaHoraria = TimeZone.getDefault();
    	InputStream pos = null;
    	
		try 
		{
			props = new Properties();
			pos = Utiles.class.getResourceAsStream(Utiles.PLANIFICADOR_PROP);
			props.load(pos);
			zonaHoraria.setID(props.getProperty(Utiles.ZONA_HORARIA));
		} 
		catch (IOException e) 
		{
			log.error("ERROR: fichero no encontrado: " + PLANIFICADOR_PROP, e);
			/* Se asigna la zona horaria CEST por defecto */
			zonaHoraria.setID("CEST");
		}
		catch (Exception ex) 
		{
			log.error("ERROR: asignando zona horaria ", ex);
			/* Se asigna la zona horaria CEST por defecto */
			zonaHoraria.setID("CEST");
		}
		finally 
		{
			if( pos != null ) 
			{
               try 
               {
            	   pos.close();
               } 
               catch (Exception e) 
               {
            	   log.error(e);
               }
			}     
        }
   	
		return zonaHoraria;
    }
    
    /**
     *  Validación de la fecha :: Comprobamos si la fecha pasada como parametros es correcta 
     *  
    */
    
    //estaba en los parametros , (int hora, int minutos)
    //TODO Debería hacerse con regex
	public static boolean validacionFechaDDMMAAAAHHMM(String dia, String mes, String anio, String mascara) 
	{
        String mesStr = null;
        String diaStr = null;
        
        try 
        {
        	SimpleDateFormat sdf = new SimpleDateFormat(mascara);
            sdf.setLenient(false);
            
            if (mes.length() == 1)
            	mesStr = "0" + mes;
            else 
            	mesStr = mes;
            
            if (dia.length() == 1)
            	diaStr = "0" + dia;
            else 
            	diaStr = dia;
                  
            
            /*Date fecha = */sdf.parse(anio + mesStr + diaStr);
            
            
            return true;
        }
        catch(Exception e)
        {  	
        	return false;
        }
	}
	
	public static boolean validacionHoraHHMM(String horas, String minutos) 
	{
      return HHMM.matcher(horas+":"+minutos).matches();
	}
	
	public static boolean comparacionFechas(String anioDesde, String mesDesde, String diaDesde, String anioHasta, String mesHasta, String diaHasta)
	{
		if (tz == null) 
    		tz = asignarZonaHoraria();
		
		Calendar fechaDesde = Calendar.getInstance(tz);
		Calendar fechaHasta = null;
		
		fechaDesde = new GregorianCalendar(Integer.parseInt(anioDesde), Integer.parseInt(mesDesde), Integer.parseInt(diaDesde));
		
		fechaHasta = new GregorianCalendar(new Integer(anioHasta).intValue(), new Integer(mesHasta).intValue(), new Integer(diaHasta).intValue());
		
        log.debug("fecha Desde : " + anioDesde+","+mesDesde+","+diaDesde);
        log.debug("fecha Hasta : " + anioHasta+","+mesHasta+","+diaHasta);
        
        return(fechaDesde.before(fechaHasta) || fechaDesde.equals(fechaHasta));
	}
    
    /**
     * Comprobamos si un nombre recibido contiene algunos caracteres concretos
     * devolvemos false si contiene alguno de los caracteres
     * devolvemos true si no contiene ningun caracter de los que comprobamos
     */
    public static boolean validacionCaracter(String nombre){
    	return !caracteres.matcher(nombre).matches();	
    }
    
}
