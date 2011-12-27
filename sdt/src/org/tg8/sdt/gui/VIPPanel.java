package org.tg8.sdt.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tg8.sdt.domain.SDTDomainLogic;
import org.tg8.sdt.domain.Student;

public class VIPPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7827731407549978931L;

	private static final String GET_VIP = "getVip";
	
	private JLabel vipLabel;
	
	VIPPanel () {
		super();
		this.setLayout(new BorderLayout());
		
		JPanel vipButtonPanel = new JPanel();
		JButton getVIPButton = new JButton("Get tonight's VIP");
		getVIPButton.addActionListener(this);
		getVIPButton.setActionCommand(GET_VIP);
		vipButtonPanel.add(getVIPButton);
		
		JPanel vipPanel = new JPanel();
		vipPanel.add(getVIPLabel());
		
		this.add(vipButtonPanel, BorderLayout.PAGE_START);
		this.add(vipPanel, BorderLayout.CENTER);
		
	}
	
	private JLabel getVIPLabel () {
		if (this.vipLabel == null) {
			this.vipLabel = new JLabel();
		}
		return this.vipLabel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(GET_VIP)) {
			List<Student> students = SDTDomainLogic.getSDTDomainLogic().initializeStudentAttendanceList();
			if (students.size() == 0) {
				this.getVIPLabel().setText("No attendance recorded tonight.");
			}
			else {
				int randomNumber = new Random().nextInt(students.size());
				Student vip = students.get(randomNumber);
				this.getVIPLabel().setText(vip.getFirstName() + " " + vip.getLastName());
			}
		}
		
	}
	
}
