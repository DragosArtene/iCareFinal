<?php
    session_start();
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      if (isset($_POST['register'])){
        echo'hello';
          header("location: login.php");
      }
  }
?>

<html>

<head>
  <link rel="stylesheet" href="..\css\register.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>Register</title>
</head>

<body>
  <div class="main">
    <p class="sign" align="center">Register</p>
    <form class="form1">
      <input class="un " type="text" align="center" id="email" placeholder="Email" required>
      <input class="un " type="text" align="center" id="firstName" placeholder="First Name" required>
      <input class="un " type="text" align="center" id="lastName" placeholder="Last Name" required>
      <input class="un " type="text" align="center" id="username" placeholder="Username" required>
      <input class="pass" type="password" align="center" id="password" placeholder="Password" required>
      <input class="pass" type="password" align="center" id="confpassword" placeholder="Confirm Password" required>
      <br>
      <p id="phoneText">To create an ONG account add also a phone number.</p>
      <input class="un " type="text" align="center" id="phoneNumber" placeholder="Phone Number">
      <br>
      <div class="checkboxDiv">
           <label for="ong" id="checkboxText" align="center"> I am an ong</label>
           <input type="checkbox" id="ong" align="center"></div>
      <br>
      <form method="POST" action="register.php">
        <button class="submit" align="center" type="submit" id="register" name="register" value="register">Submit</button>
      </form>
    </form>
    </div>

</body>


<script>
$("#register").on('click',function(){
	if ($("#email").val() && $("#firstName").val() && $("#lastName").val() && $("#username").val() && $("#password").val())
	{
		if ($("#password").val() != $("#confpassword").val())
		{
			alert("Password is not the same! Try again!");
		}
		else{
			window.location.replace("http://localhost:8012/iss/html/login.php"); 
			alert("Register successful!");
		}
	}
  
});

// $("#submit").click(function(){
//     alert("here!");
//     if ($("#email").val() && $("#firstName").val() && $("#lastName").val() && $("#username").val() && $("#password").val())
//     {
//             if ($("#password").val() != $("#confpassword").val())
//             {
//             alert("Password is not the same! Try again!");
//             }
//             else{
//                     let data = {
//                                 "email": $("#email").val(),
//                                 "firstName": $("#firstName").val(),
//                                 "lastName": $("#lastName").val(),
//                                 "password": $("#password").val(),
//                                 "username": $("#username").val(),
//                                 "phoneNumber": $("#phoneNumber").val()
//                                 };
                
//                       alert(JSON.stringify(data));
//                       $.ajax({
//                         type: 'POST',
//                         url: 'http://localhost:8081/login/api/register',
//                         headers: data,
//                         dataType: 'application/json',
//                         contentType: 'application/json',
//                         success: window.location.replace("http://localhost:8012/iss/html/login.php");        
//                         });


//                       $.ajax({
//                                 type: 'POST',
//                                 url: 'http://localhost:8081/login/api/register',
//                                 data: JSON.stringify(data),
//                                 dataType: 'application/json',
//                                 contentType: 'application/json',
//                                 success: function()
//                                 {
//                                        if ($("#ong").prop("checked") == true)
//                                     {
//                                         alert("is ong");
//                                         let ong = {
//                                             "ongFirstName": $("#firstName").val(),
//                                             "ongLastName": $("#lastName").val(),
//                                             "ongDescription": "",
//                                             "username": $("#username").val(),
//                                             "email": $("#email").val(),
//                                             "password": $("#password").val(),
//                                             "phoneNumber": $("#phoneNumber").val()
//                                             };
//                                         $.ajax({
//                                             type: 'POST',
//                                             url: 'http://localhost:8080/iCare_war_exploded/ongs',
//                                             contentType: 'application/json',
//                                             data: JSON.stringify(ong), // access in body
//                                         });
//                                         $.getJSON({
//                                             url: 'http://localhost:8080/iCare_war_exploded/ongs/user/'+$("#username").val(),
//                                             success: funtion(_ong)
//                                             {
//                                                 $.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_ong.ongId),"user_type":"ONG"});
//                                             }
                                
//                                         });
//                                     }
//                                     if($("#ong").prop("checked") == false){
//                                         alert("is giver");
//                                         add a giver
//                                         let giver = {
//                                             "giverFirstName": $("#firstName").val(),
//                                             "giverLastName": $("#lastName").val(),
//                                             "giverDescription": "",
//                                             "username": $("#username").val(),
//                                             "email": $("#email").val(),
//                                             "password": $("#password").val()
//                                             };
//                                         $.ajax({
//                                             type: 'POST',
//                                             url: 'http://localhost:8080/iCare_war_exploded/givers',
//                                             contentType: 'application/json',
//                                             data: JSON.stringify(giver), // access in body
//                                         });
//                                         $.getJSON({
//                                             url: 'http://localhost:8080/iCare_war_exploded/givers/user/'+$("#username").val(),
//                                             success: funtion(_giver)
//                                             {
//                                                 $.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_giver.giverId),"user_type":"GIVER"});
//                                             }
                                
//                                         });
                                        
//                                     }
//                                 }
//                             });
//             }
//     }
// });


        
    </script>
</html>

</html>