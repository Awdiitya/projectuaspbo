/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Acer
 */
public class main {
    public static void main (String [] args) throws ClassNotFoundException {
        new Form.Buku().setVisible(true);
        new Form.Anggota().setVisible(true);
        new Form.Peminjaman().setVisible(true);
        new Form.Pengembalian().setVisible(true);
        koneksi.koneksiDB.ambilkoneksidatabase();
    }
}