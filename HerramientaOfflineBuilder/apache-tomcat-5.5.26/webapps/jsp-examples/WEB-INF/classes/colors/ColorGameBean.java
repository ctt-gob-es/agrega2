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
package colors;

import javax.servlet.http.*;

public class ColorGameBean {

    private String background = "yellow";
    private String foreground = "red";
    private String color1 = foreground;
    private String color2 = background;
    private String hint = "no";
    private int attempts = 0;
	private int intval = 0;
    private boolean tookHints = false;

    public void processRequest(HttpServletRequest request) {

	// background = "yellow";
	// foreground = "red";

	if (! color1.equals(foreground)) {
	    if (color1.equalsIgnoreCase("black") ||
			color1.equalsIgnoreCase("cyan")) {
			background = color1;
		}
	}

	if (! color2.equals(background)) {
	    if (color2.equalsIgnoreCase("black") ||
			color2.equalsIgnoreCase("cyan")) {
			foreground = color2;
	    }
	}

	attempts++;
    }

    public void setColor2(String x) {
	color2 = x;
    }

    public void setColor1(String x) {
	color1 = x;
    }

    public void setAction(String x) {
	if (!tookHints)
	    tookHints = x.equalsIgnoreCase("Hint");
	hint = x;
    }

    public String getColor2() {
	 return background;
    }

    public String getColor1() {
	 return foreground;
    }

    public int getAttempts() {
	return attempts;
    }

    public boolean getHint() {
	return hint.equalsIgnoreCase("Hint");
    }

    public boolean getSuccess() {
	if (background.equalsIgnoreCase("black") ||
	    background.equalsIgnoreCase("cyan")) {
	
	    if (foreground.equalsIgnoreCase("black") ||
		foreground.equalsIgnoreCase("cyan"))
		return true;
	    else
		return false;
	}

	return false;
    }

    public boolean getHintTaken() {
	return tookHints;
    }

    public void reset() {
	foreground = "red";
	background = "yellow";
    }

    public void setIntval(int value) {
	intval = value;
	}

    public int getIntval() {
	return intval;
	}
}

