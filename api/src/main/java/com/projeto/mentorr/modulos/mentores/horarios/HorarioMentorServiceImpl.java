package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.ArrayList;
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
public class HorarioMentorServiceImpl implements HorarioMentorService {

	private final MentorService mentorService;
	private final HorarioMentorRepository horarioMentorRepository;

	@Override
	public List<HorarioMentorDTO> buscarHorariosMentor(Long idMentor) {
		return horarioMentorRepository.buscarHorariosMentor(idMentor);
	}

	@Override
	public List<HorarioMentorDTO> buscarHorariosMentorLogado() {
		Mentor mentor = mentorService.buscarMentorLogado();
		return buscarHorariosMentor(mentor.getId());
	}

	@Override
	public HorarioMentor buscarPorId(Long idMentor, Long idHorario) {
		return horarioMentorRepository.findByIdAndMentor_Id(idHorario, idMentor).orElseThrow(() -> new NotFoundException("Horário não encontrado"));
	}

	@Override
	public HorarioMentor buscarPorIdPorMentorLogado(Long idHorario) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return buscarPorId(mentor.getId(), idHorario);
	}

	@Override
	public HorarioMentor salvar(Long idMentor, CadastroHorarioMentorDTO horarioDTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);

		HorarioMentor horarioMentor = HorarioMentor.builder()
				.dia(horarioDTO.getDia())
				.horaInicio(horarioDTO.getHoraInicio())
				.horaFim(horarioDTO.getHoraFim())
				.mentor(mentor)
				.build();

		return horarioMentorRepository.saveAndFlush(horarioMentor);
	}

	@Override
	public HorarioMentor salvarPorMentorLogado(CadastroHorarioMentorDTO horarioDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return salvar(mentor.getId(), horarioDTO);
	}

	@Override
	public void salvarLote(Long idMentor, List<CadastroHorarioMentorDTO> horariosDTO) {
		Mentor mentor = mentorService.buscarPorId(idMentor);
		List<HorarioMentor> horarios = new ArrayList<HorarioMentor>();

		for(CadastroHorarioMentorDTO h: horariosDTO) {
			HorarioMentor horario = HorarioMentor.builder()
					.dia(h.getDia())
					.horaInicio(h.getHoraInicio())
					.horaFim(h.getHoraFim())
					.mentor(mentor)
					.build();		

			horarios.add(horario);			
		}

		horarioMentorRepository.saveAllAndFlush(horarios);
	}

	@Override
	public void salvarLotePorMentorLogado(List<CadastroHorarioMentorDTO> horariosDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		salvarLote(mentor.getId(), horariosDTO);			
	}

	@Override
	public HorarioMentor atualizar(Long idMentor, Long idHorario, CadastroHorarioMentorDTO horarioDTO) {
		HorarioMentor horario = buscarPorId(idMentor, idHorario);

		horario.setDia(horarioDTO.getDia());
		horario.setHoraInicio(horarioDTO.getHoraInicio());
		horario.setHoraFim(horarioDTO.getHoraFim());

		return horarioMentorRepository.saveAndFlush(horario);
	}

	@Override
	public HorarioMentor atualizarPorMentorLogado(Long idHorario, CadastroHorarioMentorDTO horarioDTO) {
		Mentor mentor = mentorService.buscarMentorLogado();
		return atualizar(mentor.getId(), idHorario, horarioDTO);
	}

	@Override
	public void excluir(Long idMentor, Long idHorario) {
		HorarioMentor horarioMentor = buscarPorId(idMentor, idHorario);

		try {
			horarioMentorRepository.delete(horarioMentor);
		} catch (Exception e) {
			throw new InternalErrorException("Não é possível excluir o horário, pois existem dados vinculados");
		}
	}

	@Override
	public void excluirPorMentorLogado(Long idHorario) {
		Mentor mentor = mentorService.buscarMentorLogado();
		excluir(mentor.getId(), idHorario);
	}

}
