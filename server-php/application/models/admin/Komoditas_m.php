<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Komoditas_m extends CI_Model {

	function insert($data,$side,$to)
	{
		if ($to == 'tahunan') {
			# code...
			$this->db->insert('tb_komoditas', $data);
			$this->db->insert('tb_komoditas_tahunan', $side);
		}else{
			$this->db->insert('tb_komoditas', $data);
			$this->db->insert('tb_komoditas_perenial', $side);
		}
	}
	function get_union($value='')
	{
		$query = 'SELECT tb_komoditas.* ,"Tahunan" as kategori FROM `tb_komoditas` inner JOIN `tb_komoditas_tahunan` ON tb_komoditas_tahunan.id_komoditas = tb_komoditas.id_komoditas
			UNION
			SELECT tb_komoditas.* , "Perenial" as kategori FROM `tb_komoditas` inner JOIN `tb_komoditas_perenial` ON tb_komoditas_perenial.id_komoditas = tb_komoditas.id_komoditas 
			ORDER BY id_komoditas DESC';
		return $this->db->query($query);
	}
	function simpanPenanaman($array)
	{
		$this->db->insert('tb_penanaman', $array);
	}
}
