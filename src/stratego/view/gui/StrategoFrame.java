package stratego.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;

import stratego.controller.IController;
import stratego.entities.impl.GameStatus;
import stratego.util.observer.IObserver;

public class StrategoFrame extends JFrame implements IObserver {
	private static final long serialVersionUID = 3692448238414694547L;
	private Logger logger = LogManager.getLogger(StrategoFrame.class);

	@Inject
	public StrategoFrame(IController controller) throws IOException {
		// setLookAndFeel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			logger.error("Error:", e);
		}

		this.setTitle("Stratego");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller.addObserver(this);
		Container contentPane = this.getContentPane();

		this.setJMenuBar(new MenuBar(controller));

		contentPane.add(new GridPlayGround(controller));

		contentPane.add(new StatusPanel(controller), BorderLayout.BEFORE_FIRST_LINE);

		this.setSize(1500, 1000);
		this.setResizable(true);
		this.setVisible(true);
	}

	public void update(GameStatus e) {
		// TODO
	}
}
