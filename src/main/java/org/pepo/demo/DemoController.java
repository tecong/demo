package org.pepo.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    BeerRepository beerRepository;

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllBeers(@RequestParam(required = false) String name) {
        try {
            List<Beer> beers = new ArrayList<Beer>();
            if(name == null) {
                beerRepository.findAll().forEach(beers::add);
            } else {
                beerRepository.findByNameContaining(name).forEach(beers::add);
            }
            if(beers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(beers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/beers/{id}")
    public ResponseEntity<Beer> getBeerById(@PathVariable("id") long id) {
        Optional<Beer> beerData = beerRepository.findById(id);

        if(beerData.isPresent()) {
            return new ResponseEntity<>(beerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/beers")
    public ResponseEntity<Beer> createBeer(@RequestBody Beer beer) {
        try {
            Beer _beer = beerRepository.save(new Beer(beer.getName(), beer.getBrewery(), beer.getType()));
            return new ResponseEntity<>(_beer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/beers/{id}")
    public ResponseEntity<Beer> updateBeer(@PathVariable("id") long id, @RequestBody Beer beer) {
        Optional<Beer> beerData = beerRepository.findById(id);

        if(beerData.isPresent()) {
            Beer _beer = beerData.get();
            _beer.setName(beer.getName());
            _beer.setBrewery(beer.getBrewery());
            _beer.setType(beer.getType());
            return new ResponseEntity<>(beerRepository.save(_beer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<HttpStatus> deleteBeer(@PathVariable("id") long id) {
        try {
            beerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
