package com.brian.MatrixConversion;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.brian.MatrixConversion.CentralManagementSystem.CMS;
import com.brian.MatrixConversion.FontImage.FontImage;
import com.brian.MatrixConversion.FontImage.ShowModeType;
import com.brian.MatrixConversion.tools.tools;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Panel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	
	Byte num = 0;
	
	private FontImage FImage = new FontImage();
	private JTextField text_port;
	private JButton web_Start;
	private JButton web_Stop;
	
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
		setBounds(100, 100, 665, 505);
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
					ImageIcon imageIcon = new ImageIcon(FImage.getImage_GRAY());
					imageShower.setIcon(imageIcon);
					FImage.getcode(ShowModeType.Horizontal_LEFT);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		Panel panel = new Panel();
		panel.setBounds(341, 36, 298, 162);
		contentPane.add(panel);
		panel.setLayout(null);
		
		web_Start = new JButton("Start");
		web_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("伺服器開啟中...");
				if(CMS.getwebserver().openWebServer(text_port.getText())) {
					web_Start.setEnabled(false);
					web_Stop.setEnabled(true);
					text_port.setEditable(false);
					System.out.println("伺服器開啟成功");
				}
				
			}
		});
		web_Start.setEnabled(false);
		web_Start.setBounds(20, 96, 94, 29);
		panel.add(web_Start);
		
		web_Stop = new JButton("Stop");
		web_Stop.setEnabled(false);
		web_Stop.setBounds(178, 97, 94, 26);
		panel.add(web_Stop);
		
		JLabel lblNewLabel_2 = new JLabel("Web Server");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(109, 10, 75, 29);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("port");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(81, 49, 33, 29);
		panel.add(lblNewLabel_3);
		
		text_port = new JTextField();
		text_port.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(text_port.getText().length() > 0)
					web_Start.setEnabled(true);
				else
					web_Start.setEnabled(false);
			}
		});

		text_port.setBounds(176, 54, 96, 21);
		panel.add(text_port);
		text_port.setColumns(10);

	}
}
