package br.ufrn.imd;

import br.ufrn.imd.model.Curriculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String remetente;
    @Autowired
    private JavaMailSender mailSender;


    public String enviarEmail(String destinatario, String assunto, File anexo, Curriculo curriculo) {
            try{
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                msg.setFrom(remetente);
                msg.setTo(destinatario);
                msg.setSubject(assunto);

                String mensagemUsuario = String.format(
                        "<p>Olá, %s!</p>" +
                                "<p>Recebemos seu currículo com sucesso. Agradecemos seu interesse na vaga de <strong>%s</strong>.</p>" +
                                "<p><strong>Detalhes do seu currículo:</strong></p>" +
                                "<ul>" +
                                "<li><strong>Nome:</strong> %s</li>" +
                                "<li><strong>Cargo:</strong> %s</li>" +
                                "<li><strong>Email:</strong> %s</li>" +
                                "<li><strong>Telefone:</strong> %s</li>" +  // Adicionando exemplo de dados do formulário
                                "</ul>" +
                                "<p>Se alguma informação estiver incorreta ou se precisar fazer alguma alteração, por favor, responda a este e-mail e faremos as correções.</p>" +
                                "<p>Obrigado por se candidatar ao cargo e boa sorte no processo seletivo!</p>",
                        curriculo.getNome(),
                        curriculo.getCargo(),
                        curriculo.getNome(),
                        curriculo.getCargo(),
                        curriculo.getEmail(),   // Exemplo de dado de e-mail
                        curriculo.getTelefone() // Exemplo de dado de telefone
                );

                msg.setText(mensagemUsuario,true);

                // Adicionar o anexo
                if (anexo != null && anexo.exists()) {
                    msg.addAttachment(anexo.getName(), anexo);  // Anexo
                }

                // Enviar o e-mail
                mailSender.send(mimeMessage);

                return "Email enviado com sucesso";
            }catch(Exception e){
                return "Erro ao enviar e-mail" + e.getMessage();
            }
    }
}
