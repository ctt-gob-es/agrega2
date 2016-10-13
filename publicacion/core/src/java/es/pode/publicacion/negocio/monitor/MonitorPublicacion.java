/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.publicacion.negocio.monitor;

import es.pode.publicacion.negocio.soporte.I18nModuloPublicacion;

public class MonitorPublicacion {

	

	public final static String PERMISO_CONCEDIDO = "11.0";
	public final static String PERMISO_NO_CONCEDIDO = "11.1";
	public final static String TAREA_EN_EJECUCION_INCOMPATIBLE = "11.2";
	
	/**
	 * @author crmunoz
	 * Esta clase hace las veces de monitor de las operaciones de publicación que se llevan a cabo en el
	 * servicio de publicación.
	 * Contiene la lógica de las operaciones masivas y unitarias que son compatibles entre si y las que
	 * no lo son.
	 * El servicio de publicación supedita la realización de las operaciones de publicación, reindexación,
	 * regeneracion del índice, borrados, regeneracion de imágenes de forma masiva y unitaria a la
	 * posibilidad de que ya se este realizando alguna de dichas operaciones en el momento de invocarse.
	 * Esa lógica está encerrada en esta clase que se tiene que utilizar por parte del servicio de publicacion para
	 * "pedir permiso" a la hora de realizar alguna de las operaciones monitorizadas y "devolver el permiso" al terminar
	 * de realizar la operación.
	 * Cuando se concede el permiso para realizar una de las operaciones que se monitorizan, es obligación del que pide el
	 * permiso devolverlo una vez terminada la misma ya que de no hacerse, se puede dejar alterada la memoria de las tareas
	 * que se están ejecutando e inhabilitarse alguna de las funcionalidades.
	 */
	private Boolean permitidoModificar = true;
	
	private final int TAREA_PUBLICAR_MASIVO		= 0;
	private final int TAREA_REINDEXAR_MASIVO	= 1;
	private final int TAREA_REGENERAR_INDICE 	= 2;
	private final int TAREA_BORRAR_MASIVO 		= 3;
	private final int TAREA_PUBLICAR_UNITARIO 	= 4;
	private final int TAREA_REINDEXAR_UNITARIO 	= 5;
	private final int TAREA_BORRAR_UNITARIO 	= 6;
	private final int TAREA_REGENERAR_IMAGENES 	= 7;


//	 para cada tarea vamos a guardar el numero de tareas que se estan ejecutando en este momento
	private int[] tareasEnEjecucion = new int[]{0, // Tareas de publicacion masiva
												0, // Tareas de reindexar masivo
												0, // Tareas de regenerar indice
												0, // Tareas de borrar masivo
												0, // Tareas de publicar unitario
												0, // Tareas de reindexar unitario
												0, // Tareas de borrar unitario
												0}; // Tareas de regenerar imágenes
	
//	aqui guardamos la matriz de compatibilidades de cada tarea con la que se este ejecutando en cada momento
//	para cada tarea en ejecución (primer componente del array) se guarda si es compatible con la ejecución de otra tarea (segunda componente del array)
	private Boolean[][] tareasCompatibles = new Boolean[][]{
							//PUBLICAR_MASIVO, REINDEXAR_MASIVO, REGENERAR_INDICE, BORRAR_MASIVO, PUBLICAR_UNITARIO, REINDEXAR_UNITARIO, BORRAR_UNITARIO, REGENERAR_IMAGENES
	/*PUBLICAR MASIVO en exe*/	{	true, 			false, 			false, 				false, 			true, 				true, 			true, 				false}, 
	/*REINDEXAR_MASIVO en exe*/	{	false, 			true, 			false, 				false, 			true, 				false, 			false, 				true},
	/*REGENERAR_INDICE en exe*/	{	false, 			false, 			false, 				false, 			false, 				false, 			false, 				false},
	/*BORRAR_MASIVO en exe*/	{	false, 			false, 			false, 				false, 			true, 				false, 			false, 				false},
	/*PUBLICAR_INITARIO en exe*/{	true,			true,			false,				true,			true,				true,			true,				true},
	/*REINDEXAR_UNITARIO en exe*/{	true,			false,			false,				false,			true,				true,			true,				true},
	/*BORRAR_UNITARIO en exe*/	{	true,			false,			false,				false,			true,				true,			true,				false},
	/*REGENERAR_IMAGENES en exe*/{	false,			true,			false,				false,			true,				true,			false,				false}};
	
//	***************METODOS PRIVADOS QUE GESTIONAN LAS REGIONES CRITICAS
	
	/*
	 * Metodo que genera un VO con la causa de la denegacion del permiso a partir de los datos de entrada.
	 * La causa de denegación se devuelve en código y con el mensaje internacionalizado.
	 * El monitor deniega la tarea que se pretende ejecutar e indica el porque.
	 * */
	private PermisoTareaVO denegar(int tareaAEjecutar)
	{
//		Hay que devolver el error internacionalizado
		return new PermisoTareaVO(PERMISO_NO_CONCEDIDO,I18nModuloPublicacion.getPropertyValueI18n(TAREA_EN_EJECUCION_INCOMPATIBLE));
	}
	
	/*
	 * Metodo que genera un VO con la causa de la permisión a partir de los datos de entrada.
	 * La causa de permisión se devuelve en código y con el mensaje internacionalizado.
	 * El monitor permite la operación que se pretende ejecutar.
	 * */
	private PermisoTareaVO permitir(int tareaAEjecutar)
	{
		return new PermisoTareaVO(PERMISO_CONCEDIDO,"");
	}
	
	/*
	 * Metodo que chequea, deacuerdo al estado de ejecución de tareas que hay en en este momento, si la tarea que se quiere ejecutar
	 * es compatible con el resto de tareas que se estan ejecutando.
	 * El resultado expresa si dadas las incompatibilidades expresadas en la matriz y el estado actual de ejecuciones, la tarea que se
	 * quiere ejecutar se puede ejecutar.
	 * */
	private PermisoTareaVO permitidaTarea(int tareaAEjecutar)
	{
		// Recorremos toda la lista de tareas para ver cuales estan en ejecucion
		for (int tarea = 0; tarea < tareasEnEjecucion.length; tarea++) {
//			 En cuanto encuentre una tarea que se este ejecutando y no sea compatible con la que se quiere ejecutar, ya no se puede.
			if (tareasEnEjecucion[tarea]>0 && !tareasCompatibles[tarea][tareaAEjecutar])
				return denegar(tareaAEjecutar);
		}
		return permitir(tareaAEjecutar);
	}
	
	/*
	 * Metodo que dada una tarea, aumenta el número de tareas de ese mismo tipo que se están ejecutando en este momento.
	 * Se debe de invocar en caso de obtener permiso por parte del monitor de la tarea que se notifica que se ejecuta.
	 * */
	private void aniadirTarea(int tareaAEjecutar)
	{
		synchronized (tareasEnEjecucion) {
			tareasEnEjecucion[tareaAEjecutar]++;
		}
	}
	
	/*
	 * Metodo que dada una tarea, resta del número de tareas del mismo tipo que se estan ejecutando en este momento.
	 * Se debe invocar en caso de finalizar la tarea que se notifica se termina.
	 * */
	private void eliminarTarea(int tareaEjecutada)
	{
		synchronized (tareasEnEjecucion) {
			if (tareasEnEjecucion[tareaEjecutada]>0)
				tareasEnEjecucion[tareaEjecutada]--;
		}
	}
//	******************************************
//	PUBLICACION MASIVA
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de publicacion masiva.
	 * */
	public void permitirPublicarMasivo()
	{
//		Se ha terminado una tarea de publicacion masiva, y se disminuyen el número de tareas de publicacion masiva que estén registradas
		eliminarTarea(TAREA_PUBLICAR_MASIVO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de publicación masiva.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoPublicarMasivo()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten publicar masivamente
		if ((permiso = permitidaTarea(TAREA_PUBLICAR_MASIVO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de publicacion masiva a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_PUBLICAR_MASIVO);
		}
		return permiso;
	}
//	PUBLICACION UNITARIA
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de publicacion unitaria.
	 * */
	public void permitirPublicarUnitario()
	{
//		Se ha terminado una tarea de publicacion unitaria, y se disminuyen el número de tareas de publicacion unitaria que estén registradas
		eliminarTarea(TAREA_PUBLICAR_UNITARIO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de publicación unitaria.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoPublicarUnitario()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten publicar unitariamente
		if ((permiso = permitidaTarea(TAREA_PUBLICAR_UNITARIO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de publicacion unitaria a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_PUBLICAR_UNITARIO);
		}
		return permiso;
	}
//	REINDEXADO MASIVO
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de reindexado masivo.
	 * */
	public void permitirReindexarMasivo()
	{
//		Se ha terminado una tarea de reindexacion masiva, y se disminuyen el número de tareas de reindexacion masiva que estén registradas
		eliminarTarea(TAREA_REINDEXAR_MASIVO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de reindexado masivo.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoReindexarMasivo()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten reindexar masivamente
		if ((permiso = permitidaTarea(TAREA_REINDEXAR_MASIVO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de reindexacion masiva a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_REINDEXAR_MASIVO);
		}
		return permiso;
	}
//	REINDEXADO UNITARIO
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de reindexado unitario.
	 * */
	public void permitirReindexarUnitario()
	{
//		Se ha terminado una tarea de reindexacion unitaria, y se disminuyen el número de tareas de reindexacion unitaria que estén registradas
		eliminarTarea(TAREA_REINDEXAR_UNITARIO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de reindexado unitario.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoReindexarUnitario()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten reindexar unitariamente
		if ((permiso = permitidaTarea(TAREA_REINDEXAR_UNITARIO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de reindexacion unitaria a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_REINDEXAR_UNITARIO);
		}
		return permiso;
	}
//	REGENERAR INDICE
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de regeneración de índice.
	 * */
	public void permitirRegenerarIndice()
	{
//		Se ha terminado una tarea de regeneracion de indice, y se disminuyen el número de tareas de regeneracion de indice que estén registradas
		eliminarTarea(TAREA_REGENERAR_INDICE);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de regeneración de índice.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoRegenerarIndice()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten realizar la regeneracion de indice
		if ((permiso = permitidaTarea(TAREA_REGENERAR_INDICE)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de generacion de indice a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_REGENERAR_INDICE);
		}
		return permiso;
	}
	
//	BORRAR MASIVO
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de borrado masivo.
	 * */
	public void permitirBorrarMasivo()
	{
//		Se ha terminado una tarea de borrado masivo, y se disminuyen el número de tareas de borrado masivo que estén registradas
		eliminarTarea(TAREA_BORRAR_MASIVO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de borrado masivo.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoBorrarMasivo()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten realizar el borrado masivo
		if ((permiso = permitidaTarea(TAREA_BORRAR_MASIVO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de borrar masivo a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_BORRAR_MASIVO);
		}
		return permiso;
	}
//	BORRAR UNITARIO
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de borrado unitario.
	 * */
	public void permitirBorrarUnitario()
	{
//		Se ha terminado una tarea de borrado unitario, y se disminuyen el número de tareas de borrado unitario que estén registradas
		eliminarTarea(TAREA_BORRAR_UNITARIO);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de borrado unitario.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoBorrarUnitario()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten realizar el borrado unitario
		if ((permiso = permitidaTarea(TAREA_BORRAR_UNITARIO)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de borrar unitario a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_BORRAR_UNITARIO);
		}
		return permiso;
	}
//	REGENERAR IMAGENES
	/*
	 * Metodo que se invoca para indicar al monitor que se termina una operación de regeneración de imágenes.
	 * */
	public void permitirRegenerarImagenes()
	{
//		Se ha terminado una tarea de regenerado de imagenes, y se disminuyen el número de tareas de regenerado de imagenes que estén registradas
		eliminarTarea(TAREA_REGENERAR_IMAGENES);
	}
	
	/*
	 * Metodo que se invoca para pedir al monitor la posibilidad de ejecución de una tarea de regeneración de imágenes.
	 * Devuelve en el VO si el monitor nos concede el permiso o no para realizar la operacion. 
	 * */
	public PermisoTareaVO accesoRegenerarImagenes()
	{
		PermisoTareaVO permiso;
		//Si las condiciones actuales permiten realizar el regenerado de imagenes
		if ((permiso = permitidaTarea(TAREA_REGENERAR_IMAGENES)).getIdResultado().equals(PERMISO_CONCEDIDO))
		{
//			añadimos una nueva tarea de regenerar imagenes a las tareas que se realizan en la actualidad
			aniadirTarea(TAREA_REGENERAR_IMAGENES);
		}
		return permiso;
	}
}
