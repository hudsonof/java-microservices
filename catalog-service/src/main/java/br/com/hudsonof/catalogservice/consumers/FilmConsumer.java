package br.com.hudsonof.catalogservice.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.hudsonof.catalogservice.services.CatalogService;
import br.com.hudsonof.constants.RabbitMQConstants;
import br.com.hudsonof.dtos.FilmDTO;

@Component
public class FilmConsumer {

    @Autowired
    private CatalogService catalogService;

    @RabbitListener(queues = RabbitMQConstants.CATALOG)
    private void consumer(FilmDTO filmDTO) {
        catalogService.saveFilm(filmDTO);
    }

}
