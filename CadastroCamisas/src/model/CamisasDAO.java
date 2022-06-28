package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.BD;

public class CamisasDAO {
	
	private String sql,men;
	private BD bd;
	
	public CamisasDAO() {
		bd = new BD();
	}
	/**
	 * Realiza  o cadastro das camisas
	 * @param f - Objeto do tipo 
	 * @return - Mensagem com o resultado da operação
	 */
	public String incluir(Camisas c) {
		sql = "insert into camisas (codigo,descricao,preco) values (?, ?, ?)";
		bd.getConnection();
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, c.getCodigo());
			bd.st.setString(2, c.getDescricao());
			bd.st.setDouble(3, c.getPreco());
			bd.st.executeUpdate();
			men = "Camisa cadastrada com sucesso!";
		}
		catch(SQLException erro) {
			
			sql = "update camisas set descricao=?, preco=? where codigo = ?";
			bd.getConnection();
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(3,c.getCodigo());
				bd.st.setString(1,c.getDescricao());
				bd.st.setDouble(2,c.getPreco());
				bd.st.executeUpdate();
				men = "Camisa atualizada com sucesso"; 
			}
			catch(SQLException e) {
				men = "Não foi possivel atualizar a camisa!"+e; 
			}
		
		finally {
			bd.close();
		}
		return men;
			
			}
		finally {
			bd.close();
		}
		return men;
	}
	/**
	 * Realiza a exclusão de uma camisa
	 * @param codigo - Paramentro utilizado para identificar a camisa a ser excluida
	 * @return - Mensagem com o resultado da operação
	 */
	public String excluir(int codigo) {
		sql = "delete camisas where codigo = ?";
		bd.getConnection();
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,codigo);
			if(bd.st.executeUpdate()==1)
				men = "Camisa excluída com sucesso!";
			else
				men = "Camisa não foi encontrada!";
		}
		catch(SQLException erro) {
			men = "Falha na exclusão "+erro; 
		}
		finally {
			bd.close();
		}
		return men;
	}
	/**
	 * Localiza uma camisa partir do seu código.
	 * @param codigo - O código da camisa a ser localizada.
	 * @return - O produto na forma de um objeto ou nulo caso não encontrado.
	 */
	public Camisas localizar(int codigo) {
		Camisas c = new Camisas();
		sql = "select * from camisas where codigo = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,codigo);
			bd.rs = bd.st.executeQuery();
			if(bd.rs.next()) {
				c.setCodigo(bd.rs.getInt(1));
				c.setDescricao(bd.rs.getString(2));
				c.setPreco(bd.rs.getDouble(3));
			}
			else {
				c = null;
			}
		}
		catch(SQLException erro) {
			c = null;
		}
		finally {
			bd.close();
		}
			return c;
		}
	/**
	 * Gera a listagem de camisas em um arquivo PDF.
	 * @param sql - Parametro utilizado para gerar uma listagem das camisas a partir do BD.
	 * @return - Retorna a lista de camisas.
	 */
	public ArrayList<Camisas2> listarCamisas() {
		ArrayList<Camisas2> c2 = new ArrayList<>();
		String sql = "select * from camisas";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				String codigo = bd.rs.getString(1);
				String Descricao = bd.rs.getString(2);
				String Preco = bd.rs.getString(3);
				c2.add(new Camisas2(codigo, Descricao, Preco));

			}

		} catch (Exception ear) {
			c2 = null;
			System.out.println(ear);
		} finally {
			bd.close();
		}
		return c2;

	}
	
	/**
	 * Gera a listagem de camisas para ser consultada
	 * @param sql - Parametro utilizado para gerar uma listagem das camisas
	 * @return - Mensagem com o resultado da operação
	 */
	public List<Camisas> get(String sql){
		List<Camisas> lista = new ArrayList<Camisas>();
		bd.getConnection();
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			while(bd.rs.next()) {
				lista.add(new Camisas(
							bd.rs.getInt(1),
							bd.rs.getString(2), 
							bd.rs.getDouble(3))
				);
			}
		}
		catch(SQLException erro) {
			lista = null;
			System.out.println(erro);
		}
		finally {
			bd.close();
		}
		return lista;
	}
	
}
