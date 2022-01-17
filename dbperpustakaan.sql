-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11 Jan 2022 pada 19.16
-- Versi Server: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbperpustakaan`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `relasiperpus`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `relasiperpus` (
`kode_pinjam` varchar(8)
,`id_anggota` int(8)
,`nama` varchar(40)
,`kelas` varchar(5)
,`jk` enum('Laki-laki','Perempuan')
,`telp` varchar(13)
,`alamat` varchar(50)
,`status` enum('Pinjam','Tidak pinjam')
,`kode_buku` varchar(5)
,`judul` varchar(80)
,`pengarang` varchar(50)
,`penerbit` varchar(50)
,`tahun_terbit` int(4)
,`kategori` varchar(35)
,`stok_buku` int(3)
,`tgl_pinjam` date
,`tgl_kembali` date
,`jumlah_pinjam` int(1)
,`ket` enum('Sudah kembali','Belum kembali')
,`desk` enum('Telat','Tidak telat','Hilang','Perpanjang','-')
,`denda` double
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_anggota`
--

CREATE TABLE `tb_anggota` (
  `id_anggota` int(8) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `jk` enum('Laki-laki','Perempuan') NOT NULL,
  `telp` varchar(13) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `status` enum('Pinjam','Tidak pinjam') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_anggota`
--

INSERT INTO `tb_anggota` (`id_anggota`, `nama`, `kelas`, `jk`, `telp`, `alamat`, `status`) VALUES
(18023954, 'FIQRI BAYU SAHPUTRA', 'MI-7A', 'Laki-laki', '082119823340', 'jl. Tusam', 'Pinjam'),
(19202122, 'BABE CABITA', 'SU-9Z', 'Laki-laki', '080019283040', 'jl. R.A. Kartini', 'Pinjam'),
(19202123, 'RAHMAT', 'SI-9G', 'Laki-laki', '081293931010', 'jl. H.O.S. Cokroaminoto', 'Pinjam'),
(20202020, 'Claudia Pinkan', 'SI-8P', 'Perempuan', '089797091020', 'jl. Menuju Roma', 'Tidak pinjam'),
(20220011, 'ARIEF OKTAVIANDI', 'SI-3P', 'Laki-laki', '081282821919', 'jl. Khairul Anwar', 'Tidak pinjam'),
(20220159, 'ADITYA GUNAWAN', 'SI-3P', 'Laki-laki', '083190401002', 'kisaran', 'Pinjam'),
(20220204, 'REGI MEGA ALFANSI SRG', 'SI-3P', 'Perempuan', '082290845983', 'jl. F.L. Tobing', 'Tidak pinjam'),
(20220283, 'MUHAMMAD TOPAN', 'SI-3P', 'Laki-laki', '082212120000', 'hessa air genting', 'Tidak pinjam'),
(20220344, 'PRIA WIBOWO', 'SI-3P', 'Laki-laki', '083191912929', 'jl. DIponegoro', 'Tidak pinjam'),
(20220350, 'EKA YUDHA PRATAMA', 'SI-3F', 'Laki-laki', '089080706050', 'jl. Malik Ibrahim', 'Tidak pinjam'),
(20220397, 'FAHRIZAL', 'SI-3F', 'Laki-laki', '089879403434', 'Ujung kubu', 'Tidak pinjam'),
(20220414, 'TEGAR PRASASTIYO', 'SI-3F', 'Laki-laki', '081213141516', 'Air Joman', 'Tidak pinjam'),
(20220434, 'DEWI RAFIKA NASUTION', 'SI-3P', 'Perempuan', '089820324343', 'jl. Madong Lubbis', 'Tidak pinjam'),
(21220262, 'MUHAMMAD RIZKY FACHRI', 'SI-1P', 'Laki-laki', '081293489384', 'jl. W.R. Supratman', 'Pinjam'),
(21220281, 'FEBI YUWANDA SIHOMBING', 'SI-1Q', 'Laki-laki', '082293483939', 'jl. Madong Lubis', 'Pinjam'),
(21220500, 'DIMAS SURYA', 'SI-1Q', 'Laki-laki', '083170821231', 'jl. Imam Bonjol', 'Pinjam'),
(21220525, 'ABZIDAN THORIQ', 'SI-1Q', 'Laki-laki', '082198203201', 'jl. Mas Mansyur', 'Pinjam');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_buku`
--

CREATE TABLE `tb_buku` (
  `kode_buku` varchar(5) NOT NULL,
  `judul` varchar(80) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahun_terbit` int(4) NOT NULL,
  `kategori` varchar(35) NOT NULL,
  `stok_buku` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_buku`
--

INSERT INTO `tb_buku` (`kode_buku`, `judul`, `pengarang`, `penerbit`, `tahun_terbit`, `kategori`, `stok_buku`) VALUES
('BK001', '5 Cm', 'Dhonny Dhirgantoro', 'PT. Grasindo', 2005, 'Novel', 1),
('BK002', 'Sistem Basis Data Dan Sql', 'Didik Setiyadi, S.Kom., M.Kom.', 'Mitra Wacana Media', 2020, 'Teknologi', 0),
('BK003', 'Koala Kumal', 'Raditya Dika', 'Gagas Media', 2015, 'Novel', 4),
('Bk004', 'Menguasai Pemrograman Berorientasi Objek', 'Ade Rahmat Iskandar', 'Informatika', 2020, 'Teknologi', 0),
('BK005', 'Buku Mahir Web Programming', 'Ir. Yuniar Supardi, Defri Faizal Maulana S.', 'Elex Media Komputindo', 2019, 'Teknologi', 2),
('BK006', 'Perahu Kertas', 'Dewi Lestari (Dee)', 'Treudee Pustaka Sejati dan Bentang Pustaka', 2010, 'Novel', 4),
('BK007', 'Teori Dan Praktek Sistem Operasi', 'Zaid Romegar Mair', 'Deepublish', 2018, 'Teknologi', 2),
('BK008', 'Struktur Data', 'Rosa A.S', 'Modula', 2018, 'Teknologi', 2),
('BK009', 'Pemrograman Database MySQL', 'Dr. Eng. R. H. Sianipar, S. T., M. Eng', 'Andi Publisher', 2016, 'Teknologi', 4),
('BK010', 'Laskar Pelangi', 'Andrea Hirata', 'Bentang Pustaka', 2005, 'Novel', 5);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pinjam`
--

CREATE TABLE `tb_pinjam` (
  `kode_pinjam` varchar(8) NOT NULL,
  `id_anggota` int(8) NOT NULL,
  `kode_buku` varchar(5) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `jumlah_pinjam` int(1) NOT NULL,
  `ket` enum('Sudah kembali','Belum kembali') NOT NULL,
  `desk` enum('Telat','Tidak telat','Hilang','Perpanjang','-') NOT NULL,
  `denda` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pinjam`
--

INSERT INTO `tb_pinjam` (`kode_pinjam`, `id_anggota`, `kode_buku`, `tgl_pinjam`, `tgl_kembali`, `jumlah_pinjam`, `ket`, `desk`, `denda`) VALUES
('ROYAL001', 20220159, 'BK001', '2022-01-01', '2022-01-08', 1, 'Belum kembali', '-', 0),
('ROYAL002', 19202122, 'BK001', '2021-12-30', '2022-01-06', 1, 'Belum kembali', '-', 0),
('ROYAL003', 21220525, 'BK001', '2021-12-15', '2021-12-22', 1, 'Belum kembali', '-', 0),
('ROYAL004', 21220500, 'BK003', '2021-12-28', '2022-01-04', 1, 'Belum kembali', '-', 0),
('ROYAL005', 21220281, 'Bk004', '2021-12-20', '2022-01-04', 1, 'Sudah kembali', 'Telat', 16000),
('ROYAL006', 21220262, 'Bk004', '2021-12-30', '2022-01-06', 1, 'Belum kembali', '-', 0),
('ROYAL007', 18023954, 'Bk004', '2021-12-28', '2022-01-04', 1, 'Belum kembali', '-', 0),
('ROYAL008', 21220281, 'BK010', '2021-12-15', '2021-12-22', 1, 'Belum kembali', '-', 0),
('ROYAL009', 19202123, 'BK002', '2022-01-03', '2022-01-10', 1, 'Belum kembali', '-', 0);

-- --------------------------------------------------------

--
-- Struktur untuk view `relasiperpus`
--
DROP TABLE IF EXISTS `relasiperpus`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `relasiperpus`  AS  select `tb_pinjam`.`kode_pinjam` AS `kode_pinjam`,`tb_pinjam`.`id_anggota` AS `id_anggota`,`tb_anggota`.`nama` AS `nama`,`tb_anggota`.`kelas` AS `kelas`,`tb_anggota`.`jk` AS `jk`,`tb_anggota`.`telp` AS `telp`,`tb_anggota`.`alamat` AS `alamat`,`tb_anggota`.`status` AS `status`,`tb_pinjam`.`kode_buku` AS `kode_buku`,`tb_buku`.`judul` AS `judul`,`tb_buku`.`pengarang` AS `pengarang`,`tb_buku`.`penerbit` AS `penerbit`,`tb_buku`.`tahun_terbit` AS `tahun_terbit`,`tb_buku`.`kategori` AS `kategori`,`tb_buku`.`stok_buku` AS `stok_buku`,`tb_pinjam`.`tgl_pinjam` AS `tgl_pinjam`,`tb_pinjam`.`tgl_kembali` AS `tgl_kembali`,`tb_pinjam`.`jumlah_pinjam` AS `jumlah_pinjam`,`tb_pinjam`.`ket` AS `ket`,`tb_pinjam`.`desk` AS `desk`,`tb_pinjam`.`denda` AS `denda` from ((`tb_pinjam` join `tb_anggota`) join `tb_buku`) where ((`tb_pinjam`.`id_anggota` = `tb_anggota`.`id_anggota`) and (`tb_pinjam`.`kode_buku` = `tb_buku`.`kode_buku`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  ADD PRIMARY KEY (`id_anggota`);

--
-- Indexes for table `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD PRIMARY KEY (`kode_buku`);

--
-- Indexes for table `tb_pinjam`
--
ALTER TABLE `tb_pinjam`
  ADD PRIMARY KEY (`kode_pinjam`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
