import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class bomb_fire {
	JLabel jt5;
	JLabel fireLabel[][];

	JLabel[][] Box;
	int actualEffected[] = { 0, 0, 0, 0 };
	int[][] Map;
	JFrame window;
	int[] bombNumOfOwner;
	public int power;
	bomb_time[][] bombMap;

	bomb_fire(int p, int[] bombNumOfOwner, int[][] Map, JFrame window,
			JLabel[][] Box, bomb_time[][] bombMap) {

		fireLabel = new JLabel[4][p];

		this.Map = Map;
		this.window = window;
		this.Box = Box;
		this.bombMap = bombMap;
		power = p;
		this.bombNumOfOwner = bombNumOfOwner;
	}

	public void fireout(int x, int y) {
		int a, i, j;
		int iStar, jStar, xStar, yStar;

		i = x / 40;
		j = y / 40;
		String pic;
		boolean isInBound;
		jt5 = new JLabel(new ImageIcon("firecenter.gif"));
		jt5.setBounds(x, y, 40, 40);
		window.add(jt5, 1);

		Map[i][j] = 4;
		for (int ct = 0; ct < 4; ct++) {
			
			for (a = 1; a <= power; a++) {
				switch (ct) {
				case 0:
					pic = (a == power) ? "right.gif" : "l_r.gif";
					iStar = i + a;
					jStar = j;
					xStar = x + (a * 40);
					yStar = y;
					break;
				case 1:
					pic = (a == power) ? "down.gif" : "u_d.gif";
					iStar = i;
					jStar = j + a;
					xStar = x;
					yStar = y + (a * 40);
					break;
				case 2:
					pic = (a == power) ? "left.gif" : "l_r.gif";
					iStar = i - a;
					jStar = j;
					xStar = x - (a * 40);
					yStar = y;
					break;
				case 3:
					pic = (a == power) ? "up.gif" : "u_d.gif";
					iStar = i;
					jStar = j - a;
					xStar = x;
					yStar = y - (a * 40);
					break;
				default:
					pic = null;
					iStar = i;
					jStar = j;
					xStar = x;
					yStar = y;
					break;
				}
				isInBound = iStar >= 0 && iStar <= 14 && jStar >= 0
						&& jStar <= 14;
				if (isInBound && Map[iStar][jStar] == 0) {
					fireLabel[ct][a - 1] = new JLabel(new ImageIcon(pic));
					fireLabel[ct][a - 1].setBounds(xStar, yStar, 40, 40);
					window.add(fireLabel[ct][a - 1], 1);
					actualEffected[ct]++;
					Map[iStar][jStar] = 4;
				} else if (isInBound
						&& (Map[iStar][jStar] == 3 || Map[iStar][jStar] == 5 || Map[iStar][jStar] == 6)) {
					window.remove(Box[iStar][jStar]);
					if (Map[iStar][jStar] == 3)
						produceGrower(window, Box, Map, iStar, jStar, xStar,
								yStar);
					else
						Map[iStar][jStar] = 0;
					break;
				} else if (isInBound && Map[iStar][jStar] == 2) {
					bombMap[iStar][jStar].interrupt();
					bomb_time burst = new bomb_time(iStar * 40, jStar * 40, 0,
							window, power, bombNumOfOwner, Map, Box, bombMap);
					bombMap[iStar][jStar] = burst;
					burst.start();
					System.out.println(bombNumOfOwner[0]);
					break;
				} else
					break;

			}
		}

		window.repaint();

	}

	public void firedes(int x, int y, int power) {
		int a;
		int i;

		Map[x][y] = 0;// center

		for (i = 1; i <= power; i++) {
			if (y - i >= 0 && Map[x][y - i] == 4)
				Map[x][y - i] = 0;// up
			if (y + i <= 14 && Map[x][y + i] == 4)
				Map[x][y + i] = 0;// down
			if (x - i >= 0 && Map[x - i][y] == 4)
				Map[x - i][y] = 0;
			if (x + i <= 14 && Map[x + i][y] == 4)
				Map[x + i][y] = 0;

		}
		window.remove(jt5);
		for (int ct = 0; ct < 4; ct++) {
			for (a = 1; a <= actualEffected[ct]; a++) {
				window.remove(fireLabel[ct][a - 1]);
			}
			for (a = 1; a <= actualEffected[ct]; a++) {
				window.remove(fireLabel[ct][a - 1]);
			}
			for (a = 1; a <= actualEffected[ct]; a++) {
				window.remove(fireLabel[ct][a - 1]);
			}
			for (a = 1; a <= actualEffected[ct]; a++) {
				window.remove(fireLabel[ct][a - 1]);
			}
		}
		window.repaint();

	}

	public boolean produceGrower(JFrame window, JLabel[][] Box, int[][] Map,
			int i, int j, int x, int y) {
		int rand = (int) Math.round(Math.random() * 4);
		boolean isProduced = (rand == 1) || (rand == 2);
		String pic;
		int growerClass;
		switch (rand) {
		case 1:
			pic = "addbomb.jpg";
			growerClass = 5;
			break;
		case 2:
			pic = "addfire.jpg";
			growerClass = 6;
			break;
		default:
			pic = null;
			growerClass = 0;
		}
		if (isProduced) {
			Box[i][j] = new JLabel(new ImageIcon(pic));
			Box[i][j].setBounds(x, y, 40, 40);
			window.add(Box[i][j], 2);

		}
		Map[i][j] = growerClass;
		return isProduced;
	}
}
