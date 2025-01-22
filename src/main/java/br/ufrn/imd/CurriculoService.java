package br.ufrn.imd;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CurriculoRepository curriculoRepository;


    public String enviarCurriculo(@Valid Curriculo curriculo,
                                  @RequestParam("file") MultipartFile file,
                                  HttpServletRequest request, Model model) {

        // Verifica se o formato é valido
        if(!validarArquivo(file)){
            model.addAttribute("mensagemErro", "Formato de arquivo inválido");
            return "formulario";
        }

        //Verifica se o tamanho é valido
        if(!validarTamanho(file)){
            model.addAttribute("mensagemErro", "Arquivo excede o tamanho máximo de 1MB");
            return "formulario";
        }

        try {
            //Salva o curriculo no diretorio
            salvarCurriculoDiretorio(file);
        } catch (IOException e) {
            model.addAttribute("mensagemErro", "Erro ao salvar o arquivo: " + e.getMessage());
            return "formulario";
        }

        // Salva o caminho do arquivo no banco de dados
        curriculo.setArquivo(file.getOriginalFilename()); // Salva o caminho no objeto Curriculo
        curriculo.setIp(request.getRemoteAddr());
        curriculo.setDataEnvio(LocalDateTime.now());
        salvarCurriculoBanco(curriculo);

        model.addAttribute("mensagemSucesso", "Currículo enviado com sucesso!");
        return "formulario";
    }

    public boolean validarTamanho(MultipartFile file) {
        return file.getSize() <= 1024 * 1024; // Retorna true se o tamanho for válido
    }

    public boolean validarArquivo(MultipartFile file) {
        String nomeArquivo = file.getOriginalFilename().toLowerCase();
        return nomeArquivo.endsWith(".pdf") || nomeArquivo.endsWith(".doc") || nomeArquivo.endsWith(".docx");
    }


    public void salvarCurriculoBanco(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }

    public void salvarCurriculoDiretorio(MultipartFile file) throws IOException {
        String caminhoDiretorio = "C:\\Users\\joare\\Documents\\curriculo_project\\src\\main\\resources\\uploads\\";
        String caminhoCompleto = caminhoDiretorio + file.getOriginalFilename();

        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists() && !diretorio.mkdirs()) {
            throw new IOException("Não foi possível criar o diretório: " + caminhoDiretorio);
        }

        File destino = new File(caminhoCompleto);
        file.transferTo(destino);
    }

}
