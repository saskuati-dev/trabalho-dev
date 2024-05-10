package com.devweb.vendas.control;

import org.springframework.web.bind.annotation.RestController;

import com.devweb.vendas.model.Produto;
import com.devweb.vendas.repository.ProdutoRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoRepositorio repo;


    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> getProdutos() {
        ResponseEntity<List<Produto>> result;
        try {
            List<Produto> lp = new ArrayList<>(repo.findAll());
            if (lp.isEmpty()) {
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                result = new ResponseEntity<>(lp, HttpStatus.OK);
            }
        } catch (Exception e) {
            result = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping(value = "/produto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        try{
            Produto _p = repo.save(new Produto(produto.getCodigo(), produto.getDescricao(), produto.getValor_custo(), produto.getValor_venda()));
            return new ResponseEntity<>(_p, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/produtoCod/{codigo}")
    public ResponseEntity<Produto> getProdutoByCodigo(@PathVariable("codigo") String codigo) {
        try {
            Optional<Produto> data = Optional.ofNullable(repo.findByCodigo(codigo));
            if (!data.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT /prod/produto/{codigo} : atualiza produto baseado no código
    @PutMapping("/produto/{codigo}")
    public ResponseEntity<Produto> updateProduto(@PathVariable("codigo") String codigo, @RequestBody Produto produto) {
        Optional<Produto> data = Optional.ofNullable(repo.findByCodigo(codigo));
        if (data.isPresent()) {
            Produto p = data.get();
            p.setCodigo(produto.getCodigo());
            p.setDescricao(produto.getDescricao());
            p.setValor_custo(produto.getValor_custo());
            p.setValor_venda(produto.getValor_venda());

            return new ResponseEntity<>(repo.save(p), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE /prod/produto/{codigo} : deleta produto baseado no código
    @DeleteMapping("/produto/{codigo}")
    public ResponseEntity<HttpStatus> deleteProduto(@PathVariable("codigo") String codigo) {
        try {
            repo.deleteByCodigo(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE /prod/produto : deleta todos os produtos
    @DeleteMapping("/produto")
    public ResponseEntity<HttpStatus> deleteAllProdutos() {
        try {
            repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
