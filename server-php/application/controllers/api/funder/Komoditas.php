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

	public function add()
	{
		$post = $this->input->post();

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

		$komoditas = $this->m_komoditas->komoditasByFarmer($idPetani);

		utPrintResponse(self::HTTP_OK, 'komoditas', $komoditas);
	}

	public function update()
	{
		$post = $this->input->post();

		$res_add = $this->m_komoditas->update($post);

		if ($res_add)
		{
			$msg = 'Data komoditas berhasil dirubah.';
			utPrintResponse(self::HTTP_OK, 'msg', $msg);
		}
		else
			utPrintResponse(
				self::HTTP_INTERNAL_SERVER_ERROR, 
				'msg', 
				'Terjadi error, data gagal dirubah.'
			);

	}

	public function delete()
	{
		$data['id_komoditas'] = $this->input->post('id_komoditas');
		$data['id_petani'] = $this->input->post('id_petani');
		$data['kom_type'] = $this->input->post('kom_type');

		$res_delete = $this->m_komoditas->delete($data);

		if ($res_delete)
			utPrintResponse(self::HTTP_OK, 'msg', 'Data komoditas berhasil dihapus.');
		else
			utPrintResponse(
				self::HTTP_INTERNAL_SERVER_ERROR, 
				'msg', 
				'Terjadi error, data komoditas gagal dihapus.'
			);
	}

	public function get_komoditas()
	{
		$komoditas = $this->m_komoditas->get_komoditas();

		utPrintResponse(self::HTTP_OK, 'komoditas', $komoditas);
	}

}

/* End of file Komoditas.php */
/* Location: ./application/controllers/api/Komoditas.php */