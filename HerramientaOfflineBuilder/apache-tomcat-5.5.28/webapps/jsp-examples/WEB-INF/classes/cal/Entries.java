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
package cal;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.*;

public class Entries {

  private Hashtable entries;
  private static final String[] time = {"8am", "9am", "10am", "11am", "12pm", 
					"1pm", "2pm", "3pm", "4pm", "5pm", "6pm",
					"7pm", "8pm" };
  public static final int rows = 12;

  public Entries () {   
   entries = new Hashtable (rows);
   for (int i=0; i < rows; i++) {
     entries.put (time[i], new Entry(time[i]));
   }
  }

  public int getRows () {
    return rows;
  }

  public Entry getEntry (int index) {
    return (Entry)this.entries.get(time[index]);
  }

  public int getIndex (String tm) {
    for (int i=0; i<rows; i++)
      if(tm.equals(time[i])) return i;
    return -1;
  }

  public void processRequest (HttpServletRequest request, String tm) {
    int index = getIndex (tm);
    if (index >= 0) {
      String descr = request.getParameter ("description");
      ((Entry)entries.get(time[index])).setDescription (descr);
    }
  }

}













