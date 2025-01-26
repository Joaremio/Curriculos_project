package br.ufrn.imd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Controller
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;
    private static final Logger logger = LoggerFactory.getLogger(CurriculoController.class);


    @PostMapping
    public String enviar(@Valid Curriculo curriculo, @RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
        logger.info("Recebendo currículo de {}", curriculo.getNome());
        return curriculoService.enviarCurriculo(curriculo,file,request, model);
    }

    @GetMapping
    public String exibirFormulario() {
        logger.info("Exibindo formulário para envio de currículo");
        return "formulario";
    }
}
