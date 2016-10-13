package es.pode.indexador.negocio.servicios.indexado;

public class SrvIndexadorServiceProxy implements es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService {
  private String _endpoint = null;
  private es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService srvIndexadorService = null;
  
  public SrvIndexadorServiceProxy() {
    _initSrvIndexadorServiceProxy();
  }
  
  public SrvIndexadorServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvIndexadorServiceProxy();
  }
  
  private void _initSrvIndexadorServiceProxy() {
    try {
      srvIndexadorService = (new es.pode.indexador.negocio.servicios.indexado.SrvIndexadorServiceServiceLocator()).getSrvIndexadorService();
      if (srvIndexadorService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srvIndexadorService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srvIndexadorService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srvIndexadorService != null)
      ((javax.xml.rpc.Stub)srvIndexadorService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public es.pode.indexador.negocio.servicios.indexado.SrvIndexadorService getSrvIndexadorService() {
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService;
  }
  
  public void borrarOdesPorIdioma(java.lang.String idioma, int num) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    srvIndexadorService.borrarOdesPorIdioma(idioma, num);
  }
  
  public void borrarPorIdioma(java.lang.String idioma) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    srvIndexadorService.borrarPorIdioma(idioma);
  }
  
  public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] eliminarODE(java.lang.String[] idODE) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.eliminarODE(idODE);
  }
  
  public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] indexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.indexarODE(odes);
  }
  
  public void optimizarIndice(java.lang.String idioma) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    srvIndexadorService.optimizarIndice(idioma);
  }
  
  public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndice(java.lang.String idioma, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.regenerarIndice(idioma, odes);
  }
  
  public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] regenerarIndices(java.lang.Long idTarea, es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.regenerarIndices(idTarea, odes);
  }
  
  public es.pode.indexador.negocio.servicios.indexado.IndexadorVO[] reindexarODE(es.pode.indexador.negocio.servicios.indexado.IdODEVO[] odes) throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.reindexarODE(odes);
  }
  
  public boolean sincronizarIndiceCompass() throws java.rmi.RemoteException{
    if (srvIndexadorService == null)
      _initSrvIndexadorServiceProxy();
    return srvIndexadorService.sincronizarIndiceCompass();
  }
  
  
}