package br.com.diego.Lanchonete.domain.templates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@MappedSuperclass
public abstract class Pessoa {
    @Column(length=50, nullable=false, name="NOME") @JsonProperty("name") @NotBlank
    protected String nome;

    @EqualsAndHashCode.Include
    @Column(length=11, nullable=false, name="CPF") @NotBlank
    protected String cpf;

    @Column(length=40, nullable=false, name="EMAIL") @Email @NotBlank
    protected String email;

    @Column(length=13, name="TELEFONE") @JsonProperty("phone") @NotBlank
    protected String telefone;

    @JsonIgnore
    // @Column(name="DATA_ANIVERSARIO") @JsonProperty("birhday") @Pattern(regexp="^([0-2][0-9]|(3)[0-1])(\/)(((0)[0-9])|((1)[0-2]))(\/)\d{4}$")
    protected LocalDate birthDay;
}