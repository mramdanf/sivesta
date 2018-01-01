<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Kontrak extends CI_Controller {

	function __construct()
	{
		parent::__construct();
		$this->load->helper('utility_helper');
		utCredFunders();
		$this->load->model('M_kontrak');
	}

	public function index()
	{
		
	}

	public function create()
	{
		$data    = file_get_contents('php://input');
		$kontrak = json_decode($data , true);

		$res = $this->M_kontrak->m_create($kontrak);

		if ($res)
		{
			$res['msg']     = 'Kontrak berhasil dibuat.';
			$res['status']  = TRUE;

			utPrintJson($res, 200);
		}
		else
			utPrintResponse(500, 'msg', 'Terjadi error, kontrak gagal dibuat.');
		
	}

}

/* End of file Kontrak.php */
/* Location: ./application/controllers/api/funder/Kontrak.php */