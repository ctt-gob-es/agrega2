/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvTaxonomiaService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.fuentestaxonomicas.negocio.servicio;

public interface SrvTaxonomiaService extends java.rmi.Remote {

    /**
     * metodo obtenerNodos
     *                 Buscara en la Taxonomia nomTaxonmia del idioma correspondiente,
     * el identificador id y devolvera los hijos de ese identificador
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerNodos(java.lang.String id, java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * metodo obtenerTaxonomia
     *                 Carga el primer nivel de hijos de una Taxonomia; el
     * padre y los
     *                 hijos
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTaxonomia(java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonConRutaVO[] obtenerTaxonomiaCompletaPreorden(java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;

    /**
     * Metodo que obtendra toda la ruta taxonomica que define a un
     * taxon dado.
     *                 El metodo recogera un identificador del taxon a buscar.
     * El
     *                 metodo buscara en la taxonomia todos los taxones antecedentes
     * al
     *                 taxon dado. Se devolvera una lista de TaxonVO con
     * toda la ruta.
     */
    public es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO[] obtenerTaxonPath(java.lang.String id, java.lang.String nomTaxonomia, java.lang.String idioma) throws java.rmi.RemoteException;
}
