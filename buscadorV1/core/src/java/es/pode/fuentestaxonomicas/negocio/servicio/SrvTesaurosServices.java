/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvTesaurosServices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvTesaurosServices extends java.rmi.Remote {

    /**
     * verifica la existencia de un tesauro para el idioma indicado.
     */
    public boolean chequearExistenciaIdioma(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene la jerarquia de un término.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerJerarquia(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene el primer nivel del Tesauro, es decir, aquellos términos
     * que no tengan padre.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerPrimerNivelTesauro(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.JerarquiaVO[] obtenerTerminos(java.lang.String texto, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos que están relacionados pasándole como
     *                 parámetro el identificador de un término.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorId(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos que están relacionados pasándole como
     *                 parámetro el texto de un término
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionadosPorTexto(java.lang.String textoTesauro, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos relacionados asociativamente con el
     *                 identificador que le pasamos como parámetro.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionAsociativa(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Obtiene los términos relacionados jerarquicamente con el
     *                 identificador que le pasamos como parámetro
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTerminosRelacionJerarquica(java.lang.String id, java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Método que devuelve un listado con los tesauros del idioma
     *                 indicado.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO[] obtenerTesauros(java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * obtiene el tesauro vigente de la plataforma.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.EstructuraVdexVO obtenerTesauroVigente() throws java.rmi.RemoteException;

    /**
     * obtiene los textos asociados a los ids indicados para el tesauro
     * e idioma indicados.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTextosDeIds(java.lang.String[] ids, java.lang.String idioma, java.lang.String nomTesauro) throws java.rmi.RemoteException;

    /**
     * obtiene el vocabname del pesauro indicando el nombre y el
     *                 idioma.
     */
    public java.lang.String obtenerVocabName(java.lang.String nomTesauro, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * obtiene los vocabnames de los tesauros pasados para el idioma
     * indicado.
     */
    public java.lang.String[] obtenerVocabNames(java.lang.String[] nomTesauros, java.lang.String idioma) throws java.rmi.RemoteException;
}
