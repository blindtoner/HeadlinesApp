package display;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Display extends JFrame implements Observer, ActionListener{

	private static final long serialVersionUID = 8060043667278373218L;
	JTextArea mainTextArea;
	JLabel logo;
	Button linkButton;
	Button stopButton;
	String dataObject;
	
	public Display() {
		this.setTitle("Tech HeadLines App");
		// enable anti-aliased text:
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");

		mainTextArea= new JTextArea("");
		mainTextArea.setFont(new Font("Verdana", Font.PLAIN, 15));
		mainTextArea.setBackground(Color.GRAY);
		mainTextArea.setForeground(Color.WHITE);
		mainTextArea.setOpaque(true);
		mainTextArea.setLineWrap(true);
		mainTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainTextArea.setEditable(false);
		logo = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
		
		linkButton = new Button();
		linkButton.setLabel("Open Browser");
		linkButton.setForeground(Color.GRAY);
		linkButton.addActionListener(this);
		linkButton.setEnabled(false);
		linkButton.setActionCommand("linkButton");

		stopButton = new Button();
		stopButton.setLabel("Stop");
		stopButton.setForeground(Color.GRAY);
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		stopButton.setActionCommand("stopButton");

		this.setLocationRelativeTo(null);
		this.getContentPane().add(mainTextArea, BorderLayout.CENTER);
		this.getContentPane().add(logo, BorderLayout.NORTH);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(linkButton, BorderLayout.CENTER);
		jPanel.add(stopButton,BorderLayout.WEST); 
		this.getContentPane().add(jPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(600,475));
		this.setVisible(true);
	}
	@Override
	public void update (Observable observable, Object data) {
		linkButton.setEnabled(true);
		stopButton.setEnabled(true);
		dataObject = data.toString();
		mainTextArea.setText(data.toString());
		System.out.println(Thread.currentThread() + "Display");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "stopButton":
			Thread.currentThread().interrupt();
			System.out.println("STOP BUTTON");
			break;
		case "linkButton":
			handleLinkButton();
			break;
		}
	}

	public void handleLinkButton() {
		String webAddress=null;
		try {
			webAddress = dataObject.substring(dataObject.indexOf("http"));
		} catch (StringIndexOutOfBoundsException e2) {
		}

		if (Desktop.isDesktopSupported() && webAddress !=null) {
			try {
				Desktop.getDesktop().browse(new URI(webAddress));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
}
