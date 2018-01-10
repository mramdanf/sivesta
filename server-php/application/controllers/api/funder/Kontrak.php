<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Kontrak extends CI_Controller {

	function __construct()
	{
		parent::__construct();
		$this->load->helper('utility_helper');
		utCredFunders();
		$this->load->model('M_kontrak');
		$this->load->model('Investasi_class');
		$this->load->model('Funders_class');
		$this->load->model('ProgressKomoditas_class');
		$this->load->model('Komoditas_class');
	}

	public function index()
	{
		
	}

	public function create()
	{
		$data    = file_get_contents('php://input');
		$kontrak = json_decode($data , true);
		$kontrak['id_kontrak'] = utKontrakId();
		$res = $this->Investasi_class->create_kontrak($kontrak);
		// $res = $this->M_kontrak->m_create($kontrak);

		if ($res)
		{	
			$res = $res[0];
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

	public function progres_investasi()
	{
		$id_kontrak = $this->input->get('id_kontrak');
		$progress = $this->M_kontrak->m_progres_investasi($id_kontrak);

		if (count($progress) >= 0)
		{
			$response['status']       = TRUE;
			$response['list_progress']     = $progress;
			
			$this->plog($response);

			utPrintJson($response, 200);
		}
		else 
		{
			$response['status'] = FALSE;
			$response['msg']    = 'Terjadi error, tidak dapat mengambil data new seeds';

			utPrintJson($response, 500);
		}
	}

	private function plog($data)
	{
		log_message('error', print_r($data, true));
	}

}

/* End of file Kontrak.php */
/* Location: ./application/controllers/api/funder/Kontrak.php */