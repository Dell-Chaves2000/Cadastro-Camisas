package model;



public class Camisas {
	
	private int codigo;
	private String descricao;
	private Double preco;
	
	public Camisas(int codigo, String descricao, Double preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		
	}
	
	public Camisas() {
		
	}

	public String toString() {
		return codigo+";"+descricao+";"+preco;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
