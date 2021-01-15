<?php

	$dataphim=filter_input(INPUT_POST,"idphim");
	$dataxuatchieu=filter_input(INPUT_POST,"xuatchieu");
	$dataghe=filter_input(INPUT_POST,"ghe");
	$tongtien=0;

	$con=mysqli_connect("localhost","root","","laraone");
	$response = array();
	if($con){
		$sql="select * from phims where tenphim = '".$dataphim."'";
		$result = mysqli_query($con , $sql);
		if($result){
			foreach($result as $a){
				//echo $a['giaphim'];
				$tongtien=$tongtien+(int)$a['giaphim'];
			}
			//echo "tien phim: ".$tongtien;
			
		}
		
		$sql2="select * from xuatchieus where dmy='".$dataxuatchieu."'";
		$result2=mysqli_query($con , $sql2);
		if($result2)
		{
			foreach($result2 as $b) {
			$tongtien=$tongtien+$b['giaxuatchieu'];}
			//echo "tien phim + xuat chieu: ".$tongtien;
		}
		
		
		/*
		$array = [1,2,3,4,5,6,7];
		foreach ($array as $x)
		{
			echo $x;
		}*/
		
		
		$dsghe =array();
		$dsghe = (explode(" ",$dataghe));
		foreach ($dsghe as $x)
		{
			$sql3="select ghes.id,loaighes.giaghe
			   from ghes
			   inner join loaighes on loaighes.id=ghes.loaighe
			   where ghes.id=".$x."
			";
			$result3=mysqli_query($con , $sql3);
			foreach($result3 as $c){
				$tongtien=$tongtien+$c['giaghe'];
			}

			
		}
		echo $tongtien;
		
		
		/*
		$sql3="select ghes.id,loaighes.giaghe
			   from ghes
			   inner join loaighes on loaighes.id=ghes.loaighe
			   where ghes.id=1
			";
		$result3=mysqli_query($con , $sql3);
		if($result3){
			foreach($result3 as $c){
				echo $c['giaghe'];
				$tongtien=$tongtien+$c['giaghe'];
				//echo "tien phim + xuat chieu + gia ghe: ".$tongtien;
			}
			
		}*/
		
	}
	else{
		echo "Database connection failed!";
		
	}
?>