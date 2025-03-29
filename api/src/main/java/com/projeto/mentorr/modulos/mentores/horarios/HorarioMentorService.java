package com.projeto.mentorr.modulos.mentores.horarios;

import java.util.List;

public interface HorarioMentorService {

	List<HorarioMentorDTO> buscarHorariosPorMentor(Long idMentor);

	HorarioMentor buscarPorId(Long idMentor, Long idHorario);

	HorarioMentor salvar(Long idMentor, CadastroHorarioMentorDTO DTO);
	
	void salvarLote(Long idMentor, List<CadastroHorarioMentorDTO> DTO);

	HorarioMentor atualizar(Long idMentor, Long idHorario, CadastroHorarioMentorDTO DTO);

	void excluir(Long idMentor, Long idHorario);
	
	List<HorarioMentorDTO> buscarHorariosPorApelidoMentor(String apelido);

}
