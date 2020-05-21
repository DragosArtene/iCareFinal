<?php 
  session_start();
  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
   

    if (isset($_POST['edit'])){
        $_SESSION['ong_id'] = $_POST['edit'];
        header("location: updateong.php");
    }
}
?>

<html>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://kit.fontawesome.com/01bc3f5c0c.js" crossorigin="anonymous"></script>
  <script src="..\js\functions.js"></script>
  <link rel="stylesheet" href="..\css\contact.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
  <title>iCare</title>
</head>

<body>
  <div class="wrapper">
    <div class="navBar">
        <ul class="firstList">
            <li class="firstLines"><a class="active" href="#home">Home</a></li>
            <li class="firstLines"><a href="#events">Events</a></li>
            <li class="firstLines"><a href="#account">Account</a></li>
            <li class="firstLines"><a href="#contact">Contact</a></li>

        </ul>
    </div>
  </div>
<br>


  <div class="main" id="main"> 
    <img class="image" src="..\\img\\logo2.png"/>
    <h1 class="title">CONTACT US</h1>
    <h2 class="title2">Never stay ordinary</h2>
    <div class="text">
        <div class="small"></div><p class="firstText"> Born from the desire of a group of students to make the world a better place, <b>iCare</b> is the perfect solution for <b>N</b>on-<b>G</b>overnmental 
        <b>O</b>rganizations to get funded via donations or to get more popular with their volunteering activities.</p>
        <div class="small2"></div><p class="firstText">
        Find out more about the team and where <b>iCare</b> is going.</p>
    <p class="emailText">Email us at <a href="mailto:contact@iCare.com">contact@iCare.com</a>.</p>
    </div>

     
     
      

    </div>
     



</body>

</html>