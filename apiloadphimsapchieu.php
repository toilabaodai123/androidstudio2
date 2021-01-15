<?php

	//$dataphim=filter_input(INPUT_POST,"tenphim");


	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select *
		      from phims
			  where trangthaichieu=2
					";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['tenphim']=$row['tenphim'];
				$response[$i]['tenloaiphim']=$row['tenloaiphim'];
				$response[$i]['noidungphim']=$row['noidungphim'];
				$i++;
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
			mysqli_close($con);
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>