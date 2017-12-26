

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Komoditas
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Komoditas</a></li>
        <li class="active">Tambah Komoditas</li>
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

              <h3 class="box-title">Tambah Komoditas Baru</h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
              </div>
              <!-- /. tools -->
            </div>
            
            
            <div class="box-body">
              <form action="<?php echo base_url();?>admin/Komoditas/add" method="post">
                <div class="radio">
                  <label>
                    <input type="radio" name="pilihan" id="optionsRadios1" value="perenial" required="true">
                    Komoditas Perenial
                  </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="pilihan" id="optionsRadios2" value="tahunan" required="true">
                    Komoditas Tahunan
                  </label>
                </div>
                <div class="form-group">
                  <input type="text" class="form-control" name="nama_komoditas" placeholder="Nama Komoditas">
                </div>
                <div class="form-group">
                  <select name="id_petani" class="form-control">
                    <?php 
                    foreach ($petani as $key) {
                      ?><option value="<?php echo $key->id_petani;?>"><?php echo $key->name;?></option><?php
                    }
                    ?>
                  </select>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1"><a href="">Rp.</a></span>
                    <input class="form-control" onkeyup="getharga()" id="test1" placeholder="Harga Satuan" aria-describedby="basic-addon1">
                  </div>
                  <input type="hidden" name="harga"  id="harga">
                </div>
                <div class="form-group">
                  <input type="number" class="form-control" name="stock"  placeholder="Jumlah Stock">
                </div>
                <div class="form-group">
                  <input type="number" class="form-control" name="min_kontrak" placeholder="Minimum Kontrak">
                </div>
                <div class="form-group">
                  <input type="number" class="form-control" name="persentase" placeholder="Persentase Keuntungan
                  ">
                </div>
                <div class="form-group" id="i_parenial_jumlah">
                  <input type="number" class="form-control" name="jumlah" placeholder="Jumlah Pohon">
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1"><a href="">Geolocation</a></span>
                    <!-- <button class="input-group-addon" id="basic-addon1"></button> -->
                    <input type="text" class="form-control" placeholder="Alamat" name="alamat" aria-label="Alamat" aria-describedby="basic-addon1">
                    <input type="hidden" id="longitude" class="form-control" name="longitude" >
                    <input type="hidden" id="latitude" class="form-control" name="latitude" >
                  </div>
                </div>
                <div class="form-group" id="i_tahunan_panjang">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Panjang" name="panjang" aria-label="Alamat" aria-describedby="basic-addon1"><span class="input-group-addon" id="basic-addon1"><a href="">Meter</a></span>
                  </div>
                </div>
                <div class="form-group" id="i_tahunan_lebar">
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Lebar" name="lebar" aria-label="Alamat" aria-describedby="basic-addon1">
                    <span class="input-group-addon" id="basic-addon1"><a href="">Meter</a></span>
                  </div>
                </div>
                <div class="box-footer clearfix">
                  <input type="submit" value="Submit" class="pull-right btn btn-default">
                </div>
              </form>
            </div>
            
          </div>
          <script>
                console.log(navigator.userAgent);
                $("#test1").inputmask({ 'alias': 'decimal', 'groupSeparator': '.', 'autoGroup': true, 'digits': 0, 'digitsOptional': false, 'placeholder': 'HARGA 10.000', rightAlign : false,clearMaskOnLostFocus: !1 });
              </script>
              <script>
              $(document).ready(function(){
                  $("#i_tahunan_panjang").hide();
                  $("#i_tahunan_lebar").hide();
                  $("#i_parenial_jumlah").hide();
                  $("#double-pj").hide();
                      $("#double-jml").hide();
                  $("#optionsRadios1").click(function(){
                      // alert("The paragraph was clicked.");
                      $("#double").hide();
                      $("#double-pj").hide();
                      $("#double-jml").hide();
                      $("#single").show();
                      $("#i_tahunan_panjang").hide();
                      $("#i_parenial_jumlah").show();
                  $("#i_tahunan_lebar").hide();
                  });
                  $("#optionsRadios2").click(function(){
                      $("#single").hide();
                      $("#double").show();
                       $("#double-pj").show();
                      $("#double-jml").show();
                      $("#i_tahunan_panjang").show();
                  $("#i_tahunan_lebar").show();
                  $("#i_parenial_jumlah").hide();
                  });
                  
              });
              </script>
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
                
<script>
  var long = 0;
  var lat = 0;
  navigator.geolocation.getCurrentPosition(showPosition);
  function showPosition(position) {
     
     long = position.coords.latitude;
     lat = position.coords.longitude;
     $('#longitude').val(long)
    $('#latitude').val(lat)

  }
  function getharga() {
                    var string = $("#test1").val();
                    console.log('string : ',string)
                    console.log(string.replace(/,/g , ""));
                    $('#harga').val(string)
                  }
    $('#somecomponent').locationpicker({
      location: {
            latitude: lat,
            longitude: long
        },
        radius: 300,
        inputBinding: {
            latitudeInput: $('#us3-lat'),
            longitudeInput: $('#us3-lon'),
            radiusInput: $('#us3-radius'),
            locationNameInput: $('#us3-address')
        },
        enableAutocomplete: true,
        autocompleteOptions: {
            types: ['(cities)'],
            componentRestrictions: {country: 'fr'}
        },
        onchanged: function (currentLocation, radius, isMarkerDropped) {
            $('#longitude').val(currentLocation.longitude)
            $('#latitude').val(currentLocation.latitude)
            alert("Location changed. New location (" + currentLocation.latitude + ", " + currentLocation.longitude + ")");
        }
        });
</script>
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
  