<?php 
  session_start();
  
  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
   

    if (isset($_POST['edit'])){
        $_SESSION['event_id'] = $_POST['edit'];
        header("location: updateevent.php");
    }

    if (isset($_POST['donate'])){
      $_SESSION['event_id'] = $_POST['edit'];
      header("location: paymentevent.php");
  }

  if (isset($_POST['participate'])){
    header("location: index.php");
}
}
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
        url: 'http://localhost:8080/iCare_war_exploded/events/'+<?= $_SESSION['event_id']?>,
        success: function(_event)
        {
			 $('#main').append(' <h1 class="sign">'+ _event.eventTitle +'</h1>    \
                                  <div class="line"></div>    \
                                  <h2 class="sign">'+ _event.eventType +'</h2>    \
                                  <p class="desc">Start: '+ _event.eventDateBeginning[0] + '-'+ _event.eventDateBeginning[1] + '-'+ _event.eventDateBeginning[2] + '    |    '+ _event.eventDateBeginning[3] + ':'+ _event.eventDateBeginning[4] +'</p>   \
                                            <p class="desc">End: '+  _event.eventDateEnding[0] + '-'+ _event.eventDateEnding[1] + '-'+ _event.eventDateEnding[2] + '    |    '+ _event.eventDateEnding[3] + ':'+ _event.eventDateEnding[4] +'</p>   \
                                            <br>    \
                                            <p class="desc">'+ _event.eventDescription +'</p>    \
                                            \
                                            <div align="center">  \
                                            <br>  \
                                            <button class="button1" align="center" type="submit" name="delete" id="delete"><span>Delete </span></button>   \
                                            <br>  \
                                            <br>  \
                                            <form method="POST" action="eventdetail_ong.php">  \
                                            <br>  \
                                            <br>  \
                                            </form> \
                                            </div>  \
                                            ');
            
          
       
            
        }
    });

$(document).on('click', '#delete',function(){
    var url_id = $(this).val();
    
    //delete event and redirect to index.php
    $.ajax({
            url: 'http://localhost:8080/iCare_war_exploded/events/'+ <?= $_SESSION['event_id']?>,
            type: 'delete',
    success: function(result) {
        window.location.replace("http://localhost:8012/iss/html/index_administrator.php");
    }
            
});
});

</script>

</html>