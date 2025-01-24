package br.ufrn.imd;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


@Service
public class CurriculoService {

    @Autowired
    private EmailService emailService;

    @Value("${curriculo.diretorio.uploads}")
    private String diretorioUploads;

    @Autowired
    private CurriculoRepository curriculoRepository;

    private static final Logger logger = LoggerFactory.getLogger(CurriculoService.class);

    public String enviarCurriculo(Curriculo curriculo, MultipartFile file, HttpServletRequest request, Model model) {

        if (!ArquivoUtils.validarFormato(file)) {
            model.addAttribute("mensagemErro", "Formato de arquivo inválido");
            return "formulario";
        }

        if (!ArquivoUtils.validarTamanho(file)) {
            model.addAttribute("mensagemErro", "Arquivo excede o tamanho máximo de 1MB");
            return "formulario";
        }

        File arquivoSalvo = null;
        try {
            arquivoSalvo = salvarCurriculoDiretorio(file);
        } catch (IOException e) {
            logger.error("Erro ao salvar o arquivo: {}", e.getMessage(), e);
            model.addAttribute("mensagemErro", "Erro ao salvar o arquivo.");
            return "formulario";
        }

        curriculo.setArquivo(file.getOriginalFilename());
        curriculo.setIp(request.getRemoteAddr());
        curriculo.setDataEnvio(LocalDateTime.now());
        salvarCurriculoBanco(curriculo);


        emailService.enviarEmail(curriculo.getEmail(), "Confirmação de Inscrição",arquivoSalvo,curriculo);

        model.addAttribute("mensagemSucesso", "Currículo enviado com sucesso!");
        return "formulario";
    }

    public void salvarCurriculoBanco(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }

    public File salvarCurriculoDiretorio(MultipartFile file) throws IOException {
        String caminhoDiretorio = new File(diretorioUploads).getAbsolutePath();
        String caminhoCompleto = caminhoDiretorio + File.separator + file.getOriginalFilename();

        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists() && !diretorio.mkdirs()) {
            throw new IOException("Não foi possível criar o diretório: " + caminhoDiretorio);
        }

        File destino = new File(caminhoCompleto);
        file.transferTo(destino);
        return destino;
    }
}

