$(function(){

    $.ajax({
        type: 'GET',
        url: 'iCare_war_exploded/events',
        success: function(_events)
        {
            $.each(_events,function(i,_event){
                $('#eventWrapper').append(' <div class = "art">   \
                                            <img class="imgEvent" src="..\\img\\city.jpg" />  \
                                            <a class="links" href="'+_event.eventId+'">    \
                                            <h1>' + _event.eventName + '</h1>    \
                                            </a>    \
                                            <p>' + _event.eventType + '</p> \
                                            <button class="button"><span>Details </span></button>   \
                                            </div>  \
                                            ');
            });
            
        }
    });
})