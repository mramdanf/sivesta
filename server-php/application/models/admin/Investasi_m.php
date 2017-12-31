<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Investasi_m extends CI_Model {

	function getData($value='')
	{
		$query = 'SELECT * FROM tb_kontrak INNER JOIN tb_petani on tb_petani.id_petani=tb_kontrak.id_petani INNER JOIN tb_komoditas on tb_komoditas.id_komoditas = tb_kontrak.id_komoditas INNER JOIN tb_funders on tb_funders.id_funders = tb_kontrak.id_funders';
		return $this->db->query($query);
	}
	function simpanPenanaman($array)
	{
		$this->db->insert('tb_penanaman', $array);
	}
}
