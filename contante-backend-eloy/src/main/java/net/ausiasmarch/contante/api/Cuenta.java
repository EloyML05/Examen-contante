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

import net.ausiasmarch.contante.entity.CuentaEntity;
import net.ausiasmarch.contante.service.CuentaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/cuenta")
public class Cuenta {

    @Autowired
    CuentaService oCuentaService;

    @GetMapping("")
    public ResponseEntity<Page<CuentaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<CuentaEntity>>(oCuentaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaEntity> getCuenta(@PathVariable Long id) {
        CuentaEntity test = oCuentaService.get(id);
        System.out.println(test.getId());
        System.out.println(test.getCodigo());
        System.out.println(test.getDescripcion());
        System.out.println(test.getId_tipocuenta());
        return new ResponseEntity<CuentaEntity>(oCuentaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oCuentaService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oCuentaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<CuentaEntity> create(@RequestBody CuentaEntity oCuentaEntity) {
        return new ResponseEntity<CuentaEntity>(oCuentaService.create(oCuentaEntity), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CuentaEntity> update(@RequestBody CuentaEntity oCuentaEntity) {
        return new ResponseEntity<CuentaEntity>(oCuentaService.update(oCuentaEntity), HttpStatus.OK);
    }

    @PutMapping("/random/{cantidad}")
    public ResponseEntity<Long> create(@PathVariable Long cantidad) {
        return new ResponseEntity<Long>(oCuentaService.randomCreate(cantidad), HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oCuentaService.deleteAll(), HttpStatus.OK);
    }

}
