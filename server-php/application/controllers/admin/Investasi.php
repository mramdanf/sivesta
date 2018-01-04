<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Investasi extends CI_Controller {

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->model('admin/Komoditas_m','Komoditas');
		$this->load->model('admin/Investasi_m','Investasi');
		$this->load->model('admin/Progress_investasi_m','ProgressInvestasi');
		$this->load->helper('utility_helper');
	}
	
	public function index()
	{
		$data['investasi'] = $this->Investasi->getData()->result();
		// $data['komoditas'] = $this->Komoditas->get_union()->result();
		// print_r($data);die();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('investasi',$data);
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
	public function progress($value='')
	{
		$data['investasi'] = $this->Investasi->getById($value)->result_array()[0];
		$data['progress'] = $this->ProgressInvestasi->getById($value);
		// $where['id_funder'] = $value;
		// $data['funder'] = $this->M_funder->get_details($where);
		// print_r($data);die();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('progressPenanaman',$data);
		$this->load->view('footer');
	}
	public function updateProgress($value='')
	{
		$config['upload_path'] = 'app_assets/img/progres_invest';
        $config['allowed_types'] = 'png|jpg|jpeg';
        // load library upload
        $this->load->library('upload', $config);
        if (!$this->upload->do_upload('image')) {
            $error = $this->upload->display_errors();
            echo $error;
        	$image = 'default.png';
        } else {
            $result = $this->upload->data();
            $image = $result['orig_name'];
        }
        $status = array('status_kontrak'=>$this->input->post('status_kontrak'));
        $this->Investasi->update_progress($value,$status);
		$progress = array('id_kontrak'=>$value,'image'=>$image,'keterangan'=>$this->input->post('keterangan'));
		// print_r($progress);die();
		$this->ProgressInvestasi->insert($progress);
		redirect('admin/Investasi','refresh');
	}
	public function add($value='')
	{
		// echo $this->input->post('pilihan');die();
		$id_komoditas=utLKomoditasId();
		$komoditas = array(
			'id_komoditas' => $id_komoditas,
			'nama' => $this->input->post('nama_komoditas'),
			'harga' => $this->input->post('harga'),
			'stock' => $this->input->post('stock'),
			'lokasi' => $this->input->post('alamat'),
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
		redirect('admin/Komoditas','refresh');
	}
}
