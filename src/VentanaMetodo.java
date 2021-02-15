import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaMetodo extends JFrame implements KeyListener {

	private final JTextField txtInicial;
	private final JTextField txtIteraciones;
	private final JTextField textTolerancia, txtPrecision;
	private final JTable table;
	private final JLabel lblResultado;

	public int ecuacion=0;
	public int precision=5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaMetodo frame = new VentanaMetodo();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMetodo() {
		setTitle("Metodo Newton");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 884, 245);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seleccione Ecuaci\u00F3n");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblNewLabel.setBounds(275, 11, 305, 39);
		panel.add(lblNewLabel);

		JRadioButton rdbtnEcua1 = new JRadioButton("");
		rdbtnEcua1.addActionListener(arg0 -> set_ecuacion(1));
		rdbtnEcua1.setForeground(new Color(0, 0, 0));
		rdbtnEcua1.setFont(new Font("Cambria Math", Font.BOLD, 16));
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnEcua1);
		rdbtnEcua1.setBackground(new Color(186, 85, 211));
		rdbtnEcua1.setBounds(79, 57, 21, 39);
		panel.add(rdbtnEcua1);

		JRadioButton rdbtnEcua2 = new JRadioButton("");
		rdbtnEcua2.addActionListener(arg0 -> set_ecuacion(2));
		rdbtnEcua2.setFont(new Font("Cambria Math", Font.BOLD, 16));
		buttonGroup.add(rdbtnEcua2);
		rdbtnEcua2.setBackground(new Color(152, 251, 152));
		rdbtnEcua2.setBounds(328, 57, 21, 39);
		panel.add(rdbtnEcua2);

		JRadioButton rdbtnEcua3 = new JRadioButton("");
		rdbtnEcua3.addActionListener(arg0 -> set_ecuacion(3));
		rdbtnEcua3.setFont(new Font("Cambria Math", Font.BOLD, 16));
		buttonGroup.add(rdbtnEcua3);
		rdbtnEcua3.setBackground(new Color(95, 158, 160));
		rdbtnEcua3.setBounds(575, 57, 21, 39);
		panel.add(rdbtnEcua3);

		txtInicial = new JTextField();
		txtInicial.setBounds(79, 132, 170, 30);
		txtInicial.addKeyListener(this);
		panel.add(txtInicial);
		txtInicial.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Punto Inicial Xi");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.BOLD, 18));
		lblNewLabel_1.setBounds(89, 103, 134, 30);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tolerancia (Error)");
		lblNewLabel_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1.setFont(new Font("Cambria Math", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(324, 103, 156, 30);
		panel.add(lblNewLabel_1_1);

		textTolerancia = new JTextField();
		textTolerancia.setColumns(10);
		textTolerancia.setBounds(321, 132, 170, 30);
		textTolerancia.addKeyListener(this);
		panel.add(textTolerancia);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(arg0 -> CalcularNewtonRaphson());
		btnCalcular.setFont(new Font("Cambria Math", Font.BOLD, 14));
		btnCalcular.setForeground(new Color(0, 0, 255));
		btnCalcular.setBackground(new Color(192, 192, 192));
		btnCalcular.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/calculator_full.png")));
		btnCalcular.setBounds(328, 181, 156, 53);
		panel.add(btnCalcular);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/ecuacion1.png")));
		lblNewLabel_2.setBounds(101, 57, 115, 40);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Precision Decimales");
		lblNewLabel_1_1_1.setForeground(Color.BLUE);
		lblNewLabel_1_1_1.setFont(new Font("Cambria Math", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(566, 103, 156, 30);
		panel.add(lblNewLabel_1_1_1);

		txtPrecision = new JTextField();
		txtPrecision.setColumns(10);
		txtPrecision.setBounds(552, 132, 170, 30);
		txtPrecision.addKeyListener(this);
		panel.add(txtPrecision);

		JLabel lblNewLabel_1_2 = new JLabel("Iteraciones");
		lblNewLabel_1_2.setForeground(Color.BLUE);
		lblNewLabel_1_2.setFont(new Font("Cambria Math", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(89, 174, 134, 30);
		panel.add(lblNewLabel_1_2);

		txtIteraciones = new JTextField();
		txtIteraciones.setColumns(10);
		txtIteraciones.setBounds(79, 204, 170, 30);
		txtIteraciones.addKeyListener(this);
		panel.add(txtIteraciones);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/ecuacion2.png")));
		lblNewLabel_2_1.setBounds(350, 57, 115, 40);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/ecuacion3.png")));
		lblNewLabel_2_1_1.setBounds(596, 57, 115, 40);
		panel.add(lblNewLabel_2_1_1);


		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 245, 884, 230);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBackground(new Color(169, 169, 169));
		table.setForeground(new Color(0, 0, 255));
		table.setFont(new Font("Cambria Math", Font.BOLD, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"i", "xi", "f(xi)", "f'(xi)", "xi-xi-1"
			}
		) {
			final Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(221, 160, 221));
		panel_2.setBounds(0, 475, 884, 86);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblnewlabll = new JLabel("Raíz -->");
		lblnewlabll.setBounds(10, 11, 305, 51);
		panel_2.add(lblnewlabll);
		lblnewlabll.setForeground(new Color(128, 0, 128));
		lblnewlabll.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblnewlabll.setBackground(Color.WHITE);
		
		lblResultado = new JLabel("");
		lblResultado.setForeground(new Color(128, 0, 128));
		lblResultado.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblResultado.setBackground(Color.WHITE);
		lblResultado.setBounds(325, 11, 305, 51);
		panel_2.add(lblResultado);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(arg0 -> {
			int res= JOptionPane.showConfirmDialog(null, "¿Esta seguro de Finalizar la Aplicacion?","Finalizar Aplicaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(res==0) System.exit(0);
		});
		btnSalir.setFont(new Font("Cambria Math", Font.BOLD, 16));
		btnSalir.setBackground(new Color(192, 192, 192));
		btnSalir.setForeground(new Color(128, 0, 128));
		btnSalir.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/exit.png")));
		btnSalir.setBounds(650, 11, 140, 51);
		panel_2.add(btnSalir);

		JButton btnInfo = new JButton();
		btnInfo.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(null, "\nAymar Eduardo Reina \nLuis Anderson Cardenas \nCristian Contreras \nChristian Vidal \nJesús Zambrano", "Integrantes", JOptionPane.INFORMATION_MESSAGE);
		});
		btnInfo.setFont(new Font("Cambria Math", Font.BOLD, 16));
		btnInfo.setBackground(new Color(192, 192, 192));
		btnInfo.setForeground(new Color(128, 0, 128));
		btnInfo.setIcon(new ImageIcon(VentanaMetodo.class.getResource("/Logos/info.jpeg")));
		btnInfo.setBounds(800, 11, 50, 50);

		panel_2.add(btnInfo);
	}

	public void set_ecuacion(int e){
		this.ecuacion = e;
	}
	public void printTable(DefaultTableModel modelo,Iteracion data){
		Object[] datos = {"","","","",""};
		modelo.addRow(datos);
		table.setValueAt(String.valueOf(data.iteracion) , table.getRowCount()-1,0);
		table.setValueAt(String.format("%."+precision+"f",data.x), table.getRowCount()-1,1);
		table.setValueAt(String.format("%."+precision+"f",data.fx), table.getRowCount()-1,2);
		table.setValueAt(String.format("%."+precision+"f",data.fx_derivada), table.getRowCount()-1,3);
		if(data.error != 0)
			table.setValueAt(String.format("%."+precision+"f",data.error), table.getRowCount()-1,4);
		else
			table.setValueAt("No Aplica", table.getRowCount()-1,4);
	}

	public void CalcularNewtonRaphson(){

		try{
			DefaultTableModel modelo = (DefaultTableModel) table.getModel(); // Obtenemos el modelo de la tabla
			int rowCount = modelo.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelo.removeRow(i); // Eliminar los resultados anteriores
			}
			NewtonRaphson newtonRaphson = new NewtonRaphson(this.ecuacion); // Iniciamos el objeto con el algoritmo y pasamos la ecuación
			//Obtenemos los datos del usuarios
			double xi = Double.parseDouble(txtInicial.getText());
			double tolerancia = Double.parseDouble(textTolerancia.getText());
			int iteraciones = Integer.parseInt(txtIteraciones.getText());
			this.precision = Integer.parseInt(txtPrecision.getText());

			Double resultado = newtonRaphson.MetodoNewtonRaphson(xi, tolerancia, iteraciones); // Calculamos la Raiz con el Metodo de Newton Rapshon

			//Dibujar Tabla con el ForEach
			newtonRaphson.tabla_iteraciones.forEach(
					(iteracion) -> printTable(modelo, iteracion) // Modelo la tabla y los datos
			);

			String resultado_text = String.format("%."+precision+"f", resultado);
			if(newtonRaphson.converge)
			{
				lblResultado.setText(resultado_text+"\n La función Converge a esta raíz.");
			}
			else
			{
				lblResultado.setText("La función Diverge");
				JOptionPane.showMessageDialog(null, "El resultado Diverge! Intente otro Punto Inicial", "Metodo Newton", JOptionPane.ERROR_MESSAGE);
			}

		}catch (Exception error)
		{
			if(this.ecuacion == 0)
				JOptionPane.showMessageDialog(null, "Por favor seleccione una ecuación", "Metodo Newton", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "Por favor Ingrese datos en los campos vacios", "Metodo Newton", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		char caracter = e.getKeyChar();
		if(Character.isLetter(caracter))
		{
			JOptionPane.showMessageDialog(null, "Por favor Ingrese números", "Metodo Newton", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}
}
