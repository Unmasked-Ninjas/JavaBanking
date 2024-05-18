package BankAssesment;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class GUIBANK extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField depoAccNum;
	private JTextField depoAmount;
	private JTextField withdrawAccNum;
	private JTextField WithdrawAmount;
	private JTextField transferAcc1;
	private JTextField transferAcc2;
	private JTextField transferAmount;
	private JLabel ShowallData;
	private JLabel confirmField;
	public LinkedList<Account> globalAccount;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIBANK frame = new GUIBANK(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIBANK(LinkedList<Account> accounts) {
		
		globalAccount = accounts;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton showallbtn = new JButton("Show All Data");
		showallbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		showallbtn.setBounds(10, 105, 129, 34);
		contentPane.add(showallbtn);
		showallbtn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				//Account 
				globalAccount = accounts;
				for(Account a : globalAccount) {
					sb.append(a.getFirstName()+" "+a.getLastName()+" "+a.getAccountNum()+" "+a.getBalance());
					sb.append("<br>");
				}
				ShowallData.setText("<html>" + sb.toString() + "</html>");
				
			}
			
		});

		JButton depositbtn = new JButton("Deposit");
		depositbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		depositbtn.setBounds(10, 223, 129, 34);
		contentPane.add(depositbtn);
		depositbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int depoAcc = Integer.parseInt(depoAccNum.getText());
				int depoAmt = Integer.parseInt(depoAmount.getText());
				globalAccount = accounts;
				boolean found = false;
				for (Account a: globalAccount) {
					if(depoAcc == a.getAccountNum()) {
						a.deposit(depoAmt);
						found = true;
						confirmField.setText("Successfully Deposited Amount "+depoAmt+ " to account "+ depoAcc+"\n New-Balance:"+ a.getBalance());
						break;
						
					}
				}
				if(!found) {
					confirmField.setText("Invalid account number");
				}
				
			}
			
		});
		

		JButton withdrawbtn = new JButton("Withdraw");
		withdrawbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		withdrawbtn.setBounds(10, 317, 129, 34);
		contentPane.add(withdrawbtn);
		withdrawbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int WithAcc = Integer.parseInt(withdrawAccNum.getText());
			int WithAmount = Integer.parseInt(WithdrawAmount.getText());
			boolean found = false;
			for(Account a : globalAccount) {
				if(WithAcc == a.getAccountNum()) {
					a.withdraw(WithAmount);
					found = true;
					confirmField.setText("Successfully Withdrawed "+WithAmount+" from account "+WithAmount+" New-Balance "+a.getBalance());
					break;
				}
			}
			if(!found) {
				confirmField.setText("Invalid account number");
			}
			}
		});
		

		JButton transferbtn = new JButton("Transfer");
		transferbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		transferbtn.setBounds(10, 397, 129, 34);
		contentPane.add(transferbtn);
		transferbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fromAcc = Integer.parseInt(transferAcc1.getText());
				int toAcc = Integer.parseInt(transferAcc2.getText());
				int transferAmt = Integer.parseInt(transferAmount.getText());
				
				Account fromAccount = null;
				Account toAccount = null;
				for(Account a: globalAccount) {
					if(fromAcc == a.getAccountNum()) {
						fromAccount = a;
					}
					else if(toAcc == a.getAccountNum()){
						toAccount = a; 
					}
				}
				if(fromAccount != null && toAccount != null) {
					fromAccount.withdraw(transferAmt);
					toAccount.deposit(transferAmt);
					confirmField.setText("Successfully Transferred "+transferAmt+" from Account "+ fromAcc+ " to Account "+toAcc+" New-Balance of Recieved Account "+toAccount.getBalance());
				}
				else {
					confirmField.setText("Either one or both Account numbers are incorrect");
				}
				
				
			}
			
		});
		

		depoAccNum = new JTextField();
		depoAccNum.setBounds(192, 230, 129, 25);
		contentPane.add(depoAccNum);
		depoAccNum.setColumns(10);

		depoAmount = new JTextField();
		depoAmount.setBounds(412, 230, 129, 25);
		contentPane.add(depoAmount);
		depoAmount.setColumns(10);

		withdrawAccNum = new JTextField();
		withdrawAccNum.setBounds(192, 324, 129, 25);
		contentPane.add(withdrawAccNum);
		withdrawAccNum.setColumns(10);

		WithdrawAmount = new JTextField();
		WithdrawAmount.setBounds(412, 317, 129, 32);
		contentPane.add(WithdrawAmount);
		WithdrawAmount.setColumns(10);

		JLabel lblNewLabel = new JLabel("To");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(357, 401, 49, 25);
		contentPane.add(lblNewLabel);

		transferAcc1 = new JTextField();
		transferAcc1.setBounds(192, 406, 129, 25);
		contentPane.add(transferAcc1);
		transferAcc1.setColumns(10);

		transferAcc2 = new JTextField();
		transferAcc2.setBounds(412, 406, 129, 25);
		contentPane.add(transferAcc2);
		transferAcc2.setColumns(10);

		transferAmount = new JTextField();
		transferAmount.setBounds(648, 404, 129, 25);
		contentPane.add(transferAmount);
		transferAmount.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("PuntuBankLimited");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(342, 11, 188, 34);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(683, 381, 66, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Amount");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(447, 208, 66, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3 = new JLabel("Account Num");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(208, 208, 95, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_2_1_1 = new JLabel("Amount");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(447, 290, 66, 14);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("Account Num");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(208, 297, 95, 16);
		contentPane.add(lblNewLabel_3_1);

		ShowallData = new JLabel(" ");
		ShowallData.setBounds(218, 81, 312, 94);
		contentPane.add(ShowallData);
		
		confirmField = new JLabel(" ");
		confirmField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmField.setBounds(50, 466, 765, 80);
		contentPane.add(confirmField);
	}
}
