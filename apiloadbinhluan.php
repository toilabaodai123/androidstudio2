<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	
	$idphim=filter_input(INPUT_POST,"idphim");
	
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select * from binhluans where idphim='".$idphim."'";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['khachhang']=$row['khachhang'];
				$response[$i]['noidung']=$row['noidung'];
				$response[$i]['idphim']=$row['idphim'];
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