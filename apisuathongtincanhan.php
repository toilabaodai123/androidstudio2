<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$taikhoan=filter_input(INPUT_POST,"taikhoan");
	$hoten=filter_input(INPUT_POST,"hoten");
	$matkhau=filter_input(INPUT_POST,"matkhau");
	
	$con=mysqli_connect("localhost","root","","laraone");
	if($con){
		$suathongtin = "UPDATE khachhangs
						SET hoten ='".$hoten."',matkhau='".$matkhau."'
						where taikhoan='".$taikhoan."'
						";
		if(mysqli_query($con , $suathongtin))
		{
			echo "1";
		}
		else
		{
			echo "2";
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>







		