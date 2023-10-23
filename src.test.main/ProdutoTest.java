import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {

    IProdutoDAO produtoDAO;

    @Test
    public void cadastrarProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(123);
        produto.setTipo("Console");
        produto.setModelo("PS5");
        produto.setValor(399.00);
        produto.setEstoque(40);

        Integer qtd = produtoDAO.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getModelo(), produtoBD.getModelo());

        Integer qtdDel = produtoDAO.excluir(produtoBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void consultarProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(456);
        produto.setTipo("Jogo");
        produto.setModelo("Forza Horizon - Xbox SX");
        produto.setValor(38.00);
        produto.setEstoque(8);

        Integer qtd = produtoDAO.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getModelo(), produtoBD.getModelo());

        Integer qtdDel = produtoDAO.excluir(produtoBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void excluirProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(789);
        produto.setTipo("Acessórios");
        produto.setModelo("Controle - Switch");
        produto.setValor(60.00);
        produto.setEstoque(15);

        Integer qtd = produtoDAO.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getModelo(), produtoBD.getModelo());

        Integer qtdDel = produtoDAO.excluir(produtoBD);
        assertNotNull(qtdDel);
    }

    @Test
    public void buscarTodosProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto1 = new Produto();
        produto1.setCodigo(123);
        produto1.setTipo("Console");
        produto1.setModelo("PS5");
        produto1.setValor(399.00);
        produto1.setEstoque(40);

        Integer pro1 = produtoDAO.cadastrar(produto1);
        assertTrue(pro1 == 1);

        Produto produto2 = new Produto();
        produto2.setCodigo(456);
        produto2.setTipo("Jogo");
        produto2.setModelo("Forza Horizon - Xbox SX");
        produto2.setValor(38.00);
        produto2.setEstoque(8);

        Integer pro2 = produtoDAO.cadastrar(produto2);
        assertTrue(pro2 == 1);

        List<Produto> list = produtoDAO.buscarTodos();
        assertNotNull(list);
        assertEquals(2, list.size());

        for (Produto pro : list) {
            Integer qtdDel = produtoDAO.excluir(pro);
            assertNotNull(qtdDel);
        }

        list = produtoDAO.buscarTodos();
        assertEquals(list.size(), 0);
    }

    @Test
    public void atualizarProdutoTest() throws Exception {
        produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo(123);
        produto.setTipo("Console");
        produto.setModelo("PS5");
        produto.setValor(399.00);
        produto.setEstoque(40);

        Integer qtd = produtoDAO.cadastrar(produto);
        assertTrue(qtd == 1);

        Produto produtoBD = produtoDAO.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getModelo(), produtoBD.getModelo());

        produtoBD.setValor(349.99);
        produtoBD.setEstoque(29);
        Integer qtdAtt = produtoDAO.atualizar(produtoBD);
        assertTrue(qtdAtt == 1);

        Produto produtoBD1 = produtoDAO.consultar(123);
        assertNotNull(produtoBD1);
        assertNotEquals(399.00, produtoBD.getValor());
        assertEquals(produtoBD.getValor(), produtoBD1.getValor());
        assertEquals(produtoBD.getEstoque(), produtoBD1.getEstoque());

        Integer qtdDel = produtoDAO.excluir(produtoBD);
        assertNotNull(qtdDel);
    }
}
