/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOtugas2;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import DAOImplement.tugas2implement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author L E N O V O
 */
public class tugas2DAO implements tugas2implement{
    Connection connection;
    
    final String select = "SELECT * FROM form";
    final String insert = "INSERT INTO `form` (judul, genre, penulis, penerbit, lokasi, stock) VALUES (?, ?, ?, ?, ?, ?)";
    final String update = "update form set judul=?, genre=?, penulis=?, penerbit=?, lokasi=?, stock=? where id_buku=?";
    final String delete = "delete from form where id_buku=?";
    
    public tugas2DAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(tugas2 p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1,p.getJudul());
            statement.setString(2,p.getGenre());
            statement.setString(3,p.getPenulis());
            statement.setString(4,p.getPenerbit());
            statement.setString(5,p.getLokasi());
            statement.setString(6,p.getStock());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                p.setId(rs.getInt(1));
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                }
        }
    }

    @Override
    public void update(tugas2 p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1,p.getJudul());
            statement.setString(2,p.getGenre());
            statement.setString(3,p.getPenulis());
            statement.setString(4,p.getPenerbit());
            statement.setString(5,p.getLokasi());
            statement.setString(6,p.getStock());
            statement.setInt(7, p.getId());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
                }
        }
    }

    @Override
    public void delete(int id_buku) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id_buku);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try {
                statement.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<tugas2> getAll() {
        List<tugas2> t2 = null;
        try{
            t2 = new ArrayList<tugas2>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                tugas2 tg = new  tugas2();
                tg.setId(rs.getInt("id_buku"));
                tg.setGenre(rs.getString("genre"));
                tg.setJudul(rs.getString("judul"));
                tg.setLokasi(rs.getString("lokasi"));
                tg.setPenerbit(rs.getString("penerbit"));
                tg.setPenulis(rs.getString("penulis"));
                tg.setStock(rs.getString("stock"));
                t2.add(tg);
        }
        }catch(SQLException ex){
            Logger.getLogger(tugas2DAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        return t2;
    }
}
