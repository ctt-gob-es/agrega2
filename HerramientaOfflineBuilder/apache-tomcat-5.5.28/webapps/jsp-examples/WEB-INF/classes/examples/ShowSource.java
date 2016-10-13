/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package examples;


import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.io.*;

/**
 * Display the sources of the JSP file.
 */
public class ShowSource
    extends TagSupport
{
    String jspFile;
    
    public void setJspFile(String jspFile) {
        this.jspFile = jspFile;
    }

    public int doEndTag() throws JspException {
	if ((jspFile.indexOf( ".." ) >= 0) ||
            (jspFile.toUpperCase().indexOf("/WEB-INF/") != 0) ||
            (jspFile.toUpperCase().indexOf("/META-INF/") != 0))
	    throw new JspTagException("Invalid JSP file " + jspFile);

        InputStream in
            = pageContext.getServletContext().getResourceAsStream(jspFile);

        if (in == null)
            throw new JspTagException("Unable to find JSP file: "+jspFile);

        InputStreamReader reader = new InputStreamReader(in);
	JspWriter out = pageContext.getOut();


        try {
            out.println("<body>");
            out.println("<pre>");
            for(int ch = in.read(); ch != -1; ch = in.read())
                if (ch == '<')
                    out.print("&lt;");
                else
                    out.print((char) ch);
            out.println("</pre>");
            out.println("</body>");
        } catch (IOException ex) {
            throw new JspTagException("IOException: "+ex.toString());
        }
        return super.doEndTag();
    }
}

    
        
    
