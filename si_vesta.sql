-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Nov 27, 2017 at 03:47 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
  `password` int(11) NOT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `latitude` float NOT NULL,
  `longitude` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas_perenial`
--

CREATE TABLE `tb_komoditas_perenial` (
  `id_komoditas` varchar(6) NOT NULL,
  `jumlah_phon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `id_kontrak` varchar(6) NOT NULL,
  `id_petani` varchar(6) NOT NULL,
  `id_komoditas` varchar(6) NOT NULL,
  `id_funders` varchar(6) NOT NULL,
  `waktu_mulai` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lama_kontrak` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani`
--

CREATE TABLE `tb_petani` (
  `id_petani` varchar(6) NOT NULL,
  `kontak` varchar(13) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD PRIMARY KEY (`id_kontrak`),
  ADD KEY `petani` (`id_petani`),
  ADD KEY `komoditas` (`id_komoditas`),
  ADD KEY `funders` (`id_funders`);

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
  ADD CONSTRAINT `komoditas` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_komoditas` (`id_komoditas`),
  ADD CONSTRAINT `petani` FOREIGN KEY (`id_petani`) REFERENCES `tb_petani` (`id_petani`);

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
