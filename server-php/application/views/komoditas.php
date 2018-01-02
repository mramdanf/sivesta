
         <!-- Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
               <h1>
                  Komoditas <small><a href="<?php echo base_url("admin/Komoditas/tambah");?>">Tambah</a></small>
               </h1>
               <ol class="breadcrumb">
                  <li><a href="<?php echo base_url();?>#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                  <li class="active">Komoditas</li>
               </ol>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-lg-12">
                     <?php foreach($komoditas as $key){
                        ?>
                        <div class="col-lg-3 col-md-3 col-xs-6">
                           <a href="#" class="d-block mb-4 h-100">
                           <img class="img-fluid img-thumbnail" src="http://placehold.it/400x300" alt="">
                           </a>
                           <p style="text-align: center;"><?php echo $key->nama; ?></p>
                           <span class="text-center" style="padding-right: 10px;padding-left: 10px;">
                              <a href="" data-href="<?php echo base_url();?>admin/Komoditas/delete/<?php echo $key->id_komoditas."/".$key->kategori;?>" data-toggle="modal" data-target="#confirm-delete" class="btn btn-sm btn-danger pull-left">Delete</a>
                              <a href="" class="btn btn-sm btn-info pull-right">Update</a>
                           </span>
                        </div>
                        <?php
                     }?>
                  </div>
               </div>
            </section>
            <!-- /.content -->
         </div>
         <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    Apakah anda yakin untuk menghapus salah Komoditas ?
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
        