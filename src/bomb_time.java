import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class bomb_time extends Thread {

	int[][] Map;
	JLabel[][] Box;
	bomb_time[][] bombMap;
	int power;
	int[] bombNumOfOwner;
	int x;
	int y;
	int bombTime;
	static int bomb_num = 0;
	JLabel bomb;
	JLabel shadow;
	JFrame Window;
	static int rm_cnt = 0;

	bomb_time(int x, int y, int bombTime, JFrame window, int power,
			int[] bombNumOfOwner, int[][] Map, JLabel[][] Box,
			bomb_time[][] bombMap) {
		this.Map = Map;
		this.x = x;
		this.y = y;
		this.bombTime = bombTime;
		this.Window = window;
		this.power = power;
		this.bombNumOfOwner = bombNumOfOwner;
		this.Box = Box;
		this.bombMap = bombMap;

		bomb = new JLabel(new ImageIcon("bomb.jpg"));
		bomb.setBounds(x, y, 40, 40);
		Window.add(bomb, 3);

	}

	public void run() {
		bomb_fire fire = new bomb_fire(power, bombNumOfOwner, Map, Window, Box,
				bombMap);
		try {
			Thread.sleep(bombTime * 1000);
			Window.remove(bomb);

			fire.fireout(x, y);
			Thread.sleep(500);
			fire.firedes(x / 40, y / 40, power);
			Map[x / 40][y / 40] = 0;

			bombNumOfOwner[0]++;
			System.out.println(bombNumOfOwner[0]);

		} catch (InterruptedException ex) {

			Window.remove(bomb);

			fire.fireout(x, y);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fire.firedes(x / 40, y / 40, power);
			Map[x / 40][y / 40] = 0;

			bombNumOfOwner[0]++;
			System.out.println(bombNumOfOwner[0]);
		}
	}
}