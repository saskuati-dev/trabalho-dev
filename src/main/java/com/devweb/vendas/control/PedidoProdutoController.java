package com.devweb.vendas.control;

import org.springframework.web.bind.annotation.RestController;

import com.devweb.vendas.model.ProdutoPedido;
import com.devweb.vendas.repository.ProdutoPedidoRepositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/produto-pedido")
public class PedidoProdutoController {

    @Autowired
    ProdutoPedidoRepositorio produtoPedidoRepository;

    // GET /produto-pedido
    @GetMapping
    public ResponseEntity<List<ProdutoPedido>> getAllProdutoPedidos() {
        List<ProdutoPedido> produtoPedidos = produtoPedidoRepository.findAll();
        if (produtoPedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produtoPedidos, HttpStatus.OK);
    }

    // GET /produto-pedido/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoPedido> getProdutoPedidoById(@PathVariable("id") long id) {
        Optional<ProdutoPedido> produtoPedido = produtoPedidoRepository.findById(id);
        return produtoPedido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST /produto-pedido
    @PostMapping
    public ResponseEntity<ProdutoPedido> createProdutoPedido(@RequestBody ProdutoPedido produtoPedido) {
        try {
            ProdutoPedido _produtoPedido = produtoPedidoRepository.save(produtoPedido);
            return new ResponseEntity<>(_produtoPedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /produto-pedido/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoPedido> updateProdutoPedido(@PathVariable("id") long id, @RequestBody ProdutoPedido produtoPedido) {
        Optional<ProdutoPedido> produtoPedidoData = produtoPedidoRepository.findById(id);
        if (produtoPedidoData.isPresent()) {
            ProdutoPedido _produtoPedido = produtoPedidoData.get();
            _produtoPedido.setQuantidade(produtoPedido.getQuantidade());
            _produtoPedido.setPrecoUnitario(produtoPedido.getPrecoUnitario());
            _produtoPedido.setDesconto(produtoPedido.getDesconto());
            _produtoPedido.setProduto(produtoPedido.getProduto());
            _produtoPedido.setPedido(produtoPedido.getPedido());
            return new ResponseEntity<>(produtoPedidoRepository.save(_produtoPedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /produto-pedido/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProdutoPedido(@PathVariable("id") long id) {
        try {
            produtoPedidoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE /produto-pedido
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllProdutoPedidos() {
        try {
            produtoPedidoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
