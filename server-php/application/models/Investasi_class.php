<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Investasi_class extends Eloquent
{
    protected $table = 'tb_kontrak';
    protected $primaryKey = 'id_kontrak';
    public $timestamps = false;

    public function __construct()
	{
		parent::__construct();
        // echo "class initate";
	}

    public function funder()
      {
        return $this->belongsTo('Funders_class','id_funders');
      }
    public function komoditas()
      {
        return $this->belongsTo('Komoditas_class','id_komoditas');
      }
    public function progressKomoditas()
      {
        return $this->hasMany('ProgressKomoditas_class','id_kontrak');
      }

    public function create_kontrak($value)
    {
        $min_kontrak = $value['komoditas']['min_kontrak'];

    	$kontrak = new Investasi_class;
        $kontrak->id_kontrak        = $value['id_kontrak'];
    	$kontrak->id_funders        = $value['funder']['id_funders'];
    	$kontrak->id_komoditas      = $value['komoditas']['id_komoditas'];
    	$kontrak->status_kontrak    = 1;
    	$kontrak->tgl_kadaluarsa    = date('Y-m-d', strtotime(' + '.$min_kontrak.' years'));
        $kontrak->tgl_mulai_kontrak = date('Y-m-d');
        $kontrak->biaya_total       = $value['biaya_total'];
        $kontrak->jumlah_benih      = $value['jumlah_benih'];
        $kontrak->virtual_account   = $value['virtual_account'] = mt_rand();
    	$kontrak->created_date      = date('Y-d-m');
    	$result = $kontrak->Save();
    	return ($result) ? $value : FALSE;
    }
    public function getListKontrak($value='')
    {
    	return Investasi_class::all();
    }

    public function getKontrakId($id)
    {
        return Investasi_class::where('id_kontrak',$id)->get();
    }
    public function updateKontrak($id,$value)
    {
        $kontrak = Investasi_class::where('id_kontrak',$id)->first();
        $kontrak->status_kontrak = $value['status_kontrak'];
        $kontrak->save();
    }

}