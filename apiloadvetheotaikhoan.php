<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$taikhoan = "dai";filter_input(INPUT_POST,"taikhoan");
	
	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select phim,xuatchieu
			  from ves
			  where khachhang='".$taikhoan."' 
			  ";
		$result = mysqli_query($con , $sql);
		if($result){
			$i=0;
			while($row=mysqli_fetch_assoc($result)){
				$response[$i]['tenphim']=$row['phim'];
				/*$timdmy = "select dmy from xuatchieus where id='".$row['xuatchieu']."'";
				$ketquatim = mysqli_query($con , $timdmy);
				foreach($ketquatim as $a)
				{
				echo $a['dmy'];
				}*/
				$response[$i]['thoigiandat']="Thoi gian dat";
				$i++;
			}
			echo json_encode($response,JSON_PRETTY_PRINT);
		}
		else
		{
			echo "???";
		}
	}
	else{
		echo "Database connection failed!";
		
	}
?>