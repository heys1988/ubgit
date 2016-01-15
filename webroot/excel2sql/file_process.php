<?php
session_start();
header("Content-type: text/html; charset=utf-8");
require_once("http://localhost:8080/javabridge/java/Java.inc");
//require_once("../javabridge/META-INF/java/Java.inc");
//require_once("http://localhost:8080/JavaBridge/java/Java.inc");
//java_require("JavaExecelProcess.jar");
echo '<body style="background-color: #cce8cf">';
echo '</body>';
echo '<center>';
//$System = java("java.lang.System");
//echo $System->getProperties();
$s="";
$n = 20;
for($i=0; $i<$n; $i++) {
  $s.=$i;
}
$str = new java("java.lang.String", $s);
echo '<br/>'.$str.'<br/>';
$excel_prcess = new java("javaexecelprocess.JavaExecelProcess");
$ret = $excel_prcess->readExcel("邮箱地址.xls");
echo $ret.'<br/>';
$ret = $excel_prcess->getRows();
echo $ret.'<br/>';
$excelCols = $excel_prcess->getColumns();
echo $ret.'<br/>';
$ret = $excel_prcess->isDBFormat();
echo $ret.'<br/>';

if(true == $ret){
//    $_SESSION['excelCols'] = $excelCols;
    setcookie("excelCols", $excelCols, time()+3600);
    echo '<script type="text/javascript">';
    echo 'document.location="create_db_tbl.php"';
    echo '</script>';
}

echo 'the end.';
echo '</center>';
?> 