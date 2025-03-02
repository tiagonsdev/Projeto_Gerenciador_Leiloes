
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retorna a Connection diretamente
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/vendas", "root", "tiago");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;  // Retorna null em caso de erro
        }
    }
    
}
