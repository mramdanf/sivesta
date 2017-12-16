<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Petani_m extends CI_Model {

	function insert($data)
	{
		$this->db->insert('petani', $data);
   		return $this->db->insert_id();
	}
	

}
