

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Investasi
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Investasi</a></li>
        <li class="active">Progress Investasi</li>
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
              <h3 class="box-title">Update Progress</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
              </div>
              <!-- /. tools -->
            </div>
            
            
            <div class="box-body">
              <form action="<?php echo base_url('admin/Investasi/updateProgress/'.$investasi['id_kontrak']);?>" enctype="multipart/form-data" method="post">
                <div class="form-group">
                  <select name="status_kontrak" class="form-control" <?php if($investasi['status_kontrak'] == 4){echo "disabled='true'";} ?>>
                    <?php 
                    if ($investasi['status_kontrak'] == 1) {
                      ?>
                      <option value="2">Pembayaran diterima, mencari surveyor</option>
                      <option value="3">Dalam Progress</option>
                      <option value="4">Siap Panen</option>
                      <?php
                    }elseif ($investasi['status_kontrak'] == 2) {
                      ?>
                      <option value="3">Dalam Progress</option>
                      <option value="4">Siap Panen</option>
                      <?php
                    }elseif ($investasi['status_kontrak'] == 3) {
                      ?>
                      <option value="3">Dalam Progress</option>
                      <option value="4">Siap Panen</option>
                      <?php
                    }
                    ?>
                  </select>
                </div>
                <div class="form-group">
                  <textarea class="textarea" name="keterangan" <?php if($investasi['status_kontrak'] == 4){echo "disabled='true'";} ?> placeholder="Place some text here"
                          style="width: 100%; height: 200px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                </div>
                 <div class="form-group" >
                    <input type="file" class="form-control"  name="image" aria-label="Alamat" <?php if($investasi['status_kontrak'] == 4){echo "disabled='true'";} ?>>
                  
                </div>
                <div class="box-footer clearfix">
                  <input type="submit" value="Submit" class="pull-right btn btn-default" <?php if($investasi['status_kontrak'] == 4){echo "disabled='true'";} ?>>
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

              <h3 class="box-title">Detail Investasi</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn bg-teal btn-sm" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn bg-teal btn-sm" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>

            <div class="box-body border-radius-none">
              <table>
                <tr>
                  <th style="width: 160px;">Nama Komoditas</th>
                  <td><?php echo $investasi['nama_komoditas'];?></td>
                </tr>
                <tr>
                  <th>Nama Funders</th>
                  <td><?php echo $investasi['nama_funder'];?></td>
                </tr>
                <tr>
                  <th>Lama Kontrak</th>
                  <td>dhasidhasi</td>
                </tr>
              </table>
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
          <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Recently Progress</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <ul class="products-list product-list-in-box">
                <?php 
                if ($progress->num_rows() == 0 ) {
                  ?>
                  <li class="item">
                    <div class="product-img">
                      <img src="<?php echo base_url('app_assets/img/progress_invest/default.png');?>" alt="Product Image">
                    </div>
                    <div class="product-info">
                      <span class="product-description">
                            Belum ada progress.
                          </span>
                    </div>
                  </li>
                  <?php
                }else{
                  foreach ($progress->result() as $key) {
                    ?>
                    <li class="item">
                      <div class="product-img">
                        <img src="<?php echo base_url('app_assets/img/progress_invest/'.$key->image);?>" alt="Product Image">
                      </div>
                      <div class="product-info">
                        <span class="product-description">
                              <?php echo $key->keterangan;?>
                            </span>
                      </div>
                    </li>
                    <?php
                  }
                }
                ?>
                <!-- /.item -->
              </ul>
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
  