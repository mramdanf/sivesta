<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Investasi_m extends CI_Model {

	function getData($value='')
	{
		$query = 'SELECT tb_kontrak.*, tb_komoditas.nama as nama_komoditas,tb_funders.nama as nama_funder FROM tb_kontrak INNER JOIN  tb_komoditas on tb_komoditas.id_komoditas = tb_kontrak.id_komoditas INNER JOIN tb_funders on tb_funders.id_funders = tb_kontrak.id_funders';
		return $this->db->query($query);
	}
	public function getById($value='')
	{
		// echo $value;die();
		$query = "SELECT tb_kontrak.*, tb_komoditas.nama as nama_komoditas,tb_funders.nama as nama_funder FROM tb_kontrak INNER JOIN  tb_komoditas on tb_komoditas.id_komoditas = tb_kontrak.id_komoditas INNER JOIN tb_funders on tb_funders.id_funders = tb_kontrak.id_funders WHERE id_kontrak = '".$value."' ";
		return $this->db->query($query);
	}
	public function update_progress($id,$data)
	{
		$this->db->where('id_kontrak', $id);
		$this->db->update('tb_kontrak', $data);
	}
}
