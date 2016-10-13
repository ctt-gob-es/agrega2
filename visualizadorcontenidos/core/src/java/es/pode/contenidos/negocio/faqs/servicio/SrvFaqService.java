/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/**
 * SrvFaqService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.faqs.servicio;

public interface SrvFaqService extends java.rmi.Remote {

    /**
     * Obtiene una FAQ identificada por su ID.
     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqVO consultaFaq(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public void crearCategoria(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoria) throws java.rmi.RemoteException;

    /**
     * Crea una nueva FAQ.
     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqVO crearFaq(es.pode.contenidos.negocio.faqs.servicio.FaqVO faq) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public void eliminarCategorias(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Elimina una o varias FAQ existentes.
     */
    public void eliminarFaq(java.lang.Long[] faqIDs) throws java.rmi.RemoteException;

    /**

     */
    public void limpiarCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public void limpiarFaq(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO[] listarCategorias() throws java.rmi.RemoteException;

    /**

     */
    public void modificarCategoria(es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO categoria) throws java.rmi.RemoteException;

    /**
     * Actualiza los contenidos de una FAQ existente.
     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqVO modificarFaq(es.pode.contenidos.negocio.faqs.servicio.FaqVO faq) throws java.rmi.RemoteException;

    /**
     * devuelve todas las categorias disponibles para las faqs
     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaTraducidaVO[] obtenCategoriasFaqs(java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.faqs.servicio.CategoriaFaqVO obtenerCategoria(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Obtener todas las FAQs disponibles.
     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqVO[] obtenerFaqs() throws java.rmi.RemoteException;

    /**
     * Obtiene un listado de FAQs filtradas por idioma.
     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO[] obtenerFaqsPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO obtenerFaqTraducida(java.lang.Long id, java.lang.String idioma) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.contenidos.negocio.faqs.servicio.FaqTraducidaVO[] obtenFaqsPorIdiomaCategoria(java.lang.String idioma, java.lang.Long categoria) throws java.rmi.RemoteException;
}
