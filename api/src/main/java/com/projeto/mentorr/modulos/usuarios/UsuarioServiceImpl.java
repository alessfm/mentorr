package com.projeto.mentorr.modulos.usuarios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.BadRequestException;
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
	public ListaPaginacaoDTO buscarUsuarios(String nome, Boolean ativo, Integer pagina, Integer totalPorPagina) {
		return usuarioRepository.buscarUsuarios(nome, ativo, pagina, totalPorPagina);
	}

	@Override
	public UsuarioDTO buscarUsuarioLogado() {
		return usuarioRepository.buscarUsuarioPorApelido(UserUtil.retornarApelidoUsuarioLogado());
	}
	
	@Override
	public Usuario buscarUsuarioLogadoPorApelido() {
		return usuarioRepository.findFirstByApelido(UserUtil.retornarApelidoUsuarioLogado()).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
	}

	@Override
	public Usuario salvar(CadastrarUsuarioDTO DTO) {
		UsuarioDTO usuarioCadastrado = usuarioRepository.buscarUsuarioPorApelido(DTO.getApelido());

		if (usuarioCadastrado != null) {
			throw new BadRequestException("Já existe um usuário cadastrado com esse apelido, digite um diferente");
		}
		
		Long idRole = DTO.getTipo() == TipoUsuario.ALUNO ? 2L : 3L;
		
		Set<Role> roles = new HashSet<>();
		roles.addAll(roleRepository.findAllById(List.of(idRole)));
		
		Usuario usuario = Usuario.builder()
				.nome(DTO.getNome())
				.apelido(DTO.getApelido())
				.email(DTO.getEmail())
				.senha(bCryptPasswordEncoder().encode(DTO.getSenha()))
				.tipo(DTO.getTipo())
				.ativo(true)
				.roles(roles)
				.build();
		
		return usuarioRepository.saveAndFlush(usuario);
	}
	
	@Override
	public Usuario atualizar(EditarUsuarioDTO DTO) {
		Usuario usuario = buscarUsuarioLogadoPorApelido();

		usuario.setNome(DTO.getNome());
		usuario.setEmail(DTO.getEmail());
		
		if (DTO.getSenha() != null) {
			usuario.setSenha(bCryptPasswordEncoder().encode(DTO.getSenha()));
		}
		
		return usuarioRepository.save(usuario);
	}
	
	private BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
