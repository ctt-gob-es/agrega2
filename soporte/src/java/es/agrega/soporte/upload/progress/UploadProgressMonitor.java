package es.agrega.soporte.upload.progress;

import org.apache.log4j.Logger;

public class UploadProgressMonitor implements org.apache.commons.fileupload.ProgressListener{

	private double progress = 0;
	
	public double getProgress() { return progress; }
	
	public long getPercentageProgress() {
		long iProgress = Math.round(100*progress);
		return iProgress; 
	};
	
    private Logger log = Logger.getLogger(es.agrega.soporte.upload.progress.UploadProgressMonitor.class);
    
    
    public UploadProgressMonitor() {
    	if(log.isDebugEnabled()) log.debug("Constructor UploadProgressMonitor");
    }
    
    public void update(long pBytesRead, long pContentLength, int pItems) {

    	double iProgress = pContentLength==-1?0:((double)pBytesRead / (double)pContentLength);

    	if ( (iProgress - progress)<0.01 ) {
            return;
        }

    	progress = iProgress;
    	if(log.isDebugEnabled()) {
	    	log.debug("Subiendo fichero " + pItems);
	
	    	if (pContentLength == -1) {
	     	   log.debug("Progreso desconocido. Bytes leidos " + pBytesRead);
	        } else {
	     	   log.debug("Progreso : " + 100*progress + "%");
	        }
    	}
    }
}
