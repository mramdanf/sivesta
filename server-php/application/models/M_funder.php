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
		$query = "SELECT *,COUNT(id_komoditas) as jumlah_seed, join_at as tanggal_join FROM `tb_kontrak` WHERE id_funders = '".$post['id_funders']."'";
		return $this->db->queyr($query)->result_array();
	}


	public function create_account($post)
	{
		$post['password'] = md5($post['password']);
		$post['joined_at'] = date('Y-m-d');
		$post['id_funders'] = utLFunderId();
		$post['created_at'] = date("Y-m-d");
		$res = $this->db->insert('tb_funders', $post);

		return $res;
	}

	public function update_account($post)
	{
		log_message('error', print_r($post, true));
		$id_funders = $post['id_funders'];
		unset($post['id_funders']);
		return $this->db->update('tb_funders', $post, array('id_funders'=>$id_funders));
	}
	

}

/* End of file Funder.php */
/* Location: ./application/models/Funder.php */