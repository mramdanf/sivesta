<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_artikel extends CI_Model {

	public function __construct()
	{
		parent::__construct();
	}

	public function gets()
	{
		$articles = $this->db->get('tb_artikel')->result_array();

		return $articles;
	}

}

/* End of file m_artikel.php */
/* Location: ./application/models/m_artikel.php */