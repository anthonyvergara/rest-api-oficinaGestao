package com.api.oficina.dto;

public class OficinaResponseDTO {

	private Long id;
	private String nomeOficina;
	private Long companyNumber;
	private Long vatNumber;
	private String email;
	private String telefone;
	private Endereco endereco;

	// Classe interna para o endereço
	public static class Endereco {
		private Long id;
		private String rua;
		private int numero;
		private String cidade;
		private String postcode;

		public Endereco() {
		}

		public Endereco(Long id, String rua, int numero, String cidade, String postcode) {
			this.id = id;
			this.rua = rua;
			this.numero = numero;
			this.cidade = cidade;
			this.postcode = postcode;
		}

		// Getters and Setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRua() {
			return rua;
		}

		public void setRua(String rua) {
			this.rua = rua;
		}

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getPostcode() {
			return postcode;
		}

		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
	}

	// Constructors
	public OficinaResponseDTO() {
	}

	public OficinaResponseDTO(Long id, String nomeOficina, Long companyNumber, Long vatNumber,
	                          String email, String telefone, Endereco endereco) {
		this.id = id;
		this.nomeOficina = nomeOficina;
		this.companyNumber = companyNumber;
		this.vatNumber = vatNumber;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeOficina() {
		return nomeOficina;
	}

	public void setNomeOficina(String nomeOficina) {
		this.nomeOficina = nomeOficina;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Long getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(Long vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
