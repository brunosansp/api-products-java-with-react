package br.com.api.produtos.controller;

import br.com.api.produtos.model.Produto;
import br.com.api.produtos.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
