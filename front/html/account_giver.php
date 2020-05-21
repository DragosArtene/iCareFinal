<?php 
  session_start();
    //set setion user_id
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
                <li class="firstLines"><a href="http://localhost:8012/iss/html/index_giver.php">Events</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/account_giver.php">Account</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/contact_ong.php">Contact</a></li>

            </ul>
        </div>
</div>

<br>
<br>


  <div class="main"  style="height: 600px;">
    <div align="center">
    <p class="sign2">Update Event</p>
    </div>
    <form class="form1" id="form">
    <input class="un " id="email" type="text" align="center" placeholder="Email">
    <input class="un " id="giverFirstName" type="text" align="center" placeholder="First Name">
      <input class="un " id="giverLastName" type="text" align="center" placeholder="Last Name">
      <input class="un " id="giverDescription" type="text" align="center" placeholder="Description">
      <input class="un " id="username" type="text" align="center" placeholder="username">
      <br>
      <div>
      <button class="submit" id="updategiver" align="center"><span>Submit</span></button>
      </div>
            
</form>
    </div>
     
</body>
<script>

$.getJSON({
        url: 'http://localhost:8080/iCare_war_exploded/givers/'+<?= $_SESSION['user_id']?>,
        success: function(_giver)
        {
            $('#email').val(_giver.email);
           $('#giverFirstName').val(_giver.giverFirstName);
           $('#giverLastName').val(_giver.giverLastName);
           $('#giverDescription').val(_giver.giverDescription);
           $('#username').val(_giver.username);
        }

    });

$("#updategiver").click(function(){
        let data = {
                    "email":$('#email').val(),
                    "giverFirstName":$('#giverFirstName').val(),
                    "giverLastName":$('#giverLastName').val(),
                    "giverDescription":$('#giverDescription').val(),
                    "username":$('#username').val()
                    };
                    
                $.ajax({
                    type: 'PUT',
                    url: 'http://localhost:8080/iCare_war_exploded/givers/'+<?= $_SESSION['user_id']?>,
                    contentType: 'application/json',
                    data: JSON.stringify(data), // access in body
                    success: function(){
                        window.location.replace("http://localhost:8012/iss/html/index_giver.php");
                    }
                });
                alert("Successfully updated!");
        });

    // When the user scrolls down 20px from the top of the document, show the button
        window.onscroll = function () { scrollFunction() };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("myBtn").style.display = "block";
        } else {
            document.getElementById("myBtn").style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0; // For Chrome, Safari and Opera 
        document.documentElement.scrollTop = 0; // For IE and Firefox
    }
        
    </script>
</html>