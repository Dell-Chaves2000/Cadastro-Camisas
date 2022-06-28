package Test;

import model.CamisasDAO;

public class excluiCamisa {
	
	public static void main(String[] args) {
		CamisasDAO dao = new CamisasDAO();
		System.out.println(dao.excluir(4));
		}
	}
