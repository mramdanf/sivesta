
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
                        </div>
                        <?php
                     }?>
                  </div>
               </div>
            </section>
            <!-- /.content -->
         </div>
        