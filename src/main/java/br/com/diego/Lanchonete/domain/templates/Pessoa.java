package br.com.diego.Lanchonete.domain.templates;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@MappedSuperclass
public abstract class Pessoa {
    @NotBlank(message = "Campo 'name' não pode ser null ou vazio")
    @Column(length=50, nullable=false, name="NOME") @JsonProperty("name")
    protected String nome;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Campo  'cpf' não pode ser null ou vazio")
    @Column(length=11, nullable=false, name="CPF")
    protected String cpf;

    @Email(message = "Campo 'email' não corresponde a um email valido")
    @Column(length=40, name="EMAIL")
    protected String email;

    @Column(length=13, name="TELEFONE") @JsonProperty("phone")
    protected String telefone;

    @Column(
        length=10, name="DATA_ANIVERSARIO"
        //, columnDefinition="DATE")
    )
    @JsonProperty("birthday")
    protected String birthDay;
}