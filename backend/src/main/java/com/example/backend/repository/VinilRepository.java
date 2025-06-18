package com.example.backend.repository;
import com.example.backend.models.Vinil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VinilRepository extends JpaRepository<Vinil, Long> {

    List<Vinil> findAllByArtista(String artista);
    List<Vinil> findAllByAlbum(String album);
    List<Vinil> findAllByGenero(String genero);
    List<Vinil> findAllByAno(Integer ano);
    List<Vinil> findAllByGravadora(String gravadora);

    @Query("SELECT v FROM Vinil v WHERE " +
           "LOWER(v.artista) LIKE %:termo% OR " +
           "LOWER(v.album) LIKE %:termo% OR " +
           "LOWER(v.genero) LIKE %:termo% OR " +
           "LOWER(v.gravadora) LIKE %:termo%")
    List<Vinil> buscarPorTermo(@Param("termo") String termo);
}