package com.projeto.mentorr.modulos.usuarios;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.BadRequestException;
import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.usuarios.role.Role;
import com.projeto.mentorr.modulos.usuarios.role.RoleRepository;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final RoleRepository roleRepository;
	private final UsuarioRepository usuarioRepository;

	@Override
	public ListaPaginacaoDTO<UsuarioDTO> buscarUsuarios(String nome, String apelido, TipoUsuario tipo, Boolean ativo, Integer pagina, Integer totalPorPagina) {
		return usuarioRepository.buscarUsuarios(nome, apelido, tipo, ativo, pagina, totalPorPagina);
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
	}

	@Override
	public Usuario buscarUsuarioLogado() {
		String apelido = UserUtil.retornarApelidoUsuarioLogado();
		return usuarioRepository.findFirstByApelido(apelido).orElseThrow(() -> new NotFoundException("Não foi possível buscar sua conta. Tente novamente."));
	}

	@Override
	public Usuario salvar(CadastrarUsuarioDTO usuarioDTO, TipoUsuario tipo) {
		if (usuarioRepository.existsByApelido(usuarioDTO.getApelido())) {
			throw new BadRequestException("Já existe um usuário cadastrado com esse apelido");
		}

		Long ROLE = tipo.getId();

		Set<Role> roles = new HashSet<>();
		roles.addAll(roleRepository.findAllById(List.of(ROLE)));

		Usuario usuario = Usuario.builder()
				.nome(usuarioDTO.getNome())
				.apelido(usuarioDTO.getApelido())
				.email(usuarioDTO.getEmail())
				.senha(bCryptPasswordEncoder().encode(usuarioDTO.getSenha()))
				.tipo(tipo)
				.ativo(true)
				.roles(roles)
				.build();

		return usuarioRepository.saveAndFlush(usuario);
	}

	@Override
	public Usuario atualizar(Long id, EditarUsuarioDTO usuarioDTO) {
		Usuario usuario = buscarPorId(id);
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());

		if (usuarioDTO.getApelido() != null) {
			if (usuarioRepository.existsByApelido(usuarioDTO.getApelido())) {
				throw new BadRequestException("Já existe um usuário cadastrado com esse apelido");
			}

			usuario.setApelido(usuarioDTO.getApelido());
		}

		if (usuarioDTO.getSenha() != null) {
			usuario.setSenha(bCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario atualizarUsuarioLogado(EditarUsuarioDTO usuarioDTO) {
		Usuario usuario = buscarUsuarioLogado();
		return atualizar(usuario.getId(), usuarioDTO);
	}

	@Override
	public void excluir(Long id) {
		Usuario usuario = buscarPorId(id);

		try {
			usuarioRepository.delete(usuario);
		} catch (Exception e) {
			throw new InternalErrorException("Não é possível excluir o usuário, pois existem dados vinculados");
		}
	}

	@Override
	public void ativarDesativar(Long id) {
		Usuario usuario = buscarPorId(id);

		usuario.setAtivo(!usuario.getAtivo());
		usuario.setDataDesativacao(!usuario.getAtivo() ? LocalDateTime.now() : null);

		usuarioRepository.save(usuario);
	}

	@Override
	public void ativarDesativarUsuarioLogado() {
		Usuario usuario = buscarUsuarioLogado();
		ativarDesativar(usuario.getId());
	}

	private BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
