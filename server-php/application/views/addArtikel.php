

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Artikel
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Artikel</a></li>
        <li class="active">Tambah Artikel</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
     
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-7 connectedSortable">

          <!-- quick email widget -->
          <div class="box box-info">
            <div class="box-header">
              <i class="fa fa-envelope"></i>

              <h3 class="box-title">Tambah Artikel Baru</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
              </div>
              <!-- /. tools -->
            </div>
            
            
            <div class="box-body">
              <form action="<?php echo base_url('api/funder/Artikel/add');?>" enctype="multipart/form-data" method="post">
                
                <div class="form-group" >
                    <input type="text" class="form-control" placeholder="Judul Artikel" name="judul" aria-label="Alamat" >
                  
                </div>
                <div class="form-group" >
                    <input type="text" class="form-control" placeholder="Nama Penulis" name="penulis" aria-label="Alamat" >
                  
                </div>
                <div class="form-group">
                  <textarea class="textarea" placeholder="Place some text here"
                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>
                 <div class="form-group" >
                    <input type="file" class="form-control"  name="image" aria-label="Alamat" >
                  
                </div>
                <div class="box-footer clearfix">
                  <input type="submit" value="Submit" class="pull-right btn btn-default">
                </div>
              </form>
            </div>
            
          </div>
          
        </section>
        <!-- /.Left col -->
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-5 connectedSortable">


          <!-- solid sales graph -->
          <div class="box box-solid bg-teal-gradient">
            <div class="box-header">
              <i class="fa fa-th"></i>

              <h3 class="box-title">Choose Map</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>
            <div class="box-body border-radius-none">
              <!-- <div class="chart" id="line-chart" style="height: 250px;"></div> -->
              <div id="somecomponent" style=" height: 250px;"></div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer no-border">
              <div class="row">
                

                <!-- ./col -->
              </div>
              <!-- /.row -->
            </div>
            <!-- /.box-footer -->
          </div>
          <!-- /.box -->
        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  