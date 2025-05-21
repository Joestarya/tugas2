/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author L E N O V O
 */
public class modeltabletugas2 extends AbstractTableModel{

    List<tugas2> t2;
    public modeltabletugas2(List<tugas2>t2){
        this.t2 = t2;
    }
    
    @Override
    public int getRowCount() {
        return t2.size();
        
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column){
        return switch (column) {
            case 0 -> "ID";
            case 1 -> "judul";
            case 2 -> "genre";
            case 3 -> "penulis";
            case 4 -> "penerbit";
            case 5 -> "lokasi";
            case 6 -> "stock";
            default -> null;
        };
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        return switch (column) {
            case 0 -> t2.get(row).getId();
            case 1 -> t2.get(row).getJudul();
            case 2 -> t2.get(row).getGenre();
            case 3 -> t2.get(row).getPenulis();
            case 4 -> t2.get(row).getPenerbit();
            case 5 -> t2.get(row).getLokasi();
            case 6 -> t2.get(row).getStock();
            default -> null;
        };
    }
    
}
