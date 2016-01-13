<?php
header("Content-type: text/html; charset=utf-8");
echo '<body style="background-color: #cce8cf">';
echo '</body>';
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
            echo $_FILES["xlsfile"]["name"] . " already exists. ";
        }
        else if(is_uploaded_file($_FILES["xlsfile"]["tmp_name"]))
        {
            move_uploaded_file($_FILES["xlsfile"]["tmp_name"],
                "upload/" . $_FILES["xlsfile"]["name"]);
            echo "Stored in: " . "upload/" . $_FILES["xlsfile"]["name"];
            sleep(3);
            include 'file_process.php';
        }
    }
}
else
{
    echo "Invalid file";
}
echo '<br />';
echo '<a href=\'index.php\'>return</a>';
echo '</center>';
?>