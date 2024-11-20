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

import net.ausiasmarch.contante.entity.BalanceEntity;
import net.ausiasmarch.contante.service.BalanceService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/balance")
public class Balance {

     @Autowired
    BalanceService oBalanceService;

    @GetMapping("")
    public ResponseEntity<Page<BalanceEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<BalanceEntity>>(oBalanceService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalanceEntity> getBalance(@PathVariable Long id) {
        return new ResponseEntity<BalanceEntity>(oBalanceService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oBalanceService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oBalanceService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<BalanceEntity> create(@RequestBody BalanceEntity oBalanceEntity) {
        return new ResponseEntity<BalanceEntity>(oBalanceService.create(oBalanceEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BalanceEntity> update(@RequestBody BalanceEntity oBalanceEntity) {
        return new ResponseEntity<BalanceEntity>(oBalanceService.update(oBalanceEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oBalanceService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oBalanceService.deleteAll(), HttpStatus.OK);
    }
    
}
