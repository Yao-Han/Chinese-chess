import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * �O�йC��
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
	 * �غc�l
	 * @param super("�O�йC��") �����O(JFrame)���غc��k
	 * @param setUI() �]�w��������
	 * @param setVisible(true) �e�{�e��
	 */
	public MemoryGame() {
		super("�O�йC��");
		setUI();
		setVisible(true);
	}
	/**
	 * �]�w��������
	 * @param totalclick �����`�I����
	 * @param count ����½�P��
	 * @param complete �����w�t�令�\���P��
	 * @param press1 ����½�Ĥ@�i�P�����s�s��
	 * @param press2 ����½�ĤG�i�P�����s�s��
	 * @param random[] �üư}�C
	 * @param button[] ���s�}�C
	 * @param imageicon[] �Ϥ��}�C
	 * @param listener ��ť��
	 * @param Random() ���Ͷüư}�C����k
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
	 * ���Ͷüư}�C
	 * @param N �`�@�n���ʹX�Ӷü�
	 * @param temp �Ȧs�洫���}�C��
	 * @return �üư}�C 
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
	 * Listener���O�~��MouseAdapter
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
									"   ���߹L��\n�`�@�I���F" + totalclick + "��"
											+ "\n�U�Хͤ�ּ�!!!!");

						}
					}
				}
			}
		}
	}
}
