package br.ufrn.imd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
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
@RequestMapping("/formulario")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;


    @PostMapping("/enviar")
    public String enviar(@Valid Curriculo curriculo, @RequestParam("file") MultipartFile file, HttpServletRequest request, Model model) {
        return curriculoService.enviarCurriculo(curriculo,file,request, model);
    }


    @GetMapping
    public String exibirFormulario() {
        return "formulario";
    }
}
