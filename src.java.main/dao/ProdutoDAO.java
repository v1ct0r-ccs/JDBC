package dao;

import dao.jdbc.ConnectionFactory;
import domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_PRODUTO (ID, CODIGO, TIPO, MODELO, VALOR, ESTOQUE) VALUES (nextval('SQ_PRODUTO'), ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, produto.getCodigo());
            stm.setString(2, produto.getTipo());
            stm.setString(3, produto.getModelo());
            stm.setDouble(4, produto.getValor());
            stm.setInt(5, produto.getEstoque());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !connection.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Produto consultar(Integer codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_PRODUTO WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setCodigo(rs.getInt("codigo"));
                produto.setTipo(rs.getString("tipo"));
                produto.setModelo(rs.getString("modelo"));
                produto.setValor(rs.getDouble("valor"));
                produto.setEstoque(rs.getInt("estoque"));
            }
            return produto;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !connection.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_produto WHERE CODIGO = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, produto.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (stm != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_PRODUTO";
            stm = connection.prepareStatement(sql);

            rs = stm.executeQuery();

            while (rs.next()) {
                produto = new Produto();
                Integer id = rs.getInt("ID");
                Integer codigo = rs.getInt("CODIGO");
                String tipo = rs.getString("TIPO");
                String modelo = rs.getString("MODELO");
                Double valor = rs.getDouble("VALOR");
                Integer estoque = rs.getInt("ESTOQUE");
                produto.setId(id);
                produto.setCodigo(codigo);
                produto.setTipo(tipo);
                produto.setModelo(modelo);
                produto.setValor(valor);
                produto.setEstoque(estoque);
                list.add(produto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return list;
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE TB_PRODUTO ");
            sb.append("SET VALOR = ?, ESTOQUE = ? ");
            sb.append("WHERE CODIGO = ?");
            String sql = sb.toString();
            stm = connection.prepareStatement(sql);
            stm.setDouble(1, produto.getValor());
            stm.setInt(2, produto.getEstoque());
            stm.setInt(3, produto.getCodigo());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()){
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

}
