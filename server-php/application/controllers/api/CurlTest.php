<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class CurlTest extends CI_Controller {

	function __construct()
	{
		parent::__construct();
	}

	public function index()
	{
		echo "string";
	}

	public function curlAddKom()
	{
		$url = 'http://localhost/sivesta/server-php/api/farmer/komoditas/add';

		$fields['nama']      = urlencode('Jambu Biji');
		$fields['harga']     = urlencode('1000000');
		$fields['stock']     = urlencode('500');
		$fields['lokasi']    = urlencode('Cibadak');
		$fields['latitude']  = urlencode('0');
		$fields['longitude'] = urlencode('0');

		$fields['type']         = urlencode('1');
		$fields['jumlah_pohon'] = urlencode('100');
		$fields['id_petani']    = urlencode('P001');

		//url-ify the data for the POST
		$fields_string = '';
		foreach($fields as $key=>$value) { $fields_string .= $key.'='.$value.'&'; }
		rtrim($fields_string, '&');

		//open connection
		$ch = curl_init();

		//set the url, number of POST vars, POST data
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, count($fields));
		curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);

		// Curl Header
		curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC); 
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_USERPWD, 'messi' . ":" . 'messi');

		curl_setopt($ch, CURLOPT_RETURNTRANSFER, FALSE);  

		//execute post
		$result = curl_exec($ch);

		//close connection
		curl_close($ch);
	}

	public function curlUpdateKom()
	{
		$url = 'http://localhost/sivesta/server-php/api/farmer/komoditas/add';

		$fields['nama']      = urlencode('Jambu Bijix');
		$fields['harga']     = urlencode('1100000');
		$fields['stock']     = urlencode('550');
		$fields['lokasi']    = urlencode('Cibadakx');
		$fields['latitude']  = urlencode('1');
		$fields['longitude'] = urlencode('1');

		$fields['type']         = urlencode('1');
		$fields['jumlah_pohon'] = urlencode('100');

		//url-ify the data for the POST
		$fields_string = '';
		foreach($fields as $key=>$value) { $fields_string .= $key.'='.$value.'&'; }
		rtrim($fields_string, '&');

		//open connection
		$ch = curl_init();

		//set the url, number of POST vars, POST data
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, count($fields));
		curl_setopt($ch, CURLOPT_POSTFIELDS, $fields_string);

		// Curl Header
		curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC); 
		curl_setopt($ch, CURLOPT_HEADER, FALSE);
		curl_setopt($ch, CURLOPT_USERPWD, 'messi' . ":" . 'messi');

		curl_setopt($ch, CURLOPT_RETURNTRANSFER, FALSE);  

		//execute post
		$result = curl_exec($ch);

		//close connection
		curl_close($ch);
	}

}

/* End of file CurlTest.php */
/* Location: ./application/controllers/api/CurlTest.php */