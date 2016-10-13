/**
 * SrvVocabulariosControladosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvVocabulariosControladosService extends java.rmi.Remote {

    /**
     * El metodo traduceAIdioma busca para un identificador su valor
     * en
     *                 el idioma solicitado, accediendo para ello al xml
     * de
     *                 vocabularios controlados parseado en la hashmap en
     * el idioma
     *                 requerido en el parametro.
     *                 Se obtiene como parametro de salida un array de TerminoVO.
     * Cada
     *                 uno de los TerminoVO contiene:
     *                 - idTermino: identificador del termino para el cual
     * se desea
     *                 recoger su valor en el idioma especificado.
     *                 - nomTermino: atributo que se rellena en el interior
     * del metodo
     *                 una vez que se ha encontrado el identificador indicado
     * en el
     *                 fichero xml en el idioma especificado.
     *                 - idiomaTermino: idioma en el que se ha recogido el
     * valor del
     *                 termino.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO[] crearTraduccionAIdioma(java.lang.String[] l_id, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * El metodo crearTraduccionAIngles busca para un identificador
     * dado su valor en ingles, accediendo para ello al xml de
     *                 vocabularios controlados en ingles parseado en la
     * hashmap.
     *                 Este metodo es necesario para almacenar los terminos
     * de los
     *                 vocabularios controlados en ingles en el xml final,
     * ya que es
     *                 necesario que se encuentren en este idioma para consolidar
     * un
     *                 LOM-ES valido.
     *                 Como parametro de  salida, el metodo devuelve un array
     * de
     *                 TerminoYPadreVO, cada uno de los cuales esta compuesto
     * por:
     *                 - idVocabulario: indicando el identificador del vocabulario
     * al
     *                 que pertenece el termino a buscar.
     *                 - nomTermino: atributo que se rellena en el interior
     * del metodo
     *                 una vez que se ha encontrado el identificador indicado
     * en el
     *                 fichero xml en ingles.
     *                 - idiomaTermino: por defecto, debera ser siempre ingles.
     * - idTermino: identificador del termino para el cual se desea
     *                 recoger su valor en ingles.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO[] crearTraduccionAIngles(java.lang.String[] l_id) throws java.rmi.RemoteException;

    /**
     * El metodo obtenerCombos es el encargado de rellenar los combos
     * correspondientes a los vocabularios controlados en la
     *                 herramienta de catalogacion.
     *                 Los vocabularios controlados se encuentran en ficheros
     * xml
     *                 especificados mediante VDEX, cada uno de los cuales
     * se expresa
     *                 en un idioma diferente.
     *                 El metodo espera como parametros de entrada los identificadores
     * de los vocabularios que se desean cargar y el idioma en el que
     *                 el usuario esta realizando la catalogacion.
     *                 Como parametro de  salida, el metodo devuelve un array
     * de
     *                 VocabularioVO. Cada uno de estos contiene:
     *                 - idVocabulario: identificador que se le pasa como
     * parametro en
     *                 el array de identificadores y por el que se busca
     * en el fichero
     *                 xml
     *                 - nomVocabulario: es el nombre del vocabulario que
     * se
     *                 corresponde con el parametro del identificador. Inicialmente
     * es
     *                 null, y se rellena en el metodo.
     *                 - idioma: idioma en el que se desea recoger el vocabulario.
     * Los ficheros xml que contienen los vocabularios controlados
     *                 estaran parseados en una hashmap (se cargan en el
     * constructor
     *                 del servicio), de forma que estaran accesibles para
     * la
     *                 aplicacion en cualquier momento sin necesidad de parsearlos
     * cada
     *                 vez.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO[] obtenerCombos(java.lang.String[] l_id, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * El metodo obtenerIdTermino se utiliza para obtener los
     *                 identificadores de los terminos recogidos en el catalogador.
     * Es
     *                 necesario para realizar la traduccion a ingles de
     * los terminos
     *                 recogidos mediante el catalogador, de forma que el
     * fichero xml
     *                 formado sea valido con respecto a LOM-ES.
     *                 El metodo devuelve un array de TerminoYPadreVO que
     * contienen:
     *                 - idVocabulario: indicando el identificador del vocabulario
     * al
     *                 que pertenece el termino a buscar.
     *                 - nomTermino: valor del termino recogido en el idioma
     * en el que
     *                 se encuentre catalogando el usuario
     *                 - idiomaTermino: idioma en el que se encuentra escrito
     * nomTermino.
     *                 - idTermino: atributo que se rellenara en el metodo
     * y que
     *                 contendra el identificador del termino.
     *                 como parametro de entrada tenemos un VO de tipo TerminoYPadreVO,
     * que vendra relleno con
     *                 - idVocabulario: el identificador del combo (previamente
     * buscado
     *                 en el catalogador en su 		                       
     * fichero de
     *                 properties para buscar desde el nombre el id que le
     * corresponde)
     *                 -nomTermino: es el nombre que hemos seleccionado del
     * combo
     *                 -idiomaTermino: es el idioma en que queremos devolverlo
     * -idTermino: es el identificador que debemos buscar en la hashmap
     *                 con los datos que tenemos y devolver el mismo TerminoYPadreVO,
     * con el idTermino relleno
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO[] obtenerIdTermino(es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO[] terminoypadre) throws java.rmi.RemoteException;
}
