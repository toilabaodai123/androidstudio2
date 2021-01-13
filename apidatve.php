<?php
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select * from khachhangs";
		$result = mysqli_query($con , $sql);
			
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>