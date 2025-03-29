package com.projeto.mentorr.modulos.mentores;

import java.util.ArrayList;
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
	public TotaisMentoresDTO buscarTotais() {
		Long qtdMentores = mentorRepository.countByAtivoIsTrue();
		Long qtdMentorias  = 0L;
		Long qtdPaises = 1L;
		
		return new TotaisMentoresDTO(qtdMentores, qtdMentorias, qtdPaises);
	}

	@Override
	public ListaPaginacaoDTO buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina) {
		ListaPaginacaoDTO busca = mentorRepository.buscarMentores(texto, cargo, empresa, tags, pagina, totalPorPagina);
		
		@SuppressWarnings("unchecked")
		List<ListaMentoresDTO> mentores = (List<ListaMentoresDTO>) busca.getLista();
		for(ListaMentoresDTO mentor: mentores) {
			mentor.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(mentor.getApelido(), 8));
		}
		
		return busca;
	}
	
	@Override
	public List<MentorDTO> buscarRecomendados() {
		List<MentorDTO> mentores = mentorRepository.buscarMentoresRecomendados();
		
		for(MentorDTO mentor: mentores) {
			mentor.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(mentor.getApelido(), 4));
		}
		
		return mentores;
	}

	@Override
	public MentorDTO buscarPorApelido(String apelido) {
		MentorDTO mentorDTO = mentorRepository.buscarPorApelido(apelido);
		
		mentorDTO.setHorarios(horarioMentorService.buscarHorariosPorApelidoMentor(apelido));
		mentorDTO.setPlanos(planoMentorService.buscarPlanosPorApelidoMentor(apelido));
		mentorDTO.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(apelido, 12));
		
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
		mentorDTO.setTags(tagMentorRepository.buscarTagsPorApelidoMentor(apelido, 12));
		
		return mentorDTO;
	}

	@Override
	public Mentor salvar(CadastroMentorDTO DTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		
		Mentor mentor = Mentor.builder()
				.foto(DTO.getFoto())
				.descricao(DTO.getDescricao())
				.cargo(DTO.getCargo())
				.empresa(DTO.getEmpresa())
				.dataInicio(DTO.getDataInicio())
				.ativo(false)
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
		
		return atualizacaoMentor(mentor, DTO);
	}
	
	@Override
	public Mentor atualizarPorId(Long idMentor, CadastroMentorDTO DTO) {
		Mentor mentor = buscarPorId(idMentor);
		
		return atualizacaoMentor(mentor, DTO);
	}
	
	@Override
	public void alterarStatus() {
		Usuario usuario = usuarioService.buscarUsuarioLogadoPorApelido();
		Mentor mentor = mentorRepository.findByUsuario_Id(usuario.getId());
		
		mentor.setAtivo(!mentor.getAtivo());
		mentorRepository.save(mentor);
	}
	
	@Override
	public void alterarStatusPorId(Long idMentor) {
		Mentor mentor = buscarPorId(idMentor);
		
		mentor.setAtivo(!mentor.getAtivo());
		mentorRepository.save(mentor);
	}
	
	private Mentor atualizacaoMentor(Mentor mentor, CadastroMentorDTO DTO) {
		mentor.setFoto(DTO.getFoto());
		mentor.setDescricao(DTO.getDescricao());
		mentor.setCargo(DTO.getCargo());
		mentor.setEmpresa(DTO.getEmpresa());
		mentor.setDataInicio(DTO.getDataInicio());
		
		mentor = mentorRepository.saveAndFlush(mentor);
		
		this.atualizarTags(mentor, DTO.getTags());
		
		return mentor;
	}
	
	private void salvarTags(Mentor mentor, List<Long> tags) {
		for (Long idTag: tags) {
			Tag tag = tagService.buscarPorId(idTag);
			
			TagMentor tagMentor = TagMentor.builder()
					.mentor(mentor)
					.tag(tag)
					.ordem((long) tags.indexOf(idTag))
					.build();
			
			tagMentorRepository.saveAndFlush(tagMentor);
		}
	}
	
	private void atualizarTags(Mentor mentor, List<Long> tags) {
		List<Long> ordens = new ArrayList<Long>(tags);
		List<TagMentor> tagsMentor = tagMentorRepository.findByMentor_Id(mentor.getId());
		
//		Filtrar removidas e atualizar ordem das antigas
		for (TagMentor tagMentor: tagsMentor) {
			Long idTag = tagMentor.getTag().getId();
			Boolean tagJaAdicionada = tags.contains(idTag);
			
			if (tagJaAdicionada) { 
				tagMentor.setOrdem((long) ordens.indexOf(idTag));
				tagMentorRepository.save(tagMentor);
				
				tags.remove(idTag);
			} else {
				tagMentorRepository.delete(tagMentor);
			}
		}
		
//		Adicionar novas
		for (Long idTag: tags) {
			Tag tag = tagService.buscarPorId(idTag);
			
			TagMentor tagMentor = TagMentor.builder()
					.mentor(mentor)
					.tag(tag)
					.ordem((long) ordens.indexOf(idTag))
					.build();
			
			tagMentorRepository.saveAndFlush(tagMentor);
		}
	}

}
