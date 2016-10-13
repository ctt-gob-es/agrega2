/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.pode.soporte.seguridad.openId.providers.openid;


import org.acegisecurity.AuthenticationException;


/**
 * Indicates that OpenID authentication was cancelled
 *
 * @author Robin Bramley, Opsera Ltd
 * @version $Id: AuthenticationCancelledException.java 2518 2008-01-27 02:57:57Z raykrueger $
 */
public class AuthenticationCancelledException extends AuthenticationException {
    //~ Constructors ===================================================================================================

    public AuthenticationCancelledException(String msg) {
        super(msg);
    }

    public AuthenticationCancelledException(String msg, Throwable t) {
        super(msg, t);
    }
}
