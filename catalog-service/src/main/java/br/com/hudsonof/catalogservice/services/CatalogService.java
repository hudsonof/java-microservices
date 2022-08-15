package br.com.hudsonof.catalogservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hudsonof.catalogservice.entities.Film;
import br.com.hudsonof.catalogservice.repositories.FilmRepository;
import br.com.hudsonof.dtos.FilmDTO;

@Service
@Transactional
public class CatalogService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film savedFilm = filmRepository.save(Film.builder().name(filmDTO.getName()).build());
        return FilmDTO.builder()
            .id(savedFilm.getId())
            .name(savedFilm.getName())
            .build();
    }

    public List<FilmDTO> findAllFilms() {
        return filmRepository.findAll()
            .stream()
            .map(film -> new FilmDTO(film.getId(), film.getName()))
            .collect(Collectors.toList());
    }

    public void sendToQueue(String queueName, Object message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
