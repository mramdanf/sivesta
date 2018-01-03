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

	public function kontrak_my_seeds()
	{
		$this->load->model('M_komoditas');
		$res = $this->M_kontrak->m_kontrak_newseeds($this->input->get());

		if (count($res) >= 0)
		{
			foreach ($res as $key => $value) 
			{
				$res[$key]['komoditas'] = $this->M_komoditas->get_kom_byid($value['id_komoditas']);
			}

			$response['status']       = TRUE;
			$response['list_kontrak'] = $res;

			utPrintJson($response, 200);
		}
		else 
		{
			$res['status'] = FALSE;
			$res['msg']    = 'Terjadi error, tidak dapat mengambil data new seeds';

			utPrintJson($res, 500);
		}
	}

	private function plog($data)
	{
		log_message('error', print_r($data, true));
	}

	function utLKontrakId()
	{
		$ci =& get_instance();
		$last_id = $ci->db
		            ->select('MAX(id_kontrak) last_id')
		            ->get('tb_kontrak')
		            ->row_array();

		$last_id = $last_id['last_id'];
		$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
		$new_id = str_pad($new_id, 5, '0', STR_PAD_LEFT);
		$new_id = 'KN'.$new_id;
		
		echo $new_id;
	}

}

/* End of file Kontrak.php */
/* Location: ./application/controllers/api/funder/Kontrak.php */