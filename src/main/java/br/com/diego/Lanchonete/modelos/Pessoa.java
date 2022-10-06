package br.com.diego.Lanchonete.modelos;

import java.time.*;

public abstract class Pessoa {
    protected String cpf;
    protected String nome;
    protected String telefone;
    protected String email;
    protected LocalDate dataNascimento;

    public Pessoa() {}
}