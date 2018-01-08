<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Petani_m extends CI_Model {

	function insert($data)
	{
		$this->db->insert('tb_petani', $data);
   		return $this->db->insert_id();
	}
	
	function insert_kelompok($data)
	{
		// print_r($data);die();
		$this->db->insert('tb_petani_kelompok', $data);
   		// return $this->db->insert_id();
	}
	function insert_perorangan($data)
	{
		// print_r($data);die();
		$this->db->insert('tb_petani_perorangan', $data);
   		// return $this->db->insert_id();
	}
	public function delete($where,$kategori)
	{
		if ($kategori == 'Perorangan') {
			$this->db->delete('tb_petani_perorangan',$where);
		}else{
			$this->db->delete('tb_petani_kelompok',$where);
		}
		$this->db->where('tb_petani', $where);
	}
	function get_union($value='')
	{
		$query = 'SELECT tb_petani.* , tb_petani_perorangan.nama as name, "Perorangan" as kategori FROM `tb_petani` inner JOIN `tb_petani_perorangan` ON tb_petani_perorangan.id_petani = tb_petani.id_petani
			UNION
			SELECT tb_petani.* , tb_petani_kelompok.nama_kelompok as name , "Kelompok" as kategori FROM `tb_petani` inner JOIN `tb_petani_kelompok` ON tb_petani_kelompok.id_petani = tb_petani.id_petani ';
		return $this->db->query($query);
	}
}
