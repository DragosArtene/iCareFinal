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
		<input class="un " id="email" type="text" align="center" placeholder="Name">
		<input class="un " id="ongFirstName" type="text" align="center" placeholder="Name">
		<input class="un " id="ongLastName" type="text" align="center" placeholder="Name">
		<input class="un " id="ongDescription" type="text" align="center" placeholder="Name">
        <input class="un " id="username" type="text" align="center" placeholder="Name">
       <input class="un " id="phoneNumber" type="text" align="center" placeholder="Description">
      <br>
      <div>
      <button class="submit" id="updateong" align="center"><span>Submit</span></button>
      </div>
            
</form>
    </div>
     
</body>
<script>

$.getJSON({
        url: 'http://localhost:8080/iCare_war_exploded/ongs/'+<?= $_SESSION['user_id']?>,
        success: function(_ong)
        {
           $('#ongName').val(_ong.ongName);
           $('#ongDescription').val(_ong.ongDescription);
		   
		   $("#email").val(_ong.email);
		   $("#ongFirstName").val(_ong.ongFirstName);
		   $("#ongLastName").val(_ong.ongLastName),
		   $("#ongDescription").val(_ong.ongDescription),
		   $("#username").val(_ong.username),
		   $("#phoneNumber").val(_ong.phoneNumber)
        }

    });

$("#updateong").click(function(){

        let data = {
						"email": $("#email").val(),
						"ongFirstName": $("#ongFirstName").val(),
						"ongLastName": $("#ongLastName").val(),
						"ongDescription":$("#ongDescription").val(),
						"username": $("#username").val(),
						"phoneNumber": $("#phoneNumber").val()
                                };
                    
                $.ajax({
                    type: 'PUT',
                    url: 'http://localhost:8080/iCare_war_exploded/ongs/'+<?= $_SESSION['user_id']?>,
                    contentType: 'application/json',
                    data: JSON.stringify(data), // access in body
                    success: function(){
                        window.location.replace("http://localhost:8012/iss/html/index.php");
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