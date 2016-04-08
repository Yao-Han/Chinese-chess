import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class bomb_man implements Runnable, KeyListener {

	public final int x_move = 40;
	public final int y_move = 40;

	public int x = 40;
	public int y = 40;
	public int X = 40 * 13;
	public int Y = 40 * 13;
	int x1 = 0, xr = 0, y1 = 0, yr = 0;
	public int time_count = 0;
	public JFrame theWindow;
	JLabel p1, p2;
	JLabel[][] box;
	bomb_time[][] bomb_map;
	public int[][] Map;
	int power = 2;
	int power2 = 2;
	int bombNum[] = { 1 };
	int bombNum2[] = { 1 };

	bomb_man() {
	}

	public int[][] getMap() {
		return Map;
	}

	public void setMap(int i, int j, int value) {
		Map[i][j] = value;
	}

	public void Map_init() {
		Map = new int[15][15];
		for (int i = 0; i < Map.length; i++) {
			for (int j = 0; j < Map.length; j++) {
				if ((i % 2 == 0) && (j % 2 == 0))
					Map[i][j] = 1;
				else
					Map[i][j] = 0;
			}
		}
	}

	public void bombmap_init() {
		bomb_map = new bomb_time[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				bomb_map[i][j] = null;
			}
		}
	}

	public void box_init() {
		box = new JLabel[15][15];

		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box.length; j++) {
				if (i % 2 == 0 && i != 0 && i != 14) {
					if (j % 2 == 1) {
						box[i][j] = new JLabel(new ImageIcon("box.jpg"));
						box[i][j].setBounds(i * 40, j * 40, 40, 40);
						theWindow.add(box[i][j], 2);
						Map[i][j] = 3;
					}
				} else if (i % 2 == 1 && j != 0 && j != 14) {
					if (j % 2 == 0) {
						box[i][j] = new JLabel(new ImageIcon("box.jpg"));
						box[i][j].setBounds(i * 40, j * 40, 40, 40);
						theWindow.add(box[i][j], 2);
						Map[i][j] = 3;
					}
				}
			}
		}

	}

	public void run() {
		// TODO Auto-generated method stub
		theWindow = new JFrame("¬µ¼u¶W¤H");
		theWindow.setBounds(120, 120, 610, 630);

		JPanel BColor = (JPanel) theWindow.getContentPane();
		BColor.setBackground(Color.white);
		theWindow.addKeyListener(this);
		theWindow.setLayout(null);

		p1 = new JLabel(new ImageIcon("teacher.jpg"));
		p1.setBounds(40, 40, 40, 40);

		p2 = new JLabel(new ImageIcon("exteacher.jpg"));
		p2.setBounds(40 * 13, 40 * 13, 40, 40);

		theWindow.repaint();
		theWindow.add(p1);
		theWindow.add(p2);

		theWindow.repaint();

		JLabel tempLabel;
		for (int k = 0; k < 8; k++) {
			for (int l = 0; l < 8; l++) {
				tempLabel = new JLabel(new ImageIcon("woll.jpg"));
				tempLabel.setBounds(k * 80, l * 80, 40, 40);

				theWindow.add(tempLabel);
				theWindow.repaint();
			}
		}
		Map_init();
		bombmap_init();
		box_init();

		theWindow.setVisible(true);
		while (true) {
			try {
				Thread.sleep(200);
				time_count++;
				if (Map[x / 40][y / 40] == 4) {
					theWindow.remove(p1);
					JLabel die = new JLabel(new ImageIcon("die.jpg"));
					die.setBounds(180, 300, 259, 37);
					theWindow.add(die);
					theWindow.repaint();
					Thread.sleep(2500);
					break;
				}

				if (Map[X / 40][Y / 40] == 4) {
					theWindow.remove(p2);
					JLabel die = new JLabel(new ImageIcon("die.jpg"));
					die.setBounds(180, 300, 259, 37);
					theWindow.add(die);
					theWindow.repaint();
					Thread.sleep(2500);
					break;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		theWindow.dispose();
		System.exit(0);

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int i = x / 40;
		int j = y / 40;
		int m = X / 40;
		int n = Y / 40;

		if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
			if (j - 1 >= 0 && canWalk(i, j - 1)) {
				y = y - y_move;
				if (Map[i][j - 1] == 5 || Map[i][j - 1] == 6)
					eatGower(i, j - 1);
				System.out.println("a");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && y < 630 - 70) {
			if (j + 1 >= 0 && canWalk(i, j + 1)) {
				y = y + y_move;
				if (Map[i][j + 1] == 5 || Map[i][j + 1] == 6)
					eatGower(i, j + 1);
				System.out.println("a");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
			if (i - 1 >= 0 && canWalk(i - 1, j)) {
				x = x - x_move;
				if (Map[i - 1][j] == 5 || Map[i - 1][j] == 6)
					eatGower(i - 1, j);
				System.out.println("c");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < 610 - 50) {
			if (i + 1 >= 0 && canWalk(i + 1, j)) {
				x = x + x_move;
				if (Map[i + 1][j] == 5 || Map[i + 1][j] == 6)
					eatGower(i + 1, j);
				System.out.println("c");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && bombNum[0] > 0) {
			Map[x / 40][y / 40] = 2;
			bombNum[0]--;
			bomb_time burst = new bomb_time(x, y, 2, theWindow, power, bombNum,
					Map, box, bomb_map);
			bomb_map[x / 40][y / 40] = burst;
			burst.start();
			System.out.println(bombNum[0]);
		}

		if (e.getKeyCode() == KeyEvent.VK_W && Y > 0) {
			if (n - 1 >= 0 && canWalk(m, n - 1)) {
				Y = Y - y_move;
				if (Map[m][n - 1] == 5 || Map[m][n - 1] == 6)
					eatGower2(m, n - 1);
				System.out.println("a");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S && Y < 630 - 70) {
			if (n + 1 >= 0 && canWalk(m, n + 1)) {
				Y = Y + y_move;
				if (Map[m][n + 1] == 5 || Map[m][n + 1] == 6)
					eatGower2(m, n + 1);
				System.out.println("a");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A && X > 0) {
			if (m - 1 >= 0 && canWalk(m - 1, n)) {
				X = X - x_move;
				if (Map[m - 1][n] == 5 || Map[m - 1][n] == 6)
					eatGower2(m - 1, n);
				System.out.println("c");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D && X < 610 - 50) {
			if (m + 1 >= 0 && canWalk(m + 1, n)) {
				X = X + x_move;
				if (Map[m + 1][n] == 5 || Map[m + 1][n] == 6)
					eatGower2(m + 1, n);
				System.out.println("c");
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Q && bombNum2[0] > 0) {
			Map[X / 40][Y / 40] = 2;
			bombNum2[0]--;
			bomb_time burst = new bomb_time(X, Y, 2, theWindow, power2,
					bombNum2, Map, box, bomb_map);
			bomb_map[X / 40][Y / 40] = burst;
			burst.start();
			System.out.println(bombNum2[0]);
		}

		p1.setBounds(x, y, 40, 40);
		p2.setBounds(X, Y, 40, 40);
		theWindow.repaint();
	}

	public boolean canWalk(int i, int j) {
		return Map[i][j] == 0 || Map[i][j] >= 4;
	}

	public boolean eatGower(int i, int j) {
		boolean eated = false;
		switch (Map[i][j]) {
		case 5:
			bombNum[0]++;
			break;
		case 6:
			power++;
			break;
		default:
			break;
		}
		System.out.println(power + " " + bombNum[0]);
		theWindow.remove(box[i][j]);
		Map[i][j] = 0;
		return eated;
	}

	public boolean eatGower2(int i, int j) {
		boolean eated = false;
		switch (Map[i][j]) {
		case 5:
			bombNum2[0]++;
			break;
		case 6:
			power2++;
			break;
		default:
			break;
		}
		System.out.println(power + " " + bombNum[0]);
		theWindow.remove(box[i][j]);
		Map[i][j] = 0;
		return eated;
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}
