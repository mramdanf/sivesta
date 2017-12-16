<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas extends CI_Controller {

	
	public function index()
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('petani');
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addKomoditas');
		$this->load->view('footer');
	}
}
