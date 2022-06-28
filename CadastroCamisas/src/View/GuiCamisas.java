package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Camisas;
import model.Camisas2;
import model.CamisasDAO;
import service.BD;
import service.MyTableModel;

public class GuiCamisas extends JPanel {

	private JButton btLimpar;
	private JButton btIncluir;
	private JButton btExcluir;
	private JButton btBuscar;
	private JButton btImprimir;
	private JLabel lbCodigo;
	private JLabel lbDescricao;
	private JLabel lbPreco;
	private JLabel lbLocalizar;
	private JTextField tfCodigo;
	private JTextField tfDescricao;
	private JTextField tfPreco;
	private JTextArea tfaAplicacao;
	private CamisasDAO dao;
	private Camisas c;

	private JTextField tfLocalizar;
	private JTable table;
	private DefaultTableModel model;
	private BD bd;

	public GuiCamisas() {
		dao = new CamisasDAO();
		c = new Camisas();
		bd = new BD();
		bd.getConnection();

		inicializarComponentes();
		definirEventos();

	}

	public void inicializarComponentes() {
		btLimpar = new JButton("Limpar");
		btIncluir = new JButton("Salvar");
		btExcluir = new JButton("Excluir");
		btBuscar = new JButton("Buscar");
		btImprimir = new JButton("Imprimir");
		lbCodigo = new JLabel("Codigo da camisa:");
		lbDescricao = new JLabel("Descrição da camisa:");
		lbPreco = new JLabel("Preço da Camisa:");
		lbLocalizar = new JLabel("Localizar:");
		tfaAplicacao = new JTextArea("Camisaria");
		tfCodigo = new JTextField(5);
		tfDescricao = new JTextField(50);
		tfPreco = new JTextField(10);
		tfLocalizar = new JTextField(10);
		table = new JTable();

		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		setBackground(new Color(175, 251, 175));

		add(tfaAplicacao);
		add(btLimpar);
		add(btIncluir);
		add(btExcluir);
		add(btBuscar);
		add(btImprimir);
		add(lbCodigo);
		add(lbDescricao);
		add(lbPreco);
		add(lbLocalizar);
		add(tfCodigo);
		add(tfDescricao);
		add(tfPreco);

		btLimpar.setBounds(20, 550, 100, 25);
		btLimpar.setBackground(Color.YELLOW);
		btLimpar.setForeground(Color.BLACK);
		btLimpar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btIncluir.setBounds(150, 550, 150, 25);
		btIncluir.setBackground(Color.green);
		btIncluir.setForeground(Color.BLACK);
		btIncluir.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btExcluir.setBounds(340, 550, 100, 25);
		btExcluir.setBackground(new Color(255, 20, 20));
		btExcluir.setForeground(Color.BLACK);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btBuscar.setBounds(175, 240, 130, 25);
		btBuscar.setBackground(new Color(201, 250, 20));
		btBuscar.setForeground(Color.BLACK);
		btImprimir.setBounds(490, 550, 120, 25);
		btImprimir.setBackground(Color.WHITE);
		btImprimir.setForeground(Color.BLACK);
		btImprimir.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lbCodigo.setBounds(20, 200, 130, 25);
		lbCodigo.setForeground(Color.BLACK);
		lbCodigo.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lbDescricao.setBounds(220, 200, 130, 25);
		lbDescricao.setForeground(Color.BLACK);
		lbDescricao.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lbPreco.setBounds(495, 200, 170, 25);
		lbPreco.setForeground(Color.BLACK);
		lbPreco.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lbLocalizar.setBounds(20, 240, 130, 25);
		lbLocalizar.setForeground(Color.BLACK);
		lbLocalizar.setFont(new Font("Times New Roman", Font.ITALIC, 13));

		tfaAplicacao.setBounds(200, 20, 800, 150);
		tfaAplicacao.setBackground(new Color(175, 251, 175));
		tfaAplicacao.setFont(new Font("Times New Roman", Font.BOLD, 80));
		tfaAplicacao.setFont(new Font("Times New Roman", Font.ITALIC, 80));
		tfaAplicacao.setForeground(new Color(50, 80, 255));
		tfaAplicacao.setEditable(false);
		tfCodigo.setBounds(125, 200, 80, 25);
		tfDescricao.setBounds(340, 200, 150, 25);
		tfPreco.setBounds(595, 200, 100, 25);

		table = new JTable();
		atualizarGrade();
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(20, 270, 675, 270);
		add(scroll);
		tfLocalizar.setBounds(80, 240, 80, 25);
		add(tfLocalizar);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Camisas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GuiCamisas());
		frame.pack();
		frame.setVisible(true);
	}

	private void atualizarGrade() {

		model = MyTableModel.getModel(bd, "select * from camisas");
		table.setModel(model);
		ajustarGrade();

	}

	private void ajustarGrade() {

		DefaultTableCellRenderer alinhaCentro = new DefaultTableCellRenderer();
		alinhaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer alinhaEsquerda = new DefaultTableCellRenderer();
		alinhaEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
		table.getColumnModel().getColumn(0).setPreferredWidth(3);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setCellRenderer(alinhaCentro);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(alinhaEsquerda);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaEsquerda);

		table.getTableHeader().setReorderingAllowed(false);

	}

	private void limparCampos() {
			tfCodigo.setText("");
			tfDescricao.setText("");
			tfPreco.setText("");
			tfLocalizar.setText("");
			tfCodigo.requestFocus();
		}

	public void definirEventos() {
			 table.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					tfCodigo.setText((String)table.getValueAt(table.getSelectedRow(), 0));
					tfDescricao.setText((String)table.getValueAt(table.getSelectedRow(), 1));
					tfPreco.setText((String)table.getValueAt(table.getSelectedRow(), 2));
					
				}
			});
		
			btLimpar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					limparCampos();
					
				}
			});
			btIncluir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					c.setCodigo(Integer.parseInt(tfCodigo.getText()));
					c.setDescricao(tfDescricao.getText());
					c.setPreco(Double.parseDouble(tfPreco.getText()));
					if(Double.parseDouble(tfPreco.getText())>=0)
					JOptionPane.showMessageDialog(btIncluir, dao.incluir(c));
					else
					JOptionPane.showMessageDialog(btIncluir, "Camisa não cadastrada preço digitado esta negativo");
					atualizarGrade();
					limparCampos();
				}
			});
			
			btExcluir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int r = JOptionPane.showConfirmDialog(btExcluir, "Tem certeza na exclusão da camisa?");
					if(r==0) {
						
					JOptionPane.showMessageDialog(btExcluir, dao.excluir(Integer.parseInt(tfCodigo.getText())));
					limparCampos();
					atualizarGrade();
					}
				}
			});
			
			tfLocalizar.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					try{ 
						String sql = "select * from camisas where codigo = '"+Integer.parseInt(tfLocalizar.getText())+"'";
						model = MyTableModel.getModel(bd, sql);
						table.setModel(model);
						ajustarGrade();
						
					}
				
					catch(Exception erro){
						
					}
				}
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
					
					
				
			});
			btBuscar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					c = dao.localizar(Integer.parseInt(tfCodigo.getText()));
					if(c!=null) {
						tfDescricao.setText(c.getDescricao());
						tfPreco.setText(""+c.getPreco());
					}
					else {
						JOptionPane.showMessageDialog(btBuscar, "Produto não encontrado!");
						limparCampos();
					}
				}
			});				
			btImprimir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Document documento = null;
					FileOutputStream outPutStream = null;
					try {
						documento = new Document(PageSize.A4,30,20,20,30);
						outPutStream = new FileOutputStream("E:\\Fatec\\3° Semestre\\Programação orientada a objetos\\Programas"
								+ "\\CadastroCamisas\\ListaCamisas.pdf");
						try {
							PdfWriter.getInstance(documento, outPutStream);
							documento.open();
							Paragraph paragrafo = new Paragraph("Lista de camisas");
							paragrafo.setAlignment(Element.ALIGN_CENTER);
							Paragraph paragrafo1 = new Paragraph(" ");
							Paragraph paragrafo2 = new Paragraph(" ");
							documento.add(paragrafo);
							documento.add(paragrafo1);
							documento.add(paragrafo2);
							PdfPTable tabela = new PdfPTable(3);
							tabela.addCell("Código");
							tabela.addCell("Descrição");
							tabela.addCell("Preço");
							ArrayList<Camisas2> c2 = dao.listarCamisas();
							for(int i =0;i<c2.size();i++) {
								tabela.addCell(c2.get(i).getCodigo());
								tabela.addCell(c2.get(i).getDescricao());
								tabela.addCell(c2.get(i).getPreco());
							
							}
							documento.add(tabela);
							JOptionPane.showMessageDialog(btImprimir, "Listagem gerada com sucesso!");
							}
						catch(DocumentException d) {
							JOptionPane.showMessageDialog(btImprimir, "Não foi possivel gerar a listagem!");
						}
					} 
					catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally {
						if(documento!=null) {
							documento.close();
						}
					}
						if(outPutStream!=null) {
							try {
								outPutStream.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
			}
	}

