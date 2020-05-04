package dao;
import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
public class UsuarioDAO { 
   private final Connection connection;
   public UsuarioDAO(){ 
        this.connection = new ConnectionFactory().getConnection();
    } 
   public List<Usuario> ListaUsuario(){
     String sql = "SELECT * FROM usuario ORDER BY nome";
     List<Usuario> lista = new ArrayList<>();
      try{
            PreparedStatement stmt = connection.prepareStatement(sql);
ResultSet rs = stmt.executeQuery();
            if(rs != null){
                while(rs.next()){
                  Usuario u = new Usuario();
                  u.setNome(rs.getString(2));
                  setId(rs.getString(1));
                  lista.add(u);
                }
                return lista;
            }else{
                return null;
            }
        }catch(Exception ex){
           return null;
        }
    }

    public void adiciona(Usuario usuario){ 
        String sql = "INSERT INTO usuario(nome,cpf,email,telefone) VALUES(?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 

    private void setId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String ExcluirUsuario(Usuario u){
        String sql = "DELETE FROM usuario WHERE id = ?";
       try {
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setInt(1, u.getid());
           if(stmt.executeUpdate() > 0){
           return "EXCLUIDO";
           }else{
           return "ERRO AO EXCLUIR";
           }
       } catch (SQLException ex) {
           return getMessage();
       }
       
      
}

    private String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
