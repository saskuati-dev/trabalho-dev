package com.devweb.vendas.control;

import org.springframework.web.bind.annotation.RestController;

import com.devweb.vendas.model.Pedido;
import com.devweb.vendas.repository.PedidoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping
public class PedidoController {

    @Autowired
    PedidoRepositorio repo;

    @GetMapping("/ped")
    public ResponseEntity<List<Pedido>> getPedidos(){
        ResponseEntity<List<Pedido>> result;
        try {
            List<Pedido> listaped = new ArrayList<>(repo.findAll());
            if (listaped.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(listaped, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/pedido", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido novoPed) {
        try {
            Pedido _p = repo.save(new Pedido(novoPed.getId_funcionario(), novoPed.getId_cliente(), novoPed.getData_pedido(), novoPed.getData_remessa()));
            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pedidoid/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") long id) {
        try {
            Optional<Pedido> data = repo.findById(id);
            if (!data.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pedido/{id_pedido}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("id_pedido") long id_pedido, @RequestBody Pedido pedido) {
        Optional<Pedido> data = repo.findById(id_pedido);
        if (data.isPresent()) {
            Pedido p = data.get();
            p.setId_funcionario(pedido.getId_funcionario());
            p.setId_cliente(pedido.getId_cliente());
            p.setData_pedido(pedido.getData_pedido());
            p.setData_remessa(pedido.getData_remessa());

            return new ResponseEntity<>(repo.save(p), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pedido/{id_pedido}")
    public ResponseEntity<HttpStatus> deletePedido(@PathVariable("id_pedido") long id_pedido) {
        try {
            repo.deleteById(id_pedido);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/pedido")
    public ResponseEntity<HttpStatus> deleteAllPedidos() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
