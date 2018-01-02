<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas extends CI_Controller {

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->model('admin/Komoditas_m','Komoditas');
		$this->load->helper('utility_helper');
	}
	
	public function index()
	{
		$data['komoditas'] = $this->Komoditas->get_union()->result();
		// print_r($data);die();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('komoditas',$data);
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$data['petani'] = $this->Petani->get_union()->result();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addKomoditas',$data);
		$this->load->view('footer');
	}
	public function delete($value,$kategori)
	{
		// echo $value;
		$this->Komoditas->delete($value,$kategori);
		redirect('admin/Komoditas','refresh');
	}
	public function add($value='')
	{
		// echo $this->input->post('id_petani');die();
		$config['upload_path'] = 'app_assets/img/komoditas';
        $config['allowed_types'] = 'png|jpg|jpeg';
        // load library upload
        $this->load->library('upload', $config);
        if (!$this->upload->do_upload('image')) {
            $error = $this->upload->display_errors();
            echo $error;
            // $this->session->set_flashdata('info',$error);
            // redirect('Artikel/tambah');
        	$image = 'default.png';
        } else {
            $result = $this->upload->data();
            $image = $result['orig_name'];
        }
		$id_komoditas=utLKomoditasId();
		$komoditas = array(
			'id_komoditas' => $id_komoditas,
			'nama' => $this->input->post('nama_komoditas'),
			'harga' => $this->input->post('harga'),
			'stock' => $this->input->post('stock'),
			'lokasi' => $this->input->post('alamat'),
			'image' => $image,
			'deskripsi' => $this->input->post('deskripsi'),
			'min_kontrak' => $this->input->post('min_kontrak'),
			'profit' => $this->input->post('persentase'),
			'latitude' => $this->input->post('latitude'),
			'longitude' => $this->input->post('longitude'),
			 );
		// print_r($komoditas);die();
		if ($this->input->post('pilihan') == 'tahunan') {
			$side = array('id_komoditas'=>$id_komoditas,'panjang'=>$this->input->post('panjang'),'lebar'=>$this->input->post('lebar'));
			$this->Komoditas->insert($komoditas,$side,'tahunan');
		}else{
			$side = array('id_komoditas'=>$id_komoditas,'jumlah_phon'=>$this->input->post('jumlah'));
			$this->Komoditas->insert($komoditas,$side,'perenial');
		}
		$penanaman = array('id_petani'=>$this->input->post('id_petani'),'id_komoditas'=>$id_komoditas);
		$this->Komoditas->simpanPenanaman($penanaman);
		redirect('admin/Komoditas','refresh');
	}
}
