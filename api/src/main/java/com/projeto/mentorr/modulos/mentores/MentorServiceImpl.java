package com.projeto.mentorr.modulos.mentores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.avaliacoes.AvaliacaoMentorDTO;
import com.projeto.mentorr.modulos.mentores.avaliacoes.AvaliacaoMentorRepository;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorDTO;
import com.projeto.mentorr.modulos.mentores.horarios.HorarioMentorRepository;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorDTO;
import com.projeto.mentorr.modulos.mentores.planos.PlanoMentorRepository;
import com.projeto.mentorr.modulos.mentores.tags.TagMentor;
import com.projeto.mentorr.modulos.mentores.tags.TagMentorRepository;
import com.projeto.mentorr.modulos.tags.Tag;
import com.projeto.mentorr.modulos.tags.TagService;
import com.projeto.mentorr.modulos.usuarios.Usuario;
import com.projeto.mentorr.modulos.usuarios.UsuarioService;
import com.projeto.mentorr.util.ListaPaginacaoDTO;
import com.projeto.mentorr.util.UserUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {

	private final AvaliacaoMentorRepository avaliacaoMentorRepository;
	private final HorarioMentorRepository horarioMentorRepository;
	private final MentorRepository mentorRepository;
	private final PlanoMentorRepository planoMentorRepository;
	private final TagMentorRepository tagMentorRepository;

	private final TagService tagService;
	private final UsuarioService usuarioService;

	@Override
	public TotaisMentoresDTO buscarTotais() {
		Long qtdMentores = mentorRepository.countByAtivoIsTrue();
		Long qtdMentorias  = 0L;
		Long qtdPaises = 1L;

		return new TotaisMentoresDTO(qtdMentores, qtdMentorias, qtdPaises);
	}

	@Override
	public ListaPaginacaoDTO<MentorDTO> buscarMentores(String texto, String cargo, String empresa, List<Long> tags, Integer pagina, Integer totalPorPagina) {
		ListaPaginacaoDTO<MentorDTO> busca = mentorRepository.buscarMentores(texto, cargo, empresa, tags, pagina, totalPorPagina);

		for(MentorDTO mentor: busca.getLista()) {
			mentor.setTags(tagMentorRepository.buscarTagsMentorPublic(mentor.getApelido(), 8));
		}

		return busca;
	}

	@Override
	public List<MentorDTO> buscarMentoresDestaque() {
		List<MentorDTO> mentores = mentorRepository.buscarMentoresDestaque();

		for(MentorDTO mentor: mentores) {
			mentor.setTags(tagMentorRepository.buscarTagsMentorPublic(mentor.getApelido(), 3));
		}

		return mentores;
	}

	@Override
	public List<MentorDTO> buscarSimilaresMentor(String apelido, String cargo, String empresa) {
		List<MentorDTO> mentores = mentorRepository.buscarSimilaresMentor(apelido, cargo, empresa);

		for(MentorDTO mentor: mentores) {
			mentor.setTags(tagMentorRepository.buscarTagsMentorPublic(mentor.getApelido(), 3));
		}

		return mentores;
	}

	@Override
	public Mentor buscarPorId(Long id) {
		return mentorRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentor não encontrado"));
	}

	@Override
	public Mentor buscarMentorLogado() {
		String apelido = UserUtil.retornarApelidoUsuarioLogado();
		return mentorRepository.findFirstByUsuario_Apelido(apelido).orElseThrow(() -> new NotFoundException("Complete o cadastro para acessar sua conta."));
	}

	@Override
	public MentorDTO buscarPerfilMentor(String apelido) {
		MentorDTO mentorDTO = mentorRepository.buscarMentorPorApelido(apelido);

		List<Tag> tags = tagMentorRepository.buscarTagsMentorPublic(apelido, 999);
		List<HorarioMentorDTO> horarios = horarioMentorRepository.buscarHorariosMentorPublic(apelido);
		List<PlanoMentorDTO> planos = planoMentorRepository.buscarPlanosMentorPublic(apelido);
		List<AvaliacaoMentorDTO> avaliacoes = avaliacaoMentorRepository.buscarAvaliacoesMentorPublic(apelido);

		mentorDTO.setTags(tags);
		mentorDTO.setHorarios(horarios);
		mentorDTO.setPlanos(planos);
		mentorDTO.setAvaliacoes(avaliacoes);

		return mentorDTO;
	}

	@Override
	public Mentor salvar(CadastroMentorDTO mentorDTO) {
		Usuario usuario = usuarioService.buscarUsuarioLogado();

		Mentor mentor = Mentor.builder()
				.foto(mentorDTO.getFoto())
				.descricao(mentorDTO.getDescricao())
				.cargo(mentorDTO.getCargo())
				.empresa(mentorDTO.getEmpresa())
				.dataInicio(mentorDTO.getDataInicio())
				.ativo(false)
				.usuario(usuario)
				.build();

		mentor = mentorRepository.saveAndFlush(mentor);
		this.salvarTags(mentor, mentorDTO.getTags());
		return mentor;
	}

	@Override
	public Mentor atualizar(Long idMentor, CadastroMentorDTO DTO) {
		Mentor mentor = buscarPorId(idMentor);

		manipularMentor(mentor, DTO);
		mentor = mentorRepository.save(mentor);

		this.atualizarTags(mentor, DTO.getTags());
		return mentor;
	}
	
	@Override
	public Mentor atualizarMentorLogado(CadastroMentorDTO DTO) {
		Mentor mentor = buscarMentorLogado();

		manipularMentor(mentor, DTO);
		mentor = mentorRepository.save(mentor);

		this.atualizarTags(mentor, DTO.getTags());
		return mentor;
	}

	@Override
	public void ativarDesativar(Long id) {
		Mentor mentor = buscarPorId(id);
		mentor.setAtivo(!mentor.getAtivo());
		mentorRepository.save(mentor);
	}

	@Override
	public void ativarDesativarMentorLogado() {
		Mentor mentor = buscarMentorLogado();
		mentor.setAtivo(!mentor.getAtivo());
		mentorRepository.save(mentor);
	}

	private void manipularMentor(Mentor mentor, CadastroMentorDTO DTO) {
		mentor.setFoto(DTO.getFoto());
		mentor.setDescricao(DTO.getDescricao());
		mentor.setCargo(DTO.getCargo());
		mentor.setEmpresa(DTO.getEmpresa());
		mentor.setDataInicio(DTO.getDataInicio());
	}

	private void salvarTags(Mentor mentor, List<Long> idsTags) {
		List<TagMentor> tagsMentor = new ArrayList<TagMentor>();

		for (Long id: idsTags) {
			Tag tag = tagService.buscarPorId(id);

			TagMentor tagMentor = TagMentor.builder()
					.mentor(mentor)
					.tag(tag)
					.ordem((long) idsTags.indexOf(id))
					.build();

			tagsMentor.add(tagMentor);
		}

		tagMentorRepository.saveAllAndFlush(tagsMentor);
	}

	private void atualizarTags(Mentor mentor, List<Long> idsTags) {
		List<Long> ordens = new ArrayList<Long>(idsTags);
		List<TagMentor> tagsMentor = tagMentorRepository.findByMentor_Id(mentor.getId());

//		Remover tags não listadas e atualizar ordens das já adicionadas
		for (TagMentor tagMentor: tagsMentor) {
			Long idTag = tagMentor.getTag().getId();
			Boolean tagJaAdicionada = idsTags.contains(idTag);

			if (tagJaAdicionada) { 
				tagMentor.setOrdem((long) ordens.indexOf(idTag));
				tagMentorRepository.save(tagMentor);
				idsTags.remove(idTag);
			} else {
				tagMentorRepository.delete(tagMentor);
			}
		}

//		Adicionar novas tags
		List<TagMentor> tags = new ArrayList<TagMentor>();

		for (Long id: idsTags) {
			Tag tag = tagService.buscarPorId(id);

			TagMentor tagMentor = TagMentor.builder()
					.mentor(mentor)
					.tag(tag)
					.ordem((long) ordens.indexOf(id))
					.build();

			tags.add(tagMentor);
		}

		tagMentorRepository.saveAllAndFlush(tags);
	}

}
