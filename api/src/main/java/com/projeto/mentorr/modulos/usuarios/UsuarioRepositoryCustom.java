package com.projeto.mentorr.modulos.usuarios;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface UsuarioRepositoryCustom {

	ListaPaginacaoDTO buscarUsuarios(String nome, Boolean ativo, Integer pagina, Integer totalPorPagina);

	UsuarioDTO buscarUsuarioPorApelido(String apelido);

}
