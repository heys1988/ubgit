<?php
//session_start();
//header("Content-type: text/html; charset=utf-8");
require_once("http://localhost:8080/javabridge/java/Java.inc");
//require_once("../javabridge/META-INF/java/Java.inc");
//require_once("http://localhost:8080/JavaBridge/java/Java.inc");
//java_require("JavaExecelProcess.jar");
echo '<body style="background-color: #cce8cf">';

echo '<center>';
//$System = java("java.lang.System");
//echo $System->getProperties();
$excel_prcess = new java("javaexecelprocess.JavaExecelProcess");
//$ret = $excel_prcess->readExcel(getcwd()."/upload/"."邮箱地址.xls");
//$filename = getcwd()."/upload/".$_FILES["xlsfile"]["name"];
//$filename = getcwd()."/upload/".$_COOKIE["excelName"];
//echo $filename.'<br/>';
//$ret = $excel_prcess->readExcel($filename);
//echo $ret.'<br/>';
//$is_db = $excel_prcess->isDBFormat();
//if(false == $is_db){
//    echo 'The excel file does not fix database-table format.'.'<br/>';
//    echo '<a href=\'index.php\'>return to index</a>';
//    sleep(3);
//    echo '<script type="text/javascript">';
//    echo 'document.location="index.php"';
//    echo '</script>';
//}else if(true == $ret){
////    $_SESSION['excelCols'] = $excelCols;
//    $excelCols = $excel_prcess->getColumns();
//    setcookie("excelCols", $excelCols, time()+60);
////    echo '<script type="text/javascript">';
////    echo 'document.location="create_db_tbl.php"';
////    echo '</script>';
//}

echo 'the end.';
echo '</center>';
echo '</body>';
?> 