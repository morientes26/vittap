
$(document).ready(function() {

    // function dateTimeZone(date){
    //     if (!moment.tz.zone(name)) {
    //         moment.tz.add('America/Montevideo|MMT -0330 -03 -02 -0230|3I.I 3u 30 20 2u|012121212121212121212121213232323232324242423243232323232323232323232323232323232323232|-20UIf.g 8jzJ.g 1cLu 1dcu 1cLu 1dcu 1cLu ircu 11zu 1o0u 11zu 1o0u 11zu 1qMu WLu 1qMu WLu 1qMu WLu 1qMu 11zu 1o0u 11zu NAu 11bu 2iMu zWu Dq10 19X0 pd0 jz0 cm10 19X0 1fB0 1on0 11d0 1oL0 1nB0 1fzu 1aou 1fzu 1aou 1fzu 3nAu Jb0 3MN0 1SLu 4jzu 2PB0 Lb0 3Dd0 1pb0 ixd0 An0 1MN0 An0 1wp0 On0 1wp0 Rb0 1zd0 On0 1wp0 Rb0 s8p0 1fB0 1ip0 11z0 1ld0 14n0 1o10 11z0 1o10 11z0 1o10 14n0 1ld0 14n0 1ld0 14n0 1o10 11z0 1o10 11z0 1o10 11z0|17e5');
    //         moment.tz.link('America/Montevideo');
    //     }
    //     console.log(date);
    //     console.log(moment(date).tz("America/Montevideo"));
    //     console.log(moment(date).tz("America/Montevideo").format());
    //     return moment(date).tz("America/Montevideo");
    // }

    function isEmpty(obj) {
        for(var key in obj) {
            if(obj.hasOwnProperty(key))
                return false;
        }
        return true;
    }

    function refresh(events){
        var calendar = $('#calendar');
        calendar.fullCalendar('removeEvents');
        calendar.fullCalendar('addEventSource', events);
        calendar.fullCalendar('rerenderEvents' );
    }

    function deleteEvent(id){
        $.ajax({
            url: "/event-calendar/delete/"+id,
            type: "GET",
        }).done(function(events) {
            console.log('delete item');
            refresh(events);
        });
    }

    function cleanModal(){
        $(".modal-body #event-id").val('');
        $(".modal-body #event-name").val('');
        $(".modal-body #event-description").val('');
        $(".modal-title").val("Create event");
        $(".modal-footer button[type='submit']").text("Create");
        $(".modal-footer .js_event-delete").hide();

    }

    // function addEvent(calEvent){
    //     var data = JSON.stringify(calEvent);
    //     $.ajax({
    //         url: "/event-calendar/add",
    //         contentType: 'application/json',
    //         type: "POST",
    //         data: data
    //     }).done(function(events) {
    //         console.log('add item');
    //         refresh(events);
    //     }).fail(function(xhr, status, error) {
    //         console.log(error);
    //     });
    // }

    function loadEventToModal(id){
        $.ajax({
            url: "/event-calendar/data/"+id,
            type: "GET",
        }).done(function(event) {
            $(".modal-body #event-id").val( event.id );
            $(".modal-body #event-start").val(moment(event.start).format('DD.MM.YYYY HH:mm'));
            $(".modal-body #event-duration").val((event.end - event.start) / 60000);
            $(".modal-body #event-name").val( event.name );
            $(".modal-body #event-description").val( event.description );
        });
    }

    $('#calendar').fullCalendar({
        schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
        aspectRatio: 1,
        businessHours: {
            dow: [ 1, 2, 3, 4, 5, 6 ], // Mo - Sa
            start: '09:00', // a start time
            end: '22:00' // an end time
        },
        hiddenDays: [ 0 ],
        firstDay: 1,
        minTime:"09:00",
        maxTIme:"22:00",
        timezone: 'UTC-3',//'America/Montevideo',
        allDaySlot: false,
        defaultView: 'agendaThreeDay',
        editable: false,  // cannot edit event
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaThreeDay,agendaDay'
        },
        eventLimit: true,
        views: {
            agendaThreeDay: {
                type: 'agenda',
                duration: { days: 3 },
                buttonText: '3 day',
                eventLimit: 3
            }
        },
        selectable: true,
        events: "/event-calendar/data",  // read json data from controller
        nowIndicator: true, // show actual hour

        eventClick: function(calEvent, jsEvent, view) {

            var modalStart = $(".js_modal-click");
            modalStart.data('event-object', calEvent);
            modalStart.click();

        },
        // render other flags
        eventRender: function(event, el) {

            // select view type
            var fs = $('.js_show').find(":selected").text();
            var show=1;
            if (fs=='Empty Seats Class'){
                show=2;
            } else if (fs=='Empty Seats Course'){
                show=3;
            }

            if (show==1) {
                if (!isEmpty(event.teacher)) {
                    el.find('.fc-title').after(
                        $('<span> - ' + event.teacher + '</span>')
                    );
                }

                if (event.count > 1) {
                    el.find('.fc-title').after(
                        $('<img class="image" src="/static/img/some.png"/>' +
                            '<span>[' + event.count + ']</span>')
                    );
                } else if (event.count == 1) {
                    el.find('.fc-title').after(
                        $('<img class="image" src="/static/img/one.png"/>' +
                            '<span>[' + event.count + ']</span>')
                    );
                }
            }

            if (show==2){
                if (!isEmpty(event.name)) {
                    el.find('.fc-title').after(
                        $('<span> - ' + event.name + '</span>')
                    );
                }

            }

        },
        select: function (start, end, jsEvent, view) {

            //var abc = prompt('Enter Title of event');
            var newEvent = new Object();
            newEvent.title = 'test default event';
            newEvent.start =  moment(start).format();
            newEvent.end = start.add(1,'hour').format();
            newEvent.allDay = false;
            //addEvent(newEvent);

            var modalStart = $(".js_modal-click");
            modalStart.data('event-object', newEvent);
            modalStart.click();
        }
    });

    //--- binding actions ------

    $("#add").bind('click',  function() {

        var newEvent = new Object();

        newEvent.title = "some text";

        $('#calendar').fullCalendar('renderEvent', newEvent);

    });

    $(".js_filter").keyup(function(){

        var form = $('#filterForm');
        var data = form.serialize();
        var url = form.attr('action');
        var type = form.attr('method');

        $.ajax({
            url: url,
            type: type,
            data: data
        }).done(function(events) {
            refresh(events);
        }).fail(function(xhr, status, error) {
            console.log(error);
        });
    });

    $(".js_show").change(function(){

        var form = $('#filterForm');
        var data = form.serialize();
        var url = form.attr('action');
        var type = form.attr('method');

        $.ajax({
            url: url,
            type: type,
            data: data
        }).done(function(events) {
            refresh(events);
        }).fail(function(xhr, status, error) {
            console.log(error);
        });
    });

    // delete item from calendar
    $(".js_event-delete").bind('click',  function() {

        var event = $(".js_modal-click").data('event-object');
        if (confirm('Are you sure you want to delete event '+ event.title +' ?')) {
            // delete event
            deleteEvent(event.id);
            $('#eventModal').modal('hide');
        }
    });

    // load data to modal after cell click
    $('#eventModal').on('shown.bs.modal', function (e) {

        cleanModal();
        var event = $(e.relatedTarget).data('event-object');
        $(".modal-body #event-start").val( moment(event.start).format('DD.MM.YYYY HH:mm'));

        if (event.id!=null){
            // exist event
            $(".modal-title").val("Edit event");
            $(".modal-footer button[type='submit']").text("Update");
            $(".modal-footer .js_event-delete").show();
            loadEventToModal(event.id);
        }
    });

    // submit modal (create or update event)
    $('#js_modal-form').on('submit', function(event) {
        var $form = $(this);
        var $modal = $('#eventModal');

        $.ajax({
            type: $form.attr('method'),
            url: $form.attr('action'),
            data: $form.serialize(),

            success: function(events) {
                $modal.modal('hide');
                refresh(events);
            }
        });

        event.preventDefault();
    });

    $(document).ready(function(){
        $(":input").inputmask();
    });

});
