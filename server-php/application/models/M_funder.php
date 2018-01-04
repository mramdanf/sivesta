<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_funder extends CI_Model {

	public function funder($data)
	{
		$res = $this->db->get_where(
			'funder',
			array('username'=>$data['username'], 'password'=>$data['password'])
		)
		->result_array();

		return $res;
	}
	public function getData()
	{
		return $this->db->get('tb_funders');
	}

	public function get_details($post)
	{
		$query = "SELECT COUNT(id_komoditas) as jumlah_seed, join_at as tanggal_join FROM `tb_kontrak` WHERE id_funders = '".$post['id_funders']."'";
		return $this->db->queyr($query)->result_array();
	}


	public function create_account($post)
	{
		$post['password'] = md5($post['password']);
		$post['id_funders'] = utLFunderId();
		$res = $this->db->insert('tb_funders', $post);

		return $res;
	}

	public function update_account($post)
	{
		return $this->db->update('tb_funders', $post);
	}
	

}

/* End of file Funder.php */
/* Location: ./application/models/Funder.php */