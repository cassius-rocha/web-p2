package com.example.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.models.Vinil;
import com.example.backend.repository.VinilRepository;
import static com.example.backend.utils.ValidationUtils.validateListNotEmpty;

@RestController
@RequestMapping("/vinil")
public class VinilController {

    @Autowired
    private VinilRepository vinilRepository;

    @PostMapping
    public Vinil criar(@RequestBody Vinil vinil) {
        return vinilRepository.save(vinil);
    }

    @PutMapping("/{id}")
    public Vinil atualizar(@PathVariable Long id, @RequestBody Vinil vinil) {
        vinil.setId(id);
        return vinilRepository.save(vinil);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        vinilRepository.deleteById(id);
    }

    @GetMapping
    public List<Vinil> listarTodos() {
        return vinilRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vinil buscarPorId(@PathVariable Long id) {
        return vinilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vinil não encontrado!"));
    }

    @GetMapping("/artista/{artista}")
    public List<Vinil> buscarPorArtista(@PathVariable String artista) {
        List<Vinil> vinis = vinilRepository.findAllByArtista(artista);
        return validateListNotEmpty(vinis, "Artista não encontrado!");

    }

    @GetMapping("/album/{album}")
    public List<Vinil> buscarPorAlbum(@PathVariable String album) {
        List<Vinil> vinis = vinilRepository.findAllByAlbum(album);
        return validateListNotEmpty(vinis, "Álbum não encontrado!");
    }

    @GetMapping("/genero/{genero}")
    public List<Vinil> buscarPorGenero(@PathVariable String genero) {
        List<Vinil> vinis = vinilRepository.findAllByGenero(genero);
        return validateListNotEmpty(vinis, "Não foi encontrado nenhum vinil desse gênero!");
    }

    @GetMapping("/ano/{ano}")
    public List<Vinil> buscarPorAno(@PathVariable Integer ano) {
        List<Vinil> vinis = vinilRepository.findAllByAno(ano);
        return validateListNotEmpty(vinis, "Nenhum vinil encontrado com esse ano.");
    }

    @GetMapping("/gravadora/{gravadora}")
    public List<Vinil> buscarPorGravadora(@PathVariable String gravadora) {
        List<Vinil> vinis = vinilRepository.findAllByGravadora(gravadora);
        return validateListNotEmpty(vinis, "Nenhum vinil encontrado com essa gravadora.");
    }
}