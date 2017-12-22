<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas extends CI_Controller {

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->helper('utility_helper');
	}
	
	public function index()
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('komoditas');
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$data['petani'] = $this->Petani->get_union()->result();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addKomoditas',$data);
		$this->load->view('footer');
	}
}
