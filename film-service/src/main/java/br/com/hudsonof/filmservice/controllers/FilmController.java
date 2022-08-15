package br.com.hudsonof.filmservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hudsonof.dtos.FilmDTO;
import br.com.hudsonof.filmservice.services.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    
    @PostMapping
    private ResponseEntity<FilmDTO> saveFilm(@RequestBody FilmDTO filmDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.saveFilm(filmDTO));
    }
}
