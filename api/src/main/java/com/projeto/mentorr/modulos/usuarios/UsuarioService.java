package com.projeto.mentorr.modulos.usuarios;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface UsuarioService {

	ListaPaginacaoDTO buscarUsuarios(String nome, Boolean ativo, Integer pagina, Integer totalPorPagina);

	UsuarioDTO buscarUsuarioLogado();
	
	Usuario buscarUsuarioLogadoPorApelido();

	Usuario salvar(CadastrarUsuarioDTO DTO);

	Usuario atualizar(EditarUsuarioDTO DTO);

}
