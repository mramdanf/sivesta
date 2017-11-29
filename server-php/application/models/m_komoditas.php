<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_komoditas extends CI_Model {

	function __construct()
	{
		parent::__construct();
	}

	public function add($data)
	{
		$this->load->helper('utility_helper');

		$komoditas['id_komoditas'] = utLKomoditasId();
		$komoditas['nama']      = $data['nama'];
		$komoditas['harga']     = $data['harga'];
		$komoditas['stock']     = $data['stock'];
		$komoditas['lokasi']    = $data['lokasi'];
		$komoditas['latitude']  = $data['latitude'];
		$komoditas['longitude'] = $data['longitude'];

		// Insert ke table komoditas
		$in_komoditas = $this->db->insert('tb_komoditas', $komoditas);

		// Insert ke table perenial atau tahunan
		// 1=komoditas parenial, 2=komoditas tahunan
		$in_sub_komoditas = FALSE;
		if ($data['type'] == 1)
		{
			$parenial['id_komoditas'] = $komoditas['id_komoditas'];
			$parenial['jumlah_phon']  = $data['jumlah_pohon'];
			$in_sub_komoditas = $this->db->insert('tb_komoditas_perenial', $parenial);
		}
		else if ($data['type'] == 2)
		{
			$tahunan['id_komoditas'] = $komoditas['id_komoditas'];
			$tahunan['panjang'] = $data['panjang'];
			$tahunan['lebar']   = $data['lebar'];
			$in_sub_komoditas = $this->db->insert('tb_komoditas_tahunan', $tahunan);			
		}
		
		// Insert to table penanaman
		$penanaman['id_petani'] = $data['id_petani'];
		$penanaman['id_komoditas'] = $komoditas['id_komoditas'];
		$in_penanaman = $this->db->insert('tb_penanaman', $penanaman);

		return $in_komoditas && $in_sub_komoditas && $in_penanaman;

	}

}

/* End of file m_komoditas.php */
/* Location: ./application/models/m_komoditas.php */