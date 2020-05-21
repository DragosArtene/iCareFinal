<?php 
  session_start();
 
?>

<html>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://kit.fontawesome.com/01bc3f5c0c.js" crossorigin="anonymous"></script>
  <script src="..\js\functions.js"></script>
  <link rel="stylesheet" href="..\css\EventDetailPage.css">
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
            <li class="firstLines"><a class="active" href="http://localhost:8012/iss/html/indexong.php">ONGs</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/index_administrator.php">Events</a></li>
                <li class="firstLines"><a href="http://localhost:8012/iss/html/contact.php">Contact</a></li>

        </ul>
    </div>
  </div>
<br>


  <div class="main" id="main">
            
                
    </div>
     
</body>

<script>

//loads events to the page
$.getJSON({
        url: 'http://localhost:8080/iCare_war_exploded/ongs/'+<?= $_SESSION['ong_id']?>,
        success: function(_ong)
        {
            $('#main').append(' <h1 class="sign">'+ _ong.username+' '+ +'</h1>    \
                                            <div class="line"></div>    \
                                            <p class="desc">'+ _ong.ongDescription +'</p>    \
                                            <p class="desc">Phone Number:'+ _ong.phoneNumber +'</p>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <br>    \
                                            <div align="center">  \
                                            <br>  \
                                            <button class="button1" type="submit" name="delete" id="delete"><span>Delete </span></button>   \
                                            <br>  \
                                            <br>  \
                                            </div>  \
                                            ');
            
        }
    });

$(document).on('click', '#delete',function(){
    var url_id = $(this).val();
    
    //delete event and redirect to index.php
    $.ajax({
            url: 'http://localhost:8080/iCare_war_exploded/ongs/'+ <?= $_SESSION['ong_id']?>,
            type: 'delete',
    success: function(result) {
        window.location.replace("http://localhost:8012/iss/html/index_administrator.php");
    }
            
});
});

</script>

</html>