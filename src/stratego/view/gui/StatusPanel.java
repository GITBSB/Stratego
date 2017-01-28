package stratego.view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import stratego.controller.IController;
import stratego.entities.impl.GameStatus;
import stratego.util.observer.IObserver;

public class StatusPanel extends JPanel implements IObserver {
	private static final long serialVersionUID = -6797174504280439649L;
	IController controller;
	JLabel statusLabel;
	JLabel playerLabel;
	JLabel currentPlayerLabel;

	public StatusPanel(final IController controller) {
		this.controller = controller;
		controller.addObserver(this);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		playerLabel = new JLabel("Player: ");
		playerPanel.add(playerLabel);

		currentPlayerLabel = new JLabel("");
		playerPanel.add(currentPlayerLabel);
		
		this.add(playerPanel);

		this.add(new JLabel("          "));

		statusLabel = new JLabel("");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

		this.add(statusLabel);

		this.add(new JLabel("          "));
		JButton actionButton = new JButton("Fertig!");
		actionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				controller.readyContinue();
			}
		});
		this.add(actionButton);
	}

	public void update(GameStatus e) {
		statusLabel.setText(controller.getStatus());
		statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
		if (controller.getGameStatus() != GameStatus.NOT_STARTED) {
			currentPlayerLabel.setText(controller.getPlayer().getPlayerName());
		}
	}
}
