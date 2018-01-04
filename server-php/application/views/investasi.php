
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
                  <th>Opsi</th>
                </tr>
                </thead>
                <tbody>
                <?php $i = 1;
                foreach ($investasi as $key) {
                  if ($key->status_kontrak == 1) {
                    $status = 'Payment Pending';
                  }else if (condition) {
                    $status = 'Assigning Surveyor';
                  }else if (condition) {
                    $status = 'In Progress';
                  }else{
                    $status = 'Harvested';
                  }
                  ?>
                  <tr>
                  <td><?php echo $i;?></td>
                  <td><?php echo $key->nama_komoditas ;?></td>
                  <td><?php echo $key->nama_funder;?></td>
                  <td><?php echo $key->biaya_total;?></td>
                  <td><?php echo date("d M Y",strtotime($key->tgl_mulai_kontrak))." s/d ".date("d M Y",strtotime($key->tgl_kadaluarsa));?></td>
                  <td><?php echo $status; ?></td>
                  <!-- <td><?php if($key->status_pembayaran == 'true'){echo "<span class='text-success'>Sudah dibayar</span>";}else{echo "<span class='text-danger'>Belum dibayar</span>";}?></td> -->
                  <td>
                    <a  href="<?php echo base_url('admin/Investasi/progress/'.$key->id_kontrak);?>" class="btn btn-sm btn-danger">Update Progress</a>
                  </td>
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
                  <th>Opsi</th>
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
         <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    Apakah anda yakin untuk menghapus salah Petani ?
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-danger btn-ok" id="btn-delete">Delete</a>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
      $('#confirm-delete').on('show.bs.modal', function(e) {
        console.log($(e.relatedTarget).data('href'));
          $(this).find('#btn-delete').attr('href', $(e.relatedTarget).data('href'));
      });
    </script>
        