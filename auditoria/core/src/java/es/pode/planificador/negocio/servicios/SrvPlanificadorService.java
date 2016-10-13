/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvPlanificadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.planificador.negocio.servicios;

public interface SrvPlanificadorService extends java.rmi.Remote {

    /**
     * Metodo intermedio entre el servicio de planificador y auditoria
     * para que se genere el informe al hacer la carga masiva.
     */
    public void crearInformeCargaMasiva(es.pode.planificador.negocio.servicios.ParametroInformeCargaVO parametro) throws java.rmi.RemoteException;

    /**
     * Creación de la tarea de carga de Objetos Digitales Educativos
     */
    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO crearTareaCargaODEs(es.pode.planificador.negocio.servicios.TareaCargaODEsVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Metodo que se encarga de crear una tarea de eliminacion de
     * ODEs
     *                 en estado no disponible.
     */
    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO crearTareaEliminarNoDisponibles(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Método para crear la tarea de informes
     */
    public es.pode.planificador.negocio.servicios.TareaInformesVO crearTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea) throws java.rmi.RemoteException;

    /**
     * metodo que crea una tarea con el informe del catalogo
     */
    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO crearTareaInformesCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO datosTarea) throws java.rmi.RemoteException;

    /**
     * metodo que crea una tarea de informes federada
     */
    public es.pode.planificador.negocio.servicios.TareaInformesVO crearTareaInformesFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Registra en el planificador una tarea de modificacion de odes.
     */
    public es.pode.planificador.negocio.servicios.TareaModificacionVO crearTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Método que se encarga de regenerar las imágenes
     */
    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO crearTareaRegenerarImagemes(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Creación de la tarea de reindexado
     */
    public es.pode.planificador.negocio.servicios.TareaReindexadoVO crearTareaReindexado(es.pode.planificador.negocio.servicios.TareaReindexadoVO datosTarea) throws java.rmi.RemoteException;

    /**
     * Método para eliminar los informes de carga
     */
    public es.pode.planificador.negocio.servicios.ValidaBajaInformeCargaVO eliminarInformesCarga(java.lang.String[] ficheros) throws java.rmi.RemoteException;

    /**
     * Metodo fachada para la eliminacion de los ODEs en estado no
     * disponible que hayan pasado a dicho estado en un periodo
     *                 concreto de tiempo.
     *                 Devuelve lo mismo que la correspondiente llamada al
     * servicio de
     *                 publicacion.
     */
    public java.lang.String eliminarNoDisponibles(java.util.Calendar fechaInicio, java.util.Calendar fechaHasta, java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Eliminación de las tareas programadas
     */
    public java.lang.Boolean eliminarTareas(es.pode.planificador.negocio.servicios.TrabajoVO[] tareas) throws java.rmi.RemoteException;

    /**
     * Eliminación unicamente las tareas del grupo por defecto del
     * administrador
     */
    public java.lang.Boolean eliminarTareasAdm(es.pode.planificador.negocio.servicios.TrabajoVO[] trabajo) throws java.rmi.RemoteException;

    /**
     * Se elimina la informacion relativa a un trabajo ejecutado
     *                 incluyendo el informe de la ejecucion
     */
    public java.lang.Boolean eliminarTrabajoEjecutado(java.lang.Long[] idTrabajo) throws java.rmi.RemoteException;

    /**
     * Consulta para conocer si el planificador está iniciado
     */
    public java.lang.Boolean estaIniciado() throws java.rmi.RemoteException;

    /**
     * Consulta para saber si existe ya existe un trabajo con ese
     *                 nombre y grupo
     */
    public java.lang.Boolean existeTrabajo(es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Método de generación del informe con el catálogo del repositorio
     */
    public void generarCatalogo(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Método para generar informe
     */
    public void generarInforme(es.pode.planificador.negocio.servicios.TareaInformesVO datosInforme, java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**

     */
    public void generarInformeFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosInforme, java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Método que genera los ficheros sitemaps.xml con las url de
     * las
     *                 fichas de los ODEs
     */
    public void generarSitemaps() throws java.rmi.RemoteException;

    /**
     * Método que crea los informes que se van a visualizar en el
     *                 enlace de Informes de la portada
     */
    public void informesPortada() throws java.rmi.RemoteException;

    /**
     * Inicio del planificador
     */
    public java.lang.Boolean iniciarPlanificador() throws java.rmi.RemoteException;

    /**
     * Método que crea los RSS
     */
    public void lanzarRSS() throws java.rmi.RemoteException;

    /**
     * Se ejecuta un trabajo manualmente.
     */
    public java.lang.Boolean lanzarTarea(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException;

    /**
     * Lista los informes generados al hacer la carga masiva, los
     *                 recogeremos de una carpeta en disco
     */
    public es.pode.planificador.negocio.servicios.InformeODEsCargadosVO[] listarInformesCarga() throws java.rmi.RemoteException;

    /**
     * Modificacion de la trabajo de carga de Objetos Digitales
     *                 Educativos en la plataforma
     */
    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO modificarTareaCargaODEs(es.pode.planificador.negocio.servicios.TareaCargaODEsVO datosTareaCargaODE, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Este metodo modifica una tarea de eliminacion de ODEs no
     *                 disponibles.
     */
    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO modificarTareaEliminarNoDisponibles(es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Modifica una tarea de informes
     */
    public es.pode.planificador.negocio.servicios.TareaInformesVO modificarTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO modificarTareaInformesCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.planificador.negocio.servicios.TareaInformesVO modificarTareaInformesFederado(es.pode.planificador.negocio.servicios.TareaInformesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Modifica en el planificador una tarea de modificacion de odes.
     */
    public es.pode.planificador.negocio.servicios.TareaModificacionVO modificarTareaModificacion(es.pode.planificador.negocio.servicios.TareaModificacionVO datosTarea) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO modificarTareaRegenerarImagenes(es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Modificación del trabajo de reindexado de índices en la
     *                 plataforma
     */
    public es.pode.planificador.negocio.servicios.TareaReindexadoVO modificarTareaReindexado(es.pode.planificador.negocio.servicios.TareaReindexadoVO datosTarea, es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Recuperamos el informe de la ejecucion de un trabajo.
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] obtenerInformeTrabajo(java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Recuperamos el informe de la ejecucion de un trabajo de carga
     * masiva
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaCargaEjecutadaVO[] obtenerInformeTrabajoCargaMasiva(java.lang.Long idTrabajo) throws java.rmi.RemoteException;

    /**
     * Informe de los trabajo que no se han ejecutado correctamente
     */
    public es.pode.planificador.negocio.servicios.RegistroTareaEjecutadaVO[] obtenerInformeTrabajoErroneos(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve la tarea de eliminacion de ODEs no
     *                 disponibles que coincida con la tarea.
     */
    public es.pode.planificador.negocio.servicios.TareaEliminaNoDisponiblesVO obtenerTareaEliminarrNoDisponibles(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO obtenerTareaInformeCatalogo(es.pode.planificador.negocio.servicios.TareaInformeCatalogoVO tarea) throws java.rmi.RemoteException;

    /**
     * Se recupera una tarea de informes
     */
    public es.pode.planificador.negocio.servicios.TareaInformesVO obtenerTareaInformes(es.pode.planificador.negocio.servicios.TareaInformesVO tarea) throws java.rmi.RemoteException;

    /**
     * Obtención de la tarea que se encarga de cargar los ODEs en
     * la
     *                 plataforma
     */
    public es.pode.planificador.negocio.servicios.TareaCargaODEsVO obtenerTareaModificarCargaODEs(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.planificador.negocio.servicios.TareaRegenerarImagenesVO obtenerTareaRegenerarImagenes(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException;

    /**
     * Obtención de la tarea de reindexado de índices
     */
    public es.pode.planificador.negocio.servicios.TareaReindexadoVO obtenerTareaReindexado(es.pode.planificador.negocio.servicios.TareaVO tarea) throws java.rmi.RemoteException;

    /**
     * Recuperamos las tareas en ejecucion
     */
    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasEnEjecucion() throws java.rmi.RemoteException;

    /**
     * Devuelve un listado con los trabajos pendientes de ejecución
     * y
     *                 pertenecientes al grupo por defecto.
     *                 El planificador permite programar tareas propias del
     * sistema que
     *                 puede que no sea interesante mostrarlas en la pantalla
     * de
     *                 administración de tareas
     *                 Ver: handleObtenerTareasPendientesTodas
     */
    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasPendientes() throws java.rmi.RemoteException;

    /**
     * Se recuperan todos los trabajos pendientes.
     */
    public es.pode.planificador.negocio.servicios.TareaVO[] obtenerTareasPendientesTodas() throws java.rmi.RemoteException;

    /**
     * Devuelve el tipo de la tarea
     */
    public java.lang.String obtenerTipoTarea(es.pode.planificador.negocio.servicios.TrabajoVO trabajo) throws java.rmi.RemoteException;

    /**
     * Obtención de los trabajos ejecutados
     */
    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO obtenerTrabajoEjecutado(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Obtención de los trabajos ya ejecutados.
     */
    public es.pode.planificador.negocio.servicios.TareaEjecutadaVO[] obtenerTrabajosEjecutados() throws java.rmi.RemoteException;

    /**
     * Parada del planificador
     */
    public java.lang.Boolean pararPlanificador() throws java.rmi.RemoteException;

    /**
     * Parada de un trabajo en ejecucion.
     */
    public java.lang.Boolean pararTarea(es.pode.planificador.negocio.servicios.TrabajoVO job) throws java.rmi.RemoteException;

    /**
     * Método que genera la imágen aleatoria para las sites externos
     */
    public void portadaODE() throws java.rmi.RemoteException;

    /**
     * Método fachada para la publicación del ODEs
     */
    public es.pode.planificador.negocio.servicios.ResultadoCargaVO[] publicarPIF(java.lang.String[] odes, java.lang.String idUsuario, java.lang.String nombreCarga, java.lang.String sobrescribir, java.lang.String pathCarga) throws java.rmi.RemoteException;

    /**
     * Recupera el contenido del informe que queremos visualizar
     */
    public javax.activation.DataHandler recuperarInformeCarga(java.lang.String nombreInforme) throws java.rmi.RemoteException;

    /**
     * Metodo para regenerar todas las imagenes de todos los indices
     */
    public java.lang.String regeneracionImagenes(java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Método fachada para llamar al servicio de reindexado simple
     * de
     *                 índices.
     *                 Retorna -1 si ha ido mal y el número de objetos indexados
     * si ha
     *                 ido bien.
     */
    public java.lang.Long reindexado(java.lang.String repositorio, java.lang.Long idTarea) throws java.rmi.RemoteException;

    /**
     * Se reinicia el planificador
     */
    public java.lang.Boolean reiniciarPlanificador() throws java.rmi.RemoteException;
}
