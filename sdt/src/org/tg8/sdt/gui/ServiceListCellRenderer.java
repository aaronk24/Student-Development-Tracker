package org.tg8.sdt.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import org.tg8.sdt.domain.GateService;


public class ServiceListCellRenderer extends DefaultListCellRenderer {

	/**
	 *
	 */
	private static final long serialVersionUID = -2300324366536416791L;

	//Allow services to be displayed by a JList
	public Component getListCellRendererComponent(JList arg0, Object service,
			int arg2, boolean arg3, boolean arg4) {
		GateService s = (GateService) service;
		String serviceDisplay = s.getServiceDate().toString();
		return super.getListCellRendererComponent(arg0, serviceDisplay, arg2, arg3, arg4);
	}
	
}
