/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Set;

public interface OBDADataSource extends Cloneable, Serializable {

	public abstract void setParameter(String parameter_uri, String value);

	public abstract URI getSourceID();

	public abstract void setNewID(URI newid);

	public abstract String getParameter(String parameter_uri);

	public abstract Set<Object> getParameters();

	public abstract void setEnabled(boolean enabled);

	public abstract boolean isEnabled();

	public abstract void setRegistred(boolean registred);

	public abstract boolean isRegistred();
	
	public Object clone();
}
