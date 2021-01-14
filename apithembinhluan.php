<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$taikhoan=filter_input(INPUT_POST,"taikhoan");
	$noidung=filter_input(INPUT_POST,"noidung");
	$idphim=filter_input(INPUT_POST,"idphim");
	
	$con=mysqli_connect("localhost","root","","laraone");
	if($con){
		$thembinhluan = "INSERT INTO binhluans (khachhang,noidung,idphim) values('".$taikhoan."','".$noidung."',".$idphim.")";
		if(!mysqli_query($con , $thembinhluan))
		{
			echo "2";
		}
		else
		{
			echo "1";
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>







		