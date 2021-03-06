/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.protege4.gui.preferences;


import java.awt.BorderLayout;

import it.unibz.krdb.obda.owlrefplatform.core.QuestPreferences;
import it.unibz.krdb.obda.protege4.panels.ConfigPanel;

import org.protege.editor.owl.ui.preferences.OWLPreferencesPanel;

public class OBDAOWLReformulationPlatformConfigPanel extends OWLPreferencesPanel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2017399622537704497L;
	private QuestPreferences preference = null;
	private ConfigPanel configPanel = null;
	
	@Override
	public void applyChanges() {
		// Do nothing.
	}

	@Override
	public void initialise() throws Exception {
		preference = (QuestPreferences)
			getEditorKit().get(QuestPreferences.class.getName());
		
		this.setLayout(new BorderLayout());
		configPanel = new ConfigPanel(preference);
		this.add(configPanel,BorderLayout.CENTER);
	}

	@Override
	public void dispose() throws Exception {
		// Do nothing.
	}
}
