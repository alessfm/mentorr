package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.projeto.mentorr.core.exception.BadRequestException;
import com.projeto.mentorr.core.exception.InternalErrorException;
import com.projeto.mentorr.core.exception.NotFoundException;
import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.mentores.MentorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = { @Lazy })
@Service
public class HorarioMentorServiceImpl implements HorarioMentorService {
	
	private final MentorService mentorService;
	private final HorarioMentorRepository horarioMentorRepository;

	@Override
	public List<HorarioMentorDTO> buscarHorariosPorMentor(Long idMentor) {
		return horarioMentorRepository.buscarHorariosPorMentor(idMentor);
	}
	
	@Override
	public HorarioMentor buscarPorId(Long idMentor, Long idHorario) {
		return horarioMentorRepository.findByIdAndMentor_Id(idHorario, idMentor).orElseThrow(() -> new NotFoundException("Horário não encontrado"));
	}

	@Override
	public HorarioMentor salvar(Long idMentor, CadastroHorarioMentorDTO DTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);
		
		Optional<HorarioMentor> horarioExistente = horarioMentorRepository.findByDiaAndMentor_Id(DTO.getDia(), idMentor);
		
		if (horarioExistente.isPresent()) {
			throw new BadRequestException("Já existe um horário cadastrado para o mesmo dia");
		}
		
		HorarioMentor horarioMentor = HorarioMentor.builder()
				.dia(DTO.getDia())
				.horaInicio(DTO.getHoraInicio())
				.horaFim(DTO.getHoraFim())
				.mentor(mentor)
				.build();
		
		return horarioMentorRepository.saveAndFlush(horarioMentor);
	}
	
	@Override
	public void salvarLote(Long idMentor, List<CadastroHorarioMentorDTO> DTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);
		
		for(CadastroHorarioMentorDTO item: DTO) {
			HorarioMentor horarioMentor = HorarioMentor.builder()
					.dia(item.getDia())
					.horaInicio(item.getHoraInicio())
					.horaFim(item.getHoraFim())
					.mentor(mentor)
					.build();			

			horarioMentorRepository.saveAndFlush(horarioMentor);			
		}
	}

	@Override
	public HorarioMentor atualizar(Long idMentor, Long idHorario, CadastroHorarioMentorDTO DTO) {
		HorarioMentor horarioMentor = buscarPorId(idMentor, idHorario);
		
		Optional<HorarioMentor> horarioExistente = horarioMentorRepository.findByDiaAndMentor_Id(DTO.getDia(), idMentor);
		
		if (horarioExistente.isPresent() && !horarioExistente.get().getId().equals(idHorario)) {
			throw new BadRequestException("Já existe um horário cadastrado para o mesmo dia");
		}

		horarioMentor.setDia(DTO.getDia());
		horarioMentor.setHoraInicio(DTO.getHoraInicio());
		horarioMentor.setHoraFim(DTO.getHoraFim());
		
		return horarioMentorRepository.saveAndFlush(horarioMentor);
	}

	@Override
	public void excluir(Long idMentor, Long idHorario) {
		HorarioMentor horarioMentor = buscarPorId(idMentor, idHorario);
		
		try {
			horarioMentorRepository.delete(horarioMentor);
		} catch (Exception e) {
			throw new InternalErrorException("Não foi possivel excluir o horário");
		}
		
	}
	
	@Override
	public List<HorarioMentorDTO> buscarHorariosPorApelidoMentor(String apelido) {
		return horarioMentorRepository.buscarHorariosPorApelidoMentor(apelido);
	}

}
