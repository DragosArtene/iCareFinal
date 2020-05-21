<?php
    session_start();
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['detail'])){
            echo 'detail';
            $_SESSION['ong_id'] = $_POST['detail'];
            header("location: ongdetail.php");
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
    <div class="eventWrapper" id="eventWrapper">
    </div>

    <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fas fa-arrow-up"></i></button>
    <script>

    //loads events to the page
    $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/iCare_war_exploded/ongs',
            success: function(_ongs)
            {
                $.each(_ongs,function(i,_ong){
                    $('#eventWrapper').append(' <div class = "art">   \
                                                <div>   \
                                                <h1>' + _ong.username + '</h1>    \
                                                </div>  \
                                                <br>    \
                                                <div>   \
                                                <form method="POST" action="indexong.php">  \
                                                <button class="button" type="submit" name="detail" value="'+_ong.ongId+'"><span>Details </span></button>   \
                                                </form> \
                                                </div>  \
                                                </div>  \
                                                ');
                });
                
            }
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