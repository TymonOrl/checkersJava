package boardPackage;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

	Board board;

	public BoardPanel() {
		this.setLayout(new BorderLayout());
		board = new Board(8);

		this.add(board, BorderLayout.CENTER);
	}

	public BoardPanel(Board inBoard){
		board = inBoard;
		this.setLayout(new BorderLayout());
		this.add(board, BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth(), h = getHeight();
		Color color1 = new Color(50,50,50);
		Color color2 = new Color(205,126,0);
		GradientPaint gp = new GradientPaint(w-200, 0+200, color1, 0-100, h+100, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

	public Board getBoard() {
		return board;
	}
}
