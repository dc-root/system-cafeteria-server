package br.com.diego.Lanchonete.domain.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class Pessoa {
    @EqualsAndHashCode.Include
    @Column(length = 11)
    protected String cpf;
    @Column(length = 50, nullable = false)
    protected String nome;
    @Column(length = 13)
    @JsonProperty("fone")
    protected String telefone;
    @Column(length = 40)
    protected String email;
    @JsonIgnore
    protected LocalDate dataNascimento;
}