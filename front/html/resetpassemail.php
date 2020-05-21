<?php 
  session_start();

?>

<html>

<head>
  <link rel="stylesheet" href="..\css\login.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>Sign in</title>
</head>

<body>
  <div class="main">
    <p class="sign" align="center">Reset Password</p>
    <form class="form1" method="POST" action="resetpassemail.php">
      <input class="un " type="text" align="center" id="email" placeholder="Email">
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <a class="submit" name = "submit" align="center" id = "submit" >Submit</a>
    </form>   
                
    </div>
     
</body>


<script>
$("#submit").click(function(){

  $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8080/login/api/forgot-password?email='+$("#email").val()
                        });
                    
        window.location.replace("http://localhost:8012/iss/html/resetpass.php");
}
        

</script>

</html>