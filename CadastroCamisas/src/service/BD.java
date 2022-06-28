package service;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD {
	
	public Connection con = null;//realiza a conexão banco.
	public PreparedStatement st = null; //executa instruções SQL.
	public ResultSet rs = null; //armazena as querys.
	
	public final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //driver de conexão banco
	public final String BANCO = "camisas";
	public final String URL = "jdbc:sqlserver://localhost:1433;databasename="+BANCO;
	public final String LOGIN = "sa";
	public final String SENHA = "123456789";
	
	/**
	 * Abre uma conexão com o banco de dados a partir dos dados definidos acima
	 * @return - true em caso de sucesso ou false caso contrário
	 */
	public boolean getConnection() {
		//carrega o driver
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,LOGIN,SENHA);
			System.out.println("Sucesso!!");
			return true;
		}
		catch(ClassNotFoundException erro) {
			System.out.println("Driver não encontrado!!!");
			return false;
		}
		catch (SQLException erro) {
			System.out.println("Falha na conexão !!!"+erro);
			return false;
		}
	}
	/**
	 * Encerra a conexão com o banco de dados
	 */
	public void close() {
		try {
			if(rs!=null) rs.close();
			
		}
		catch(SQLException erro) {
			
		}
		try {
			if(st!=null) st.close();
			
		}
		catch(SQLException erro) {
			
		}	try {
			if(con!=null) {
				con.close();
			
			System.out.println("Desconectou!!");
			}
		}
		catch(SQLException erro) {
			
		}
	}
	
	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		bd.close();
	}

}
