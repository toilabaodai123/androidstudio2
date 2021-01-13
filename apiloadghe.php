<?php
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select * from ghes";
		
						
		$result = mysqli_query($con , $sql);

		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['id']=$row['id'];
				$response[$i]['tenghe']=$row['tenghe'];
				$i++;
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
		}
	}
	else{
		echo "Database connection failed!";
		
	}


	
	if($result3){
		while($row=mysqli_fetch_assoc($result3)){
			$response3['dmy']=$row['dmy'];
		}
		echo $response3['dmy'];
		
		
		
		
	}
?>