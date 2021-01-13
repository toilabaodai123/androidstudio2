<?php
	//ini_set('mssql.charset', 'UTF-8');
	
	$phim = filter_input(INPUT_POST,"phim");
	$dmy = filter_input(INPUT_POST,"dmy");
	$taikhoan = filter_input(INPUT_POST,"taikhoan");
	$rap = filter_input(INPUT_POST,"rap");
	$ghe = filter_input(INPUT_POST,"ghe");
	//$taikhoan_nhap=filter_input(INPUT_POST,"taikhoann");
	
	
	$dataxuatchieu="";
	$dataphim="";
	$datarap="";
	
	
	
	
	$response = array();
	$con=mysqli_connect("localhost","root","","laraone");
	if($con){
		$j=0;
		$idphim = "select *
				   from phims 
				   where tenphim = '".$phim."'";
		$result = mysqli_query($con , $idphim);
		foreach($result as $b){
			$dataphim = $b['id'];
			//echo "id phim la :".$dataphim;
		}
		
		$dmyxuatchieu = "select *
					     from xuatchieus
						 where dmy ='".$dmy."'";
		$result2 = mysqli_query($con , $dmyxuatchieu);
		foreach($result2 as $a)
		{
			$dataxuatchieu = $a['id'];
			//echo "id xuat chieu la ".$dataxuatchieu ;
		
		}
		$idrap = "select * 
				  from raps
				  where tenrap = '".$rap."'
				  ";
		$result3 = mysqli_query($con , $idrap);
		if($result3){
			foreach($result3 as $a)
		{
			$datarap = $a['id'];
			echo "id rap la ".$datarap ;
		
		}
		}
		else
			echo "không rạp";
			
			
		/*--------------------------------------------------------------------------*/
		$themve = "INSERT INTO ves (phim,khachhang,xuatchieu,rap) values(".$dataphim.",'".$taikhoan."',".$dataxuatchieu.",".$datarap.")";
		if(!mysqli_query($con , $themve))
		{
			echo "1";
		}
		else
		{
			echo "2";
		}
		
		$dsghe = array();
		
		$dsghe = (explode(" ",$ghe));
		
		foreach($dsghe as $a){
			$themthoigianghe = "INSERT INTO thoigianghes (thoigian,ghe,phim,rap) values(".$dataxuatchieu.",'".$a."',".$dataphim.",".$datarap.")";
			if(!mysqli_query($con , $themthoigianghe))
				{
					//echo "them thoi gian ghe khong thanh cong";
				}
				else
				{
					//echo "them thoi gian ghe thanh cong";
				}
				$dsve = "select * from ves";
				$resultdsve = mysqli_query($con , $dsve);
				$idve = 0;
				if(!$resultdsve)
					$idve=1;
				else
					foreach($resultdsve as $xx){$idve++;}
					
			$themchitietve = "INSERT INTO chitietves (idve,idphim,idghe,idrap) values(".$idve.",'".$dataphim."',".$a.",".$datarap.")";
			if(!mysqli_query($con , $themchitietve))
				{
					//echo "them chi tiet ve khong thanh cong";
				}
				else
				{
					//echo "them chi tiet ve thanh cong";
				}
			
		}

	}
	else{
		echo "Database connection failed!";
		
	}
	
?>