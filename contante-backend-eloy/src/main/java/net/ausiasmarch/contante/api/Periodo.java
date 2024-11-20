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

import net.ausiasmarch.contante.entity.PeriodoEntity;
import net.ausiasmarch.contante.service.PeriodoService;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/periodo")
public class Periodo {
    
    @Autowired
    PeriodoService oPeriodoService;

    @GetMapping("")
    public ResponseEntity<Page<PeriodoEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<PeriodoEntity>>(oPeriodoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoEntity> getPeriodo(@PathVariable Long id) {
        return new ResponseEntity<PeriodoEntity>(oPeriodoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oPeriodoService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oPeriodoService.delete(id), HttpStatus.OK);
    }

       @PutMapping("")
    public ResponseEntity<PeriodoEntity> create(@RequestBody PeriodoEntity oPeriodoEntity) {
        return new ResponseEntity<PeriodoEntity>(oPeriodoService.create(oPeriodoEntity), HttpStatus.OK);
    }

        @PostMapping("")
    public ResponseEntity<PeriodoEntity> update(@RequestBody PeriodoEntity oPeriodoEntity) {
        return new ResponseEntity<PeriodoEntity>(oPeriodoService.update(oPeriodoEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oPeriodoService.randomCreate(cantidad), HttpStatus.OK);
    }

    
    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oPeriodoService.deleteAll(), HttpStatus.OK);
    }

}
