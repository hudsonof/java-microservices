package br.com.hudsonof.filmservice.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.hudsonof.constants.RabbitMQConstants;
import br.com.hudsonof.dtos.FilmDTO;
import br.com.hudsonof.filmservice.entities.Film;
import br.com.hudsonof.filmservice.repositories.FilmRepository;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public FilmDTO saveFilm(FilmDTO filmDTO) {
        Film savedFilm = filmRepository.save(Film.builder().name(filmDTO.getName()).build());
        FilmDTO savedFilmDTO = FilmDTO.builder()
            .id(savedFilm.getId())
            .name(savedFilm.getName())
            .build();
            
        sendToQueue(RabbitMQConstants.CATALOG, savedFilmDTO);

        return savedFilmDTO;
    }

    public void sendToQueue(String queueName, Object message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
