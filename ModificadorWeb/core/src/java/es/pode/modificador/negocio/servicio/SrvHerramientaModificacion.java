/**
 * SrvHerramientaModificacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.modificador.negocio.servicio;

public interface SrvHerramientaModificacion extends java.rmi.Remote {

    /**
     * Devuelve aquellas modificaciones que han modificado el ODE
     *                 identificado.
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] chequearResultadosODE(java.lang.String pathOriginal) throws java.rmi.RemoteException;

    /**
     * Configura una modificación. Si no existía previamente la tarea,
     * la crea.
     *                 El valor de retorno es el identificador de la Tarea
     */
    public java.lang.Long configurar(java.lang.Long idModificacion, es.pode.modificador.negocio.servicio.ConfiguracionTarea configuracion, java.lang.String nombre) throws java.rmi.RemoteException;

    /**
     * Método que devuelve el contenido del ode modificado
     */
    public javax.activation.DataHandler descargarModificacion(java.lang.Long idModificacion, java.lang.String idOde, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Elimina del planificador la tarea de modificacion previamente
     * programada.
     */
    public java.lang.Boolean desprogramarTarea(java.lang.Long idModificacion, java.lang.String idPlanificador) throws java.rmi.RemoteException;

    /**
     * Envía un mensaje a la cola JMS para que el servicio de tareas
     * de
     *                 modificación lance la tarea especificada. Devuelve
     * false si ha
     *                 habido un problema en el envío del mensaje. La modificacion
     * cambia su estado en base de datos de CONFIGURADA a RUNNING, para
     *                 indicar que la tarea esta en ejecución.
     */
    public java.lang.Boolean ejecutarModificacion(java.lang.Long idModificacion) throws java.rmi.RemoteException;

    /**
     * Elimina la tarea de modificación indicada. Imposibilita
     *                 restaurar los odes afectados
     */
    public java.lang.Boolean eliminarModificacion(java.lang.Long[] identificadores) throws java.rmi.RemoteException;

    /**
     * Genera un XML de configuracion a partir de la tarea configurada
     * y lo serializa para su exportacion.
     */
    public javax.activation.DataHandler exportarModificacion(es.pode.modificador.negocio.servicio.ConfiguracionTarea tarea) throws java.rmi.RemoteException;

    /**
     * Pone a estado FINALIZADA las tareas de modificación que se
     * pasan
     *                 por parámetro y elimina de BD y de disco los backups
     * de los odes
     *                 que se modifican en esta tarea, de esta forma la tarea
     * se puede
     *                 volver a utilizar.
     *                 Las tareas cuyos identificadores  se pasan por parámetro
     * pasarán
     *                 de PENDIENTES a FINALIZADAS.
     */
    public java.lang.Boolean finalizarModificaciones(java.lang.Long[] idModificaciones) throws java.rmi.RemoteException;

    /**
     * Genera el codigo XML correspondiente a un taxonPath del arbol
     * curricular que se quiere insertar en un campo classification
     *                 (soportados: arbolcurricular o ETB). Este codigo devuelto
     * esta
     *                 listo para incorporarse a una tarea de Añadir Termino
     * Lomes como
     *                 nuevoValor.
     */
    public java.lang.String generarArbolCurricular(es.pode.modificador.negocio.servicio.TaxonomiaVO taxonomia) throws java.rmi.RemoteException;

    /**
     * Genera el codigo XML correspondiente a un taxonPath del ETB
     * que
     *                 se quiere insertar en un campo classification (soportados:
     * arbolcurricular o ETB). Este codigo devuelto esta listo para
     *                 incorporarse a una tarea de Añadir Termino Lomes como
     * nuevoValor.
     */
    public java.lang.String generarETB(es.pode.modificador.negocio.servicio.TaxonomiaVO taxonomia) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.modificador.negocio.servicio.ConfiguracionTarea importarModificacion(javax.activation.DataHandler fichero) throws java.rmi.RemoteException;

    /**
     * Realiza una consulta a fuentes taxonomicas para navegar por
     * la
     *                 taxonomia elegida (en este caso, LOM)
     */
    public es.pode.modificador.negocio.servicio.TerminoLomVO navegarLom(java.lang.String idTermino) throws java.rmi.RemoteException;

    /**
     * Realiza una consulta a fuentes taxonomicas para navegar por
     * la
     *                 taxonomia elegida (arbol curricular, etb)
     */
    public es.pode.modificador.negocio.servicio.TaxonomiaVO navegarTaxonomia(java.lang.String idNodo, java.lang.String nombreTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Realiza una consulta a fuentes taxonomicas para navegar por
     * la
     *                 taxonomia elegida (arbol curricular, etb)
     */
    public es.pode.modificador.negocio.servicio.TaxonomiaVO navegarTesauro(java.lang.String idNodo, java.lang.String nombreTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.modificador.negocio.servicio.FormularioTaxonomias obtenerCombosTaxonomias(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Carga la configuracion de una tarea de modificacion para su
     * modificación.
     */
    public es.pode.modificador.negocio.servicio.ConfiguracionTarea obtenerConfiguracionTarea(java.lang.Long idModificacion) throws java.rmi.RemoteException;

    /**
     * Obtiene los datos del termino que se desea modificar. Esto
     *                 permite pintar el formulario de nuevo valor como campo
     * de texto
     *                 o combo según sea el termino a modificar un vocabulario
     * controlado o no.
     */
    public es.pode.modificador.negocio.servicio.FormularioModificarVO obtenerFormularioModificar(java.lang.String idTermino) throws java.rmi.RemoteException;

    /**
     * Obtiene los detalles de la modificación
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO obtenerModificacion(java.lang.Long idModificacion) throws java.rmi.RemoteException;

    /**
     * Obtiene modificaciones ya configuradas.
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] obtenerModificacionesConfiguradas() throws java.rmi.RemoteException;

    /**
     * Obtiene modificaciones ya ejecutadas
     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] obtenerModificacionesEjecutadas() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.modificador.negocio.servicio.ModificacionVO[] obtenerModificacionesEnEjecucion() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.modificador.negocio.servicio.ResultadoModificacionVO[] obtenerModificacionesPorEstado(java.lang.Long idModificacion, java.lang.String[] estados) throws java.rmi.RemoteException;

    /**
     * Obtiene un listado con informacion de los ODEs publicados por
     * la
     *                 carga de objetos deseada. En caso de que en la carga
     * no se
     *                 publicase con exito ningun objeto, se devuelve un
     * array vacio.
     */
    public es.pode.modificador.negocio.servicio.OdeCargaMasivaVO[] obtenerOdesCargaMasiva(java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Obtiene el resultado de la modificación indicada
     */
    public es.pode.modificador.negocio.servicio.ResultadoModificacionVO[] obtenerResultadoModificacion(java.lang.Long idModificacion) throws java.rmi.RemoteException;

    /**
     * Obtiene del planificador un listado de tareas de carga de ODEs.
     * Los resultados obtenidos del planificador se mapean a un objeto
     *                 de la clase
     *                 es.pode.modificador.negocio.servicio.TareaCargaMasivaVO.
     */
    public es.pode.modificador.negocio.servicio.TareaCargaMasivaVO[] obtenerTareasCargaMasiva() throws java.rmi.RemoteException;

    /**
     * Obtiene tipos de modificación
     */
    public es.pode.modificador.negocio.servicio.CambioTypes obtenerTipos() throws java.rmi.RemoteException;

    /**
     * Devuelve un array de ListadoTareasModificacionVO con el listado
     * de tareas de modificación ejecutadas y pendientes de
     *                 finalización
     */
    public es.pode.modificador.negocio.servicio.ListadoTareasModificacionVO obtenerTodasModificaciones() throws java.rmi.RemoteException;

    /**
     * Programa en el planificador la ejecucion diferida de la tarea.
     */
    public java.lang.Boolean planificarModificacion(java.lang.Long idModificacion, java.util.Calendar fechaEjecucion) throws java.rmi.RemoteException;

    /**
     * Recupera una plantilla para añadir un nuevo termino lomes.
     */
    public java.lang.String recuperarPlantillaLomes(java.lang.String terminoLomes) throws java.rmi.RemoteException;

    /**
     * Recupera la traza de una modificación, si existe.
     *                 El valor de retorno es la URI a la traza.
     */
    public es.pode.modificador.negocio.servicio.ResultadoModificacionVO recuperarResultadoODE(java.lang.Long idModificacion, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Asigna los resultados dados a la tarea de modificación indicada
     */
    public java.lang.Boolean registrarResultadosTarea(java.lang.Long idModificacion, es.pode.modificador.negocio.servicio.ResultadoModificacionVO[] resultados) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean replanificarModificaciones(java.lang.Long[] idModificaciones) throws java.rmi.RemoteException;

    /**
     * Restaura backup de la modificación indicada
     */
    public java.lang.Boolean restaurar(java.lang.Long idModificacion, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Realiza una ejecución de unos pocos ODEs escogidos al azar
     * entre
     *                 los configurados para que se pueda obtener una pseudo-validación
     * de los cambios configurados. El value object devuelto contiene
     *                 la información necesaria para mostrar los resultados
     * por ODE y
     *                 los textos de traza.
     */
    public es.pode.modificador.negocio.servicio.ModificacionSimuladaVO simularModificacion(es.pode.modificador.negocio.servicio.ConfiguracionTarea configuracion, java.lang.Integer numeroOdes) throws java.rmi.RemoteException;

    /**
     * Valida modificación
     */
    public java.lang.Boolean validar(es.pode.modificador.negocio.servicio.ConfiguracionTarea configuracion) throws java.rmi.RemoteException;
}
