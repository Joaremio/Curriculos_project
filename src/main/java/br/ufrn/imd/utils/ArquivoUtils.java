package br.ufrn.imd.utils;

import org.springframework.web.multipart.MultipartFile;

public class ArquivoUtils {

    public static boolean validarTamanho(MultipartFile file) {
        return file.getSize() <= 1024 * 1024; // Retorna true se o tamanho for válido
    }

    public static boolean validarFormato(MultipartFile file) {
        String nomeArquivo = file.getOriginalFilename().toLowerCase();
        return nomeArquivo.endsWith(".pdf") || nomeArquivo.endsWith(".doc") || nomeArquivo.endsWith(".docx");
    }
}
