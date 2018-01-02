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
	public function index()
	{
		$data['artikel'] = $this->m_artikel->gets();
		// print_r($data);die();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('artikel',$data);
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addArtikel');
		$this->load->view('footer');
	}
	public function delete($value='')
	{
		$where = array('id_artikel'=>$value);
		$this->m_artikel->delete($where);
		redirect('api/funder/Artikel','refresh');
	}
	public function add($value='')
	{
		$image = 'default.png';
		// setting konfigurasi upload
        $config['upload_path'] = 'app_assets/img/artikel';
        $config['allowed_types'] = 'png|jpg|jpeg';
        // load library upload
        $this->load->library('upload', $config);
        if (!$this->upload->do_upload('image')) {
            $error = $this->upload->display_errors();
            // $this->session->set_flashdata('info',$error);
            // redirect('Artikel/tambah');
        	echo $error;
        } else {
            $result = $this->upload->data();
            $image = $result['orig_name'];
        }
		$artikel = array('id_artikel'=>utArtikelId(),'judul' => $this->input->post('judul'),'penulis' => $this->input->post('penulis'),'konten' => $this->input->post('konten'),'tgl_posting' => date("Y-m-d"),'image'=>$image);
		// print_r($artikel);die();
		$this->m_artikel->insert($artikel);
		redirect('api/funder/Artikel','refresh');
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