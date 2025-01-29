package com.projeto.mentorr.modulos.mentores;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioService;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorService;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorService;
import com.projeto.mentorr.modulos.mentores.tags.Tag;
import com.projeto.mentorr.modulos.mentores.tags.TagRepository;
import com.projeto.mentorr.util.ListaPaginacaoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {
	
	private final TagRepository tagRepository;
	private final MentorRepository mentorRepository;
	private final UsuarioService usuarioService;
	private final HorarioMentorService horarioMentorService;
	private final PlanoMentorService planoMentorService;

	@Override
	public ListaPaginacaoDTO buscarMentores(String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina) {
		return mentorRepository.buscarMentores(cargo, empresa, tags, pagina, totalPorPagina);
	}

	@Override
	public MentorDTO buscarPorApelido(String apelido) {
		MentorDTO mentorDTO = mentorRepository.buscarPorApelido(apelido);
		
		mentorDTO.setHorarios(horarioMentorService.buscarHorariosPorApelidoMentor(apelido));
		mentorDTO.setPlanos(planoMentorService.buscarPlanosPorApelidoMentor(apelido));
		mentorDTO.setTags(null);
		
		return mentorDTO;
	}
	
	@Override
	public Mentor buscarMentorLogado() {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		return mentorRepository.findByUsuario_Id(usuario.getId());
	}

	@Override
	public Mentor salvar(CadastroMentorDTO DTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		
		Set<Tag> tags = new HashSet<>();
		tags.addAll(tagRepository.findAllById(DTO.getTags()));
		
		Mentor mentor = Mentor.builder()
				.descricao(DTO.getDescricao())
				.cargo(DTO.getCargo())
				.empresa(DTO.getEmpresa())
				.dataInicio(DTO.getDataInicio())
				.usuario(usuario)
				.tags(tags)
				.build();
		
		return mentorRepository.saveAndFlush(mentor);
	}

	@Override
	public Mentor atualizar(CadastroMentorDTO DTO) {
		Mentor mentor = buscarMentorLogado();
		
		Set<Tag> tags = new HashSet<>();
		tags.addAll(tagRepository.findAllById(DTO.getTags()));

		mentor.setDescricao(DTO.getDescricao());
		mentor.setCargo(DTO.getCargo());
		mentor.setEmpresa(DTO.getEmpresa());
		mentor.setDataInicio(DTO.getDataInicio());
		mentor.setTags(tags);
		
		return mentorRepository.saveAndFlush(mentor);
	}
	
	@Override
	public Mentor buscarPorId(Long id) {
		return mentorRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentor não encontrado"));
	}

	@Override
	public Long buscarTotalMentores() {
		return 0L;
//		return mentorRepository.countById();
	}

}
