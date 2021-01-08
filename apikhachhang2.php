<?php
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
			}
			mysqli_close($con);
			echo json_encode($response,JSON_PRETTY_PRINT);
			
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>