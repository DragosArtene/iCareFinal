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
                <li class="firstLines"><a href="http://localhost:8012/iss/html/index_giver.php">Events</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/account_giver.php">Account</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/contact_giver.php">Contact</a></li>

            </ul>
        </div>
</div>

<br>
<br>


  <div class="main" style="height: 600px;">
    <p class="sign" align="center">Event Payment</p>
    <form class="form1">
      <input class="un " id="cardNumber" type="text" align="center" placeholder="Card Number">
      <input class="un " id="cardName" type="text" align="center" placeholder="Card Name">
      <input class="un " id="keyCode" type="number" align="center" placeholder="Key Code">
      <input class="un " id="payedSum" type="number" align="center" placeholder="Sum">
      <input class="un " id="expMonth" type="text" align="center" placeholder="Expiration Month">
      <input class="un " id="expYear" type="text" align="center" placeholder="Expiration Year">
      
      <br>
      <div>
      <button class="submit"  id="donateevent"><span>Submit</span></button>
      </div>
            
</form>
    </div>
     
</body>
<script>


$('#donateevent').click(function(){
    var expDate = $("#expMonth").val()+"/"+$("#expYear").val();
        let data = {
                    "cardNumber":$("#cardNumber").val(),
                    "cardName":$("#cardName").val(),
                    "keyCode":parseInt($("#keyCode").val()),
                    "payedSum":parseInt($("#payedSum").val()),
                    "expDate":expDate
                    };
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/iCare_war_exploded/payments',
                    contentType: 'application/json',
                    data: JSON.stringify(data), // access in body
                    success: function(){
                        alert("Transaction successful!"); 
                        window.location.replace("http://localhost:8012/iss/html/index_giver.php");
                    }
                });
                alert("Transaction submited!"); 
    
    });
        
    </script>
</html>