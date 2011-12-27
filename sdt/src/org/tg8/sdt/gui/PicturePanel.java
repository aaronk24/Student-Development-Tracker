package org.tg8.sdt.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class PicturePanel extends JPanel {

	private BufferedImage picture;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3416466707363233709L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.getGraphics().drawImage(picture, 0, 0, 218, 144, null);
		this.setPreferredSize(new Dimension(220, 150));
	}
	
	void setPicture (BufferedImage newPicture) {
		this.picture = newPicture;
		this.repaint();
	}
}
