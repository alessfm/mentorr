package com.projeto.mentorr.modulos.mentores.planos;

import java.util.List;
import java.util.UUID;

public interface PlanoMentorService {

	List<PlanoMentorDTO> buscarPlanosMentor(Long idMentor);

	List<PlanoMentorDTO> buscarPlanosMentorLogado();

	PlanoMentor buscarPorId(Long idMentor, Long idPlano);

	PlanoMentor buscarPorCodigo(Long idMentor, UUID codigoPlano);

	PlanoMentor buscarPorIdPorMentorLogado(Long idPlano);

	PlanoMentor salvar(Long idMentor, CadastroPlanoMentorDTO planoDTO);

	PlanoMentor salvarPorMentorLogado(CadastroPlanoMentorDTO planoDTO);

	void salvarLote(Long idMentor, List<CadastroPlanoMentorDTO> planosDTO);

	void salvarLotePorMentorLogado(List<CadastroPlanoMentorDTO> planosDTO);

	PlanoMentor atualizar(Long idMentor, Long idPlano, CadastroPlanoMentorDTO planoDTO);

	PlanoMentor atualizarPorMentorLogado(Long idPlano, CadastroPlanoMentorDTO planoDTO);

	void excluir(Long idMentor, Long idPlano);

	void excluirPorMentorLogado(Long idPlano);

}
