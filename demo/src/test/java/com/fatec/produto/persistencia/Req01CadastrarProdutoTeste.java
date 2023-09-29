package com.fatec.produto.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Produto;
import com.example.demo.ProdutoRepository;

@SpringBootTest
class Req01CadastrarProdutoTeste {
	@Autowired
	ProdutoRepository repository;

	@Test
	void ct01_cadastrar_produto_sucesso() {

		// dado que nao exista nenhum registro cadastrado
		repository.deleteAll();
		// quando o usuario cadastra um produto
		Produto produto = new Produto();
		produto.setDescricao("Banana Prata");
		produto.setCategoria("Fruta");
		produto.setCusto(5.50);
		produto.setQtdEstoque(300);
		Produto p = repository.save(produto);
		// entao o produto fica disponivel para consulta
		assertEquals(1, repository.count());
		assertNotNull(p);
	}
	@Test
	void ct02_cadastrar_produto_descricao_invalida() {
		try {
			Produto produto = new Produto();
			produto.setDescricao("");
			fail("Deveria disparar uma exception");
		}catch (IllegalArgumentException e) {
			assertEquals("A descrição não pode ser branco", e.getMessage());
		}
	}
}
