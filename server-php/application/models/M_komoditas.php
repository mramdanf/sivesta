<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class M_komoditas extends CI_Model {

	function __construct()
	{
		parent::__construct();
		$this->load->helper('utility_helper');
	}

	public function add($data)
	{
		$this->load->helper('utility_helper');

		$komoditas['id_komoditas'] = utLKomoditasId();
		$komoditas['nama']      = $data['nama'];
		$komoditas['harga']     = $data['harga'];
		$komoditas['stock']     = $data['stock'];
		$komoditas['lokasi']    = $data['lokasi'];
		$komoditas['latitude']  = $data['latitude'];
		$komoditas['longitude'] = $data['longitude'];

		// Insert ke table komoditas
		$in_komoditas = $this->db->insert('tb_komoditas', $komoditas);

		// Insert ke table perenial atau tahunan
		// 1=komoditas parenial, 2=komoditas tahunan
		$in_sub_komoditas = FALSE;
		if ($data['type'] == 1)
		{
			$parenial['id_komoditas'] = $komoditas['id_komoditas'];
			$parenial['jumlah_phon']  = $data['jumlah_pohon'];
			$in_sub_komoditas = $this->db->insert('tb_komoditas_perenial', $parenial);
		}
		else if ($data['type'] == 2)
		{
			$tahunan['id_komoditas'] = $komoditas['id_komoditas'];
			$tahunan['panjang'] = $data['panjang'];
			$tahunan['lebar']   = $data['lebar'];
			$in_sub_komoditas = $this->db->insert('tb_komoditas_tahunan', $tahunan);			
		}
		
		// Insert to table penanaman
		$penanaman['id_petani'] = $data['id_petani'];
		$penanaman['id_komoditas'] = $komoditas['id_komoditas'];
		$in_penanaman = $this->db->insert('tb_penanaman', $penanaman);

		return $in_komoditas && $in_sub_komoditas && $in_penanaman;

	}

	public function update($data)
	{
		$this->load->helper('utility_helper');

		$komoditas['nama']      = $data['nama'];
		$komoditas['harga']     = $data['harga'];
		$komoditas['stock']     = $data['stock'];
		$komoditas['lokasi']    = $data['lokasi'];
		$komoditas['latitude']  = $data['latitude'];
		$komoditas['longitude'] = $data['longitude'];

		// update ke table komoditas
		$in_komoditas = $this->db
		        ->update('tb_komoditas', $komoditas, array('id_komoditas'=>$data['id_komoditas']));


		// Delete first
		$this->db->delete('tb_komoditas_perenial', array('id_komoditas'=>$data['id_komoditas']));
		$this->db->delete('tb_komoditas_tahunan', array('id_komoditas'=>$data['id_komoditas']));

		// update ke table perenial atau tahunan
		// 1=komoditas parenial, 2=komoditas tahunan
		$in_sub_komoditas = FALSE;
		if ($data['type'] == 1)
		{
			$parenial['id_komoditas'] = $data['id_komoditas'];
			$parenial['jumlah_phon']  = $data['jumlah_pohon'];
			$in_sub_komoditas = $this->db
			       ->insert('tb_komoditas_perenial', $parenial);
		}
		else if ($data['type'] == 2)
		{
			$tahunan['id_komoditas'] = $data['id_komoditas'];
			$tahunan['panjang'] = $data['panjang'];
			$tahunan['lebar']   = $data['lebar'];
			$in_sub_komoditas = $this->db
			   		->insert('tb_komoditas_tahunan', $tahunan);			
		}

		return $in_komoditas && $in_sub_komoditas;

	}

	public function komoditasByFarmer($farmerId)
	{
		$sql = "SELECT k.nama, k.harga, k.stock, k.lokasi, k.latitude, k.longitude,
				kp.jumlah_phon, kt.panjang, kt.lebar, t.id_petani, k.id_komoditas
			FROM tb_penanaman p 
			INNER JOIN tb_petani t 
			 ON p.id_petani = t.id_petani 
			INNER JOIN tb_komoditas k 
			 ON p.id_komoditas = k.id_komoditas
			LEFT JOIN tb_komoditas_perenial kp
             ON k.id_komoditas = kp.id_komoditas
            LEFT JOIN tb_komoditas_tahunan kt
             ON k.id_komoditas = kt.id_komoditas 
			WHERE t.id_petani = '".$farmerId."'";

		$sql = $this->db
		            ->query($sql)
		            ->result_array();

		if (count($sql) > 0)
		{
			foreach ($sql as $key => $komoditas) 
			{
				// 1=parenial 2=tahunan
				if (empty($komoditas['lebar']))
				{
					$parenial = array(
						'jumlah_pohon' => $komoditas['jumlah_phon']
					);
					$sql[$key]['parenial'] = $parenial;
				}
				else
				{
					$tahunan = array(
						'panjang' => $komoditas['panjang'],
						'lebar' => $komoditas['lebar']
					);
					$sql[$key]['tahunan'] = $tahunan;
				}
			}
		}

		return $sql;

	}

	public function delete($data)
	{
		$kom_type = $data['kom_type'];

		// Delete parenial or tahunan
		$del_sub_komoditas = FALSE;
		if ($kom_type == 1)
		{
			$del_sub_komoditas = $this->db
				->delete('tb_komoditas_perenial', array('id_komoditas'=>$data['id_komoditas']));
		}
		else if ($kom_type == 2)
		{
			$del_sub_komoditas = $this->db
				->delete('tb_komoditas_tahunan', array('id_komoditas'=>$data['id_komoditas']));	
		}

		// Delete from tb_penanaman
		$del_penanaman = $this->db
		        ->delete('tb_penanaman', 
		        	array(
		        		'id_komoditas'=>$data['id_komoditas'], 
		        		'id_petani'=>$data['id_petani'])
		        );

		// Delete from tb_komoditas
		$del_komoditas = $this->db
		        ->delete('tb_komoditas', array('id_komoditas'=>$data['id_komoditas']));

		return $del_sub_komoditas && $del_penanaman && $del_komoditas;

	}

	public function get_komoditas()
	{
		$kom = $this->db
		            ->get('tb_komoditas')
		            ->result_array();

		foreach ($kom as $key => $k) 
		{
			$kom[$key]['img_url'] = base_url('app_assets/img/komoditas').'/'.$k['image'];
			$kom[$key]['format_rupiah'] = utFormatRupiah($k['harga']);
		}

		return $kom;
	}

}

/* End of file m_komoditas.php */
/* Location: ./application/models/m_komoditas.php */