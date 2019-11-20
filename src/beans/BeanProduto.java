package beans;

public class BeanProduto {
	
	private Long idProduto;
	private String nome;
	private Double quantidade;
	private Double valor;
	private String valorEmTexto;
	private Long idCategoria;
	
	
	
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setValorEmTexto(String valorEmTexto) {
		this.valorEmTexto = valorEmTexto;
	}

	public String getValorEmTexto() {
		valorEmTexto = valor.toString().replace(".", ",");
		return valorEmTexto;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
