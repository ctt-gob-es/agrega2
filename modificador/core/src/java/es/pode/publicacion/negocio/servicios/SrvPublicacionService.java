/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvPublicacionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvPublicacionService extends java.rmi.Remote {

    /**
     * Este metodo implementa las operaciones de creacion de un nuevo
     * ODE desde la nada hasta el estado de Creacion.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String creacion(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Este metodo realiza las operaciones de creacion de un ODE en
     * estado CREADO.
     *                 Valida la informacion del ODE que se pasa en formato
     * PIF(ZIP) y
     *                 lo alberga en la plataforma bajo el usuario con el
     * que se crea.
     *                 Se descomprime en un temporal para validarlo, y si
     * valida se
     *                 crea un localizador y se copia en Ã©l.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String crearPIF(javax.activation.DataHandler ficheroPIF, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Elimina el ode que se pasa por parametro del usuario. Los odes
     * eliminables por el usuario son los que estan en estado:creacion,
     *                 no disponible, rechazado.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String eliminar(java.lang.String idODEs, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Este metodo elimina los ODEs en estado No Disponible que cumplen
     * las condiciones de los parametros que se le pasan: rango de
     *                 fecha en el que paso a no disponible, usuario al que
     * pertenece
     *                 el ODE, etc.
     *                 Si la lista de usuarios es vacia, se tendran en cuenta
     * los ODEs
     *                 no disponibles de todo el repositorio.
     *                 Si la fecha de fin es vacia, se tendra en cuenta la
     * fecha actual
     *                 como limite temporal superior.
     *                 Si la fecha de inicio es vacia, no se tendra en cuenta
     * limite
     *                 temporal inferior.
     *                 Devuelve un array de resultados de eliminar los ODEs
     * que cumplen
     *                 las condiciones de eliminacion.
     */
    public es.pode.publicacion.negocio.servicios.EliminarResultadoVO[] eliminarNoDisponibles(es.pode.publicacion.negocio.servicios.EliminarNoDisponiblesVO parametro) throws java.rmi.RemoteException;

    /**
     * Este metodo modifica los valores de titulo y comentarios de
     * un
     *                 ode en estado creado que le pasan.
     *                 Si los valores de titulo o comentarios suministrados
     * son alguno
     *                 o los dos nulos, no se modificara el campo afectado.
     * Solo se
     *                 modificaran los campos con contenido.
     *                 Devuelve un booleano con el resultado de la modificacion
     * y una
     *                 excepcion en caso de no existir ningun ode en creacion
     * con esas
     *                 caracteristicas.
     */
    public java.lang.Boolean modificaODECreado(java.lang.String idODE, java.lang.String idUsuario, java.lang.String titulo, java.lang.String comentarios) throws java.rmi.RemoteException;

    /**
     * Metodo que implementa el paso a no disponible del ODE que le
     * indican.
     *                 El paso a no disponible implica la desindexacion del
     * ODE, la
     *                 eliminacion de todas sus valoraciones y un cambio
     * de
     *                 localizacion en disco.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String noDisponible(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Este metodo consulta el estado actual del ODE que le pasan.
     * Se
     *                 le pasa el idioma al que traducir el texto descriptivo
     * del
     *                 estado.
     *                 Obtenemos el estado del ODE traducido al idioma que
     * le pasamos.
     */
    public java.lang.String obtenEstadoPorIdODE(java.lang.String idODE, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Este metodo recibe un identificador de un ODE del que espera
     * obtener un historial de las transiciones de estados por los que
     *                 ha ido pasando en su historia.
     *                 Devuelve un array con las transiciones ordenadas por
     * fecha de
     *                 los estados del ODE.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenHistorialPorIdODE(java.lang.String idODE) throws java.rmi.RemoteException;

    /**
     * Este metodo devuele la licencia de un ODE, del que sabemos
     * su
     *                 id.
     */
    public es.pode.publicacion.negocio.servicios.LicenciaVO obtenLicenciaODE(java.lang.String idODE, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Este metodo recupera la lista de ODEs en estado CREADO asociados
     * a un usuario.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsCreadosPorUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Muestra los odes de todos los usuarios del noodo que estan
     *                 despublicados (no disponibles)
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsDespublicados() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve los ODEs que estan en estado No Disponible
     * asociados al usuario dado.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsDespublicadosPorUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Devuelve los odes de todos los usuarios del nodo que estan
     *                 pendientes de publicacion (en estado propuesto)
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsPropuestos() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve los ODEs en estado PROPUESTO asociados
     * al
     *                 usuario dado.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsPropuestosPorUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve una lista de identificadores de ODEs que
     * se
     *                 encuentren en estado publicado.
     */
    public es.pode.publicacion.negocio.servicios.IdODEVO[] obtenODEsPublicados() throws java.rmi.RemoteException;

    /**
     * Este metodo selecciona los ODEs en estado PROPUESTO asociados
     * al
     *                 usuario dado.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsPublicadosPorUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Devuelve los odes del usuario que estan en estado rechazado.
     */
    public es.pode.publicacion.negocio.servicios.TransicionVO[] obtenODEsRechazadosPorUsuario(java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Este metodo propone para publicacion el ODE que se le indica.
     * Devuelve un string con el error que se ha producido en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String proponerPublicacion(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Este metodo se encargara de coger un identificador de ODE y
     * realizar todos los pasos para publicarlo, incluyendo la
     *                 generacion del MEC.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String publicar(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo, java.lang.String comunidades, java.lang.String tipoLicencia, java.lang.String universal) throws java.rmi.RemoteException;

    /**
     * Publica un ode que este en estado despublicado. No genera un
     * nuevo codigo mec.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String publicarDespublicado(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo, java.lang.String comunidades, java.lang.String tipoLicencia, java.lang.String universal) throws java.rmi.RemoteException;

    /**
     * Metodo encargado de publicar objetos en formato PIF (ZIP).
     * La
     *                 funcionalidad sera similar a la realizada por el metodo
     * publicar(idODE).
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String publicarPIF(javax.activation.DataHandler pif, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String sobrescribir) throws java.rmi.RemoteException;

    /**
     * Metodo que implementa el rechazo del ODE que le indican.
     *                 Devuelve un string con el error que se ha producido
     * en el caso
     *                 de detectarse algun problema.
     */
    public java.lang.String rechazar(java.lang.String idODE, java.lang.String idUsuario, java.lang.String comentarios, java.lang.String titulo) throws java.rmi.RemoteException;

    /**
     * Se regeneran los indices de los idiomas que se pasan.
     *                 Se borran todos los odes indexados en esos idiomas
     * y se indexan
     *                 los odes publicados en el momento de la invocacion
     * para cada
     *                 idioma afectado.
     */
    public es.pode.publicacion.negocio.servicios.ReindexarODEResultadoVO[] regeneraIndiceIdioma(es.pode.publicacion.negocio.servicios.RegeneracionIndiceVO paramRegenera) throws java.rmi.RemoteException;

    /**
     * Este metodo reindexa en el indice de busqueda el ODE que se
     * le
     *                 indica con la valoracion que le pasan.
     *                 Devuelve un VO con el codigo de exito o error en la
     * operacion.
     */
    public es.pode.publicacion.negocio.servicios.ReindexarODEResultadoVO reindexarODEPublicado(java.lang.String idODE, java.lang.Float valoracion) throws java.rmi.RemoteException;

    /**
     * Este metodo reindexa la lista de ODEs publicados que le pasan
     * (lista de identificadores).
     *                 Los elimina del indice  y los vuelve a reindexar cada
     * uno en su
     *                 idioma.
     *                 Se devuelve un VO con codigo de exito/fracaso para
     * cada ODE.
     */
    public es.pode.publicacion.negocio.servicios.ReindexarODEResultadoVO[] reindexarODEsPublicados(java.lang.String[] idODEs) throws java.rmi.RemoteException;
}
