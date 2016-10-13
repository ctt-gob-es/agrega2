package es.pode.soporte.cuota;

import org.apache.log4j.Logger;

public class Cuota {
	
	private static Logger logger = Logger.getLogger(Cuota.class);
	
	public static long totalTamainoOdes(Long[] tamainosOdes){
		long suma=0;
		if (tamainosOdes!=null) {
			for (int i = 0; i < tamainosOdes.length; i++) {
				if(tamainosOdes[i]!=null)
					suma = suma + tamainosOdes[i];
			}
		}
		return suma;
	}
	public static long restanteTamaino(Long suma, Long cuota){
		long restante=0;
		if(cuota>=suma){
			restante=cuota-suma;
		}
		return restante;
	}
	public static int porcentajeCubierto(Long suma, Long cuota){
		int porcentaje=0;
		double cuotaDou=cuota!=null?cuota:0L;
		double sumaDou=suma!=null?suma:0L;
		double div = sumaDou/cuotaDou;
		if(div >=0){
			if(div<=1){
				porcentaje=(int) java.lang.Math.ceil(div*100);//El techo para que la aprox. sea por arriba
			}else{
				porcentaje=100;
			}
		}else{
			porcentaje=0;//Para que no haya posiblidad de ningún número negativo
		}
		if(logger.isDebugEnabled())logger.debug("El porcentaje cubierto:["+porcentaje+"]");
		return porcentaje;
	}
}
