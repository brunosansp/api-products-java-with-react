package br.com.api.produtos.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Response {

    private String mensagem;
}
