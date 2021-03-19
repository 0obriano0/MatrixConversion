package com.brian.MatrixConversion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.brian.MatrixConversion.tools.FontImage;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

public class mainFrom extends JFrame {

	private JPanel contentPane;
	private JTextField User_Text;
	private JTextField Text_Font_Size;
	private JLabel lblNewLabel_1;
	private JTextField shower;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrom frame = new mainFrom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println( "Hello World!" );
//		try {
//			FontImage.createImage("请在这里输入文字", new Font("微软雅黑", Font.PLAIN, 32), new File("./a.png"), 500, 64);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the frame.
	 */
	public mainFrom() {
		setTitle("中文轉換");
		setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User_Text = new JTextField();
		User_Text.setBounds(120, 36, 96, 21);
		User_Text.setFont(new Font("新細明體", Font.PLAIN, 12));
		contentPane.add(User_Text);
		User_Text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("輸入文字");
		lblNewLabel.setBounds(37, 39, 73, 15);
		contentPane.add(lblNewLabel);
		
		Text_Font_Size = new JTextField();
		Text_Font_Size.setBounds(120, 87, 96, 21);
		Text_Font_Size.setFont(new Font("新細明體", Font.PLAIN, 12));
		Text_Font_Size.setColumns(10);
		contentPane.add(Text_Font_Size);
		
		lblNewLabel_1 = new JLabel("文字大小");
		lblNewLabel_1.setBounds(37, 90, 73, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(66, 284, 87, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Input_Srting = User_Text.getText();
				int Input_String_realLength = FontImage.getRealLength(Input_Srting);
				int FontSize = Integer.valueOf(Text_Font_Size.getText());
				shower.setText(shower.getText() + "FontSize = " + FontSize + "\n");
				shower.setText(shower.getText() + "Input_Srting length = " + Input_Srting.length() + "\n");
				shower.setText(shower.getText() + "Input_Srting real length = " + Input_String_realLength + "\n");
				try {
					FontImage.createImage(Input_Srting, new Font("微软雅黑", Font.PLAIN, FontSize), new File("./a.png"), Input_String_realLength*(FontSize/2), FontSize+2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		shower = new JTextField();
		shower.setFont(new Font("標楷體", Font.PLAIN, 12));
		shower.setBounds(37, 179, 250, 95);
		contentPane.add(shower);
		shower.setColumns(10);
	}
}
