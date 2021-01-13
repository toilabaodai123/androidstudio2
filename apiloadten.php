<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$taikhoan = filter_input(INPUT_POST,"taikhoan");
	
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select hoten from khachhangs where taikhoan='".$taikhoan."' ";
		$result = mysqli_query($con , $sql);
		if($result)
			foreach($result as $a) echo $a['hoten'];
		else
			echo 2;
	}
	else{
		echo "Database connection failed!";
		
	}
?>