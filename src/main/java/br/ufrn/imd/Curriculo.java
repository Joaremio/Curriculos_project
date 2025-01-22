package br.ufrn.imd;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity
@Table(name = "curriculos")  // Especificando explicitamente o nome da tabela
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
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    private String telefone;

    @NotEmpty(message = "O cargo desejado é obrigatório.")
    private String cargo;

    @NotBlank(message = "A escolaridade é obrigatória.")
    private String escolaridade;

    private String observacoes; // Opcional

    private String arquivo; // O campo para armazenar o nome do arquivo

    private String ip;

    @Column(name = "dataEnvio")
    private LocalDateTime dataEnvio;
}


