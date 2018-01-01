<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_kontrak extends CI_Model {

	function __construct()
	{
		parent::__construct();
		
	}

	/*
	(
	    [id_funder] => F00001
	    [id_komoditas] => K00001
	    [biaya_total] => 2608000
	    [status_pembayaran] => 1
	)
	*/
	public function m_create($kontrak)
	{
		$this->load->model('M_komoditas');

		$komoditas   = $this->M_komoditas->get_kom_byid($kontrak['id_komoditas']);
		$min_kontrak = $komoditas['min_kontrak'];

		$kontrak['tgl_mulai_kontrak'] = date('Y-m-d');
		$kontrak['tgl_kadaluarsa']    = date('Y-m-d', strtotime(' + '.$min_kontrak.' years'));
		$kontrak['virtual_account']   = mt_rand();
		$kontrak['id_petani']         = NULL;

		$this->db->trans_start();
		$res = $this->db->insert('tb_kontrak', $kontrak);
		$this->db->trans_complete();

		return ($res) ? $kontrak : FALSE;
	}

	private function plog($data)
	{
		log_message('error', print_r($data, true));
	}

}

/* End of file M_kontrak.php */
/* Location: ./application/models/M_kontrak.php */