package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;

public interface HorarioMentorService {

	List<HorarioMentorDTO> buscarHorariosMentor(Long idMentor);

	List<HorarioMentorDTO> buscarHorariosMentorLogado();

	HorarioMentor buscarPorId(Long idMentor, Long idHorario);

	HorarioMentor buscarPorIdPorMentorLogado(Long idHorario);

	HorarioMentor salvar(Long idMentor, CadastroHorarioMentorDTO horarioDTO);

	HorarioMentor salvarPorMentorLogado(CadastroHorarioMentorDTO horarioDTO);

	void salvarLote(Long idMentor, List<CadastroHorarioMentorDTO> horariosDTO);

	void salvarLotePorMentorLogado(List<CadastroHorarioMentorDTO> horariosDTO);

	HorarioMentor atualizar(Long idMentor, Long idHorario, CadastroHorarioMentorDTO horarioDTO);

	HorarioMentor atualizarPorMentorLogado(Long idHorario, CadastroHorarioMentorDTO horarioDTO);

	void excluir(Long idMentor, Long idHorario);

	void excluirPorMentorLogado(Long idHorario);

}
