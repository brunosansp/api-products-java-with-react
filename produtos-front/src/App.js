import { useState, useEffect } from 'react';
import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';

function App() {

  const produto = {
    codigo: 0,
    nome: '',
    marca: ''
  };

  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [produtos, setProdutos] = useState([]);
  const [objProduto, setObjProduto] = useState(produto);

  useEffect(() => {
    fetch("http://localhost:8080/listar")
      .then(response => response.json())
      .then(responseConverted => setProdutos(responseConverted));
  }, []); // O [] neste ponto, garante que será feita uma única requisição

  const aoDigitar = (e) => {
    // console.log(e.target);
    setObjProduto({ ...objProduto, [e.target.name]: e.target.value });
  };

  const cadastrar = () => {
    fetch("http://localhost:8080/cadastrar",
      {
        method: 'post',
        body: JSON.stringify(objProduto),
        headers: {
          'Content-type': 'application/json',
          'Accept': 'application/json'
        }
      }
    )
      .then(retorno => retorno.json())
      .then(retorno_convertido => {
        // console.log(retorno_convertido);
        if (retorno_convertido.mensagem !== undefined) {
          alert(retorno_convertido.mensagem);
        } else {
          setProdutos([...produtos], retorno_convertido);
          alert("Produto cadastrado com sucesso!");
          limparFormulario();
        }
      });
  };

  const limparFormulario = () => {
    setObjProduto(produto);
    setBtnCadastrar(true);
  };

  const selecionarProduto = (indice) => {
    setObjProduto(produtos[indice]);
    setBtnCadastrar(false);
  };

  return (
    <div>
      {/* <p>{JSON.stringify(produtos)}</p> */}
      {/* <p>{JSON.stringify(objProduto)}</p> */}
      <Formulario
        botao={btnCadastrar}
        eventKeyboard={aoDigitar}
        cadastrar={cadastrar}
        obj={objProduto}
        cancelar={limparFormulario} />

      <Tabela vetor={produtos} selecionar={selecionarProduto} />
    </div>
  );
}

export default App;
