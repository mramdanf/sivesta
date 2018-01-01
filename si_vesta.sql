-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2017 at 02:03 PM
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
-- Table structure for table `tb_artikel`
--

CREATE TABLE `tb_artikel` (
  `id_artikel` varchar(5) NOT NULL,
  `judul` varchar(200) DEFAULT NULL,
  `konten` text,
  `penulis` varchar(200) DEFAULT NULL,
  `tgl_posting` date DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_artikel`
--

INSERT INTO `tb_artikel` (`id_artikel`, `judul`, `konten`, `penulis`, `tgl_posting`, `image`) VALUES
('A0001', 'Peluang di Circular Economy', '<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\n\n<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\n\n<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>', 'Arif Widiyatmiko', '2017-12-11', 'Circular_Economy.png'),
('A0002', 'Cengkeh Indonesia', '<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\r\n\r\n<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\r\n\r\n<p style="text-align: justify;">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>', 'Arif Widiyatmiko', '2017-12-11', 'kebangkitan_cengkeh.jpg');

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
  `password` varchar(50) NOT NULL,
  `profile_image` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_funders`
--

INSERT INTO `tb_funders` (`id_funders`, `nama`, `alamat`, `telepon`, `email`, `username`, `password`, `profile_image`) VALUES
('F00001', 'murdi', '', '', 'murdi@sivesta.com', NULL, '0d55568f98eae7b013df3fe97de2db29', NULL);

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
  `image` varchar(100) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `min_kontrak` int(11) DEFAULT NULL,
  `profit` float DEFAULT NULL,
  `deskripsi` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas`
--

INSERT INTO `tb_komoditas` (`id_komoditas`, `nama`, `harga`, `stock`, `lokasi`, `image`, `latitude`, `longitude`, `min_kontrak`, `profit`, `deskripsi`) VALUES
('K00001', 'Forage Crops', 1304000, 500, 'Cibadak', 'forage_crops.jpg', 0, 0, 2, 15, '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vel blandit elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam eu auctor magna. Aliquam sollicitudin urna quis diam auctor, ac maximus ex dignissim. Nulla facilisi. Fusce a mauris purus. Aliquam tellus nulla, blandit non eleifend eu, efficitur vel arcu. Etiam ultricies lobortis nisl, vitae vehicula libero volutpat a. Morbi congue sapien ut bibendum pharetra. Quisque id pulvinar lectus, ac maximus elit.</p>\n\n<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque felis nisl, malesuada ac aliquam nec, mattis non ligula. Praesent euismod nec urna sed ullamcorper. Morbi consectetur vulputate sapien, eget facilisis sapien semper id. Nulla vitae commodo lorem, non ornare massa. Donec lorem velit, hendrerit imperdiet ex eu, eleifend vehicula nunc. Nulla consequat sapien ac suscipit tincidunt. Proin sed condimentum ligula. Proin posuere, diam ac tempor ultrices, libero augue elementum sapien, ut cursus ligula libero id libero. Donec faucibus magna in auctor bibendum. Morbi cursus pretium augue ac porttitor. Maecenas dictum vitae magna eget interdum. Pellentesque vulputate dignissim facilisis. Proin orci est, lobortis non nunc sed, viverra fringilla nulla. Quisque bibendum sollicitudin lorem, auctor finibus nisl tincidunt non.</p>\n\n<p>In tempus placerat dui, at tempus ante dignissim vel. Vivamus auctor tellus et nunc interdum scelerisque. Integer eros urna, porttitor id leo id, bibendum euismod ligula. Morbi sollicitudin mi ante, rutrum pharetra tortor fringilla et. Nulla laoreet, nulla quis convallis fermentum, mi eros ultricies turpis, sit amet pharetra ligula tortor ac quam. Proin malesuada eget nulla id ullamcorper. Integer porta vulputate felis, non cursus tortor blandit eget. Aliquam sed imperdiet nisi. Quisque pulvinar erat eu tristique maximus. In non facilisis metus, quis ultricies neque. Proin in mi auctor, dictum nulla at, malesuada lacus. Nunc finibus, turpis eleifend egestas bibendum, tortor nibh pharetra nisl, non egestas eros lacus sit amet augue. Maecenas ultricies viverra ligula tristique malesuada. Nam elementum aliquet iaculis. Phasellus eu arcu auctor, aliquet quam non, fringilla neque.</p>'),
('K00002', 'Muntok White Papper', 1650000, 500, 'Cibadak', 'muntok_white.jpg', 0, 0, 3, 12, '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vel blandit elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam eu auctor magna. Aliquam sollicitudin urna quis diam auctor, ac maximus ex dignissim. Nulla facilisi. Fusce a mauris purus. Aliquam tellus nulla, blandit non eleifend eu, efficitur vel arcu. Etiam ultricies lobortis nisl, vitae vehicula libero volutpat a. Morbi congue sapien ut bibendum pharetra. Quisque id pulvinar lectus, ac maximus elit.</p>\n\n<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque felis nisl, malesuada ac aliquam nec, mattis non ligula. Praesent euismod nec urna sed ullamcorper. Morbi consectetur vulputate sapien, eget facilisis sapien semper id. Nulla vitae commodo lorem, non ornare massa. Donec lorem velit, hendrerit imperdiet ex eu, eleifend vehicula nunc. Nulla consequat sapien ac suscipit tincidunt. Proin sed condimentum ligula. Proin posuere, diam ac tempor ultrices, libero augue elementum sapien, ut cursus ligula libero id libero. Donec faucibus magna in auctor bibendum. Morbi cursus pretium augue ac porttitor. Maecenas dictum vitae magna eget interdum. Pellentesque vulputate dignissim facilisis. Proin orci est, lobortis non nunc sed, viverra fringilla nulla. Quisque bibendum sollicitudin lorem, auctor finibus nisl tincidunt non.</p>\n\n<p>In tempus placerat dui, at tempus ante dignissim vel. Vivamus auctor tellus et nunc interdum scelerisque. Integer eros urna, porttitor id leo id, bibendum euismod ligula. Morbi sollicitudin mi ante, rutrum pharetra tortor fringilla et. Nulla laoreet, nulla quis convallis fermentum, mi eros ultricies turpis, sit amet pharetra ligula tortor ac quam. Proin malesuada eget nulla id ullamcorper. Integer porta vulputate felis, non cursus tortor blandit eget. Aliquam sed imperdiet nisi. Quisque pulvinar erat eu tristique maximus. In non facilisis metus, quis ultricies neque. Proin in mi auctor, dictum nulla at, malesuada lacus. Nunc finibus, turpis eleifend egestas bibendum, tortor nibh pharetra nisl, non egestas eros lacus sit amet augue. Maecenas ultricies viverra ligula tristique malesuada. Nam elementum aliquet iaculis. Phasellus eu arcu auctor, aliquet quam non, fringilla neque.</p>'),
('K00003', 'Sugar Cane', 3913000, 500, 'Cibadak', 'sugar_cane.jpg', 0, 0, 1, 12, '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vel blandit elit. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam eu auctor magna. Aliquam sollicitudin urna quis diam auctor, ac maximus ex dignissim. Nulla facilisi. Fusce a mauris purus. Aliquam tellus nulla, blandit non eleifend eu, efficitur vel arcu. Etiam ultricies lobortis nisl, vitae vehicula libero volutpat a. Morbi congue sapien ut bibendum pharetra. Quisque id pulvinar lectus, ac maximus elit.</p>\n\n<p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque felis nisl, malesuada ac aliquam nec, mattis non ligula. Praesent euismod nec urna sed ullamcorper. Morbi consectetur vulputate sapien, eget facilisis sapien semper id. Nulla vitae commodo lorem, non ornare massa. Donec lorem velit, hendrerit imperdiet ex eu, eleifend vehicula nunc. Nulla consequat sapien ac suscipit tincidunt. Proin sed condimentum ligula. Proin posuere, diam ac tempor ultrices, libero augue elementum sapien, ut cursus ligula libero id libero. Donec faucibus magna in auctor bibendum. Morbi cursus pretium augue ac porttitor. Maecenas dictum vitae magna eget interdum. Pellentesque vulputate dignissim facilisis. Proin orci est, lobortis non nunc sed, viverra fringilla nulla. Quisque bibendum sollicitudin lorem, auctor finibus nisl tincidunt non.</p>\n\n<p>In tempus placerat dui, at tempus ante dignissim vel. Vivamus auctor tellus et nunc interdum scelerisque. Integer eros urna, porttitor id leo id, bibendum euismod ligula. Morbi sollicitudin mi ante, rutrum pharetra tortor fringilla et. Nulla laoreet, nulla quis convallis fermentum, mi eros ultricies turpis, sit amet pharetra ligula tortor ac quam. Proin malesuada eget nulla id ullamcorper. Integer porta vulputate felis, non cursus tortor blandit eget. Aliquam sed imperdiet nisi. Quisque pulvinar erat eu tristique maximus. In non facilisis metus, quis ultricies neque. Proin in mi auctor, dictum nulla at, malesuada lacus. Nunc finibus, turpis eleifend egestas bibendum, tortor nibh pharetra nisl, non egestas eros lacus sit amet augue. Maecenas ultricies viverra ligula tristique malesuada. Nam elementum aliquet iaculis. Phasellus eu arcu auctor, aliquet quam non, fringilla neque.</p>');

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
('K00002', 100),
('K00003', 100);

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
('P001', 'K00002'),
('P001', 'K00003');

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
-- Indexes for table `tb_artikel`
--
ALTER TABLE `tb_artikel`
  ADD PRIMARY KEY (`id_artikel`);

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
