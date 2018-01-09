<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class Funders extends Eloquent
{
    protected $table = 'tb_funders';
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

	public function set_id_artikel($value='')
	{
		# code...
	}

    public function create_account($value)
    {
    	$funder = new Funders;
    	$funder->id_funders = $value['id_funders'];
    	$funder->nama = $value['nama'];
    	$funder->email = $value['email'];
    	$funder->password = md5($value['password']);
    	$funder->created_date = date('Y-d-m');
    	$result = $funder->Save();
    	return $result;
    }
    public function update_account($value='')
    {
    	# code...
    }
    public function login($email,$password)
    {
    	return Funders::where('email',$email)->where('password',$password)->get();
    }
}