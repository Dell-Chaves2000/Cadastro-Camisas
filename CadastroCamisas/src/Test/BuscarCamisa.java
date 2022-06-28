package Test;

import model.Camisas;
import model.CamisasDAO;

public class BuscarCamisa {

	public static void main(String[] args) {

		CamisasDAO dao = new CamisasDAO();
		Camisas c = dao.localizar(1);
		if(c!=null) {
			System.out.println(c.toString());
		}
		else {
			System.out.println("Camisa não encontrada");
		}
	}

}
