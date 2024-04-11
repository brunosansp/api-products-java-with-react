package br.com.api.produtos.controller;

import br.com.api.produtos.model.Produto;
import br.com.api.produtos.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    @GetMapping("/")
    public String healthCheck() {
        return "API de produtos funcionando";
    }
    
    @GetMapping("/listar")
    public Iterable<Produto> listar() {
        return produtoService.listar();
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Produto produto) {
        return produtoService.cadastrar(produto);
    }
    
    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody Produto produto) {
        return produtoService.alterar(produto);
    }
    
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> remover(@PathVariable long id) {
        return produtoService.remover(id);
    }
}
