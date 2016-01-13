<?php
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
$ret = $excel_prcess->processExcel();
echo $ret;
echo '</center>';
?> 