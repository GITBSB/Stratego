package stratego.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import stratego.controller.IController;
import stratego.entities.impl.GameStatus;
import stratego.entities.impl.figures.Aufklärer;
import stratego.entities.impl.figures.Bombe;
import stratego.entities.impl.figures.Fahne;
import stratego.entities.impl.figures.General;
import stratego.entities.impl.figures.Hauptmann;
import stratego.entities.impl.figures.Leutnant;
import stratego.entities.impl.figures.Major;
import stratego.entities.impl.figures.Marschall;
import stratego.entities.impl.figures.Mineur;
import stratego.entities.impl.figures.Oberst;
import stratego.entities.impl.figures.Spion;
import stratego.entities.impl.figures.Unteroffizier;
import stratego.util.collection.Position;
import stratego.util.observer.IObserver;

@SuppressWarnings("serial")
public class GridPlayGround extends JPanel implements IObserver {
	private JPanel gameBoard;
	private IController controller;
	private Logger logger = Logger.getLogger(GridPlayGround.class);

	private JButton[][] gridButton = new JButton[GRID_SIZE][GRID_SIZE];
	private ArrayList<Integer> idx_Button_List = new ArrayList<Integer>();
	private int windowHight;

	private JButton[] figureButton = new JButton[FIG_PANEL];
	private String[] figures = { Fahne.FAHNE, Bombe.BOMBE, Spion.SPION, Aufklärer.AUFKLÄRER, Mineur.MINEUR,
			Unteroffizier.UNTEROFFIZIER, Leutnant.LEUTNANT, Hauptmann.HAUPTMANN, Major.MAJOR, Oberst.OBERST,
			General.GENERAL, Marschall.MARSCHALL, GridPlayGround.DELETE };
	private BufferedImage img;
	
	private JPanel figurePanel;

	private static final String COLUMN_LABEL = "ABCDEFGHIJ";
	private static final String DISTANCE_ROW__LABEL = "       ";
	private static final int GRID_SIZE = 10;
	private static final int FIG_PANEL = 13;
	private static final String DELETE = "delete";
	private static final int NOTHING_MARKED = 100;
	private static final String JPEG = ".jpg";

	public GridPlayGround(IController controller) {
		this.controller = controller;
		controller.addObserver(this);

		GridListener gridListener = new GridListener();
		FigurePanelListener figurePanelListener = new FigurePanelListener();

		try {
			img = ImageIO.read(getClass().getResource("/StrategoImages/Stratego_Board.jpg"));
		} catch (Exception e) {
			logger.error("Error:", e);
		}

		gameBoard = new JPanel() {
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				Dimension prefSize = null;
				Component c = getParent();
				if (c == null) {
					prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
				} else if (c != null && c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
					prefSize = c.getSize();
				} else {
					prefSize = d;
				}
				int w = (int) prefSize.getWidth();
				
				int h = (int) prefSize.getHeight();
				// the smaller of the two sizes
				int s = (w > h ? h : w);
				windowHight = s;
				return new Dimension(s, s);
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		gameBoard.setLayout(new GridLayout(0, GRID_SIZE + 1));
		gameBoard.setBorder(new CompoundBorder(new EmptyBorder(GRID_SIZE, GRID_SIZE, GRID_SIZE, GRID_SIZE),
				new LineBorder(Color.GRAY)));
		gameBoard.setBackground(Color.LIGHT_GRAY);

		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				JButton tmpFigureButton = new JButton();
				tmpFigureButton.setMargin(buttonMargin);
				// tmpFigureButton.setBackground(Color.darkGray);
				StretchIcon ImageFigureButton = new StretchIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
//				StretchIcon ImageFigureButton = null;
				tmpFigureButton.setIcon(ImageFigureButton);
				gridButton[j][i] = tmpFigureButton;
				gridButton[j][i].setOpaque(false);
				gridButton[j][i].setContentAreaFilled(false);
				gridButton[j][i].addActionListener(gridListener);
			}
		}
		gridButton[2][4].setEnabled(false);
		gridButton[3][4].setEnabled(false);
		gridButton[2][5].setEnabled(false);
		gridButton[3][5].setEnabled(false);
		gridButton[6][4].setEnabled(false);
		gridButton[7][4].setEnabled(false);
		gridButton[6][5].setEnabled(false);
		gridButton[7][5].setEnabled(false);

		gameBoard.add(new JLabel(""));
		for (int i = 0; i < GRID_SIZE; i++) {
			gameBoard.add(new JLabel(COLUMN_LABEL.substring(i, i + 1), SwingConstants.CENTER));
		}

		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				if (j == 0) {
					gameBoard.add(new JLabel(DISTANCE_ROW__LABEL + i));
				}
				gameBoard.add(gridButton[j][i]);
			}
		}
		this.add(gameBoard);

		// figure panel
		figurePanel = new JPanel(new GridLayout(0, 1)) {
			@Override
			public final Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				return new Dimension((int) (d.getWidth() * 1.7), windowHight);
			}
		};

		figurePanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(Color.GRAY)));
		figurePanel.setBackground(Color.LIGHT_GRAY);

		for (int i = 0; i < FIG_PANEL - 1; i++) {
			StretchIcon ImageFigureButton = new StretchIcon(getClass().getResource("/StrategoImages/" + figures[i] + JPEG)); //figures[i]
			figureButton[i] = new JButton();
			figureButton[i].setIcon(ImageFigureButton);
			figureButton[i].addActionListener(figurePanelListener);

			figurePanel.add(figureButton[i]); // linepanel
		}
		// delete Button
		int del_idx = figures.length - 1;
		figureButton[del_idx] = new JButton("Delete");
		figureButton[del_idx].addActionListener(figurePanelListener);
		figurePanel.add(figureButton[del_idx]);

		figurePanel.setVisible(false);
		
		this.add(figurePanel, BorderLayout.EAST);

		JPanel navPanel = new JPanel();
		JButton button = new JButton("Action");
		button.setVisible(false);
		navPanel.add(button);

		this.add(navPanel, BorderLayout.SOUTH);
	}

	public void update(GameStatus gameStatus) {
		// Anzeige gameboard
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				StretchIcon icon = null;
				String fieldId;
				fieldId = controller.getFieldId(new Position(i, j));

				if (fieldId != null) {
					icon = new StretchIcon(getClass().getResource("/StrategoImages/" + fieldId + JPEG));
				}
				gridButton[j][i].setIcon(icon);
			}
		}

		// Anzeige figurePanel
		if (controller.getGameStatus() == GameStatus.SET_FIGURES) {
			figurePanel.setVisible(true);
			for (int i = 0; i < FIG_PANEL - 1; i++) {
				int anzFigure = controller.getPlayer().getFigureSet().getfigure(figures[i]).getAnz();
				figureButton[i].setText(Integer.toString(anzFigure));
				figureButton[i].setFont(new Font("Arial", Font.ITALIC, 30));
				figureButton[i].setForeground(Color.orange);
			}
		} else {
			figurePanel.setVisible(false);
		}
	}

	private class GridListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (controller.getGameStatus() == GameStatus.FIGHT) {
				int idx_i = 100;
				int idx_j = 100;
				for (int i = 0; i < GRID_SIZE; i++) {
					for (int j = 0; j < GRID_SIZE; j++) {
						if (e.getSource() == gridButton[j][i]) {
							idx_i = i;
							idx_j = j;
							gridButton[j][i].setBackground(Color.green);
						}
					}
				}
				idx_Button_List.add(idx_i);
				idx_Button_List.add(idx_j);

				if (idx_Button_List.size() == 4) {
					// wenn zweiter Klick eigene Figur -> reset
					if (controller.getField(new Position(idx_i, idx_j)).getFigure().getPlayer() == controller
							.getPlayer()) {
						clearMoves();
						idx_Button_List.add(idx_i);
						idx_Button_List.add(idx_j);
						gridButton[idx_j][idx_i].setBackground(Color.green);
						System.out.println("reset: " + idx_i + idx_j);

					} else {
						controller.move_attack(new Position(idx_Button_List.get(0), idx_Button_List.get(1)),
								new Position(idx_Button_List.get(2), idx_Button_List.get(3)));
						clearMoves();
					}
				}
			} else if (controller.getGameStatus() == GameStatus.SET_FIGURES) {
				String figureToSet = controller.getFigureToSet();
				if (figureToSet != null) {
					Position position = new Position();
					for (int i = 0; i < GRID_SIZE; i++) {
						for (int j = 0; j < GRID_SIZE; j++) {
							if (e.getSource() == gridButton[j][i]) {
								position.setX(i);
								position.setY(j);
							}
						}
					}

					if (figureToSet == GridPlayGround.DELETE) {
						controller.deleteFigure(position);
					} else {
						controller.setFigure(position, figureToSet);
					}

				}
			}
		}

		private void clearMoves() {
			gridButton[idx_Button_List.get(1)][idx_Button_List.get(0)].setBackground(null);
			gridButton[idx_Button_List.get(3)][idx_Button_List.get(2)].setBackground(null);
			idx_Button_List.clear();
		}
	}

	private class FigurePanelListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < FIG_PANEL; i++) {
				if (e.getSource() == figureButton[i]) {

					int lastFigureId = getLastMarkedFigureButtonId();
					if (lastFigureId != NOTHING_MARKED) {
						figureButton[lastFigureId].setBackground(null);
					}

					controller.setFigureToSet(figures[i]);
					figureButton[i].setBackground(Color.GREEN);

				}
			}
		}

		private int getLastMarkedFigureButtonId() {
			String lastFigure = controller.getFigureToSet();
			for (int i = 0; i < figures.length; i++) {
				if (lastFigure == figures[i]) {
					return i;
				}
			}
			return NOTHING_MARKED;
		}
	}
}
