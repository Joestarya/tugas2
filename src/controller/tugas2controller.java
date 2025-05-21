
package controller;
import java.util.List;
import DAOtugas2.tugas2DAO;
import DAOImplement.tugas2implement;
import model.*;
import view.MainView;
import koneksi.connector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;


public class tugas2controller {
    MainView frame;
    tugas2implement impltugas2;
    List<tugas2> t2;
    
    public tugas2controller(MainView frame){
        this.frame = frame;
        impltugas2 = new tugas2DAO();
        t2 = impltugas2.getAll();
        
    }
    public void isitabel(){
        t2 = impltugas2.getAll();
        modeltabletugas2 mt = new modeltabletugas2(t2);
        frame.getTabelTugas2().setModel(mt);
    }
    public void insert(){
        tugas2 t2 = new tugas2();
        t2.setJudul(frame.getJudul().getText());
        t2.setGenre(frame.getGenre().getText());
        t2.setPenulis(frame.getPenulis().getText());
        t2.setPenerbit(frame.getPenerbit().getText());
        t2.setLokasi(frame.getLokasi().getText());
        t2.setStock(frame.getStock().getText());   
        impltugas2.insert(t2);
    }
    
    public void update(){
        tugas2 t2 = new tugas2();
        t2.setJudul(frame.getJudul().getText());
        t2.setGenre(frame.getGenre().getText());
        t2.setPenulis(frame.getPenulis().getText());
        t2.setPenerbit(frame.getPenerbit().getText());
        t2.setLokasi(frame.getLokasi().getText());
        t2.setStock(frame.getStock().getText());   
        t2.setId(Integer.parseInt(frame.getId_buku().getText()));
        impltugas2.update(t2);
    }
    public void delete(){
        int id_buku = Integer.parseInt(frame.getId_buku().getText());
        impltugas2.delete(id_buku);
    }
    
    public void search(String column, String keyword){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id_buku");
        model.addColumn("judul");
        model.addColumn("genre");
        model.addColumn("penulis");
        model.addColumn("penerbit");
        model.addColumn("lokasi");
        model.addColumn("stock");
        try {
            Connection conn = connector.connection();
            String sql = "SELECT * FROM form WHERE " + column + " LIKE ? "; 
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                model.addRow(new Object[] {
                    rs.getInt("id_buku"),
                    rs.getString("judul"),
                    rs.getString("genre"),
                    rs.getString("penulis"),
                    rs.getString("penerbit"),
                    rs.getString("lokasi"),
                    rs.getString("stock"),
                });
            }
            frame.getTabelTugas2().setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"OKOKOK" + e.getMessage());
        }
        
    }
}
