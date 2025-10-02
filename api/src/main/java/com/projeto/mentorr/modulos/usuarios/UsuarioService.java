package com.projeto.mentorr.modulos.usuarios;

import com.projeto.mentorr.util.ListaPaginacaoDTO;

public interface UsuarioService {

	ListaPaginacaoDTO<UsuarioDTO> buscarUsuarios(String nome, String apelido, TipoUsuario tipo, Boolean ativo, Integer pagina, Integer totalPorPagina);

	Usuario buscarPorId(Long id);

	Usuario buscarUsuarioLogado();

	Usuario salvar(CadastrarUsuarioDTO usuarioDTO, TipoUsuario tipo);

	Usuario atualizar(Long id, EditarUsuarioDTO usuarioDTO);

	Usuario atualizarUsuarioLogado(EditarUsuarioDTO usuarioDTO);

	void excluir(Long id);

	void ativarDesativar(Long id);

	void ativarDesativarUsuarioLogado();

}
