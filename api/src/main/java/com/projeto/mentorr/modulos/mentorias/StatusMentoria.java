package com.projeto.mentorr.modulos.mentorias;

public enum StatusMentoria {

	SOLICITADA("Solicitada"),
	RECUSADA("Recusada"),
	ATIVA("Ativa"),
	PAUSADA("Pausada"),
	FINALIZADA("Finalizada");

	private String descricao;

	private StatusMentoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
