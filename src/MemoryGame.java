import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 記憶遊戲
 */
public class MemoryGame extends JFrame {
	int totalclick = 0;
	int count = 0;
	int complete = 0;
	int press1 = -1;
	int press2 = -1;
	int[] random;
	JButton[] button;
	ImageIcon imageIcon1, imageIcon2;
	ImageIcon[] imageicon;
	Container container;
	Listener listener;
	/**
	 * 建構子
	 * @param super("記憶遊戲") 父類別(JFrame)的建構方法
	 * @param setUI() 設定視窗元件
	 * @param setVisible(true) 呈現畫面
	 */
	public MemoryGame() {
		super("記憶遊戲");
		setUI();
		setVisible(true);
	}
	/**
	 * 設定視窗元件
	 * @param totalclick 紀錄總點擊數
	 * @param count 紀錄翻牌數
	 * @param complete 紀錄已配對成功之牌組
	 * @param press1 紀錄翻第一張牌的按鈕編號
	 * @param press2 紀錄翻第二張牌的按鈕編號
	 * @param random[] 亂數陣列
	 * @param button[] 按鈕陣列
	 * @param imageicon[] 圖片陣列
	 * @param listener 傾聽者
	 * @param Random() 產生亂數陣列的方法
	 */
	public void setUI() {
		setSize(560, 410);
		container = getContentPane();
		listener = new Listener();
		this.setLayout(new GridLayout(3, 4, 10, 10));

		imageicon = new ImageIcon[13];
		imageicon[0] = new ImageIcon("0.gif");
		for (int i = 1; i <= 6; i++) {
			imageicon[i] = new ImageIcon(i + ".jpg");
			imageicon[i + 6] = new ImageIcon(i + ".jpg");
		}

		Random();

		button = new JButton[12];
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton(imageicon[0]);
			button[i].setEnabled(true);
			this.add(button[i]);
			button[i].addMouseListener(listener);
		}

	}
	/**
	 * 產生亂數陣列
	 * @param N 總共要產生幾個亂數
	 * @param temp 暫存交換的陣列值
	 * @return 亂數陣列 
	 */
	public int[] Random() {
		int N = 12;
		random = new int[N + 1];

		for (int i = 1; i <= N; i++)
			random[i] = i;

		for (int i = 1; i <= N; i++) {
			int j = (int) (Math.random() * N);

			if (j == 0)
				j = 1;

			int tmp = random[i];
			random[i] = random[j];
			random[j] = tmp;
		}
		return random;
	}
	/**
	 * Listener類別繼承MouseAdapter
	 */
	class Listener extends MouseAdapter {
		/**
		 * (non-Javadoc)
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			if (press1 != -1)
				if (e.getSource() == button[press1])
					return;

			if (press2 != -1)
				if (e.getSource() == button[press2])
					return;

			for (int i = 0; i < button.length; i++) {
				if (button[i].isEnabled()) {
					if (e.getSource() == button[i]) {
						count++;
						totalclick++;

						if (count > 2) {
							button[press1].setIcon(imageicon[0]);
							button[press2].setIcon(imageicon[0]);
							count = 1;
							press1 = -1;
							press2 = -1;
						}

						button[i].setIcon(imageicon[random[i + 1]]);

						if (count == 1) {
							press1 = i;
						}
						if (count == 2) {
							press2 = i;
						}

						if (press1 != -1 && press2 != -1) {

							if (Math.abs(random[press1 + 1]
									- random[press2 + 1]) == 6) {
								button[press1].setEnabled(false);
								button[press2].setEnabled(false);

								press1 = -1;
								press2 = -1;
								count = 0;
								complete++;
							}
						}

						if (complete == 6) {
							JOptionPane.showMessageDialog(null,
									"   恭喜過關\n總共點擊了" + totalclick + "次"
											+ "\n助教生日快樂!!!!");

						}
					}
				}
			}
		}
	}
}
