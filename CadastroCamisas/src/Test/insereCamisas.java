package Test;

import java.util.List;

import model.Camisas;
import model.CamisasDAO;

public class insereCamisas {

		public static void main(String[] args) {
			
			Camisas c = new Camisas(1, "Camisa Polo", 59.80);
			CamisasDAO dao = new CamisasDAO();
				
			c.setCodigo(c.getCodigo());
			c.setDescricao(c.getDescricao());
			c.setPreco(c.getPreco());
			System.out.println(dao.incluir(c));
		}
		
	}
	

