package com.brian.MatrixConversion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.brian.MatrixConversion.FontImage.FontImage;
import com.brian.MatrixConversion.tools.tools;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class mainFrom extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField User_Text;
	private JTextField Text_Font_Size;
	private JLabel lblNewLabel_1;
	private JComboBox<String> FontStyle;
	private JTextArea shower;
	private JLabel imageShower;
	
	private FontImage FImage = new FontImage();
	
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
	}

	/**
	 * Create the frame.
	 */
	public mainFrom() {
		setTitle("中文轉換");
		setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User_Text = new JTextField();
		User_Text.setBounds(120, 36, 167, 21);
		User_Text.setFont(new Font("新細明體", Font.PLAIN, 12));
		contentPane.add(User_Text);
		User_Text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("輸入文字");
		lblNewLabel.setBounds(37, 39, 73, 15);
		contentPane.add(lblNewLabel);
		
		Text_Font_Size = new JTextField();
		Text_Font_Size.setBounds(120, 87, 167, 21);
		Text_Font_Size.setFont(new Font("新細明體", Font.PLAIN, 12));
		Text_Font_Size.setColumns(10);
		contentPane.add(Text_Font_Size);
		
		lblNewLabel_1 = new JLabel("文字大小");
		lblNewLabel_1.setBounds(37, 90, 73, 15);
		contentPane.add(lblNewLabel_1);
		
		FontStyle = new JComboBox<String>();
		FontStyle.setBounds(119, 136, 168, 25);
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String[] fontNames = e.getAvailableFontFamilyNames();
	    for (String fontName : fontNames) {
	    	FontStyle.addItem(fontName);
	    }
	    FontStyle.setSelectedItem("新細明體");
		contentPane.add(FontStyle);
		
		JLabel lblNewLabel_1_1 = new JLabel("字體");
		lblNewLabel_1_1.setBounds(37, 139, 73, 15);
		contentPane.add(lblNewLabel_1_1);
		
		shower = new JTextArea();
		shower.setBounds(37, 179, 250, 97);
		contentPane.add(shower);
		
	    imageShower = new JLabel("");
		imageShower.setBounds(37, 354, 250, 86);
		contentPane.add(imageShower);
		
		JButton btnNewButton = new JButton("生圖");
		btnNewButton.setBounds(94, 302, 141, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Input_Srting = User_Text.getText();
				int Input_String_realLength = tools.getRealLength(Input_Srting);
				int FontSize = Integer.valueOf(Text_Font_Size.getText());
				shower.setText(shower.getText() + "FontSize = " + FontSize + "\n");
				shower.setText(shower.getText() + "Input_Srting length = " + Input_Srting.length() + "\n");
				shower.setText(shower.getText() + "Input_Srting real length = " + Input_String_realLength + "\n");
				try {
					FImage.createImage(Input_Srting, new Font((String) FontStyle.getSelectedItem(), Font.PLAIN, FontSize), new File("./a.png"), Input_String_realLength*(FontSize/2), FontSize);
					ImageIcon imageIcon = new ImageIcon(FImage.getImage());
					imageShower.setIcon(imageIcon);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);

	}
}
