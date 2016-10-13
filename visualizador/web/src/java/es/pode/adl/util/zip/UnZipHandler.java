/*******************************************************************************
**
** Advanced Distributed Learning Co-Laboratory (ADL Co-Lab) Hub grants you 
** ("Licensee") a non-exclusive, royalty free, license to use, modify and 
** redistribute this software in source and binary code form, provided that 
** i) this copyright notice and license appear on all copies of the software; 
** and ii) Licensee does not utilize the software in a manner which is 
** disparaging to ADL Co-Lab Hub.
**
** This software is provided "AS IS," without a warranty of any kind.  ALL 
** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING 
** ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE 
** OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED.  ADL Co-Lab Hub AND ITS LICENSORS 
** SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
** USING, MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO 
** EVENT WILL ADL Co-Lab Hub OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, 
** PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
** INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE 
** THEORY OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE 
** SOFTWARE, EVEN IF ADL Co-Lab Hub HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH 
** DAMAGES.
**
*******************************************************************************/
package es.pode.adl.util.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import es.pode.adl.debug.DebugIndicator;
import es.pode.adl.sequencer.ADLDuration;

import org.apache.log4j.Logger;
/**
 *
 * <strong>Filename:</strong><br>UnZipHandler.java<br><br>
 *
 * <strong>Description:</strong><br>
 * A <code>UnZipHandler</code> provides the ability to extract the contents
 * of a zipped file to a given directory.<br><br>
 * 
 * <strong>Side Effects:</strong><br> The files will be extracted to the given
 * directory
 * 
 * @author ADL Technical Team
 */
public class UnZipHandler
{
	private static Logger logger = Logger.getLogger(UnZipHandler.class);
   /**
    * Buffer size for use in extracting Zip File
    */
   public static final int BUFFER_SIZE = 16384;
   
   /**
    * A reference to the Zip File to be extracted
    */
   private File mZipFile;

   /**
    * The directory to which the Zip File will be extracted to
    */
   private String mExtractToDir;
   
   /**
    * Constructor for the <code>UnZipHandler</code> class.
    *
    * @param iZipFileName Name and path of the <code>.zip</code> file.
    * @param iTargetDirName Name and path of the directory to extract the 
    *                       contents of the Zip File to.
    */
   public UnZipHandler(String iZipFileName, String iTargetDirName )
   {
      setFile( iZipFileName );
      setTargetDirectory( iTargetDirName );
   }

   /**
    * Set the name and location of the Zip File to be extracted.
    *
    * @param iFileName Name and location of the Zip File to be extracted.
    */
   private void setFile(String iFileName)
   {
      try
      {
         mZipFile = new File( iFileName ); 
      }
      catch ( NullPointerException npe )
      {
    	  logger.error(npe);
      }
   }

   /**
    * Set the target directory of the extracted contents of the Zip File.
    *
    * @param iTargetDirPath The target directory of the extracted contents of 
    *                       the Zip File.
    *
    */
   private void setTargetDirectory( String iTargetDirPath )
   {
      try
      {
         mExtractToDir = iTargetDirPath;
      }
      catch ( NullPointerException npe )
      {
         logger.error(npe);
      }
   }

   /**
    * Returns the target directory of the extracted contents of the Zip 
    * File.
    *
    * @return target The target directory of the extracted contents of the Zip 
    *                File.
    */
   public String getTargetDirectory()
   {
      return mExtractToDir;
   }

   /**
    * Extracts the Zip File into the destination directory
    *
    */
   public boolean extract()
   {
      boolean result = true;
      String fileName = "";
      String destFileName = "";
      InputStream in = null;
      OutputStream out = null;      

      // Create a byte buffer
      byte[] buffer = new byte[BUFFER_SIZE];

      try
      {  
    	 ZipFile archive;
    	 
       // protect against the case that the user only installs the JRE
       // with no language support
       try
    	 {
    	    archive = new ZipFile( mZipFile, "CP437");
    	 }
    	 catch ( ZipException ZE )
    	 {
    		 archive = new ZipFile( mZipFile );
    	 }
    	 
         for ( Enumeration e = archive.getEntries(); e.hasMoreElements(); )
         {
            // Get the next entry in the Zip File
            ZipEntry entry = (ZipEntry)e.nextElement();

            if ( !entry.isDirectory() )
            {
               fileName = entry.getName();
               fileName = fileName.replace('/', File.separatorChar);

               destFileName = mExtractToDir + fileName;

               File destFile = new File(destFileName);

               // Create the destination path, if needed
               String parent = destFile.getParent();
               if ( parent != null )
               {
                  File parentFile = new File(parent);
                  if ( !parentFile.exists() )
                  {
                     // Create the chain of sub-directories to the file
                     parentFile.mkdirs();
                  }
               }

               // Get a stream of the archive entry's bytes
               in = archive.getInputStream(entry);

               // Open a stream to the destination file
               out = new FileOutputStream(destFileName);

               // Repeat reading into buffer and writing buffer to file,
               // until done.  Count will always be # bytes read, until
               // EOF when it is -1.
               int count;
               while ( (count = in.read(buffer)) != -1 )
               {
                  out.write(buffer, 0, count );
               }
               
               // Close the input stream and output stream
               in.close();
               out.close();
            }
         }
         archive.close();
      }
      catch ( ZipException ze )
      {
          result = false;
          if ( DebugIndicator.ON )
          {
        	  logger.error(ze); 
          }          
      }
      catch ( NullPointerException npe )
      {
         result = false;
         if ( DebugIndicator.ON )
         {
        	 logger.error(npe); 
         }
      }
      catch ( IOException ioe )
      {
         result = false;
         if ( DebugIndicator.ON )
         {
        	 logger.error(ioe); 
         }
      }
      catch ( SecurityException se )
      {
         result = false;
         if ( DebugIndicator.ON )
         {
        	 logger.error(se); 
         }
      }
      catch ( Exception e )
      {
         result = false;
         if ( DebugIndicator.ON )
         {
        	 logger.error(e); 
         }
      }
      finally
      {
         // In case an exception is thrown prior to closing the input stream
         // and output stream, close the streams
         
         // Check to make sure the input stream has not been closed
         if (in != null)
         {
            try
            { 
               in.close();
            }
            catch (IOException e)
            {
            	logger.error(e);
            }
         }

         // Check to make sure the output stream has not been closed
         if (out != null)
         {
            try
            {
               out.close();
            }
            catch (IOException e)
            {
            	logger.error(e);
            }
         }        
      } // end finally
      
      return result;
      
   }  // end extract()
}