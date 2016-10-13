/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvLocalizadorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.localizador.negocio.servicios;

public interface SrvLocalizadorService extends java.rmi.Remote {

    /**
     * Para el localizador que se le pasa, recalcula el espacio que
     * ocupa la localización en disco si difiere
     *                 de la cifra almacenada en BBDD. Si no hay localizador
     * asociado
     *                 al id, se devuelve un 0.
     */
    public java.lang.Long actualizaEspacioLocalizador(java.lang.String idLocalizador) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve un array de localizadores de todos los
     * identificadores de ODEs que le pasan.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO[] buscarLocalizadoresPorId(java.lang.String[] ids) throws java.rmi.RemoteException;

    /**
     * Este metodo calcula el espacio que ocupa en disco en el momento
     * de la invocación el localizador que se le pasa.
     */
    public java.lang.Long calculaEspacioLocalizador(java.lang.String idLocalizador) throws java.rmi.RemoteException;

    /**
     * Consulta de espacio ocupado por un id de ODE
     */
    public java.lang.Long consultaEspacioLocalizador(java.lang.String idLocalizador) throws java.rmi.RemoteException;

    /**
     * Consulta de espacio ocupado por una lista de identificadores
     */
    public java.lang.Long[] consultaEspacioLocalizadores(java.lang.String[] identificadores) throws java.rmi.RemoteException;

    /**
     * Toma como parametro el identificador del localizador del que
     * se
     *                 esta interesado.
     *                 Devuelve un VO del localizador.
     *                 Este metodo recupera el localizador asociado al identificador
     * suministrado.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO consultaLocalizador(java.lang.String identificador) throws java.rmi.RemoteException;

    /**
     * Este metodo toma como parametro un identificador MEC y devuelve
     * la localizacion de este MEC cuando estaba en el taller.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO consultaLocalizadorNoPublicado(java.lang.String mec) throws java.rmi.RemoteException;

    /**
     * Este método permite crear un localizador para un ODE con su
     * identificador MEC y el usuario dado.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO crearLocalizador(java.lang.String mec, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Metodo para crear el localizador de un ODE no publicado.
     *                 Toma como parametro un nombre de usuario y un numero
     * identificador UUID.
     *                 Devuelve el VO del localizador creado.
     *                 El metodo crea una nueva entrada de localizacion en
     * la tabla de
     *                 localizadores. El localizador esta compuesto por una
     * ruta en
     *                 disco PATH, una ruta URL de acceso remoto al ODE y
     * un
     *                 identificador.
     *                 El PATH se construye a partir de la ruta-base del
     * fichero de
     *                 configuracion para ODE's de taller. Se le concatena
     * el nombre
     *                 del usuario mas el uuid.
     *                 La URL se construye a partir de la ruta-base-remota
     * del fichero
     *                 de configuracion para ODE's de taller. Se le concatena
     * el nombre
     *                 del usuario mas el uuid.
     *                 El valor del campo identificador toma el valor del
     * parametro
     *                 uuid.
     *                 El MEC se deja a 0.
     *                 El metodo ha de ser enteramente transaccional, de
     * forma que si
     *                 se produce cualquier error creando los directorios
     * u operando en
     *                 la BD se ha de deshacer cualquier operacion ya realizada.
     * Si ya existe un localizador con el campo "identificador" igual,
     *                 no se realiza el alta y se genera una excepcion.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO crearLocalizadorNoPublicado(java.lang.String usuario, java.lang.String uuid) throws java.rmi.RemoteException;

    /**
     * Metodo para crear el localizador de un ODE publicado.
     *                 Toma como parametros el localizador de un ODE no publicado
     * y un
     *                 numero identificador MEC.
     *                 Devuelve el VO del localizador creado.
     *                 El metodo crea una nueva entrada de localizacion en
     * la tabla de
     *                 localizadores.
     *                 El nuevo localizador esta compuesto por una ruta en
     * disco PATH,
     *                 una ruta URL de acceso remoto al ODE y un identificador
     * MEC.
     *                 El PATH se construye a partir de la ruta-base del
     * fichero de
     *                 configuracion para ODE's publicados al que se concatena
     * el
     *                 identificador MEC.
     *                 La URL se construye a partir de la ruta-base-remota
     * del fichero
     *                 de configuracion para ODE's publicados a la que se
     * le concatena
     *                 el identificador MEC.
     *                 El identificador toma el valor del parametro mec.
     *                 El MEC se deja a 0.
     *                 Si el identificador uuid suministrado corresponde
     * con un
     *                 localizador no publicado en la BD (el campo "identificador"
     * coincide con "uuid"):
     *                 - Si el localizador del ODE no publicado ya tiene
     * valor en el
     *                 campo MEC, devolvemos una excepcion y no hacemos nada.
     * (ese ODE
     *                 no publicado ya tiene un ODE publicado asociado).
     *                 - Si el localizador del ODE no publicado no tiene
     * valor en el
     *                 campo MEC, actualizamos dicho localizador aniadiendole
     * el valor
     *                 de la variable mec suministrada al campo MEC del registro
     * de la
     *                 BD y creamos un nuevo localizador para el ODE publicado
     * como se
     *                 define mas arriba.
     *                 Si el localizador no publicado no existe en la BD
     * (no existen
     *                 registros en la BD con el campo "idenficador" coincidentes
     * con
     *                 la variable "uuid"):
     *                 - Se lanza una excepción.
     *                 El metodo ha de ser enteramente transaccional, de
     * forma que si
     *                 se produce cualquier error creando los directorios
     * u operando en
     *                 la BD se ha de deshacer cualquier operacion ya realizada.
     */
    public es.pode.localizador.negocio.servicios.LocalizadorVO crearLocalizadorPublicado(java.lang.String uuid, java.lang.String mec) throws java.rmi.RemoteException;

    /**
     * Se elimina la localizacion, esto ocurrira en dos ocasiones:
     * a) Eliminacion fisica del ODE cuando este esta en estado
     *                 "Despublicado"
     *                 b) El ODE esta en creacion pero no se guarda.
     *                 La eliminacion implica, la eliminacion de todos los
     * registros
     *                 que tengan que ver con el identificador introducido.
     * Se elimina cualquier ODE que tenga como identificador el ID
     *                 introducido por parametro O que tenga como MEC el
     * id introducido
     *                 por parametro.
     *                 Eliminamos tambien el directorio que indique la/s
     * localizaciones
     *                 que vamos a eliminar.
     *                 Se devuelve un boolean: True, si todo ha ido bien
     * y False, en
     *                 caso contrario.
     */
    public java.lang.Boolean eliminarLocalizador(java.lang.String identificador) throws java.rmi.RemoteException;
}
