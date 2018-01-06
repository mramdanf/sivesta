<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_kontrak extends CI_Model {

	function __construct()
	{
		parent::__construct();
		
	}

	public function m_create($kontrak)
	{
		$this->load->helper('utility_helper');
		$this->load->model('M_komoditas');

		$min_kontrak = $kontrak['komoditas']['min_kontrak'];

		$in_kontrak['id_kontrak']        = utLKontrakId();
		$in_kontrak['tgl_mulai_kontrak'] = date('Y-m-d');
		$in_kontrak['tgl_kadaluarsa']    = date('Y-m-d', strtotime(' + '.$min_kontrak.' years'));
		$in_kontrak['virtual_account']   = mt_rand();
		$in_kontrak['id_funders']        = $kontrak['funder']['id_funders'];
		$in_kontrak['id_komoditas']      = $kontrak['komoditas']['id_komoditas'];
		$in_kontrak['status_kontrak']    = $kontrak['status_kontrak'];
		$in_kontrak['biaya_total']       = $kontrak['biaya_total'];
		$in_kontrak['jumlah_benih']      = $kontrak['jumlah_benih'];

		$kontrak['virtual_account'] = $in_kontrak['virtual_account'];

		$res = $this->db->insert('tb_kontrak', $in_kontrak);

		return ($res) ? $kontrak : FALSE;
	}

	public function m_kontrak_newseeds($get)
	{
		$id_funder = $get['id_funders'];
		$filter    = $get['filter'];

		if ($filter == 'new_seeds')
			$this->db->where('status_kontrak = 1 OR status_kontrak = 2'); // Pending, assigning
		else if ($filter == 'in_progress')
			$this->db->where('status_kontrak = 3'); // In Progres
		else if ($filter == 'harvested')
			$this->db->where('status_kontrak = 4'); // Harvested

		$res = $this->db
		            ->where('id_funders', $id_funder)
		            ->get('tb_kontrak')
		            ->result_array();

		return $res;
	}

	public function m_progres_investasi($id_kontrak)
	{
		$res = $this->db
		            ->get_where('tb_progres_investasi', array('id_kontrak'=>$id_kontrak))
		            ->result_array();
		
		foreach ($res as $key => $value) 
		{
			$res[$key]['img_url'] = base_url('app_assets/img/progress_invest/'.$value['image']);
			$res[$key]['posted_at'] = date('M d, Y', strtotime($value['posted_at']));
			$res[$key]['striped_progress_text'] = strip_tags($value['keterangan']);
		}
		return $res;
	}

	private function plog($data)
	{
		log_message('error', print_r($data, true));
	}

}

/* End of file M_kontrak.php */
/* Location: ./application/models/M_kontrak.php */