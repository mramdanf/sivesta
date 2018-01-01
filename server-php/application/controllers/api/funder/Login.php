<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Login extends CI_Controller {

	const HTTP_OK = 200;

	public function __construct()
	{
		parent::__construct();
	}

	public function index()
	{
		$this->load->helper('Utility');

		if ($funder = utCredFunders())
			utPrintJson($funder, self::HTTP_OK);
		else
			echo "failed";
	}

}

/* End of file Login.php */
/* Location: ./application/controllers/api/farmer/Login.php */