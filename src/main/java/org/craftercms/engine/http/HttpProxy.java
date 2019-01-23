/*
 * Copyright (C) 2007-2019 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.engine.http;

import org.craftercms.engine.exception.HttpProxyException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Represents a proxy to a HTTP service: a request is issued to the service according to the given servlet request and the response
 * from the service is written back to the given servlet response.
 *
 * @author Alfonso Vásquez
 */
public interface HttpProxy {

    void proxyGet(String url, HttpServletRequest request, HttpServletResponse response) throws HttpProxyException;
    void proxyPost(String url, HttpServletRequest request, HttpServletResponse response) throws HttpProxyException;

}
