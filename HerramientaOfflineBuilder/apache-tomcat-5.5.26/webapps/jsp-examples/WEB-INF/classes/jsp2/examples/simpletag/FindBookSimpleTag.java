/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

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


package jsp2.examples.simpletag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.HashMap;
import jsp2.examples.BookBean;

/**
 * SimpleTag handler that pretends to search for a book, and stores
 * the result in a scoped variable.
 */
public class FindBookSimpleTag extends SimpleTagSupport {
    private String var;
    
    private static final String BOOK_TITLE = "The Lord of the Rings";
    private static final String BOOK_AUTHOR = "J. R. R. Tolkein";
    private static final String BOOK_ISBN = "0618002251";

    public void doTag() throws JspException {
        BookBean book = new BookBean( BOOK_TITLE, BOOK_AUTHOR, BOOK_ISBN );
        getJspContext().setAttribute( this.var, book );
    }

    public void setVar( String var ) {
	this.var = var;
    }
}
