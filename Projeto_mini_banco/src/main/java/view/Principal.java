package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Cliente;
import entities.Contas;
import entities.GravadorDeDados;

public class Principal extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCPF;
	private JTextField textConta;
	private JTextField textAgencia;
	private JTextField textSaldo;
	private JButton btnSalvar;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblConta = new JLabel("Conta :");
		lblConta.setBounds(10, 33, 46, 14);
		contentPane.add(lblConta);
		
		JLabel lblAgencia = new JLabel("Agencia :");
		lblAgencia.setBounds(10, 55, 46, 14);
		contentPane.add(lblAgencia);
		
		JLabel lblConta_1_1 = new JLabel("Saldo :");
		lblConta_1_1.setBounds(10, 80, 46, 14);
		contentPane.add(lblConta_1_1);
		
		textNome = new JTextField();
		textNome.setBounds(49, 8, 134, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setBounds(193, 11, 46, 14);
		contentPane.add(lblCpf);
		
		textCPF = new JTextField();
		textCPF.setColumns(10);
		textCPF.setBounds(223, 8, 134, 20);
		contentPane.add(textCPF);
		
		textConta = new JTextField();
		textConta.setColumns(10);
		textConta.setBounds(49, 30, 134, 20);
		contentPane.add(textConta);
		
		textAgencia = new JTextField();
		textAgencia.setColumns(10);
		textAgencia.setBounds(59, 52, 134, 20);
		contentPane.add(textAgencia);
		
		textSaldo = new JTextField();
		textSaldo.setColumns(10);
		textSaldo.setBounds(49, 77, 134, 20);
		contentPane.add(textSaldo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(this);
		btnSalvar.setBounds(257, 55, 89, 23);
		contentPane.add(btnSalvar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 414, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Nome", "CPF", "Agencia", "Conta", "Saldo"}));
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		scrollPane.setViewportView(table);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSalvar) {
			try {
				actionPerformedBtnSalvarJButton(e);
				try {
					listarConta();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	protected void actionPerformedBtnSalvarJButton(ActionEvent e) throws IOException {
		String nome = textNome.getText();
        String cpf = textCPF.getText();
        int numC = Integer.parseInt(textConta.getText());
        int numAg = Integer.parseInt(textAgencia.getText());
        double saldo = Double.parseDouble(textSaldo.getText());
        
        GravadorDeDados gravadorDeDados = new GravadorDeDados();
        Cliente cliente = new Cliente(nome, cpf);
        Contas conta = new Contas(cliente, numC, numAg, saldo);
        gravadorDeDados.gravaContas(conta);
	}
	
	protected void listarConta() throws SQLException, IOException {

		GravadorDeDados gravadorDeDados = new GravadorDeDados();
		Cliente cliente = new Cliente(getWarningString(), getName());
		Contas contas =  gravadorDeDados.lerContas();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		model.addRow(new Object[] { contas.cliente.getNome(), contas.cliente.getCpf(), 
				     contas.getNumeroAgencia(), contas.getNumeroConta(), contas.getSaldo()

//		for (Contas c : contas) {
//			model.addRow(new Object[] { c.cliente.getNome(), c.cliente.getCpf(), 
//				     c.getNumeroAgencia(), c.getNumeroConta(), c.getSaldo()
//
			});
		}

	}

