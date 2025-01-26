package com.projeto.mentorr.modulos.usuarios;

public enum TipoUsuario {
	
	ALUNO("Aluno"), MENTOR("Mentor"), ALUNO_MENTOR("Aluno/Mentor"), GESTAO("Gestão");
	
	private String descricao;

	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}