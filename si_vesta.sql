-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2018 at 07:54 AM
-- Server version: 5.5.39
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `si_vesta`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_artikel`
--

CREATE TABLE IF NOT EXISTS `tb_artikel` (
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
('A0001', 'Kebangkitan Cengkeh', '<p>Dahulu bangsa barat menempuh perjalanan yang sangat jauh ke Nusantara dan kemudian menjajahnya karena melihat sesuatu yang sangat berharga di negeri ini, yaitu antara lain cengkeh dan lada. Ironinya ketika berabad-abad kemudian negeri ini merdeka, komoditi yang sangat berharga ini malah tidak menjadi andalan kita. Negara-negara Eropa kini memvisikan Bioeconomy 2030, sumber daya hayati akan kembali menjadi buruan mereka. Agar kita tidak kembali ‘terjajah’ secara ekonomi, maka sumber daya hayati itu harus kita sendiri yang menguasainya. Kita harus memimpin kebangkitan sumberdaya hayati ini, dan salah satu titik awalnya juga rempah-rempah, yaitu cengkeh !</p><p>&nbsp;</p><p>Maka setelah project iGrow Salt yang alhamdulillah berhasil mengembalikan pekerjaan bagi ratusan petani garam di ladang-ladang garam iGrow di Indramayu, dalam waktu dekat insyaAllah kami akan meluncurkan program baru yang kami sebut Clove Revival atau Kebangkitan Cengkeh.</p><p>Beberapa dasawarsa terakhir nasib petani cengkeh – sama dengan petani pada umumnya – penuh ketidak pastian apalagi ketika suatu rezim pernah secara berlebihan mengatur tata niaga cengkeh ini. Petani yang frustasi bahkan banyak yang menebang pohon-pohon kesayangannya yang sudah berusia puluhan tahun.</p><p>&nbsp;</p><p>Kini tiga dasawarsa berlalu, nasib petani cengkeh juga nyaris tidak ada yang peduli. Apalagi komoditi yang satu ini sering diasosiasikan terlalu dekat dengan produk yang banyak dibenci orang – yaitu rokok. Banyak perdebatan yang tidak kunjung usai di tingkat legislative, eksekutif dan juga dari kajian fikihnya – yang semuanya tentu berdampak pada kehidupan para petani cengkeh.</p><p>&nbsp;</p><p>Memang harus diakui, produk utama dari tanaman cengkeh – berupa bunga dan tangkai bunga cengkeh pasar terbesarnya adalah pabrik-pabrik rokok. Namun yang banyak tidak diketahui orang adalah sesungguhnya cengkeh tidak harus dipanen bunganya, daun pohon cengkeh yang jatuh-pun bisa diolah menjadi minyak daun cengkeh atau yang disebut Clove Leaf Oil (CLO).</p><p>&nbsp;</p><p>CLO ini mengandung 68%-78 % senyawa yang disebut Eugenol, sebuah senyawa super yang nyaris bisa jadi bahan obat apa saja. Dia bersifat anti oksidan, anti bakteri, anti virus, anti jamur, anti inflamasi dan bahkan temuan terakhir juga menunjukkan sifat anti cancer ! bayangkan, penyakit apa yang tidak bisa diobati melalui ikhtiar menggunakan senyawa yang berkarakter seperti ini ?</p><p>&nbsp;</p><p>Kemudian Eugenol sendiri juga mengandung sejumlah functional group, antara lain seperti allyl (-CH2-CH=CH2), phenol (-OH) dan methoxy (-OCH3). Keberadaan group-group inilah yang membuat Eugenol menjadi bahan dasar untuk menghasilkan berbagai produk-produk turunan yang bernilai lebih tinggi seperti isoeugenol, eugenolacetate, isoeugenolacetate, benzyl eugenol, benzylisoeugenol, methyleugenol, eugenolmethyl-ether, eugenolethylether, isoeugenolmethylether, vanillin dlsb.</p><p>&nbsp;</p><p>Dari Eugenol dan produk-produk turunan &nbsp;yang sangat beragam tersebut pula Eugenol adalah bahan baku di sejumlah besar industri mulai dari industri farmasi, kosmetik, makanan, minuman, parfum, pertanian, perikanan dan berbagai industri lainnya.</p><p>&nbsp;</p><p>Di industri makanan misalnya, flavor yang paling universal dan paling banyak dipakai di dunia adalah vanilla. Tetapi produksi vanilla dunia sangat sedikit dan jauh dari memadai, lantas dari mana rasa vanilla bisa dihasilkan ? Salah satunya ya menggunakan produk turunan Eugenol yang disebut vanillin di atas.</p><p>&nbsp;</p><p>Potensi besar inilah yang mendorong iGrow untuk menjadi katalisator bangkitnya kembali industri cengkeh yang sempat melayu dalam beberapa dasawarsa terakhir – antara lain karena efek traumatis panjang dari kebijakan yang menohok tumbuh kembangnya industri pertanian berbasis cengkeh ini sejak tahun 1990-an.</p><p>&nbsp;</p><p>Program kebangkitan cengkeh ini intinya berupa pembiayaan revitalisasi cengkeh secara terukur, yaitu mendorong produksi minyak daun cengkeh atau CLO pada target yang disepakati dengan petani. Sengaja yang kami dorong adalah produksi CLO, karena melalui CLO inilah petani cengkeh memiliki opsi – sebuah peluang pasar yang berada di luar pasar tradisional komoditi cengkeh mereka sebelumnya yang tergantung pada industri rokok.</p><p>&nbsp;</p><p>Langkah ini akan memberikan alternatif sumber kemakmuran bagi petani cengkeh yang selama ini mengandalkan industri rokok untuk membeli bunga dan tangkai bunga cengkeh mereka. Melalui program iGrow ini – mereka tidak akan lagi tergantung pada industri rokok, bahkan mereka bisa memilih untuk focus memproduksi daun cengkeh untuk diambil minyaknya, tanpa harus menunggu pohon cengkehnya sendiri berbunga.</p><p>&nbsp;</p><p>Solusi ini akan meng-encourage petani untuk kembali merawat dan bahkan menanam kembali komoditi unggulan nasional yang tiada duanya ini. Dengan berbagai karakter Eugenol tersebut diatas ditambah diversifikasi produk-produk turunannya yang begitu luas, tinggal masalah kemauan dan waktu sebelum cengkeh menjadi pendorong lahirnya industri dasar baru – hulu yang menghadirkan hilir berbagai jenis industri turunan lainnya.</p><p>&nbsp;</p><p>Lebih dari itu, bila selama ini kita apatis terhadap nasib petani yang dihimpit berbagai persoalan – karena selama ini kita berpikir tidak akan banyak yang bisa kita lakukan untuk merubahnya – kini kita bisa ikut membuat perubahan itu. Meskipun belum akan merubah nasib 40-an juta petani negeri ini, tetapi satu atau dua petani akan dapat Anda tolong untuk melanjutkan kehidupannya – dengan Anda menjadi sponsor bagi 1-2 unit <a target="_blank" rel="nofollow" href="http://bit.ly/2fVm0uh">program Clove Revival dari iGrow</a>.  Yes, it is a change we can make ! InsyaAllah.</p><br><p></p>', 'Raply', '2018-01-04', '1.jpeg'),
('A0002', 'SALTBIC : Salt Basic Industries', '<p>Bila Saudi Arabia memiliki SABIC (Saudia Arabia Basic Industries Corporation) yang memproduksi segala macam chemicals, polymers dan fertilizers untuk kebutuhan pasar export, itu karena mereka punya bahan bakunya yang melimpah – yaitu hasil samping ekstraksi minyak. Kita tidak memiliki minyak sebanyak mereka, tetapi kita memiliki sumber bahan baku &nbsp;yang tidak kalah menariknya – yaitu garam laut. Garam ini sesungguhnya juga bisa menjadi bahan baku untuk industri dasar yang tiada habis-habisnya.&nbsp;</p><p>Hanya sekitar 6 % garam dunia yang dikonsumsi manusia untuk penyedap makanannya, selebihnya yang paling besar adalah untuk industri. Ada sekitar 14,000 – 15,000 jenis produk yang ada di sekitar kita yang dibuat dari garam atau proses produksinya memerlukan garam.</p><p></p><p>Semua produk berbasis kertas, plastic, PVC, metal, kaca dan kulit – proses atau sebagian bahannya memerlukan garam. Garam juga digunakan untuk industri pupuk, kesehatan, pertanian, industri minyak, sanitasi dan bahkan juga salah satu kandidat penghasil <b><strong><i><em><a target="_blank" rel="nofollow" href="http://geraidinar.com/using-joomla/extensions/components/content-component/article-categories/81-gd-articles/entrepreneurship/1920-ketika-tangan-musa-bercahaya">energy piezoelectric</a></em></i></strong></b>&nbsp;masa depan seperti Sodium Potassium Niobate dll.</p><p>Pertanyaannya adalah dengan potensi yang sangat besar dan bahan yang melimpah di negeri ini, mengapa kita justru mengimpor garam ? Banyak sekali alasannya. Diantaranya yang sering dikambing-hitamkan adalah kwalitas dan efisiensi pengolahan garam kita yang mayoritas masih dilakukan dengan cara yang sangat tradisional, sehingga industri – yang merupakan konsumen garam terbesar – memilih garam impor.</p><p>Tingkat produksi petani garam yang rendah dan harga jualnya-pun rendah, membuat nasib petani garam mirip dengan petani-petani lainnya di Indonesia yang sulit beranjak dari kemiskinan. Di sisi lain garam impor juga murah karena beberapa negeri memiliki tambang garam yang tinggal mengambilnya.</p><p>Namun semurah-murahnya garam impor, mestinya kita tetap bisa bersaing setidaknya untuk konsumsi industri dalam negeri – karena biaya pengiriman garam itu mahal. Maka fokusnya tinggal bagaimana kita bisa mendongkrak produktifitas dan kwalitas garam petani.</p><p>Untuk peningkatan produktifitas dan kwalitas ini, Alhamdulillah sudah dapat kami lakukan. Teknologi yang dimiliki oleh mitra-mitra <b><strong><i><em><a target="_blank" rel="nofollow" href="https://www.igrow.asia/home/detail_type_seed/23">iGrow Salt</a></em></i></strong></b>&nbsp;– mampu meningkatkan produktifitas garam sampai sekitar 7 kali dibandingkan dengan cara tradisional dan dengan kwalitas garam yang terbaik.</p><p>Tetapi kita tidak bisa berhenti di sini, produktifitas dan kwalitas barang terbaik sekalipun masih bisa dikalahkan oleh kepentingan dan politik dagang. Nasib garam ini mirip dengan gula, daging, beras, jagung, kedelai &nbsp;dan komoditi-komoditi lainnya di negeri ini. Kita bukannya tidak bisa swasembada, tetapi ada kepentingan dan kekuatan lain yang sangat besar yang sudah berlangsung lebih dari setengah abad ini – yang membuat kita tidak didorong untuk swasembada.</p><p>Maka memperbakinya harus menyeluruh dan bersama-sama dengan seluruh kekuatan yang ada di masyarakat. Karena modal bukan kekuatan kita – tetapi jama’ahlah kekuatan kita, dari sanalah kita bisa memulainya.</p><p>Bila 10 ha lahan garam yang kita mulai didanai ratusan orang dan menjadi ringan karenanya, mengapa tidak dengan pembangunan industri dasarnya ? Selain garamnya sendiri, garam dengan mudah bisa diolah menjadi bahan dasar seperti Sodium Bicarbonate (baking soda – NaHCO3), Sodium Hydroxide (caustic soda – NaOH) , KCl dlsb. yang selanjutnya menjadi bahan baku untuk berbagai jenis industri tersebut di atas.</p><p>Bayangkan dampak dari proses industrialisasi garam ini bila dilakukan secara massive, lapangan kerja akan terbuka sangat luas karena bahan bakunya yang melimpah di negeri ini. Lebih dari itu industri garam akan menjadi Natural Hedge yang secara harfiah akan berfungsi saling mengimbangi industri pertanian kita. Bila hujan baik industri pertanian yang berjaya, bila kemarau giliran industri garam yang ganti berjaya &nbsp;– InsyaAllah &nbsp;Indonesia tidak akan paceklik lagi !</p><p>Barangkali ini juga salah satu hikmah, mengapa garam menjadi satu dari enam komoditi penting yang disebut dalam hadits Nabi yang sahih &nbsp;berikut : <i><em>“(Jual beli) emas dengan emas, perak dengan perak, gandum dengan gandum, sya’ir dengan sya’ir, kurma dengan kurma dan garam dengan garam, harus sama berat/ukuran dan harus dari tangan ke tangan (tunai). Juka jenisnya tidak sama, maka jual-beli-lah sesuka kalian asalkan dari tangan ke tangan (tunai)</em></i>”. (HR. Bukhari)</p><p>Bila sesuatu disebut berurutan di dalam Al-Qur’an atau Hadits yang sahih, maka ada tingkat kepentingan yang relative sama satu sama lain atau yang disebut lebih dahulu yang dipentingkan. Artinya garam ini nyaris sepenting emas dan perak yang merepresentasikan uang, bahkan garam bener-bener bisa digunakan sebagai alat tukar jual beli sebagaimana hadits tersebut di atas.</p><p>Lantas apa yang bisa kita lakukan berikutnya ? kami ingin mengembangkan industri dasar berbasis garam ini tidak dengan pendekatan konglomerasi kapitalisme sebagaimana yang umum dilakukan orang di industri-industri besar selama ini. Ini era disruptive innovation startup, dimana pekerjaan dari rumah atau garasi rumahan-pun bisa mengambil alih peran raksasa-raksasa industri.</p><p>Era disruptive innovation ini mirip dengan era <b><strong><i><em><a target="_blank" rel="nofollow" href="http://geraidinar.com/using-joomla/extensions/components/content-component/article-categories/81-gd-articles/entrepreneurship/1837-naqabah">naqabah</a></em></i></strong></b>&nbsp;– ketika umat Islam berhasil membangun industri percetakan raksasa jauh sebelum mesin cetak ditemukan. Maka seperti <b><strong><i><em><a target="_blank" rel="nofollow" href="http://geraidinar.com/using-joomla/extensions/components/content-component/article-categories/81-gd-articles/entrepreneurship/1837-naqabah">naqabah waraqiin</a>&nbsp;</em></i></strong></b>tersebutlah industri dasar berbasis garam ini bisa kita kembangkan.</p><p>Untuk mewujudkan ini, awalnya yang kami lakukan di Indonesia Startup Center hanya tiga yaitu meng-inspirasi, memotivasi dan men-challenge. Setelah ada yang bener-bener ter-inspirasi, termotivasi dan ter-challenge baru kami lanjutkan untuk secara all-out memfasilitasinya untuk merubah mimpi menjadi visi yang secara istiqomah dieksekusi dengan aksi.</p><p>Untuk memulainya, kami ingin membuat event dan untuk ini kami butuh event organizer yang tidak biasa. EO yang bisa mengubah lahan-lahan garam kami menjadi lokasi edu-tourism yang menarik berbagai kalangan yang mau meng-eksplorasi peluang di garam ini.</p><p>Bagi yang fit untuk pekerjaan semacam ini, atau yang sudah langsung bisa melihat peluang di Salt Basic Industries ini – saya sebut SALTBIC – agar kelak bisa lebih besar dari SABIC-nya Arab Saudi! &nbsp;silahkan menghubungi kami di : <a target="_blank" rel="nofollow">ceo@iou.id</a>&nbsp;, dibaca I Owe You Indonesia karena kita memang serius ingin membangun Indonesia yang kemerdekaannya dahulu dibayar oleh darah dan air mata para pahlawan dan suhada kita.</p><p></p><p></p>', 'Arif Widiyatmiko', '2018-01-04', '2.png');

-- --------------------------------------------------------

--
-- Table structure for table `tb_funders`
--

CREATE TABLE IF NOT EXISTS `tb_funders` (
  `id_funders` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `alamat` text NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `email` varchar(35) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `profile_image` varchar(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_funders`
--

INSERT INTO `tb_funders` (`id_funders`, `nama`, `alamat`, `telepon`, `email`, `username`, `password`, `profile_image`, `created_date`) VALUES
('F002', 'murdi', 'bogor', '081222333444', 'murdi@sivesta.com', 'test', '0d55568f98eae7b013df3fe97de2db29', NULL, '2017-12-05'),
('F003', 'messi dona', 'Jakarta', '081222333444', 'messi@sivesta.com', NULL, '1463ccd2104eeb36769180b8a0c86bb6', NULL, '2018-01-08'),
('F004', 'sudir', '', '', 'sudir@sivesta.com', NULL, 'f53c079488957b8f501693d640f57011', NULL, '2018-01-08'),
('F005', 'ramdan', '', '', 'ramdan@sivesta.com', NULL, '889752dcb81b4ad98ad6e36e9db2cd43', NULL, '2018-01-08'),
('F006', 'Arif Widiyatmiko', '', '', 'arif@sivesta.com', NULL, '0ff6c3ace16359e41e37d40b8301d67f', NULL, '2018-01-08');

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas`
--

CREATE TABLE IF NOT EXISTS `tb_komoditas` (
  `id_komoditas` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `harga` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `lokasi` text NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `min_kontrak` int(11) DEFAULT NULL,
  `profit` float DEFAULT NULL,
  `deskripsi` text,
  `id_petani` varchar(6) NOT NULL,
  `created_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas`
--

INSERT INTO `tb_komoditas` (`id_komoditas`, `nama`, `harga`, `stock`, `lokasi`, `image`, `min_kontrak`, `profit`, `deskripsi`, `id_petani`, `created_date`) VALUES
('K00001', 'Forage Crops', 1304000, 632, 'Bogor', 'forage_crops.jpg', 3, 12, '<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In pretium a lectus in tempus. Mauris sit amet elit imperdiet, hendrerit tellus et, pellentesque justo. Praesent placerat rutrum nisl, in eleifend lectus laoreet in. Donec maximus bibendum eros, nec congue eros ornare sit amet. Vivamus placerat vestibulum ante, eu mollis ipsum rhoncus in. Cras ornare erat id justo rutrum laoreet. Vivamus vitae ex mollis, fermentum quam id, volutpat turpis. Phasellus vehicula, turpis eu interdum blandit, ligula magna accumsan magna, id sagittis magna leo vitae urna. Nam quis enim ante. Curabitur in dolor euismod, ultrices leo non, cursus felis.</p>\r\n\r\n<p>Mauris tincidunt odio eleifend, pretium eros posuere, ullamcorper est. Aliquam ut ante aliquet, vulputate nunc interdum, egestas sapien. Fusce enim tellus, ullamcorper sit amet sapien vel, mattis pharetra tortor. Aliquam scelerisque dictum ultrices. Fusce egestas mi et nunc auctor blandit. Morbi bibendum ut enim id finibus. Cras vitae laoreet felis. Quisque bibendum sapien eu imperdiet ullamcorper. Sed felis justo, volutpat nec commodo sit amet, ornare ut nunc. Vivamus in erat consequat, consequat risus et, accumsan magna. Integer interdum odio ut varius finibus. Donec bibendum quam lorem. Sed commodo ligula in ipsum commodo facilisis.</p>\r\n\r\n<p>Mauris vel felis dolor. Suspendisse iaculis porta iaculis. Phasellus eu ante nec sapien feugiat auctor. Morbi semper convallis dolor, eget condimentum orci cursus ac. Fusce cursus mi molestie diam placerat, ut consectetur ipsum convallis. Aliquam erat volutpat. In varius eros eu augue posuere, quis accumsan libero congue. Aenean vestibulum condimentum ligula, eget lobortis mi dignissim sed. Proin finibus, justo in pharetra facilisis, purus nisi tincidunt lectus, vel molestie turpis est ut nisl. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed a magna efficitur, semper mi sit amet, commodo lorem. Donec eu ornare enim.</p>', 'P001', NULL),
('K00004', 'Durian', 50000, 2000, 'Jalan Pendidikan 3 no 1', '8.png', 18, 50, '<p>\r\n\r\nDurian atau nama latinnya Durio zibethinus adalah buah tropis yang kontroversial, orang bisa sangat suka terhadap durian ini atau sebaliknya. Bagi penggemar durian, mereka akan rela berburu durian sampai tempat-tempat yang jauh.\r\n\r\n<br></p>', 'P002', NULL),
('K00005', 'Breadfruit', 90000, 1000, 'Jl. Siliwangi ', '17.jpeg', 1, 99, '<p><br>Breadfruit or Artocarpus altilis is one potential source of food for a tropical country like Indonesia. The fruit that has been tasted like bread though only grilled or fried, making this fruit called breadfruit in English.<br>&nbsp;<br>Breadfruit can be cooked straight from the fruit, but can also be processed into flour for the next processed food ingredients. The edible content of breadfruit was also relatively high for approximately 78% of the fresh fruit.<br>&nbsp;<br>Aside from being a source of carbohydrate, breadfruit rich in vitamins - such as vitamin C and Vitamin B1 to B6, also rich in minerals such as Calcium, Iron, Potassium, Phosphorus and Magnesium. Therefore it is one of a potential food source, to strengthen our food security.<br>&nbsp;<br><br>Breadfruit when it is intensively planted, it has high economic value, start to bear fruit in the year fourth &nbsp;and will be earning up to year 15th. The estimated yield will range between 10-15% per year.<br></p>', 'P002', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas_perenial`
--

CREATE TABLE IF NOT EXISTS `tb_komoditas_perenial` (
  `id_komoditas` varchar(6) NOT NULL,
  `jumlah_phon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas_perenial`
--

INSERT INTO `tb_komoditas_perenial` (`id_komoditas`, `jumlah_phon`) VALUES
('K00001', 200),
('K00004', 2000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_komoditas_tahunan`
--

CREATE TABLE IF NOT EXISTS `tb_komoditas_tahunan` (
  `id_komoditas` varchar(6) NOT NULL,
  `panjang` int(11) NOT NULL,
  `lebar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_komoditas_tahunan`
--

INSERT INTO `tb_komoditas_tahunan` (`id_komoditas`, `panjang`, `lebar`) VALUES
('K00005', 200, 200),
('K00006', 100, 200);

-- --------------------------------------------------------

--
-- Table structure for table `tb_kontrak`
--

CREATE TABLE IF NOT EXISTS `tb_kontrak` (
  `id_kontrak` varchar(10) NOT NULL,
  `id_komoditas` varchar(6) NOT NULL,
  `id_funders` varchar(6) NOT NULL,
  `status_kontrak` enum('1','2','3','4') DEFAULT NULL,
  `tgl_kadaluarsa` date NOT NULL,
  `tgl_mulai_kontrak` date NOT NULL,
  `biaya_total` int(11) NOT NULL,
  `jumlah_benih` int(11) NOT NULL,
  `virtual_account` varchar(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani`
--

CREATE TABLE IF NOT EXISTS `tb_petani` (
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
('P001', '081333444111', '', 'test', '098f6bcd4621d373cade4e832627b4f6'),
('P002', '0856726354628', 'Jalan Pendidikan 3 no 1', 'laknatusfarm', '202cb962ac59075b964b07152d234b70'),
('P003', '0858726346356', 'Jalan Malabar ujung', 'arman', '202cb962ac59075b964b07152d234b70'),
('P004', '0867283736272', 'jalan cilibende no 3', 'jayatani', '202cb962ac59075b964b07152d234b70'),
('P005', '4738492678', 'Jl. Pajajaan', 'dhani', 'da92f7066e5b27648d685cd6cf84f4bb'),
('P006', '081222444555', 'Ciawi', 'sueb', 'a217642bdd581f404eff4c3af2e93e73');

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani_kelompok`
--

CREATE TABLE IF NOT EXISTS `tb_petani_kelompok` (
  `id_petani` varchar(6) NOT NULL,
  `nama_kelompok` varchar(35) NOT NULL,
  `penanggung_jawab` varchar(35) NOT NULL,
  `jumlah_petani` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_petani_kelompok`
--

INSERT INTO `tb_petani_kelompok` (`id_petani`, `nama_kelompok`, `penanggung_jawab`, `jumlah_petani`) VALUES
('P002', 'laknatusFarm', 'Amin Ramdhany', 7),
('P004', 'jayaTani', 'Mamang', 20);

-- --------------------------------------------------------

--
-- Table structure for table `tb_petani_perorangan`
--

CREATE TABLE IF NOT EXISTS `tb_petani_perorangan` (
  `id_petani` varchar(6) NOT NULL,
  `nama` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_petani_perorangan`
--

INSERT INTO `tb_petani_perorangan` (`id_petani`, `nama`) VALUES
('P001', 'ramdan'),
('P003', 'Arman Shaum'),
('P005', 'Dhany Kun'),
('P006', 'Sueb');

-- --------------------------------------------------------

--
-- Table structure for table `tb_progres_investasi`
--

CREATE TABLE IF NOT EXISTS `tb_progres_investasi` (
  `id_kontrak` varchar(5) NOT NULL,
  `image` text NOT NULL,
  `keterangan` text NOT NULL,
  `created_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
 ADD PRIMARY KEY (`id_komoditas`), ADD KEY `petani` (`id_petani`);

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
 ADD PRIMARY KEY (`id_kontrak`), ADD KEY `funders` (`id_funders`), ADD KEY `komoditas` (`id_komoditas`);

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
-- Indexes for table `tb_progres_investasi`
--
ALTER TABLE `tb_progres_investasi`
 ADD KEY `kontrak` (`id_kontrak`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_komoditas`
--
ALTER TABLE `tb_komoditas`
ADD CONSTRAINT `tb_komoditas_ibfk_1` FOREIGN KEY (`id_petani`) REFERENCES `tb_petani` (`id_petani`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
ADD CONSTRAINT `komoditas` FOREIGN KEY (`id_komoditas`) REFERENCES `tb_komoditas` (`id_komoditas`);

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

--
-- Constraints for table `tb_progres_investasi`
--
ALTER TABLE `tb_progres_investasi`
ADD CONSTRAINT `kontrak` FOREIGN KEY (`id_kontrak`) REFERENCES `tb_kontrak` (`id_kontrak`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
