<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Create DB table</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{
                background-color:#cce8cf;
                text-align:center;
            }
            #dbprams{
                text-align: right;
                width:50%;
                padding: 10px;
                
                /*border: 1px solid black;*/
            }
            .fieldset1{
                text-align:left;
            }
        </style>
    </head>
    <body>
    <!--<center>-->
        <fieldset id="fieldset1" class="fieldset1">
            <legend>Shenzhen Giec Electronics Co., Ltd. Database definition</legend>
            <form id="dbprams" name="dbprams" action="create_table.php" onsubmit=""
              method="post" >
            <!--<input type="text" id="xlsfile" name="xlsfile" value="xlsfile"--> 
                   <!--autofocus="true" onfocus="this.select()"></input><br />-->
                <?php
                    echo "<label for=\"tblname\">table name: </label>";
                    echo "<input type=\"text\" id=\"tblname\" name=\"tblname\" value=\"tblname\" "
                    . "autofocus=\"true\" onfocus=\"this.select()\"/><br />";
                    for($i = 1; $i <= $_COOKIE["excelCols"]; $i++){
                        echo "<label for=\"field$i\">field$i: </label>";
                        echo "<input type=\"text\" id=\"field$i\" name=\"field$i\" value=\"field$i\" "
                                . "onfocus=\"this.select()\"/><br />";
 
                    }
//                    echo getcwd() . "<br/>"; 
                    
                    
                ?>
                <input type="submit" id="submit1" name="submit1" value="Submit" />
                <input type="reset" id="reset1" name="reset1" value="Reset" />
                
        </form>
        </fieldset>
    <!--</center>-->        
    </body>
</html>
