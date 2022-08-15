package brasileiraoView;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BrasileiraoMenu {

	public static void main(String[] args) {

		// JFrame = a GUI window to add components to

		ImageIcon image = new ImageIcon("logo.png");

		JLabel label = new JLabel();
		label.setIcon(image);
		label.setText("Menu");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("fira code", Font.BOLD, 40));

		JFrame frame = new JFrame(); // Creates a frame
		frame.setTitle("Brasileirão"); // sets title of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1000, 800); // sets the x-dimension, and y-dimension of frame
		frame.setVisible(true); // make frame visible
		frame.add(label);

		ImageIcon image2 = new ImageIcon("bola.png"); // create ImageIcon
		frame.setIconImage(image2.getImage());
		frame.getContentPane().setBackground(new Color(47, 79, 79)); // change color of background

	}

}