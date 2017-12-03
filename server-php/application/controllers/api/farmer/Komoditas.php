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

		utCredFarmers();
	}

	public function add()
	{
		$post = $this->input->post();

		log_message('error', print_r($post, TRUE));

		$res_add = $this->m_komoditas->add($post);

		if ($res_add)
			utPrintResponse(self::HTTP_OK, 'msg', 'Data komoditas berhasil ditambahkan.');
		else
			utPrintResponse(
				self::HTTP_INTERNAL_SERVER_ERROR, 
				'msg', 
				'Data komoditas berhasil ditambahkan.'
			);

	}

	public function gets()
	{
		$idPetani = $this->input->get('id_petani');
		
		log_message('error', 'id_petani: '.$idPetani);

		$komoditas = $this->m_komoditas->komoditasByFarmer($idPetani);

		if ($komoditas)
			utPrintResponse(self::HTTP_OK, 'komoditas', $komoditas);
		else
			utPrintResponse(
				self::HTTP_INTERNAL_SERVER_ERROR, 
				'komoditas', 
				array()
			);
	}

}

/* End of file Komoditas.php */
/* Location: ./application/controllers/api/Komoditas.php */