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


  <div class="main"  style="height: 600px;">
    <div align="center">
    <p class="sign">Update Event</p>
    </div>
    <form class="form1" id="form">
      <input class="un " id="eventTitle" type="text" align="center" placeholder="Title">
      <select class="un " id="eventType">
      <option>donate</option>
      <option>volunteering</option>
      </select>
      <input class="un " id="eventDescription" type="text" align="center" placeholder="Description">
      <input class="un " id="eventDateBeginning" type="datetime-local" align="center">
      <input class="un " id="eventDateEnding" type="datetime-local" align="center">
      <br>
      <div>
      <button class="submit" id="updateevent" align="center"><span>Submit</span></button>
      </div>
            
</form>
    </div>
     
</body>
<script>

$.getJSON({
        url: 'http://localhost:8080/iCare_war_exploded/events/'+<?= $_SESSION['event_id']?>,
        success: function(_event)
        {
           $('#eventTitle').val(_event.eventTitle);
           $("#eventType").val(_event.eventType);
           $('#eventDescription').val(_event.eventDescription);
           var datetime_beginning = _event.eventDateBeginning[0] + '-'+ _event.eventDateBeginning[1] + '-'+ _event.eventDateBeginning[2] + 'T'+ _event.eventDateBeginning[3] + ':'+ _event.eventDateBeginning[4];
           $('#eventDateBeginning').val(daretime_beginning);
           var datetime_ending = _event.eventDateEnding[0] + '-'+ _event.eventDateEnding[1] + '-'+ _event.eventDateEnding[2] + 'T'+ _event.eventDateEnding[3] + ':'+ _event.eventDateEnding[4];
           $('#eventDateEnding').val(datetime_ending);
        }

    });

$("#updateevent").click(function(){
    var datetime_beginning = $("#eventDateBeginning").val();
    var datetime_ending = $("#eventDateBeginning").val();
        let data = {
                    "eventTitle":$("#eventTitle").val(),
                    "eventType":$("#eventType option:selected").text(),
                    "eventDescription":$("#eventDescription").val(),
                    "eventDateBeginning":$("#eventDateBeginning").val(),
                    "eventDateEnding":$("#eventDateBeginning").val()
                    };
                    
                $.ajax({
                    type: 'PUT',
                    url: 'http://localhost:8080/iCare_war_exploded/events/'+<?= $_SESSION['event_id']?>,
                    contentType: 'application/json',
                    data: JSON.stringify(data), // access in body
                    success: window.location.replace("http://localhost:8012/iss/html/index.php")
                });
                alert("Successfully updated!");
        });

    // // When the user scrolls down 20px from the top of the document, show the button
    //     window.onscroll = function () { scrollFunction() };

    // function scrollFunction() {
    //     if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
    //         document.getElementById("myBtn").style.display = "block";
    //     } else {
    //         document.getElementById("myBtn").style.display = "none";
    //     }
    // }

    // // When the user clicks on the button, scroll to the top of the document
    // function topFunction() {
    //     document.body.scrollTop = 0; // For Chrome, Safari and Opera 
    //     document.documentElement.scrollTop = 0; // For IE and Firefox
    // }
        
    </script>
</html>