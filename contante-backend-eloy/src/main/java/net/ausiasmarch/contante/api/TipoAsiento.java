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

import net.ausiasmarch.contante.entity.TipoAsientoEntity;
import net.ausiasmarch.contante.service.TipoAsientoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/tipoasiento")
public class TipoAsiento {

    @Autowired
    TipoAsientoService oTipoAsientoService;

    @GetMapping("")
    public ResponseEntity<Page<TipoAsientoEntity>> getPage(
            Pageable oPageable,
            @RequestParam Optional<String> filter) {
        return new ResponseEntity<Page<TipoAsientoEntity>>(oTipoAsientoService.getPage(oPageable, filter),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAsientoEntity> getTipoAsiento(@PathVariable Long id) {
        return new ResponseEntity<TipoAsientoEntity>(oTipoAsientoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oTipoAsientoService.count(), HttpStatus.OK);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oTipoAsientoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<TipoAsientoEntity> create(@RequestBody TipoAsientoEntity oTipoAsientoEntity) {
        return new ResponseEntity<TipoAsientoEntity>(oTipoAsientoService.create(oTipoAsientoEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<TipoAsientoEntity> update(@RequestBody TipoAsientoEntity oTipoAsientoEntity) {
        return new ResponseEntity<TipoAsientoEntity>(oTipoAsientoService.update(oTipoAsientoEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oTipoAsientoService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oTipoAsientoService.deleteAll(), HttpStatus.OK);
    }
}
