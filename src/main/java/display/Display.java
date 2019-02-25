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

import mainPackage.IDisplayContents;
import mainPackage.ObservedData;

public class Display extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = 8060043667278373218L;
	JTextArea mainTextArea;
	JLabel logo;
	Button linkButton;
	Button stopButton;
	IDisplayContents dataObject;

	public Display() {
		this.setTitle("Tech HeadLines App");
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");

		mainTextArea = setupWindow();

		setButtons();

		this.setLocationRelativeTo(null);
		this.getContentPane().add(mainTextArea, BorderLayout.CENTER);
		this.getContentPane().add(logo, BorderLayout.NORTH);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(linkButton, BorderLayout.CENTER);
		jPanel.add(stopButton, BorderLayout.WEST);
		this.getContentPane().add(jPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(600, 475));
		this.setVisible(true);
	}

	private void setButtons() {
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
	}

	JTextArea setupWindow() {
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 15));
		textArea.setBackground(Color.GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setOpaque(true);
		textArea.setLineWrap(true);
		textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
		textArea.setEditable(false);
		logo = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
		return textArea;
	}

	@Override
	public void update(Observable observable, Object data) {
		linkButton.setEnabled(true);
		stopButton.setEnabled(true);

		dataObject = (IDisplayContents) data;
		mainTextArea.setText(dataObject.getDisplayTitle() + "\n" + dataObject.getDisplayWebAddress());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "stopButton":
			handleStopButton((Button) (e.getSource()));
			break;
		case "linkButton":
			handleLinkButton();
			break;
		}
	}

	public void handleStopButton(Button button) {
		if (button.getLabel().equals("Stop")) {
			stopButton.setLabel("Resume");
			ObservedData.pauseTimer();
		} else {
			stopButton.setLabel("Stop");
			ObservedData.resumeTimer();
		}

	}

	public void handleLinkButton() {
		String webAddress = null;
		try {
			webAddress = dataObject.getDisplayWebAddress();
		} catch (StringIndexOutOfBoundsException e2) {
		}

		if (Desktop.isDesktopSupported() && webAddress != null) {
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
