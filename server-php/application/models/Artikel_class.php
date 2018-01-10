<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Artikel_class extends Eloquent
{
    protected $table = 'tb_artikel';
    protected $primaryKey = 'id_artikel';
    public $timestamps = false;


    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}  
    public function getData()
    {
        // $komoditas = new Komoditas_class;
        return Artikel_class::all();
    }
    public function insert($value)
    {
      $artikel = new Artikel_class;
      $artikel->id_artikel = $value['id_artikel'];
      $artikel->judul = $value['judul'];
      $artikel->konten = $value['konten'];
      $artikel->penulis = $value['penulis'];
      $artikel->image = $value['image'];
      $artikel->tgl_posting = date('Y-m-d');
      $artikel->save();
    }
    public function deletes($value)
    {
      Artikel_class::where('id_artikel',$value['id_artikel'])->delete();
    }
    public function updates($value='')
    {
      # code...
    }
}