<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body bgcolor = "#cce8cf">
    <center>
        
        <div>TODO write content</div>
       <?php
//            session_start();
//            echo $_SESSION['excelCols'];
            echo $_COOKIE["excelCols"];
            echo 'nothing';
        ?>
        
        <form id="dbprams" name="dbprams" action="" onsubmit="return validateNotNull()"
              method="post">
            <fieldset>
                <legend>Shenzhen Giec Electronics Co., Ltd. Database definition</legend>
                <label for="xlsfile">Select excel file(*.xls): </label>
                <input type="text" id="fields[]" name="fields[]" ></input><br />
                <input type="submit" id="submit1" name="submit1" value="Submit" />
                <input type="reset" id="reset1" name="reset1" value="Reset" />
                </fieldset>
        </form>
    </center>        
    </body>
</html>
