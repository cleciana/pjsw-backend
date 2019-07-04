package main.java.com.example.demo.rest.dto;

/**
 * ContComentarios
 */
public class ContComentariosDTO implements Comparable<ContComentariosDTO> {

    private String description;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    private int quantidade;

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int compareTo(ContComentariosDTO cont) {
		if (this.getQuantidade() > cont.getQuantidade()) {
			return 1;

		} else if (this.getQuantidade() < cont.getQuantidade()) {
			return -1;
		}
		return 0;
	}
}