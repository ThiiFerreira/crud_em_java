package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;

    public ResultSet autencicacaoUsuario(UsuarioDTO objusuariodto) {
        // faz a conexao com o banco
        conn = new ConexaoDAO().conectaBD();
        try {
            String sql = "select * from usuario where nome_usuario = ? and senha_usuario = ?";

            PreparedStatement pstm = conn.prepareCall(sql);

            pstm.setString(1, objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getSenha_usuario());

            //recebe o comando executado
            ResultSet rs = pstm.executeQuery();

            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO" + erro);
            return null;
        }

    }
}
