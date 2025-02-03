package com.projeto.mentorr.modulos.usuarios;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface UsuarioService {

	ListaPaginacaoDTO buscarUsuarios(String nome, String apelido, TipoUsuario tipo, Boolean ativo, Integer pagina, Integer totalPorPagina);

	Usuario buscarPorId(Long idUsuario);
	
	UsuarioDTO buscarUsuarioLogado();
	
	Usuario buscarUsuarioLogadoPorApelido();

	Usuario salvar(CadastrarUsuarioDTO DTO);

	Usuario atualizar(EditarUsuarioDTO DTO);
	
	Usuario atualizarPorId(Long idUsuario, EditarUsuarioDTO DTO);
	
	void excluirRestaurar();
	
	void alterarStatus(Long idUsuario);

}
