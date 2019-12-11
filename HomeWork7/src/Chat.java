
	
	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JTextArea;
	import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
	import java.net.UnknownHostException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

	public class Chat extends JFrame implements ActionListener {

		private JPanel contentPane;
		private JTextField textField;
		static JTextArea textArea;
		private static InetAddress ipAddress;
		
		private static String name="mahboba";
		 static int port;
		static String host;
		private DatagramSocket dSocket;
		private JButton send;
		
		
	private static HashMap<String, Chat>map=new HashMap<>();
		
	   
		static Socket mySocket;
		public Chat(Socket socket, String host, int port) {
			
			this.port=port;
			this.host=host;
			try {
				this.ipAddress= InetAddress.getByName(host);
			}catch(UnknownHostException ue) {
				ue.printStackTrace();
				System.exit(-1);
			}
			
		setTitle("Let's chat");
	
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 310);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(139, 218, 146, 26);
			contentPane.add(textField);
			textField.setColumns(10);
			textArea = new JTextArea();
			textArea.setBounds(0, 0, 428, 202);
			contentPane.add(textArea);
			
			
			
			send = new JButton("Send");
			send.addActionListener(this);
		send.setBounds(300, 217, 115, 27);
			contentPane.add(send);
			
			
			
			JLabel lblNewLabel = new JLabel("Message");
			lblNewLabel.setBounds(47, 221, 60, 20);
			contentPane.add(lblNewLabel);
			setVisible(true);
			
			
		}
		
		public void window() {
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 310);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(139, 218, 146, 26);
			contentPane.add(textField);
			textField.setColumns(10);
			textArea = new JTextArea();
			textArea.setBounds(0, 0, 428, 202);
			contentPane.add(textArea);
			
			
			
			send = new JButton("Send");
			send.addActionListener(this);
		send.setBounds(300, 217, 115, 27);
			contentPane.add(send);
			
			
			
			JLabel lblNewLabel = new JLabel("Message");
			lblNewLabel.setBounds(47, 221, 60, 20);
			contentPane.add(lblNewLabel);
			setVisible(true);
			
			
			
			
			
		}
  
		
		 public static void console(String name) {
				
				textArea.append(name+ "\n");
				
			}
		 
		 public static void keepMsg() {
			 DatagramPacket inPacket=null;
			 do {
				 try {
					 inPacket= mySocket.receive();
					 if(inPacket!=null) {
						 String message=new String (inPacket.getData());
						 System.out.println("Message Received : " + message);
						 String ip=inPacket.getAddress().getHostAddress();
						 Chat chat= null;
						 String[] msgContains= message.split("");
						 if(message.contains("?????")&& msgContains[1].equalsIgnoreCase("mahboba")) {
							 chat= new Chat(mySocket, ip, 64000);
							 chat.setTitle("Name:" + msgContains[3]+ "IP Address:"+ ip);
							mySocket.send("#####"+" "+ msgContains[1]+" "+ "#####", inPacket.getAddress(),64000);
							Name.map.put(ip, chat);
							chat.setVisible(true);
						 }
						 else if(message.contains("##### "+ip)) {
							 chat= new Chat(mySocket, ip, 64000);
							 console(message);
							 Name.map.put(ip, chat);
							 chat.setVisible(true);
						 }else if(Name.map.containsKey(ip)) {
							 console(message);
							 chat.setVisible(true);
							 
						 }
						 
					 }
				 }catch(Exception ex) {
					 
				 }
			 }while(true);
		 }
		@Override
		public void actionPerformed(ActionEvent e) {
		
			Object obj=e.getSource();
		if (obj== send){
				mySocket.send(textField.getText(), ipAddress,64000);
			console(textField.getText());
				
			}
			
		}
	}



