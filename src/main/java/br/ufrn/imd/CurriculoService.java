package br.ufrn.imd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    public void salvarCurriculo(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }
}
