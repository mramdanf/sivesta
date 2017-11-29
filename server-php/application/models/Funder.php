<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Funder extends CI_Model {

	function funder($data)
	{
		$res = $this->db->get_where(
			'funder',
			array('username'=>$data['username'], 'password'=>$data['password'])
		)
		->result_array();

		return $res;
	}
	

}

/* End of file Funder.php */
/* Location: ./application/models/Funder.php */