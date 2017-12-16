<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Petani extends CI_Controller {

	
	public function index()
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('petani');
		$this->load->view('footer');
	}
	public function tambah($value='')
	{
		$this->load->view('header');
		$this->load->view('sidebar');
		$this->load->view('addPetani');
		$this->load->view('footer');
	}
	public function insert($value='')
	{
		// echo $this->input->post('kontak');
		$petani = array('kontak' => $this->input->post('kontak'),'alamat' => $this->input->post('alamat') );
		$id_petani = $this->Petani->insert($petani);
		if ($this->input->post('optionsRadios') == 'Kelompok') {
			$petani_kelompok = array('id_petani' => $id_petani,'nama_kelompok' => $this->input->post('nama_kelompok'),'jumlah_petani' => $this->input->post('jumlah_anggota'),'penanggung_jawab' => $this->input->post('pj'));
			$this->Petani->insert_kelompok($petani_kelompok);
		}else{
			$petani_perorangan = array('id_petani' => $id_petani,'nama'=>$this->input->post('nama'));
			$this->Petani->insert_perorangan($petani_perorangan);
		}
		redirect('Petani','refresh')
	}
}
