<?php
defined('BASEPATH') OR exit('No direct script access allowed');
use Illuminate\Database\Eloquent\Model as Eloquent;
class ProgressInvestasi extends Eloquent
{
    protected $table = 'tb_kontrak';
    public $timestamps = false;

    public function __construct()
	{
		parent::__construct();
		// $this->load->helper('utility_helper');
	}

    public function Investasi()
      {
        return $this->belongsTo('tb_');
      }

    public function getProgressKontrak($email,$password)
    {
    	return Funders::where('email',$email)->where('password',$password)->get();
    }
}