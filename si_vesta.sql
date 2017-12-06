-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2017 at 02:48 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `si_vesta`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_funders`
--

CREATE TABLE `tb_funders` (
  `id_funders` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `email` varchar(35) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_funders`
--

INSERT INTO `tb_funders` (`id_funders`, `nama`, `alamat`, `telepon`, `email`, `username`, `password`) VALUES
('', 'ramdan', 'bogor', '081999111777', 'mramdanf@gmail.com', 'ramdan', '889752dcb81b4ad98ad6e36e9db2cd43');

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas`
--

CREATE TABLE `tb_komoditas` (
  `id_komoditas` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `harga` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `lokasi` text NOT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas`
--

INSERT INTO `tb_komoditas` (`id_komoditas`, `nama`, `harga`, `stock`, `lokasi`, `latitude`, `longitude`) VALUES
('K00001', 'Forage Crops', 1304000, 500, 'Cibadak', 0, 0),
('K00002', 'Muntok White Papper', 1650000, 500, 'Cibadak', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas_perenial`
--

CREATE TABLE `tb_komoditas_perenial` (
  `id_komoditas` varchar(6) NOT NULL,
  `jumlah_phon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas_perenial`
--

INSERT INTO `tb_komoditas_perenial` (`id_komoditas`, `jumlah_phon`) VALUES
('K00001', 100),
('K00002', 100);

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas_tahunan`
--

CREATE TABLE `tb_komoditas_tahunan` (
  `id_komoditas` varchar(6) NOT NULL,
  `panjang` int(11) NOT NULL,
  `lebar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_kontrak`
--

CREATE TABLE `tb_kontrak` (
  `id_petani` varchar(6) NOT NULL,
  `id_komoditas` varchar(6) NOT NULL,
  `id_funders` varchar(6) NOT NULL,
  `waktu_mulai` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status_pembayaran` enum('true','false') NOT NULL,
  `tgl_kadaluarsa` date NOT NULL,
  `tgl_mulai_kontrak` date NOT NULL,
  `biaya_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penanaman`
--

CREATE TABLE `tb_penanaman` (
  `id_petani` varchar(6) DEFAULT NULL,
  `id_komoditas` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_penanaman`
--

INSERT INTO `tb_penanaman` (`id_petani`, `id_komoditas`) VALUES
('P001', 'K00001'),
('P001', 'K00002');

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani`
--

CREATE TABLE `tb_petani` (
  `id_petani` varchar(6) NOT NULL,
  `kontak` varchar(13) NOT NULL,
  `alamat` text NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_petani`
--

INSERT INTO `tb_petani` (`id_petani`, `kontak`, `alamat`, `username`, `password`) VALUES
('P001', '08122233344', 'Ciamis', 'messi', '1463ccd2104eeb36769180b8a0c86bb6');

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani_kelompok`
--

CREATE TABLE `tb_petani_kelompok` (
  `id_petani` varchar(6) NOT NULL,
  `nama_kelompok` varchar(35) NOT NULL,
  `penanggung_jawab` varchar(35) NOT NULL,
  `jumlah_petani` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani_perorangan`
--

CREATE TABLE `tb_petani_perorangan` (
  `id_petani` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_petani_perorangan`
--

INSERT INTO `tb_petani_perorangan` (`id_petani`, `nama`) VALUES
('P001', 'miftah');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_funders`
--
ALTER TABLE `tb_funders`
  ADD PRIMARY KEY (`id_funders`);

--
-- Indexes for table `tb_komoditas`
--
ALTER TABLE `tb_komoditas`
  ADD PRIMARY KEY (`id_komoditas`);

--
-- Indexes for table `tb_komoditas_perenial`
--
ALTER TABLE `tb_komoditas_perenial`
  ADD KEY `perenial` (`id_komoditas`);

--
-- Indexes for table `tb_komoditas_tahunan`
--
ALTER TABLE `tb_komoditas_tahunan`
  ADD KEY `tahunan` (`id_komoditas`);

--
-- Indexes for table `tb_kontrak`
--
ALTER TABLE `tb_kontrak`
  ADD KEY `petani` (`id_petani`),
  ADD KEY `komoditas` (`id_komoditas`),
  ADD KEY `funders` (`id_funders`);

--
-- Indexes for table `tb_penanaman`
--
ALTER TABLE `tb_penanaman`
  ADD KEY `id_petani` (`id_petani`),
  ADD KEY `id_komoditas` (`id_komoditas`);

--
-- Indexes for table `tb_petani`
--
ALTER TABLE `tb_petani`
  ADD PRIMARY KEY (`id_petani`);

--
-- Indexes for table `tb_petani_kelompok`
--
ALTER TABLE `tb_petani_kelompok`
  ADD KEY `kelompok` (`id_petani`);

--
-- Indexes for table `tb_petani_perorangan`
--
ALTER TABLE `tb_petani_perorangan`
  ADD KEY `perorangan` (`id_petani`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_komoditas_perenial`
--
ALTER TABLE `tb_komoditas_perenial`
  ADD CONSTRAINT `perenial` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_komoditas` (`id_komoditas`);

--
-- Constraints for table `tb_komoditas_tahunan`
--
ALTER TABLE `tb_komoditas_tahunan`
  ADD CONSTRAINT `tahunan` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_komoditas` (`id_komoditas`);

--
-- Constraints for table `tb_kontrak`
--
ALTER TABLE `tb_kontrak`
  ADD CONSTRAINT `funders` FOREIGN KEY (`id_funders`) REFERENCES `tb_funders` (`id_funders`),
  ADD CONSTRAINT `tb_kontrak_ibfk_1` FOREIGN KEY (`id_petani`) REFERENCES `tb_penanaman` (`id_petani`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_kontrak_ibfk_2` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_penanaman` (`id_komoditas`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_penanaman`
--
ALTER TABLE `tb_penanaman`
  ADD CONSTRAINT `tb_penanaman_ibfk_1` FOREIGN KEY (`id_petani`) REFERENCES `tb_petani` (`id_petani`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_penanaman_ibfk_2` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_komoditas` (`id_komoditas`);

--
-- Constraints for table `tb_petani_kelompok`
--
ALTER TABLE `tb_petani_kelompok`
  ADD CONSTRAINT `kelompok` FOREIGN KEY (`id_petani`) REFERENCES `tb_petani` (`id_petani`);

--
-- Constraints for table `tb_petani_perorangan`
--
ALTER TABLE `tb_petani_perorangan`
  ADD CONSTRAINT `perorangan` FOREIGN KEY (`id_petani`) REFERENCES `tb_petani` (`id_petani`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
