package com.curso.api.gestaovendas.controller;

import com.curso.api.gestaovendas.model.Produto;
import com.curso.api.gestaovendas.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @ApiOperation(value = "Listar todos produtos")
    @GetMapping("/getAll")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return new ResponseEntity<List<Produto>>(produtoService.getAllProdutos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Bucar produto por Descrição")
    @GetMapping("/getByNome")
    public ResponseEntity<List<Produto>> getByNome(@RequestParam String nome){
        return new ResponseEntity<List<Produto>>(produtoService.getByNome(nome), HttpStatus.OK);
    }


    @ApiOperation(value = "Bucar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        Optional<Produto>produtoOptional = produtoService.getIdProduto(id);

        return produtoOptional.isPresent() ? new ResponseEntity<Produto>(produtoOptional.get(), HttpStatus.OK) :
                ResponseEntity.notFound().build();
    }


}