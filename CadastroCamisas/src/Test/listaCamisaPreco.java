package Test;

import java.util.List;

import model.Camisas;
import model.CamisasDAO;

public class listaCamisaPreco {
	
	public static void main(String[] args) {
		CamisasDAO dao = new CamisasDAO();
		List<Camisas> lista = dao.get("select * from camisas where preco>60.00");
		
		for(Camisas c:lista) {
			System.out.println(c.getCodigo());
			System.out.println(c.getDescricao());
			System.out.println(c.getPreco());
		}
	}
}
