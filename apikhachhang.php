<?php

	header('Access-Control-Allow-Origin: *');
	header("Content-type: application/json; charset=utf-8");
	$taikhoan_nhap=filter_input(INPUT_POST,"taikhoann");
	$matkhau_nhap=filter_input(INPUT_POST,"matkhauu");

	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select * from khachhangs";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['taikhoan']=$row['taikhoan'];
				$response[$i]['matkhau']=$row['matkhau'];
				$response[$i]['hoten']=$row['hoten'];
				$i++;
				if(($taikhoan_nhap == $row['taikhoan'])&& ($matkhau_nhap == $row['matkhau']))
					echo '1';
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
			$test= "Phạm Thanh Bảo Đại";
			echo $test;
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>