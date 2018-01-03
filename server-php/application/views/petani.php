
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
               <?php 
                  if ($this->session->flashdata('info')) {
                    echo $this->session->flashdata('info');
                  }
                  ?>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example1" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>No.</th>
                  <th>Nama / Nama Kelompok</th>
                  <th>Kontak</th>
                  <th>Alamat</th>
                  <th>Kategori</th>
                  <th>Opsi</th>
                </tr>
                </thead>
                <tbody>
                <?php $i = 1;
                foreach ($petani as $key) {
                  ?>
                  <tr>
                  <td><?php echo $i;?></td>
                  <td><?php echo $key->name;?></td>
                  <td><?php echo $key->kontak;?></td>
                  <td><?php echo $key->alamat;?></td>
                  <td><?php echo $key->kategori;?></td>
                  <td>
                    <span class="text-center" style="padding-right: 10px;padding-left: 10px;">
                              <a href="" data-href="<?php echo base_url();?>admin/Petani/delete/<?php echo $key->id_petani.'/'.$key->kategori;?>" data-toggle="modal" data-target="#confirm-delete" class="btn btn-sm btn-danger">Delete</a>
                              <a href="" class="btn btn-sm btn-info ">Update</a>
                           </span>
                  </td>
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