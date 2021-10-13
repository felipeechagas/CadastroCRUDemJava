import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbnome;
	private JTextField txtcpf;
	private JTextField txtitem;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from book");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
     }
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 601, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 76, 287, 151);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel bname = new JLabel("Nome");
		bname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bname.setBounds(21, 36, 66, 14);
		panel.add(bname);
		
		txtbnome = new JTextField();
		txtbnome.setBounds(97, 33, 160, 20);
		panel.add(txtbnome);
		txtbnome.setColumns(10);
		
		JLabel edition = new JLabel("Cpf");
		edition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edition.setBounds(21, 62, 66, 14);
		panel.add(edition);
		
		txtcpf = new JTextField();
		txtcpf.setColumns(10);
		txtcpf.setBounds(97, 59, 160, 20);
		panel.add(txtcpf);
		
		JLabel price = new JLabel("Item");
		price.setFont(new Font("Tahoma", Font.PLAIN, 14));
		price.setBounds(21, 88, 66, 14);
		panel.add(price);
		
		txtitem = new JTextField();
		txtitem.setColumns(10);
		txtitem.setBounds(97, 85, 160, 20);
		panel.add(txtitem);
		
		JLabel Id = new JLabel("Cadastro Usu\u00E1rios");
		Id.setFont(new Font("Tahoma", Font.BOLD, 24));
		Id.setBounds(167, 11, 222, 39);
		frame.getContentPane().add(Id);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				bname = txtbnome.getText();
				edition = txtcpf.getText();
				price = txtitem.getText();
				
				try {
				pst = con.prepareStatement("insert into book(nome,cpf,item)values(?,?,?)");
				pst.setString(1, bname);
				pst.setString(2, edition);
				pst.setString(3, price);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado com Sucesso!");
				table_load();
				          
				txtbnome.setText("");
				txtcpf.setText("");
				txtitem.setText("");
				txtbnome.requestFocus();
				}
				 
				catch (SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(22, 238, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
				}
			});
		
		btnExit.setBounds(121, 238, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtbnome.setText("");
	            txtcpf.setText("");
	            txtitem.setText("");
	            txtbnome.requestFocus();
			}
		});
		btnClear.setBounds(220, 238, 89, 23);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 76, 227, 185);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(22, 272, 287, 74);
		frame.getContentPane().add(panel_1);
		
		JLabel lblid = new JLabel("New label");
		lblid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblid.setBounds(21, 36, 66, 14);
		panel_1.add(lblid);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
	            String id = txtbid.getText();
	 
                pst = con.prepareStatement("select nome,cpf,item from book where id = ?");
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true){
		            	
	                String name = rs.getString(1);
	                String edition = rs.getString(2);
	                String price = rs.getString(3);
	                
	                txtbnome.setText(name);
	                txtcpf.setText(edition);
	                txtitem.setText(price);
		            }  
		            else
		            {
		             txtbnome.setText("");
		             txtcpf.setText("");
		             txtitem.setText("");
		            }
		        }
		catch (SQLException ex) {
		        }
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(97, 33, 160, 20);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid;
				
				bname = txtbnome.getText();
				edition = txtcpf.getText();
				price = txtitem.getText();
				bid  = txtbid.getText();
				
				try {
				pst = con.prepareStatement("update book set nome= ?,cpf=?,item=? where id =?");
				pst.setString(1, bname);
	            pst.setString(2, edition);
	            pst.setString(3, price);
	            pst.setString(4, bid);
	            pst.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Alteração Realizada com Sucesso!!!!!");
	            table_load();
		          
	            txtbnome.setText("");
	            txtcpf.setText("");
	            txtitem.setText("");
	            txtbnome.requestFocus();
				}
			    catch (SQLException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(354, 299, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	            String bid;
	            bid  = txtbid.getText();
	            try {
            	pst = con.prepareStatement("delete from book where id =?");
            	pst.setString(1, bid);
			    pst.executeUpdate();
			    JOptionPane.showMessageDialog(null, "Deletado com Sucesso!!!!!");
				table_load();
  
				txtbnome.setText("");
			    txtcpf.setText("");
			    txtitem.setText("");
			    txtbnome.requestFocus();
                }
                catch (SQLException e1) {
                	e1.printStackTrace();
                }
			}
		});
		btnDelete.setBounds(453, 299, 89, 23);
		frame.getContentPane().add(btnDelete);
	}
}
