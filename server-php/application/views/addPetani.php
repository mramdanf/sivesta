

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Komoditas
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Petani</a></li>
        <li class="active">Tambah Petani</li>
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

              <h3 class="box-title">Tambah Petani Baru</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
              </div>
              <!-- /. tools -->
            </div>
             <script>
          $(document).ready(function(){
              $("#single").hide();
              $("#double").hide();
              $("#double-pj").hide();
                  $("#double-jml").hide();
              $("#r-single").click(function(){
                  // alert("The paragraph was clicked.");
                  $("#double").hide();
                  $("#double-pj").hide();
                  $("#double-jml").hide();
                  $("#single").show();
              });
              $("#r-double").click(function(){
                  $("#single").hide();
                  $("#double").show();
                   $("#double-pj").show();
                  $("#double-jml").show();
              });
          });
          </script>
          
            <div class="box-body">
              <form action="<?php echo base_url();?>admin/Petani/insert" method="post">
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="r-double" value="Kelompok">
                    Petani Kelompok
                  </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="optionsRadios" id="r-single" value="Petani">
                    Petani Perorangan
                  </label>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="nama" id="single" placeholder="Nama">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="nama_kelompok" id="double" placeholder="Nama Kelompok">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="username" placeholder="Username Petani">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control" name="password"  placeholder="Password Petani">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="nama_pj" id="double-pj" placeholder="Nama Penanggung Jawab">
                </div>
                <div class="form-group">
                  <input type="number" class="form-control" name="jumlah" id="double-jml" placeholder="Jumlah Anggota">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="kontak" placeholder="kontak">
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1"><a href="">Geolocation</a></span>
                    <!-- <button class="input-group-addon" id="basic-addon1"></button> -->
                    <input type="text" class="form-control" placeholder="Alamat" name="alamat" aria-label="Alamat" aria-describedby="basic-addon1">
                  </div>
                </div>
                <div class="box-footer clearfix">
                  <!-- <button type="button" class="pull-right btn btn-default" id="sendEmail">Send
                    <i class="fa fa-arrow-circle-right "></i></button> -->
                    <input type="submit"  class="pull-right btn btn-default" value="Simpan">
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

              <h3 class="box-title">Sales Graph</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>
            <div class="box-body border-radius-none">
              <div class="chart" id="line-chart" style="height: 250px;"></div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer no-border">
              <div class="row">
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="20" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">Mail-Orders</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center" style="border-right: 1px solid #f4f4f4">
                  <input type="text" class="knob" data-readonly="true" value="50" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">Online</div>
                </div>
                <!-- ./col -->
                <div class="col-xs-4 text-center">
                  <input type="text" class="knob" data-readonly="true" value="30" data-width="60" data-height="60"
                         data-fgColor="#39CCCC">

                  <div class="knob-label">In-Store</div>
                </div>
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
  