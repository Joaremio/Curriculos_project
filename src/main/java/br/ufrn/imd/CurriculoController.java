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
    public String enviarCurriculo(@Valid Curriculo curriculo, BindingResult result,
                                  @RequestParam("file") MultipartFile file,
                                  HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            model.addAttribute("mensagemErro", "Erro no envio do formulário");
            return "formulario";
        }

        // Validação do arquivo
        if (!file.getOriginalFilename().endsWith(".pdf") &&
                !file.getOriginalFilename().endsWith(".doc") &&
                !file.getOriginalFilename().endsWith(".docx")) {
            model.addAttribute("mensagemErro", "Formato de arquivo inválido");
            return "formulario";
        }

        if (file.getSize() > 1024 * 1024) {
            model.addAttribute("mensagemErro", "Arquivo excede o tamanho máximo de 1MB");
            return "formulario";
        }

        try {
            // Salva o arquivo no diretório do servidor
            String caminhoDiretorio = "C:\\Users\\joare\\Documents\\curriculo_project\\src\\main\\resources\\uploads\\";
            String caminhoCompleto = caminhoDiretorio + file.getOriginalFilename();

            // Cria o diretório, caso não exista
            File diretorio = new File(caminhoDiretorio);
            if (!diretorio.exists()) {
                boolean criado = diretorio.mkdirs(); // Retorna false se a criação falhar
                if (!criado) {
                    throw new IOException("Não foi possível criar o diretório: " + caminhoDiretorio);
                }
            }

            // Cria o arquivo no diretório
            File destino = new File(caminhoCompleto);
            file.transferTo(destino); // Salva o arquivo no sistema de arquivos

            // Salva o caminho do arquivo no banco de dados
            curriculo.setArquivo(file.getOriginalFilename()); // Salva o caminho no objeto Curriculo
            curriculo.setIp(request.getRemoteAddr());
            curriculo.setDataEnvio(LocalDateTime.now());

            // Salva o currículo no banco
            curriculoService.salvarCurriculo(curriculo);

            // Mensagem de sucesso
            model.addAttribute("mensagemSucesso", "Currículo enviado com sucesso!");
            return "formulario";
        } catch (Exception e) {
            e.printStackTrace(); // Mostra a exceção completa no console
            model.addAttribute("mensagemErro", "Erro ao salvar o currículo: " + e.getMessage());
            return "formulario";
        }
    }
    @GetMapping
    public String exibirFormulario() {
        return "formulario";
    }
}
