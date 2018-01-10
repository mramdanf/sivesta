<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class ProgressKomoditas_class extends Eloquent
{
    protected $table = 'tb_progres_investasi';
    public $timestamps = false;

   	// protected $id_artikel = 'AS01';
   	// protected $judul;
   	// protected $konten;
   	// protected $image;
   	// protected $tgl_posting; 

    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}  
  public function update_progress($value)
  {
      $progress = new ProgressKomoditas_class;
      $progress->id_kontrak = $value['id_kontrak'];
      $progress->image = $value['image'];
      $progress->keterangan = $value['keterangan'];
      $progress->created_date = date('Y-m-d');
      return $progress->save();
  }
}