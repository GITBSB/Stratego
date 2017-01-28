package stratego.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import stratego.controller.IController;

public class MenuBar extends JMenuBar {

	// JMenuBar
	private static final long serialVersionUID = 4033280135064819188L;
	private JMenu fileMenu;
	private JMenuItem newMenuItem, quitMenuItem, optionPlayerItem;

	private JMenu pMenu;
	IController controller;
	JFrame blackjackframe;
	PlayerOptions playerOptions;
	int dialogButton = JOptionPane.YES_NO_OPTION;

	public MenuBar(final IController controller) {
		playerOptions = new PlayerOptions(controller);
		// fileMenu
		fileMenu = new JMenu("Datei ");
		fileMenu.setMnemonic(KeyEvent.VK_D);

		pMenu = new JMenu(" Optionen");

		// newMenuItem
		newMenuItem = new JMenuItem("Neues Spiel");
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to start a new game?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					controller.startNewGame();
				}
			}
		});
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(newMenuItem);

		// quitMenuItem
		quitMenuItem = new JMenuItem("Beenden");
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(quitMenuItem);
		fileMenu.add(new JSeparator());

		// optionPlayerItem
		optionPlayerItem = new JMenuItem("Spieler Optionen");
		optionPlayerItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playerOptions.shownewplayerDialog();
			}
		});
		optionPlayerItem.setMnemonic(KeyEvent.VK_O);
		optionPlayerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		pMenu.add(optionPlayerItem);
		this.add(fileMenu);
		this.add(pMenu);
	}

}