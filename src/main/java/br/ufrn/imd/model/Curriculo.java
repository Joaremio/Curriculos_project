package br.ufrn.imd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "curriculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O cargo desejado é obrigatório.")
    private String cargo;

    @NotBlank(message = "A escolaridade é obrigatória.")
    private String escolaridade;

    private String observacoes;

    private String arquivo;

    private String ip;

    @Column(name = "dataEnvio")
    private LocalDateTime dataEnvio;
}


