package br.com.api.produtos.service;

import br.com.api.produtos.model.Produto;
import br.com.api.produtos.model.Response;
import br.com.api.produtos.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    
    private final ProdutoRepository produtoRepository;
    private final Response response;
    
    public ProdutoService(ProdutoRepository produtoRepository, Response response) {
        this.produtoRepository = produtoRepository;
        this.response = response;
    }
    
    public Iterable<Produto> listar() {
        return produtoRepository.findAll();
    }
    
    public ResponseEntity<?> cadastrar(Produto produto) {
        if (validaProduto(produto)) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(produtoRepository.save(produto), HttpStatus.CREATED);
    }
    
    public ResponseEntity<?> alterar(Produto produto) {
        if (validaProduto(produto)) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(produtoRepository.save(produto), HttpStatus.OK);
    }
    
    public ResponseEntity<Response> remover(long id) {
        produtoRepository.deleteById(id);
        response.setMensagem("Produto removido!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    public boolean validaProduto(Produto produto) {
        response.setMensagem("O nome e a marca do produto são obrigatórios!");
        return produto.getNome().isBlank() || produto.getMarca().isBlank();
    }
}
