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

  return (
    <div>
      {/* <p>{JSON.stringify(produtos)}</p> */}
      <p>{JSON.stringify(objProduto)}</p>
      <Formulario botao={btnCadastrar} eventKeyboard={aoDigitar} />
      <Tabela vetor={produtos} />
    </div>
  );
}

export default App;
