<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Petani extends CI_Controller {

	function __construct() {
		parent::__construct();
		date_default_timezone_set("Asia/Jakarta");
		$this->load->model('admin/Petani_m','Petani');
		$this->load->model('admin/Komoditas_m','Komoditas');
		$this->load->helper('utility_helper');
	}
	
	public function index()
	{
		$data['petani'] = $this->Petani->get_union()->result();
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('petani',$data);
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addPetani');
		$this->load->view('footer');
	}
	public function delete($id,$kategori)
	{
		$find_in_komoditas = $this->Komoditas->getKomoditas_petani($id)->num_rows();
		if ($find_in_komoditas == 0) {
			$where =  array('id_petani'=>$id);
			$this->Petani->delete($where,$kategori);
			// redirect('admin/Petani','refresh');
		}else{
			$this->session->set_flashdata('info', '<div class="alert alert-danger">Petani masih memiliki kmoditas.</div>');
			// redirect('admin/Petani','refresh');
		}
		// die();
		redirect('admin/Petani','refresh');
	}
	public function insert($value='')
	{
		
		$id_petani = utPetaniId();
		// echo $this->input->post('nama');die();
		$petani = array('id_petani'=>$id_petani,'kontak' => $this->input->post('kontak'),'alamat' => $this->input->post('alamat'),'username'=>$this->input->post('username'),'password'=>md5($this->input->post('password')) );
		// print_r($petani);die();
		$this->Petani->insert($petani);
		if ($this->input->post('optionsRadios') == 'Kelompok') {
			$petani_kelompok = array('id_petani' => $id_petani,'nama_kelompok' => $this->input->post('nama_kelompok'),'jumlah_petani' => $this->input->post('jumlah'),'penanggung_jawab' => $this->input->post('nama_pj'));
			$this->Petani->insert_kelompok($petani_kelompok);
		}else{
			$petani_perorangan = array('id_petani' => $id_petani,'nama'=>$this->input->post('nama'));
			$this->Petani->insert_perorangan($petani_perorangan);
		}
		redirect('admin/Petani','refresh');
	}
}
