<?php
//the url for down

    if ($url = $_POST['value']){
        $file = fopen("down.txt","w");
        fwrite($file,$url);
        fclose($file);}
    else{
        $value = file("down.txt");
        foreach($value as $data){
            echo $data;
        }
    }
?>