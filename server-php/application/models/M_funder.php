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

	public function create_account($post)
	{
		$post['password'] = md5($post['password']);
		$post['id_funders'] = utLFunderId();
		$res = $this->db->insert('tb_funders', $post);

		return $res;
	}
	

}

/* End of file Funder.php */
/* Location: ./application/models/Funder.php */