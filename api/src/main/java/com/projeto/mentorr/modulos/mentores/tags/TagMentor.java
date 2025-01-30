package com.projeto.mentorr.modulos.mentores.tags;

import com.projeto.mentorr.modulos.mentores.Mentor;
import com.projeto.mentorr.modulos.tags.Tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "tags_mentor")
public class TagMentor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENTOR")
	private Mentor mentor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TAG")
	private Tag tag;

	@Column(name = "ORDEM", nullable = false)
	private Long ordem;

}
