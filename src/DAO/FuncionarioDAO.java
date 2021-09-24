package DAO;

import DTO.FuncionarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {

        String sql = "insert into funcionario (nome_funcionario, endereco_funcionario) values (?,?)";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "FUNCIONARIO CADASTRADO COM SUCESSO");

            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FUNCIONARIODAO Cadastrar" + erro);
        }

    }

    public ArrayList<FuncionarioDTO> PesquisarFuncionario() {
        String sql = "select * from funcionario";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                FuncionarioDTO objfuncionarioDTO = new FuncionarioDTO();
                objfuncionarioDTO.setId_funcionario(rs.getInt("id_funcionario"));
                objfuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objfuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario"));
                lista.add(objfuncionarioDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar" + erro);
        }
        return lista;
    }

    public void alterarFuncionario(FuncionarioDTO objfuncionariodto) {
        String sql = "update funcionario set nome_funcionario = ?, endereco_funcionario = ? where id_funcionario = ?";
        conn = new ConexaoDAO().conectaBD();

        try {
            //ABRE A CONEXAO COM O BD
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setInt(3, objfuncionariodto.getId_funcionario());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "FUNCIONARIO ALTERADO COM SUCESSO");

            //FECHA A CONEXAO COM BD
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FUNCIONARIODAO Alterar" + erro);
        }

    }

    public void deletarFuncionario(FuncionarioDTO objfuncionariodto) {
        String sql = "delete from funcionario where id_funcionario = ?";
        conn = new ConexaoDAO().conectaBD();

        try {
            //ABRE A CONEXAO COM O BD
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objfuncionariodto.getId_funcionario());
            
            pstm.execute();
            JOptionPane.showMessageDialog(null, "FUNCIONARIO DELETADO COM SUCESSO");

            //FECHA A CONEXAO COM BD
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FUNCIONARIODAO deletar" + erro);
        }

    }
}
