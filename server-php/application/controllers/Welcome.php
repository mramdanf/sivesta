<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Welcome extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->model('admin/Komoditas_m','Komoditas');
		$this->load->model('admin/Investasi_m','Investasi');
		$this->load->model('M_funder','Funders');
		$this->load->helper('utility_helper');
	}

	public function index()
	{
		$data['investasi'] = $this->Investasi->getData()->num_rows();
		$data['petani'] = $this->Petani->get_union()->num_rows();
		$data['komoditas'] = $this->Komoditas->get_union()->num_rows();
		$data['funder'] = $this->Funders->getData()->num_rows();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('welcome_message',$data);
		$this->load->view('footer');
	}
	public function sandbox($value='')
	{
		$this->load->model('admin/User_model');
		$users = $this->User_model->create_funder();
		// $users = User_model::where('username','dhani')->get();
		print_r('success');
		// foreach ($users as $key => $value) {
  //           echo "Judul Artikel : ".$value->judul."<br>";
  //       }
	}
}
