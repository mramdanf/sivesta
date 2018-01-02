
         <!-- Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
               <h1>
                  Investasi <small>
               </h1>
               <ol class="breadcrumb">
                  <li><a href="<?php echo base_url();?>#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                  <li class="active">Investasi</li>
               </ol>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-lg-12">
                     <div class="box">
            <div class="box-header">
              <h3 class="box-title">Tabel Informasi Kontrak Investasi</h3>
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
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <?php $i = 1;
                foreach ($investasi as $key) {
                  ?>
                  <tr>
                  <td><?php echo $i;?></td>
                  <td><?php echo $key->nama_komoditas ;?></td>
                  <td><?php echo $key->nama_funder;?></td>
                  <td><?php echo $key->biaya_total;?></td>
                  <td><?php echo date("d M Y",strtotime($key->tgl_mulai_kontrak))." s/d ".date("d M Y",strtotime($key->tgl_kadaluarsa));?></td>
                  <td><?php if($key->status_pembayaran == 'true'){echo "<span class='text-success'>Sudah dibayar</span>";}else{echo "<span class='text-danger'>Belum dibayar</span>";}?></td>
                </tr>
                  <?php $i++;
                }
                ?>
               </tbody>
                <tfoot>
                <tr>
                  <th>No.</th>
                  <th>Komoditas</th>
                  <th>Funders</th>
                  <th>Total Investasi</th>
                  <th>Waktu Kontrak</th>
                  <th>Status</th>
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
        