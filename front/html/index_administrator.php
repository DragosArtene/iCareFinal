<?php
    session_start();
	
	if (isset($_POST["logged_in"]) == "true"){
		header("location: login.php");
	}
     $_SESSION["logged_in"] = "true";
	 $_SESSION["username"] = "admin";
     $_SESSION["user_id"] = 3;
     $_SESSION["user_type"] = "ADMINISTRATOR";
	 
	 $_POST["logged_in"] = "true";
	 $_POST["username"] = "admin";
     $_POST["user_id"] = 3;
     $_POST["user_type"] = "ADMINISTRATOR";


    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['detail'])){
            $_SESSION['event_id'] = $_POST['detail'];
            header("location: eventdetail_administrator.php");
        }

        if (isset($_POST['add'])){
           
           
            header("location: addevent.php");
        }
    }
?>

<!DOCTYPE html>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/01bc3f5c0c.js" crossorigin="anonymous"></script>
    <script src="..\js\functions.js"></script>
    <link rel="stylesheet" href="..\css\main_style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



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
        <div class="filterDiv">
        <!-- <label class="sign" for="filter">Filter events</label> -->
        <select id="filter" >
            <option value="all">all</option>
            <option value="donate">donate</option>
            <option value="volunteering">volunteering</option>
        </select>
        <button class="submit" id="filter-button"> Go </button></div>
        <br>
        <?php
            if ($_SESSION["user_type"]=="ONG"){
                echo '<form method="POST" action="index_administrator.php">';
                echo '<button class="submit2" type="submit" name="add"><span>Add Event </span></button>';
                echo '</form>';
            }
        ?>
    <div class="eventWrapper" id="eventWrapper">
    </div>

    <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fas fa-arrow-up"></i></button>
    <script>

    //loads events to the page
    $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/iCare_war_exploded/events',
            success: function(_events)
            {
                $.each(_events,function(i,_event){
                    $('#eventWrapper').append(' <div class = "art">   \
                                                <img class="imgEvent" src="..\\uploads\\e'+_event.eventId+'.jpg" />  \
                                                <h1 class="eventHeader">' + _event.eventTitle + '</h1>    \
                                                <p class="eventDescr">' + _event.eventType + '</p> \
                                                <form method="POST" action="index_administrator.php">  \
                                                <button class="button" type="submit" name="detail" value="'+_event.eventId+'"><span>Details </span></button>   \
                                                </form> \
                                                </div>  \
                                                ');
                });
                
            }
        });
    
    $("#filter-button").click(function(){
        $('#eventWrapper').empty();
        var filter_val = $('#filter').find(':selected').text();
        if (filter_val == "all") filter_val="";
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/iCare_war_exploded/events?categories='+filter_val,
            success: function(_events)
            {
                $.each(_events,function(i,_event){
                    $('#eventWrapper').append(' <div class = "art">   \
                                                <img class="imgEvent" src="..\\uploads\\e'+_event.eventId+'.jpg" />  \
                                                <h1 class="eventHeader">' + _event.eventTitle + '</h1>    \
                                                <p class="eventDescr">' + _event.eventType + '</p> \
                                                <form method="POST" action="index_administrator.php">  \
                                                <button class="button" type="submit" name="detail" value="'+_event.eventId+'"><span>Details </span></button>   \
                                                </form> \
                                                </div>  \
                                                ');
                });
                
            }
        });
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
</body>

</html>