<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas extends CI_Controller {

	
	public function index()
	{
		$this->load->view('komoditas');
	}
	public function tambah($value='')
	{
		// echo "string";
	}
}
