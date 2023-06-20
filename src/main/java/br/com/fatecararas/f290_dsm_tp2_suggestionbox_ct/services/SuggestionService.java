package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.services;

import java.util.List;
import java.util.Optional;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.config.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.model.entities.Suggestion;
import br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.repositories.SuggestionRepository;

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository repository;

    //TODO: Criar um script que crie 4 tipos de categoria [Sugestão, Crítica, Elogio, Comentário] e crie uma menos 1 registro com base numa categoria.

    public Suggestion create(Suggestion suggestion) {
        //TODO: Validar exceção para ExceptionHandler
        if(suggestion == null){
            throw new IllegalArgumentException("Wrong Schema");
        }
        return repository.save(suggestion);
    }

    //TODO: Criar método que retorne uma sugestão com base num id.
    public Suggestion findById(Integer pId) {
        Optional<Suggestion> optional = repository.findById(pId);
        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("Suggestion not found. ID: " + pId);
        }
        return optional.get();
    }

    //TODO: Criar método que retorne todas as sugestões do banco de dados.
    public List<Suggestion> findAll() {
        return repository.findAll();
    }

    public Suggestion update(Integer id, Suggestion pSuggestion) {
        Suggestion suggestion = findById(id);
        suggestion.setDescription(pSuggestion.getDescription());
        return repository.save(suggestion);
    }

    //TODO: Criar método que remova uma sugestão do banco de dados.
    public void deleteById(Integer id) {
        Suggestion suggestion = findById(id);
        repository.deleteById(suggestion.getId());
    }

    //TODO: Alterar o application-test.properties para não criar as entidades Category e Suggestion

}
