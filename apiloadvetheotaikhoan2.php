<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$taikhoan = "dai";filter_input(INPUT_POST,"taikhoan");
	
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select ves.phim , phims.tenphim
			  from ves
			  inner join phims on phims.id=ves.phim
			  where khachhang='".$taikhoan."' 
			  ";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['tenphim']=$row['tenphim'];
				//$response[$i]['created_at']=$row['created_at'];
				$response[$i]['thoigiandat']="Thoi gian dat";
				$i++;
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>