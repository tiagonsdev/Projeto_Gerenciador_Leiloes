/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean conectar() {
        try {
            conn = new conectaDAO().conectar();  // Agora conecta() retorna uma Connection
            return conn != null;  // Verifica se a conexão foi bem-sucedida
        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return false;  // Retorna falso se ocorrer algum erro
        }
    }

    // Método para salvar o produto no banco de dados
    public int salvar(ProdutosDTO produto) {
        int status;
        try {
            // Verifica se a conexão está ativa
            if (conn == null || conn.isClosed()) {
                if (!conectar()) {  // Se não estiver conectada, tenta conectar
                    throw new SQLException("Conexão com o banco de dados não estabelecida.");
                }
            }

            st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            st.setString(1, produto.getNome());
            st.setString(2, produto.getValor().toString());  // Se produto.getValor() for Double, use toString()
            st.setString(3, produto.getStatus());
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar produto: " + ex.getMessage());
            return ex.getErrorCode();
        } catch (Exception ex) {
            System.out.println("Erro ao formatar dados: " + ex.getMessage());
            return -1;
        }
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }

    // Método para cadastrar um produto (necessário chamar salvar ou outros métodos)
    public void cadastrarProduto(ProdutosDTO produto) {
        salvar(produto);  // Chama o método salvar, caso queira cadastrar
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
