<?php

	//$datadmy="2020-12-31";
	$datadmy=filter_input(INPUT_POST,"dmy");
	$dataphim=filter_input(INPUT_POST,"phim");
	$datarap=filter_input(INPUT_POST,"rap");
	$dataghe="a";
	$abc=filter_input(INPUT_POST,"abc");
	
	
	//if($abc) echo $abc;
	
	//if($datadmy) echo 1;
	
	
	
	
	
	//$dataphim="afff";
	//$datarap="R?p1";

	$con=mysqli_connect("localhost","root","","laraone");
	if($con){

		$sql4="select thoigian,xuatchieus.dmy,ghes.tenghe,phims.tenphim,raps.tenrap
			   from thoigianghes
			   inner join xuatchieus on xuatchieus.id=thoigianghes.thoigian
			   inner join ghes on ghes.id=thoigianghes.ghe
			   inner join phims on phims.id=thoigianghes.phim
			   inner join raps on raps.id=thoigianghes.rap
			   "
			   ;

		$sql5="select * from ghes";
		$result4=mysqli_query($con,$sql4);
		$result5=mysqli_query($con,$sql5);
		
		
		if($result5){
			$i=0;
			while($row=mysqli_fetch_assoc($result5)){
				$responsee[$i]['id']=$row['id'];
				$responsee[$i]['tenghe']=$row['tenghe'];
				$i++;
			}
			
			//echo json_encode($responsee);s
		}
		
		
		if($result4){
			$i=0;
			while($row = mysqli_fetch_assoc($result4)){
				$response[$i]['dmy']=$row['dmy'];
				$response[$i]['tenghe']=$row['tenghe'];
				$response[$i]['tenphim']=$row['tenphim'];
				$response[$i]['tenrap']=$row['tenrap'];
				$i++;
			}
			//echo json_encode($response);
		}



		$i=0;
		foreach($responsee as $a){
			$dem=0;
			foreach($response as $b){
				if($b['dmy']==$datadmy && $b['tenghe']==$a['tenghe'] && $b['tenphim'] == $dataphim && $b['tenrap'] == $datarap){
					$dem++;
				}
			}
			if($dem==0){
				$responseex[$i]['id']=$a['id'];
				$responseex[$i]['tenghe']=$a['tenghe'];
				$i++;
			}

		}
		echo json_encode($responseex);
			



		

	}
	else{
		echo "Database connection failed!";
		
	}
?>