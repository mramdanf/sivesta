# Allow NULL for column id_petani in tb_kontrak
ALTER TABLE `tb_kontrak` CHANGE `id_petani` `id_petani` VARCHAR(6) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;

# Add virtual_account column in tb_kontrak
ALTER TABLE `tb_kontrak` ADD `virtual_account` VARCHAR(100) NULL;

# Rename status_pembayaran to status_kontrak
ALTER TABLE `tb_kontrak` CHANGE `status_pembayaran` `status_kontrak` ENUM('true','false') CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

# Change enum for status_pembayaran
# 1=belum bayar, 2=assign_surveyor
ALTER TABLE `tb_kontrak` CHANGE `status_kontrak` `status_kontrak` ENUM('1','2') CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

# Add enum for status_pembayaran
ALTER TABLE `tb_kontrak` CHANGE `status_kontrak` `status_kontrak` ENUM('1','2','3') CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;
ALTER TABLE `tb_kontrak` CHANGE `status_kontrak` `status_kontrak` ENUM('1','2','3') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL;

# Change lengt id_kontrak
ALTER TABLE `tb_kontrak` CHANGE `id_kontrak` `id_kontrak` VARCHAR(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL;

# add column posted_at in tb_progres_investasi
# nambah kompleksitas krn di android akan ditambahkan
# view detail dari update
ALTER TABLE `tb_progres_investasi` ADD `posted_at` DATE NULL ;

# add column joined_at in tb_funders
# Digunakan untuk menghitung berapa lama funder
# teregister di sistem
ALTER TABLE `tb_funders` ADD `joined_at` DATE NULL ;

# add column created_date in tb_kontrak
# but forget the why reaseon
ALTER TABLE `tb_kontrak` ADD `created_date` DATE NULL ;

# rename colum posted_at in tb_progres_investasi
# reason: ngikutin arif, miskom padahal udah gw bikin ini
ALTER TABLE `tb_progres_investasi` CHANGE `posted_at` `created_date` DATE NULL DEFAULT NULL;

# reason: nyamain sama arif
ALTER TABLE `tb_funders` CHANGE `joined_at` `created_date` DATE NULL DEFAULT NULL;
