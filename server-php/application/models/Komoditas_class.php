<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Komoditas_class extends Eloquent
{
    protected $table = 'tb_komoditas';
    protected $primaryKey = 'id_komoditas';
    public $timestamps = false;

   	// protected $id_artikel = 'AS01';
   	// protected $judul;
   	// protected $konten;
   	// protected $image;
   	// protected $tgl_posting; 

    public function funder()
      {
        return $this->belongsTo('Petani_class','id_petani');
      }

    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}  
    public function getAllKomoditas()
    {
        // $komoditas = new Komoditas_class;
        return Komoditas_class::all();
    }
}