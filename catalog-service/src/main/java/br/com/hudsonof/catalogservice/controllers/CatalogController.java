package br.com.hudsonof.catalogservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hudsonof.catalogservice.services.CatalogService;
import br.com.hudsonof.dtos.FilmDTO;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;
    
    @GetMapping
    private ResponseEntity<List<FilmDTO>> findAllFilms() {
        return ResponseEntity.ok().body(catalogService.findAllFilms());
    }
}
