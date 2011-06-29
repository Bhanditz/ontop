/*
 * Copyright 2011 OBDA-Lib Team
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/*
 * DatasourceListSelector.java
 * 
 * Created on Feb 23, 2011, 2:32:23 PM
 */

package it.unibz.krdb.obda.gui.swing.panel;

import it.unibz.krdb.obda.gui.swing.utils.DatasourceSelectorListener;
import it.unibz.krdb.obda.model.DataSource;
import it.unibz.krdb.obda.model.DatasourcesController;
import it.unibz.krdb.obda.model.DatasourcesControllerListener;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * A combo box component to select a data source.
 * 
 * @author Josef Hardi <josef.hardi@gmail.com>
 */
public class DatasourceSelector extends javax.swing.JPanel implements DatasourcesControllerListener {

	/**
	 * 
	 */
	private static final long					serialVersionUID	= 7644402239114670390L;

	private DataSource							previousSource;

	private DefaultComboBoxModel				cboModelDatasource;
	private DatasourceCellRenderer				cboRendererDatasource;

	private Vector<DatasourceSelectorListener>	listeners;

	DatasourcesController						dsController		= null;

	/** Creates new form DatasourceListSelector */
	public DatasourceSelector(DatasourcesController dsController) {
		this.dsController = dsController;
		this.setDatasourceController(dsController);

		listeners = new Vector<DatasourceSelectorListener>();
		initComponents();

	}

	public void setDatasourceController(DatasourcesController dsController) {
		this.dsController.removeDatasourceControllerListener(this);
		this.dsController = dsController;
		this.dsController.addDatasourceControllerListener(this);
		initSources();

	}

	public void initSources() {

		Vector<DataSource> vecDatasource = new Vector<DataSource>(dsController.getAllSources());
		if (cboModelDatasource == null) {
			cboModelDatasource = new DefaultComboBoxModel(vecDatasource.toArray());
			cboRendererDatasource = new DatasourceCellRenderer();
		}

		cboModelDatasource.removeAllElements();
		for (DataSource ds : vecDatasource) {
			cboModelDatasource.addElement(ds);
		}
//		cboModelDatasource.setSelectedItem(-1);
//		this.cboDatasource.setSelectedIndex(-1);
	}

	public DataSource getSelectedDataSource() {
		return (DataSource) cboDatasource.getSelectedItem();

	}

	public void addDatasourceListListener(DatasourceSelectorListener l) {
		listeners.add(l);
	}

	@Override
	public void datasourceAdded(DataSource source) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cboDatasource.getModel();
		model.addElement(source);
	}

	@Override
	public void datasourceDeleted(DataSource source) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cboDatasource.getModel();
		model.removeElement(source);
	}

	@Override
	public void datasourceUpdated(String oldSourceUri, DataSource newSource) {
		// TODO Change the interface??
	}

	@Override
	public void alldatasourcesDeleted() {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cboDatasource.getModel();
		model.removeAllElements();
	}

	@Override
	public void datasourcParametersUpdated() {
		// TODO Change the interface??
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		cboDatasource = new JComboBox();

		setLayout(new BorderLayout());

		cboDatasource.setModel(cboModelDatasource);
		cboDatasource.setRenderer(cboRendererDatasource);
		cboDatasource.setSelectedIndex(-1);
		cboDatasource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cboDatasourceSelected(evt);
			}
		});
		add(cboDatasource, BorderLayout.CENTER);
	}// </editor-fold>//GEN-END:initComponents

	private void cboDatasourceSelected(ActionEvent evt) {// GEN-FIRST:event_cboDatasourceSelected
		JComboBox cb = (JComboBox) evt.getSource();
		DataSource currentSource = (DataSource) cb.getSelectedItem();
		for (DatasourceSelectorListener listener : listeners) {
			listener.datasourceChanged(previousSource, currentSource);
		}
		// After the listeners have been notified, update the previousSource
		// to be as the same as the currentSource, so that we have a historical
		// record of data sources.
		previousSource = (DataSource) cb.getSelectedItem();
	}// GEN-LAST:event_cboDatasourceSelected

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JComboBox	cboDatasource;

	// End of variables declaration//GEN-END:variables

	private class DatasourceCellRenderer extends JLabel implements ListCellRenderer {

		/**
 * 
 */
		private static final long	serialVersionUID	= -8521494988522078279L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

			if (value == null) {
				setText("<Select a datasource>");
			} else {
				DataSource datasource = (DataSource) value;
				String datasourceUri = datasource.getSourceID().toString();
				setText(datasourceUri);
			}

			return this;
		}
	}
}
