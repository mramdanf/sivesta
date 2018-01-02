<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_artikel extends CI_Model {

	public function __construct()
	{
		parent::__construct();
	}
	
	// testing
	public function gets()
	{
		$articles = $this->db->get('tb_artikel')->result_array();

		foreach ($articles as $key => $art) 
		{
			$articles[$key]['tgl_posting_text'] = date('M d, Y', strtotime($art['tgl_posting']));
			$articles[$key]['img_url'] = base_url('app_assets/img/artikel').'/'.$art['image'];
			$articles[$key]['content_strip'] = strip_tags($art['konten']);
			$articles[$key]['web_view_url'] = base_url('api/funder/artikel/artikel_webview').'/'.$art['id_artikel'];
		}

		return $articles;
	}

	public function delete($value='')
	{
		$this->db->delete('tb_artikel',$value);
	}
	public function insert($object)
	{
		$this->db->insert('tb_artikel', $object);
	}

	public function view_artikel($id_artikel)
	{
		return $this->db->get_where('tb_artikel', array('id_artikel'=>$id_artikel))->row_array();
	}

}

/* End of file m_artikel.php */
/* Location: ./application/models/m_artikel.php */