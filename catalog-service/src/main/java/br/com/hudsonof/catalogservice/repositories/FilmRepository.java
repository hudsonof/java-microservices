package br.com.hudsonof.catalogservice.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hudsonof.catalogservice.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {
    
}
