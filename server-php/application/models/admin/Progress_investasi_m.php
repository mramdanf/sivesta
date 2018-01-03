<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Progress_investasi_m extends CI_Model {

	function getData($value='')
	{
		$query = 'SELECT tb_progres_investasi.* FROM `tb_progres_investasi` INNER JOIN tb_kontrak on tb_kontrak.id_kontrak = tb_progres_investasi.id_kontrak';
		return $this->db->query($query);
	}
	function getById($value='')
	{
		$query = "SELECT tb_progres_investasi.* FROM `tb_progres_investasi` INNER JOIN tb_kontrak on tb_kontrak.id_kontrak = tb_progres_investasi.id_kontrak WHERE tb_progres_investasi.id_kontrak ='".$value."' ";
		return $this->db->query($query);
	}
	public function insert($object)
	{
		$this->db->insert('tb_progres_investasi', $object);
	}
}
