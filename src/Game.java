import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame {
	JLabel[] easyMap;
	JPanel map;
	ImageIcon wall;
	JButton bomberman, memory;
	Container c = getContentPane();

	public Game() {
		super("★☆可愛小遊戲☆★");
		Menu();
		setVisible(true);
	}

	public void Menu() {
		bomberman = new JButton("=== 炸彈超人 ===");
		memory = new JButton("===記憶遊戲 ===");
		bomberman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bomb_man b = new bomb_man();
				Thread go = new Thread(b);
				go.start();
			}
		});
		memory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoryGame m = new MemoryGame();
			}
		});

		c.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		c.add(bomberman);
		c.add(memory);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Game();
	}
}
