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
    <p class="sign" align="center">Sign in</p>
    <form class="form1" method="POST" action="login.php">
      <input class="un " type="text" align="center" id="username" placeholder="username">
      <input class="pass" type="password" align="center" id="password" placeholder="Password">
      <br>
      <div class="checkboxDiv">
         <label for="ong" id="checkboxText" align="center"> I am an ong</label>
         <input type="checkbox" id="ong" name="ong" value="ong" align="center">
      </div>
      <a class="submit" name = "submit" align="center" id = "submit" >Sign in</a>
      <p class="forgot" align="center">Not registered yet? <a align="center" href="http://localhost:8012/iss/html/register.php">Create an account.
      <br><br><a align="center" href="resetpassemail.php">Forgot password.</p>
</form>
                
    </div>
     
</body>


<script>
$("#submit").click(function(){		
	if ($("#username").val() && $("#password").val())
   {
	   if ($("#username").val() == "admin" && $("#password").val() == "admin")
	   {
		   window.location.replace("http://localhost:8012/iss/html/index_administrator.php");
	   }
	   else{
            let data = {
                        "password": $("#password").val(),
                        "username": $("#username").val()
                        };
           
             $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8081/login/api/auth',
                        data: JSON.stringify(data),
                        contentType: 'application/json',
                        success: function(){
                            
                            if ($("#ong").prop("checked") == true)
                            {
                                
                                $.post(
									"http://localhost:8012/iss/html/index_ong.php",
									{
										logged_in: "true",
										username:$("#username").val(),
										user_type:"ONG"
									});
                                window.location.replace("http://localhost:8012/iss/html/index_ong.php");
                            }
                            else {
                                
								/* let giver = {
									logged_in: "true",
									username:$("#username").val(),
									user_type:"GIVER"
								};
                                $.post("http://localhost:8012/iss/html/index.php",giver,funtion(){alert("YEEEY");}; */
								var islogged="true";
								var giver = 'logged_in='+islogged;
								$.ajax({
									type:'POST',
									url:'index.php',
									data: giver,
									success: function(){
										
									}
								});
								
								window.location.replace("http://localhost:8012/iss/html/index_giver.php");
						}
					}
			 });
	   }                     
    }
});


/* $("#submit").click(function(){
    alert("Hei");
    if ($("#username").val() && $("#password").val())
   {
            let data = {
                        "password": $("#password").val(),
                        "username": $("#username").val()
                        };
            alert(JSON.stringify(data));
             $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8081/login/api/auth',
                        data: JSON.stringify(data),
                        contentType: 'application/json',
                        success: function(){
                            alert("e sucess");
                            if ($("#ong").prop("checked") == true)
                            {
                                alert("ong");
                                $.ajax({
                                    type: 'POST',
                                    url: 'http://localhost:8080/iCare_war_exploded/ongs/user/'+$("#username").val(),
                                    sucess: function(_ong)
                                    {
                                        alert(_ong);
                                        $.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_ong.ongId),"user_type":"ONG"});
                                        window.location.replace("http://localhost:8012/iss/html/index.php");
                                    }
                        
                                });
                            }
                            else{
                                alert("giver");
                                $.ajax({
                                    type: 'POST',
                                    url: 'http://localhost:8080/iCare_war_exploded/givers/user/'+$("#username").val(),
                                    sucess: function(_giver)
                                    {
                                        alert("here2");
                                        //$.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_giver.giverId),"user_type":"GIVER"});
                                        window.location.replace("http://localhost:8012/iss/html/index.php");
                                    }
                        
                                });
                                
                            }
                        }
             }); */
                    // $.ajax({
                    //     type: 'POST',
                    //     url: 'http://localhost:8081/login/api/auth',
                    //     data: JSON.stringify(data)
                    //     contentType: 'application/json',
                    //     complete: function(){
                    //           alert("hello");
                    //     //   if ($("#ong"). prop("checked") == true)
                    //     //     {
                    //     //         $.getJSON({
                    //     //             url: 'http://localhost:8080/iCare_war_exploded/ongs/user/'+$("#username").val(),
                    //     //             sucess: function(_ong)
                    //     //             {
                    //     //                 $.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_ong.ongId),"user_type":"ONG"});
                    //     //                 window.location.replace("http://localhost:8012/iss/html/index.php");
                    //     //             }
                        
                    //     //         });
                    //     //     }
                    //     //     else{
                    //     //         $.getJSON({
                    //     //             url: 'http://localhost:8080/iCare_war_exploded/givers/user/'+$("#username").val(),
                    //     //             sucess: function(_giver)
                    //     //             {
                    //     //                 $.post("http://localhost:8012/iss/html/index.php",{"logged_in": "true", "username":$("#username").val(),"user_id":String(_giver.giverId),"user_type":"GIVER"});
                    //     //                 window.location.replace("http://localhost:8012/iss/html/index.php");
                    //     //             }
                        
                    //     //         });
                                
                    //     //     }

                    //     }
 
                      
                    // });
//        }
  //  });
</script>

</html>