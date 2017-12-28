import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener{

	private BufferedImage blocks;

	private final int blockSize = 30;

	private final int boardWidth = 10, boardHeight = 20;

	private int[][] board = new int[boardWidth][boardHeight];

	private Shape[] shapes = new Shape[7];

	private Shape currentShape;

	private Timer timer;

	private final int FPS = 60;

	private final int delay = 1000 / FPS;

	public Board() {
		try {
			blocks = ImageIO.read(Board.class.getResource("/tiles.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});

		//shapes

		shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][] {
			{1, 1, 1, 1} //I-Shape
		}, this);

		shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][] {
			{1, 1, 0},
			{0, 1, 1}//Z-Shape
		}, this);

		shapes[2] = new Shape(blocks.getSubimage(blockSize * 2, 0, blockSize, blockSize), new int[][] {
			{0, 1, 1},
			{1, 1, 0}//S-Shape
		}, this);

		shapes[3] = new Shape(blocks.getSubimage(blockSize * 3, 0, blockSize, blockSize), new int[][] {
			{1, 1, 1},
			{0, 0, 1}//J-Shape
		}, this);

		shapes[4] = new Shape(blocks.getSubimage(blockSize * 4, 0, blockSize, blockSize), new int[][] {
			{1, 1, 1},
			{1, 0, 0}//L-Shape
		}, this);

		shapes[5] = new Shape(blocks.getSubimage(blockSize * 5, 0, blockSize, blockSize), new int[][] {
			{1, 1, 1},
			{0, 1, 0}//T-Shape
		}, this);

		shapes[6] = new Shape(blocks.getSubimage(blockSize * 6, 0, blockSize, blockSize), new int[][] {
			{1, 1},
			{1, 1}//O-Shape
		}, this);

		currentShape = shapes[4];

	}

	public void update() {
		currentShape.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		currentShape.render(g);

		for (int i = 0; i < boardHeight; i++) {
			g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
		}

		for (int j = 0; j < boardWidth; j++) {
			g.drawLine(j * blockSize, 0, j * blockSize, boardHeight * blockSize);;
		}
	}

	public int getBlockSize() {
		return blockSize;
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
