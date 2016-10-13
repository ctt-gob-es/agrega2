/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/******************************************************************************
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
package es.pode.adl.ims.server.bucket;

/**
 * Enumeration of possible success status types that describe the status of an 
 * SSP bucket.<br><br>
 *
 * <strong>Filename:</strong> SuccessStatus.java<br><br>
 *
 * <strong>Description:</strong><br>
 * Enumeration of possible success status types that describe the status of
 * an SSP bucket.  The values consist of FAILURE, MINIMUM, REQUESTED, and
 * NONE_REQUESTED.
 *
 * <br><br>
 *
 * <strong>Design Issues:</strong><br>
 * This implementation is intended to be used by the SCORM 2004 3rd Edition
 * Sample RTE. <br>
 * <br>
 *
 * <strong>Implementation Issues:</strong><br><br>
 *
 * <strong>Known Problems:</strong><br><br>
 *
 * <strong>Side Effects:</strong><br><br>
 *
 * <strong>References:</strong><br>
 * <ul>
 *     <li>IMS SSP Specification
 *     <li>SCORM 2004 3rd Edition
 * </ul>
 *
 * @author ADL Technical Team
 */
public class SuccessStatus
{
   /**
    * Indicates that the SCO's allocation request has failed.
    * 
    * <br>Failure
    *
    * <br><b>0</b>
    *
    * <br>[SCORM SSP SUBSYSTEM CONSTANT]
    */
   public static final int FAILURE = 0;

   /**
    * Indicates that the LMS allocated the minimum size for the bucket.
    *
    * <br>Minimum
    *
    * <br><b>1</b>
    *
    * <br>[SCORM SSP SUBSYSTEM CONSTANT]
    */
   public static final int MINIMUM = 1;

   /**
    * Indicates that the LMS allocated the requested size for the bucket.
    *
    * <br>Requested
    *
    * <br><b>2</b>
    *
    * <br>[SCORM SSP SUBSYSTEM CONSTANT]
    */
   public static final int REQUESTED = 2;

   /**
    * Indicates that no allocation request has been made for the size of the 
    * bucket.
    *
    * <br>None Requested
    *
    * <br><b>3</b>
    *
    * <br>[SCORM SSP SUBSYSTEM CONSTANT]
    */
   public static final int NONE_REQUESTED = 3;
}