<?php
session_start();
header("Content-type: text/html; charset=utf-8");
require_once("http://localhost:8080/javabridge/java/Java.inc");
echo '<body style="background-color: #cce8cf">';

echo '<center>';
echo 'file type:'.$_FILES["xlsfile"]["type"].'<br/>';
echo 'file size:'.$_FILES["xlsfile"]["size"].'<br/>';
if ($_FILES["xlsfile"]["type"] == "application/vnd.ms-excel")
{
    if ($_FILES["xlsfile"]["error"] > 0)
    {
        echo "Error: " . $_FILES["xlsfile"]["error"] . "<br />";
    }
    else
    {
        echo "Upload: " . $_FILES["xlsfile"]["name"] . "<br />";
        echo "Stored in: " . $_FILES["xlsfile"]["tmp_name"]. "<br />";
        if (file_exists("upload/" . $_FILES["xlsfile"]["name"]))
        {
            echo $_FILES["xlsfile"]["name"] . " already exists. ". "<br />";
        }
        else if(is_uploaded_file($_FILES["xlsfile"]["tmp_name"]))
        {
            move_uploaded_file($_FILES["xlsfile"]["tmp_name"],
                "upload/" . $_FILES["xlsfile"]["name"]);
            echo "Stored in: " . "upload/" . $_FILES["xlsfile"]["name"];
            
//            sleep(3);
//            include 'file_process.php';
            
        }
        
//        setcookie("excelName", $_FILES["xlsfile"]["name"], time()+3600);
//        echo '<script type="text/javascript">';
//        echo 'document.location="file_process.php"';
//        echo '</script>';
//        echo "<meta http-equiv=\"refresh\" content=\"3;url=file_process.php\">";
        $excel_prcess = new java("javaexecelprocess.JavaExecelProcess");
//$ret = $excel_prcess->readExcel(getcwd()."/upload/"."邮箱地址.xls");
$filename = getcwd()."/upload/".$_FILES["xlsfile"]["name"];
//$filename2 = utf8_encode($filename);
//$filename = getcwd()."/upload/".$_COOKIE["excelName"];
//echo $filename.'<br/>';
$ret = $excel_prcess->readExcel($filename);
$ret2 = java_values($ret);
//echo $ret2.'<br/>';
//echo java_inspect($ret).'<br/>';
$is_db = $excel_prcess->isDBFormat();
$is_db2 = java_values($is_db);
//echo boolval($is_db2).'<br/>';
//echo java_inspect($is_db).'<br/>';
if(false == $is_db2){
    echo 'The excel file does not fix database-table format.'.'<br/>';
    echo '<a href=\'index.php\'>return to index</a>';
    echo "<meta http-equiv=\"refresh\" content=\"3;url=index.php\">";
//    sleep(3);
//    echo '<script type="text/javascript">';
//    echo 'document.location="index.php"';
//    echo '</script>';
}else{
    $_SESSION['excelCols'] = $excelCols;
    $excelCols = $excel_prcess->getColumns();
    $excelCols2 = java_values($excelCols);
    if(!isset($_COOKIE["excelCols"])){
        setcookie("excelCols", $excelCols2, time()+3600);
    }
    if(!isset($_COOKIE["excelName"])){
        
//        setcookie("excelName", $filename, time()+3600);
    }
    if(!isset($_SESSION['excelName'])){
        $_SESSION['excelName'] = $filename;
    }
    echo "<meta http-equiv=\"refresh\" content=\"3;url=create_db_tbl.php\">";
    echo '<script type="text/javascript">';
    echo 'document.location="create_db_tbl.php"';
    echo '</script>';
}
    }
}
else
{
    echo "Invalid file";
}
echo '<br />';
//echo '<a href=\'index.php\'>return</a>';

echo '</center>';
echo '</body>';
?>