<?php
//android from this to get/putinto moive's name
//writer:QiaoY<Traceurq@gmail.com>

    @$moive = $_POST['moive'];
    @$passwd = $_POST['passwd'];
    @$time = date("H:i:s",strtotime("+5 minute"));
    if($moive && $passwd){
        if ($passwd == '123456'){
            $file = fopen("moive.txt","w");
            fwrite($file,$time);
	    fwrite($file,",");
	    fwrite($file,$moive);
	    fclose($file);}
		}
        
    $datas = file("moive.txt");
    foreach($datas as $data){
	list($timeout,$name)=explode(",",$data);
	}
    if (date("H:i:s") < $timeout){
        echo $name;}
?>