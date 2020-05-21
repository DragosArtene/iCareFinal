<?php
    session_start();

?>

<html>

<head>
  <link rel="stylesheet" href="..\css\register.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <script src="..\js\functions.js"></script>
    <link rel="stylesheet" href="..\css\main_style.css">
  <title>Create Event</title>
</head>

<body>

<div class="wrapper">
        <div class="navBar">
            <ul class="firstList">
                <li class="firstLines"><a href="http://localhost:8012/iss/html/index_ong.php">Events</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/account_ong.php">Account</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/contact_ong.php">Contact</a></li>

            </ul>
        </div>
</div>

<br>
<br>


  <div class="main" style="height: 600px;" >
  <div align="center">
    <p class="sign2">Add Event</p>
    </div>
   <form class="form1">

        <br>
        <br>
      <input class="un " id="eventTitle" type="text" align="center" placeholder="Title">
      <input class="un " id="eventDescription" type="text" align="center" placeholder="Description">
      <input class="un " id="eventDateBeginning" type="datetime-local" align="center" placeholder="Start Date" onfocus="(this.type='date')" onblur="(this.type='text')">
      <input class="un " id="eventDateEnding" type="datetime-local" align="center" placeholder="End Date" onfocus="(this.type='date')" onblur="(this.type='text')">
      <br>
      <select class="un2 " id="eventType">
      <option id="options">Donate</option>
      <option id="options">Volunteer</option>
      </select>
      <div class="updateDiv" align="center">
        <!-- <input type="file" class="custom-file-input" name="file"> -->
        <input id="myFile" name="file" type="file" style="position:absolute;left:-10000px;top:-10000px;">
        <button class="uploadFile" onclick="$('#myFile').click();">Upload</button>
        </div>
      <div align="center">
      <button class="submitEvent" id="addevent" align="center"><span>Submit</span></button>
       </div>

</form>
    </div>
     
</body>
<script>


$('#addevent').on('click',function(){
    var datetime_beginning = $("#eventDateBeginning").val();
    var datetime_ending = $("#eventDateBeginning").val();
        let data = {
                    "ongId":"1",
                    "eventTitle":$("#eventTitle").val(),
                    "eventType":$("#eventType option:selected").text(),
                    "eventDescription":$("#eventDescription").val(),
                    "eventDateBeginning":$("#eventDateBeginning").val(),
                    "eventDateEnding":$("#eventDateBeginning").val()
                    };
 
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/iCare_war_exploded/events',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(){
                         window.location.replace("http://localhost:8012/iss/html/index_giver.php");
                     }
        });
		
                
});
        
    </script>
</html>