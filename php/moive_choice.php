<?php
//android from this putinto choice which moive to download
//writer:QiaoY<Traceurq@gmail.com>

    if ($value = $_POST['value']){
        $file = fopen("moive_list.txt","w");
        fwrite($file,$value);
        fclose($file);}
    elseif($_POST['passwd'] == '123456'){
        $datas = file("moive_list.txt");
        foreach($datas as $data){
	    echo $data;
	}
    }
    
?>