package com.example.backend.repository;
import com.example.backend.models.Vinil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VinilRepository extends JpaRepository<Vinil, Long>{

    List<Vinil> findAllByArtista(String artista);

    List<Vinil> findAllByAlbum(String album);

    List<Vinil> findAllByGenero(String genero);

    List<Vinil> findAllByAno(Integer ano);

    List<Vinil> findAllByGravadora(String gravadora);

}