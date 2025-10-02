package com.projeto.mentorr.modulos.mentores.planos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentores.MentorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = { @Lazy })
@Service
public class PlanoMentorServiceImpl implements PlanoMentorService {

	private final MentorService mentorService;
	private final PlanoMentorRepository planoMentorRepository;

	@Override
	public List<PlanoMentorDTO> buscarPlanosMentor(Long idMentor) {
		return planoMentorRepository.buscarPlanosMentor(idMentor);
	}

	@Override
	public List<PlanoMentorDTO> buscarPlanosMentorLogado() {
		Mentor mentor = mentorService.buscarMentorLogado();
		return buscarPlanosMentor(mentor.getId());
	}

	@Override
	public PlanoMentor buscarPorId(Long idMentor, Long idPlano) {
		return planoMentorRepository.findByIdAndMentor_Id(idPlano, idMentor).orElseThrow(() -> new NotFoundException("Plano não encontrado"));
	}

	@Override
	public PlanoMentor buscarPorCodigo(Long idMentor, UUID codigoPlano) {
		return planoMentorRepository.findByCodigoAndMentor_Id(codigoPlano, idMentor).orElseThrow(() -> new NotFoundException("Plano não encontrado"));
	}

	@Override
	public PlanoMentor buscarPorIdPorMentorLogado(Long idPlano) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return buscarPorId(mentor.getId(), idPlano);
	}

	@Override
	public PlanoMentor salvar(Long idMentor, CadastroPlanoMentorDTO planoDTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);

		PlanoMentor plano = PlanoMentor.builder()
				.codigo(UUID.randomUUID())
				.tipo(planoDTO.getTipo())
				.valor(planoDTO.getValor())
				.descricao(planoDTO.getDescricao())
				.totalChamadas(planoDTO.getTotalChamadas())
				.duracaoChamada(planoDTO.getDuracaoChamada())
				.tempoResposta(planoDTO.getTempoResposta())
				.mentor(mentor)
				.build();

		return planoMentorRepository.saveAndFlush(plano);
	}

	@Override
	public PlanoMentor salvarPorMentorLogado(CadastroPlanoMentorDTO planoDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return salvar(mentor.getId(), planoDTO);
	}

	@Override
	public void salvarLote(Long idMentor, List<CadastroPlanoMentorDTO> planosDTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);
		List<PlanoMentor> planos = new ArrayList<PlanoMentor>();

		for(CadastroPlanoMentorDTO p: planosDTO) {
			PlanoMentor plano = PlanoMentor.builder()
					.codigo(UUID.randomUUID())
					.tipo(p.getTipo())
					.valor(p.getValor())
					.descricao(p.getDescricao())
					.totalChamadas(p.getTotalChamadas())
					.duracaoChamada(p.getDuracaoChamada())
					.tempoResposta(p.getTempoResposta())
					.mentor(mentor)
					.build();

			planos.add(plano);
		}

		planoMentorRepository.saveAllAndFlush(planos);			
	}

	@Override
	public void salvarLotePorMentorLogado(List<CadastroPlanoMentorDTO> planosDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		salvarLote(mentor.getId(), planosDTO);			
	}

	@Override
	public PlanoMentor atualizar(Long idMentor, Long idPlano, CadastroPlanoMentorDTO planoDTO) {
		PlanoMentor plano = buscarPorId(idMentor, idPlano);
//		plano.setTipo();
		plano.setValor(planoDTO.getValor());
		plano.setDescricao(planoDTO.getDescricao());
		plano.setTotalChamadas(planoDTO.getTotalChamadas());
		plano.setDuracaoChamada(planoDTO.getDuracaoChamada());
		plano.setTempoResposta(planoDTO.getTempoResposta());
		return planoMentorRepository.save(plano);
	}

	@Override
	public PlanoMentor atualizarPorMentorLogado(Long idPlano, CadastroPlanoMentorDTO planoDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return atualizar(mentor.getId(), idPlano, planoDTO);
	}

	@Override
	public void excluir(Long idMentor, Long idPlano) {
		PlanoMentor plano = buscarPorId(idMentor, idPlano);

		try {
			planoMentorRepository.delete(plano);
		} catch (Exception e) {
			throw new InternalErrorException("Não é possível excluir o plano, pois existem dados vinculados");
		}
	}

	@Override
	public void excluirPorMentorLogado(Long idPlano) {
		Mentor mentor = mentorService.buscarMentorLogado();
		excluir(mentor.getId(), idPlano);
	}

}
