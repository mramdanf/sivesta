<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Petani_class extends Eloquent
{
    protected $table = 'tb_petani';
    protected $primaryKey = 'id_petani';
    public $timestamps = false;


    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}
    public function create_petani($value)
    {
    	$petani = new Petani_class;
    	$petani->id_funders = $value['id_funders'];
    	$petani->nama = $value['nama'];
    	$petani->email = $value['email'];
    	$petani->password = md5($value['password']);
    	$petani->created_date = date('Y-d-m');
    	$result = $petani->Save();
    	return $result;
    }
    public function update_petani($id,$value)
    {
    	$petani = Petani_class::where('id_kontrak',$id)->first();
        $petani->status_kontrak = $value['status_kontrak'];
        $petani->save();
    }
    public function hapus_petani($id)
    {
        Petani_class::where('id_petani',$id)->delete();
    }
}