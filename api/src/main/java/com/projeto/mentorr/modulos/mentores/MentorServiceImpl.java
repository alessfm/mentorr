package com.projeto.mentorr.modulos.mentores;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioService;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorService;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorService;
import com.projeto.mentorr.modulos.tags.Tag;
import com.projeto.mentorr.modulos.tags.TagService;
import com.projeto.mentorr.modulos.mentores.tags.TagMentor;
import com.projeto.mentorr.modulos.mentores.tags.TagMentorRepository;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {
	
	private final TagMentorRepository tagMentorRepository;
	private final MentorRepository mentorRepository;
	private final UsuarioService usuarioService;
	private final TagService tagService;
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
		mentorDTO.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(apelido));
		
		return mentorDTO;
	}
	
	@Override
	public Mentor buscarPorId(Long id) {
		return mentorRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentor não encontrado"));
	}
	
	@Override
	public MentorDTO buscarMentorLogado() {
		String apelido = UserUtil.retornarApelidoUsuarioLogado();
		
		MentorDTO mentorDTO = mentorRepository.buscarMentorLogado(apelido);
		mentorDTO.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(apelido));
		
		return mentorDTO;
	}

	@Override
	public Mentor salvar(CadastroMentorDTO DTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		
		Mentor mentor = Mentor.builder()
				.descricao(DTO.getDescricao())
				.cargo(DTO.getCargo())
				.empresa(DTO.getEmpresa())
				.dataInicio(DTO.getDataInicio())
				.usuario(usuario)
				.build();
		
		mentor = mentorRepository.saveAndFlush(mentor);
		
		this.salvarTags(mentor, DTO.getTags());
		
		return mentor;
	}

	@Override
	public Mentor atualizar(CadastroMentorDTO DTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		Mentor mentor = mentorRepository.findByUsuario_Id(usuario.getId());

		mentor.setDescricao(DTO.getDescricao());
		mentor.setCargo(DTO.getCargo());
		mentor.setEmpresa(DTO.getEmpresa());
		mentor.setDataInicio(DTO.getDataInicio());
		
		mentor = mentorRepository.saveAndFlush(mentor);
		
		this.deletarTags(mentor);
		this.salvarTags(mentor, DTO.getTags());
		
		return mentor;
	}

	@Override
	public Long buscarTotalMentores() {
		return mentorRepository.countByUsuario_AtivoIsTrue();
	}
	
	public void salvarTags(Mentor mentor, List<Long> tags) {
		Long ordem = 0L;
		
		for (Long idTag: tags) {
			Tag tag = tagService.buscarPorId(idTag);
			
			TagMentor tagMentor = TagMentor.builder()
					.mentor(mentor)
					.tag(tag)
					.ordem(ordem)
					.build();
			
			tagMentorRepository.saveAndFlush(tagMentor);
			ordem++;
		}
	}
	
	public void deletarTags(Mentor mentor) {
		List<TagMentor> tags = tagMentorRepository.findByMentor_Id(mentor.getId());
		tagMentorRepository.deleteAll(tags);
	}

}
