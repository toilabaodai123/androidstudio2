<?php
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		
		$sql="select loaiphims.tenloaiphim,phims.tenloaiphim,phims.id,phims.tenphim,phims.noidungphim,loaiphims.tenloaiphim
			  from phims
			  inner join loaiphims on loaiphims.id=phims.tenloaiphim
						   ";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			//echo "b";
			//foreach($result as $a) {echo $a['tenloaiphim'];}
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['id']=$row['id'];
				$response[$i]['tenphim']=$row['tenphim'];
				$response[$i]['noidungphim']=$row['noidungphim'];
				$response[$i]['tenloaiphim']=$row['tenloaiphim'];
				$i++;
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
			mysqli_close($con);
		}
		else{
			echo "a";
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>