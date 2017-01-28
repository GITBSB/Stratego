package stratego.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import stratego.controller.IController;

public class PlayerOptions extends JDialog implements ActionListener {

	private static final long serialVersionUID = 3857209854964141461L;
	private JTextField playerATextField = new JTextField(10);
	private JTextField playerBTextField = new JTextField(10);

	private JButton saveButton = new JButton("Speichern");
	private JButton quitButton = new JButton("Abbrechen");
	private IController controller;

	public PlayerOptions(IController controller) {
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(500, 300));
		this.setTitle("Spieler Optionen");

		this.controller = controller;
		JPanel panel1 = new JPanel();
		JLabel spielerALabel = new JLabel("Spieler A:");
		spielerALabel.setForeground(Color.RED);
		panel1.add(spielerALabel);
		panel1.add(playerATextField);

		JPanel panel2 = new JPanel();
		JLabel spielerBLabel = new JLabel("Spieler B:");
		spielerBLabel.setForeground(Color.BLUE);
		panel2.add(spielerBLabel);
		panel2.add(playerBTextField);

		JPanel panel3 = new JPanel();
		panel3.add(saveButton);
		panel3.add(quitButton);
		saveButton.addActionListener(this);
		quitButton.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		this.add(panel);
		setResizable(false);
		pack();
	}

	public void shownewplayerDialog() {
		if (controller.getPlayer() != null) {
			playerATextField.setText(controller.getPlayerFromPlayerList(0).getPlayerName());
			playerBTextField.setText(controller.getPlayerFromPlayerList(1).getPlayerName());
		}
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			String playerA = playerATextField.getText();
			String playerB = playerBTextField.getText();
			if (playerA.equals("") || playerB.equals("")) {
				JOptionPane.showMessageDialog(this, "Namen dürfen nicht leer sein", "Warnung",
						JOptionPane.WARNING_MESSAGE);
			} else {
				controller.getPlayerFromPlayerList(0).setPlayerName(playerATextField.getText());
				controller.getPlayerFromPlayerList(1).setPlayerName(playerBTextField.getText());
				setVisible(false);
				controller.notifyObserversC(null);
			}
		} else {
			setVisible(false);
		}
	}
}
