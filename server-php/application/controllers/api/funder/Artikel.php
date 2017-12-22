<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Artikel extends CI_Controller {

	const HTTP_OK = 200;

	public function __construct()
	{
		parent::__construct();
		$this->load->model('m_artikel');
		$this->load->helper('utility_helper');
	}

	public function gets()
	{
		$articles = $this->m_artikel->gets();
		utPrintResponse(self::HTTP_OK, 'articles', $articles);

	}

	public function artikel_webview($id_artikel)
	{
		
		$artikel = $this->m_artikel->view_artikel($id_artikel);
		$content['artikel'] = $artikel;
		
		$this->load->view('android_view_artikel', $content);
	}

}

/* End of file Artikel.php */
/* Location: ./application/controllers/api/Artikel.php */