package br.ufrn.imd;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.eclipse.angus.mail.imap.protocol.UID;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

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


