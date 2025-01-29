package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

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
	public List<PlanoMentorDTO> buscarPlanosPorMentor(Long idMentor) {
		return planoMentorRepository.buscarPlanosPorMentor(idMentor);
	}
	
	@Override
	public PlanoMentor buscarPorId(Long idMentor, Long idPlano) {
		return planoMentorRepository.findByIdAndMentor_Id(idPlano, idMentor).orElseThrow(() -> new NotFoundException("Plano não encontrado"));
	}

	@Override
	public PlanoMentor salvar(Long idMentor, CadastroPlanoMentorDTO DTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);
		
		PlanoMentor planoMentor = PlanoMentor.builder()
				.valor(DTO.getValor())
				.mentor(mentor)
				.build();
		
		return planoMentorRepository.saveAndFlush(planoMentor);
	}

	@Override
	public PlanoMentor atualizar(Long idMentor, Long idPlano, CadastroPlanoMentorDTO DTO) {
		PlanoMentor planoMentor = buscarPorId(idMentor, idPlano);

		planoMentor.setValor(DTO.getValor());
		
		return planoMentorRepository.saveAndFlush(planoMentor);
	}

	@Override
	public void excluir(Long idMentor, Long idPlano) {
		PlanoMentor planoMentor = buscarPorId(idMentor, idPlano);
		
		try {
			planoMentorRepository.delete(planoMentor);
		} catch (Exception e) {
			throw new InternalErrorException("Não foi possivel excluir o plano");
		}
		
	}
	
	@Override
	public List<PlanoMentorDTO> buscarPlanosPorApelidoMentor(String apelido) {
		return planoMentorRepository.buscarPlanosPorApelidoMentor(apelido);
	}

}
