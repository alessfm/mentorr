package com.projeto.mentorr.modulos.mentores.planos;

public enum TipoPlano {

	BASE("Base"),
	PADRAO("Padrão"),
	PRO("Pro"),
	CUSTOM("Customizado");

	private String descricao;

	private TipoPlano(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
