package net.ausiasmarch.contante.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.ausiasmarch.contante.entity.AsientoEntity;
import net.ausiasmarch.contante.service.AsientoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/asiento")
public class Asiento {

    @Autowired
    AsientoService oAsientoService;

    @GetMapping("")
    public ResponseEntity<Page<AsientoEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {


        return new ResponseEntity<Page<AsientoEntity>>(oAsientoService.getPage(oPageable, filter), HttpStatus.OK);
    
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsientoEntity> getAsiento(@PathVariable Long id) {
        return new ResponseEntity<AsientoEntity>(oAsientoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oAsientoService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oAsientoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<AsientoEntity> create(@RequestBody AsientoEntity oAsientoEntity) {
        return new ResponseEntity<AsientoEntity>(oAsientoService.create(oAsientoEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<AsientoEntity> update(@RequestBody AsientoEntity oAsientoEntity) {
        return new ResponseEntity<AsientoEntity>(oAsientoService.update(oAsientoEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oAsientoService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oAsientoService.deleteAll(), HttpStatus.OK);
    }


}