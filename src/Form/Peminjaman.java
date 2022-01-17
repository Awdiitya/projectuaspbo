 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

/**
 *
 * @author Acer
 */
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.text.ParseException; //untuk tbl klik mouse tgl
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksiDB;

// untuk ireport > library
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Peminjaman extends javax.swing.JFrame {
    public String tglp;
    public String tglk;
    private String sql;
    private PreparedStatement ps=null;
    public Connection con;
    private Object conn;
    /**
     * Creates new form Peminjaman
     */
    public Peminjaman() {
        initComponents();
        setLocationRelativeTo(this);
        tampilkanDataBuku();
        tampilkanDataAnggota();
        tampilkanDataPinjam();
        bersih();
    }
    
      private void tampilkanDataAnggota() {
        try {
            String sql="select * from tb_anggota";
            PreparedStatement ps=null;
            Object[]row={"Id Anggota","Nama","Kelas","Jenis Kelamin","No. Handphone","Alamat","Status"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblAnggota.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(TblAnggota);
            ResultSet rs=null;
            ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                String id=rs.getString(1);
                String nama=rs.getString(2);
                String kelas=rs.getString(3);
                String jk=rs.getString(4);
                String hp=rs.getString(5);
                String alamat=rs.getString(6);
                String status=rs.getString(7);
                String [] baris={id,nama,kelas,jk,hp,alamat,status};
                dtm.addRow(baris);
            }
        } catch (Exception e){}
    }

       private void tampilkanDataBuku() {
        try {
            String sql="select * from tb_buku";
            PreparedStatement ps=null;
            Object[]row={"Kode Buku","Judul Buku","Pengarang","Penerbit","Tahun Terbit","Kategori","Stok"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblBuku.setModel(dtm);
            jScrollPane3.setEnabled(true);
            jScrollPane3.setBorder(null);
            jScrollPane3.setViewportView(TblBuku);
            ResultSet rs=null;
            ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                String kode=rs.getString(1);
                String judul=rs.getString(2);
                String pengarang=rs.getString(3);
                String penerbit=rs.getString(4);
                String tahun=rs.getString(5);
                String kategori=rs.getString(6);
                String stok=rs.getString(7);
                String [] baris={kode,judul,pengarang,penerbit,tahun,kategori,stok};
                dtm.addRow(baris);
            }
        } catch (Exception e){}
    }
       
       private void tampilkanDataPinjam() {
        try {
            String sql="select * from relasiperpus";
            PreparedStatement ps=null;
            Object[]row={"Kode Pinjam","Id Anggota","Nama","Kelas","Kode Buku","Judul Buku","Pengarang","Stok","Tgl Pinjam","Tgl Kembali","Keterangan"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblPinjam.setModel(dtm);
            jScrollPane4.setEnabled(true);
            jScrollPane4.setBorder(null);
            jScrollPane4.setViewportView(TblPinjam);
            ResultSet rs=null;
            ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()) {
                String kdpinjam=rs.getString(1);
                String id=rs.getString(2);
                String nama=rs.getString(3);
                String kelas=rs.getString(4);
                String kdbuku=rs.getString(9);
                String judul=rs.getString(10);
                String pengarang=rs.getString(11);
                String stok=rs.getString(15);
                String pinjam=rs.getString(16);
                String kembali=rs.getString(17);
                String ket=rs.getString(19);
                String [] baris={kdpinjam,id,nama,kelas,kdbuku,judul,pengarang,stok,pinjam,kembali,ket};
                dtm.addRow(baris);
            }
        } catch (Exception e){}
    }
       
    
       private void bersih(){
        tfKodePinjam.setText("");
        tfKodePinjam.setEditable(true);
        tfId.setText("");
        tfId.setEditable(true);
        tfNama.setText("");
         tfNama.setEditable(true);
        tfKelas.setText("");
        tfKelas.setEditable(true);
        tfKodeBuku.setText("");
        tfKodeBuku.setEditable(true);
        tfJudul.setText("");
        tfJudul.setEditable(true);
        tfPengarang.setText("");
        tfPengarang.setEditable(true);
        tfStok.setText("");
        tfStok.setEditable(true);
        tfJumlahPinjam.setText("");
        jDatePinjam.setDate(null);
        jDateKembali.setDate(null);
        jDatePinjam.setEnabled(true);
        jDateKembali.setEnabled(true);
        tfCariDataAnggota.setText("");
        tfCariDataBuku.setText("");
        tfCariDataPinjam.setText("");
        btnDelete.setEnabled(false);
        btnSave.setEnabled(true);       
        tampilkanDataBuku();
        tampilkanDataAnggota();
        tampilkanDataPinjam();
    }
       
       private void CariDataBuku() {
        try {
            String cari=tfCariDataBuku.getText();
            String sql="select * from tb_buku where kode_buku like'%"+cari+"%' or judul like'%"+cari+"%' or pengarang like'%"+cari+"%' or kategori like'%"+cari+"%' ORDER BY stok_buku DESC";
            PreparedStatement ps=null;
            Object[]row={"Kode Buku","Judul Buku","Pengarang","Penerbit","Tahun Terbit","Kategori","Stok"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblBuku.setModel(dtm);
            jScrollPane3.setEnabled(true);
            jScrollPane3.setBorder(null);
            jScrollPane3.setViewportView(TblBuku);
            ResultSet rs=null;
            ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String kode=rs.getString(1);
                String judul=rs.getString(2);
                String pengarang=rs.getString(3);
                String penerbit=rs.getString(4);
                String tahun=rs.getString(5);
                String kategori=rs.getString(6);
                String stok=rs.getString(7);
                String [] baris={kode,judul,pengarang,penerbit,tahun,kategori,stok};
                dtm.addRow(baris);
            }
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak dapat ditemukan !" +e.getMessage());
        }
    }
       
       private void CariDataAnggota() {
        try {
            String cari=tfCariDataAnggota.getText();
            String sql="select * from tb_anggota where id_anggota like'%"+cari+"%' or nama like'%"+cari+"%' or kelas like'%"+cari+"%' or jk like'%"+cari+"%' or telp like'%"+cari+"%' ORDER BY status DESC";
            PreparedStatement ps=null;
            Object[]row={"Id Anggota","Nama","Kelas","Jenis Kelamin","No. Handphone","Alamat","Status"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblAnggota.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(TblAnggota);
            ResultSet rs=null;
            ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String id=rs.getString(1);
                String nama=rs.getString(2);
                String kelas=rs.getString(3);
                String jk=rs.getString(4);
                String hp=rs.getString(5);
                String alamat=rs.getString(6);
                String status=rs.getString(7);
                String [] baris={id,nama,kelas,jk,hp,alamat,status};
                dtm.addRow(baris);
            }
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak dapat ditemukan !" +e.getMessage());
        }
    }
       
       private void CariDataPinjam() {
        try {
            String cari=tfCariDataPinjam.getText();
            String sql="select * from relasiperpus where kode_pinjam like'%"+cari+"%' or id_anggota like'%"+cari+"%' or nama like'%"+cari+"%' or judul like'%"+cari+"%' order by tgl_pinjam desc";
            PreparedStatement ps=null;
            Object[]row={"Kode Pinjam","Id Anggota","Nama","Kelas","Kode Buku","Judul Buku","Pengarang","Stok","Tgl Pinjam","Tgl Kembali","Keterangan"};
            DefaultTableModel dtm=new DefaultTableModel(null,row);
            TblPinjam.setModel(dtm);
            jScrollPane4.setEnabled(true);
            jScrollPane4.setBorder(null);
            jScrollPane4.setViewportView(TblPinjam);
            ResultSet rs=null;
            ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String pinjam=rs.getString(1);
                String id=rs.getString(2);
                String nama=rs.getString(3);
                String kelas=rs.getString(4);
                String buku=rs.getString(9);
                String judul=rs.getString(10);
                String pengarang=rs.getString(11);
                String tglp=rs.getString(16);
                String tglk=rs.getString(17);
                String ket=rs.getString(19);
                String [] baris={pinjam,id,nama,kelas,buku,judul,pengarang,tglp,tglk,ket};
                dtm.addRow(baris);
            }
            
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak dapat ditemukan !" +e.getMessage());
        }
    }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfKodePinjam = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        tfKelas = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        tfKodeBuku = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        tfJudul = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        tfPengarang = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jDatePinjam = new com.toedter.calendar.JDateChooser();
        jDateKembali = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblAnggota = new javax.swing.JTable();
        tfCariDataAnggota = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        tfStok = new javax.swing.JTextField();
        tfket = new javax.swing.JTextField();
        tfJumlahPinjam = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        tfCariDataBuku = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblBuku = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tfCariDataPinjam = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        judul = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblPinjam = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(28, 42, 57));
        jLabel13.setText("Kode Pinjam");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tfKodePinjam.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfKodePinjam.setForeground(new java.awt.Color(28, 42, 57));
        tfKodePinjam.setBorder(null);
        jPanel7.add(tfKodePinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 247, -1));

        jSeparator6.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 257, 10));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(28, 42, 57));
        jLabel14.setText("ID Anggota");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jSeparator7.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 257, 10));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(28, 42, 57));
        jLabel15.setText("Nama Anggota");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        tfNama.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfNama.setForeground(new java.awt.Color(28, 42, 57));
        tfNama.setBorder(null);
        tfNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaActionPerformed(evt);
            }
        });
        jPanel7.add(tfNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 247, -1));

        jSeparator8.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 257, 10));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(28, 42, 57));
        jLabel16.setText("Kelas");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 72, -1));

        tfKelas.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfKelas.setForeground(new java.awt.Color(28, 42, 57));
        tfKelas.setBorder(null);
        jPanel7.add(tfKelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 247, -1));

        jSeparator9.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 257, 10));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(28, 42, 57));
        jLabel17.setText("Kode Buku");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        tfKodeBuku.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfKodeBuku.setForeground(new java.awt.Color(28, 42, 57));
        tfKodeBuku.setBorder(null);
        jPanel7.add(tfKodeBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 243, -1));

        jSeparator10.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 257, 10));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(28, 42, 57));
        jLabel18.setText("Tanggal Pinjam");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(28, 42, 57));
        jLabel20.setText("Tanggal Kembali");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 130, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(28, 42, 57));
        jLabel19.setText("Judul Buku");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        tfId.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfId.setForeground(new java.awt.Color(28, 42, 57));
        tfId.setBorder(null);
        jPanel7.add(tfId, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 247, -1));

        tfJudul.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfJudul.setForeground(new java.awt.Color(28, 42, 57));
        tfJudul.setBorder(null);
        jPanel7.add(tfJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 243, -1));

        jSeparator13.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 257, 10));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(28, 42, 57));
        jLabel21.setText("Pengarang");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        tfPengarang.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfPengarang.setForeground(new java.awt.Color(28, 42, 57));
        tfPengarang.setBorder(null);
        jPanel7.add(tfPengarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 243, -1));

        jSeparator14.setForeground(new java.awt.Color(28, 42, 57));
        jPanel7.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 257, 10));

        btnSave.setBackground(new java.awt.Color(102, 102, 255));
        btnSave.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Pinjam");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel7.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 110, 40));

        btnDelete.setBackground(new java.awt.Color(102, 102, 255));
        btnDelete.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel7.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 110, 40));

        jDatePinjam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDatePinjamPropertyChange(evt);
            }
        });
        jPanel7.add(jDatePinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 130, 20));

        jDateKembali.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateKembaliPropertyChange(evt);
            }
        });
        jPanel7.add(jDateKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 130, -1));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 280, 560));

        TblAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        TblAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblAnggota);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 990, 110));

        tfCariDataAnggota.setText("jTextField1");
        tfCariDataAnggota.setBorder(null);
        tfCariDataAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariDataAnggotaActionPerformed(evt);
            }
        });
        tfCariDataAnggota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariDataAnggotaKeyReleased(evt);
            }
        });
        getContentPane().add(tfCariDataAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 150, 20));
        getContentPane().add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 150, 20));

        tfStok.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfStok.setForeground(new java.awt.Color(28, 42, 57));
        tfStok.setText("stok");
        tfStok.setBorder(null);
        getContentPane().add(tfStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 60, 0, -1));

        tfket.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfket.setForeground(new java.awt.Color(28, 42, 57));
        tfket.setText("stok");
        tfket.setBorder(null);
        getContentPane().add(tfket, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 60, 0, -1));

        tfJumlahPinjam.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tfJumlahPinjam.setForeground(new java.awt.Color(28, 42, 57));
        tfJumlahPinjam.setText("pinjam");
        tfJumlahPinjam.setBorder(null);
        tfJumlahPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfJumlahPinjamActionPerformed(evt);
            }
        });
        getContentPane().add(tfJumlahPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 0, -1));

        jLabel23.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cari Data Anggota");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 140, 20));

        jLabel24.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Cari Data Buku");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, 110, 20));

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 590, 80, 40));

        tfCariDataBuku.setText("jTextField1");
        tfCariDataBuku.setBorder(null);
        tfCariDataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariDataBukuActionPerformed(evt);
            }
        });
        tfCariDataBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariDataBukuKeyReleased(evt);
            }
        });
        getContentPane().add(tfCariDataBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 160, 20));
        getContentPane().add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 160, 10));

        TblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        TblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblBukuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TblBuku);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 990, 110));

        btnRefresh.setBackground(new java.awt.Color(0, 204, 0));
        btnRefresh.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, 90, 40));

        btnPrint.setBackground(new java.awt.Color(204, 204, 255));
        btnPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 590, 90, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/library.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 990, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/library.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 990, 40));

        jLabel25.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Cari Data Peminjam");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 150, 20));

        tfCariDataPinjam.setText("jTextField1");
        tfCariDataPinjam.setBorder(null);
        tfCariDataPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCariDataPinjamActionPerformed(evt);
            }
        });
        tfCariDataPinjam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCariDataPinjamKeyReleased(evt);
            }
        });
        getContentPane().add(tfCariDataPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 440, 150, 20));
        getContentPane().add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 150, 10));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/library.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 990, 40));

        judul.setBackground(new java.awt.Color(0, 0, 0));
        judul.setFont(new java.awt.Font("Rockwell", 1, 28)); // NOI18N
        judul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        judul.setText("Form Pengembalian Buku");
        getContentPane().add(judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1330, 50));

        TblPinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        TblPinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblPinjamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TblPinjam);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, 990, 110));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/ddp-EX3gFC6eP4c-unsplash.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1330, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaActionPerformed

    private void TblAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblAnggotaMouseClicked
        // TODO add your handling code here:
         int baris = TblAnggota.getSelectedRow();
        String status = "Pinjam";
        tfId.setText(TblAnggota.getModel().getValueAt(baris, 0).toString());
        tfNama.setText(TblAnggota.getModel().getValueAt(baris, 1).toString());
        tfKelas.setText(TblAnggota.getModel().getValueAt(baris, 2).toString());
         if(status.equals(TblAnggota.getValueAt(baris, 6))){
            JOptionPane.showMessageDialog(null, "               Tidak dapat melakukan peminjaman\nSaat ini "+tfNama.getText()+" sedang dalam masa peminjaman !","Kesalahan", JOptionPane.ERROR_MESSAGE);  

        }else
         {
              tfKodeBuku.setText("");
            tfJudul.setText("");
            tfPengarang.setText("");
        tfId.setEditable(false);
        tfNama.setEditable(false);
        tfKelas.setEditable(false);
        }
    }//GEN-LAST:event_TblAnggotaMouseClicked

    private void tfCariDataAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariDataAnggotaActionPerformed
    }//GEN-LAST:event_tfCariDataAnggotaActionPerformed

    private void tfCariDataAnggotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariDataAnggotaKeyReleased
        CariDataAnggota();
    }//GEN-LAST:event_tfCariDataAnggotaKeyReleased

    private void tfCariDataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariDataBukuActionPerformed
    }//GEN-LAST:event_tfCariDataBukuActionPerformed

    private void tfCariDataBukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariDataBukuKeyReleased
        // TODO add your handling code here:
        CariDataBuku();
    }//GEN-LAST:event_tfCariDataBukuKeyReleased

    private void TblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblBukuMouseClicked
        // TODO add your handling code here:
        int row = TblBuku.getSelectedRow();
        String stok = "0";
        tfKodeBuku.setText(TblBuku.getModel().getValueAt(row,0).toString());
        tfJudul.setText(TblBuku.getModel().getValueAt(row,1).toString());
        tfPengarang.setText(TblBuku.getModel().getValueAt(row,2).toString());
        if(stok.equals(TblBuku.getValueAt(row, 6))){
            JOptionPane.showMessageDialog(null, "Stok buku "+tfJudul.getText()+" habis !","Kesalahan", JOptionPane.ERROR_MESSAGE);  
        }else
        {
         tfStok.setText(TblBuku.getModel().getValueAt(row,6).toString());
         tfKodeBuku.setEditable(false);
        tfJudul.setEditable(false);
        tfPengarang.setEditable(false);
        tfStok.setEditable(false);
        tfJumlahPinjam.setText("1");
        }
    }//GEN-LAST:event_TblBukuMouseClicked

    private void tfJumlahPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfJumlahPinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfJumlahPinjamActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
           try{
            if((tfKodePinjam.getText().equals(""))
              ||(tfId.getText().equals(""))
              ||(tfNama.getText().equals(""))
              ||(tfKelas.getText().equals(""))
                    ||(tfKodeBuku.getText().equals(""))
                    ||(tfJudul.getText().equals(""))
                    ||(tfPengarang.getText().equals("")))
            {
                JOptionPane.showMessageDialog(rootPane, "Lengkapi data Dengan Benar");
            }
            else{
                 String sqlA="select * from tb_pinjam where kode_pinjam='"+tfKodePinjam.getText()+"'and id_anggota='"+tfId.getText()+"'";
                ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sqlA);
                ResultSet rs=ps.executeQuery(sqlA);
                System.out.println(rs.first());
                 if(rs.first()==true)
                {
                    JOptionPane.showMessageDialog(rootPane, "Data Sudah Anda");
                }
                else
                {
                   sql="insert into tb_pinjam(kode_pinjam,id_anggota,kode_buku,tgl_pinjam,tgl_kembali,jumlah_pinjam,ket,desk,denda)values"+
                    "('" + tfKodePinjam.getText() +
                    "','" + tfId.getText()+
                    "','"+ tfKodeBuku.getText()+
                    "','"+ tglp+
                    "','"+ tglk+
                    "','1','Belum kembali','-','0')";
                    ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
                    ps.executeUpdate(sql);
                    JOptionPane.showMessageDialog(rootPane,"Data Berhasil Disimpan");
                      
                    Integer stokbuku,jumlahpinjam,sisastok;
                    stokbuku=Integer.parseInt(tfStok.getText());
                     jumlahpinjam=Integer.parseInt(tfJumlahPinjam.getText());
            
                   sisastok=(stokbuku-jumlahpinjam);
                    String sqlB="update tb_buku set stok_buku='"+sisastok+"' where kode_buku='"+ tfKodeBuku.getText()+"'";
                    ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
                    ps.executeUpdate(sqlB);
                    
                    String sqlC="update tb_anggota set status='Pinjam' where id_anggota='"+tfId.getText()+"'";
                     ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
                    ps.executeUpdate(sqlC);
                     tampilkanDataPinjam();
                    tampilkanDataBuku();
                    tampilkanDataAnggota();
                    bersih();
            }
            }
         } catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Cek Lagi Sistem"+e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try{
            int jawab;
             int baris = TblPinjam.getSelectedRow();
            String status = "Belum kembali";
            if(status.equals(TblPinjam.getValueAt(baris, 10))){
                JOptionPane.showMessageDialog(null, "Tidak dapat menghapus selama dalam masa peminjaman");
            }else 
             if((jawab = JOptionPane.showConfirmDialog(null,"Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)) == 0){
              sql="delete from tb_pinjam where kode_pinjam='"+tfKodePinjam.getText()+"'";
              ps=koneksi.koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
              ps.executeUpdate(sql);
              JOptionPane.showMessageDialog(rootPane,"Data Berhasil Dihapus");
                    
              String sqlB="update tb_buku set stok_buku='"+tfStok.getText()+"' where kode_buku='"+tfKodeBuku.getText()+"'";
               ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
               ps.executeUpdate(sqlB);
                    
               String sqlC="update tb_anggota set status='Tidak pinjam' where id_anggota='"+tfId.getText()+"'";
               ps=koneksiDB.ambilkoneksidatabase().prepareStatement(sql);
               ps.executeUpdate(sqlC);
                tampilkanDataPinjam();
                tampilkanDataBuku();
                tampilkanDataAnggota();
                bersih();
                } 
        }catch (Exception e)
                    {  
                        JOptionPane.showMessageDialog(rootPane, "Cek Lagi Sistem"+e.getMessage());
                    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tfCariDataPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariDataPinjamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariDataPinjamActionPerformed

    private void tfCariDataPinjamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCariDataPinjamKeyReleased
        // TODO add your handling code here:
        CariDataPinjam();
    }//GEN-LAST:event_tfCariDataPinjamKeyReleased

    private void TblPinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblPinjamMouseClicked
        // TODO add your handling code here:
        int row = TblPinjam.getSelectedRow();
        tfJumlahPinjam.setText("1");
        tfKodePinjam.setText(TblPinjam.getModel().getValueAt(row,0).toString());
        tfId.setText(TblPinjam.getModel().getValueAt(row,1).toString());
        tfNama.setText(TblPinjam.getModel().getValueAt(row,2).toString());
        tfKelas.setText(TblPinjam.getModel().getValueAt(row,3).toString());
        tfKodeBuku.setText(TblPinjam.getModel().getValueAt(row,4).toString());
        tfJudul.setText(TblPinjam.getModel().getValueAt(row,5).toString());
        tfPengarang.setText(TblPinjam.getModel().getValueAt(row,6).toString());
        String stokbuku1;
        Integer stokbuku2, jumlahpinjam,returnstok;
        stokbuku1=(String)TblPinjam.getModel().getValueAt(row,7);
        jumlahpinjam=Integer.parseInt(tfJumlahPinjam.getText());
        stokbuku2=Integer.parseInt(stokbuku1);
        returnstok=(jumlahpinjam+stokbuku2);
        tfStok.setText(String.valueOf(returnstok));
        //untuk tgl pinjam
        String tglPinjam=(String) TblPinjam.getModel().getValueAt(row,8);
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date p = format.parse(tglPinjam);
            jDatePinjam.setDate(p);
        }catch(ParseException ex){}
        
        //untuk tgl kembali
        String tglKembali=(String) TblPinjam.getModel().getValueAt(row,9);
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date k = format.parse(tglKembali);
            jDateKembali.setDate(k);
        }catch(ParseException ex){}
        tfket.setText(TblPinjam.getModel().getValueAt(row,10).toString());   
        btnDelete.setEnabled(true);
        btnSave.setEnabled(false);
    }//GEN-LAST:event_TblPinjamMouseClicked

    private void jDatePinjamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDatePinjamPropertyChange
          try {
            if(jDatePinjam.getDate()!=null) {
                String ubahtanggal="yyyy/MM/dd";
                SimpleDateFormat fp=new SimpleDateFormat(ubahtanggal);
                tglp=String.valueOf(fp.format(jDatePinjam.getDate()));
            }
            }catch(Exception e){  }
    }//GEN-LAST:event_jDatePinjamPropertyChange

    private void jDateKembaliPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateKembaliPropertyChange
        // TODO add your handling code here:
        try {
            if(jDateKembali.getDate()!=null) {
                String ubahtanggal="yyyy/MM/dd";
                SimpleDateFormat fk=new SimpleDateFormat(ubahtanggal);
                tglk=String.valueOf(fk.format(jDateKembali.getDate()));
            }
            }catch(Exception e){  }
    }//GEN-LAST:event_jDateKembaliPropertyChange

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        tampilkanDataAnggota();
        tampilkanDataBuku();
        tampilkanDataPinjam();
        bersih();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int keluar=JOptionPane.showConfirmDialog(rootPane, "Keluar Program?","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(keluar==JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
    java.sql.Connection conn=null;
    try {
        String jdbcDriver = "com.mysql.jdbc.Driver";
        Class.forName(jdbcDriver);
        
        String url = "jdbc:mysql://localhost:3306/dbperpustakaan";
        String user = "root";
        String pass = "";
        
        conn = DriverManager.getConnection(url, user, pass);
        Statement stm = conn.createStatement();
        try {
            String report ="D:\\Semester 3\\Pemrograman Berorientasi Objek\\NetBeansProjects\\Perpustakaan\\src\\Form\\notapinjam.jasper" ;
            HashMap hash = new HashMap();      // Mengambil parameter dari ireport 
            hash.put("kode", tfKodePinjam.getText());
             JasperReport JR = JasperCompileManager.compileReport(report);
             JasperPrint JP = JasperFillManager.fillReport(JR, hash, conn);
            JasperViewer.viewReport(JP, false);      
             } catch(Exception rptexcpt) 
                        {
                           System.out.println("Gagal Menampilkan Laporan: "+ rptexcpt);
                        }   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Peminjaman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Peminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Peminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblAnggota;
    private javax.swing.JTable TblBuku;
    private javax.swing.JTable TblPinjam;
    private javax.swing.JLabel background;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private com.toedter.calendar.JDateChooser jDateKembali;
    private com.toedter.calendar.JDateChooser jDatePinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel judul;
    private javax.swing.JTextField tfCariDataAnggota;
    private javax.swing.JTextField tfCariDataBuku;
    private javax.swing.JTextField tfCariDataPinjam;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfJudul;
    private javax.swing.JTextField tfJumlahPinjam;
    private javax.swing.JTextField tfKelas;
    private javax.swing.JTextField tfKodeBuku;
    private javax.swing.JTextField tfKodePinjam;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfPengarang;
    private javax.swing.JTextField tfStok;
    private javax.swing.JTextField tfket;
    // End of variables declaration//GEN-END:variables
}
