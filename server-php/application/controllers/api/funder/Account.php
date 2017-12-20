<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Account extends CI_Controller {

	const HTTP_OK = 200;
	const HTTP_INTERNAL_SERVER_ERROR = 500;

	public function __construct()
	{
		parent::__construct();
		$this->load->model('m_funder');
		$this->load->helper('utility_helper');
	}

	public function create()
	{
		$res = $this->m_funder->create_account($this->input->post());

		if ($res)
			utPrintResponse(self::HTTP_OK, 'msg', "Account successfully registered.");
		else
			utPrintResponse(self::HTTP_INTERNAL_SERVER_ERROR, 'msg', "Error uccored, registration failed");
	}

}

/* End of file Account.php */
/* Location: ./application/controllers/api/funder/Account.php */