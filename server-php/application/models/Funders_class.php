<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Funders_class extends Eloquent
{
    protected $table = 'tb_funders';
    protected $primaryKey = 'id_funders';
    public $timestamps = false;


    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}
    public function create_account($value)
    {
    	$funder = new Funders_class;
    	$funder->id_funders = $value['id_funders'];
    	$funder->nama = $value['nama'];
    	$funder->email = $value['email'];
    	$funder->password = md5($value['password']);
    	$funder->created_date = date('Y-d-m');
    	$result = $funder->Save();
    	return $result;
    }
    public function update_account($id,$value)
    {
    	$funder = Funders_class::where('id_kontrak',$id)->first();
        $funder->nama = $value['nama'];
        $funder->email = $value['email'];
        $funder->password = md5($value['password']);
        $funder->created_date = date('Y-d-m');
        $result = $funder->Save();
        return $result;
    }
    public function login($email,$password)
    {
    	return Funders_class::where('email',$email)->where('password',$password)->get();
    }
}