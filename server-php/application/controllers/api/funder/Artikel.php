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
		foreach ($articles as $key => $article) 
		{
			$articles[$key]['tgl_posting_text'] = date('M d, Y', strtotime($article['tgl_posting']));
		}
		utPrintResponse(self::HTTP_OK, 'articles', $articles);

	}

}

/* End of file Artikel.php */
/* Location: ./application/controllers/api/Artikel.php */