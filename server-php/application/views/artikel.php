
         <!-- Content Wrapper. Contains page content -->
         <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
               <h1>
                  Artikel <small><a href="<?php echo base_url("api/funder/artikel/tambah");?>">Tambah</a></small>
               </h1>
               <ol class="breadcrumb">
                  <li><a href="<?php echo base_url();?>#"><i class="fa fa-dashboard"></i> Dashboard</a></li>
                  <li class="active">Artikel</li>
               </ol>
            </section>
            <!-- Main content -->
            <section class="content">
               <div class="row">
                  <div class="col-lg-12">
                     <?php foreach($artikel as $key){
                        ?>
                        <div class="col-lg-3 col-md-3 col-xs-6">
                           <a href="#" class="d-block mb-4 h-100">
                           <img class="img-thumbnail" style="height: 160px;" src="<?php echo $key['img_url']?>" alt="">
                           </a>
                           <p style="text-align: center;"><?php echo $key['judul']; ?></p>
                        </div>
                        <?php
                     }?>
                  </div>
               </div>
            </section>
            <!-- /.content -->
         </div>
        