<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Investasi extends CI_Controller {

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->model('admin/Komoditas_m','Komoditas');
		$this->load->model('admin/Investasi_m');
		$this->load->model('admin/Progress_investasi_m','ProgressInvestasi');
		$this->load->helper('utility_helper');
		$this->load->model('Investasi_class');
		$this->load->model('Funders_class');
		$this->load->model('ProgressKomoditas_class');
		$this->load->model('Komoditas_class');
	}
	
	public function index()
	{
		$data['investasi'] = $this->Investasi_class->getListKontrak();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('investasi',$data);
		$this->load->view('footer');
	}
	public function progress($value='')
	{
		$data['investasi'] = $this->Investasi_class->getKontrakId($value);
		// $where['id_funder'] = $value;
		// $data['funder'] = $this->M_funder->get_details($where);
		// print_r($data['investasi']);die();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('progressPenanaman',$data);
		$this->load->view('footer');
	}
	public function updateProgress($value='')
	{
		$config['upload_path'] = 'app_assets/img/progress_invest';
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
        // print_r($status);die();
        $this->Investasi_class->updateKontrak($value,$status);
		$progress = array('id_kontrak'=>$value,'image'=>$image,'keterangan'=>$this->input->post('keterangan'),'created_date'=>date("Y-m-d H:m:s"));
		// print_r($progress);die();
		$this->ProgressKomoditas_class->insert($progress);
		redirect('admin/Investasi','refresh');
	}
	
}
