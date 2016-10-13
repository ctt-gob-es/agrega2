/**
 * SrvAuditoriaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.soporte.auditoria.servicios;

public interface SrvAuditoriaServicio extends java.rmi.Remote {

    /**
     * Método de generación de un informe. El informe que se desea
     * ejecutar, los parámetros que necesita y el path donde se
     *                 almacenará una vez generado se le pasará como parámetro
     */
    public void crearAlmacenarInforme(es.pode.soporte.auditoria.servicios.ParametroInformeGenericoVO parametroInformeGenerico) throws java.rmi.RemoteException;

    /**

     */
    public void crearGuardarInforme(es.pode.soporte.auditoria.servicios.ParametroCrearInformeVO parametroCrearInformeVO) throws java.rmi.RemoteException;

    /**
     * metodo que se encarga de llamar al metodo que crear el informe
     * y
     *                 lo guarda en el lugar correspondiente
     */
    public void crearGuardarInformeFederado(es.pode.soporte.auditoria.servicios.ParametroCrearInformeVO parametroCrearInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public javax.activation.DataHandler crearInforme(es.pode.soporte.auditoria.servicios.ParametroCrearInformeVO parametrosCrearInforme) throws java.rmi.RemoteException;

    /**
     * Genera un informe con toda la información del repositorio.
     * Será
     *                 invocado a través de una tarea programada
     */
    public void crearInformeRepositorio(java.lang.String idioma, java.util.Calendar fechaActualizacion) throws java.rmi.RemoteException;

    /**
     * Obtiene los informes de tipo 'Mas' que aparecerán en la portada.
     * Será el método que se invocará desde la tarea planificada
     */
    public void crearInformesPortada() throws java.rmi.RemoteException;

    /**
     * Este método devolverá la información que se va a mostrar en
     * el
     *                 informe
     */
    public es.pode.soporte.auditoria.servicios.InformeCargaMasivaVO informeCargaMasiva(long idTarea) throws java.rmi.RemoteException;

    /**
     * Método que obtiene un VO con toda la información necesaria
     * para
     *                 obtener el informe de odes por cobertura curricular
     */
    public es.pode.soporte.auditoria.servicios.InformeCoberturaCurricularVO[] informeCoberturaCurricular(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de objetos InformeEstadoVO con
     * el
     *                 número de odes que se encuentran en cada uno de los
     * estados
     *                 posibles.
     */
    public es.pode.soporte.auditoria.servicios.InformeEstadoOdesVO[] informeEstadoOdes(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.auditoria.servicios.InformeEstadoOdesVO[] informeEstadoOdesTransicciones(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeLicenciaOdesVO con el
     * número de odes que hay por licencia para un rango de fechas en
     *                 concreto
     */
    public es.pode.soporte.auditoria.servicios.InformeLicenciasOdesVO[] informeLicenciasOdes(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más descargados
     */
    public es.pode.soporte.auditoria.servicios.InformeMasVO[] informeMasDescargados(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.auditoria.servicios.InformeMasVO[] informeMasEnviado(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más mostrados
     */
    public es.pode.soporte.auditoria.servicios.InformeMasVO[] informeMasMostrado(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un array de InformeMasPesadosVO con los odes más
     *                 pesados.
     */
    public es.pode.soporte.auditoria.servicios.InformeMasPesadosVO[] informeMasPesados(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más previsualizados
     */
    public es.pode.soporte.auditoria.servicios.InformeMasVO[] informeMasPrevisualizados(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeMasValoradoVO con los
     * odes más valorados. El número de odes que devolverá este métodos
     *                 dependerá de lo que se le pase por parámetro
     */
    public es.pode.soporte.auditoria.servicios.InformeMasValoradoVO[] informeMasValorado(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Devuelve un VO con el número de odes que se le pasa por
     *                 parámetro más visualizados
     */
    public es.pode.soporte.auditoria.servicios.InformeMasVO[] informeMasVisualizados(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Obtiene un VO con la información del informe de odes por nivel
     * de agregación entre dos fechas concretas, estas fechas serán las
     *                 fechas de publicación de los odes.
     */
    public es.pode.soporte.auditoria.servicios.InformeNivelAgregacionVO[] informeNivelAgregacion(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve las operaciones que ha realizado un usuario
     * entre dos fechas concretas
     */
    public es.pode.soporte.auditoria.servicios.InformeOdeUsuarioVO[] informeOdeUsuario(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.auditoria.servicios.InformeOdeUsuarioVO[] informeOdeUsuarioOperacion(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Obtiene un VO con las operaciones realizadas (previsualizar,
     * visualizar, descargar,....) entre una fecha de inicio y una
     *                 fecha de fin
     */
    public es.pode.soporte.auditoria.servicios.InformeOperacionVO[] informeOperacionesRealizadas(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.soporte.auditoria.servicios.InformeOperacionUsuarioVO[] informeOperacionUsuario(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeProcesoPlanificado con
     * los procesos planificados entre un rango de fechas concretos
     */
    public es.pode.soporte.auditoria.servicios.InformeProcesoPlanificadoVO[] informeProcesosPlanificados(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un VO con toda la información necesaria
     * para
     *                 obtener el informe de términos buscados
     */
    public es.pode.soporte.auditoria.servicios.InformeTerminoBusquedaVO[] informeTerminosBusqueda(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametrosInformeVO) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un array de InformeUsuariosVO con todos
     * los
     *                 datos de los usuarios activos entre un rango de fechas
     * concreto
     */
    public es.pode.soporte.auditoria.servicios.InformeUsuariosVO[] informeUsuarios(es.pode.soporte.auditoria.servicios.ParametrosInformeVO parametroInformeVO) throws java.rmi.RemoteException;

    /**
     * Recupera de base de datos las estadisticas de peticiones a
     * feeds
     *                 para el intervalo de fechas especificado. En caso
     * de que se
     *                 dejen las fechas a null, recupera las estadisticas
     * totales.
     */
    public es.pode.soporte.auditoria.servicios.EstadisticasFeedsVO[] obtenerEstadisticasFeeds(java.util.Calendar fechaDesde, java.util.Calendar fechaHasta) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] obtenerInformes() throws java.rmi.RemoteException;

    /**
     * Nos devuelve un array de numeroOperacionesVO
     */
    public es.pode.soporte.auditoria.servicios.NumeroOperacionesVO[] obtenNumeroOperaciones(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Método que almacena en BD los términos que han sido buscados
     * así
     *                 como el ámbito y el usuario que realiza la búsqueda
     */
    public void registrarBusqueda(es.pode.soporte.auditoria.servicios.BusquedaVO busqueda) throws java.rmi.RemoteException;

    /**
     * Almacena en BD las distintas operaciones que se van realizando
     * (previsualizar, descargar, visualizar....) en la plataforma
     */
    public void registrarOperacion(es.pode.soporte.auditoria.servicios.OperacionVO operacion) throws java.rmi.RemoteException;

    /**
     * Registra una peticion a un canal RSS/Atom en la tabla de
     *                 auditoria.
     */
    public java.lang.Long registrarPeticionFeed(es.pode.soporte.auditoria.servicios.PeticionFeedVO peticionFeed) throws java.rmi.RemoteException;

    /**
     * Registro de la fecha de finalización de un trabajo ejecutado
     */
    public java.lang.Long registrarTrabajoFechaFinPlan(es.pode.soporte.auditoria.servicios.TareaEjecutadaPlanVO trabajo) throws java.rmi.RemoteException;

    /**
     * Regostro de la subtarea de una tarea
     */
    public java.lang.Long registrarTrabajoHijoPlan(es.pode.soporte.auditoria.servicios.RegistroTareaEjecPlanVO trabajoHijo) throws java.rmi.RemoteException;

    /**
     * Se registra el trabajo que se ha interrumpido
     */
    public java.lang.Long registrarTrabajoInterrPlan() throws java.rmi.RemoteException;

    /**
     * Registro de un trabajo planificado
     */
    public java.lang.Long registrarTrabajoPlan(es.pode.soporte.auditoria.servicios.TareaEjecutadaPlanVO trabajo) throws java.rmi.RemoteException;

    /**
     * Obtiene todo el contenido del repositorio
     */
    public es.pode.soporte.auditoria.servicios.RepositorioVO[] repositorio(java.lang.String idioma, java.lang.String fechaActualizacion) throws java.rmi.RemoteException;
}
