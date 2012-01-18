<?php
//android from this to get/putinto moive's name
//writer:QiaoY<Traceurq@gmail.com>

    @$movie = $_POST['movie'];
    @$passwd = $_POST['passwd'];
    if($movie && $passwd){
        if ($passwd == 'P@ssw0rd_m'){
            $file = fopen("movie.txt","w");
	    fwrite($file,$movie);
	    fclose($file);}
		}
        
    $datas = file("movie.txt");
    foreach($datas as $data){
	echo urldecode($data);
	}
?>