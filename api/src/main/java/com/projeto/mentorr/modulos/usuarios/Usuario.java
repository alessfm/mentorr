package com.projeto.mentorr.modulos.usuarios;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.mentorr.modulos.usuarios.role.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "APELIDO", unique = true, nullable = false)
	private String apelido;

	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@JsonIgnore
	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO", nullable = false)
	private TipoUsuario tipo;

	@Column(name = "FLAG_ATIVO", columnDefinition = "boolean default false", nullable = false)
	private Boolean ativo;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_usuario", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
	private Set<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.apelido;
	}

}
