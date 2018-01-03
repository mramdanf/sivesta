<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');


function utCredFunders()
{
	$HTTP_UNAUTHORIZED = 401;
	$STATUS = 'status';

	if (!isset($_SERVER['PHP_AUTH_USER'])) {
	    header('WWW-Authenticate: Basic realm="My Realm"');
	    header('HTTP/1.0 401 Unauthorized');
		$response = array(
			'status' => FALSE,
			'user_detail' => array()
			);
		utPrintJson($response, $HTTP_UNAUTHORIZED);
		exit;
	} else 
	{
	    $email = $_SERVER['PHP_AUTH_USER'];
	    $password =  $_SERVER['PHP_AUTH_PW'];

	    $result = utCheckFunders($email, $password);
		
		if (!empty($result)) 
		{
			return $result;
		}else
		{
			$response = array(
				'status' => FALSE,
				'user_detail' => array()
				);
			utPrintJson($response, $HTTP_UNAUTHORIZED);
			exit;
		}
	}
}

function utCheckFunders($email, $password)
{
	$CI =& get_instance();
	
	$CI->load->database();

	$CI->db->where('email', $email);
	$CI->db->where('password', md5($password));

	$query = $CI->db->get('tb_funders')->row_array();


	if ($query)
	{
		$CI->load->helper('url');
		$query['password'] = $password;
		$query['profile_image_url'] 
		 = base_url('app_assets/img/user') .'/'.$query['profile_image'];
	}

	return $query;

}

function utCredFarmers()
{
	$HTTP_UNAUTHORIZED = 401;
	$STATUS = 'status';

	if (!isset($_SERVER['PHP_AUTH_USER'])) {
	    header('WWW-Authenticate: Basic realm="My Realm"');
	    header('HTTP/1.0 401 Unauthorized');
		$response = array(
			'status' => FALSE,
			'user_detail' => array()
			);
		utPrintJson($response, $HTTP_UNAUTHORIZED);
		exit;
	} else 
	{
	    $username = $_SERVER['PHP_AUTH_USER'];
	    $password =  $_SERVER['PHP_AUTH_PW'];

	    $result = utCheckFarmers($username, $password);
		
		if (!empty($result)) 
		{
			return $result;
		}else
		{
			$response = array(
				'status' => FALSE,
				'user_detail' => array()
				);
			utPrintJson($response, $HTTP_UNAUTHORIZED);
			exit;
		}
	}
}

function utCheckFarmers($username, $password)
{
	$CI =& get_instance();
	
	$CI->load->database();

	$CI->db->where('username', $username);
	$CI->db->where('password', md5($password));

	$query = $CI->db
	           ->select('p.*, pp.nama')
	           ->from('tb_petani p')
	           ->join('tb_petani_perorangan pp', 'p.id_petani = pp.id_petani')
	           ->get()
	           ->row_array();

	if ($query)
		$query['password'] = $password;

	return $query;

}

function utPrintJson($data, $http_code)
{
	$CI =& get_instance();
	
	$CI->output
      ->set_status_header($http_code)
      ->set_content_type('application/json', 'utf-8')
      ->set_output(json_encode($data, JSON_PRETTY_PRINT))
      ->_display();
    exit;
}

function utPrint($data)
{
	echo "<pre>";
	print_r($data);
}

/*
	@return:
	 - String ID Komoditas
	@desc:
	 - Generate new ID Komoditas, base on current
	   max ID Komoditas
*/
function utLKomoditasId()
{
	$ci =& get_instance();
	$last_id = $ci->db
	            ->select('MAX(id_komoditas) last_id')
	            ->get('tb_komoditas')
	            ->row_array();

	$last_id = $last_id['last_id'];
	$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
	$new_id = str_pad($new_id, 5, '0', STR_PAD_LEFT);
	$new_id = 'K'.$new_id;
	
	return $new_id;
}
function utPetaniId()
{
	$ci =& get_instance();
	$last_id = $ci->db
	            ->select('MAX(id_petani) last_id')
	            ->get('tb_petani')
	            ->row_array();

	$last_id = $last_id['last_id'];
	$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
	$new_id = str_pad($new_id, 3, '0', STR_PAD_LEFT);
	$new_id = 'P'.$new_id;
	
	return $new_id;
}
function utArtikelId()
{
	$ci =& get_instance();
	$last_id = $ci->db
	            ->select('MAX(id_artikel) last_id')
	            ->get('tb_artikel')
	            ->row_array();

	$last_id = $last_id['last_id'];
	$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
	$new_id = str_pad($new_id, 4, '0', STR_PAD_LEFT);
	$new_id = 'A'.$new_id;
	
	return $new_id;
}
function utKontrakId()
{
	$ci =& get_instance();
	$last_id = $ci->db
	            ->select('MAX(id_artikel) last_id')
	            ->get('tb_kontrak')
	            ->row_array();

	$last_id = $last_id['last_id'];
	$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
	$new_id = str_pad($new_id, 4, '0', STR_PAD_LEFT);
	$new_id = 'I'.$new_id;
	
	return $new_id;
}
function utPrintResponse($http_code, $tag, $data)
{
	$response = array(
		$tag => $data
	);

	utPrintJson($response, $http_code);
}

function utLog($data)
{
	$ci =& get_instance();

	//$ci->log_message('error', print_r($data, TRUE));
}

function utLFunderId()
{
	$ci =& get_instance();
	$last_id = $ci->db
	            ->select('MAX(id_funders) last_id')
	            ->get('tb_funders')
	            ->row_array();

	$last_id = $last_id['last_id'];
	$new_id = preg_replace('/[^0-9]/', '', $last_id) + 1;
	$new_id = str_pad($new_id, 5, '0', STR_PAD_LEFT);
	$new_id = 'F'.$new_id;
	
	return $new_id;
}

function utFormatRupiah($bil, $i = FALSE)
{
	$prefix = $i === FALSE ? '' : $i.'. ';
	// $prefix = '';
	if($bil == 0){
		return $prefix.'-';
	}
	// $bil = preg_replace('[^0-9]', '', $bil);
	$bil = intval($bil);
	return $prefix.'Rp. '.number_format($bil, 0, ',', '.');
}