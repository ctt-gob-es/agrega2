package es.agrega.galeriaimg.rmi;


public interface GaleriaImgServer 
{
	public void getImageUrlForId(String identifier, String mainFile, String serverOn) throws Exception;
	public void getImagesUrlsForIds(String[] identifiers, String[] mainFiles, String serverOn) throws Exception;
}
