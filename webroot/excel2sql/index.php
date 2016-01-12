<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <!--<meta http-equiv=”refresh” content=”3;url=example.php”>-->
        <title>excel2sql</title>
        <script type="text/javascript">
            function validateExcel(){
                var filename = document.getElementById("xlsfile").value;
                var ext = filename.substring(filename.length-4, filename.length);
                if(ext === ".xls"){
                    return true;
                }
                else{
                    alert("You must select an Excel file(*.xls)!");
                    document.getElementById("reset1").reset();
                    return false;
                }
            }
        </script>
    </head>
    <body style="background-color: #cce8cf">
    <center>        
        <form id="filedetail" name="filedetail" action="file_upload.php" onsubmit="return validateExcel()"
              method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Shenzhen Giec Electronics Co., Ltd.</legend>
                <label for="xlsfile">Select excel file(*.xls): </label>
                <input type="file" id="xlsfile" name="xlsfile" ></input><br />
                <input type="submit" id="submit1" name="submit1" value="Upload" />
                <input type="reset" id="reset1" name="reset1" value="Reset" />
                </fieldset>
        </form>
    </center>
        <?php
        // put your code here
        ?>
    </body>
</html>
