import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Name extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;

	static HashMap<String, Chat> map = new HashMap<>();

	private InetAddress ipAddress;
//	final String ip="255.255.255.255";
	int host;
	String address;
	Socket mySocket;

	public Name() {
		Chat.mySocket = new Socket(64000, Socket.SocketState.Broadcast);
		setTitle("Chat Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(169, 37, 185, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(85, 40, 69, 20);
		contentPane.add(lblNewLabel);

		JButton search = new JButton("Chat");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = textField.getText();

				try {
					ipAddress = InetAddress.getByName("255.255.255.255");
				} catch (UnknownHostException ex) {
					ex.printStackTrace();
					System.exit(-1);
				}
				String str = "Looking for: ";
				String showName = "?????" + " " + name + " " + "##### mahboba";
				if (!name.isEmpty()) {
					Chat.mySocket.send(showName, ipAddress, 64000);
					window(mySocket, address, host);
					textField.setText("");
					Chat.textArea.append(str + showName + "\n");
				}

			}

		});

		search.setBounds(152, 199, 115, 29);
		contentPane.add(search);
		setVisible(true);
	}

	public void window(Socket mySocket, String address, int port) {
		new Chat(mySocket, address, port);
	}

}
