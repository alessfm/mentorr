package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;

public interface PlanoMentorService {

	List<PlanoMentorDTO> buscarPlanosPorMentor(Long idMentor);

	PlanoMentor buscarPorId(Long idMentor, Long idPlano);

	PlanoMentor salvar(Long idMentor, CadastroPlanoMentorDTO DTO);

	PlanoMentor atualizar(Long idMentor, Long idPlano, CadastroPlanoMentorDTO DTO);

	void excluir(Long idMentor, Long idPlano);
	
	List<PlanoMentorDTO> buscarPlanosPorApelidoMentor(String apelido);

}
