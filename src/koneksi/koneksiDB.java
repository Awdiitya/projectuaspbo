/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author Acer
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksiDB {
public static Connection ambilkoneksidatabase() {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbperpustakaan"; //ganti dengan database mu
        String user = "root";
        String password = "";
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "                       Cek Lagi Koneksi !\n"
                        + "Silahkan Aktifkan Mysql di XAMPP terlebih dahulu");
               System.exit(0);
            }

        }
        return conn;
    }
}