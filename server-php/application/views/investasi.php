
         <!-- Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
               <h1>
                  Petani <small><a href="<?php echo base_url("admin/Petani/tambah");?>">Tambah</a></small>
               </h1>
               <ol class="breadcrumb">
                  <li><a href="<?php echo base_url();?>#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                  <li class="active">Petani</li>
               </ol>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-lg-12">
                     <div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>No.</th>
                  <th>Komoditas</th>
                  <th>Funders</th>
                  <th>Total Investasi</th>
                  <th>Waktu Kontrak</th>
                </tr>
                </thead>
                <tbody>
                <?php $i = 1;
                foreach ($investasi as $key) {
                  ?>
                  <tr>
                  <td><?php echo $i;?></td>
                  <td><?php echo $key->nama_petani;?></td>
                  <td><?php echo $key->nama_funders;?></td>
                  <td><?php echo $key->biaya_total;?></td>
                  <td><?php echo $key->jumlah;?></td>
                </tr>
                  <?php $i++;
                }
                ?>
               </tbody>
                <tfoot>
                <tr>
                  <th>No.</th>
                  <th>Nama / Nama Kelompok</th>
                  <th>Kontak</th>
                  <th>Alamat</th>
                  <th>Kategori</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
                  </div>
                  </div>
               </div>
            </section>
            <!-- /.content -->
         </div>
        