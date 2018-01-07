<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas extends CI_Controller {

	const HTTP_OK = 200;
	const HTTP_INTERNAL_SERVER_ERROR = 500;

	function __construct()
	{
		parent::__construct();
		$this->load->model('m_komoditas');
		$this->load->helper('utility_helper');
	}

	public function get_komoditas()
	{
		$limit = $this->input->get('limit');
		$limit = (!empty($limit)) ? $limit : 0;

		$komoditas = $this->m_komoditas->get_komoditas($limit);

		utPrintResponse(self::HTTP_OK, 'komoditas', $komoditas);
	}

	public function get_new_komoditas()
	{
		$komoditas = $this->m_komoditas->get_komoditas(3);

		utPrintResponse(self::HTTP_OK, 'komoditas', $komoditas);
	}

	public function simulation()
	{
		$res = $this->m_komoditas->m_simulation($this->input->get());

		if ($res)
		{
			$response['status']          = TRUE;
			$response['list_simulation'] = $res;
			utPrintJson($response, 200);

		} else
		{
			$response['status']          = FALSE;
			$response['msg']             = 'Terjadi error, gagal membuat simulasi.';
			utPrintJson($response, 500);
		}
	}

}

/* End of file Komoditas.php */
/* Location: ./application/controllers/api/Komoditas.php */