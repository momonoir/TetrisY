import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shape {

	private BufferedImage block;
	private int[][] coords;
	private Board board;
	private int deltaX = 0;

	public Shape(BufferedImage block, int[][] coords, Board board) {
		this.block = block;
		this.coords = coords;
		this.board = board;
	}

	private void update() {

	}

	public void render(Graphics g) {

		for (int row = 0; row < coords.length; row++) {
			for (int col = 0; col < coords[row].length; col++) {
				if (coords[row][col] != 0) {
					g.drawImage(block, col * board.getBlockSize(), row * board.getBlockSize(), null);
				}

			}

		}
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
}