package com.projeto.mentorr.modulos.usuarios;

public enum TipoUsuario {

	GESTAO(1L, "Gestão"),
	ALUNO(2L, "Aluno"),
	MENTOR(3L, "Mentor"),
	ALUNO_MENTOR(4L, "Aluno/Mentor");

	private Long id;
	private String descricao;

	private TipoUsuario(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
