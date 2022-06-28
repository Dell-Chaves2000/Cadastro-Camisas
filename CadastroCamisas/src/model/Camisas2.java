package model;



public class Camisas2 {
	
	private String codigo;
	private String descricao;
	private String preco;
		
	public Camisas2(String codigo, String descricao, String preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		
	}
	
	public Camisas2() {
		
	}

	public String toString() {
		return codigo+";"+descricao+";"+preco;
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}
